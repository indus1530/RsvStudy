package edu.aku.hassannaqvi.rsvstudy.ui.other;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import edu.aku.hassannaqvi.rsvstudy.FormsList;
import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.adapter.BottomSheetFragment;
import edu.aku.hassannaqvi.rsvstudy.contracts.AreasContract;
import edu.aku.hassannaqvi.rsvstudy.contracts.FormsContract;
import edu.aku.hassannaqvi.rsvstudy.contracts.VersionAppContract;
import edu.aku.hassannaqvi.rsvstudy.core.AndroidDatabaseManager;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityMainBinding;
import edu.aku.hassannaqvi.rsvstudy.ui.ChildListActivity;
import edu.aku.hassannaqvi.rsvstudy.ui.sync.SyncActivity;
import edu.aku.hassannaqvi.rsvstudy.utils.Constants;

public class MainActivity extends AppCompatActivity {

    static File file;
    private final String TAG = "MainActivity";
    ActivityMainBinding bi;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());
    String dtToday1 = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    String m_Text = "";
    ProgressDialog mProgressDialog;
    ArrayList<String> lablesAreas;
    Collection<AreasContract> AreasList;
    Map<String, String> AreasMap;
    SharedPreferences sharedPrefDownload;
    SharedPreferences.Editor editorDownload;
    DownloadManager downloadManager;
    String preVer = "", newVer = "";
    VersionAppContract versionAppContract;
    DatabaseHelper db;
    Long refID;
    FormType fType;
    private ProgressDialog pd;
    private Boolean exit = false;
    private String rSumText = "";

    private void loadTagDialog() {
        sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        editor = sharedPref.edit();
        if (!sharedPref.contains("tagName") && sharedPref.getString("tagName", null) == null) {

            builder = new AlertDialog.Builder(MainActivity.this);
            ImageView img = new ImageView(getApplicationContext());
            img.setImageResource(R.drawable.tagimg);
            img.setPadding(0, 15, 0, 15);
            builder.setCustomTitle(img);

            final EditText input = new EditText(MainActivity.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text = input.getText().toString();
                    if (!m_Text.equals("")) {
                        editor.putString("tagName", m_Text);
                        editor.commit();
                        dialog.dismiss();

                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);


        db = new DatabaseHelper(this);


        Collection<FormsContract> todaysForms = db.getTodayForms();
        Collection<FormsContract> unsyncedForms = db.getUnsyncedForms();

        rSumText += "TODAY'S RECORDS SUMMARY\r\n";

        rSumText += "=======================\r\n";
        rSumText += "\r\n";
        rSumText += "Total Forms Today" + "(" + dtToday1 + "): " + todaysForms.size() + "\r\n";
        rSumText += "\r\n";
        if (todaysForms.size() > 0) {
            rSumText += "\tFORMS' LIST: \r\n";
            String iStatus;
            rSumText += "--------------------------------------------------\r\n";
            rSumText += "[ DSS ID ] \t[Form Status] \t[Sync Status]\r\n";
            rSumText += "--------------------------------------------------\r\n";

            for (FormsContract fc : todaysForms) {
                if (fc.getIstatus() != null) {
                    switch (fc.getIstatus()) {
                        case "1":
                            iStatus = "Complete";
                            break;
                        case "2":
                            iStatus = "Incomplete";
                            break;
                        case "3":
                            iStatus = "Refused";
                            break;
                        case "4":
                            iStatus = "Refused";
                            break;
                        default:
                            iStatus = "N/A";
                    }
                } else {
                    iStatus = "N/A";
                }

                rSumText += fc.getDSSID();
                rSumText += "\t\t\t\t\t";

                rSumText += iStatus;
                rSumText += "\t\t\t\t\t";

                rSumText += (fc.getSynced() == null ? "Not Synced" : "Synced");
                rSumText += "\r\n";
                rSumText += "--------------------------------------------------\r\n";
            }
        }


        if (MainApp.admin) {
            bi.databaseBtn.setVisibility(View.VISIBLE);
            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            rSumText += "Last Data Download: \t" + syncPref.getString("LastDownSyncServer", "Never Updated");
            rSumText += "\r\n";
            rSumText += "Last Data Upload: \t" + syncPref.getString("LastUpSyncServer", "Never Synced");
            rSumText += "\r\n";
            rSumText += "\r\n";
            rSumText += "Unsynced Forms: \t" + unsyncedForms.size();
            rSumText += "\r\n";
        }
        Log.d(TAG, "onCreate: " + rSumText);
        bi.recordSummary.setText(rSumText);

        loadTagDialog();

    }


    public void OpenFormFunc(int id) {
        Intent oF = new Intent();
        if (!MainApp.userName.equals("0000")) {
            oF = new Intent(MainActivity.this, ChildListActivity.class).putExtra("code", id);
            startActivity(oF);
        } else {
            Toast.makeText(getApplicationContext(), "Please login Again!", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * f_type
     * 0 = followup
     * 1= assessment
     */
    public void openForm(final FormType formType) {
        if (!MainApp.userName.equals("0000")) {
            final BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), "bottomSheet");
            bottomSheetFragment.setItemClick(new BottomSheetFragment.OnItemClick() {
                @Override
                public void OnItemClick(final int position) {
                    if (formType == FormType.ASSESSMENT) {
                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.item_dialog_prepost);
                        dialog.setCancelable(false);
                        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                        params.copyFrom(dialog.getWindow().getAttributes());
                        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        dialog.show();
                        dialog.getWindow().setAttributes(params);
                        dialog.findViewById(R.id.preTest).setOnClickListener(v -> {
                            Intent oF = new Intent(MainActivity.this, ChildListActivity.class)
                                    .putExtra(Constants.FORMTYPE, FormType.PRETEST).putExtra("code", position + 1);
                            startActivity(oF);
                            dialog.dismiss();
                            bottomSheetFragment.dismiss();

                        });
                        dialog.findViewById(R.id.postTest).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent oF = new Intent(MainActivity.this, ChildListActivity.class)
                                        .putExtra(Constants.FORMTYPE, FormType.POSTTEST).putExtra("code", position + 1);
                                startActivity(oF);
                                dialog.dismiss();
                                bottomSheetFragment.dismiss();

                            }
                        });
                        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                    } else {
                        Intent oF = new Intent(MainActivity.this, ChildListActivity.class)
                                .putExtra(Constants.FORMTYPE, FormType.FOLLOWUP).putExtra("code", position + 1);
                        startActivity(oF);
                        bottomSheetFragment.dismiss();
                    }

                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "Please login Again!", Toast.LENGTH_LONG).show();
        }


    }


    public void openDB() {
        Intent dbmanager = new Intent(getApplicationContext(), AndroidDatabaseManager.class);
        startActivity(dbmanager);
    }

    public void CheckCluster(View v) {
        Intent cluster_list = new Intent(getApplicationContext(), FormsList.class);
        cluster_list.putExtra("dssid", MainApp.regionDss);
        startActivity(cluster_list);

    }

    public void syncServer() {
        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            startActivity(new Intent(this, SyncActivity.class));
        } else {
            Toast.makeText(this, "No network connection available!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity

            startActivity(new Intent(this, LoginActivity.class));

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.onSync:
                startActivity(new Intent(MainActivity.this, SyncActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
