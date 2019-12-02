package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

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

                if (checkedId != bi.RS16a.getId()) {
                    bi.btnContinue.setVisibility(View.GONE);
                    bi.btnEnd.setVisibility(View.VISIBLE);
                } else {
                    bi.btnContinue.setVisibility(View.VISIBLE);
                    bi.btnEnd.setVisibility(View.GONE);
                }
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
                startActivity(new Intent(this, Section02Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
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
                MainApp.endActivity(this, this);
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

        MainApp.fc.setStudy_Id(childData.getDssid());
        //MainApp.fc.setCode_lhw(chwCodes.get(bi.RS13.getSelectedItemPosition()));
        //MainApp.fc.setRef_ID(bi.chwcode.getText().toString());

        JSONObject SA = new JSONObject();

        SA.put("RS7", childData.getDssid());
//        SA.put("RS8", bi.RS8.getText().toString());
        SA.put("RS9", bi.RS9.getText().toString());
        SA.put("RS10", childData.getMother_name());
        SA.put("RS11", childData.getFather_name());
        SA.put("RS12", childData.getHhhead());
        SA.put("RS13", childData.getDob());
        SA.put("RS14", String.valueOf(DateUtils.ageInMonthsByDOB(DateUtils.getDate(childData.getDob()))));
        SA.put("RS15", bi.RS15.getText().toString());
        SA.put("RS16", bi.RS16a.isChecked() ? "1" : bi.RS16b.isChecked() ? "2"
                : bi.RS16c.isChecked() ? "3" : bi.RS1696.isChecked() ? "96" : "0");
        SA.put("RS1696x", bi.RS1696x.getText().toString());

        MainApp.fc.setsA(String.valueOf(SA));
        MainApp.setGPS(this);

        /*DOB = getDOB();*/

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll01);
    }

}

