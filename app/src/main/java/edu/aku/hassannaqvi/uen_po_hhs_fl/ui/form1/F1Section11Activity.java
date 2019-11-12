package edu.aku.hassannaqvi.uen_po_hhs_fl.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.uen_po_hhs_fl.R;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.MainApp;
import edu.aku.hassannaqvi.uen_po_hhs_fl.databinding.ActivityF1Section11Binding;
import edu.aku.hassannaqvi.uen_po_hhs_fl.ui.other.EndingActivity;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ClearClass;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ValidatorClass;

public class F1Section11Activity extends AppCompatActivity {

    ActivityF1Section11Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section11);
        bi.setCallback(this);
        this.setTitle("Form 01 (Case Reporting Form)");
        setupSkips();

    }


    private void setupSkips() {

        /*bi.pocfk10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.pocfk10a.getId()) {
                    bi.cvpocfk11.setVisibility(View.VISIBLE);
                    ClearClass.ClearAllFields(bi.cvpocfk12, null);
                    bi.cvpocfk12.setVisibility(View.GONE);
                } else {
                    bi.cvpocfk12.setVisibility(View.VISIBLE);
                    ClearClass.ClearAllFields(bi.cvpocfk11, null);
                    bi.cvpocfk11.setVisibility(View.GONE);
                }
            }
        });*/


        //pocfk0697
        bi.pocfk0697.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfk06a.setText("");
                    bi.pocfk06b.setText("");
                    bi.pocfk06a.setVisibility(View.GONE);
                    bi.pocfk06b.setVisibility(View.GONE);
                    ClearClass.ClearAllFields(bi.pocfk07cv, null);
                    bi.pocfk07cv.setVisibility(View.GONE);
                } else {
                    bi.pocfk06a.setVisibility(View.VISIBLE);
                    bi.pocfk06b.setVisibility(View.VISIBLE);
                    bi.pocfk07cv.setVisibility(View.VISIBLE);
                }
            }
        });


        //pocfk07
        bi.pocfk07.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.pocfk0797.getId()) {
                    ClearClass.ClearAllFields(bi.pocfk06cv, null);
                    bi.pocfk06cv.setVisibility(View.GONE);
                } else {
                    bi.pocfk06cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.pocfk0897.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.llpocfk08, false);
                    bi.llpocfk08.setTag("-1");
                } else {
                    ClearClass.ClearAllFields(bi.llpocfk08, true);
                    bi.llpocfk08.setTag("0");
                }
            }
        });


    }


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
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

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSH();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {
        JSONObject sK = new JSONObject();

        sK.put("pocfk01", bi.pocfk01a.isChecked() ? "1"
                : bi.pocfk01b.isChecked() ? "2"
                : bi.pocfk01c.isChecked() ? "3"
                : bi.pocfk01d.isChecked() ? "4"
                : bi.pocfk0196.isChecked() ? "96"
                : "0");

        sK.put("pocfk01cx", bi.pocfk01cx.getText().toString());
        sK.put("pocfk01dx", bi.pocfk01dx.getText().toString());
        sK.put("pocfk0196x", bi.pocfk0196x.getText().toString());

        sK.put("pocfk02", bi.pocfk02a.isChecked() ? "1"
                : bi.pocfk02b.isChecked() ? "2"
                : "0");

        sK.put("pocfk03a", bi.pocfk03a.getText().toString());
        sK.put("pocfk03b", bi.pocfk03b.getText().toString());

        sK.put("pocfk04", bi.pocfk04a.isChecked() ? "1"
                : bi.pocfk04b.isChecked() ? "2"
                : "0");

        sK.put("pocfk05", bi.pocfk05a.isChecked() ? "1"
                : bi.pocfk05b.isChecked() ? "2"
                : "0");

        sK.put("pocfk06a", bi.pocfk06a.getText().toString());
        sK.put("pocfk06b", bi.pocfk06b.getText().toString());
        sK.put("pocfk0697", bi.pocfk0697.isChecked() ? "97" : "0");

        sK.put("pocfk07", bi.pocfk07a.isChecked() ? "1"
                : bi.pocfk07b.isChecked() ? "2"
                : bi.pocfk0797.isChecked() ? "97"  //Not applicable for control group(23-Sep-19, Hassan Bhai)
                : "0");

        sK.put("pocfk08a", bi.pocfk08a.isChecked() ? "1" : "0");
        sK.put("pocfk08b", bi.pocfk08b.isChecked() ? "2" : "0");
        sK.put("pocfk08c", bi.pocfk08c.isChecked() ? "3" : "0");
        sK.put("pocfk08d", bi.pocfk08d.isChecked() ? "4" : "0");
        sK.put("pocfk08e", bi.pocfk08e.isChecked() ? "5" : "0");
        sK.put("pocfk0897", bi.pocfk0897.isChecked() ? "97" : "0");


        sK.put("pocfk09", bi.pocfk09a.isChecked() ? "1"
                : bi.pocfk09b.isChecked() ? "2"
                : bi.pocfk09c.isChecked() ? "3"
                : "0");

/*

        sK.put("pocfk10", bi.pocfk10a.isChecked() ? "1"
                : bi.pocfk10b.isChecked() ? "2"
                : "0");

        sK.put("pocfk11", bi.pocfk11a.isChecked() ? "1"
                : bi.pocfk11b.isChecked() ? "2"
                : bi.pocfk1196.isChecked() ? "96"
                : "0");
        sK.put("pocfk1196x", bi.pocfk1196x.getText().toString());

        sK.put("pocfk12", bi.pocfk12a.isChecked() ? "1"
                : bi.pocfk12b.isChecked() ? "2"
                : "0");

        sK.put("pocfk13", bi.pocfk13a.isChecked() ? "1"
                : bi.pocfk13b.isChecked() ? "2"
                : "0");

*/

        MainApp.fc.setsH(String.valueOf(sK));

    }

    private boolean formValidation() {

        if (!ValidatorClass.EmptyCheckingContainer(this, bi.ll11))
            return false;

        if (bi.pocfk06b.getVisibility() == View.VISIBLE) {
            if (Integer.parseInt(bi.pocfk06b.getText().toString().trim()) >= 92 && bi.pocfk07a.isChecked()) {
                return ValidatorClass.EmptyCustomeTextBox(this, bi.pocfk06b, "please check below question!!");
            } else if (Integer.parseInt(bi.pocfk06b.getText().toString().trim()) < 92 && bi.pocfk07b.isChecked()) {
                return ValidatorClass.EmptyCustomeTextBox(this, bi.pocfk06b, "please check below question!!");
            }
            //return ValidatorClass.EmptyCustomeTextBox(this, bi.pocfk06b, "please check below question!!");
        }

        return true;
    }

    public void BtnEnd() {

        MainApp.endActivity(this, this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
