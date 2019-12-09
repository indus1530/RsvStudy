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

        SC.put("RSf3027", bi.RS27a.isChecked() ? "1"
                : bi.RS27b.isChecked() ? "2"
                : bi.RS27c.isChecked() ? "3"
                : bi.RS27d.isChecked() ? "4"
                : bi.RS27e.isChecked() ? "5"
                : bi.RS27f.isChecked() ? "6"
                : bi.RS27g.isChecked() ? "7"
                : bi.RS27h.isChecked() ? "8"
                : bi.RS27i.isChecked() ? "9"
                : bi.RS27j.isChecked() ? "10"
                : bi.RS27k.isChecked() ? "11"
                : bi.RS27l.isChecked() ? "12"
                : bi.RS27m.isChecked() ? "13"
                : bi.RS27n.isChecked() ? "14"
                : bi.RS27o.isChecked() ? "15"
                : bi.RS27p.isChecked() ? "16"
                : bi.RS27q.isChecked() ? "17"
                : bi.RS27r.isChecked() ? "18"
                : bi.RS27s.isChecked() ? "19"
                : bi.RS27t.isChecked() ? "20"
                : bi.RS2796.isChecked() ? "96"
                : "0");
        SC.put("RS2796x", bi.RS2796x.getText().toString());

        SC.put("RSf3028", bi.RS28a.isChecked() ? "1"
                : bi.RS28b.isChecked() ? "2"
                : bi.RS28c.isChecked() ? "3"
                : bi.RS28d.isChecked() ? "4"
                : bi.RS28e.isChecked() ? "5"
                : bi.RS28f.isChecked() ? "6"
                : bi.RS28g.isChecked() ? "7"
                : bi.RS28h.isChecked() ? "8"
                : bi.RS28i.isChecked() ? "9"
                : bi.RS28j.isChecked() ? "10"
                : bi.RS28k.isChecked() ? "11"
                : bi.RS28l.isChecked() ? "12"
                : bi.RS28m.isChecked() ? "13"
                : bi.RS2896.isChecked() ? "96"
                : "0");
        SC.put("RS2896x", bi.RS2896x.getText().toString());

        SC.put("RSf3029", bi.RSf3029a.isChecked() ? "1"
                : bi.RSf3029b.isChecked() ? "2"
                : bi.RSf3029c.isChecked() ? "3"
                : bi.RSf3029d.isChecked() ? "4"
                : bi.RSf3029e.isChecked() ? "5"
                : bi.RSf3029f.isChecked() ? "6"
                : bi.RSf3029g.isChecked() ? "7"
                : bi.RSf3029h.isChecked() ? "8"
                : bi.RSf3029i.isChecked() ? "9"
                : bi.RSf3029j.isChecked() ? "10"
                : bi.RSf3029k.isChecked() ? "11"
                : bi.RSf3029l.isChecked() ? "12"
                : bi.RSf3029m.isChecked() ? "13"
                : bi.RSf3029n.isChecked() ? "14"
                : bi.RSf3029o.isChecked() ? "15"
                : bi.RSf3029p.isChecked() ? "16"
                : bi.RSf3029q.isChecked() ? "17"
                : bi.RSf302996.isChecked() ? "96"
                : "0");
        SC.put("RSf302996x", bi.RSf302996x.getText().toString());

        SC.put("RSf3030", bi.RSf3030a.isChecked() ? "1"
                : bi.RSf3030b.isChecked() ? "2"
                : bi.RSf3030c.isChecked() ? "3"
                : bi.RSf3030d.isChecked() ? "4"
                : bi.RSf3030e.isChecked() ? "5"
                : bi.RSf3030f.isChecked() ? "6"
                : bi.RSf3030g.isChecked() ? "7"
                : bi.RSf303096.isChecked() ? "96"
                : "0");
        SC.put("RSf303096x", bi.RSf303096x.getText().toString());

        SC.put("RSf3031", bi.RSf3031a.isChecked() ? "1"
                : bi.RSf3031b.isChecked() ? "2"
                : bi.RSf3031c.isChecked() ? "3"
                : bi.RSf3031d.isChecked() ? "4"
                : bi.RSf3031e.isChecked() ? "5"
                : bi.RSf3031f.isChecked() ? "6"
                : bi.RSf3031g.isChecked() ? "7"
                : bi.RSf3031h.isChecked() ? "8"
                : bi.RSf3031i.isChecked() ? "9"
                : bi.RSf3031j.isChecked() ? "10"
                : bi.RSf3031k.isChecked() ? "11"
                : bi.RSf3031l.isChecked() ? "12"
                : bi.RSf3031m.isChecked() ? "13"
                : bi.RSf303196.isChecked() ? "96"
                : "0");
        SC.put("RSf303196x", bi.RSf303196x.getText().toString());

        SC.put("RSf30321", bi.RSf30321a.isChecked() ? "1"
                : bi.RSf30321b.isChecked() ? "2"
                : "0");
        SC.put("RSf30322", bi.RSf30322a.isChecked() ? "1"
                : bi.RSf30322b.isChecked() ? "2"
                : "0");
        SC.put("RSf30323", bi.RSf30323a.isChecked() ? "1"
                : bi.RSf30323b.isChecked() ? "2"
                : "0");
        SC.put("RSf30324", bi.RSf30324a.isChecked() ? "1"
                : bi.RSf30324b.isChecked() ? "2"
                : "0");
        SC.put("RSf30325", bi.RSf30325a.isChecked() ? "1"
                : bi.RSf30325b.isChecked() ? "2"
                : "0");
        SC.put("RSf30326", bi.RSf30326a.isChecked() ? "1"
                : bi.RSf30326b.isChecked() ? "2"
                : "0");
        SC.put("RSf30327", bi.RSf30327a.isChecked() ? "1"
                : bi.RSf30327b.isChecked() ? "2"
                : "0");
        SC.put("RSf30328", bi.RSf30328a.isChecked() ? "1"
                : bi.RSf30328b.isChecked() ? "2"
                : "0");
        SC.put("RSf30329", bi.RSf30329a.isChecked() ? "1"
                : bi.RSf30329b.isChecked() ? "2"
                : "0");
        SC.put("RSf303210", bi.RSf303210a.isChecked() ? "1"
                : bi.RSf303210b.isChecked() ? "2"
                : "0");
        SC.put("RSf303211", bi.RSf303211a.isChecked() ? "1"
                : bi.RSf303211b.isChecked() ? "2"
                : "0");
        SC.put("RSf303212", bi.RSf303212a.isChecked() ? "1"
                : bi.RSf303212b.isChecked() ? "2"
                : "0");
        SC.put("RSf303213", bi.RSf303213a.isChecked() ? "1"
                : bi.RSf303213b.isChecked() ? "2"
                : "0");
        SC.put("RSf303214", bi.RSf303214a.isChecked() ? "1"
                : bi.RSf303214b.isChecked() ? "2"
                : "0");
        SC.put("RSf303215", bi.RSf303215a.isChecked() ? "1"
                : bi.RSf303215b.isChecked() ? "2"
                : "0");
        SC.put("RSf303216", bi.RSf303216a.isChecked() ? "1"
                : bi.RSf303216b.isChecked() ? "2"
                : "0");
        SC.put("RSf303217", bi.RSf303217a.isChecked() ? "1"
                : bi.RSf303217b.isChecked() ? "2"
                : "0");
        SC.put("RSf303218", bi.RSf303218a.isChecked() ? "1"
                : bi.RSf303218b.isChecked() ? "2"
                : "0");
        SC.put("RSf303219", bi.RSf303219a.isChecked() ? "1"
                : bi.RSf303219b.isChecked() ? "2"
                : "0");
        SC.put("RSf303220", bi.RSf303220a.isChecked() ? "1"
                : bi.RSf303220b.isChecked() ? "2"
                : "0");
        SC.put("RSf303221", bi.RSf303221a.isChecked() ? "1"
                : bi.RSf303221b.isChecked() ? "2"
                : "0");
        SC.put("RSf303222", bi.RSf303222a.isChecked() ? "1"
                : bi.RSf303222b.isChecked() ? "2"
                : "0");
        SC.put("RSf303223", bi.RSf303223a.isChecked() ? "1"
                : bi.RSf303223b.isChecked() ? "2"
                : "0");
        SC.put("RSf303224", bi.RSf303224a.isChecked() ? "1"
                : bi.RSf303224b.isChecked() ? "2"
                : "0");
        SC.put("RSf303225", bi.RSf303225a.isChecked() ? "1"
                : bi.RSf303225b.isChecked() ? "2"
                : "0");
        SC.put("RSf303226", bi.RSf303226a.isChecked() ? "1"
                : bi.RSf303226b.isChecked() ? "2"
                : "0");
        SC.put("RSf303227", bi.RSf303227a.isChecked() ? "1"
                : bi.RSf303227b.isChecked() ? "2"
                : "0");
        SC.put("RSf303228", bi.RSf303228a.isChecked() ? "1"
                : bi.RSf303228b.isChecked() ? "2"
                : "0");
        SC.put("RSf303229", bi.RSf303229a.isChecked() ? "1"
                : bi.RSf303229b.isChecked() ? "2"
                : "0");
        SC.put("RSf303230", bi.RSf303230a.isChecked() ? "1"
                : bi.RSf303230b.isChecked() ? "2"
                : "0");

        MainApp.fc.setsC(String.valueOf(SC));

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}


