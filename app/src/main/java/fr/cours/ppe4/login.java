package fr.cours.ppe4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.filament.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private TextView connexion_login, connexion_password;
    private Button button_action_connexion;

    private CookieManager CookieManagercookieManager;
    private CookieHandler cookieManager;
    private StringRequest StringRequestsr;
    private RequestQueue requestQueue;
    private String url = "http://192.168.1.42/zonestockage/login.php";
    //private String url = "http://172.16.252.5/zonestockage/login.php";
    private String _password, _login;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.connexion);


            CookieManagercookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);

            requestQueue = Volley.newRequestQueue(login.this);

            this.button_action_connexion=(Button)findViewById(R.id.button_action_connexion);

            button_action_connexion.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    authentification(view);
                }
            });

        }



    protected void authentification(android.view.View view) {

        this.connexion_login=(TextView)findViewById(R.id.connexion_login);
        this.connexion_password=(TextView)findViewById(R.id.connexion_password);
        this.button_action_connexion=(Button) findViewById(R.id.button_action_connexion);

         _login = connexion_login.getText().toString();
        _password = connexion_password.getText().toString();

        StringRequestsr = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        String reponse=response;

                        if(reponse.equals("Authentification")){
                            Intent myIntent = new Intent(login.this, MenuUtilisateur.class);
                            startActivity(myIntent);
                        }else if(reponse.equals("Erreur Insertion !")){
                            Intent myIntent = new Intent(login.this, login.class);
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
                params.put("password", _password);
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
/*String _nbEmplacement = nbEmplacement.getText().toString();
                String _dateDebStockage = dateDebStockage.getText().toString();
                String _duréeJ = duréeJ.getText().toString();

                Map<String, String> params = new HashMap<String, String>();
                params.put("nbEmplacement", _nbEmplacement);
                params.put("_dateDebStockage", _dateDebStockage);
                params.put("duréeJ", _duréeJ);
                return params;*/