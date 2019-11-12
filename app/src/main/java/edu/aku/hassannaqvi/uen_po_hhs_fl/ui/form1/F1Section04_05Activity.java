package edu.aku.hassannaqvi.uen_po_hhs_fl.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.uen_po_hhs_fl.R;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.MainApp;
import edu.aku.hassannaqvi.uen_po_hhs_fl.databinding.ActivityF1Section0405Binding;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ClearClass;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ValidatorClass;


public class F1Section04_05Activity extends AppCompatActivity {

    ActivityF1Section0405Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section04_05);
        bi.setCallback(this);
        this.setTitle("Form 01 (Case Reporting Form)");
        EventsCall();
    }


    void EventsCall() {

        bi.pocfe01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (bi.pocfe01.getText().toString().isEmpty()) return;
                bi.pocfe03.setMaxvalue(Integer.valueOf(bi.pocfe01.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bi.pocfd01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.pocfd01a.getId()) {
                    bi.cvpocfd02.setVisibility(View.VISIBLE);
                    bi.cvpocfd03.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.cvpocfd02, null);
                    ClearClass.ClearAllFields(bi.cvpocfd03, null);
                    bi.cvpocfd02.setVisibility(View.GONE);
                    bi.cvpocfd03.setVisibility(View.GONE);
                }
            }
        });


        bi.pocfd0297.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.llpocfd02, false);
                    bi.llpocfd02.setTag("-1");
                } else {
                    ClearClass.ClearAllFields(bi.llpocfd02, true);
                    bi.llpocfd02.setTag("0");
                }
            }
        });


        bi.pocfe04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bi.pocfe04c.isChecked()) {
                    bi.pocfe05.clearCheck();
                    bi.pocfe06.setEnabled(false);
                } else {
                    bi.pocfe06.setEnabled(true);
                }
            }
        });

        bi.pocfe05.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (!bi.pocfe05a.isChecked()) {
                    bi.pocfe06.setEnabled(false);
                } else {
                    bi.pocfe06.setEnabled(true);
                }
            }
        });

        bi.pocfe10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (!bi.pocfe10a.isChecked()) {
                    bi.pocfe11.setEnabled(false);
                    bi.pocfe12.clearCheck();
                } else {
                    bi.pocfe11.setEnabled(true);
                }
            }
        });

        bi.pocfe13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (!bi.pocfe13a.isChecked()) {
                    bi.pocfe14.setEnabled(false);
                    bi.pocfe15.clearCheck();
                } else {
                    bi.pocfe14.setEnabled(true);
                }
            }
        });

        bi.pocfe16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (!bi.pocfe16a.isChecked()) {
                    bi.pocfe17.setEnabled(false);
                    bi.pocfe18.clearCheck();
                } else {
                    bi.pocfe17.setEnabled(true);
                }
            }
        });

    }


    private void SaveDraft() throws JSONException {

        JSONObject s0405 = new JSONObject();

        s0405.put("pocfd01", bi.pocfd01a.isChecked() ? "1"
                : bi.pocfd01b.isChecked() ? "2"
                : "0");

        s0405.put("pocfd02a", bi.pocfd02a.isChecked() ? "1" : "0");
        s0405.put("pocfd02b", bi.pocfd02b.isChecked() ? "2" : "0");
        s0405.put("pocfd02c", bi.pocfd02c.isChecked() ? "3" : "0");
        s0405.put("pocfd02d", bi.pocfd02d.isChecked() ? "4" : "0");
        s0405.put("pocfd0297", bi.pocfd0297.isChecked() ? "97" : "0");

        s0405.put("pocfd03a", bi.pocfd03a.isChecked() ? "1" : "0");
        s0405.put("pocfd03b", bi.pocfd03b.isChecked() ? "2" : "0");
        s0405.put("pocfd03c", bi.pocfd03c.isChecked() ? "3" : "0");
        s0405.put("pocfd03d", bi.pocfd03d.isChecked() ? "4" : "0");
        s0405.put("pocfd03e", bi.pocfd03e.isChecked() ? "5" : "0");
        s0405.put("pocfd0396", bi.pocfd0396.isChecked() ? "96" : "0");
        s0405.put("pocfd0396x", bi.pocfd0396x.getText().toString());

        s0405.put("pocfe01", bi.pocfe01.getText().toString().trim().length() > 0 ? bi.pocfe01.getText().toString() : "0");
        s0405.put("pocfe02", bi.pocfe02.getText().toString().trim().length() > 0 ? bi.pocfe02.getText().toString() : "0");
        s0405.put("pocfe03", bi.pocfe02.getText().toString().trim().length() > 0 ? bi.pocfe03.getText().toString() : "0");
        s0405.put("pocfe04", bi.pocfe04a.isChecked() ? "1" : bi.pocfe04b.isChecked() ? "2" : bi.pocfe04c.isChecked() ? "3" : bi.pocfe0496.isChecked() ? "96" : "0");
        s0405.put("pocfe0496x", bi.pocfe0496x.getText().toString());
        s0405.put("pocfe05", bi.pocfe05a.isChecked() ? "1" : bi.pocfe05b.isChecked() ? "2" : "0");
        s0405.put("pocfe06", bi.pocfe06.getText().toString());
        s0405.put("pocfe07", bi.pocfe07a.isChecked() ? "1" : bi.pocfe07b.isChecked() ? "2" : bi.pocfe07c.isChecked() ? "3" : bi.pocfe07d.isChecked() ? "4" : bi.pocfe07e.isChecked() ? "5" : bi.pocfe07f.isChecked() ? "6" : bi.pocfe07g.isChecked() ? "7" : bi.pocfe07h.isChecked() ? "8" : bi.pocfe07i.isChecked() ? "9" : bi.pocfe07j.isChecked() ? "10" : bi.pocfe0796.isChecked() ? "96" : "0");
        s0405.put("pocfe0796x", bi.pocfe0796x.getText().toString());
        s0405.put("pocfe08", bi.pocfe08a.isChecked() ? "1" : bi.pocfe08b.isChecked() ? "2" : "0");
        s0405.put("pocfe09", bi.pocfe09a.isChecked() ? "1" : bi.pocfe09b.isChecked() ? "2" : "0");
        s0405.put("pocfe10", bi.pocfe10a.isChecked() ? "1" : bi.pocfe10b.isChecked() ? "2" : "0");
        s0405.put("pocfe11", bi.pocfe11.getText().toString());
        s0405.put("pocfe12", bi.pocfe12a.isChecked() ? "1" : bi.pocfe12b.isChecked() ? "2" : bi.pocfe12c.isChecked() ? "3" : "0");
        s0405.put("pocfe13", bi.pocfe13a.isChecked() ? "1" : bi.pocfe13b.isChecked() ? "2" : "0");
        s0405.put("pocfe14", bi.pocfe14.getText().toString());
        s0405.put("pocfe15", bi.pocfe15a.isChecked() ? "1" : bi.pocfe15b.isChecked() ? "2" : bi.pocfe15c.isChecked() ? "3" : "0");
        s0405.put("pocfe16", bi.pocfe16a.isChecked() ? "1" : bi.pocfe16b.isChecked() ? "2" : "0");
        s0405.put("pocfe17", bi.pocfe17.getText().toString());
        s0405.put("pocfe18", bi.pocfe18a.isChecked() ? "1" : bi.pocfe18b.isChecked() ? "2" : bi.pocfe18c.isChecked() ? "3" : "0");

        MainApp.fc.setsC(String.valueOf(s0405));
        //fc.setSqoc1(String.valueOf(s0405));

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
                Intent ii = new Intent(this, F1Section06Activity.class);
                startActivity(ii);

                //MainApp.endActivity(this, this, Qoc2.class, true, RSDInfoActivity.fc);

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void BtnEnd() {

        MainApp.endActivity(this, this);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSC();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }


    }


    public boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll0405);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}


