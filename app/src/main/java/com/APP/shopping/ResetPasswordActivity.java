package com.APP.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

   // private Toolbar mtoolbar;
    private Button resetPswdBtn;
    private EditText resetEmailInput;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        mAuth = FirebaseAuth.getInstance();
        resetPswdBtn = (Button) findViewById(R.id.SendEmailbtn);
        resetEmailInput = (EditText) findViewById(R.id.editTextEmailAddress);

        resetPswdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = resetEmailInput.getText().toString();
                if (TextUtils.isEmpty(userEmail))
                {
                    resetEmailInput.setError("A Valid E-mail Address is Required");
                }
                else
                {
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ResetPasswordActivity.this,"Please Check your Email Account ...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
                            }
                            else
                            {
                                String Message = task.getException().getMessage();
                                Toast.makeText(ResetPasswordActivity.this,"Error Occurred" + Message,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
