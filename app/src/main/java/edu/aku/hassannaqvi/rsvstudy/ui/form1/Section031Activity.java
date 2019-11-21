package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section031Binding;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;


public class Section031Activity extends AppCompatActivity {

    ActivityF1Section031Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section031);
        bi.setCallback(this);
        setListeners();
    }

    private void setListeners() {

        bi.RSf3034.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (bi.RSf3034b.isChecked()) {
                    ClearClass.ClearAllFields(bi.llrsv01, null);
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
                startActivity(new Intent(this, Section04Activity.class));
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

        int updcount = db.updateSD();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll03A);
    }

    private void SaveDraft() throws JSONException {

        JSONObject SD = new JSONObject();

        SD.put("RSf30331", bi.RSf30331a.isChecked() ? "1"
                : bi.RSf30331b.isChecked() ? "2"
                : "0");
        SD.put("RSf30332", bi.RSf30332a.isChecked() ? "1"
                : bi.RSf30332b.isChecked() ? "2"
                : "0");
        SD.put("RSf30333", bi.RSf30333a.isChecked() ? "1"
                : bi.RSf30333b.isChecked() ? "2"
                : "0");
        SD.put("RSf30334", bi.RSf30334a.isChecked() ? "1"
                : bi.RSf30334b.isChecked() ? "2"
                : "0");
        SD.put("RSf30335", bi.RSf30335a.isChecked() ? "1"
                : bi.RSf30335b.isChecked() ? "2"
                : "0");
        SD.put("RSf303396", bi.RSf303396a.isChecked() ? "1"
                : bi.RSf303396b.isChecked() ? "2"
                : "0");
        SD.put("RSf303396x", bi.RSf303396x.getText().toString());

        SD.put("RSf3034", bi.RSf3034a.isChecked() ? "1"
                : bi.RSf3034b.isChecked() ? "2"
                : "0");

        SD.put("RSf3035", bi.RSf3035a.isChecked() ? "1"
                : bi.RSf3035b.isChecked() ? "2"
                : bi.RSf3035c.isChecked() ? "3"
                : bi.RSf3035d.isChecked() ? "4"
                : bi.RSf3035e.isChecked() ? "5"
                : bi.RSf3035f.isChecked() ? "6"
                : "0");

        SD.put("RSf3036", bi.RSf3036a.isChecked() ? "1"
                : bi.RSf3036b.isChecked() ? "2"
                : bi.RSf3036c.isChecked() ? "3"
                : bi.RSf3036d.isChecked() ? "4"
                : "0");

        SD.put("RSf3037", bi.RSf3037a.isChecked() ? "1"
                : bi.RSf3037b.isChecked() ? "2"
                : bi.RSf3037c.isChecked() ? "3"
                : bi.RSf3037d.isChecked() ? "4"
                : bi.RSf3037e.isChecked() ? "5"
                : bi.RSf3037f.isChecked() ? "6"
                : bi.RSf3037g.isChecked() ? "7"
                : bi.RSf303796.isChecked() ? "96"
                : "0");
        SD.put("RSf303796x", bi.RSf303796x.getText().toString());

        SD.put("RSf3038", bi.RSf3038a.isChecked() ? "1"
                : bi.RSf3038b.isChecked() ? "2"
                : bi.RSf3038c.isChecked() ? "3"
                : bi.RSf3038d.isChecked() ? "4"
                : bi.RSf3038e.isChecked() ? "5"
                : bi.RSf303896.isChecked() ? "96"
                : "0");
        SD.put("RSf303896x", bi.RSf303896x.getText().toString());

        SD.put("RSf3039", bi.RSf3039a.isChecked() ? "1"
                : bi.RSf3039b.isChecked() ? "2"
                : bi.RSf3039c.isChecked() ? "3"
                : bi.RSf3039d.isChecked() ? "4"
                : bi.RSf303996.isChecked() ? "96"
                : "0");
        SD.put("RSf303996x", bi.RSf303996x.getText().toString());

        SD.put("RSf3040", bi.RSf3040a.isChecked() ? "1"
                : bi.RSf3040b.isChecked() ? "2"
                : bi.RSf3040c.isChecked() ? "3"
                : "0");

        MainApp.fc.setsD(String.valueOf(SD));

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}


