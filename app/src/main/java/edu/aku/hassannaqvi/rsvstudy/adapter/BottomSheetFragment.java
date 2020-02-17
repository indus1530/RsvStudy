package edu.aku.hassannaqvi.rsvstudy.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import edu.aku.hassannaqvi.rsvstudy.R;

public class BottomSheetFragment extends BottomSheetDialogFragment {


    OnItemClick itemClick;

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet, container);

        LinearLayout main = view.findViewById(R.id.parentLayout);

        for (int i = 0; i < main.getChildCount(); i++) {
            LinearLayout lv = (LinearLayout) main.getChildAt(i);
            final int finalI = i;
            lv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.OnItemClick(finalI);
                }
            });

        }


        return view;
    }

    public interface OnItemClick {
        void OnItemClick(int position);
    }
}
