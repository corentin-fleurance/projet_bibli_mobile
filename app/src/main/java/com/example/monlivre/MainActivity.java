package com.example.monlivre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button login_Button;
    private EditText email;
    private EditText mdp;
    private String val_email;
    private String val_mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        login_Button = (Button) findViewById(R.id.bouton_login);
        email = (EditText) findViewById(R.id.email);
        mdp = (EditText) findViewById(R.id.mdp);

        val_email = email.getText().toString();
        val_mdp = mdp.getText().toString();

        login_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                val_email = email.getText().toString();
                val_mdp = mdp.getText().toString();
                if(val_email.matches("") && val_mdp.matches("")) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.title_champs_vides)
                            .setMessage(R.string.champs_vide)

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setNegativeButton(android.R.string.ok, null)
                            .show();
                }else if (val_email.matches("")){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.title_email_vide)
                            .setMessage(R.string.email_vide)

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setNegativeButton(android.R.string.ok, null)
                            .show();
                }else if(val_mdp.matches("")){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.title_mdp_vide)
                            .setMessage(R.string.mdp_vide)

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setNegativeButton(android.R.string.ok, null)
                            .show();
                }else{
                    signIn(val_email, val_mdp);
                }
            }
        });

    }

    private static final String TAG = "MyActivity";


    private void signIn(final String email, final String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        boolean isSignedIn = (user != null);
        Log.i(TAG, "is signed"+isSignedIn+", user:"+user);

        if (isSignedIn) {
            Intent intent = new Intent(getBaseContext(), ListActivty.class);
            intent.putExtra("email", val_email);
            startActivity(intent);

        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.title_id_faux)
                    .setMessage(R.string.id_faux)

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setNegativeButton(android.R.string.ok, null)
                    .show();
        }

    }
}
