package fr.cours.ppe4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class PageAccueilConnexion extends AppCompatActivity {

    private Button button_connexion;
    private Button button_inscription;
    private Intent conexionActivityIntent;
    private Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_connexion);

        this.button_connexion = (Button) findViewById(R.id.button_connexion);

        button_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conexionActivityIntent = new Intent(PageAccueilConnexion.this, login.class);
                startActivity(conexionActivityIntent);
            }
        });
    }





}