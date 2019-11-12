package edu.aku.hassannaqvi.uen_po_hhs_fl.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import edu.aku.hassannaqvi.uen_po_hhs_fl.R;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.MainApp;
import edu.aku.hassannaqvi.uen_po_hhs_fl.databinding.ActivityF1Section07Binding;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ValidatorClass;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;

public class F1Section07Activity extends AppCompatActivity {

    ActivityF1Section07Binding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section07);
        this.setTitle("Form 01 (Case Reporting Form)");

        db = new DatabaseHelper(this);
        bi.setCallback(this);

        final ArrayList<RadioGroup> pickers = new ArrayList<>(Arrays.asList(
                bi.pocfgbcg, bi.pocfgopv0, bi.pocfgopv1, bi.pocfgp1, bi.pocfgpcv1
                , bi.pocfgrt1, bi.pocfgopv2, bi.pocfgp2, bi.pocfgpcv2, bi.pocfgrt2
                , bi.pocfgopv3, bi.pocfgp3, bi.pocfgpcv3, bi.pocfgipv, bi.pocfgm1
                , bi.pocfgm2
        ));

        final ArrayList<Integer> pickersOption = new ArrayList<>(Arrays.asList(
                bi.pocfgbcga.getId(), bi.pocfgopv0a.getId(), bi.pocfgopv1a.getId(), bi.pocfgp1a.getId(), bi.pocfgpcv1a.getId()
                , bi.pocfgrt1a.getId(), bi.pocfgopv2a.getId(), bi.pocfgp2a.getId(), bi.pocfgpcv2a.getId(), bi.pocfgrt2a.getId()
                , bi.pocfgopv3a.getId(), bi.pocfgp3a.getId(), bi.pocfgpcv3a.getId(), bi.pocfgipva.getId(), bi.pocfgm1a.getId()
                , bi.pocfgm2a.getId()
        ));

        final ArrayList<DatePickerInputEditText> pickersEdittext = new ArrayList<>(Arrays.asList(
                bi.pocfgbcgdt, bi.pocfgopv0dt, bi.pocfgopv1dt, bi.pocfgp1dt, bi.pocfgpcv1dt
                , bi.pocfgrt1dt, bi.pocfgopv2dt, bi.pocfgp2dt, bi.pocfgpcv2dt, bi.pocfgrt2dt
                , bi.pocfgopv3dt, bi.pocfgp3dt, bi.pocfgpcv3dt, bi.pocfgipvdt, bi.pocfgm1dt
                , bi.pocfgm2dt
        ));

        for (int i = 0; i < pickers.size(); i++) {

            pickers.get(i).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int index = -1;
                    for (RadioGroup rdg : pickers) {
                        index++;
                        if (rdg.getId() == radioGroup.getId())
                            break;
                    }

                    if (pickersOption.get(index) != i)
                        pickersEdittext.get(index).setText(null);

                }
            });
        }

        for (DatePickerInputEditText datePickerEditText : pickersEdittext) {
            datePickerEditText.setMinDate(F1Section01Activity.DOB);
        }

        bi.pocfgbcgdt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgbcgdt.setText(null);
                    bi.pocfgbcgdt.setEnabled(false);
                } else {
                    bi.pocfgbcgdt.setEnabled(true);
                    bi.pocfgbcgdt.setError(null);
                }
            }
        });
        bi.pocfgopv0dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgopv0dt.setText(null);
                    bi.pocfgopv0dt.setEnabled(false);
                } else {
                    bi.pocfgopv0dt.setEnabled(true);
                    bi.pocfgopv0dt.setError(null);
                }
            }
        });
        bi.pocfgopv1dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgopv1dt.setText(null);
                    bi.pocfgopv1dt.setEnabled(false);
                } else {
                    bi.pocfgopv1dt.setEnabled(true);
                    bi.pocfgopv1dt.setError(null);
                }
            }
        });
        bi.pocfgp1dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgp1dt.setText(null);
                    bi.pocfgp1dt.setEnabled(false);
                } else {
                    bi.pocfgp1dt.setEnabled(true);
                    bi.pocfgp1dt.setError(null);
                }
            }
        });
        bi.pocfgpcv1dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgpcv1dt.setText(null);
                    bi.pocfgpcv1dt.setEnabled(false);
                } else {
                    bi.pocfgpcv1dt.setEnabled(true);
                    bi.pocfgpcv1dt.setError(null);
                }
            }
        });
        bi.pocfgrt1dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgrt1dt.setText(null);
                    bi.pocfgrt1dt.setEnabled(false);
                } else {
                    bi.pocfgrt1dt.setEnabled(true);
                    bi.pocfgrt1dt.setError(null);
                }
            }
        });
        bi.pocfgopv2dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgopv2dt.setText(null);
                    bi.pocfgopv2dt.setEnabled(false);
                } else {
                    bi.pocfgopv2dt.setEnabled(true);
                    bi.pocfgopv2dt.setError(null);
                }
            }
        });
        bi.pocfgp2dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgp2dt.setText(null);
                    bi.pocfgp2dt.setEnabled(false);
                } else {
                    bi.pocfgp2dt.setEnabled(true);
                    bi.pocfgp2dt.setError(null);
                }
            }
        });
        bi.pocfgpcv2dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgpcv2dt.setText(null);
                    bi.pocfgpcv2dt.setEnabled(false);
                } else {
                    bi.pocfgpcv2dt.setEnabled(true);
                    bi.pocfgpcv2dt.setError(null);
                }
            }
        });
        bi.pocfgrt2dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgrt2dt.setText(null);
                    bi.pocfgrt2dt.setEnabled(false);
                } else {
                    bi.pocfgrt2dt.setEnabled(true);
                    bi.pocfgrt2dt.setError(null);
                }
            }
        });
        bi.pocfgopv3dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgopv3dt.setText(null);
                    bi.pocfgopv3dt.setEnabled(false);
                } else {
                    bi.pocfgopv3dt.setEnabled(true);
                    bi.pocfgopv3dt.setError(null);
                }
            }
        });
        bi.pocfgp3dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgp3dt.setText(null);
                    bi.pocfgp3dt.setEnabled(false);
                } else {
                    bi.pocfgp3dt.setEnabled(true);
                    bi.pocfgp3dt.setError(null);
                }
            }
        });
        bi.pocfgpcv3dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgpcv3dt.setText(null);
                    bi.pocfgpcv3dt.setEnabled(false);
                } else {
                    bi.pocfgpcv3dt.setEnabled(true);
                    bi.pocfgpcv3dt.setError(null);
                }
            }
        });
        bi.pocfgipvdt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgipvdt.setText(null);
                    bi.pocfgipvdt.setEnabled(false);
                } else {
                    bi.pocfgipvdt.setEnabled(true);
                    bi.pocfgipvdt.setError(null);
                }
            }
        });
        bi.pocfgm1dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgm1dt.setText(null);
                    bi.pocfgm1dt.setEnabled(false);
                } else {
                    bi.pocfgm1dt.setEnabled(true);
                    bi.pocfgm1dt.setError(null);
                }
            }
        });
        bi.pocfgm2dt98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.pocfgm2dt.setText(null);
                    bi.pocfgm2dt.setEnabled(false);
                } else {
                    bi.pocfgm2dt.setEnabled(true);
                    bi.pocfgm2dt.setError(null);
                }
            }
        });

        bi.pocfg01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == bi.pocfg01b.getId())
                    bi.pocfg02.clearCheck();
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
                startActivity(new Intent(this, F1Section08Activity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void BtnEnd() {

        MainApp.endActivity(this, this);
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrppocfgA);
    }

    private void SaveDraft() throws JSONException {

        JSONObject sG = new JSONObject();

//        pocfg01
        sG.put("pocfg01", bi.pocfg01a.isChecked() ? "1"
                : bi.pocfg01b.isChecked() ? "2"
                : "0");

//        pocfg02
        sG.put("pocfg02", bi.pocfg02a.isChecked() ? "1"
                : bi.pocfg02b.isChecked() ? "2"
                : bi.pocfg02c.isChecked() ? "3"
                : bi.pocfg02d.isChecked() ? "4"
                : "0");
//at birth
//          pocfgbcg
        sG.put("pocfgbcg", bi.pocfgbcga.isChecked() ? "1"
                : bi.pocfgbcgb.isChecked() ? "2"
                : bi.pocfgbcg97.isChecked() ? "97"
                : "0");
        sG.put("pocfgbcgdt98", bi.pocfgbcgdt98.isChecked() ? "1" : "0");
        sG.put("pocfgbcgdt", bi.pocfgbcgdt.getText().toString());

//          pocfgopv0
        sG.put("pocfgopv0", bi.pocfgopv0a.isChecked() ? "1"
                : bi.pocfgopv0b.isChecked() ? "2"
                : bi.pocfgopv097.isChecked() ? "97"
                : "0");
        sG.put("pocfgopv0dt98", bi.pocfgopv0dt98.isChecked() ? "1" : "0");
        sG.put("pocfgopv0dt", bi.pocfgopv0dt.getText().toString());

//       at age of 6

//          pocfgopv1
        sG.put("pocfgopv1", bi.pocfgopv1a.isChecked() ? "1"
                : bi.pocfgopv1b.isChecked() ? "2"
                : bi.pocfgopv197.isChecked() ? "97"
                : "0");
        sG.put("pocfgopv1dt98", bi.pocfgopv1dt98.isChecked() ? "1" : "0");
        sG.put("pocfgopv1dt", bi.pocfgopv1dt.getText().toString());

//          pocfgp1
        sG.put("pocfgp1", bi.pocfgp1a.isChecked() ? "1"
                : bi.pocfgp1b.isChecked() ? "2"
                : bi.pocfgp197.isChecked() ? "97"
                : "0");
        sG.put("pocfgp1dt98", bi.pocfgp1dt98.isChecked() ? "1" : "0");
        sG.put("pocfgp1dt", bi.pocfgp1dt.getText().toString());

//          pocfgpcv1
        sG.put("pocfgpcv1", bi.pocfgpcv1a.isChecked() ? "1"
                : bi.pocfgpcv1b.isChecked() ? "2"
                : bi.pocfgpcv197.isChecked() ? "97"
                : "0");
        sG.put("pocfgpcv1dt98", bi.pocfgpcv1dt98.isChecked() ? "1" : "0");
        sG.put("pocfgpcv1dt", bi.pocfgpcv1dt.getText().toString());

//          pocfgrt1
        sG.put("pocfgrt1", bi.pocfgrt1a.isChecked() ? "1"
                : bi.pocfgrt1b.isChecked() ? "2"
                : bi.pocfgrt197.isChecked() ? "97"
                : "0");
        sG.put("pocfgrt1dt98", bi.pocfgrt1dt98.isChecked() ? "1" : "0");
        sG.put("pocfgrt1dt", bi.pocfgrt1dt.getText().toString());

//       at age of 10 weeks

//          pocfgopv2
        sG.put("pocfgopv2", bi.pocfgopv2a.isChecked() ? "1"
                : bi.pocfgopv2b.isChecked() ? "2"
                : bi.pocfgopv297.isChecked() ? "97"
                : "0");
        sG.put("pocfgopv2dt98", bi.pocfgopv2dt98.isChecked() ? "1" : "0");
        sG.put("pocfgopv2dt", bi.pocfgopv2dt.getText().toString());

//          pocfgp2
        sG.put("pocfgp2", bi.pocfgp2a.isChecked() ? "1"
                : bi.pocfgp2b.isChecked() ? "2"
                : bi.pocfgp297.isChecked() ? "97"
                : "0");
        sG.put("pocfgp2dt98", bi.pocfgp2dt98.isChecked() ? "1" : "0");
        sG.put("pocfgp2dt", bi.pocfgp2dt.getText().toString());

//          pocfgpcv2
        sG.put("pocfgpcv2", bi.pocfgpcv2a.isChecked() ? "1"
                : bi.pocfgpcv2b.isChecked() ? "2"
                : bi.pocfgpcv297.isChecked() ? "97"
                : "0");
        sG.put("pocfgpcv2dt98", bi.pocfgpcv2dt98.isChecked() ? "1" : "0");
        sG.put("pocfgpcv2dt", bi.pocfgpcv2dt.getText().toString());

//          pocfgrt2
        sG.put("pocfgrt2", bi.pocfgrt2a.isChecked() ? "1"
                : bi.pocfgrt2b.isChecked() ? "2"
                : bi.pocfgrt297.isChecked() ? "97"
                : "0");
        sG.put("pocfgrt2dt98", bi.pocfgrt2dt98.isChecked() ? "1" : "0");
        sG.put("pocfgrt2dt", bi.pocfgrt2dt.getText().toString());

//       at age of 14 weeks

//          pocfgopv3
        sG.put("pocfgopv3", bi.pocfgopv3a.isChecked() ? "1"
                : bi.pocfgopv3b.isChecked() ? "2"
                : bi.pocfgopv397.isChecked() ? "97"
                : "0");
        sG.put("pocfgopv3dt98", bi.pocfgopv3dt98.isChecked() ? "1" : "0");
        sG.put("pocfgopv3dt", bi.pocfgopv3dt.getText().toString());

//          pocfgp3
        sG.put("pocfgp3", bi.pocfgp3a.isChecked() ? "1"
                : bi.pocfgp3b.isChecked() ? "2"
                : bi.pocfgp397.isChecked() ? "97"
                : "0");
        sG.put("pocfgp3dt98", bi.pocfgp3dt98.isChecked() ? "1" : "0");
        sG.put("pocfgp3dt", bi.pocfgp3dt.getText().toString());

//          pocfgpcv3
        sG.put("pocfgpcv3", bi.pocfgpcv3a.isChecked() ? "1"
                : bi.pocfgpcv3b.isChecked() ? "2"
                : bi.pocfgpcv397.isChecked() ? "97"
                : "0");
        sG.put("pocfgpcv3dt98", bi.pocfgpcv3dt98.isChecked() ? "1" : "0");
        sG.put("pocfgpcv3dt", bi.pocfgpcv3dt.getText().toString());

//          pocfgipv
        sG.put("pocfgipv", bi.pocfgipva.isChecked() ? "1"
                : bi.pocfgipvb.isChecked() ? "2"
                : bi.pocfgipv97.isChecked() ? "97"
                : "0");
        sG.put("pocfgipvdt98", bi.pocfgipvdt98.isChecked() ? "1" : "0");
        sG.put("pocfgipvdt", bi.pocfgipvdt.getText().toString());

//at the age of 9 months
//          pocfgm1
        sG.put("pocfgm1", bi.pocfgm1a.isChecked() ? "1"
                : bi.pocfgm1b.isChecked() ? "2"
                : bi.pocfgm197.isChecked() ? "97"
                : "0");
        sG.put("pocfgm1dt98", bi.pocfgm1dt98.isChecked() ? "1" : "0");
        sG.put("pocfgm1dt", bi.pocfgm1dt.getText().toString());

//at age of 15 months
//          pocfgm2
        sG.put("pocfgm2", bi.pocfgm2a.isChecked() ? "1"
                : bi.pocfgm2b.isChecked() ? "2"
                : bi.pocfgm297.isChecked() ? "97"
                : "0");
        sG.put("pocfgm2dt98", bi.pocfgm2dt98.isChecked() ? "1" : "0");
        sG.put("pocfgm2dt", bi.pocfgm2dt.getText().toString());

        sG.put("pocfg03", bi.pocfg03.getText().toString());
        sG.put("pocfg0397", bi.pocfg0397.isChecked() ? "97" : "0");

        MainApp.fc.setsE(String.valueOf(sG));

    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSE();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}