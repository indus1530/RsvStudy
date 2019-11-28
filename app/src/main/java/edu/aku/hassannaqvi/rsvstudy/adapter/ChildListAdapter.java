package edu.aku.hassannaqvi.rsvstudy.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.databinding.ItemChildListBinding;
import edu.aku.hassannaqvi.rsvstudy.utils.DateUtils;

public class ChildListAdapter extends RecyclerView.Adapter<ChildListAdapter.ViewHolder> {


    OnItemClicked itemClicked;
    private Context mContext;
    private List<ChildList> mList;

    public ChildListAdapter(Context mContext, List<ChildList> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.setHasStableIds(true);
    }

    public void setItemClicked(OnItemClicked itemClicked) {
        this.itemClicked = itemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {

        ItemChildListBinding bi = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_child_list, null, true);
        return new ViewHolder(bi);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

        holder.bi.dssID.setText(mList.get(i).getDssid());
        holder.bi.studyID.setText(mList.get(i).getStudy_id());
        holder.bi.motherName.setText(mList.get(i).getMother_name() + " / " + mList.get(i).getFather_name());
        holder.bi.dob.setText(DateUtils.ageInMonthsByDOB(DateUtils.getDate(mList.get(i).getDob())) + " month(s)");
        holder.bi.genderImage.setImageResource(mList.get(i).getGender().equals("1") ? R.drawable.boy : R.drawable.girl);

        holder.bi.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemClicked.onItemClick(mList.get(i), i);
            }
        });

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
