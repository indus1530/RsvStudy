package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityForm2bTestBinding;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Form2BTest extends AppCompatActivity {

    ActivityForm2bTestBinding bi;
    DatabaseHelper db;
    ChildList item;
    List<View> RS117List;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_form2b_test);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        item = getIntent().getParcelableExtra("data");


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

        JSONObject json = new JSONObject();

        //RST307A
        json.put("RST307A", bi.RST307A.getText().toString());

        //RST307B
        json.put("RST307B", bi.RST307Ba.isChecked() ? "1"
                : bi.RST307Bb.isChecked() ? "2"
                : bi.RST307Bc.isChecked() ? "3"
                : bi.RST307Bd.isChecked() ? "4"
                : bi.RST307Be.isChecked() ? "5"
                : bi.RST307Bf.isChecked() ? "6"
                : bi.RST307Bg.isChecked() ? "7"
                : bi.RST307Bh.isChecked() ? "8"
                : bi.RST307Bi.isChecked() ? "9"
                : bi.RST307Bj.isChecked() ? "10"
                : "0");

        //RST307C
        json.put("RST307C", bi.RST307Ca.isChecked() ? "1"
                : bi.RST307Cb.isChecked() ? "2"
                : "0");

        //RST307D
        json.put("RST307D", bi.RST307D.getText().toString());

        //RST307E
        json.put("RST307E", bi.RST307E.getText().toString());

        //RST307F
        json.put("RST307F", bi.RST307F.getText().toString());

        MainApp.fc.setsB(String.valueOf(json));

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
        return ValidatorClass.EmptyCheckingContainer(this, bi.lltest);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}