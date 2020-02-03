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

import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.utils.DateUtils;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;
import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section04Binding;

public class Section04Activity extends AppCompatActivity {

    ActivityF1Section04Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section04);
        bi.setCallback(this);


        bi.RS46.setMinDate(DateUtils.convertDateFormat(MainApp.DOB));
        String maxDate = DateUtils.addYearsByDate(DateUtils.getCalendarDate(DateUtils.convertDateFormat(MainApp.DOB)), "dd/MM/yyyy", 1);
        bi.RS46.setMaxDate(maxDate);

        setupSkips();
        /*bi.pocff03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.pocff03a.getId())
                    bi.pocff04.clearCheck();
            }
        });

        bi.pocff06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.pocff06a.getId())
                    bi.pocff07.clearCheck();
            }
        });

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(Section01Activity.DOB));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DOBinMonths = DateUtils.ageInMonthsByDOB(cal);

        if (DOBinMonths >= 6) {
            bi.pocfh03cv.setVisibility(View.VISIBLE);
        } else {
            ClearClass.ClearAllFields(bi.pocfh03cv, null);
            bi.pocfh03cv.setVisibility(View.GONE);
        }

        */

//        bi.RS46.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                long days = DateUtils.ageInDaysByDOB(bi.RS46.getText().toString());
//                bi.RS47.setMaxvalue(days);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

    }


    private void setupSkips() {

        bi.RS41.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS41a.getId()) {
                    bi.RS42cv.setVisibility(View.VISIBLE);
                    bi.RS43cv.setVisibility(View.VISIBLE);
                    bi.RS44cv.setVisibility(View.VISIBLE);
                    bi.RS45cv.setVisibility(View.VISIBLE);
                    bi.RS46cv.setVisibility(View.VISIBLE);
                    bi.RS47cv.setVisibility(View.VISIBLE);
                    bi.RS48cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RS42cv, null);
                    ClearClass.ClearAllFields(bi.RS43cv, null);
                    ClearClass.ClearAllFields(bi.RS44cv, null);
                    ClearClass.ClearAllFields(bi.RS45cv, null);
                    ClearClass.ClearAllFields(bi.RS46cv, null);
                    ClearClass.ClearAllFields(bi.RS47cv, null);
                    ClearClass.ClearAllFields(bi.RS48cv, null);
                    bi.RS42cv.setVisibility(View.GONE);
                    bi.RS43cv.setVisibility(View.GONE);
                    bi.RS44cv.setVisibility(View.GONE);
                    bi.RS45cv.setVisibility(View.GONE);
                    bi.RS46cv.setVisibility(View.GONE);
                    bi.RS47cv.setVisibility(View.GONE);
                    bi.RS48cv.setVisibility(View.GONE);
                }
            }
        });


        bi.RS43.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS43a.getId()) {
                    bi.RS44cv.setVisibility(View.VISIBLE);
                    bi.RS45cv.setVisibility(View.VISIBLE);
                    bi.RS46cv.setVisibility(View.VISIBLE);
                    bi.RS47cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RS44cv, null);
                    ClearClass.ClearAllFields(bi.RS45cv, null);
                    ClearClass.ClearAllFields(bi.RS46cv, null);
                    ClearClass.ClearAllFields(bi.RS47cv, null);
                    bi.RS44cv.setVisibility(View.GONE);
                    bi.RS45cv.setVisibility(View.GONE);
                    bi.RS46cv.setVisibility(View.GONE);
                    bi.RS47cv.setVisibility(View.GONE);
                }
            }
        });


        bi.RS45.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS45a.getId()) {
                    bi.RS46cv.setVisibility(View.VISIBLE);
                    bi.RS47cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RS46cv, null);
                    ClearClass.ClearAllFields(bi.RS47cv, null);
                    bi.RS46cv.setVisibility(View.GONE);
                    bi.RS47cv.setVisibility(View.GONE);
                }
            }
        });


    }


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, Section05Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {

       /* DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSE();

        //            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
        //            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        return updcount == 1;*/
        return true;
    }


    private void SaveDraft() throws JSONException {
        JSONObject SE = new JSONObject();

        SE.put("RS41", bi.RS41a.isChecked() ? "1"
                : bi.RS41b.isChecked() ? "2"
                : "0");

        SE.put("RS421", bi.RS421a.isChecked() ? "1"
                : bi.RS421b.isChecked() ? "2"
                : "0");

        SE.put("RS422", bi.RS422a.isChecked() ? "1"
                : bi.RS422b.isChecked() ? "2"
                : "0");

        SE.put("RS423", bi.RS423a.isChecked() ? "1"
                : bi.RS423b.isChecked() ? "2"
                : "0");

        SE.put("RS424", bi.RS424a.isChecked() ? "1"
                : bi.RS424b.isChecked() ? "2"
                : "0");

        SE.put("RS4296", bi.RS4296a.isChecked() ? "1"
                : bi.RS4296b.isChecked() ? "2"
                : "0");
        SE.put("RS4296ax", bi.RS4296ax.getText().toString());

        SE.put("RS43", bi.RS43a.isChecked() ? "1"
                : bi.RS43b.isChecked() ? "2"
                : "0");

        SE.put("RS441", bi.RS441a.isChecked() ? "1"
                : bi.RS441b.isChecked() ? "2"
                : "0");

        SE.put("RS442", bi.RS442a.isChecked() ? "1"
                : bi.RS442b.isChecked() ? "2"
                : "0");

        SE.put("RS443", bi.RS443a.isChecked() ? "1"
                : bi.RS443b.isChecked() ? "2"
                : "0");

        SE.put("RS444", bi.RS444a.isChecked() ? "1"
                : bi.RS444b.isChecked() ? "2"
                : "0");

        SE.put("RS445", bi.RS445a.isChecked() ? "1"
                : bi.RS445b.isChecked() ? "2"
                : "0");

        SE.put("RS4496", bi.RS4496a.isChecked() ? "1"
                : bi.RS4496b.isChecked() ? "2"
                : "0");
        SE.put("RS4496ax", bi.RS4496ax.getText().toString());

        SE.put("RS45", bi.RS45a.isChecked() ? "1"
                : bi.RS45b.isChecked() ? "2"
                : bi.RS4598.isChecked() ? "98"
                : "0");

        SE.put("RS46", bi.RS46.getText().toString());

        SE.put("RS47", bi.RS47.getText().toString());

        SE.put("RS48", bi.RS48a.isChecked() ? "1"
                : bi.RS48b.isChecked() ? "2"
                : bi.RS4898.isChecked() ? "98"
                : "0");


        /*MainApp.fc.setsE(String.valueOf(SE));*/


    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll04);

    }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
