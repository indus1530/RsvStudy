package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityForm2bPosttestBinding;
import edu.aku.hassannaqvi.rsvstudy.databinding.LayoutTestsBinding;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Form2BPostTest extends AppCompatActivity {

    ActivityForm2bPosttestBinding bi;
    DatabaseHelper db;
    ChildList item;
    List<View> RS128List;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_form2b_posttest);
        bi.setCallback(this);
        db = new DatabaseHelper(this);

        item = getIntent().getParcelableExtra("data");
        RS128List = new ArrayList<>();
        setupSkips();


        bi.RS128Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ValidatorClass.EmptyCheckingContainer(Form2BPostTest.this, bi.RS128View) == false) {
                    Toast.makeText(Form2BPostTest.this, "Please fill first Test", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (RS128List.size() == 7) {
                    Toast.makeText(Form2BPostTest.this, "Can't add more than 8 tests", Toast.LENGTH_SHORT).show();
                    return;
                }

                addViewInRS128();
                //bi.RS128Items.getChildAt(0).setEnabled(false);

            }
        });


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



    private void addViewInRS128() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.layout_tests, null);
        bi.RS128Items.addView(rowView);
        RS128List.add(rowView);

        LayoutTestsBinding layoutTestsBinding = DataBindingUtil.bind(rowView);
        layoutTestsBinding.btnClearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bi.RS128Items.removeView(rowView);
                RS128List.remove(rowView);
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

        JSONObject json = new JSONObject();

        //RS49
        json.put("f_type", MainApp.followUp);

        //RS121
        json.put("RS121", bi.RS121a.isChecked() ? "1"
                : bi.RS121b.isChecked() ? "2"
                : "0");

        //RS128A
        json.put("RS128A", bi.RS128Aa.isChecked() ? "1"
                : bi.RS128Ab.isChecked() ? "2"
                : bi.RS128Ac.isChecked() ? "3"
                : bi.RS128Ad.isChecked() ? "4"
                : bi.RS128Ae.isChecked() ? "5"
                : bi.RS128Af.isChecked() ? "6"
                : bi.RS128Ag.isChecked() ? "7"
                : bi.RS128Ah.isChecked() ? "8"
                : bi.RS128Ai.isChecked() ? "9"
                : bi.RS128Aj.isChecked() ? "10"
                : "0");

        //RS128B
        json.put("RS128B", bi.RS128Ba.isChecked() ? "1"
                : bi.RS128Bb.isChecked() ? "2"
                : "0");

        //RS128C
        json.put("RS128C", bi.RS128C.getText().toString());

        //RS128D
        json.put("RS128D", bi.RS128D.getText().toString());

        //RS128E
        json.put("RS128E", bi.RS128E.getText().toString());


        int counterRS128 = 2;
        for (View view : RS128List) {
            LayoutTestsBinding layoutTestsBinding = DataBindingUtil.bind(view);
            json.put("RS128" + String.format("%02d", counterRS128) + "A", layoutTestsBinding.RS117Aa.isChecked() ? "1"
                    : layoutTestsBinding.RS117Ab.isChecked() ? "2"
                    : layoutTestsBinding.RS117Ac.isChecked() ? "3"
                    : layoutTestsBinding.RS117Ad.isChecked() ? "4"
                    : layoutTestsBinding.RS117Ae.isChecked() ? "5"
                    : layoutTestsBinding.RS117Af.isChecked() ? "6"
                    : layoutTestsBinding.RS117Ag.isChecked() ? "7"
                    : layoutTestsBinding.RS117Ah.isChecked() ? "8"
                    : layoutTestsBinding.RS117Ai.isChecked() ? "9"
                    : layoutTestsBinding.RS117Aj.isChecked() ? "10"
                    : "0");
            json.put("RS128" + String.format("%02d", counterRS128) + "B", layoutTestsBinding.RS117Ba.isChecked() ? "1"
                    : layoutTestsBinding.RS117Bb.isChecked() ? "2"
                    : "0");
            json.put("RS128" + String.format("%02d", counterRS128) + "C", layoutTestsBinding.RS117C.getText().toString());
            json.put("RS128" + String.format("%02d", counterRS128) + "D", layoutTestsBinding.RS117D.getText().toString());
            json.put("RS128" + String.format("%02d", counterRS128) + "E", layoutTestsBinding.RS117E.getText().toString());

            counterRS128++;
        }


        //RS131
        json.put("RS131", bi.RS131a.isChecked() ? "1"
                : bi.RS131b.isChecked() ? "2"
                : "0");

        //RS132
        json.put("RS132", bi.RS132a.isChecked() ? "1"
                : bi.RS132b.isChecked() ? "2"
                : bi.RS132c.isChecked() ? "3"
                : bi.RS13296.isChecked() ? "96"
                : "0");
        json.put("RS13296x", bi.RS13296x.getText().toString());


        //RS133
        json.put("RS133", bi.RS133a.isChecked() ? "1"
                : bi.RS133b.isChecked() ? "2"
                : "0");

        //RS134
        json.put("RS134", bi.RS134.getText().toString());


        //RS141
        json.put("RS141", bi.RS141a.isChecked() ? "1"
                : bi.RS141b.isChecked() ? "2"
                : "0");

        //RS142
        json.put("RS142", bi.RS142.getText().toString());

        //RS143
        json.put("RS143", bi.RS143.getText().toString());


        MainApp.fc.setsF(String.valueOf(json));

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
        return ValidatorClass.EmptyCheckingContainer(this, bi.llpost);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
