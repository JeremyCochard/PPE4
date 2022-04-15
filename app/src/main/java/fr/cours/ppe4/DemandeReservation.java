package fr.cours.ppe4;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class DemandeReservation extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private TextView nbEmplacement, duréeJ, quantite, connexion_login;
    private Spinner spinner;
    private Button validationReservation, button_pageAccueilConnexion, button_demande_reservation, button_historique_reservation;
private String _urgent;
    private Intent demandeReservation, historiqueReservation, PageAccueilConnexion;

    private CookieManager CookieManagercookieManager;
    private CookieHandler cookieManager;
    private StringRequest StringRequestsr;
    private RequestQueue requestQueue;

    private String _nbEmplacement, _duréeJ, _quantite, _login;
    //private String url = "http://192.168.1.42/zonestockage/demandeReservation.php";
    private String url = "http://172.16.252.5/zonestockage/demandeReservation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demande_reservation);

        CookieManagercookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        requestQueue = Volley.newRequestQueue(DemandeReservation.this);

        this.validationReservation = (Button) findViewById(R.id.validationReservation);
        this.button_demande_reservation = (Button) findViewById(R.id.buttom_demande_reservation);
        this.button_historique_reservation = (Button) findViewById(R.id.buttom_historique_reservation);

        this.spinner=(Spinner)findViewById(R.id.spinner_type);
        Spinner spinner = new Spinner(this);
        ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_urgence,
                android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemClickListener(this);

        _urgent = spinner.getSelectedItem().toString();


        validationReservation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                demandeRes(view);
                PageAccueilConnexion = new Intent(DemandeReservation.this, PageAccueilConnexion.class);
                startActivity(PageAccueilConnexion);
            }
        });

        button_demande_reservation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                demandeReservation = new Intent(DemandeReservation.this, DemandeReservation.class);
                startActivity(demandeReservation);
            }
        });
        button_historique_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historiqueReservation = new Intent(DemandeReservation.this, ConsultationReservation.class);
                startActivity(historiqueReservation);
            }
        });
    }
        protected void demandeRes(android.view.View view){

            this.nbEmplacement = (TextView) findViewById(R.id.nbEmplacement);
            this.duréeJ = (TextView) findViewById(R.id.duréeJ);
            this.quantite = (TextView) findViewById(R.id.quantite);
            this.connexion_login=(TextView)findViewById(R.id.connexion_login);

            _login=connexion_login.getText().toString();
            _nbEmplacement = nbEmplacement.getText().toString();
            _duréeJ = duréeJ.getText().toString();
            _quantite = quantite.getText().toString();


            StringRequestsr = new StringRequest(Request.Method.POST, url,

                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            String reponse = response;

                            if (reponse.equals("ReservOk")) {
                                Intent myIntent = new Intent(DemandeReservation.this, ConsultationReservation.class);
                                startActivity(myIntent);
                            } else if (reponse.equals("Erreur Insertion !")) {
                                Intent myIntent = new Intent(DemandeReservation.this, DemandeReservation.class);
                                startActivity(myIntent);
                            }
                        /*JSONObject connected = null;
                        try {
                            connected = new JSONObject(""+response+"");
                            Boolean state = connected.getBoolean("authentification");
                            if (state) {
                                Intent myIntent = new Intent(login.this, MenuUtilisateur.class);
                                startActivity(myIntent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            String err = e.getMessage();
                            Log.e("loginTest", "JsonException" + err);
                        }*/
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String err = error.getMessage();
                            Log.e("login", err);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("login", _login);
                    params.put("nbEmplacement", _nbEmplacement);
                    params.put("duréeJ", _duréeJ);
                    params.put("quantite", _quantite);
                    //params.put("urgent", _urgent);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String spi=adapterView.getItemAtPosition(i).toString();
    }
}