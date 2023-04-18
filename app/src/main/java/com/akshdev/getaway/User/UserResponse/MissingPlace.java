package com.akshdev.getaway.User.UserResponse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.akshdev.getaway.R;
import com.akshdev.getaway.User.UserDashboard;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MissingPlace extends AppCompatActivity {

    TextInputEditText subEdt, bodyEdt;
    AppCompatButton sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_place);

        subEdt = findViewById(R.id.mis_subject);
        bodyEdt = findViewById(R.id.mis_body);
        sendBtn = findViewById(R.id.mis_send);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendTo = "akshatv.ip.20@nitj.ac.in";
                String subject = "(MissingPlace) " + Objects.requireNonNull(subEdt.getText());
                String body = Objects.requireNonNull(bodyEdt.getText()).toString();


                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{sendTo});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);

                // set type of intent
                intent.setType("message/rfc822");

                // startActivity with intent with chooser as Email client using createChooser function
                startActivity(Intent.createChooser(intent, "Send email"));

            }
        });

        findViewById(R.id.missing_place_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDashboard.navigationView.setCheckedItem(R.id.dash_nav_home);
                MissingPlace.super.onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UserDashboard.navigationView.setCheckedItem(R.id.dash_nav_home);
    }
}