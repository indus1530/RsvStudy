package edu.aku.hassannaqvi.rsvstudy.ui.form3;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildrenContract;
import edu.aku.hassannaqvi.rsvstudy.contracts.FormsContract;
import edu.aku.hassannaqvi.rsvstudy.contracts.LHWContract;
import edu.aku.hassannaqvi.rsvstudy.contracts.TalukasContract;
import edu.aku.hassannaqvi.rsvstudy.contracts.UCsContract;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF3Section01Binding;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.utils.DateUtils;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;


public class F3Section01Activity extends AppCompatActivity {

    ActivityF3Section01Binding bi;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());
    private List<String> talukaNames, ucName, lhwNames;
    private List<String> talukaCodes, ucCode, lhwCodes;
    private DatabaseHelper db;
    private ChildrenContract cContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f3_section01);
        bi.setCallback(this);
        this.setTitle("Form 03 (Referral Form)");
        events_call();
        clickListener();

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

        bi.pofi001.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, talukaNames));

        bi.pofi001.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                bi.pofi002.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, ucName));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        bi.pofi002.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;

                    /*villageCodes = new ArrayList<>();
                    villageNames = new ArrayList<>();

                    villageCodes.add("....");
                    villageNames.add("....");

                    Collection<VillagesContract> pc =
                            db.getAllPSUsByTaluka(talukaCodes.get(bi.pocfa01.getSelectedItemPosition()),
                                    ucCode.get(bi.pocfa02.getSelectedItemPosition()));
                    for (VillagesContract p : pc) {
                        villageCodes.add(p.getVillagecode());
                        villageNames.add(p.getVillagename());
                    }*/

                lhwCodes = new ArrayList<>();
                lhwNames = new ArrayList<>();

                lhwCodes.add("....");
                lhwNames.add("....");

                Collection<LHWContract> lhw =
                        db.getAllLHWsByTaluka(talukaCodes.get(bi.pofi001.getSelectedItemPosition()),
                                ucCode.get(bi.pofi002.getSelectedItemPosition()));
                for (LHWContract p : lhw) {
                    lhwCodes.add(p.getLhwcode());
                    lhwNames.add(p.getLhwname());
                }

                bi.pofi003.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, lhwNames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bi.pofi003.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                bi.lhwcode.setText("LHW Code: " + lhwCodes.get(i));
                ClearClass.ClearAllFields(bi.llform03, null);
                bi.llform03.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void clickListener() {

        bi.checkHHBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!formValidation())
                    return;

                cContract = db.getChildById(lhwCodes.get(bi.pofi003.getSelectedItemPosition()), bi.pofi00.getText().toString());

                if (cContract == null)
                    cContract = db.getChildById("f1", lhwCodes.get(bi.pofi003.getSelectedItemPosition()), bi.pofi00.getText().toString());

                if (cContract == null) {
                    Toast.makeText(F3Section01Activity.this, "Referral ID not Found!", Toast.LENGTH_SHORT).show();
                    ClearClass.ClearAllFields(bi.llform03, false);
                    bi.llform03.setVisibility(View.GONE);
                    return;
                }
                ClearClass.ClearAllFields(bi.llform03, true);
                bi.llform03.setVisibility(View.VISIBLE);
                bi.pofi004.setText(cContract.getChild_name());
                bi.pofi005.setText(cContract.getF_name());
                bi.pofi004.setEnabled(false);
                bi.pofi005.setEnabled(false);


            }
        });

        bi.pofi00.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                bi.llform03.setVisibility(View.GONE);
                ClearClass.ClearAllFields(bi.llform03, false);
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {

        if (!ValidatorClass.EmptyCheckingContainer(this, bi.llF3S1B))
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

    public boolean formValidation() {

        return ValidatorClass.EmptyCheckingContainer(this, bi.llF3S1A);
    }

    private void SaveDraft() throws JSONException {

        MainApp.fc = new FormsContract();
        MainApp.fc.setDeviceID(MainApp.deviceId);
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setFormType(MainApp.formtype);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDevicetagID(getSharedPreferences("tagName", MODE_PRIVATE).getString("tagName", ""));

        MainApp.fc.setCode_lhw(lhwCodes.get(bi.pofi003.getSelectedItemPosition()));
        MainApp.fc.setRef_ID(bi.pofi00.getText().toString());

        JSONObject form03_01 = new JSONObject();

        form03_01.put("pofi001", talukaCodes.get(bi.pofi001.getSelectedItemPosition()));
        form03_01.put("pofi002", ucCode.get(bi.pofi002.getSelectedItemPosition()));
        //form03_01.put("pofi01", bi.pofi01.getText().toString());

        form03_01.put("pofi004", bi.pofi004.getText().toString());
        form03_01.put("pofi005", bi.pofi005.getText().toString());

        form03_01.put("pofi02", bi.pofi02a.isChecked() ? "1"
                : bi.pofi02b.isChecked() ? "2"
                : "0");

        form03_01.put("pofi03a", bi.pofi03a.isChecked() ? "1" : "0");
        form03_01.put("pofi03b", bi.pofi03b.isChecked() ? "2" : "0");
        form03_01.put("pofi03c", bi.pofi03c.isChecked() ? "3" : "0");
        form03_01.put("pofi0396", bi.pofi0396.isChecked() ? "96" : "0");
        form03_01.put("pofi0396x", bi.pofi0396x.getText().toString());

        form03_01.put("pofi04", bi.pofi04a.isChecked() ? "1"
                : bi.pofi04b.isChecked() ? "2"
                : "0");

        form03_01.put("pofi05a", bi.pofi05a.isChecked() ? "1" : "0");
        form03_01.put("pofi05b", bi.pofi05b.isChecked() ? "2" : "0");
        form03_01.put("pofi05c", bi.pofi05c.isChecked() ? "3" : "0");
        form03_01.put("pofi05d", bi.pofi05d.isChecked() ? "4" : "0");
        form03_01.put("pofi0596", bi.pofi0596.isChecked() ? "96" : "0");
        form03_01.put("pofi0596x", bi.pofi0596x.getText().toString());

        form03_01.put("pofi051", bi.pofi051a.isChecked() ? "1"
                : bi.pofi051b.isChecked() ? "2"
                : "0");
        form03_01.put("pofi051ax", bi.pofi051ax.getText().toString());

        form03_01.put("pofi06", bi.pofi06a.isChecked() ? "1"
                : bi.pofi06b.isChecked() ? "2"
                : "0");
        form03_01.put("pofi06ax", bi.pofi06ax.getText().toString());
        form03_01.put("pofi06ay", bi.pofi06ay.getText().toString());

        form03_01.put("pofi07", bi.pofi07a.isChecked() ? "1"
                : bi.pofi07b.isChecked() ? "2"
                : bi.pofi0797.isChecked() ? "97"
                : "0");

        form03_01.put("pofi08", bi.pofi08a.isChecked() ? "1"
                : bi.pofi08b.isChecked() ? "2"
                : bi.pofi0896.isChecked() ? "96"
                : "0");
        form03_01.put("pofi0896x", bi.pofi0896x.getText().toString());

        form03_01.put("pofi09", bi.pofi09a.isChecked() ? "1"
                : bi.pofi09b.isChecked() ? "2"
                : bi.pofi0997.isChecked() ? "97"
                : "0");

        form03_01.put("pofi10", bi.pofi10a.isChecked() ? "1"
                : bi.pofi10b.isChecked() ? "2"
                : "0");

        form03_01.put("pofi101", bi.pofi101a.isChecked() ? "1"
                : bi.pofi101b.isChecked() ? "2"
                : "0");

        form03_01.put("pofi11", bi.pofi11a.isChecked() ? "1"
                : bi.pofi11b.isChecked() ? "2"
                : "0");
        form03_01.put("pofi11ax", bi.pofi11ax.getText().toString());

        form03_01.put("pofi12", bi.pofi12.getText().toString());

        form03_01.put("pofi13a", bi.pofi13a.isChecked() ? "1" : "0");
        form03_01.put("pofi13b", bi.pofi13b.isChecked() ? "2" : "0");
        form03_01.put("pofi13c", bi.pofi13c.isChecked() ? "3" : "0");
        form03_01.put("pofi13d", bi.pofi13d.isChecked() ? "4" : "0");
        form03_01.put("pofi13e", bi.pofi13e.isChecked() ? "5" : "0");
        form03_01.put("pofi13f", bi.pofi13f.isChecked() ? "6" : "0");
        form03_01.put("pofi1396", bi.pofi1396.isChecked() ? "96" : "0");
        form03_01.put("pofi1396x", bi.pofi1396x.getText().toString());

        form03_01.put("pofi14a", bi.pofi14a.isChecked() ? "1" : "0");
        form03_01.put("pofi14b", bi.pofi14b.isChecked() ? "2" : "0");
        form03_01.put("pofi14c", bi.pofi14c.isChecked() ? "3" : "0");
        form03_01.put("pofi14d", bi.pofi14d.isChecked() ? "4" : "0");
        form03_01.put("pofi14e", bi.pofi14e.isChecked() ? "5" : "0");
        form03_01.put("pofi14f", bi.pofi14f.isChecked() ? "6" : "0");
        form03_01.put("pofi14g", bi.pofi14g.isChecked() ? "7" : "0");
        form03_01.put("pofi14h", bi.pofi14h.isChecked() ? "8" : "0");
        form03_01.put("pofi14i", bi.pofi14i.isChecked() ? "9" : "0");
        form03_01.put("pofi1496", bi.pofi1496.isChecked() ? "96" : "0");
        form03_01.put("pofi1496x", bi.pofi1496x.getText().toString());

        form03_01.put("pofi15", bi.pofi15a.isChecked() ? "1"
                : bi.pofi15b.isChecked() ? "2"
                : "0");

        MainApp.fc.setsA(String.valueOf(form03_01));
        MainApp.setGPS(this);
    }


    void events_call() {

        db = new DatabaseHelper(this);
        populateSpinner(this);

        bi.pofi02.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.pofi02a.getId()) {
                    ClearClass.ClearAllFields(bi.pofi03cv, null);
                    bi.pofi03cv.setVisibility(View.GONE);
                } else {
                    bi.pofi03cv.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.pofi04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.pofi04a.getId()) {
                    ClearClass.ClearAllFields(bi.pofi05cv, null);
                    bi.pofi05cv.setVisibility(View.GONE);
                } else {
                    bi.pofi05cv.setVisibility(View.VISIBLE);
                }
            }
        });

        //bi.pofi01.setMinDate(DateUtils.getMonthsBack("dd/MM/yyyy", -6));
        bi.pofi12.setMinDate(DateUtils.getYearsBack("dd/MM/yyyy", -5));

        bi.pofi07.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                ClearClass.ClearAllFields(bi.cvpofi08, null);
                ClearClass.ClearAllFields(bi.cvpofi09, null);
                bi.cvpofi08.setVisibility(View.GONE);
                bi.cvpofi09.setVisibility(View.GONE);

                if (checkedId == bi.pofi07a.getId()) {
                    bi.cvpofi09.setVisibility(View.VISIBLE);
                } else if (checkedId == bi.pofi07b.getId()) {
                    bi.cvpofi08.setVisibility(View.VISIBLE);
                }
            }
        });


       /* bi.pofi05.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (!bi.pofi05a.isChecked()) {
                    bi.pofi06.clearCheck();
                }
            }
        });*/


        /*bi.pofi09.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bi.pofi09b.isChecked()) {
                    ClearClass.ClearAllFields(bi.fldGrppofi11, null);
                }
            }
        });*/

        bi.pofi101.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                ClearClass.ClearAllFields(bi.pofi11cv, null);
                ClearClass.ClearAllFields(bi.pofi12cv, null);
                ClearClass.ClearAllFields(bi.pofi13cv, null);
                ClearClass.ClearAllFields(bi.pofi14cv, null);
                bi.pofi11cv.setVisibility(View.GONE);
                bi.pofi12cv.setVisibility(View.GONE);
                bi.pofi13cv.setVisibility(View.GONE);
                bi.pofi14cv.setVisibility(View.GONE);

                if (checkedId == bi.pofi101a.getId()) {
                    bi.pofi11cv.setVisibility(View.VISIBLE);
                    bi.pofi12cv.setVisibility(View.VISIBLE);
                    bi.pofi13cv.setVisibility(View.VISIBLE);
                } else if (checkedId == bi.pofi101b.getId()) {
                    bi.pofi14cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.pofi11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                ClearClass.ClearAllFields(bi.pofi12cv, null);
                ClearClass.ClearAllFields(bi.pofi13cv, null);
                ClearClass.ClearAllFields(bi.pofi14cv, null);
                bi.pofi12cv.setVisibility(View.GONE);
                bi.pofi13cv.setVisibility(View.GONE);
                bi.pofi14cv.setVisibility(View.GONE);

                if (checkedId == bi.pofi11a.getId()) {
                    bi.pofi12cv.setVisibility(View.VISIBLE);
                    bi.pofi13cv.setVisibility(View.VISIBLE);
                } else if (checkedId == bi.pofi11b.getId()) {
                    bi.pofi14cv.setVisibility(View.VISIBLE);
                }
            }
        });

    }

}


