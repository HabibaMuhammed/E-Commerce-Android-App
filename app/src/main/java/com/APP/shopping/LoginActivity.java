package com.APP.shopping;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.APP.shopping.Model.Users;
import com.APP.shopping.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;
import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private static final String File_Email="rememberMe";
    private EditText InputEmail, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdminLink;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;
    private TextView foregetPassword;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;

    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("sharedPref",0);
        editor = sharedPreferences.edit();

        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        LoginButton = findViewById(R.id.login_btn);
        InputPassword =  findViewById(R.id.login_password_input);
        InputEmail=  findViewById(R.id.login_Email_input);
        AdminLink = findViewById(R.id.admin_panel_link);
        NotAdminLink =  findViewById(R.id.not_admin_panel_link);
        loadingBar = new ProgressDialog(this);
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        foregetPassword = findViewById(R.id.forget_password_link);
        chkBoxRememberMe =findViewById(R.id.remember_me_chkb);

        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = InputEmail.getText().toString();
                String password = InputPassword.getText().toString();

                    if (TextUtils.isEmpty(Email)) {
                        InputEmail.setError("Email is required");
                        InputEmail.requestFocus();}
                   else if (TextUtils.isEmpty(password)) {
                        InputPassword.setError("Password is required");
                        InputPassword.requestFocus();}
                    else {
                        loadingBar.setTitle("Login Account...");
                        loadingBar.setMessage("Please wait, while we are checking the credentials.");
                        loadingBar.setCanceledOnTouchOutside(false);
                        loadingBar.show();
                        mAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    firebaseUser = task.getResult().getUser();
                                    Toast.makeText(getApplicationContext(),"Login Successfully!!",Toast.LENGTH_SHORT).show();
                                    loadingBar.setTitle("Login Account");
                                    loadingBar.setMessage("Please wait, while we are checking the credentials.");
                                    loadingBar.setCanceledOnTouchOutside(false);
                                    loadingBar.show();
                                   // AllowAccessToAccount(Email, password);

                                    if (parentDbName.equals("Admins")) {
                                        mAuth.signInWithEmailAndPassword(InputEmail.getText().toString(),InputPassword.getText().toString());
                                      Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                      loadingBar.dismiss();

                                      Intent intent = new Intent(LoginActivity.this, com.APP.shopping.AdminCategoryActivity.class);
                                      startActivity(intent);

                                    } else if (parentDbName.equals("Users")) {
                                      Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                      loadingBar.dismiss();

                                      AllowAccessToAccount(Email);

                                       Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                       startActivity(intent);
                                       finish();
                                       }
                                }
                                else{Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();}
                            }
                        });
                    }
            }
        });
        foregetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
            }
        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });

    }
    private void AllowAccessToAccount(final String Email)
    {
        if (chkBoxRememberMe.isChecked())
        {
            editor.putString(Prevalent.UserEmailKey,Email);
            editor.putBoolean(Prevalent.IS_LOGGED, true);
            editor.apply();
//            Paper.book().write(Prevalent.UserEmailKey,Email);
//            Paper.book().write(Prevalent.UserPasswordKey, password);
        }
    }





//    private void AllowAccessToAccount(final String Email, final String password)
//    {
//
//        if(chkBoxRememberMe.isChecked())
//        {
//            Paper.book().write(Prevalent.UserEmailKey, Email);
//            Paper.book().write(Prevalent.UserPasswordKey, password);
//        }
//        DatabaseReference RootRef ;
//       RootRef = reference.child(firebaseUser.getUid());
//
//        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child(parentDbName).child(firebaseUser.getUid()).exists()){
//                    Users usersData = dataSnapshot.child(parentDbName).child(firebaseUser.getUid()).getValue(Users.class);
//                    if (usersData.getEmail().equals(Email)) {
//                        if (usersData.getPassword().equals(password)) {
//                            if (parentDbName.equals("Admins")) {
//                                Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
//                                loadingBar.dismiss();
//
//                                Intent intent = new Intent(LoginActivity.this, com.APP.shopping.AdminCategoryActivity.class);
//                                startActivity(intent);
//
//                            } else if (parentDbName.equals("Users")) {
//                                Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
//                                loadingBar.dismiss();
//
//                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                Prevalent.currentOnlineUser = usersData;
//                                startActivity(intent);
//                            }
//
//                        }
//                    else {
//                            loadingBar.dismiss();
//                            Toast.makeText(LoginActivity.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                else {
//                    Toast.makeText(LoginActivity.this, "Account with this Email: " + Email + "do not exists.", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

}
