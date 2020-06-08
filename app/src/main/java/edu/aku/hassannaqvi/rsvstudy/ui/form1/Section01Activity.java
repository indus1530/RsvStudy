package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.contracts.FormsContract;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section01Binding;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.utils.DateUtils;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Section01Activity extends AppCompatActivity {

    public static String DOB;
    ActivityF1Section01Binding bi;
    private List<String> dssID, motherName, fatherName, hHhead, studyId;
    private DatabaseHelper db;
    private ChildList cContract;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    ChildList childData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section01);
        bi.setCallback(this);

        childData = getIntent().getParcelableExtra("data");

        setupViews();
    }

    private void setupViews() {

        bi.dssID.setText(childData.getDssid());
        bi.studyID.setText(childData.getStudy_id());
        bi.fatherName.setText(childData.getFather_name());
        bi.motherName.setText(childData.getMother_name());
        bi.dob.setText(childData.getDob());
        MainApp.DOB = childData.getDob();
        bi.gender.setText(childData.equals("1") ? "Male" : "Female");
        bi.genderImage.setImageResource(childData.getGender().equals("1") ? R.drawable.boy : R.drawable.girl);
        bi.months.setText(String.valueOf(DateUtils.ageInMonthsByDOB(DateUtils.getDate(childData.getDob()))));


        bi.RS16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == bi.RS16c.getId() || checkedId == bi.RS16b.getId()) {
                    MainApp.status = 3;
                }
                if (checkedId == bi.RS16d.getId()) {
                    MainApp.status = 5;
                }
                if (checkedId == bi.RS16e.getId()) {
                    MainApp.status = 6;
                }
            }
        });

        bi.RS15.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //calculating age in months w.r.t visit date
                Long afterVisitDate = DateUtils.dobDiff(DateUtils.getCalDate(childData.getDob()), DateUtils.getCalDate(bi.RS15.getText().toString()));
//                Long actualDOB = DateUtils.ageInMonthsByDOB(DateUtils.getCalDate(childData.getDob()));
                bi.dobInMonths.setText(afterVisitDate + " month(s)");


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
                if (bi.RS16a.isChecked()) {
                    finish();
                    startActivity(new Intent(this, Section02Activity.class));
                } else {
                    Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    public void BtnEnd() {
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


    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);
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

        MainApp.fc.setDSSID(childData.getDssid());
        //MainApp.fc.setCode_lhw(chwCodes.get(bi.RS13.getSelectedItemPosition()));
        //MainApp.fc.setRef_ID(bi.chwcode.getText().toString());

        JSONObject SA = new JSONObject();

        SA.put("RS07", childData.getDssid());
//        SA.put("RS08", bi.RS8.getText().toString());
        SA.put("RS09", bi.RS9.getText().toString());
        SA.put("RS10", childData.getMother_name());
        SA.put("RS11", childData.getFather_name());
        SA.put("RS12", childData.getHhhead());
        SA.put("RS13", childData.getDob());
        SA.put("RS14", bi.months.getText().toString());
        SA.put("RS15", bi.RS15.getText().toString());
        SA.put("RS16", bi.RS16a.isChecked() ? "1"
                : bi.RS16b.isChecked() ? "2"
                : bi.RS16c.isChecked() ? "3"
                : bi.RS16d.isChecked() ? "4"
                : bi.RS1696.isChecked() ? "96" : "0");
        SA.put("RS1696x", bi.RS1696x.getText().toString());

        MainApp.fc.setsA(String.valueOf(SA));
        MainApp.setGPS(this);

        /*DOB = getDOB();*/

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll01);
    }

}

