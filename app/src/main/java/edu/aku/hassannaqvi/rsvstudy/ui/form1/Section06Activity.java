package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section06Binding;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Section06Activity extends AppCompatActivity {

    ActivityF1Section06Binding bi;
    DatabaseHelper db;
    ChildList item;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section06);
        bi.setCallback(this);
        db = new DatabaseHelper(this);

        item = getIntent().getParcelableExtra("data");


    }


    public void BtnPreTest() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, Form2BPreTest.class).putExtra("complete", true));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void BtnPostTest() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, Form2BPostTest.class).putExtra("complete", true));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void SaveDraft() throws JSONException {

       /* MainApp.fc = new FormsContract();
        MainApp.fc.setDeviceID(MainApp.deviceId);
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDevicetagID(getSharedPreferences("tagName", MODE_PRIVATE).getString("tagName", ""));
        MainApp.fc.setDSSID(item.getDssid());*/

        JSONObject SF = new JSONObject();

        //RS49
        SF.put("f_type", MainApp.followUp);

        //RS101
        SF.put("RS101", bi.RS101.getText().toString());

        //RS102
        SF.put("RS102", bi.RS102.getText().toString());

        //RS103
        SF.put("RS103", bi.RS103.getText().toString());


        //MainApp.fc.setsF(String.valueOf(SF));
        //MainApp.setGPS(this);

    }


    private boolean UpdateDB() {

        /*DatabaseHelper db = new DatabaseHelper(this);
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
        }*/

        return true;


    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.llsec06);
    }

    /*public void BtnEnd() {
        MainApp.endActivity(this, this);
    }*/


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
