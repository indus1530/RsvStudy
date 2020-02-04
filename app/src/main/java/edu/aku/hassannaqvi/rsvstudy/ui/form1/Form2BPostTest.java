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
import edu.aku.hassannaqvi.rsvstudy.contracts.FormsAssessmentContract;
import edu.aku.hassannaqvi.rsvstudy.contracts.FormsContract;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityForm2bPosttestBinding;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.ui.other.FormType;
import edu.aku.hassannaqvi.rsvstudy.utils.Constants;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Form2BPostTest extends AppCompatActivity {

    ActivityForm2bPosttestBinding bi;
    DatabaseHelper db;
    ChildList item;
    List<View> RS128List;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());
    FormType formType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_form2b_posttest);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        item = getIntent().getParcelableExtra("data");
        formType = (FormType) getIntent().getExtras().getSerializable(Constants.FORMTYPE);
        setupSkips();


    }


    private void setupSkips() {

        bi.RST401.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RST401a.getId()) {
                    bi.RST402Acv.setVisibility(View.VISIBLE);
                    bi.RST402Bcv.setVisibility(View.VISIBLE);
                    bi.RST402Ccv.setVisibility(View.VISIBLE);
                    bi.RST402Dcv.setVisibility(View.VISIBLE);
                    bi.RST402Ecv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RST402Acv, null);
                    ClearClass.ClearAllFields(bi.RST402Bcv, null);
                    ClearClass.ClearAllFields(bi.RST402Ccv, null);
                    ClearClass.ClearAllFields(bi.RST402Dcv, null);
                    ClearClass.ClearAllFields(bi.RST402Ecv, null);
                    bi.RST402Acv.setVisibility(View.GONE);
                    bi.RST402Bcv.setVisibility(View.GONE);
                    bi.RST402Ccv.setVisibility(View.GONE);
                    bi.RST402Dcv.setVisibility(View.GONE);
                    bi.RST402Ecv.setVisibility(View.GONE);
                }
            }
        });


        bi.RST403.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RST403b.getId()) {
                    bi.RST404cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RST404cv, null);
                    bi.RST404cv.setVisibility(View.GONE);
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
                startActivity(new Intent(this, Form2BTest.class)
                        .putExtra("data", item).putExtra(Constants.FORMTYPE, formType));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }


    private void SaveDraft() throws JSONException {

        MainApp.fac = new FormsAssessmentContract();
        MainApp.fac.setDeviceID(MainApp.deviceId);
        MainApp.fac.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fac.setUser(MainApp.userName);
        MainApp.fac.setFormDate(dtToday);
        MainApp.fac.setDevicetagID(getSharedPreferences("tagName", MODE_PRIVATE).getString("tagName", ""));
        MainApp.fac.setDSSID(item.getDssid());
        MainApp.fac.setFormType("post_test");

        JSONObject json = new JSONObject();

        json.put("RST405A", dtToday);

        //RST401
        json.put("RST401", bi.RST401a.isChecked() ? "1"
                : bi.RST401b.isChecked() ? "2"
                : "0");

        //RST402A
        json.put("RST402A", bi.RST402A.getText().toString());

        //RST402B
        json.put("RST402B", bi.RST402B.getText().toString());

        //RST402C
        json.put("RST402C", bi.RST402C.getText().toString());

        //RST402D
        json.put("RST402D", bi.RST402D.getText().toString());

        //RST402E
        json.put("RST402E", bi.RST402E.getText().toString());


        //RST403
        json.put("RST403", bi.RST403a.isChecked() ? "1"
                : bi.RST403b.isChecked() ? "2"
                : "0");

        //RST404
        json.put("RST404", bi.RST404.getText().toString());

        MainApp.fac.setsA(String.valueOf(json));

    }


    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);
        long updcount = db.addForm(MainApp.fac);

        MainApp.fac.set_ID(String.valueOf(updcount));
        if (updcount != 0) {
            MainApp.fac.set_UID(
                    (MainApp.fac.getDeviceID() + MainApp.fac.get_ID()));
            db.updateAssessmentFormID();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }


    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.llpost);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
