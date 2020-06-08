package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import com.validatorcrawler.aliazaz.Clear;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section0502Binding;
import edu.aku.hassannaqvi.rsvstudy.databinding.Rs86acvBinding;
import edu.aku.hassannaqvi.rsvstudy.models.RSV;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.ui.other.FormType;
import edu.aku.hassannaqvi.rsvstudy.utils.Constants;
import edu.aku.hassannaqvi.rsvstudy.utils.JSONUtils;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Section0502Activity extends AppCompatActivity {

    private ActivityF1Section0502Binding bi;
    private FormType formType;
    private MutableLiveData<List<View>> rs86acvList;
    private List<View> rs86acvMainList;
    private boolean daysFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section0502);
        bi.setCallback(this);

        setupList();
        formType = (FormType) getIntent().getSerializableExtra(Constants.FORMTYPE);
        setupSkips();
        rs86acvMainList = new ArrayList<>();
        if (rs86acvList == null) {
            rs86acvList = new MutableLiveData<>();
            RSV.txtDaysCounter.set("NO:" + (rs86acvMainList.size() + 1) + "/4");
        }
        rs86acvList.observe(this, item -> {
            RSV.txtDaysCounter.set("NO:" + (item.size() + 1) + "/4");
        });

    }

    private void setupList() {
        RadioGroup[] daysSelectionList = new RadioGroup[]{bi.RS861, bi.RS862, bi.RS863, bi.RS864};
        for (RadioGroup radioItem : daysSelectionList) {
            radioItem.setOnCheckedChangeListener((group, checkedId) -> {
                if (bi.RS861a.isChecked() || bi.RS862a.isChecked() || bi.RS863a.isChecked() || bi.RS864a.isChecked()) {
                    bi.RS86bcv.setVisibility(View.VISIBLE);
                    bi.RS86d1.setVisibility(View.VISIBLE);
                } else {
                    Clear.clearAllFields(bi.RS86bcv);
                    bi.RS86bcv.setVisibility(View.GONE);
                    bi.RS86d1.setVisibility(View.GONE);
                    bi.RS86d1.setText(null);
                }
            });
        }


    }

    private void setupSkips() {

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

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.ll05);
    }

    private void SaveDraft() throws JSONException {

        JSONObject SF = new JSONObject();

        //RS85
        SF.put("RS85", bi.RS85a.isChecked() ? "1"
                : bi.RS85b.isChecked() ? "2"
                : "0");

        //RS86a
        SF.put("RS8611", bi.RS861a.isChecked() ? "1"
                : bi.RS861b.isChecked() ? "2"
                : "0");

        SF.put("RS8612", bi.RS862a.isChecked() ? "1"
                : bi.RS862b.isChecked() ? "2"
                : "0");

        SF.put("RS8613", bi.RS863a.isChecked() ? "1"
                : bi.RS863b.isChecked() ? "2"
                : "0");

        SF.put("RS8614", bi.RS864a.isChecked() ? "1"
                : bi.RS864b.isChecked() ? "2"
                : "0");
        SF.put("RS861d1", bi.RS86d1.getText().toString());


        int counter15 = 2;
        for (View view : rs86acvMainList) {
            Rs86acvBinding pofpa15Binding = DataBindingUtil.bind(view);
            SF.put("RS86" + counter15 + "1", pofpa15Binding.RS861a.isChecked() ? "1"
                    : pofpa15Binding.RS861b.isChecked() ? "2"
                    : "0");

            SF.put("RS86" + counter15 + "2", pofpa15Binding.RS862a.isChecked() ? "1"
                    : pofpa15Binding.RS862b.isChecked() ? "2"
                    : "0");

            SF.put("RS86" + counter15 + "3", pofpa15Binding.RS863a.isChecked() ? "1"
                    : pofpa15Binding.RS863b.isChecked() ? "2"
                    : "0");

            SF.put("RS86" + counter15 + "4", pofpa15Binding.RS864a.isChecked() ? "1"
                    : pofpa15Binding.RS864b.isChecked() ? "2"
                    : "0");
            SF.put("RS86" + counter15 + "d1", pofpa15Binding.RS86d1.getText().toString());

            counter15++;
        }

        //RS86b
        SF.put("RS865", bi.RS865.isChecked() ? "1" : "0");
        SF.put("RS865d", bi.RS865d.getText().toString());
        SF.put("RS866", bi.RS866.isChecked() ? "2" : "0");
        SF.put("RS866d", bi.RS866d.getText().toString());
        SF.put("RS867", bi.RS867.isChecked() ? "3" : "0");
        SF.put("RS867d", bi.RS867d.getText().toString());
        SF.put("RS868", bi.RS868.isChecked() ? "4" : "0");
        SF.put("RS868d", bi.RS868d.getText().toString());

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.fc.getsA()), SF);

            MainApp.fc.setsA(String.valueOf(json_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void addDaysInRS86() {
        addViewInRS86();
    }

    private void addViewInRS86() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.rs86acv, null);
        bi.rs86Items.addView(rowView);
        rs86acvMainList.add(rowView);
        rs86acvList.setValue(rs86acvMainList);
        Rs86acvBinding rs86acvBi = DataBindingUtil.bind(rowView);
        rs86acvBi.setLifecycleOwner(this);
        rs86acvBi.btnClearView.setOnClickListener(view -> {
            bi.rs86Items.removeView(rowView);
            rs86acvMainList.remove(rowView);
            rs86acvList.setValue(rs86acvMainList);
            if (bi.rs86Btn.getVisibility() == View.GONE)
                bi.rs86Btn.setVisibility(View.VISIBLE);
        });

        RadioGroup[] daysSelectionList = new RadioGroup[]{rs86acvBi.RS861, rs86acvBi.RS862, rs86acvBi.RS863, rs86acvBi.RS864};
        for (RadioGroup radioItem : daysSelectionList) {
            radioItem.setOnCheckedChangeListener((group, checkedId) -> {
                if (rs86acvBi.RS861a.isChecked() || rs86acvBi.RS862a.isChecked() || rs86acvBi.RS863a.isChecked() || rs86acvBi.RS864a.isChecked() || bi.RS861a.isChecked() || bi.RS862a.isChecked() || bi.RS863a.isChecked() || bi.RS864a.isChecked()) {
                    bi.RS86bcv.setVisibility(View.VISIBLE);
                } else {
                    Clear.clearAllFields(bi.RS86bcv);
                    bi.RS86bcv.setVisibility(View.GONE);
                }
            });
        }

        if (rs86acvMainList.size() == 3)
            bi.rs86Btn.setVisibility(View.GONE);

    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);
        int updcount = db.updateSA();
        return updcount == 1;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}