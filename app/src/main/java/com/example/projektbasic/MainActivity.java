package com.example.projektbasic;


import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public String array1;
    public String array2;
    public static String INFO =" ";
    public static String FACT=" ";
    private ArrayList<String> listData;
    private ArrayAdapter<MyClass> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new FetchData().execute();

        adapter = new ArrayAdapter<MyClass>(this,R.layout.list_item_textview,R.id.list_item_textview);
        final ListView my_listview=(ListView)findViewById(R.id.my_listview);
        my_listview.setAdapter(adapter);

        my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String info = adapter.getItem(position).info();
                String fact = adapter.getItem(position).fact();
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(INFO, info);
                intent.putExtra(FACT, fact);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){ //Skapar funktionalitet till menyknappen
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        if (id == R.id.action_settings){

            String info = "F1 2019 Drivers List is an application for people interested in knowing simple facts about the drivers of the F1 2019 World Championship.";
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra(INFO, info);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class FetchData extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/a18wilis/JSON-filer/drivers.json?type=brom");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }
        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            Log.d("brom","DataFetched:"+o);
            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.
            try {

                JSONArray drivers= new JSONArray(o);
                for(int i=0; i < drivers.length(); i++) {
                    Log.d("brom", "element 0:" + drivers.get(i).toString());
                    JSONObject driverInfo= drivers.getJSONObject(i);
                    Log.d("brom",  driverInfo.getString("name"));
                    Log.d("brom",  driverInfo.getString("location"));
                    Log.d("brom",  driverInfo.getString("cost"));
                    Log.d("brom",  driverInfo.getString("auxdata"));

                    MyClass myClass = new MyClass(driverInfo.getString("name"),driverInfo.getString("location"), driverInfo.getString("cost"), driverInfo.getString("auxdata")); //Lägger in förardata i MyClass.
                    Log.d("brom",  myClass.toString());
                    adapter.add(myClass); //Det nya berget läggs till i adaptern
                }
            } catch (JSONException e) {
                Log.e("brom","E:"+e.getMessage());
            }

            // Implement a parsing code that loops through the entire JSON and creates objects
            // of our newly created Mountain class.
        }
    }
}
