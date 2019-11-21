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

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section05Binding;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class Section05Activity extends AppCompatActivity {

    ActivityF1Section05Binding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section05);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        setupSkips();


    }


    private void setupSkips() {

        bi.RS49.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS49b.getId()) {
                    ClearClass.ClearAllFields(bi.RS50cv, null);
                    ClearClass.ClearAllFields(bi.RS51cv, null);
                    ClearClass.ClearAllFields(bi.RS52cv, null);
                    ClearClass.ClearAllFields(bi.RS53cv, null);
                    ClearClass.ClearAllFields(bi.RS54cv, null);
                    bi.RS50cv.setVisibility(View.GONE);
                    bi.RS51cv.setVisibility(View.GONE);
                    bi.RS52cv.setVisibility(View.GONE);
                    bi.RS53cv.setVisibility(View.GONE);
                    bi.RS54cv.setVisibility(View.GONE);
                } else {
                    bi.RS50cv.setVisibility(View.VISIBLE);
                    bi.RS51cv.setVisibility(View.VISIBLE);
                    bi.RS52cv.setVisibility(View.VISIBLE);
                    bi.RS53cv.setVisibility(View.VISIBLE);
                    bi.RS54cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.RS55.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS55b.getId()) {
                    ClearClass.ClearAllFields(bi.RS56cv, null);
                    bi.RS56cv.setVisibility(View.GONE);
                } else {
                    bi.RS56cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.RS57.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS57b.getId()) {
                    ClearClass.ClearAllFields(bi.RS58cv, null);
                    ClearClass.ClearAllFields(bi.RS59cv, null);
                    ClearClass.ClearAllFields(bi.RS60cv, null);
                    ClearClass.ClearAllFields(bi.RS61cv, null);
                    ClearClass.ClearAllFields(bi.RS62cv, null);
                    bi.RS58cv.setVisibility(View.GONE);
                    bi.RS59cv.setVisibility(View.GONE);
                    bi.RS60cv.setVisibility(View.GONE);
                    bi.RS61cv.setVisibility(View.GONE);
                    bi.RS62cv.setVisibility(View.GONE);
                } else {
                    bi.RS58cv.setVisibility(View.VISIBLE);
                    bi.RS59cv.setVisibility(View.VISIBLE);
                    bi.RS60cv.setVisibility(View.VISIBLE);
                    bi.RS61cv.setVisibility(View.VISIBLE);
                    bi.RS62cv.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.RS58.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS58b.getId()) {
                    ClearClass.ClearAllFields(bi.RS59cv, null);
                    ClearClass.ClearAllFields(bi.RS60cv, null);
                    bi.RS59cv.setVisibility(View.GONE);
                    bi.RS60cv.setVisibility(View.GONE);
                } else {
                    bi.RS59cv.setVisibility(View.VISIBLE);
                    bi.RS60cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.RS63.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS63b.getId()) {
                    ClearClass.ClearAllFields(bi.RS64cv, null);
                    bi.RS64cv.setVisibility(View.GONE);
                } else {
                    bi.RS64cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.RS67.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS67b.getId()) {
                    ClearClass.ClearAllFields(bi.RS68cv, null);
                    ClearClass.ClearAllFields(bi.RS69cv, null);
                    ClearClass.ClearAllFields(bi.RS70cv, null);
                    ClearClass.ClearAllFields(bi.RS71cv, null);
                    bi.RS68cv.setVisibility(View.GONE);
                    bi.RS69cv.setVisibility(View.GONE);
                    bi.RS70cv.setVisibility(View.GONE);
                    bi.RS71cv.setVisibility(View.GONE);
                } else {
                    bi.RS68cv.setVisibility(View.VISIBLE);
                    bi.RS69cv.setVisibility(View.VISIBLE);
                    bi.RS70cv.setVisibility(View.VISIBLE);
                    bi.RS71cv.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.RS69.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS69b.getId()) {
                    ClearClass.ClearAllFields(bi.RS70cv, null);
                    ClearClass.ClearAllFields(bi.RS71cv, null);
                    bi.RS70cv.setVisibility(View.GONE);
                    bi.RS71cv.setVisibility(View.GONE);
                } else {
                    bi.RS70cv.setVisibility(View.VISIBLE);
                    bi.RS71cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.RS72.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS72b.getId()) {
                    ClearClass.ClearAllFields(bi.RS73cv, null);
                    bi.RS73cv.setVisibility(View.GONE);
                } else {
                    bi.RS73cv.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.RS77.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS77b.getId()) {
                    ClearClass.ClearAllFields(bi.RS78cv, null);
                    bi.RS78cv.setVisibility(View.GONE);
                } else {
                    bi.RS78cv.setVisibility(View.VISIBLE);
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
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));

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

        //RS49
        SF.put("RS49", bi.RS49a.isChecked() ? "1"
                : bi.RS49b.isChecked() ? "2"
                : bi.RS4998.isChecked() ? "98"
                : "0");

        //RS50
        SF.put("RS50", bi.RS50a.isChecked() ? "1"
                : bi.RS50b.isChecked() ? "2"
                : bi.RS5098.isChecked() ? "98"
                : "0");

        //RS51
        SF.put("RS51", bi.RS51a.isChecked() ? "1"
                : bi.RS51b.isChecked() ? "2"
                : bi.RS5198.isChecked() ? "98"
                : "0");

        //RS52a
        SF.put("RS52a", bi.RS52a.getText().toString());
        //RS52b
        SF.put("RS52b", bi.RS52b.getText().toString());

        //RS53
        SF.put("RS53", bi.RS53a.isChecked() ? "1"
                : bi.RS53b.isChecked() ? "2"
                : bi.RS5398.isChecked() ? "98"
                : "0");

        //RS54
        SF.put("RS54", bi.RS54a.isChecked() ? "1"
                : bi.RS54b.isChecked() ? "2"
                : bi.RS54c.isChecked() ? "3"
                : bi.RS5497.isChecked() ? "97"
                : "0");

        //RS55
        SF.put("RS55", bi.RS55a.isChecked() ? "1"
                : bi.RS55b.isChecked() ? "2"
                : bi.RS5598.isChecked() ? "98"
                : "0");

        //RS56
        SF.put("RS56", bi.RS56a.isChecked() ? "1"
                : bi.RS56b.isChecked() ? "2"
                : bi.RS5698.isChecked() ? "98"
                : "0");

        //RS57
        SF.put("RS57", bi.RS57a.isChecked() ? "1"
                : bi.RS57b.isChecked() ? "2"
                : bi.RS5798.isChecked() ? "98"
                : "0");

        //RS58
        SF.put("RS58", bi.RS58a.isChecked() ? "1"
                : bi.RS58b.isChecked() ? "2"
                : bi.RS5898.isChecked() ? "98"
                : "0");

        //RS59
        SF.put("RS59", bi.RS59.getText().toString());

        //RS60
        SF.put("RS60", bi.RS60a.isChecked() ? "1"
                : bi.RS60b.isChecked() ? "2"
                : bi.RS6097.isChecked() ? "97"
                : "0");

        //RS61
        SF.put("RS61", bi.RS61a.isChecked() ? "1"
                : bi.RS61b.isChecked() ? "2"
                : bi.RS6197.isChecked() ? "97"
                : "0");

        //RS62
        SF.put("RS62", bi.RS62a.isChecked() ? "1"
                : bi.RS62b.isChecked() ? "2"
                : bi.RS6298.isChecked() ? "98"
                : "0");

        //RS63
        SF.put("RS63", bi.RS63a.isChecked() ? "1"
                : bi.RS63b.isChecked() ? "2"
                : bi.RS6398.isChecked() ? "98"
                : "0");

        //RS63
        SF.put("RS63", bi.RS63a.isChecked() ? "1"
                : bi.RS63b.isChecked() ? "2"
                : bi.RS6398.isChecked() ? "98"
                : "0");

        //RS64
        SF.put("RS64", bi.RS64a.isChecked() ? "1"
                : bi.RS64b.isChecked() ? "2"
                : bi.RS64c.isChecked() ? "3"
                : bi.RS6496.isChecked() ? "96"
                : "0");
        SF.put("RS6496x", bi.RS6496x.getText().toString());

        //RS65
        SF.put("RS65", bi.RS65a.isChecked() ? "1"
                : bi.RS65b.isChecked() ? "2"
                : bi.RS6598.isChecked() ? "98"
                : "0");

        //RS66
        SF.put("RS66", bi.RS66a.isChecked() ? "1"
                : bi.RS66b.isChecked() ? "2"
                : bi.RS6698.isChecked() ? "98"
                : "0");

        //RS67
        SF.put("RS67", bi.RS67a.isChecked() ? "1"
                : bi.RS67b.isChecked() ? "2"
                : bi.RS6798.isChecked() ? "98"
                : "0");

        //RS68
        SF.put("RS68", bi.RS68a.isChecked() ? "1"
                : bi.RS68b.isChecked() ? "2"
                : bi.RS68c.isChecked() ? "3"
                : bi.RS6897.isChecked() ? "97"
                : "0");

        //RS69
        SF.put("RS69", bi.RS69a.isChecked() ? "1"
                : bi.RS69b.isChecked() ? "2"
                : bi.RS6998.isChecked() ? "98"
                : "0");

        //RS70
        SF.put("RS70a", bi.RS70a.isChecked() ? "1" : "0");
        SF.put("RS70b", bi.RS70b.isChecked() ? "2" : "0");
        SF.put("RS70c", bi.RS70c.isChecked() ? "3" : "0");
        SF.put("RS70d", bi.RS70d.isChecked() ? "4" : "0");
        SF.put("RS70e", bi.RS70e.isChecked() ? "5" : "0");
        SF.put("RS70f", bi.RS70f.isChecked() ? "6" : "0");
        SF.put("RS70g", bi.RS70g.isChecked() ? "7" : "0");
        SF.put("RS70h", bi.RS70h.isChecked() ? "8" : "0");
        SF.put("RS70i", bi.RS70i.isChecked() ? "9" : "0");
        SF.put("RS70j", bi.RS70j.isChecked() ? "10" : "0");
        SF.put("RS70k", bi.RS70k.isChecked() ? "11" : "0");
        SF.put("RS70l", bi.RS70l.isChecked() ? "12" : "0");

        //RS71
        SF.put("RS71", bi.RS71a.isChecked() ? "1"
                : bi.RS71b.isChecked() ? "2"
                : bi.RS71c.isChecked() ? "3"
                : bi.RS71d.isChecked() ? "4"
                : "0");

        //RS72
        SF.put("RS72", bi.RS72a.isChecked() ? "1"
                : bi.RS72b.isChecked() ? "2"
                : bi.RS7298.isChecked() ? "98"
                : "0");

        //RS73
        SF.put("RS73", bi.RS73a.isChecked() ? "1"
                : bi.RS73b.isChecked() ? "2"
                : bi.RS7398.isChecked() ? "98"
                : "0");

        //RS741
        SF.put("RS741", bi.RS741a.isChecked() ? "1"
                : bi.RS741b.isChecked() ? "2"
                : "0");

        //RS742
        SF.put("RS742", bi.RS742a.isChecked() ? "1"
                : bi.RS742b.isChecked() ? "2"
                : "0");

        //RS743
        SF.put("RS743", bi.RS743a.isChecked() ? "1"
                : bi.RS743b.isChecked() ? "2"
                : "0");

        //RS751
        SF.put("RS751", bi.RS751a.isChecked() ? "1"
                : bi.RS751b.isChecked() ? "2"
                : "0");

        //RS752
        SF.put("RS752", bi.RS752a.isChecked() ? "1"
                : bi.RS752b.isChecked() ? "2"
                : "0");

        //RS753
        SF.put("RS753", bi.RS753a.isChecked() ? "1"
                : bi.RS753b.isChecked() ? "2"
                : "0");

        //RS761
        SF.put("RS761", bi.RS761a.isChecked() ? "1"
                : bi.RS761b.isChecked() ? "2"
                : bi.RS76198.isChecked() ? "98"
                : "0");

        //RS762
        SF.put("RS762", bi.RS762a.isChecked() ? "1"
                : bi.RS762b.isChecked() ? "2"
                : bi.RS76298.isChecked() ? "98"
                : "0");

        //RS763
        SF.put("RS763", bi.RS763a.isChecked() ? "1"
                : bi.RS763b.isChecked() ? "2"
                : bi.RS76398.isChecked() ? "98"
                : "0");

        //RS764
        SF.put("RS764", bi.RS764a.isChecked() ? "1"
                : bi.RS764b.isChecked() ? "2"
                : bi.RS76498.isChecked() ? "98"
                : "0");

        //RS765
        SF.put("RS765", bi.RS765a.isChecked() ? "1"
                : bi.RS765b.isChecked() ? "2"
                : bi.RS76598.isChecked() ? "98"
                : "0");

        //RS766
        SF.put("RS766", bi.RS766a.isChecked() ? "1"
                : bi.RS766b.isChecked() ? "2"
                : bi.RS76698.isChecked() ? "98"
                : "0");

        //RS767
        SF.put("RS767", bi.RS767a.isChecked() ? "1"
                : bi.RS767b.isChecked() ? "2"
                : bi.RS76798.isChecked() ? "98"
                : "0");

        //RS768
        SF.put("RS768", bi.RS768a.isChecked() ? "1"
                : bi.RS768b.isChecked() ? "2"
                : bi.RS76898.isChecked() ? "98"
                : "0");

        //RS769
        SF.put("RS769", bi.RS769a.isChecked() ? "1"
                : bi.RS769b.isChecked() ? "2"
                : bi.RS76998.isChecked() ? "98"
                : "0");

        //RS7610
        SF.put("RS7610", bi.RS7610a.isChecked() ? "1"
                : bi.RS7610b.isChecked() ? "2"
                : bi.RS761098.isChecked() ? "98"
                : "0");

        //RS7611
        SF.put("RS7611", bi.RS7611a.isChecked() ? "1"
                : bi.RS7611b.isChecked() ? "2"
                : bi.RS761198.isChecked() ? "98"
                : "0");

        //RS7612
        SF.put("RS7612", bi.RS7612a.isChecked() ? "1"
                : bi.RS7612b.isChecked() ? "2"
                : bi.RS761298.isChecked() ? "98"
                : "0");

        //RS77
        SF.put("RS77", bi.RS77a.isChecked() ? "1"
                : bi.RS77b.isChecked() ? "2"
                : bi.RS7798.isChecked() ? "98"
                : "0");

        //RS78
        SF.put("RS78", bi.RS78.getText().toString());


        MainApp.fc.setsF(String.valueOf(SF));

    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSF();

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