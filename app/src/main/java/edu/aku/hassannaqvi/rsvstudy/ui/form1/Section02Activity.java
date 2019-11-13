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

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section02Binding;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Section02Activity extends AppCompatActivity {

    ActivityF1Section02Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section02);
        bi.setCallback(this);
        //this.setTitle("RSV Study section 2");
        setupSkips();

    }


    private void setupSkips() {

        bi.RS17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS17b.getId()) {
                    bi.RS18cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RS18cv, null);
                    bi.RS18cv.setVisibility(View.GONE);
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
                startActivity(new Intent(this, Section03Activity.class));
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

        int updcount = db.updateSB();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject SB = new JSONObject();

        SB.put("RS17", bi.RS17a.isChecked() ? "1"
                : bi.RS17b.isChecked() ? "2" : "0");

        SB.put("RS18", bi.RS18a.isChecked() ? "1"
                : bi.RS18b.isChecked() ? "2"
                : bi.RS18c.isChecked() ? "3"
                : bi.RS18d.isChecked() ? "4"
                : bi.RS18e.isChecked() ? "5"
                : bi.RS18f.isChecked() ? "6" : "0");

        SB.put("RS19", bi.RS19a.isChecked() ? "1"
                : bi.RS19b.isChecked() ? "2"
                : bi.RS19c.isChecked() ? "3"
                : bi.RS19d.isChecked() ? "4"
                : bi.RS19e.isChecked() ? "5"
                : bi.RS19f.isChecked() ? "6"
                : bi.RS19g.isChecked() ? "7"
                : bi.RS19h.isChecked() ? "8"
                : bi.RS1996.isChecked() ? "96" : "0");
        SB.put("RS19x", bi.RS1996x.getText().toString());

        SB.put("RS20", bi.RS20a.isChecked() ? "1"
                : bi.RS20b.isChecked() ? "2"
                : bi.RS20c.isChecked() ? "3"
                : bi.RS20d.isChecked() ? "4"
                : bi.RS2096.isChecked() ? "96" : "0");
        SB.put("RS20x", bi.RS2096x.getText().toString());

        SB.put("RS21", bi.RS21a.isChecked() ? "1"
                : bi.RS21b.isChecked() ? "2" : "0");

        SB.put("RS22", bi.RS22.getText().toString());


        MainApp.fc.setsB(String.valueOf(SB));


    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll02);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
