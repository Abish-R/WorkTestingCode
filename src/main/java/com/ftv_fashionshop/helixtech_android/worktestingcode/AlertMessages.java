package com.ftv_fashionshop.helixtech_android.worktestingcode;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by helixtech-android on 3/6/16.
 */
public class AlertMessages {

    Context context;
    public AlertMessages(Context cc){
        context = cc;
    }

    public void customTwoAlert(){
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_alert);
        //dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        //TextView title_text = (TextView) dialog.findViewById(R.id.title_text);
        TextView text = (TextView) dialog.findViewById(R.id.text_msg);
        ImageView image = (ImageView) dialog.findViewById(R.id.title_image);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        Button ok = (Button) dialog.findViewById(R.id.ok);

        //title_text.setText("Custom Dialog");
        text.setText("Android custom dialog example!");
        image.setImageResource(R.mipmap.ic_launcher);

        // if button is clicked, close the custom dialog
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showCustomAlert()
    {

        // Create layout inflator object to inflate toast.xml file
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Call toast.xml file for toast layout
        View toastRoot = inflater.inflate(R.layout.toast, null);

        Toast toast = new Toast(context);

        // Set layout to toast
        toast.setView(toastRoot);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
                0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();

    }

}
