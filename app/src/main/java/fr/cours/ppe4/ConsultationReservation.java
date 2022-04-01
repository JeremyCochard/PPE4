package fr.cours.ppe4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

public class ConsultationReservation extends AppCompatActivity {
    private TextView id, dateReservation, datePrevueStockage, nbJoursDeStockagePrevu, quantite, etat;
    private Button button_pageAccueilConnexion, button_demande_reservation, button_historique_reservation, buttonConsultation;

    private Intent demandeReservation, historiqueReservation, PageAccueilConnexion;

    private CookieManager CookieManagercookieManager;
    private CookieHandler cookieManager;
    private StringRequest StringRequestsr;
    private RequestQueue requestQueue;
    private LinearLayout zone_inflate_consultation;
    private LayoutInflater layoutInflater;

    //private String _id, _dateReservation, _datePrevueStockage, _nbJoursDeStockagePrevu, _quantite, _etat, _login;
    private String _login;
    private int _lenghtReq;
    //private String url = "http://192.168.1.42/zonestockage/affichageReservationUtilisateur.php";
    private String url = "http://172.16.252.5/zonestockage/affichageReservationUtilisateur.php";

    private TextView textView8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultation_reservation);

        this.button_pageAccueilConnexion=(Button) findViewById(R.id.button_page_acceuil);
        this.button_demande_reservation=(Button)findViewById(R.id.buttom_demande_reservation);
        this.button_historique_reservation=(Button)findViewById(R.id.buttom_historique_reservation);
        this.buttonConsultation=(Button)findViewById(R.id.buttonConsultation);



        CookieManagercookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        requestQueue = Volley.newRequestQueue(ConsultationReservation.this);

        this.textView8=(TextView)findViewById(R.id.textView8);

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

        buttonConsultation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                consultationReservation(view);
                }
        });

        this.zone_inflate_consultation=(LinearLayout) findViewById(R.id.zone_inflate_consultation);
        layoutInflater=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

    }

    protected void consultationReservation(android.view.View view) {
        _login = "jcoc";


        StringRequestsr = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {
                    TextView id, dateReservation, datePrevueStockage, nbJoursDeStockagePrevu, quantite, etat;
                    String _id, _dateReservation, _datePrevueStockage, _nbJoursDeStockagePrevu, _quantite, _etat;


                    @Override
                    public void onResponse(String response) {
                        JSONArray collectionReservation = null;
                        Log.v("**********", String.valueOf(response));

                        try {

                            collectionReservation = new JSONArray(response);

                                for(int y =0 ; y <= collectionReservation.length()-1; y++) {

                                    View viewInflater = layoutInflater.inflate(R.layout.consultation_inflater, null, false);
                                    zone_inflate_consultation.addView(viewInflater);

                                    JSONObject jsonObject = collectionReservation.getJSONObject(y);

                                    _id=jsonObject.opt("id").toString();
                                    _dateReservation=jsonObject.opt("dateReservation").toString();
                                    _datePrevueStockage=jsonObject.opt("datePrevueStockage").toString();
                                    _nbJoursDeStockagePrevu=jsonObject.opt("nbJoursDeStockagePrevu").toString();
                                    _quantite=jsonObject.opt("quantite").toString();
                                    _etat=jsonObject.opt("etat").toString();

                                    this.id=(TextView)viewInflater.findViewById(R.id.id);
                                    this.dateReservation=(TextView)viewInflater.findViewById(R.id.dateReservation);
                                    this.datePrevueStockage=(TextView)viewInflater.findViewById(R.id.datePrevueStockage);
                                    this.nbJoursDeStockagePrevu=(TextView)viewInflater.findViewById(R.id.nbJoursDeStockagePrevu);
                                    this.quantite=(TextView)viewInflater.findViewById(R.id.quantite);
                                    this.etat=(TextView)viewInflater.findViewById(R.id.etat);

                                    id.setText(_id);
                                    dateReservation.setText(_dateReservation);
                                    datePrevueStockage.setText(_datePrevueStockage);
                                    nbJoursDeStockagePrevu.setText(_nbJoursDeStockagePrevu);
                                    quantite.setText(_quantite);
                                    etat.setText(_etat);
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
                //params.put("login", _login);
                params.put("login", "jcoc");
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


