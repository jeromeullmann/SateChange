package com.example.juju.satechange;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.ConnectivityManager;
import android.widget.Toast;




public class StateChangeActivity extends ActionBarActivity {

    private static final String TAG = "com.exemple.StateChange";

    private ConnectivityManager connec;

    // GPSTracker class
    GPSTracker gps;

    Button btnShowLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String gpsLat = null;
        String gpsLong = null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_change);
        Log.i(TAG,"onCreate");

        // get Connectivity Manager object to check connection
        final ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // get Connectivity Manager object to check gps

        gps = new GPSTracker(StateChangeActivity.this);
        Log.i(TAG,"GPSTracker");

        // check if GPS enabled
        if(gps.canGetLocation()){
            Log.i(TAG,"canGetLocation");
            double latitude = gps.getLatitude();
            gpsLat = String.valueOf(latitude);

            double longitude = gps.getLongitude();
            gpsLong = String.valueOf(longitude);

            Log.i(TAG,gpsLat);
            Log.i(TAG,gpsLong);

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

            TextView myTextViewGpsLat = (TextView)findViewById(R.id.myTextPositionGPSLat); myTextViewGpsLat.setText(gpsLat);
            TextView myTextViewGpsLong = (TextView)findViewById(R.id.myTextPositionGPSLong); myTextViewGpsLong.setText(gpsLong);

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            Log.i(TAG,"NOT canGetLocation");
            gps.showSettingsAlert();
        }

        // actualisation de la position GPS si btn GPS

        Button buttonOnGPS = (Button)findViewById(R.id.button_gps);
        buttonOnGPS.setOnClickListener( new Button.OnClickListener() { public void onClick(View v) {

            TextView myTextView = (TextView)findViewById(R.id.mytextViewCaptureGPS);
            myTextView.setText("GPS capture");

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Toast.makeText(getApplicationContext(), "Click GPS - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            TextView myTextViewGpsLat = (TextView)findViewById(R.id.myTextPositionGPSLat); myTextViewGpsLat.setText(String.valueOf(latitude));
            TextView myTextViewGpsLong = (TextView)findViewById(R.id.myTextPositionGPSLong); myTextViewGpsLong.setText(String.valueOf(longitude));

            myTextView.setText("");

        }});


        // get Sqlite Manager object to check base

        Button buttonOnWifi = (Button)findViewById(R.id.button_on_wifi);
        buttonOnWifi.setOnClickListener( new Button.OnClickListener() { public void onClick(View v) {
            TextView myTextView = (TextView)findViewById(R.id.myTextViewStatusWifi);
            myTextView.setText("WIFI ON");
            setWifiOn(connec);
        }});

        Button buttonOffWifi = (Button)findViewById(R.id.button_off_wifi);
        buttonOffWifi.setOnClickListener( new Button.OnClickListener() { public void onClick(View v) {
            TextView myTextView = (TextView)findViewById(R.id.myTextViewStatusWifi);
            myTextView.setText("WIFI OFF");
            setWifiOff(connec);
        }});

        }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceSate");
        final EditText textBox = (EditText) findViewById(R.id.editTextLocalisation);
        CharSequence userText = textBox.getText();
        outState.putCharSequence("savedText",userText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Log.i(TAG,"onRestoreInstanceSate");
        final EditText textBox =(EditText) findViewById(R.id.editTextLocalisation);
        CharSequence userText = saveInstanceState.getCharSequence("savedText");
        textBox.setText(userText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_state_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void setWifiOn (ConnectivityManager connec){

    }

    public void setWifiOff(ConnectivityManager connec){

    }

}
