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
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section03Binding;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;


public class Section03Activity extends AppCompatActivity {

    ActivityF1Section03Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section03);
        bi.setCallback(this);

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
                startActivity(new Intent(this, Section031Activity.class));
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

        int updcount = db.updateSC();

        //            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
        //            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        return updcount == 1;
    }

    public boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll03A);
    }

    private void SaveDraft() throws JSONException {

        JSONObject SC = new JSONObject();

        SC.put("RS24", bi.RS24a.isChecked() ? "1"
                : bi.RS24b.isChecked() ? "2"
                : "0");
        SC.put("RS24ax", bi.RS24ax.getText().toString());
        SC.put("RS24bx", bi.RS24bx.getText().toString());
        SC.put("RS25", bi.RS25.getText().toString());
        SC.put("RS26", bi.RS26.getText().toString());

        SC.put("RS27", bi.RS27a.isChecked() ? "11"
                : bi.RS27b.isChecked() ? "12"
                : bi.RS27c.isChecked() ? "13"
                : bi.RS27d.isChecked() ? "14"
                : bi.RS27e.isChecked() ? "15"
                : bi.RS27f.isChecked() ? "21"
                : bi.RS27g.isChecked() ? "22"
                : bi.RS27h.isChecked() ? "23"
                : bi.RS27i.isChecked() ? "24"
                : bi.RS27j.isChecked() ? "25"
                : bi.RS27k.isChecked() ? "26"
                : bi.RS27l.isChecked() ? "31"
                : bi.RS27m.isChecked() ? "32"
                : bi.RS27n.isChecked() ? "33"
                : bi.RS27o.isChecked() ? "34"
                : bi.RS27p.isChecked() ? "35"
                : bi.RS27q.isChecked() ? "36"
                : bi.RS27r.isChecked() ? "37"
                : bi.RS27s.isChecked() ? "38"
                : bi.RS27t.isChecked() ? "39"
                : bi.RS2796.isChecked() ? "96"
                : "0");
        SC.put("RS2796x", bi.RS2796x.getText().toString());

        SC.put("RS28", bi.RS28a.isChecked() ? "11"
                : bi.RS28b.isChecked() ? "12"
                : bi.RS28c.isChecked() ? "21"
                : bi.RS28d.isChecked() ? "22"
                : bi.RS28e.isChecked() ? "23"
                : bi.RS28f.isChecked() ? "24"
                : bi.RS28g.isChecked() ? "31"
                : bi.RS28h.isChecked() ? "32"
                : bi.RS28i.isChecked() ? "33"
                : bi.RS28j.isChecked() ? "34"
                : bi.RS28k.isChecked() ? "35"
                : bi.RS28l.isChecked() ? "36"
                : bi.RS28m.isChecked() ? "37"
                : bi.RS2896.isChecked() ? "96"
                : "0");
        SC.put("RS2896x", bi.RS2896x.getText().toString());

        SC.put("RS29", bi.RS29a.isChecked() ? "11"
                : bi.RS29b.isChecked() ? "12"
                : bi.RS29c.isChecked() ? "13"
                : bi.RS29d.isChecked() ? "14"
                : bi.RS29e.isChecked() ? "15"
                : bi.RS29f.isChecked() ? "21"
                : bi.RS29g.isChecked() ? "22"
                : bi.RS29h.isChecked() ? "23"
                : bi.RS29i.isChecked() ? "24"
                : bi.RS29j.isChecked() ? "25"
                : bi.RS29k.isChecked() ? "26"
                : bi.RS29l.isChecked() ? "31"
                : bi.RS29m.isChecked() ? "32"
                : bi.RS29n.isChecked() ? "33"
                : bi.RS29o.isChecked() ? "34"
                : bi.RS29p.isChecked() ? "35"
                : bi.RS29q.isChecked() ? "36"
                : bi.RS2996.isChecked() ? "96"
                : "0");
        SC.put("RS2996x", bi.RS2996x.getText().toString());

        SC.put("RS30", bi.RS30a.isChecked() ? "11"
                : bi.RS30b.isChecked() ? "12"
                : bi.RS30c.isChecked() ? "13"
                : bi.RS30d.isChecked() ? "14"
                : bi.RS30e.isChecked() ? "15"
                : bi.RS30f.isChecked() ? "16"
                : bi.RS30g.isChecked() ? "17"
                : bi.RS3096.isChecked() ? "96"
                : "0");
        SC.put("RS3096x", bi.RS3096x.getText().toString());

        SC.put("RS31", bi.RS31a.isChecked() ? "11"
                : bi.RS31b.isChecked() ? "12"
                : bi.RS31c.isChecked() ? "13"
                : bi.RS31d.isChecked() ? "14"
                : bi.RS31e.isChecked() ? "15"
                : bi.RS31f.isChecked() ? "21"
                : bi.RS31g.isChecked() ? "22"
                : bi.RS31h.isChecked() ? "23"
                : bi.RS31i.isChecked() ? "31"
                : bi.RS31j.isChecked() ? "41"
                : bi.RS31k.isChecked() ? "51"
                : bi.RS31l.isChecked() ? "61"
                : bi.RS31m.isChecked() ? "71"
                : bi.RS3196.isChecked() ? "96"
                : "0");
        SC.put("RS3196x", bi.RS3196x.getText().toString());

        SC.put("RS321", bi.RS321a.isChecked() ? "1"
                : bi.RS321b.isChecked() ? "2"
                : "0");
        SC.put("RS322", bi.RS322a.isChecked() ? "1"
                : bi.RS322b.isChecked() ? "2"
                : "0");
        SC.put("RS323", bi.RS323a.isChecked() ? "1"
                : bi.RS323b.isChecked() ? "2"
                : "0");
        SC.put("RS324", bi.RS324a.isChecked() ? "1"
                : bi.RS324b.isChecked() ? "2"
                : "0");
        SC.put("RS325", bi.RS325a.isChecked() ? "1"
                : bi.RS325b.isChecked() ? "2"
                : "0");
        SC.put("RS326", bi.RS326a.isChecked() ? "1"
                : bi.RS326b.isChecked() ? "2"
                : "0");
        SC.put("RS327", bi.RS327a.isChecked() ? "1"
                : bi.RS327b.isChecked() ? "2"
                : "0");
        SC.put("RS328", bi.RS328a.isChecked() ? "1"
                : bi.RS328b.isChecked() ? "2"
                : "0");
        SC.put("RS329", bi.RS329a.isChecked() ? "1"
                : bi.RS329b.isChecked() ? "2"
                : "0");
        SC.put("RS3210", bi.RS3210a.isChecked() ? "1"
                : bi.RS3210b.isChecked() ? "2"
                : "0");
        SC.put("RS3211", bi.RS3211a.isChecked() ? "1"
                : bi.RS3211b.isChecked() ? "2"
                : "0");
        SC.put("RS3212", bi.RS3212a.isChecked() ? "1"
                : bi.RS3212b.isChecked() ? "2"
                : "0");
        SC.put("RS3213", bi.RS3213a.isChecked() ? "1"
                : bi.RS3213b.isChecked() ? "2"
                : "0");
        SC.put("RS3214", bi.RS3214a.isChecked() ? "1"
                : bi.RS3214b.isChecked() ? "2"
                : "0");
        SC.put("RS3215", bi.RS3215a.isChecked() ? "1"
                : bi.RS3215b.isChecked() ? "2"
                : "0");
        SC.put("RS3216", bi.RS3216a.isChecked() ? "1"
                : bi.RS3216b.isChecked() ? "2"
                : "0");
        SC.put("RS3217", bi.RS3217a.isChecked() ? "1"
                : bi.RS3217b.isChecked() ? "2"
                : "0");
        SC.put("RS3218", bi.RS3218a.isChecked() ? "1"
                : bi.RS3218b.isChecked() ? "2"
                : "0");
        SC.put("RS3219", bi.RS3219a.isChecked() ? "1"
                : bi.RS3219b.isChecked() ? "2"
                : "0");
        SC.put("RS3220", bi.RS3220a.isChecked() ? "1"
                : bi.RS3220b.isChecked() ? "2"
                : "0");
        SC.put("RS3221", bi.RS3221a.isChecked() ? "1"
                : bi.RS3221b.isChecked() ? "2"
                : "0");
        SC.put("RS3222", bi.RS3222a.isChecked() ? "1"
                : bi.RS3222b.isChecked() ? "2"
                : "0");
        SC.put("RS3223", bi.RS3223a.isChecked() ? "1"
                : bi.RS3223b.isChecked() ? "2"
                : "0");
        SC.put("RS3224", bi.RS3224a.isChecked() ? "1"
                : bi.RS3224b.isChecked() ? "2"
                : "0");
        SC.put("RS3225", bi.RS3225a.isChecked() ? "1"
                : bi.RS3225b.isChecked() ? "2"
                : "0");
        SC.put("RS3226", bi.RS3226a.isChecked() ? "1"
                : bi.RS3226b.isChecked() ? "2"
                : "0");
        SC.put("RS3227", bi.RS3227a.isChecked() ? "1"
                : bi.RS3227b.isChecked() ? "2"
                : "0");
        SC.put("RS3228", bi.RS3228a.isChecked() ? "1"
                : bi.RS3228b.isChecked() ? "2"
                : "0");
        SC.put("RS3229", bi.RS3229a.isChecked() ? "1"
                : bi.RS3229b.isChecked() ? "2"
                : "0");
        SC.put("RS3230", bi.RS3230a.isChecked() ? "1"
                : bi.RS3230b.isChecked() ? "2"
                : "0");

        MainApp.fc.setsC(String.valueOf(SC));

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}


