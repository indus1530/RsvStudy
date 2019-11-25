package edu.aku.hassannaqvi.rsvstudy.ui.other;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityEndingBinding;
import edu.aku.hassannaqvi.rsvstudy.utils.DateUtils;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class EndingActivity extends Activity {

    ActivityEndingBinding bi;

    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_ending);
        bi.setCallback(this);


        bi.RS82.setMaxDate(DateUtils.getMonthsBack("dd/MM/yyyy", 1));
        bi.RS82.setMinDate(DateUtils.getDaysBack("dd/MM/yyyy", 1));

        Boolean check = getIntent().getExtras().getBoolean("complete");

        if (check) {
            bi.istatus1.setEnabled(true);
            bi.istatus2.setEnabled(false);
            bi.istatus3.setEnabled(false);
            bi.istatus4.setEnabled(false);
            bi.istatus5.setEnabled(false);
            bi.istatus6.setEnabled(false);
            bi.istatus7.setEnabled(false);
            bi.istatus8.setEnabled(false);

        } else {
            //fldGrpmn0823Reason.setVisibility(View.GONE);
            bi.istatus1.setEnabled(false);
        }


    }

    public void BtnContinue() {

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

        MainApp.fc.setIstatus(bi.istatus1.isChecked() ? "1"
                : bi.istatus2.isChecked() ? "2"
                : bi.istatus3.isChecked() ? "3"
                : bi.istatus4.isChecked() ? "4"
                : bi.istatus5.isChecked() ? "4"
                : bi.istatus6.isChecked() ? "6"
                : bi.istatus7.isChecked() ? "7"
                : bi.istatus8.isChecked() ? "8"
                : "0");

        MainApp.fc.setIstatus88x(bi.istatus888x.getText().toString());

        MainApp.fc.setNextVisit(bi.RS82.getText().toString());
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
        return ValidatorClass.EmptyCheckingContainer(this, bi.llend);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
