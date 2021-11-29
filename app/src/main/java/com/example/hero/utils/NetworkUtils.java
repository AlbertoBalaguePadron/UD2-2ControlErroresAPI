package com.example.hero.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String APIALLSUPERHERO = "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/all.json";
    final static String APIONESUPERHERO = "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/id/";
    final static String Final = ".json";

    public static URL BuildURLALLHERO(){

            Uri buildUrl = Uri.parse(APIALLSUPERHERO);

        URL url = null;
        try {
            url = new URL(buildUrl.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }



    public static URL BuildURLONEHERO(int NUMERHERO){

        Uri buildUrl = Uri.parse(APIONESUPERHERO);

        URL url = null;
        try {
            url = new URL(buildUrl.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }


    public static String RespuestaURL(URL url) throws IOException{
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        InputStream in = conexion.getInputStream();
        Scanner leer = new Scanner(in);
        leer.useDelimiter("\\A");

        try {
            boolean hasInput = leer.hasNext();
            if (hasInput) {
                return leer.next();
            } else {
                return null;
            }
        } finally {
            conexion.disconnect();
        }

    }





}
