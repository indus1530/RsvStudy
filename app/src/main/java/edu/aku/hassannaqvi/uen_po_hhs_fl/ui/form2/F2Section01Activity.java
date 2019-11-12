package edu.aku.hassannaqvi.uen_po_hhs_fl.ui.form2;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.uen_po_hhs_fl.R;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.ChildrenContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.FormsContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.LHWContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.TalukasContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.contracts.UCsContract;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.MainApp;
import edu.aku.hassannaqvi.uen_po_hhs_fl.databinding.ActivityF2Section01Binding;
import edu.aku.hassannaqvi.uen_po_hhs_fl.databinding.Pofpa15Binding;
import edu.aku.hassannaqvi.uen_po_hhs_fl.ui.other.EndingActivity;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ClearClass;
import edu.aku.hassannaqvi.uen_po_hhs_fl.validator.ValidatorClass;

public class F2Section01Activity extends AppCompatActivity {

    ActivityF2Section01Binding bi;
    String DAY;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());
    List<View> pofpa15List;
    List<View> pofpa16List;
    List<View> pofpa23List;
    private List<String> talukaNames, ucName, lhwNames;
    private List<String> talukaCodes, ucCode, lhwCodes;
    private DatabaseHelper db;
    private ChildrenContract cContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f2_section01);
        bi.setCallback(this);


        DAY = getIntent().getStringExtra("day");
        if (DAY == null)
            DAY = "";
        bi.dayHeading.setText("DAY " + (DAY.equals("7") ? "07" : "14"));
        this.setTitle(DAY.equals("7") ? "Form 02 (Follow Ups - 7 Day)" : "Form 02 (Follow Ups - 14 Day)");

        if (DAY.equals("14")) {
            bi.pofpa1597.setVisibility(View.VISIBLE);
        } else {
            bi.pofpa1597.setChecked(false);
            bi.pofpa1597.setVisibility(View.GONE);
        }

        initializingComponents();


        pofpa15List = new ArrayList<>();
        pofpa16List = new ArrayList<>();
        pofpa23List = new ArrayList<>();
        clickListener();


    }


    private void initializingComponents() {
        db = new DatabaseHelper(this);
        populateSpinner(this);


        bi.pofpa07.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.pofpa07b.getId()) {
                    bi.pofpa08cv.setVisibility(View.VISIBLE);
                    ClearClass.ClearAllFields(bi.llpofpa09ALL, null);
                    bi.pofpa15Items.removeAllViews();
                    pofpa15List.clear();
                    bi.pofpa16Items.removeAllViews();
                    pofpa16List.clear();
                    bi.pofpa23Items.removeAllViews();
                    pofpa23List.clear();
                    bi.llpofpa09ALL.setVisibility(View.GONE);
                } else {
                    ClearClass.ClearAllFields(bi.pofpa08cv, null);
                    bi.pofpa08cv.setVisibility(View.GONE);
                    bi.llpofpa09ALL.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.pofpa10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.pofpa10a.getId()) {
                    ClearClass.ClearAllFields(bi.pofpa11cv, null);
                    bi.pofpa11cv.setVisibility(View.GONE);
                    bi.pofpa12cv.setVisibility(View.VISIBLE);
                    bi.pofpa13cv.setVisibility(View.VISIBLE);
                    bi.pofpa14cv.setVisibility(View.VISIBLE);
                    bi.pofpa15View.setVisibility(View.VISIBLE);
                    ClearClass.ClearAllFields(bi.pofpa16View, null);
                    bi.pofpa16Items.removeAllViews();
                    pofpa16List.clear();
                    ClearClass.ClearAllFields(bi.pofpa17cv, null);
                    bi.pofpa16View.setVisibility(View.GONE);
                    bi.pofpa17cv.setVisibility(View.GONE);
                } else {
                    bi.pofpa11cv.setVisibility(View.VISIBLE);
                    ClearClass.ClearAllFields(bi.pofpa12cv, null);
                    ClearClass.ClearAllFields(bi.pofpa13cv, null);
                    ClearClass.ClearAllFields(bi.pofpa14cv, null);
                    ClearClass.ClearAllFields(bi.pofpa15View, null);
                    bi.pofpa12cv.setVisibility(View.GONE);
                    bi.pofpa13cv.setVisibility(View.GONE);
                    bi.pofpa14cv.setVisibility(View.GONE);
                    bi.pofpa15Items.removeAllViews();
                    pofpa15List.clear();
                    bi.pofpa15View.setVisibility(View.GONE);
                    bi.pofpa16View.setVisibility(View.VISIBLE);
                    bi.pofpa17cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.pofpa12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.pofpa12b.getId()) {
                    bi.pofpa121cv.setVisibility(View.VISIBLE);
                    ClearClass.ClearAllFields(bi.pofpa13cv, null);
                    ClearClass.ClearAllFields(bi.pofpa14cv, null);
                    bi.pofpa13cv.setVisibility(View.GONE);
                    bi.pofpa14cv.setVisibility(View.GONE);
                } else {
                    ClearClass.ClearAllFields(bi.pofpa121cv, null);
                    bi.pofpa121cv.setVisibility(View.GONE);
                    bi.pofpa13cv.setVisibility(View.VISIBLE);
                    bi.pofpa14cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.pofpa1597.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.llpofpa151a, null);
                    ClearClass.ClearAllFields(bi.llpofpa151b, null);
                    ClearClass.ClearAllFields(bi.llpofpa151c, null);
                    ClearClass.ClearAllFields(bi.llpofpa151d, null);
                    bi.llpofpa151a.setVisibility(View.GONE);
                    bi.llpofpa151b.setVisibility(View.GONE);
                    bi.llpofpa151c.setVisibility(View.GONE);
                    bi.llpofpa151d.setVisibility(View.GONE);
                    bi.pofpa152.setChecked(false);
                    bi.pofpa152.setVisibility(View.GONE);
                    bi.pofpa15Items.removeAllViews();
                    pofpa15List.clear();
                    bi.pofpa15Items.setVisibility(View.GONE);
                    bi.pofpa15Btn.setVisibility(View.GONE);
                } else {
                    bi.llpofpa151a.setVisibility(View.VISIBLE);
                    bi.llpofpa151b.setVisibility(View.VISIBLE);
                    bi.llpofpa151c.setVisibility(View.VISIBLE);
                    bi.llpofpa151d.setVisibility(View.VISIBLE);
                    bi.pofpa152.setChecked(false);
                    bi.pofpa152.setVisibility(View.VISIBLE);
                    bi.pofpa152.setTag("-1");
                    bi.pofpa15Items.setVisibility(View.VISIBLE);
                    bi.pofpa15Btn.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.pofpa19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.pofpa19a.getId()) {
                    bi.pofpa20cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.pofpa20cv, null);
                    bi.pofpa20cv.setVisibility(View.GONE);
                }
            }
        });


        bi.pofpa21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.pofpa21a.getId()) {
                    bi.pofpa22cv.setVisibility(View.VISIBLE);
                    bi.pofpa23View.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.pofpa22cv, null);
                    ClearClass.ClearAllFields(bi.pofpa23View, null);
                    bi.pofpa23Items.removeAllViews();
                    pofpa23List.clear();
                    bi.pofpa22cv.setVisibility(View.GONE);
                    bi.pofpa23View.setVisibility(View.GONE);
                }
            }
        });

    }


    public void populateSpinner(final Context context) {
        // Spinner Drop down elements
        talukaNames = new ArrayList<>();
        talukaCodes = new ArrayList<>();

        talukaNames.add("....");
        talukaCodes.add("....");

        Collection<TalukasContract> dc = db.getAllTalukas();

        for (TalukasContract d : dc) {
            talukaNames.add(d.getTaluka());
            talukaCodes.add(d.getTalukacode());
        }

        bi.pofpa02.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, talukaNames));

        bi.pofpa02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;

                ucCode = new ArrayList<>();
                ucName = new ArrayList<>();

                ucCode.add("....");
                ucName.add("....");

                Collection<UCsContract> pc = db.getAllUCsbyTaluka(talukaCodes.get(position));
                for (UCsContract p : pc) {
                    ucCode.add(p.getUccode());
                    ucName.add(p.getUcs());
                }

                bi.pofpa03.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, ucName));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        bi.pofpa03.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;

                    /*villageCodes = new ArrayList<>();
                    villageNames = new ArrayList<>();

                    villageCodes.add("....");
                    villageNames.add("....");

                    Collection<VillagesContract> pc =
                            db.getAllPSUsByTaluka(talukaCodes.get(bi.pocfa01.getSelectedItemPosition()),
                                    ucCode.get(bi.pocfa02.getSelectedItemPosition()));
                    for (VillagesContract p : pc) {
                        villageCodes.add(p.getVillagecode());
                        villageNames.add(p.getVillagename());
                    }*/

                lhwCodes = new ArrayList<>();
                lhwNames = new ArrayList<>();

                lhwCodes.add("....");
                lhwNames.add("....");

                Collection<LHWContract> lhw =
                        db.getAllLHWsByTaluka(talukaCodes.get(bi.pofpa02.getSelectedItemPosition()),
                                ucCode.get(bi.pofpa03.getSelectedItemPosition()));
                for (LHWContract p : lhw) {
                    lhwCodes.add(p.getLhwcode());
                    lhwNames.add(p.getLhwname());
                }

                bi.pofpa04.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, lhwNames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bi.pofpa04.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                bi.lhwcode.setText("LHW Code: " + lhwCodes.get(i));
                ClearClass.ClearAllFields(bi.f2Section01, null);
                bi.f2Section01.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void clickListener() {

        bi.pofpa00.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                bi.f2Section01.setVisibility(View.GONE);
                ClearClass.ClearAllFields(bi.f2Section01, false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bi.checkHHBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!formValidation())
                    return;

                cContract = db.getChildById(lhwCodes.get(bi.pofpa04.getSelectedItemPosition()), bi.pofpa00.getText().toString());

                if (cContract == null)
                    cContract = db.getChildById("f1", lhwCodes.get(bi.pofpa04.getSelectedItemPosition()), bi.pofpa00.getText().toString());

                if (cContract == null) {
                    Toast.makeText(F2Section01Activity.this, "Referral ID not Found!", Toast.LENGTH_SHORT).show();
                    ClearClass.ClearAllFields(bi.f2Section01, false);
                    bi.f2Section01.setVisibility(View.GONE);
                    return;
                }
                ClearClass.ClearAllFields(bi.f2Section01, true);
                bi.f2Section01.setVisibility(View.VISIBLE);
                bi.pofpa05.setText(cContract.getChild_name());
                bi.pofpa06.setText(cContract.getF_name());
                bi.pofpa05.setEnabled(false);
                bi.pofpa06.setEnabled(false);
            }
        });

        bi.pofpa15Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pofpa15List.size() == 4) {
                    Toast.makeText(F2Section01Activity.this, "Can't add more than 5 medicine", Toast.LENGTH_SHORT).show();
                    return;
                }

                addViewInPof15();

            }
        });

        bi.pofpa16Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pofpa16List.size() == 4) {
                    Toast.makeText(F2Section01Activity.this, "Can't add more than 5 medicine", Toast.LENGTH_SHORT).show();
                    return;
                }

                addViewInPof16();

            }
        });

        bi.pofpa23Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pofpa23List.size() == 4) {
                    Toast.makeText(F2Section01Activity.this, "Can't add more than 5 medicine", Toast.LENGTH_SHORT).show();
                    return;
                }

                addViewInPof23();

            }
        });

    }

    private void addViewInPof15() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.pofpa15, null);
        bi.pofpa15Items.addView(rowView);
        pofpa15List.add(rowView);

        Pofpa15Binding pofpa15Binding = DataBindingUtil.bind(rowView);
        pofpa15Binding.btnClearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bi.pofpa15Items.removeView(rowView);
                pofpa15List.remove(rowView);
            }
        });

    }

    private void addViewInPof16() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.pofpa15, null);
        bi.pofpa16Items.addView(rowView);
        pofpa16List.add(rowView);

        Pofpa15Binding pofpa15Binding = DataBindingUtil.bind(rowView);
        pofpa15Binding.btnClearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bi.pofpa16Items.removeView(rowView);
                pofpa16List.remove(rowView);
            }
        });

    }

    private void addViewInPof23() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.pofpa15, null);
        bi.pofpa23Items.addView(rowView);
        pofpa23List.add(rowView);

        Pofpa15Binding pofpa15Binding = DataBindingUtil.bind(rowView);
        pofpa15Binding.btnClearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bi.pofpa23Items.removeView(rowView);
                pofpa23List.remove(rowView);
            }
        });

    }



/*

                if (!formValidation())
                    return;

                cContract = db.getChildById(lhwCodes.get(bi.pohra03.getSelectedItemPosition()), bi.pohra04.getText().toString());

                if (cContract == null) {

                    Toast.makeText(F4Section01Activity.this, "Referral ID not Found!", Toast.LENGTH_SHORT).show();
                    ClearClass.ClearAllFields(bi.llform04, null);
                    bi.llform04.setVisibility(View.GONE);
                    return;
                }

                bi.llform04.setVisibility(View.VISIBLE);

            */


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {

                if (bi.pofpa07b.isChecked() || bi.pofpa08d.isChecked()) {
                    finish();
                    startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
                } else {
                    finish();
                    startActivity(new Intent(this, F2Section02Activity.class).putExtra("day", DAY));
                }

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {
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


    private void SaveDraft() throws JSONException {

        MainApp.fc = new FormsContract();
        MainApp.fc.setDeviceID(MainApp.deviceId);
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setFormType(MainApp.formtype);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDevicetagID(getSharedPreferences("tagName", MODE_PRIVATE).getString("tagName", ""));

        MainApp.fc.setCode_lhw(lhwCodes.get(bi.pofpa04.getSelectedItemPosition()));
        MainApp.fc.setRef_ID(bi.pofpa00.getText().toString());

        JSONObject f02 = new JSONObject();
        f02.put("pofp_survey", DAY);

        f02.put("pofpa02", talukaCodes.get(bi.pofpa02.getSelectedItemPosition()));
        f02.put("pofpa03", ucCode.get(bi.pofpa03.getSelectedItemPosition()));

        f02.put("pofpa05", bi.pofpa05.getText().toString());
        f02.put("pofpa06", bi.pofpa06.getText().toString());

//        f02.put("pofpa01", bi.pofpa01.getText().toString());

        f02.put("pofpa07", bi.pofpa07a.isChecked() ? "1"
                : bi.pofpa07b.isChecked() ? "2"
                : "0");

        f02.put("pofpa08", bi.pofpa08a.isChecked() ? "1"
                : bi.pofpa08b.isChecked() ? "2"
                : bi.pofpa08c.isChecked() ? "3"
                : bi.pofpa08d.isChecked() ? "4"
                : bi.pofpa0896.isChecked() ? "96"
                : "0");
        f02.put("pofpa0896x", bi.pofpa0896x.getText().toString());

        f02.put("pofpa09", bi.pofpa09a.isChecked() ? "1"
                : bi.pofpa09b.isChecked() ? "2"
                : bi.pofpa09c.isChecked() ? "3"
                : bi.pofpa09d.isChecked() ? "4"
                : bi.pofpa0996.isChecked() ? "96"
                : "0");
        f02.put("pofpa09cx", bi.pofpa09cx.getText().toString());
        f02.put("pofpa09dx", bi.pofpa09dx.getText().toString());
        f02.put("pofpa0996x", bi.pofpa0996x.getText().toString());

        f02.put("pofpa10", bi.pofpa10a.isChecked() ? "1"
                : bi.pofpa10b.isChecked() ? "2"
                : "0");
        f02.put("pofpa10ax", bi.pofpa10ax.getText().toString());

        f02.put("pofpa11", bi.pofpa11a.isChecked() ? "1"
                : bi.pofpa11b.isChecked() ? "2"
                : bi.pofpa11c.isChecked() ? "3"
                : bi.pofpa11d.isChecked() ? "4"
                : bi.pofpa11e.isChecked() ? "5"
                : bi.pofpa11f.isChecked() ? "6"
                : bi.pofpa11g.isChecked() ? "7"
                : bi.pofpa11h.isChecked() ? "8"
                : bi.pofpa1196.isChecked() ? "96"
                : "0");
        f02.put("pofpa1196x", bi.pofpa1196x.getText().toString());

        f02.put("pofpa12", bi.pofpa12a.isChecked() ? "1"
                : bi.pofpa12b.isChecked() ? "2"
                : "0");
        f02.put("pofpa12ax", bi.pofpa12ax.getText().toString());

        f02.put("pofpa121a", bi.pofpa121a.isChecked() ? "1" : "0");
        f02.put("pofpa121b", bi.pofpa121b.isChecked() ? "2" : "0");
        f02.put("pofpa121c", bi.pofpa121c.isChecked() ? "3" : "0");
        f02.put("pofpa121d", bi.pofpa121d.isChecked() ? "4" : "0");
        f02.put("pofpa121e", bi.pofpa121e.isChecked() ? "5" : "0");
        f02.put("pofpa121f", bi.pofpa121f.isChecked() ? "6" : "0");
        f02.put("pofpa121g", bi.pofpa121g.isChecked() ? "7" : "0");
        f02.put("pofpa12196", bi.pofpa12196.isChecked() ? "96" : "0");
        f02.put("pofpa12196x", bi.pofpa12196x.getText().toString());

        f02.put("pofpa13", bi.pofpa13.getText().toString());

        f02.put("pofpa14", bi.pofpa14a.isChecked() ? "1"
                : bi.pofpa14b.isChecked() ? "2"
                : "0");

        f02.put("pofpa15101a", bi.pofpa151a.getText().toString());
        f02.put("pofpa15101b", bi.pofpa151b.getText().toString());
        f02.put("pofpa15101c", bi.pofpa151c.getText().toString());
        f02.put("pofpa15101d", bi.pofpa151da.isChecked() ? "1"
                : bi.pofpa151db.isChecked() ? "2"
                : "0");
        f02.put("pofpa15201", bi.pofpa152.isChecked() ? "1" : "0");
        f02.put("pofpa150197", bi.pofpa1597.isChecked() ? "97" : "0");

        int counter15 = 2;
        for (View view : pofpa15List) {
            Pofpa15Binding pofpa15Binding = DataBindingUtil.bind(view);
            f02.put("pofpa151" + String.format("%02d", counter15) + "a", pofpa15Binding.pofpa151a.getText().toString());
            f02.put("pofpa151" + String.format("%02d", counter15) + "b", pofpa15Binding.pofpa151b.getText().toString());
            f02.put("pofpa151" + String.format("%02d", counter15) + "c", pofpa15Binding.pofpa151c.getText().toString());
            f02.put("pofpa151" + String.format("%02d", counter15) + "d", pofpa15Binding.pofpa151da.isChecked() ? "1"
                    : pofpa15Binding.pofpa151db.isChecked() ? "2"
                    : "0");
            f02.put("pofpa152" + String.format("%02d", counter15), pofpa15Binding.pofpa152.isChecked() ? "1" : "0");

            counter15++;
        }

        f02.put("pofpa16101a", bi.pofpa161a.getText().toString());
        f02.put("pofpa16101b", bi.pofpa161b.getText().toString());
        f02.put("pofpa16101c", bi.pofpa161c.getText().toString());
        f02.put("pofpa16101d", bi.pofpa161da.isChecked() ? "1"
                : bi.pofpa161db.isChecked() ? "2"
                : "0");
        f02.put("pofpa16201", bi.pofpa162.isChecked() ? "1" : "0");

        int counter16 = 2;
        for (View view : pofpa16List) {
            Pofpa15Binding pofpa15Binding = DataBindingUtil.bind(view);
            f02.put("pofpa161" + String.format("%02d", counter16) + "a", pofpa15Binding.pofpa151a.getText().toString());
            f02.put("pofpa161" + String.format("%02d", counter16) + "b", pofpa15Binding.pofpa151b.getText().toString());
            f02.put("pofpa161" + String.format("%02d", counter16) + "c", pofpa15Binding.pofpa151c.getText().toString());
            f02.put("pofpa161" + String.format("%02d", counter16) + "d", pofpa15Binding.pofpa151da.isChecked() ? "1"
                    : pofpa15Binding.pofpa151db.isChecked() ? "2"
                    : "0");
            f02.put("pofpa162" + String.format("%02d", counter16), pofpa15Binding.pofpa152.isChecked() ? "1" : "0");

            counter16++;
        }

        f02.put("pofpa17", bi.pofpa17.getText().toString());

       /* f02.put("pofpa18", bi.pofpa18a.isChecked() ? "1"
                : bi.pofpa18b.isChecked() ? "2"
                : bi.pofpa18c.isChecked() ? "3"
                : bi.pofpa18d.isChecked() ? "4"
                : bi.pofpa18e.isChecked() ? "5"
                : "0");*/

        f02.put("pofpa19", bi.pofpa19a.isChecked() ? "1"
                : bi.pofpa19b.isChecked() ? "2"
                : "0");

        f02.put("pofpa20a", bi.pofpa20a.isChecked() ? "1" : "0");
        f02.put("pofpa20b", bi.pofpa20b.isChecked() ? "2" : "0");
        f02.put("pofpa20c", bi.pofpa20c.isChecked() ? "3" : "0");
        f02.put("pofpa2096", bi.pofpa2096.isChecked() ? "96" : "0");
        f02.put("pofpa2096x", bi.pofpa2096x.getText().toString());

        f02.put("pofpa21", bi.pofpa21a.isChecked() ? "1"
                : bi.pofpa21b.isChecked() ? "2"
                : "0");

        f02.put("pofpa22", bi.pofpa22a.isChecked() ? "1"
                : bi.pofpa22b.isChecked() ? "2"
                : bi.pofpa22c.isChecked() ? "3"
                : bi.pofpa2296.isChecked() ? "96"
                : "0");
        f02.put("pofpa2296x", bi.pofpa2296x.getText().toString());

        f02.put("pofpa23a", bi.pofpa23a.getText().toString());
        f02.put("pofpa23b", bi.pofpa23b.getText().toString());
        f02.put("pofpa23c", bi.pofpa23c.getText().toString());
        f02.put("pofpa23d", bi.pofpa23da.isChecked() ? "1"
                : bi.pofpa23db.isChecked() ? "2"
                : "0");

        int counter23 = 2;
        for (View view : pofpa23List) {
            Pofpa15Binding pofpa15Binding = DataBindingUtil.bind(view);
            f02.put("pofpa231" + String.format("%02d", counter23) + "a", pofpa15Binding.pofpa151a.getText().toString());
            f02.put("pofpa231" + String.format("%02d", counter23) + "b", pofpa15Binding.pofpa151b.getText().toString());
            f02.put("pofpa231" + String.format("%02d", counter23) + "c", pofpa15Binding.pofpa151c.getText().toString());
            f02.put("pofpa231" + String.format("%02d", counter23) + "d", pofpa15Binding.pofpa151da.isChecked() ? "1"
                    : pofpa15Binding.pofpa151db.isChecked() ? "2"
                    : "0");
            f02.put("pofpa232" + String.format("%02d", counter23), pofpa15Binding.pofpa152.isChecked() ? "1" : "0");

            counter23++;
        }

        MainApp.fc.setsA(String.valueOf(f02));
        MainApp.setGPS(this);

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.f2Section011);
    }

    public void BtnEnd() {

//        if (!ValidatorClass.EmptyTextBox(this, bi.pofpa00, getString(R.string.pocfa06))) return;

        if (!ValidatorClass.EmptyCheckingContainer(this, bi.llF2S1))
            return;

        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            MainApp.endActivity(this, this);
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }

        /*if (!ValidatorClass.EmptyCheckingContainer(this, bi.llF2S1))
            return;

        new AlertDialog.Builder(this)
                .setTitle("END INTERVIEW")
                .setIcon(R.drawable.ic_power_settings_new_black_24dp)
                .setCancelable(false)
                .setCancelable(false)
                .setMessage("Do you want to End Interview??")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            SaveDraft();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (!UpdateDB()) {
                            Toast.makeText(F2Section01Activity.this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        MainApp.endActivity(F2Section01Activity.this, F2Section01Activity.this);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();*/
    }

}
