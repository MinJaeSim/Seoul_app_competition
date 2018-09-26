package com.example.mj975.woder_woman.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.mj975.woder_woman.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseAuth auth;

        EditText emailEditText = findViewById(R.id.enroll_email_edit);
        EditText passwordEditText = findViewById(R.id.enroll_password_edit);
        EditText passwordConfirmEditText = findViewById(R.id.pass_word_confirm_edit);

        Button signUp = findViewById(R.id.button_sign_up);

        if(emailEditText.getText().toString().length() <1) {
            signUp.setEnabled(false);
            signUp.setText("이메일을 입력해주세요");
            signUp.setBackgroundColor(Color.rgb(220, 10, 10));
        }

        passwordConfirmEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!passwordEditText.getText().toString()
                        .equals(passwordConfirmEditText.getText().toString())) {
                    signUp.setEnabled(false);
                    signUp.setText(R.string.wrong_password);
                    signUp.setBackgroundColor(Color.rgb(220, 10, 10));
                } else {
                    signUp.setEnabled(true);
                    signUp.setText("회원 가입");
                    signUp.setBackgroundColor(Color.rgb(10, 220, 10));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        auth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            finish();
                        } else {
                            Snackbar.make(view, "회원가입에 실패하였습니다.",
                                    Snackbar.LENGTH_SHORT).show();
                        }
                    });
        });

    }
}
