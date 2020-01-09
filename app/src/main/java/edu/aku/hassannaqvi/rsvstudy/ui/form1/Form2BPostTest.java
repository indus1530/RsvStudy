package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
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
                startActivity(new Intent(this, Section07Activity.class).putExtra("complete", true));

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

        //RS121
        SF.put("RS121", bi.RS121a.isChecked() ? "1"
                : bi.RS121b.isChecked() ? "2"
                : "0");

        //RS128A
        SF.put("RS128A", bi.RS128Aa.isChecked() ? "1"
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
        SF.put("RS128B", bi.RS128Ba.isChecked() ? "1"
                : bi.RS128Bb.isChecked() ? "2"
                : "0");

        //RS128C
        SF.put("RS128C", bi.RS128C.getText().toString());

        //RS128D
        SF.put("RS128D", bi.RS128D.getText().toString());

        //RS128E
        SF.put("RS128E", bi.RS128E.getText().toString());


        int counterRS128 = 2;
        for (View view : RS128List) {
            LayoutTestsBinding layoutTestsBinding = DataBindingUtil.bind(view);
            SF.put("RS128" + String.format("%02d", counterRS128) + "A", layoutTestsBinding.RS117Aa.isChecked() ? "1"
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
            SF.put("RS128" + String.format("%02d", counterRS128) + "B", layoutTestsBinding.RS117Ba.isChecked() ? "1"
                    : layoutTestsBinding.RS117Bb.isChecked() ? "2"
                    : "0");
            SF.put("RS128" + String.format("%02d", counterRS128) + "C", layoutTestsBinding.RS117C.getText().toString());
            SF.put("RS128" + String.format("%02d", counterRS128) + "D", layoutTestsBinding.RS117D.getText().toString());
            SF.put("RS128" + String.format("%02d", counterRS128) + "E", layoutTestsBinding.RS117E.getText().toString());

            counterRS128++;
        }


        MainApp.fc.setsF(String.valueOf(SF));

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


    /*@Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }*/

}
