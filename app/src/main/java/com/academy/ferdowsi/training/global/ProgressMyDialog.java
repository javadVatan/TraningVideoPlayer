package com.academy.ferdowsi.training.global;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.academy.ferdowsi.training.R;


public class ProgressMyDialog {

    private Context myContext;
    private int resID;
    private ProgressDialog progressBar;
    private ImageView imageView;
    private Animation rotation;
    private View view;

    public ProgressMyDialog(Context context, int resourceIdOfImage) {
        myContext = context;
        resID = resourceIdOfImage;
        LayoutInflater inflater;
        inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dialog_wait, null);
        imageView = view.findViewById(R.id.blankImageView);
        imageView.setBackgroundResource(resID);
        rotation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotation.setRepeatMode(Animation.INFINITE);
        rotation.setDuration(1000);

        TextView tvM;
        tvM = view.findViewById(R.id.tvSubject);
        tvM.setTypeface(Constants.iranSansLight);
    }

    public void setText(String str) {
        TextView tvM;
        tvM = view.findViewById(R.id.tvSubject);
        tvM.setText(str);
    }

    public void show(boolean Cancelable) {
        close();
        progressBar = ProgressDialog.show(myContext, "", "");
        progressBar.setContentView(view);
        progressBar.setCancelable(Cancelable);
        imageView.startAnimation(rotation);
    }

    public void close() {

        if (progressBar != null && progressBar.isShowing()) {
            rotation.cancel();
            progressBar.dismiss();
            progressBar = null;
        }

    }

    public boolean isShowing() {
        return progressBar != null && progressBar.isShowing();
    }
}
