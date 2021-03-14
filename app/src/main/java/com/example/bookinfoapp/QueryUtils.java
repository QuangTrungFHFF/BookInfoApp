package com.example.bookinfoapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }

    /**
     * Create Url from query string
     * @param stringUrl
     * @return
     */
    public static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG,"Error while creating Url!");
        }
        return url;
    }

    /**
     * Make http request to get JSON String from server
     * @param url
     * @return
     */
    public static String makeHttpRequest(URL url){
        String jsonResponse = "";

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            }


        } catch (IOException e) {
            Log.e(LOG_TAG,"Error while meke http request!");
        }

        return jsonResponse;

    }

    /**
     * Read input stream and return JSON string
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bf = new BufferedReader(inputStreamReader);
            String line = bf.readLine();
            while(line!=null){
                output.append(line);
                line = bf.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Book> getBookFromServer(String mUrl){
        String jsonString ="";
        URL url = createUrl(mUrl);
        jsonString = makeHttpRequest(url);

        if(jsonString == null){
            return null;
        }

        ArrayList<Book> bookList = new ArrayList<Book>();

        try {
            JSONObject root = new JSONObject(jsonString);
            JSONArray items = root.getJSONArray("items");
            for(int i =0; i<items.length();i++){
                JSONObject bookItem = items.optJSONObject(i);
                JSONObject volumeInfo = bookItem.getJSONObject("volumeInfo");
                // Get cover image//
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String coverResourceId = imageLinks.getString("smallThumbnail");
                coverResourceId = getImageResourceId(coverResourceId);
                // Get title//
                String title = volumeInfo.getString("title");
                // Get all authors//
                ArrayList<String> authors = new ArrayList<String>();
                JSONArray authorList = volumeInfo.getJSONArray("authors");
                for(int j =0; j<authorList.length();j++){
                    authors.add(authorList.getString(j));
                }

                // Create book and add to list//
                Book book = new Book(title,authors,coverResourceId);
                bookList.add(book);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG,"Error while make Json object!");
        }

        return bookList;

    }

    private static String getImageResourceId(String mUri){
        String result = mUri.replaceFirst("http","https");
        return result;
    }

}
