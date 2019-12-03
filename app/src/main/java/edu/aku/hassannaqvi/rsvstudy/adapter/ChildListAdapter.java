package edu.aku.hassannaqvi.rsvstudy.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.databinding.ItemChildListBinding;
import edu.aku.hassannaqvi.rsvstudy.utils.DateUtils;

public class ChildListAdapter extends RecyclerView.Adapter<ChildListAdapter.ViewHolder> {


    OnItemClicked itemClicked;
    private Context mContext;
    private List<ChildList> mList;
    DatabaseHelper db;

    public ChildListAdapter(Context mContext, List<ChildList> mList) {
        this.mContext = mContext;
        this.mList = mList;
        db = new DatabaseHelper(mContext);

    }

    public void setItemClicked(OnItemClicked itemClicked) {
        this.itemClicked = itemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {

        ItemChildListBinding bi = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_child_list, viewGroup, false);
        return new ViewHolder(bi);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {

        holder.bi.dssID.setText(mList.get(i).getDssid());
        holder.bi.studyID.setText(mList.get(i).getStudy_id());
        holder.bi.motherName.setText(mList.get(i).getMother_name() + " / " + mList.get(i).getFather_name());
        holder.bi.dob.setText(DateUtils.ageInMonthsByDOB(DateUtils.getDate(mList.get(i).getDob())) + " month(s)");
        holder.bi.genderImage.setImageResource(mList.get(i).getGender().equals("1") ? R.drawable.boy : R.drawable.girl);

        holder.bi.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.isDataExists(mList.get(i).getDssid())) {
                    if (db.getChildStatus(mList.get(i).getDssid()).equals("3")
                            || db.getChildStatus(mList.get(i).getDssid()).equals("4")
                            || db.getChildStatus(mList.get(i).getDssid()).equals("5")) {
                        itemClicked.onItemClick(mList.get(i), i);
                    } else {
                        Toast.makeText(mContext, "Data already exist!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    itemClicked.onItemClick(mList.get(i), i);
                }


            }
        });

        if (db.isDataExists(mList.get(i).getDssid())) {
            holder.bi.childStatus.setVisibility(View.VISIBLE);
            if (db.getChildStatus(mList.get(i).getDssid()).equals("1")) {
                holder.bi.parentLayout.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.enrolled));
                holder.bi.childStatus.setBackgroundColor(mContext.getResources().getColor(R.color.enrolled));
                holder.bi.childStatus.setText("Enrolled");
            } else if (db.getChildStatus(mList.get(i).getDssid()).equals("2")) {
                holder.bi.parentLayout.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.died));
                holder.bi.childStatus.setBackgroundColor(mContext.getResources().getColor(R.color.died));
                holder.bi.childStatus.setText("Died");
            } else if (db.getChildStatus(mList.get(i).getDssid()).equals("3")) {
                holder.bi.parentLayout.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.locked));
                holder.bi.childStatus.setBackgroundColor(mContext.getResources().getColor(R.color.locked));
                holder.bi.childStatus.setText("Locked");
            } else if (db.getChildStatus(mList.get(i).getDssid()).equals("4")) {
                holder.bi.parentLayout.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.refused));
                holder.bi.childStatus.setBackgroundColor(mContext.getResources().getColor(R.color.refused));
                holder.bi.childStatus.setText("Refused");
            } else if (db.getChildStatus(mList.get(i).getDssid()).equals("5")) {
                holder.bi.parentLayout.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.shifted));
                holder.bi.childStatus.setBackgroundColor(mContext.getResources().getColor(R.color.shifted));
                holder.bi.childStatus.setText("Shifted");
            }
        } else {
            holder.bi.childStatus.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClicked {

        void onItemClick(ChildList item, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ItemChildListBinding bi;

        public ViewHolder(@NonNull ItemChildListBinding itemView) {
            super(itemView.getRoot());

            bi = itemView;
        }
    }
}
