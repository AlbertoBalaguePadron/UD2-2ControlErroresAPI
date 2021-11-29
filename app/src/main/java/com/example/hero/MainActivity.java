package com.example.hero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hero.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView urlgethero;
    TextView searchResults;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.Launch){
            Log.i("MainActivity", "El usuario ha pulsado Launch");

            URL githubUrl = NetworkUtils.BuildURLALLHERO();
            // Tengo error inesperado al convertir el objeto a String para mostrarlo
            searchResults.setText(githubUrl.toString());
            Context context = MainActivity.this;
            Toast.makeText(context, "Se ha pulsado launch ", Toast.LENGTH_LONG).show();

            URL Superhero = NetworkUtils.BuildURLALLHERO();
            urlgethero.setText(githubUrl.toString());
            searchResults.setText(Superhero.toString());
            new ResultadoBusqueda().execute(Superhero);

        } if (itemId == R.id.clear){
            Log.i("MainActivity", "El usuario ha pulsado Clear");
            Context context = MainActivity.this;
            Toast.makeText(context, "Se ha pulsado clear ", Toast.LENGTH_LONG).show();

            urlgethero.setText("");
            searchResults.setText("");
        }
        return true;
    }


    public class ResultadoBusqueda extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String gitHubSearchResults = null;

            try {
                gitHubSearchResults = NetworkUtils.RespuestaURL(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return gitHubSearchResults;

        }

        @Override
        protected void onPostExecute (String s){
            if (s != null && !s.equals("")){
                searchResults.setText(s);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlgethero = (TextView) findViewById(R.id.urlhero);
        searchResults = (TextView) findViewById(R.id.search_results);
    }

}