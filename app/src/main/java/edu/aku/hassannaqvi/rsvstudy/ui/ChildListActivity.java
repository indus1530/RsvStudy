package edu.aku.hassannaqvi.rsvstudy.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.adapter.ChildListAdapter;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityChildListBinding;
import edu.aku.hassannaqvi.rsvstudy.databinding.LayoutDialogeBinding;
import edu.aku.hassannaqvi.rsvstudy.ui.form1.Form2BPostTest;
import edu.aku.hassannaqvi.rsvstudy.ui.form1.Form2BPreTest;
import edu.aku.hassannaqvi.rsvstudy.ui.form1.Section05Activity;
import edu.aku.hassannaqvi.rsvstudy.ui.other.FormType;
import edu.aku.hassannaqvi.rsvstudy.utils.Constants;
import edu.aku.hassannaqvi.rsvstudy.utils.DateUtils;

public class ChildListActivity extends AppCompatActivity {


    int areaCode;
    ActivityChildListBinding bi;
    DatabaseHelper db;
    ChildListAdapter adapter;
    List<ChildList> list;
    ArrayList<String> dssids;
    ArrayList<ChildList> filteredItems;
    private TextView dssID;
    FormType formType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_child_list);

        this.setTitle("Child List");

        areaCode = getIntent().getIntExtra("code", 0);
        formType = (FormType) getIntent().getExtras().getSerializable(Constants.FORMTYPE);

        db = new DatabaseHelper(this);

        setListener();
        setupViews();
    }

    private void setupViews() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        bi.childlist.setLayoutManager(manager);
        bi.childlist.setHasFixedSize(true);
        list = db.getChildList(String.valueOf(areaCode));
        setupRecyclerView(list);

    }


    private void setListener() {

        bi.filterDSS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filteredItems = new ArrayList<>();
                if (!s.toString().equals("")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getDssid().toLowerCase().contains(s.toString().toLowerCase())) {
                            filteredItems.add(list.get(i));
                        }
                    }
                    setupRecyclerView(filteredItems);
                } else {
                    setupRecyclerView(list);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void setupRecyclerView(List<ChildList> list) {
        adapter = new ChildListAdapter(this, list);
        bi.childlist.setAdapter(adapter);
        adapter.setItemClicked(new ChildListAdapter.OnItemClicked() {
            @Override
            public void onItemClick(final ChildList item, int position) {
                if (formType == FormType.POSTTEST) {
                    if (!db.isChildExists(item.getDssid())) {
                        Toast.makeText(ChildListActivity.this, "Pre-Test Not Found! Child is not eligible for Post Test", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else if (formType == FormType.PRETEST) {
                    if (!db.hasFollowup(item.getDssid())) {
                        Toast.makeText(ChildListActivity.this, "2nd Follow up Not Found! Child is not Eligible for Assessment", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (db.isChildExists(item.getDssid())) {
                        Toast.makeText(ChildListActivity.this, "Pre-Test Found! This child is eligible for Post Test", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else if (formType == FormType.FOLLOWUP) {
                    if (db.hasFollowup(item.getDssid())) {
                        Toast.makeText(ChildListActivity.this, "Follow Up Done", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                final Dialog dialog = new Dialog(ChildListActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                View view = LayoutInflater.from(ChildListActivity.this).inflate(R.layout.layout_dialoge, null);
                final LayoutDialogeBinding bi = DataBindingUtil.bind(view);
                dialog.setContentView(view);
                dialog.setCancelable(false);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                params.copyFrom(dialog.getWindow().getAttributes());
                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.show();
                dialog.getWindow().setAttributes(params);
                bi.dssID.setText(item.getDssid());
                bi.studyID.setText(item.getStudy_id());
                bi.fatherName.setText(item.getFather_name());
                bi.motherName.setText(item.getMother_name());
                bi.childName.setText(item.getChild_name().equals("null") ? "Child Name Not Found" : item.getChild_name());
                bi.dob.setText(item.getDob());
                bi.gender.setText(item.equals("1") ? "Male" : "Female");
                bi.genderImage.setImageResource(item.getGender().equals("1") ? R.drawable.boy : R.drawable.girl);
                bi.months.setText(String.valueOf(DateUtils.ageInMonthsByDOB(DateUtils.getDate(item.getDob()))));
                bi.start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!bi.checkChild.isChecked()) {
                            bi.checkChild.setError("Required field");
                            return;
                        }
                        startActivity(new Intent(ChildListActivity.this, formType == FormType.FOLLOWUP ? Section05Activity.class
                                : formType == FormType.PRETEST ? Form2BPreTest.class : Form2BPostTest.class)
                                .putExtra("data", item)
                                .putExtra(Constants.FORMTYPE, formType));
                        dialog.dismiss();
                    }
                });
                bi.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.setTitle("Confirm Child");
                dialog.show();


            }
        });
    }
}
