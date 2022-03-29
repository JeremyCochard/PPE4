package fr.cours.ppe4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

public class ConsultationReservation extends AppCompatActivity {
    private Button button_pageAccueilConnexion;
    private Button button_demande_reservation;
    private Button button_historique_reservation;

    private Intent demandeReservation;
    private Intent historiqueReservation;
    private Intent PageAccueilConnexion;

    private CookieManager CookieManagercookieManager;
    private CookieHandler cookieManager;
    private StringRequest StringRequestsr;
    private RequestQueue requestQueue;
    private String url = "http://192.168.1.42/zonestockage/affichageReservationUtilisateur.php";
    //private String url = "http://172.16.252.5/zonestockage/demandeReservation.php";
    public Request<?> stringRequest;

    private String _login;
    private TextView connexion_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultation_reservation);

        this.button_pageAccueilConnexion=(Button) findViewById(R.id.button_page_acceuil);
        this.button_demande_reservation=(Button)findViewById(R.id.buttom_demande_reservation);
        this.button_historique_reservation=(Button)findViewById(R.id.buttom_historique_reservation);


        button_pageAccueilConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageAccueilConnexion = new Intent(ConsultationReservation.this, PageAccueilConnexion.class);
                startActivity(PageAccueilConnexion);
            }
        });

        button_demande_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demandeReservation = new Intent(ConsultationReservation.this, DemandeReservation.class);
                startActivity(demandeReservation);
            }
        });
        button_historique_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historiqueReservation = new Intent(ConsultationReservation.this, ConsultationReservation.class);
                startActivity(historiqueReservation);
            }
        });


    }

    protected void consultationReservation(com.google.android.filament.View view) {
        _login = connexion_login.getText().toString();

        this.connexion_login=(TextView)findViewById(R.id.connexion_login);

        CookieManagercookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequestsr = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        JSONObject connected = null;
                        try {
                            connected = new JSONObject(response);
                            Boolean state = connected.getBoolean("Reservation effectuer !");
                            if (state) {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            String err = e.getMessage();
                            Log.e("MainActivity", "JsonException" + err);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("login", _login);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}


