package com.APP.shopping;

import android.app.ProgressDialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.APP.shopping.Model.Users;
import com.APP.shopping.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button joinNowButton, loginButton;
    private ProgressDialog loadingBar;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private String parentDBname = "Users";
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("sharedPref",0);
        if(sharedPreferences.getBoolean(Prevalent.IS_LOGGED,false))
        {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_main);
        joinNowButton = (Button) findViewById(R.id.main_join_now_btn);
        loginButton = (Button) findViewById(R.id.main_login_btn);
        loadingBar = new ProgressDialog(this);
        //Paper.init(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.APP.shopping.LoginActivity.class);
                startActivity(intent);
            }
        });
        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        String UserEmailKey = sharedPreferences.getString(Prevalent.UserEmailKey,"");
        //String UserPasswordKey = sharedPreferences.getString(Prevalent.UserPasswordKey,"");
        //String UserEmailKey = Paper.book().read(Prevalent.UserEmailKey);
        //String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

//        if (UserEmailKey != "" && UserPasswordKey != "")
//        {
//            if (!TextUtils.isEmpty(UserEmailKey)  &&  !TextUtils.isEmpty(UserPasswordKey))
//            {
//                AllowAccess(UserEmailKey, UserPasswordKey);
//                loadingBar.setTitle("Already Logged in");
//                loadingBar.setMessage("Please wait.....");
//                loadingBar.setCanceledOnTouchOutside(false);
//                loadingBar.show();
////                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
////                startActivity(intent);
////                finish();
//                loadingBar.dismiss();
//            }
//        }
    }
    ArrayList <Users> user = new ArrayList<>();
    private void AllowAccess(final String Email, final String password)
    {
        //final DatabaseReference RootRef = reference.child(firebaseUser.getUid());
        final DatabaseReference RootRef, userref;
        RootRef = FirebaseDatabase.getInstance().getReference();
        userref = RootRef.child("Users");
        userref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    for (DataSnapshot dataSnapshot:task.getResult().getChildren()) {
                        user.add(new Users(dataSnapshot.child("name").getValue(String.class),
                                dataSnapshot.child("email").getValue(String.class),
                                dataSnapshot.child("phone").getValue(String.class),
                                dataSnapshot.child("password").getValue(String.class),
                                dataSnapshot.child("image").getValue(String.class),
                                dataSnapshot.child("address").getValue(String.class)));

                    }
                }

            }
        });
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child("name").exists()){

                    Users usersData = dataSnapshot.child("Users").child("name").getValue(Users.class);
                    //Users test = dataSnapshot.child("Users").getValue(Users.class);


                    if (usersData.getEmail().equals(Email))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(MainActivity.this, "Please wait, you are already logged in...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);

                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Account with this " + Email + "  do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
