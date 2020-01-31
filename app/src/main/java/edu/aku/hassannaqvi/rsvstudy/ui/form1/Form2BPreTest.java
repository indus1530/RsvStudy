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
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityForm2bPretestBinding;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Form2BPreTest extends AppCompatActivity {

    ActivityForm2bPretestBinding bi;
    DatabaseHelper db;
    ChildList item;
    List<View> RS117List;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_form2b_pretest);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        item = getIntent().getParcelableExtra("data");
        setupSkips();


    }


    private void setupSkips() {


        bi.RST301.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                ClearClass.ClearAllFields(bi.RST302cv, null);
                ClearClass.ClearAllFields(bi.RST3AScv, null);
//                ClearClass.ClearAllFields(bi.RST303Acv, null);
//                ClearClass.ClearAllFields(bi.RST303Bcv, null);
//                ClearClass.ClearAllFields(bi.RST304Acv, null);
//                ClearClass.ClearAllFields(bi.RST304Bcv, null);
//                ClearClass.ClearAllFields(bi.RST305cv, null);
//                ClearClass.ClearAllFields(bi.RST306cv, null);
                bi.RST302cv.setVisibility(View.GONE);
                bi.RST3AScv.setVisibility(View.GONE);
//                bi.RST303Acv.setVisibility(View.GONE);
//                bi.RST303Bcv.setVisibility(View.GONE);
//                bi.RST304Acv.setVisibility(View.GONE);
//                bi.RST304Bcv.setVisibility(View.GONE);
//                bi.RST305cv.setVisibility(View.GONE);
//                bi.RST306cv.setVisibility(View.GONE);

                if (checkedId == bi.RST301a.getId()) {
                    bi.RST3AScv.setVisibility(View.VISIBLE);
//                    bi.RST303Acv.setVisibility(View.VISIBLE);
//                    bi.RST303Bcv.setVisibility(View.VISIBLE);
//                    bi.RST304Acv.setVisibility(View.VISIBLE);
//                    bi.RST304Bcv.setVisibility(View.VISIBLE);
//                    bi.RST305cv.setVisibility(View.VISIBLE);
//                    bi.RST306cv.setVisibility(View.VISIBLE);
                } else if (checkedId == bi.RST301b.getId()) {
                    bi.RST302cv.setVisibility(View.VISIBLE);
                }
            }
        });


        /*bi.RST305.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RST305b.getId()) {
                    bi.RST306cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RST306cv, null);
                    bi.RST306cv.setVisibility(View.GONE);
                }
            }
        });


        bi.RST316.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RST316a.getId()) {
                    bi.RST317cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RST317cv, null);
                    bi.RST317cv.setVisibility(View.GONE);
                }
            }
        });*/


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
                startActivity(new Intent(this, Form2BTest.class).putExtra("complete", true));

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

        //RST201
        json.put("RST201", bi.RST201.getText().toString());

        //RST202
        json.put("RST202", bi.RST202.getText().toString());

        //RST203
        json.put("RST203", bi.RST203.getText().toString());

        //RST301
        json.put("RST301", bi.RST301a.isChecked() ? "1"
                : bi.RST301b.isChecked() ? "2"
                : "0");

        //RST3AS
        json.put("RST3AS", bi.RST3AS.getText().toString());

        //RST302
        json.put("RST302", bi.RST302.getText().toString());

        //RST303A
        /*json.put("RST303A", bi.RST303A.getText().toString());

        //RST303B
        json.put("RST303B", bi.RST303B.getText().toString());

        //RST304A
        json.put("RST304A", bi.RST304A.getText().toString());

        //RST304B
        json.put("RST304B", bi.RST304B.getText().toString());

        //RST305
        json.put("RST305", bi.RST305a.isChecked() ? "1"
                : bi.RST305b.isChecked() ? "2"
                : "0");

        //RST306
        json.put("RST306", bi.RST306a.isChecked() ? "1"
                : bi.RST306b.isChecked() ? "2"
                : bi.RST306c.isChecked() ? "3"
                : bi.RST306d.isChecked() ? "4"
                : bi.RST30696.isChecked() ? "96"
                : "0");
        json.put("RST30696x", bi.RST30696x.getText().toString());

        //RST316
        json.put("RST316", bi.RST316a.isChecked() ? "1"
                : bi.RST316b.isChecked() ? "2"
                : "0");

        //RST317
        json.put("RST317", bi.RST317.getText().toString());*/

        MainApp.fc.setsA(String.valueOf(json));

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
        return ValidatorClass.EmptyCheckingContainer(this, bi.llpre);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}
