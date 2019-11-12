package edu.aku.hassannaqvi.uen_po_hhs_fl.ui.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.uen_po_hhs_fl.R;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_po_hhs_fl.core.MainApp;

public class EndingActivity extends Activity {

    private static final String TAG = EndingActivity.class.getSimpleName();
    @BindView(R.id.scrollView01)
    ScrollView scrollView01;
    @BindView(R.id.istatus)
    RadioGroup istatus;
    @BindView(R.id.istatus1)
    RadioButton istatus1;
    @BindView(R.id.istatus2)
    RadioButton istatus2;
    @BindView(R.id.istatus3)
    RadioButton istatus3;
    @BindView(R.id.istatus4)
    RadioButton istatus4;
    @BindView(R.id.istatus5)
    RadioButton istatus5;
    @BindView(R.id.istatus6)
    RadioButton istatus6;
    @BindView(R.id.istatus7)
    RadioButton istatus7;
    @BindView(R.id.istatus8)
    RadioButton istatus8;
    @BindView(R.id.istatus888x)
    EditText istatus888x;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);
        ButterKnife.bind(this);

        Boolean check = getIntent().getExtras().getBoolean("complete");

        if (check) {
            istatus1.setEnabled(true);
            istatus2.setEnabled(false);
            istatus3.setEnabled(false);
            istatus4.setEnabled(false);
            istatus5.setEnabled(false);
            istatus6.setEnabled(false);
            istatus7.setEnabled(false);
            istatus8.setEnabled(false);
            istatus888x.setEnabled(false);
            istatus888x.setText(null);

        } else {
            //fldGrpmn0823Reason.setVisibility(View.GONE);
            istatus1.setEnabled(false);
        }

        istatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (istatus8.isChecked()) {
                    istatus888x.setVisibility(View.VISIBLE);
                    istatus888x.requestFocus();
                } else {
                    istatus888x.setText(null);
                    istatus888x.setVisibility(View.GONE);
                }
            }
        });

    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {
                MainApp.memFlag = 0;

                MainApp.TotalMembersCount = 0;
                MainApp.TotalMWRACount = 0;
                MainApp.mwraCount = 1;
                MainApp.TotalChildCount = 0;
                MainApp.imsCount = 1;
                MainApp.totalImsCount = 0;
                MainApp.motherList.clear();
                MainApp.fatherList.clear();
                MainApp.childList.clear();
                MainApp.positionIm = 0;

//                MainApp.CounterDeceasedMother = 0;
                MainApp.CounterDeceasedChild = 0;

                MainApp.lstChild.clear();


                MainApp.counter = 0;

                MainApp.selectedPos = -1;

                MainApp.randID = 1;

                MainApp.isRsvp = false;
                MainApp.isHead = false;

                MainApp.flag = true;

                finish();

                Intent endSec = new Intent(this, MainActivity.class);
                startActivity(endSec);
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        MainApp.fc.setIstatus(istatus1.isChecked() ? "1"
                : istatus2.isChecked() ? "2"
                : istatus3.isChecked() ? "3"
                : istatus4.isChecked() ? "4"
                : istatus5.isChecked() ? "4"
                : istatus6.isChecked() ? "6"
                : istatus7.isChecked() ? "7"
                : istatus8.isChecked() ? "8"
                : "0");

        MainApp.fc.setIstatus88x(istatus888x.getText().toString());
        MainApp.fc.setEndingdatetime(dtToday);

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateEnding();
        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

//        return true;

    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (istatus.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Not Selected): " + getString(R.string.dcstatus), Toast.LENGTH_LONG).show();
            istatus1.setError("Please Select One");    // Set Error on last radio button
            Log.i(TAG, "istatus: This data is Required!");
            return false;
        } else {
            istatus1.setError(null);
        }

        if (istatus8.isChecked()) {

            if (istatus888x.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.other), Toast.LENGTH_SHORT).show();
                istatus888x.setError("This data is Required!");    // Set Error on last radio button
                Log.i(TAG, "istatus888x: This data is Required!");
                return false;
            } else {
                istatus888x.setError(null);
            }

        }


        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
