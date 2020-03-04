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
import edu.aku.hassannaqvi.rsvstudy.contracts.FormsContract;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.databinding.ItemChildListBinding;

public class ChildListAdapter extends RecyclerView.Adapter<ChildListAdapter.ViewHolder> {


    OnItemClicked itemClicked;
    private Context mContext;
    private List<ChildList> mList;
    DatabaseHelper db;
    FormsContract ChildData;

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
        holder.bi.childName.setText(mList.get(i).getChild_name().equals("null") ? "Child Name Not found" : mList.get(i).getChild_name());
        holder.bi.studyID.setText(mList.get(i).getStudy_id());
        holder.bi.motherName.setText(mList.get(i).getMother_name() + " / " + mList.get(i).getFather_name());
        holder.bi.dob.setText(mList.get(i).getDob());
        holder.bi.childIndex.setText(String.valueOf(i + 1));
        holder.bi.genderImage.setImageResource(mList.get(i).getGender().equals("1") ? R.drawable.boy : R.drawable.girl);

        holder.bi.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClicked.onItemClick(mList.get(i), i);

              /*  FormsContract ChildData;
                ChildData = db.isDataExists(mList.get(i).getDssid());
                if (ChildData != null) {
                    if (!ChildData.getStatus().contains("1") && !ChildData.getStatus().contains("2")) {

                    } else {
                        Toast.makeText(mContext, "Completed form for this child already exist!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(mContext, "Completed form for this child already exist!", Toast.LENGTH_LONG).show();
                }
//                ArrayList<FormsContract> ChildData = new ArrayList<>();
//                ChildData = db.isDataExists(mList.get(i).getDssid());
//
//                if (ChildData.size() != 1) {
//                    itemClicked.onItemClick(mList.get(i), i);
//                } else {
//
//                    Toast.makeText(mContext, "Completed form for this child already exist!", Toast.LENGTH_LONG).show();
//                }*/


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
