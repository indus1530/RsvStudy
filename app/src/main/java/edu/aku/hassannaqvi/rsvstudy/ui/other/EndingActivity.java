package edu.aku.hassannaqvi.rsvstudy.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityEndingBinding;
import edu.aku.hassannaqvi.rsvstudy.validator.ValidatorClass;

public class EndingActivity extends AppCompatActivity {

    ActivityEndingBinding bi;

    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_ending);
        bi.setCallback(this);


        boolean check = getIntent().getBooleanExtra("complete", false);

        if (check) {
            bi.istatus1.setEnabled(true);

            bi.istatus5.setEnabled(false);

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
                MainApp.testCount = 1;
                MainApp.acceptableTest = 0;

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
//                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() {
//        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        MainApp.fc.setIstatus(bi.istatus1.isChecked() ? "1"
                : bi.istatus5.isChecked() ? "2"
                : "0");

//        MainApp.status == 5 && bi.istatus1.isChecked()? "5" ||
//                MainApp.status == 2 && bi.istatus1.isChecked()? "2" ||
//                MainApp.status == 1 && bi.istatus1.isChecked()? "1" ||
//                MainApp.status == 4 && bi.istatus1.isChecked()? "4"

       /* MainApp.fc.setStatus(MainApp.status == 3 && bi.istatus1.isChecked() ? "3"
                : MainApp.status == 5 && bi.istatus1.isChecked() ? "5"
                : MainApp.status == 2 && bi.istatus1.isChecked() ? "2"
                : MainApp.status == 1 && bi.istatus1.isChecked() ? "1"
                : MainApp.status == 4 && bi.istatus1.isChecked() ? "4"
                : MainApp.status == 6 && bi.istatus1.isChecked() ? "6"
                : "0");*/

        MainApp.fc.setEndingdatetime(dtToday);

//        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateEnding();
        //            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
        //            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        return updcount == 1;

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
