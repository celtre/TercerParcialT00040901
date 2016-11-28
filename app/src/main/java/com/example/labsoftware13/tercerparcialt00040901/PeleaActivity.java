package com.example.labsoftware13.tercerparcialt00040901;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Random;


public class PeleaActivity extends AppCompatActivity {

    public String urlPokemon = "http://pokeapi.co/api/v2/pokemon/";
    public String urlImgPokemon1;
    public String urlImgPokemon2;
    AsyncHttpClient client = new AsyncHttpClient();

    public static String STORE_IN_FOLDER = android.os.Environment.getExternalStorageDirectory().toString() + "/Android/data/com.example.labsoftware13.tercerparcialt00040901";

    TextView txtNamePokemon1;
    TextView txtNamePokemon2;
    TextView txtVidaPokemon1;
    TextView txtVidaPokemon2;
    ImageView imgPokemon1;
    ImageView imgPokemon2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelea);
        txtNamePokemon1 = (TextView) findViewById(R.id.txtNamePokemon1);
        txtNamePokemon2 = (TextView) findViewById(R.id.txtNamePokemon2);
        txtVidaPokemon1 = (TextView) findViewById(R.id.txtVidaPokemon1);
        txtVidaPokemon2 = (TextView) findViewById(R.id.txtVidaPokemon2);
        imgPokemon1 = (ImageView) findViewById(R.id.imgPokemon1);
        imgPokemon2 = (ImageView) findViewById(R.id.imgPokemon2);
        Random rand = new Random();
        int n = rand.nextInt(700);
        int m = rand.nextInt(700);
        SimpleJSON1(n);
        SimpleJSON2(m);


    }

    public void siguienteTurno(View view){

        Random rand = new Random();
        int n = rand.nextInt(101);
        int m = rand.nextInt(101);

        txtVidaPokemon1.setText(String.valueOf(Integer.valueOf(txtVidaPokemon1.getText().toString()) - m ));
        txtVidaPokemon2.setText(String.valueOf(Integer.valueOf(txtVidaPokemon2.getText().toString()) - n ));
        if (Integer.valueOf(txtVidaPokemon1.getText().toString()) <= 0){
            Intent intent = new Intent(this, VictoriaActivity.class);
            startActivity(intent);
        }
        if (Integer.valueOf(txtVidaPokemon2.getText().toString()) <= 0){
            Intent intent = new Intent(this, DerrotaActivity.class);
            startActivity(intent);

        }

    }

    private void SimpleJSON1(int n) {
        /*
         * Se hace el request y se usa como argumento una subclase (anónima? Cual sería el término
         * correcto en este caso?) de JsonHttpResponseHandler, sobreescribiendo sólo el método de
         * request exitoso (onSuccess)
         */
        client.get(urlPokemon+ String.valueOf(n)+"/", null, new JsonHttpResponseHandler() {
            /**
             * Handler de evento "request exitoso."
             *
             * @param statusCode Código HTTP de la respuesta del servidor.
             * @param headers Headers HTTP de respuesta del servidor.
             * @param response Objeto JSON recibido.
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    // Mostrando la respuesta en LogCat y en campoLog, en la Activity.
                    //Log.v("Respuesta JSON:", response.getJSONObject("forms").getString("name").toString())
                    txtNamePokemon1.setText(response.getJSONArray("forms").getJSONObject(0).getString("name").toString());
                    txtVidaPokemon1.setText("100");
                    urlImgPokemon1 = response.getJSONObject("sprites").getString("front_shiny");
                    Glide.with(PeleaActivity.this).load(urlImgPokemon1).into(imgPokemon1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            /**
             * Handler de evento "request exitoso.
             *
             * @param statusCode Código HTTP de la respuesta del servidor.
             * @param headers Headers HTTP de respuesta del servidor.
             * @param response Objeto JSON recibido.
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.v("Respuesta JSON:", response.toString());
            }
        });
    }
    private void SimpleJSON2(int n){
        /*
         * Se hace el request y se usa como argumento una subclase (anónima? Cual sería el término
         * correcto en este caso?) de JsonHttpResponseHandler, sobreescribiendo sólo el método de
         * request exitoso (onSuccess)
         */
        client.get(urlPokemon+ String.valueOf(n)+"/", null, new JsonHttpResponseHandler() {
            /**
             * Handler de evento "request exitoso."
             *
             * @param statusCode Código HTTP de la respuesta del servidor.
             * @param headers Headers HTTP de respuesta del servidor.
             * @param response Objeto JSON recibido.
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    // Mostrando la respuesta en LogCat y en campoLog, en la Activity.
                    //Log.v("Respuesta JSON:", response.getJSONObject("forms").getString("name").toString())
                    txtNamePokemon2.setText(response.getJSONArray("forms").getJSONObject(0).getString("name").toString());
                    txtVidaPokemon2.setText("100");
                    urlImgPokemon2 = response.getJSONObject("sprites").getString("front_shiny");
                    Glide.with(PeleaActivity.this).load(urlImgPokemon2).into(imgPokemon2);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            /**
             * Handler de evento "request exitoso.
             *
             * @param statusCode Código HTTP de la respuesta del servidor.
             * @param headers Headers HTTP de respuesta del servidor.
             * @param response Objeto JSON recibido.
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.v("Respuesta JSON:", response.toString());
            }
        });
    }

}
