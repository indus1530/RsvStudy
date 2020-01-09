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

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section07Binding;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Section07Activity extends AppCompatActivity {

    ActivityF1Section07Binding bi;
    DatabaseHelper db;
    ChildList item;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section07);
        bi.setCallback(this);
        db = new DatabaseHelper(this);

        item = getIntent().getParcelableExtra("data");
        setupSkips();


    }


    private void setupSkips() {

        bi.RS131.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS131b.getId()) {
                    bi.RS132cv.setVisibility(View.VISIBLE);
                    bi.RS133cv.setVisibility(View.VISIBLE);
                    bi.RS134cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RS132cv, null);
                    ClearClass.ClearAllFields(bi.RS133cv, null);
                    ClearClass.ClearAllFields(bi.RS134cv, null);
                    bi.RS132cv.setVisibility(View.GONE);
                    bi.RS133cv.setVisibility(View.GONE);
                    bi.RS134cv.setVisibility(View.GONE);
                }
            }
        });


        bi.RS133.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS133a.getId()) {
                    bi.RS134cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RS134cv, null);
                    bi.RS134cv.setVisibility(View.GONE);
                }
            }
        });


        bi.RS141.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS141a.getId()) {
                    bi.RS142cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RS142cv, null);
                    bi.RS142cv.setVisibility(View.GONE);
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
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }


    private void SaveDraft() throws JSONException {

        /*MainApp.fc = new FormsContract();
        MainApp.fc.setDeviceID(MainApp.deviceId);
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDevicetagID(getSharedPreferences("tagName", MODE_PRIVATE).getString("tagName", ""));
        MainApp.fc.setDSSID(item.getDssid());*/

        JSONObject SF = new JSONObject();

        //RS49
        SF.put("f_type", MainApp.followUp);

        //RS131
        SF.put("RS131", bi.RS131a.isChecked() ? "1"
                : bi.RS131b.isChecked() ? "2"
                : "0");

        //RS132
        SF.put("RS132", bi.RS132a.isChecked() ? "1"
                : bi.RS132b.isChecked() ? "2"
                : bi.RS132c.isChecked() ? "3"
                : bi.RS13296.isChecked() ? "96"
                : "0");
        SF.put("RS13296x", bi.RS13296x.getText().toString());


        //RS133
        SF.put("RS133", bi.RS133a.isChecked() ? "1"
                : bi.RS133b.isChecked() ? "2"
                : "0");

        //RS134
        SF.put("RS134", bi.RS134.getText().toString());


        //RS141
        SF.put("RS141", bi.RS141a.isChecked() ? "1"
                : bi.RS141b.isChecked() ? "2"
                : "0");

        //RS142
        SF.put("RS142", bi.RS142.getText().toString());

        //RS143
        SF.put("RS143", bi.RS143.getText().toString());


        MainApp.fc.setsF(String.valueOf(SF));
        MainApp.setGPS(this);

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


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.llsec07);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
