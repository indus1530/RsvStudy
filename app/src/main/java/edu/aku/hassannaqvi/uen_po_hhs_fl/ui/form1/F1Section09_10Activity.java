package edu.aku.hassannaqvi.uen_po_hhs_fl.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.uen_po_hhs_fl.R;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.MainApp;
import edu.aku.hassannaqvi.uen_po_hhs_fl.databinding.ActivityF1Section0910Binding;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ClearClass;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ValidatorClass;

public class F1Section09_10Activity extends AppCompatActivity {

    ActivityF1Section0910Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section09_10);
        bi.setCallback(this);
        this.setTitle("Form 01 (Case Reporting Form)");

        settingListener();
    }

    private void settingListener() {

        bi.pocfj0197.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.fldGrppocfj01, false);
                    bi.fldGrppocfj01.setTag("-1");
                } else {
                    ClearClass.ClearAllFields(bi.fldGrppocfj01, true);
                    bi.fldGrppocfj01.setTag("0");
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
                startActivity(new Intent(this, F1Section11Activity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSG();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {
        JSONObject sIJ = new JSONObject();

        sIJ.put("pocfi01", bi.pocfi01a.isChecked() ? "1"
                : bi.pocfi01b.isChecked() ? "2"
                : "0");

        sIJ.put("pocfi02", bi.pocfi02a.isChecked() ? "1"
                : bi.pocfi02b.isChecked() ? "2"
                : "0");

        sIJ.put("pocfj01a", bi.pocfj01a.isChecked() ? "1" : "0");
        sIJ.put("pocfj01b", bi.pocfj01b.isChecked() ? "2" : "0");
        sIJ.put("pocfj01c", bi.pocfj01c.isChecked() ? "3" : "0");
        sIJ.put("pocfj01d", bi.pocfj01d.isChecked() ? "4" : "0");
        sIJ.put("pocfj01e", bi.pocfj01e.isChecked() ? "5" : "0");
        sIJ.put("pocfj01f", bi.pocfj01f.isChecked() ? "6" : "0");
        sIJ.put("pocfj01g", bi.pocfj01g.isChecked() ? "7" : "0");
        sIJ.put("pocfj01h", bi.pocfj01h.isChecked() ? "8" : "0");
        sIJ.put("pocfj01i", bi.pocfj01i.isChecked() ? "9" : "0");
        sIJ.put("pocfj01j", bi.pocfj01j.isChecked() ? "10" : "0");
        sIJ.put("pocfj01k", bi.pocfj01k.isChecked() ? "11" : "0");
        sIJ.put("pocfj01l", bi.pocfj01l.isChecked() ? "12" : "0");
        sIJ.put("pocfj01m", bi.pocfj01m.isChecked() ? "13" : "0");
        sIJ.put("pocfj01n", bi.pocfj01n.isChecked() ? "14" : "0");
        sIJ.put("pocfj01o", bi.pocfj01o.isChecked() ? "15" : "0");
        sIJ.put("pocfj01p", bi.pocfj01p.isChecked() ? "16" : "0");
        sIJ.put("pocfj0196", bi.pocfj0196.isChecked() ? "96" : "0");

        sIJ.put("pocfj01bx", bi.pocfj01bx.getText().toString());
        sIJ.put("pocfj0196x", bi.pocfj0196x.getText().toString());
        sIJ.put("pocfj0197", bi.pocfj0197.isChecked() ? "97" : "0");

        sIJ.put("pocfj02", bi.pocfj02a.isChecked() ? "1"
                : bi.pocfj02b.isChecked() ? "2"
                : "0");
        sIJ.put("pocfj02ax", bi.pocfj02ax.getText().toString());

        MainApp.fc.setsG(String.valueOf(sIJ));
    }

    private boolean formValidation() {

        return ValidatorClass.EmptyCheckingContainer(this, bi.ll0910);
    }

    public void BtnEnd() {

        MainApp.endActivity(this, this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
