package fr.cours.ppe4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

public class DeclarationSinistre extends AppCompatActivity {

    private Button validationReservation, button_demande_reservation, button_historique_reservation, validationSinistre;
    private Intent demandeReservation, historiqueReservation, PageAccueilConnexion;
    private EditText numero_de_déclaration, libelle, numero_container;

    private StringRequest StringRequestsr;
    private RequestQueue requestQueue;

    private String _numero_de_déclaration, _libelle, _numero_container;
    private String url = "http://172.16.252.5/zonestockage/declarationSinistre.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.declaration_sinistre);

        requestQueue = Volley.newRequestQueue(DeclarationSinistre.this);

        this.validationReservation = (Button) findViewById(R.id.validationReservation);
        this.button_demande_reservation = (Button) findViewById(R.id.buttom_demande_reservation);
        this.button_historique_reservation = (Button) findViewById(R.id.buttom_historique_reservation);
        this.validationSinistre=(Button)findViewById(R.id.validationSinistre) ;

        validationReservation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                PageAccueilConnexion = new Intent(DeclarationSinistre.this, PageAccueilConnexion.class);
                startActivity(PageAccueilConnexion);
            }
        });

        button_demande_reservation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                demandeReservation = new Intent(DeclarationSinistre.this, DemandeReservation.class);
                startActivity(demandeReservation);
            }
        });
        button_historique_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historiqueReservation = new Intent(DeclarationSinistre.this, ConsultationReservation.class);
                startActivity(historiqueReservation);
            }
        });

        validationSinistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                declarationSinistre(view);
            }
        });


    }
    protected void declarationSinistre(android.view.View view){

        this.numero_de_déclaration = (EditText) findViewById(R.id.numero_de_déclaration);
        this.libelle = (EditText) findViewById(R.id.duréeJ);
        this.numero_container = (EditText) findViewById(R.id.quantite);

        _numero_de_déclaration=numero_de_déclaration.getText().toString();
        _libelle = libelle.getText().toString();
        _numero_container = numero_container.getText().toString();


        StringRequestsr = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        String reponse = response;

                        if (reponse.equals("DeclaOK")) {
                            Intent myIntent = new Intent(DeclarationSinistre.this, ConsultationReservation.class);
                            startActivity(myIntent);
                        } else if (reponse.equals("ErreurDecla")) {
                            Intent myIntent = new Intent(DeclarationSinistre.this, DeclarationSinistre.class);
                            startActivity(myIntent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err = error.getMessage();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("numeroDecla", _numero_de_déclaration);
                params.put("libelle", _libelle);
                params.put("numeroContainer", _numero_container);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

        };
        requestQueue.add(StringRequestsr);
    }
}
