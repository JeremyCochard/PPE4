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
import java.util.Map;

public class SuppressionReservation extends AppCompatActivity {
    private TextView supp_numReserv;
    private Button validationSuppressionReservation, button_pageMenuUtilisateur, button_demande_reservation, button_historique_reservation;

    private Intent demandeReservation, historiqueReservation, PageAccueilConnexion;

    private CookieManager CookieManagercookieManager;
    private CookieHandler cookieManager;
    private StringRequest StringRequestsr;
    private RequestQueue requestQueue;

    private String _supp_numReserv;
    private String url = "http://192.168.1.42/zonestockage/suppressionReservation.php";
    //private String url = "http://172.16.252.5/zonestockage/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suppression_reservation);

        CookieManagercookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        requestQueue = Volley.newRequestQueue(SuppressionReservation.this);

        this.validationSuppressionReservation = (Button) findViewById(R.id.validationSuppressionReservation);
        this.button_demande_reservation = (Button) findViewById(R.id.buttom_demande_reservation);
        this.button_historique_reservation = (Button) findViewById(R.id.buttom_historique_reservation);
        this.button_pageMenuUtilisateur=(Button)findViewById(R.id.button_pageMenuUtilisateur);

        button_pageMenuUtilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageAccueilConnexion = new Intent(SuppressionReservation.this, MenuUtilisateur.class);
                startActivity(PageAccueilConnexion);
            }
        });
        button_demande_reservation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                demandeReservation = new Intent(SuppressionReservation.this, DemandeReservation.class);
                startActivity(demandeReservation);
            }
        });
        button_historique_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historiqueReservation = new Intent(SuppressionReservation.this, ConsultationReservation.class);
                startActivity(historiqueReservation);
            }
        });


        validationSuppressionReservation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                suppRes(view);
                PageAccueilConnexion = new Intent(SuppressionReservation.this, ConsultationReservation.class);
                startActivity(PageAccueilConnexion);
            }
        });
    }

    protected void suppRes(android.view.View view) {

        this.supp_numReserv = (TextView) findViewById(R.id.supp_numReserv);


        _supp_numReserv = supp_numReserv.getText().toString();

        StringRequestsr = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        String reponse = response;
                        Log.v("supp", response);

                        if (reponse.equals("SuppOk")) {
                            Intent myIntent = new Intent(SuppressionReservation.this, ConsultationReservation.class);
                            startActivity(myIntent);
                        } else if (reponse.equals("ErreurSupp")) {
                            Intent myIntent = new Intent(SuppressionReservation.this, ModificationReservation.class);
                            startActivity(myIntent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err = error.getMessage();
                        Log.e("Supp", err);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("numSuppReserv", _supp_numReserv);
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
