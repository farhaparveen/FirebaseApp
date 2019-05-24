package com.example.firebaseapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
EditText email;
EditText pass;
Button b;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.editText);
        pass=findViewById(R.id.editText3);
        b=findViewById(R.id.button);

       b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!email.getText().toString().equals("")&&!pass.getText().toString().equals(""))
               connect(email.getText().toString(),pass.getText().toString());
               else
                   Toast.makeText(MainActivity.this, "Fill....", Toast.LENGTH_SHORT).show();
           }
       });


    }

    private void connect(String email, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                          //  signin();
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void signin() {
        ProgressDialog p=new ProgressDialog(this);
        p.setTitle("Almost there....");
        p.setMessage("Signin..");
        p.show();


    }

    private void updateUI(FirebaseUser user) {

        if(user!=null)
        {
            startActivity(new Intent(this,Home.class));
            finish();
        }
    }
}
