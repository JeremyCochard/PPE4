package fr.cours.ppe4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;

public class MenuUtilisateur extends AppCompatActivity {
    private Button button_pageAccueilConnexion;
    private Button button_demande_reservation;
    private Button button_historique_reservation;
    private Button buttom_suppression_reservation;
    private Button buttom_modification_reservation;

    private Intent demandeReservation;
    private Intent historiqueReservation;
    private Intent PageAccueilConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utilisateur);

        this.button_pageAccueilConnexion=(Button) findViewById(R.id.button_page_acceuil);
        this.button_demande_reservation=(Button)findViewById(R.id.buttom_demande_reservation);
        this.button_historique_reservation=(Button)findViewById(R.id.buttom_historique_reservation);
        this.buttom_suppression_reservation=(Button)findViewById(R.id.buttom_suppression_reservation);
        this.buttom_modification_reservation=(Button)findViewById(R.id.buttom_modification_reservation);

        button_pageAccueilConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageAccueilConnexion = new Intent(MenuUtilisateur.this, PageAccueilConnexion.class);
                startActivity(PageAccueilConnexion);
            }
        });

        button_demande_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demandeReservation = new Intent(MenuUtilisateur.this, DemandeReservation.class);
                startActivity(demandeReservation);
            }
        });
        button_historique_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historiqueReservation = new Intent(MenuUtilisateur.this, ConsultationReservation.class);
                startActivity(historiqueReservation);
            }
        });
        buttom_suppression_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent suppression = new Intent(MenuUtilisateur.this, SuppressionReservation.class);
                startActivity(suppression);
            }
        });
        buttom_modification_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent suppression = new Intent(MenuUtilisateur.this, ModificationReservation.class);
                startActivity(suppression);
            }
        });



    }


}
