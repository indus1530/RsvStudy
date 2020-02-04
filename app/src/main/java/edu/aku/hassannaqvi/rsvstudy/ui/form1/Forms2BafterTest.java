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
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityForms2bAfterTestBinding;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.ui.other.FormType;
import edu.aku.hassannaqvi.rsvstudy.utils.Constants;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Forms2BafterTest extends AppCompatActivity {

    ActivityForms2bAfterTestBinding bi;
    DatabaseHelper db;
    ChildList item;
    List<View> RS128List;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());
    FormType formType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_forms2b_after_test);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        item = getIntent().getParcelableExtra("data");
        formType = (FormType) getIntent().getExtras().getSerializable(Constants.FORMTYPE);


        setUIComponent();
        setupSkips();


    }

    private void setUIComponent() {
        bi.forPostTest.setVisibility(formType == FormType.PRETEST ? View.GONE : View.VISIBLE);
        bi.RST406A.setText(String.valueOf(MainApp.testCount));
        bi.RST406B.setText(String.valueOf(MainApp.acceptableTest));

        if (MainApp.acceptableTest >= 3) {
            bi.RST407a.setChecked(true);
        } else {
            bi.RST407b.setChecked(true);
        }
    }


    private void setupSkips() {


        bi.RST407.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RST407b.getId()) {
                    bi.RST408cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RST408cv, null);
                    bi.RST408cv.setVisibility(View.GONE);
                }
            }
        });


        bi.RST417.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RST417a.getId()) {
                    bi.RST418cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RST418cv, null);
                    bi.RST418cv.setVisibility(View.GONE);
                }
            }
        });


        bi.RST501.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RST501b.getId()) {
                    bi.RST502cv.setVisibility(View.VISIBLE);
                    bi.RST503cv.setVisibility(View.VISIBLE);
                    bi.RST504cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RST502cv, null);
                    ClearClass.ClearAllFields(bi.RST503cv, null);
                    ClearClass.ClearAllFields(bi.RST504cv, null);
                    bi.RST502cv.setVisibility(View.GONE);
                    bi.RST503cv.setVisibility(View.GONE);
                    bi.RST504cv.setVisibility(View.GONE);
                }
            }
        });


        bi.RST503.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RST503a.getId()) {
                    bi.RST504cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RST504cv, null);
                    bi.RST504cv.setVisibility(View.GONE);
                }
            }
        });


        bi.RST600.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RST600a.getId()) {
                    bi.RST601cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.RST601cv, null);
                    bi.RST601cv.setVisibility(View.GONE);
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
                startActivity(new Intent(this, EndingActivity.class)
                        .putExtra(Constants.FORMTYPE, formType)
                        .putExtra("complete", true));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }


    private void SaveDraft() throws JSONException {

//        MainApp.fc.setEndingdatetime(dtToday);


        JSONObject json = new JSONObject();

        //RST405A
        json.put("RST405A", dtToday);

/*        //RST405B
        json.put("RST405B", bi.RST406A.getText().toString());*/

        //RST406A
        json.put("RST406A", bi.RST406A.getText().toString());

        //RST406B
        json.put("RST406B", bi.RST406B.getText().toString());


        //RST407
        json.put("RST407", bi.RST407a.isChecked() ? "1"
                : bi.RST407b.isChecked() ? "2"
                : "0");


        //RST408
        json.put("RST408", bi.RST408a.isChecked() ? "1"
                : bi.RST408b.isChecked() ? "2"
                : bi.RST408c.isChecked() ? "3"
                : bi.RST408d.isChecked() ? "4"
                : bi.RST40896.isChecked() ? "96"
                : "0");
        json.put("RST40896x", bi.RST40896x.getText().toString());


        //RST417
        json.put("RST417", bi.RST417a.isChecked() ? "1"
                : bi.RST417b.isChecked() ? "2"
                : "0");

        //RST418
        json.put("RST418", bi.RST418.getText().toString());


        //RST501
        json.put("RST501", bi.RST501a.isChecked() ? "1"
                : bi.RST501b.isChecked() ? "2"
                : "0");


        //RST502
        json.put("RST502", bi.RST502a.isChecked() ? "1"
                : bi.RST502b.isChecked() ? "2"
                : bi.RST502c.isChecked() ? "3"
                : bi.RST50296.isChecked() ? "96"
                : "0");
        json.put("RST50296x", bi.RST50296x.getText().toString());


        //RST503
        json.put("RST503", bi.RST503a.isChecked() ? "1"
                : bi.RST503b.isChecked() ? "2"
                : "0");

        //RST504
        json.put("RST504", bi.RST504.getText().toString());


        //RST600
        json.put("RST600", bi.RST600a.isChecked() ? "1"
                : bi.RST600b.isChecked() ? "2"
                : "0");

        //RST601
        json.put("RST601", bi.RST601.getText().toString());

        //RST6AS
        json.put("RST6AS", bi.RST6AS.getText().toString());


        MainApp.fc.setsB(String.valueOf(json));

    }


    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);
        int updcount = db.updateForm();
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.llafter);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
