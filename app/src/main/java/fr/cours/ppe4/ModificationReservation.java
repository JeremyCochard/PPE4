package fr.cours.ppe4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.util.Map;;

public class ModificationReservation extends AppCompatActivity{
    private TextView modif_login, modif_duréeJ, modif_quatite, modif_numReserv;
    private Button validationModifReservation, button_pageMenuUtilisateur, button_demande_reservation, button_historique_reservation;

    private Intent demandeReservation, historiqueReservation, PageAccueilConnexion;

    private CookieManager CookieManagercookieManager;
    private CookieHandler cookieManager;
    private StringRequest StringRequestsr;
    private RequestQueue requestQueue;

    private String _login, _modif_duréeJ, _modif_quatite, _modif_numReserv;
    private String url = "http://192.168.1.42/zonestockage/modificationReservation.php";
    //private String url = "http://172.16.252.5/zonestockage/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modification_reservation);

        CookieManagercookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        requestQueue = Volley.newRequestQueue(ModificationReservation.this);

        this.validationModifReservation = (Button) findViewById(R.id.validationReservation);
        this.button_demande_reservation = (Button) findViewById(R.id.buttom_demande_reservation);
        this.button_historique_reservation = (Button) findViewById(R.id.buttom_historique_reservation);
        this.button_pageMenuUtilisateur=(Button)findViewById(R.id.button_pageMenuUtilisateur);
        this.modif_login=(TextView)findViewById(R.id.modif_login);



        button_pageMenuUtilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageAccueilConnexion = new Intent(ModificationReservation.this, MenuUtilisateur.class);
                startActivity(PageAccueilConnexion);
            }
        });
        button_demande_reservation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                demandeReservation = new Intent(ModificationReservation.this, DemandeReservation.class);
                startActivity(demandeReservation);
            }
        });
        button_historique_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historiqueReservation = new Intent(ModificationReservation.this, ConsultationReservation.class);
                startActivity(historiqueReservation);
            }
        });


        validationModifReservation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                modifRes(view);
                PageAccueilConnexion = new Intent(ModificationReservation.this, ConsultationReservation.class);
                startActivity(PageAccueilConnexion);
            }
        });
    }

    protected void modifRes(android.view.View view) {

        this.modif_duréeJ = (TextView) findViewById(R.id.modif_duréeJ);
        this.modif_quatite = (TextView) findViewById(R.id.modif_quatite);
        this.modif_numReserv = (TextView) findViewById(R.id.modif_numReserv);
        this.modif_login=(TextView)findViewById(R.id.modif_login);

        _modif_numReserv = modif_numReserv.getText().toString();
        _modif_duréeJ = modif_duréeJ.getText().toString();
        _modif_quatite = modif_quatite.getText().toString();
        _login=modif_login.getText().toString();


        StringRequestsr = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        String reponse = response;
                        Log.v("modification", reponse);

                        if (reponse.equals("UpdateOk")) {
                            Intent myIntent = new Intent(ModificationReservation.this, MenuUtilisateur.class);
                            startActivity(myIntent);
                        } else if (reponse.equals("Erreur Modif !")) {
                            Intent myIntent = new Intent(ModificationReservation.this, ModificationReservation.class);
                            startActivity(myIntent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err = error.getMessage();
                        Log.e("Modif", err);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("numReserv", _modif_numReserv);
                params.put("duréeJ", _modif_duréeJ);
                params.put("quantite", _modif_quatite);
                params.put("login", _login);
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

