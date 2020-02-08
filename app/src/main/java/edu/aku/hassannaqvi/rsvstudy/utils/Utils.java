package edu.aku.hassannaqvi.rsvstudy.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import edu.aku.hassannaqvi.rsvstudy.R;
import edu.aku.hassannaqvi.rsvstudy.ui.form1.Forms2BafterTest;
import edu.aku.hassannaqvi.rsvstudy.ui.other.FormType;

public class Utils {


    public static void openDialog(final Activity activity, final FormType formType) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_dialog);
        dialog.setCancelable(false);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(dialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(params);

        dialog.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
                activity.startActivity(new Intent(activity, Forms2BafterTest.class)
                        .putExtra(Constants.FORMTYPE, formType));
            }
        });
        dialog.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

}
