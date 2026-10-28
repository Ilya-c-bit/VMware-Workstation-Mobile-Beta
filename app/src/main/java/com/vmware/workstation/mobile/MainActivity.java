package com.vmware.workstation.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

private int dp(float value) {
    return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
}

private TextView createText(String text, float size, int color) {
    TextView view = new TextView(this);
    view.setText(text);
    view.setTextSize(size);
    view.setTextColor(color);
    return view;
}

private Button createButton(String text) {
    Button button = new Button(this);
    button.setText(text);
    button.setAllCaps(false);
    return button;
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final int background = Color.rgb(24, 27, 32);
    final int panel = Color.rgb(37, 42, 49);
    final int white = Color.rgb(240, 243, 247);
    final int muted = Color.rgb(165, 175, 188);
    final int accent = Color.rgb(65, 145, 235);

    LinearLayout root = new LinearLayout(this);
    root.setOrientation(LinearLayout.VERTICAL);
    root.setBackgroundColor(background);

    LinearLayout header = new LinearLayout(this);
    header.setOrientation(LinearLayout.VERTICAL);
    header.setPadding(dp(20), dp(24), dp(20), dp(18));
    header.setBackgroundColor(panel);

    TextView title = createText(
            "VMware Workstation Mobile",
            23,
            white
    );
    title.setTypeface(null, Typeface.BOLD);

    TextView subtitle = createText(
            "VIRTUAL MACHINE MANAGER",
            12,
            muted
    );

    header.addView(title);
    header.addView(subtitle);

    root.addView(header);

    ScrollView scroll = new ScrollView(this);

    LinearLayout content = new LinearLayout(this);
    content.setOrientation(LinearLayout.VERTICAL);
    content.setPadding(dp(18), dp(22), dp(18), dp(24));

    TextView section = createText("Virtual Machines", 20, white);
    section.setTypeface(null, Typeface.BOLD);

    content.addView(section);

    TextView emptyState = createText(
            "No virtual machines yet.\n\nCreate your first virtual machine to get started.",
            15,
            muted
    );
    emptyState.setGravity(Gravity.CENTER);
    emptyState.setPadding(dp(18), dp(34), dp(18), dp(34));
    emptyState.setBackgroundColor(panel);

    LinearLayout.LayoutParams emptyParams =
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
    emptyParams.topMargin = dp(16);

    content.addView(emptyState, emptyParams);

    Button createButton = createButton("+ Create virtual machine");
    createButton.setTextColor(Color.WHITE);
    createButton.setBackgroundTintList(
            android.content.res.ColorStateList.valueOf(accent)
    );

    LinearLayout.LayoutParams buttonParams =
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
    buttonParams.topMargin = dp(20);

    content.addView(createButton, buttonParams);

    TextView status = createText(
            "Status: Virtualization engine not connected",
            13,
            muted
    );
    status.setPadding(0, dp(24), 0, dp(8));
    content.addView(status);

    TextView note = createText(
            "This is the initial interface. Real guest operating system execution will require a compatible virtualization engine.",
            13,
            muted
    );
    content.addView(note);

    scroll.addView(content);

    root.addView(
            scroll,
            new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    0,
                    1
            )
    );

    setContentView(root);

    createButton.setOnClickListener(v -> {
        new android.app.AlertDialog.Builder(this)
                .setTitle("Create virtual machine")
                .setMessage(
                        "The virtual machine creation workflow " +
                        "will be implemented in the next stage."
                )
                .setPositiveButton("OK", null)
                .show();
    });
}

      }
