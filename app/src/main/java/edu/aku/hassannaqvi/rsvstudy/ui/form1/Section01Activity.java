package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.FormsContract;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section01Binding;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Section01Activity extends AppCompatActivity {

    public static String DOB;
    ActivityF1Section01Binding bi;
    private List<String> ucName, talukaNames, villageNames, chwNames;
    private List<String> ucCode, talukaCodes, villageCodes, chwCodes;
    private DatabaseHelper db;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section01);
        bi.setCallback(this);
        //this.setTitle("RSV Study section 1");
        //initializingComponents();

    }

    private void initializingComponents() {
        db = new DatabaseHelper(this);
        //populateSpinner(this);

        //bi.pocfa12.setMinDate(DateUtils.getYearsBack("dd/MM/yyyy", -5));
    }

    /*public void populateSpinner(final Context context) {
        // Spinner Drop down elements
        chwNames = new ArrayList<>();
        chwCodes = new ArrayList<>();

        chwNames.add("....");
        chwCodes.add("....");

        Collection<LHWContract> dc = db.getAllLhw();

        for (LHWContract d : dc) {
            chwNames.add(d.getLhwname());
            chwCodes.add(d.getLhwcode());
        }

        bi.RS13.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, chwNames));

        bi.RS13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                bi.chwcode.setText("LHW Code: " + chwCodes.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }*/

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, Section02Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void BtnEnd() {

        if (!ValidatorClass.EmptyCheckingContainer(this, bi.ll01))
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

        //DatabaseHelper db = new DatabaseHelper(this);
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
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDevicetagID(getSharedPreferences("tagName", MODE_PRIVATE).getString("tagName", ""));

        MainApp.fc.setRef_ID(bi.RSID.getText().toString());
        //MainApp.fc.setCode_lhw(chwCodes.get(bi.RS13.getSelectedItemPosition()));
        //MainApp.fc.setRef_ID(bi.chwcode.getText().toString());

        JSONObject SA = new JSONObject();

        SA.put("RS7", bi.RS7.getText().toString());
        SA.put("RS8", bi.RS8.getText().toString());
        SA.put("RS9", bi.RS9.getText().toString());
        SA.put("RS10", bi.RS10.getText().toString());
        SA.put("RS11", bi.RS11.getText().toString());
        SA.put("RS12", bi.RS12.getText().toString());

        MainApp.fc.setsA(String.valueOf(SA));
        MainApp.setGPS(this);

        /*DOB = getDOB();*/

    }


    /*private String getDOB() {
        if (bi.pocfa11a.isChecked())
            return DateUtils.convertDateFormat(bi.pocfa12.getText().toString());
        else return DateUtils.getDOB("dd/MM/yyyy",
                Integer.valueOf(bi.pocfa13y.getText().toString()),
                Integer.valueOf(bi.pocfa13m.getText().toString()),
                Integer.valueOf(bi.pocfa13d.getText().toString()));
    }*/

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll01);
    }

}

