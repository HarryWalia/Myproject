package com.walia.colors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private RelativeLayout mainLayout;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupLogoutDIalog();
    }

    private void setupLogoutDIalog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_logout_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button logoutBtn = dialog.findViewById(R.id.logout_button);
        Button cancelBtn = dialog.findViewById(R.id.cancelBtn);
        logoutBtn.setMovementMethod(new ScrollingMovementMethod());
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void findViews() {
        button = findViewById(R.id.button);
        mainLayout = findViewById(R.id.mainLinear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackground();
            }
        });
    }

    private void changeBackground() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        mainLayout.setBackgroundColor(color);
    }

    @Override
    public void onBackPressed() {
        showLogoutDialog();
    }

    private void showLogoutDialog() {
        if (dialog != null && !dialog.isShowing()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            this.dialog.getWindow().setLayout((int) (width * 0.86), (int) (ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.show();
        }
    }
}
