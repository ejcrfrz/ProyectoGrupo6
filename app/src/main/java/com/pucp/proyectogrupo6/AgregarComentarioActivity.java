package com.pucp.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class AgregarComentarioActivity extends AppCompatActivity {


    String idAccidente = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_comentario);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idAccidente = extras.getString("idAccidente");

        }

    }


    public void guardar(View view){

        TextView coment = findViewById(R.id.textComentario);
         String comentario = coment.getText().toString();
        //------------------------------------------------------------
        JSONObject jsonBodyObj = new JSONObject();
        try{
            jsonBodyObj.put("idaccidentes", idAccidente);
            jsonBodyObj.put("estado", "Atendido");
            jsonBodyObj.put("comentario", comentario);

        }catch (JSONException e){
            e.printStackTrace();
        }
        final String requestBody = jsonBodyObj.toString();
        String url = "http://192.168.1.11:3000/";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("exitoVolley1", response);

                        // Convertir los datos en formato json (response) a una clase en Java con Gson
                        // Gson gson = new Gson();
                        //Status asd = gson.fromJson(response, Status.class);
                        //Log.d("le", asd.getStatus());


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorVolley","");
                    }
                }
        ) {
            // Sobreescribir getParams() --> Enviar parámetro de body con POST

            // Sobreescribir getHeaders()

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }



            @Override    public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


        //------------------------------------------------------------
        Intent intent2 = new Intent(AgregarComentarioActivity.this,ListaPersonalActivity.class);
        setResult(RESULT_OK,intent2);
        finish();



    }

}
