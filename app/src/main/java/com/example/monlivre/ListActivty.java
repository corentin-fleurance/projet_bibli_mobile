package com.example.monlivre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListActivty extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageButton logoutButton;
    ArrayList<Livre> itemsList;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activty);

        itemsList = new ArrayList<>();
        lv = findViewById(R.id.mobile_list);
        mAuth = FirebaseAuth.getInstance();
        logoutButton = (ImageButton) findViewById(R.id.loggout);

        GetData();

        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });

    }

    private static String TAG = "Mydb";

    private void GetData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("livres")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData().get("titre"));
                                String id = document.getId();
                                String titre = String.valueOf(document.getData().get("titre"));
                                String auteur = String.valueOf(document.getData().get("auteur"));
                                String couverture = String.valueOf(document.getData().get("couverture"));
                                String description = String.valueOf(document.getData().get("description"));
                                String annee = String.valueOf(document.getData().get("annee"));
                                boolean lu = Boolean.parseBoolean(String.valueOf(document.getData().get("lu")));

                                itemsList.add(new Livre(
                                        id,titre,auteur,couverture,description,annee,lu
                                ));

                                CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(), R.layout.activity_list_item, itemsList);
                                lv.setAdapter(adapter);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
