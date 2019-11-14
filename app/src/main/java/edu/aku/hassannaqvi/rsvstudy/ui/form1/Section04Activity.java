package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section04Binding;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Section04Activity extends AppCompatActivity {

    ActivityF1Section04Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section04);
        bi.setCallback(this);


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

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSD();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {
        JSONObject SD = new JSONObject();

        SD.put("RS41", bi.RS41a.isChecked() ? "1"
                : bi.RS41b.isChecked() ? "2"
                : "0");

        SD.put("RS421", bi.RS421a.isChecked() ? "1"
                : bi.RS421b.isChecked() ? "2"
                : "0");

        SD.put("RS422", bi.RS422a.isChecked() ? "1"
                : bi.RS422b.isChecked() ? "2"
                : "0");

        SD.put("RS423", bi.RS423a.isChecked() ? "1"
                : bi.RS423b.isChecked() ? "2"
                : "0");

        SD.put("RS424", bi.RS424a.isChecked() ? "1"
                : bi.RS424b.isChecked() ? "2"
                : "0");

        SD.put("RS4296", bi.RS4296a.isChecked() ? "1"
                : bi.RS4296b.isChecked() ? "2"
                : "0");
        SD.put("RS4296ax", bi.RS4296ax.getText().toString());

        SD.put("RS43", bi.RS43a.isChecked() ? "1"
                : bi.RS43b.isChecked() ? "2"
                : "0");

        SD.put("RS441", bi.RS441a.isChecked() ? "1"
                : bi.RS441b.isChecked() ? "2"
                : "0");

        SD.put("RS442", bi.RS442a.isChecked() ? "1"
                : bi.RS442b.isChecked() ? "2"
                : "0");

        SD.put("RS443", bi.RS443a.isChecked() ? "1"
                : bi.RS443b.isChecked() ? "2"
                : "0");

        SD.put("RS444", bi.RS444a.isChecked() ? "1"
                : bi.RS444b.isChecked() ? "2"
                : "0");

        SD.put("RS445", bi.RS445a.isChecked() ? "1"
                : bi.RS445b.isChecked() ? "2"
                : "0");

        SD.put("RS4496", bi.RS4496a.isChecked() ? "1"
                : bi.RS4496b.isChecked() ? "2"
                : "0");
        SD.put("RS4496ax", bi.RS4496ax.getText().toString());

        SD.put("RS45", bi.RS45a.isChecked() ? "1"
                : bi.RS45b.isChecked() ? "2"
                : bi.RS4598.isChecked() ? "98"
                : "0");

        SD.put("RS46", bi.RS46.getText().toString());

        SD.put("RS47", bi.RS47.getText().toString());

        SD.put("RS48", bi.RS48a.isChecked() ? "1"
                : bi.RS48b.isChecked() ? "2"
                : bi.RS4898.isChecked() ? "98"
                : "0");


        MainApp.fc.setsD(String.valueOf(SD));


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