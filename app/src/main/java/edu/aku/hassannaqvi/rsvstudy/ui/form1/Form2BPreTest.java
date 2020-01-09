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
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityForm2bPretestBinding;
import edu.aku.hassannaqvi.rsvstudy.databinding.LayoutTestsBinding;
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
        RS117List = new ArrayList<>();


        bi.RS117Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ValidatorClass.EmptyCheckingContainer(Form2BPreTest.this, bi.RS117View) == false) {
                    Toast.makeText(Form2BPreTest.this, "Please fill first Test", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (RS117List.size() == 7) {
                    Toast.makeText(Form2BPreTest.this, "Can't add more than 8 tests", Toast.LENGTH_SHORT).show();
                    return;
                }

                addViewInRS117();

            }
        });

    }


    private void addViewInRS117() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.layout_tests, null);
        bi.RS117Items.addView(rowView);
        RS117List.add(rowView);

        LayoutTestsBinding layoutTestsBinding = DataBindingUtil.bind(rowView);
        layoutTestsBinding.btnClearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bi.RS117Items.removeView(rowView);
                RS117List.remove(rowView);
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

        //RS111
        SF.put("RS111", bi.RS111a.isChecked() ? "1"
                : bi.RS111b.isChecked() ? "2"
                : "0");

        //RS112
        SF.put("RS112", bi.RS112.getText().toString());

        //RS113A
        SF.put("RS113A", bi.RS113A.getText().toString());

        //RS113B
        SF.put("RS113B", bi.RS113B.getText().toString());


        //RS114A
        SF.put("RS114A", bi.RS114A.getText().toString());

        //RS114B
        SF.put("RS114B", bi.RS114B.getText().toString());

        //RS115
        SF.put("RS115", bi.RS115a.isChecked() ? "1"
                : bi.RS115b.isChecked() ? "2"
                : "0");

        //RS116
        SF.put("RS116", bi.RS116a.isChecked() ? "1"
                : bi.RS116b.isChecked() ? "2"
                : bi.RS116c.isChecked() ? "3"
                : bi.RS116d.isChecked() ? "4"
                : bi.RS11696.isChecked() ? "96"
                : "0");
        SF.put("RS11696x", bi.RS11696x.getText().toString());

        /*//RS117A
        SF.put("RS117A", bi.RS117Aa.isChecked() ? "1"
                : bi.RS117Ab.isChecked() ? "2"
                : bi.RS117Ac.isChecked() ? "3"
                : bi.RS117Ad.isChecked() ? "4"
                : bi.RS117Ae.isChecked() ? "5"
                : bi.RS117Af.isChecked() ? "6"
                : bi.RS117Ag.isChecked() ? "7"
                : bi.RS117Ah.isChecked() ? "8"
                : bi.RS117Ai.isChecked() ? "9"
                : bi.RS117Aj.isChecked() ? "10"
                : "0");

        //RS117B
        SF.put("RS117B", bi.RS117Ba.isChecked() ? "1"
                : bi.RS117Bb.isChecked() ? "2"
                : "0");

        //RS117C
        SF.put("RS117C", bi.RS117C.getText().toString());

        //RS117D
        SF.put("RS117D", bi.RS117D.getText().toString());

        //RS117E
        SF.put("RS117E", bi.RS117E.getText().toString());*/


        int counterRS117 = 1;
        for (View view : RS117List) {
            LayoutTestsBinding layoutTestsBinding = DataBindingUtil.bind(view);
            SF.put("RS117" + String.format("%02d", counterRS117) + "A", layoutTestsBinding.RS117Aa.isChecked() ? "1"
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
            SF.put("RS117" + String.format("%02d", counterRS117) + "B", layoutTestsBinding.RS117Ba.isChecked() ? "1"
                    : layoutTestsBinding.RS117Bb.isChecked() ? "2"
                    : "0");
            SF.put("RS117" + String.format("%02d", counterRS117) + "C", layoutTestsBinding.RS117C.getText().toString());
            SF.put("RS117" + String.format("%02d", counterRS117) + "D", layoutTestsBinding.RS117D.getText().toString());
            SF.put("RS117" + String.format("%02d", counterRS117) + "E", layoutTestsBinding.RS117E.getText().toString());

            counterRS117++;
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
        return ValidatorClass.EmptyCheckingContainer(this, bi.llpre);
    }


    /*@Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }*/


}
