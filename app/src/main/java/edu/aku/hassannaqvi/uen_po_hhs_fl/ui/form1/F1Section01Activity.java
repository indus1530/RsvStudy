package edu.aku.hassannaqvi.uen_po_hhs_fl.ui.form1;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.uen_po_hhs_fl.R;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.FormsContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.LHWContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.TalukasContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.UCsContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.VillagesContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.MainApp;
import edu.aku.hassannaqvi.uen_po_hhs_fl.databinding.ActivityF1Section01Binding;
import edu.aku.hassannaqvi.uen_po_hhs_fl.utils.DateUtils;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ValidatorClass;

public class F1Section01Activity extends AppCompatActivity {

    public static String DOB;
    ActivityF1Section01Binding bi;
    private List<String> ucName, talukaNames, villageNames, lhwNames;
    private List<String> ucCode, talukaCodes, villageCodes, lhwCodes;
    private DatabaseHelper db;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section_01);
        bi.setCallback(this);
        this.setTitle("Form 01 (Case Reporting Form)");
        initializingComponents();

    }

    private void initializingComponents() {
        db = new DatabaseHelper(this);
        populateSpinner(this);

        bi.pocfa12.setMinDate(DateUtils.getYearsBack("dd/MM/yyyy", -5));
    }

    public void populateSpinner(final Context context) {
        // Spinner Drop down elements
        talukaNames = new ArrayList<>();
        talukaCodes = new ArrayList<>();

        talukaNames.add("....");
        talukaCodes.add("....");

        Collection<TalukasContract> dc = db.getAllTalukas();

        for (TalukasContract d : dc) {
            talukaNames.add(d.getTaluka());
            talukaCodes.add(d.getTalukacode());
        }

        bi.pocfa01.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, talukaNames));

        bi.pocfa01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;

                ucCode = new ArrayList<>();
                ucName = new ArrayList<>();

                ucCode.add("....");
                ucName.add("....");

                Collection<UCsContract> pc = db.getAllUCsbyTaluka(talukaCodes.get(position));
                for (UCsContract p : pc) {
                    ucCode.add(p.getUccode());
                    ucName.add(p.getUcs());
                }

                bi.pocfa02.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, ucName));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        bi.pocfa02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;

                villageCodes = new ArrayList<>();
                villageNames = new ArrayList<>();

                villageCodes.add("....");
                villageNames.add("....");

                Collection<VillagesContract> pc =
                        db.getAllPSUsByTaluka(talukaCodes.get(bi.pocfa01.getSelectedItemPosition()),
                                ucCode.get(bi.pocfa02.getSelectedItemPosition()));
                for (VillagesContract p : pc) {
                    villageCodes.add(p.getVillagecode());
                    villageNames.add(p.getVillagename());
                }

                lhwCodes = new ArrayList<>();
                lhwNames = new ArrayList<>();

                lhwCodes.add("....");
                lhwNames.add("....");

                Collection<LHWContract> lhw =
                        db.getAllLHWsByTaluka(talukaCodes.get(bi.pocfa01.getSelectedItemPosition()),
                                ucCode.get(bi.pocfa02.getSelectedItemPosition()));
                for (LHWContract p : lhw) {
                    lhwCodes.add(p.getLhwcode());
                    lhwNames.add(p.getLhwname());
                }

                bi.pocfa03.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, lhwNames));
                bi.pocfa04.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, villageNames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bi.pocfa03.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                bi.lhwcode.setText("LHW Code: " + lhwCodes.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, F1Section02_03Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void BtnEnd() {

        if (!ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpSecA01))
            return;

        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            MainApp.endActivity(this, this);
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {

        long updcount = db.addForm(MainApp.fc);

        MainApp.fc.set_ID(String.valueOf(updcount));
        if (updcount != 0) {
            MainApp.fc.set_UID(
                    (MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        MainApp.fc = new FormsContract();
        MainApp.fc.setDeviceID(MainApp.deviceId);
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setFormType(MainApp.formtype);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDevicetagID(getSharedPreferences("tagName", MODE_PRIVATE).getString("tagName", ""));

        MainApp.fc.setCode_lhw(lhwCodes.get(bi.pocfa03.getSelectedItemPosition()));
        MainApp.fc.setRef_ID(bi.pocfa08.getText().toString());

        JSONObject form01_01 = new JSONObject();

        form01_01.put("pocfa01", talukaCodes.get(bi.pocfa01.getSelectedItemPosition()));
        form01_01.put("pocfa02", ucCode.get(bi.pocfa02.getSelectedItemPosition()));
        form01_01.put("pocfa04", villageCodes.get(bi.pocfa04.getSelectedItemPosition()));
        form01_01.put("pocfa05", bi.pocfa05.getText().toString());
        form01_01.put("pocfa06", bi.pocfa06.getText().toString());
        form01_01.put("pocfa07", bi.pocfa07a.isChecked() ? "1"
                : bi.pocfa07b.isChecked() ? "2"
                : bi.pocfa07c.isChecked() ? "3"
                : bi.pocfa07d.isChecked() ? "4"
                : bi.pocfa07e.isChecked() ? "5"
                : bi.pocfa07f.isChecked() ? "6"
                : bi.pocfa0796.isChecked() ? "96"
                : "0");
        form01_01.put("pocfa0796x", bi.pocfa0796x.getText().toString());
        form01_01.put("pocfa09", bi.pocfa09.getText().toString());
        form01_01.put("pocfa10", bi.pocfa10a.isChecked() ? "1" : bi.pocfa10b.isChecked() ? "2" : "0");
        form01_01.put("pocfa11", bi.pocfa11a.isChecked() ? "1" : bi.pocfa11b.isChecked() ? "2" : "0");
        form01_01.put("pocfa12", bi.pocfa12.getText().toString());
        form01_01.put("pocfa13y", bi.pocfa13y.getText().toString());
        form01_01.put("pocfa13m", bi.pocfa13m.getText().toString());
        form01_01.put("pocfa13d", bi.pocfa13d.getText().toString());
        form01_01.put("pocfa14", bi.pocfa14a.isChecked() ? "1" : bi.pocfa14b.isChecked() ? "2" : bi.pocfa14c.isChecked() ? "3" : "0");

        MainApp.fc.setsA(String.valueOf(form01_01));
        MainApp.setGPS(this);

        DOB = getDOB();

    }


    private String getDOB() {
        if (bi.pocfa11a.isChecked())
            return DateUtils.convertDateFormat(bi.pocfa12.getText().toString());
        else return DateUtils.getDOB("dd/MM/yyyy",
                Integer.valueOf(bi.pocfa13y.getText().toString()),
                Integer.valueOf(bi.pocfa13m.getText().toString()),
                Integer.valueOf(bi.pocfa13d.getText().toString()));
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpSecA01);
    }

}

