package edu.aku.hassannaqvi.rsvstudy.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.adapter.ChildListAdapter;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.databinding.ActivityChildListBinding;
import edu.aku.hassannaqvi.rsvstudy.ui.form1.Section01Activity;

public class ChildListActivity extends AppCompatActivity {


    int areaCode;
    ActivityChildListBinding bi;
    DatabaseHelper db;
    ChildListAdapter adapter;
    List<ChildList> list;
    ArrayList<String> dssids;
    ArrayList<ChildList> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_child_list);

        this.setTitle("Child List");

        areaCode = getIntent().getIntExtra("code", 0);

        db = new DatabaseHelper(this);

        setListener();
        setupViews();
    }

    private void setupViews() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setItemPrefetchEnabled(false);
        bi.childlist.setLayoutManager(manager);
        bi.childlist.setHasFixedSize(true);
        bi.childlist.setItemAnimator(null);
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

        adapter = new ChildListAdapter(ChildListActivity.this, filteredItems);
        bi.childlist.setAdapter(adapter);

        adapter = new ChildListAdapter(this, list);
        bi.childlist.setAdapter(adapter);

        adapter.setItemClicked(new ChildListAdapter.OnItemClicked() {
            @Override
            public void onItemClick(final ChildList item, int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ChildListActivity.this);
                View view = LayoutInflater.from(ChildListActivity.this).inflate(R.layout.layout_dialoge, null);
                builder.setView(view);
                final AlertDialog dialog = builder.create();

                Button cancel, start;
                final CheckBox check;
                cancel = view.findViewById(R.id.cancel);
                start = view.findViewById(R.id.start);
                check = view.findViewById(R.id.checkChild);

                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!check.isChecked()) {
                            check.setError("Required field");
                            return;
                        }
                        startActivity(new Intent(ChildListActivity.this, Section01Activity.class).putExtra("data", item));
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
    }
}
