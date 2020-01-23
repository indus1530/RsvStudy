package edu.aku.hassannaqvi.rsvstudy.ui.form1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.contracts.FormsContract;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.ui.other.EndingActivity;
import edu.aku.hassannaqvi.rsvstudy.validator.ClearClass;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;
import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityF1Section05Binding;

public class Section05Activity extends AppCompatActivity {

    ActivityF1Section05Binding bi;
    DatabaseHelper db;
    ChildList item;
    private String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_f1_section05);
        bi.setCallback(this);
        db = new DatabaseHelper(this);

        item = getIntent().getParcelableExtra("data");
        setupSkips();


    }


    private void setupSkips() {
        bi.RS72.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS7297.getId())
                    ClearClass.ClearAllFields(bi.RS7375Layout, null);
            }
        });

        bi.RS83.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.RS83a.getId()) {

                    ClearClass.ClearAllFields(bi.RS84cv, null);
                }
            }
        });

        bi.RS81.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.RS81a.getId()) {
                    ClearClass.ClearAllFields(bi.RS82cv, null);
                }
            }


        });

        bi.RS69.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS69b.getId()) {

                    ClearClass.ClearAllFields(bi.RS70cv, null);

                }
            }
        });

        bi.RS50.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS50b.getId()) {
                    ClearClass.ClearAllFields(bi.RS5155Layout, null);
                }
            }
        });

        bi.RS58.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS58b.getId()) {
                    ClearClass.ClearAllFields(bi.RS5962Layout, null);
                }
            }
        });

        bi.RS63.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS63b.getId()) {
                    ClearClass.ClearAllFields(bi.RS64cv, null);
                }
            }
        });

        bi.RS69.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.RS69b.getId()) {
                    ClearClass.ClearAllFields(bi.RS70cv, null);
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

        MainApp.fc = new FormsContract();
        MainApp.fc.setDeviceID(MainApp.deviceId);
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDevicetagID(getSharedPreferences("tagName", MODE_PRIVATE).getString("tagName", ""));
        MainApp.fc.setDSSID(item.getDssid());

        JSONObject SF = new JSONObject();

//        //RS49
        SF.put("f_type", MainApp.followUp);

        SF.put("RS15", bi.RS15.getText().toString());

        //RS50
        SF.put("RS50", bi.RS50a.isChecked() ? "1"
                : bi.RS50b.isChecked() ? "2"
                : bi.RS5098.isChecked() ? "98"
                : "0");

//        //RS51
        SF.put("RS51", bi.RS51a.isChecked() ? "1"
                : bi.RS51b.isChecked() ? "2"
                : bi.RS5198.isChecked() ? "98"
                : "0");
//
//        //RS52a
//        SF.put("RS52a", bi.RS52a.getText().toString());
//        //RS52b
//        SF.put("RS52b", bi.RS52b.getText().toString());
//
        //RS53
        SF.put("RS53", bi.RS53a.isChecked() ? "1"
                : bi.RS53b.isChecked() ? "2"
                : bi.RS5398.isChecked() ? "98"
                : "0");
//
//        //RS54
        SF.put("RS54", bi.RS54a.isChecked() ? "1"
                : bi.RS54b.isChecked() ? "2"
                : bi.RS54c.isChecked() ? "3"
                : bi.RS5497.isChecked() ? "97"
                : "0");

//        //RS55
        SF.put("RS55", bi.RS55a.isChecked() ? "1"
                : bi.RS55b.isChecked() ? "2"
                : bi.RS5598.isChecked() ? "98"
                : "0");

//        //RS56
//        SF.put("RS56", bi.RS56a.isChecked() ? "1"
//                : bi.RS56b.isChecked() ? "2"
//                : bi.RS5698.isChecked() ? "98"
//                : "0");
//
//        //RS57
//        SF.put("RS57", bi.RS57a.isChecked() ? "1"
//                : bi.RS57b.isChecked() ? "2"
//                : bi.RS5798.isChecked() ? "98"
//                : "0");
//
//        //RS58
        SF.put("RS58", bi.RS58a.isChecked() ? "1"
                : bi.RS58b.isChecked() ? "2"
                : bi.RS5898.isChecked() ? "98"
                : "0");
//
//        //RS59
        SF.put("RS59", bi.RS59.getText().toString());

//
//        //RS60
        SF.put("RS60", bi.RS60a.isChecked() ? "1"
                : bi.RS60b.isChecked() ? "2"
                : bi.RS6097.isChecked() ? "97"
                : "0");
//
//        //RS61
        SF.put("RS61", bi.RS61a.isChecked() ? "1"
                : bi.RS61b.isChecked() ? "2"
                : bi.RS6197.isChecked() ? "97"
                : "0");
//
//        //RS62
        SF.put("RS62", bi.RS62a.isChecked() ? "1"
                : bi.RS62b.isChecked() ? "2"
                : bi.RS6298.isChecked() ? "98"
                : "0");

//        //RS63
        SF.put("RS63", bi.RS63a.isChecked() ? "1"
                : bi.RS63b.isChecked() ? "2"
                : bi.RS6398.isChecked() ? "98"
                : "0");
//
//        //RS63
//        SF.put("RS63", bi.RS63a.isChecked() ? "1"
//                : bi.RS63b.isChecked() ? "2"
//                : bi.RS6398.isChecked() ? "98"
//                : "0");
//
//        //RS64
        SF.put("RS64", bi.RS64a.isChecked() ? "1"
                : bi.RS64b.isChecked() ? "2"
                : bi.RS64c.isChecked() ? "3"
                : bi.RS6496.isChecked() ? "96"
                : "0");
        SF.put("RS6496x", bi.RS6496x.getText().toString());
//
//        //RS65
        SF.put("RS65", bi.RS65a.isChecked() ? "1"
                : bi.RS65b.isChecked() ? "2"
                : bi.RS6598.isChecked() ? "98"
                : "0");
//
//        //RS66
        SF.put("RS66", bi.RS66a.isChecked() ? "1"
                : bi.RS66b.isChecked() ? "2"
                : bi.RS6698.isChecked() ? "98"
                : "0");

//        //RS67
        SF.put("RS67", bi.RS6798.isChecked() ? "98" : bi.RS67.getText().toString());
//
//        //RS68
        SF.put("RS68", bi.RS68.getText().toString());
//
//        //RS69
        SF.put("RS69", bi.RS69a.isChecked() ? "1"
                : bi.RS69b.isChecked() ? "2"
                : bi.RS6998.isChecked() ? "98"
                : "0");

        SF.put("RS70", bi.RS70.getText().toString());

        SF.put("RS72", bi.RS72a.isChecked() ? "1"
                : bi.RS72b.isChecked() ? "2"
                : bi.RS7297.isChecked() ? "97"
                : "0");

        SF.put("RS73", bi.RS73a.isChecked() ? "1"
                : bi.RS73b.isChecked() ? "2"
                : bi.RS7398.isChecked() ? "97"
                : "0");

//
//        //RS74
        SF.put("RS74jan", bi.RS74a.isChecked() ? "1" : "0");
        SF.put("RS74feb", bi.RS74b.isChecked() ? "2" : "0");
        SF.put("RS74mar", bi.RS74c.isChecked() ? "3" : "0");
        SF.put("RS74apr", bi.RS74d.isChecked() ? "4" : "0");
        SF.put("RS74may", bi.RS74e.isChecked() ? "5" : "0");
        SF.put("RS74jun", bi.RS74f.isChecked() ? "6" : "0");
        SF.put("RS74jul", bi.RS74g.isChecked() ? "7" : "0");
        SF.put("RS74aug", bi.RS74h.isChecked() ? "8" : "0");
        SF.put("RS74sep", bi.RS74i.isChecked() ? "9" : "0");
        SF.put("RS74oct", bi.RS74j.isChecked() ? "10" : "0");
        SF.put("RS74nov", bi.RS74k.isChecked() ? "11" : "0");
        SF.put("RS74dec", bi.RS74l.isChecked() ? "12" : "0");
//
//        //RS71
        SF.put("RS75", bi.RS75a.isChecked() ? "1"
                : bi.RS75b.isChecked() ? "2"
                : bi.RS75c.isChecked() ? "3"
                : bi.RS75d.isChecked() ? "4"
                : "0");
//
//        //RS72
        SF.put("RS77", bi.RS77a.isChecked() ? "1"
                : bi.RS77b.isChecked() ? "2"
                : bi.RS7798.isChecked() ? "98"
                : "0");

//        //RS73
//        SF.put("RS73", bi.RS73a.isChecked() ? "1"
//                : bi.RS73b.isChecked() ? "2"
//                : bi.RS7398.isChecked() ? "98"
//                : "0");
//
//        //RS741
        SF.put("RS781", bi.RS781a.isChecked() ? "1"
                : bi.RS781b.isChecked() ? "2"
                : "0");
//
//        //RS742
        SF.put("RS782", bi.RS782a.isChecked() ? "1"
                : bi.RS782b.isChecked() ? "2"
                : "0");
//
//        //RS743
        SF.put("RS783", bi.RS783a.isChecked() ? "1"
                : bi.RS783b.isChecked() ? "2"
                : "0");
//
//        //RS751
        SF.put("RS791", bi.RS791a.isChecked() ? "1"
                : bi.RS791b.isChecked() ? "2"
                : "0");

        //RS752
        SF.put("RS792", bi.RS792a.isChecked() ? "1"
                : bi.RS792b.isChecked() ? "2"
                : "0");

        //RS753
        SF.put("RS793", bi.RS793a.isChecked() ? "1"
                : bi.RS793b.isChecked() ? "2"
                : "0");
//
//        //RS761
        SF.put("RS801", bi.RS801a.isChecked() ? "1"
                : bi.RS801b.isChecked() ? "2"
                : bi.RS80198.isChecked() ? "98"
                : "0");

        //RS762
        SF.put("RS802", bi.RS802a.isChecked() ? "1"
                : bi.RS802b.isChecked() ? "2"
                : bi.RS80298.isChecked() ? "98"
                : "0");

        //RS763
        SF.put("RS803", bi.RS803a.isChecked() ? "1"
                : bi.RS803b.isChecked() ? "2"
                : bi.RS80398.isChecked() ? "98"
                : "0");

        //RS764
        SF.put("RS804", bi.RS804a.isChecked() ? "1"
                : bi.RS804b.isChecked() ? "2"
                : bi.RS80498.isChecked() ? "98"
                : "0");

        //RS765
        SF.put("RS805", bi.RS805a.isChecked() ? "1"
                : bi.RS805b.isChecked() ? "2"
                : bi.RS80598.isChecked() ? "98"
                : "0");

        //RS766
        SF.put("RS806", bi.RS806a.isChecked() ? "1"
                : bi.RS806b.isChecked() ? "2"
                : bi.RS80698.isChecked() ? "98"
                : "0");

        //RS767
        SF.put("RS807", bi.RS807a.isChecked() ? "1"
                : bi.RS807b.isChecked() ? "2"
                : bi.RS80798.isChecked() ? "98"
                : "0");

        //RS768
        SF.put("RS808", bi.RS808a.isChecked() ? "1"
                : bi.RS808b.isChecked() ? "2"
                : bi.RS80898.isChecked() ? "98"
                : "0");

        //RS769
        SF.put("RS809", bi.RS809a.isChecked() ? "1"
                : bi.RS809b.isChecked() ? "2"
                : bi.RS80998.isChecked() ? "98"
                : "0");

        //RS7610
        SF.put("RS8010", bi.RS8010a.isChecked() ? "1"
                : bi.RS8010b.isChecked() ? "2"
                : bi.RS801098.isChecked() ? "98"
                : "0");

        //RS7611
        SF.put("RS8011", bi.RS8011a.isChecked() ? "1"
                : bi.RS8011b.isChecked() ? "2"
                : bi.RS801198.isChecked() ? "98"
                : "0");

        //RS7612
        SF.put("RS8012", bi.RS8012a.isChecked() ? "1"
                : bi.RS8012b.isChecked() ? "2"
                : bi.RS801298.isChecked() ? "98"
                : "0");
//
//        //RS77
        SF.put("RS81", bi.RS81a.isChecked() ? "1"
                : bi.RS81b.isChecked() ? "2"
                : bi.RS8198.isChecked() ? "98"
                : "0");

        //RS78
        SF.put("RS82", bi.RS82.getText().toString());

        SF.put("RS83", bi.RS83a.isChecked() ? "1"
                : bi.RS83b.isChecked() ? "2"
                : bi.RS8398.isChecked() ? "98"
                : "0");

        SF.put("RS84", bi.RS84a.isChecked() ? "1"
                : bi.RS84b.isChecked() ? "2"
                : bi.RS84c.isChecked() ? "3"
                : bi.RS84d.isChecked() ? "4"
                : bi.RS84e.isChecked() ? "5"
                : bi.RS84f.isChecked() ? "6"
                : bi.RS84g.isChecked() ? "7 "
                : "0");

        SF.put("RS85", bi.RS85a.isChecked() ? "1"
                : bi.RS85b.isChecked() ? "2"
                : "0");


        MainApp.fc.setsF(String.valueOf(SF));
        MainApp.setGPS(this);

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

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}