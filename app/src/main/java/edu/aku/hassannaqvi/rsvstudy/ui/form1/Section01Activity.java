package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Section01Activity extends AppCompatActivity {

    public static String DOB;
    ActivityF1Section01Binding bi;
    private List<String> dssID, motherName, fatherName, hHhead, studyId;
    private DatabaseHelper db;
    private ChildList cContract;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section01);
        bi.setCallback(this);
        clickListener();

        // bi.RS13.setMinDate(DateUtils.getYearsBack("dd/MM/yyyy", -8));

    }

    /*@Override
    public void afterTextChanged(Editable s) {
        if (bind.A4005.getText().hashCode() == s.hashCode()) {
            if (bind.A4005.getText().toString().trim().length() > 0 && Integer.parseInt(bind.A4005.getText().toString().trim()) > 7) {
                ClearAllcontrol.ClearAllC(bind.A4006cv);
                bind.A4006cv.setVisibility(View.GONE);
            } else {
                bind.A4006cv.setVisibility(View.VISIBLE);
            }
        }

        if (bind.A4011.getText().hashCode() == s.hashCode()) {
            if (bind.A4011.getText().toString().trim().length() > 0 && Integer.parseInt(bind.A4011.getText().toString().trim()) >= 1) {
                ClearAllcontrol.ClearAllC(bind.A4012cv);
                bind.A4012cv.setVisibility(View.GONE);
            } else {
                bind.A4012cv.setVisibility(View.VISIBLE);
            }
        }
    }*/


    private void clickListener() {
        db = new DatabaseHelper(this);

        bi.RSID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                bi.ll0101.setVisibility(View.GONE);
                ClearClass.ClearAllFields(bi.ll0101, false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bi.RS13.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (bi.RS13.getText().toString().isEmpty()) return;

                Long months = DateUtils.ageInMonthsByDOB(DateUtils.getCalendarDate(bi.RS13.getText().toString()));
                bi.RS14.setText(String.valueOf(months));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bi.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!formValidation())
                    return;

                cContract = db.getChildlistBy(bi.RSID.getText().toString());

                if (cContract == null) {
                    Toast.makeText(Section01Activity.this, "Study ID not Found!", Toast.LENGTH_SHORT).show();
                    ClearClass.ClearAllFields(bi.ll0101, false);
                    bi.ll0101.setVisibility(View.GONE);
                    return;
                }
                ClearClass.ClearAllFields(bi.ll0101, true);
                bi.ll0101.setVisibility(View.VISIBLE);
                bi.RS7.setText(cContract.getDssid());
                bi.RS8.setText(cContract.getDssid());
                bi.RS10.setText(cContract.getMother_name());
                bi.RS11.setText(cContract.getFather_name());
                bi.RS12.setText(cContract.getHhhead());
                bi.RS7.setEnabled(false);
                bi.RS8.setEnabled(false);
                bi.RS10.setEnabled(false);
                bi.RS11.setEnabled(false);
                bi.RS12.setEnabled(false);
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
        MainApp.endActivity(this, this);

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

        MainApp.fc.setRef_ID(bi.RSID.getText().toString());
        //MainApp.fc.setCode_lhw(chwCodes.get(bi.RS13.getSelectedItemPosition()));
        //MainApp.fc.setRef_ID(bi.chwcode.getText().toString());

        JSONObject SA = new JSONObject();

        SA.put("RS7", bi.RS7.getText().toString());
        SA.put("RS8", bi.RS8.getText().toString());
        SA.put("RS9", bi.RS9.getText().toString());
        SA.put("RS10", bi.RS10.getText().toString());
        SA.put("RS11", bi.RS11.getText().toString());
        SA.put("RS12", bi.RS12.getText().toString());/*
        SA.put("RS13", bi.RS13.getText().toString());
        SA.put("RS14", bi.RS14.getText().toString());*/

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

