package fr.cours.ppe4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuUtilisateur extends AppCompatActivity {
    private Button button_pageAccueilConnexion;
    private Button button_demande_reservation;
    private Button button_historique_reservation;

    private Button button_Decla;

    private Intent demandeReservation;
    private Intent historiqueReservation;
    private Intent PageAccueilConnexion;

    private Intent declarationSinistre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utilisateur);

        this.button_pageAccueilConnexion=(Button) findViewById(R.id.button_page_acceuil);
        this.button_demande_reservation=(Button)findViewById(R.id.buttom_demande_reservation);
        this.button_historique_reservation=(Button)findViewById(R.id.buttom_historique_reservation);

        this.button_Decla=(Button)findViewById(R.id.button_Decla);


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

        button_Decla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                declarationSinistre = new Intent(MenuUtilisateur.this, DeclarationSinistre.class);
                startActivity(declarationSinistre);
            }
        });



    }


}
