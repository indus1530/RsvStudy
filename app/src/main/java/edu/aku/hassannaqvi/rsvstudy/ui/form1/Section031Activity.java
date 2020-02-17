package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.rsvstudy.R;
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

        bi.RS34.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (bi.RS34b.isChecked()) {
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

      /*  DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSD();

        //            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
        //            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        return updcount == 1;*/

        return true;
    }

    public boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll03A);
    }

    private void SaveDraft() throws JSONException {

        JSONObject SD = new JSONObject();

        SD.put("RS331", bi.RS331a.isChecked() ? "1"
                : bi.RS331b.isChecked() ? "2"
                : "0");
        SD.put("RS332", bi.RS332a.isChecked() ? "1"
                : bi.RS332b.isChecked() ? "2"
                : "0");
        SD.put("RS333", bi.RS333a.isChecked() ? "1"
                : bi.RS333b.isChecked() ? "2"
                : "0");
        SD.put("RS334", bi.RS334a.isChecked() ? "1"
                : bi.RS334b.isChecked() ? "2"
                : "0");
        SD.put("RS335", bi.RS335a.isChecked() ? "1"
                : bi.RS335b.isChecked() ? "2"
                : "0");
        SD.put("RS3396", bi.RS3396a.isChecked() ? "1"
                : bi.RS3396b.isChecked() ? "2"
                : "0");
        SD.put("RS3396x", bi.RS3396x.getText().toString());

        SD.put("RS34", bi.RS34a.isChecked() ? "1"
                : bi.RS34b.isChecked() ? "2"
                : "0");

        SD.put("RS35", bi.RS35a.isChecked() ? "1"
                : bi.RS35b.isChecked() ? "2"
                : bi.RS35c.isChecked() ? "3"
                : bi.RS35d.isChecked() ? "4"
                : bi.RS35e.isChecked() ? "5"
                : bi.RS35f.isChecked() ? "6"
                : "0");

        SD.put("RS36", bi.RS36a.isChecked() ? "1"
                : bi.RS36b.isChecked() ? "2"
                : bi.RS36c.isChecked() ? "3"
                : bi.RS36d.isChecked() ? "4"
                : "0");

        SD.put("RS37", bi.RS37a.isChecked() ? "11"
                : bi.RS37b.isChecked() ? "21"
                : bi.RS37c.isChecked() ? "31"
                : bi.RS37d.isChecked() ? "41"
                : bi.RS37e.isChecked() ? "42"
                : bi.RS37f.isChecked() ? "43"
                : bi.RS37g.isChecked() ? "51"
                : bi.RS3796.isChecked() ? "96"
                : "0");
        SD.put("RS3796x", bi.RS3796x.getText().toString());

        SD.put("RS38", bi.RS38a.isChecked() ? "1"
                : bi.RS38b.isChecked() ? "2"
                : bi.RS38c.isChecked() ? "3"
                : bi.RS38d.isChecked() ? "4"
                : bi.RS38e.isChecked() ? "5"
                : bi.RS3896.isChecked() ? "96"
                : "0");
        SD.put("RS3896x", bi.RS3896x.getText().toString());

        SD.put("RS39", bi.RS39a.isChecked() ? "1"
                : bi.RS39b.isChecked() ? "2"
                : bi.RS39c.isChecked() ? "3"
                : bi.RS39d.isChecked() ? "4"
                : bi.RS3996.isChecked() ? "96"
                : "0");
        SD.put("RS3996x", bi.RS3996x.getText().toString());

        SD.put("RS40", bi.RS40a.isChecked() ? "1"
                : bi.RS40b.isChecked() ? "2"
                : bi.RS40c.isChecked() ? "3"
                : "0");

//        MainApp.fc.setsD(String.valueOf(SD));

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}


