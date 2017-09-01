package com.example.mkoep.oledblack;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Switch switchButton;
    Intent svc;

    EditText red;
    EditText green;
    EditText blue;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        OverlayShowingService.col = 0xff000000;
        OverlayShowingService.pf = getWindowManager().getDefaultDisplay().getPixelFormat();

        switchButton = (Switch) findViewById(R.id.switch1);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    startOverlay();
                } else {
                    stopOverlay();
                }
            }
        });

        button = (Button) findViewById(R.id.button);
        red = (EditText) findViewById(R.id.editText4);
        green = (EditText) findViewById(R.id.editText5);
        blue = (EditText) findViewById(R.id.editText6);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                setColor(view);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(view);
                Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button.
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            //TODO Start new activity for separated settings.

            return true;
        }

        //Handle ResetClick.
        if (id == R.id.action_reset) {
            OverlayShowingService.col = 0xff000000;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Get RGB Color from User input and set it as Overlay Color (OverlayShowingService.col)
    public void setColor(View view){
        try {
            int i, j, k;

            //If UserInput is null set it to 0
            try {
                i = Integer.parseInt(red.getText().toString());
            }
            catch (Exception e){
                i=0;
                red.setText("0");
            }
            try {
                j = Integer.parseInt(green.getText().toString());
            }
            catch (Exception e){
                j=0;
                green.setText("0");
            }
            try {
                k = Integer.parseInt(blue.getText().toString());
            }
            catch (Exception e){
                k=0;
                blue.setText("0");
            }

            int rgb ;

            //Test if User input is legit
            if(i<=255 && j <= 255 && k<=255) {
                try {
                    rgb = Color.rgb(i, j, k);
                    button.setBackgroundColor(rgb);
                    OverlayShowingService.col = rgb;
                } catch (Exception e) {
                    CharSequence text = "Not RGB either!";
                    /*
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                    */
                    Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
            //User input is not a RGB value
            else {
                throw new Exception();
            }
        }
        //Handle thrown exception
        catch (Exception e){

            CharSequence text = "Not RGB!";
            /*
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
            */
            Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }


    }

    //Starting OverlayService
    public void startOverlay(){
        //Test if Service is already running
        if(!isIntentRunning()) {
            svc = new Intent(this, OverlayShowingService.class);
            svc.putExtra("color", 0xff0000ff);
            startService(svc);
        }
    }
    public void stopOverlay(){
        //TODO Possible Bug
        //Test if Service is still running before trying to close it.
        try {
            if(isIntentRunning()) {
                stopService(svc);
            }
            else {

            }
        }
        catch (Exception e){

        }
    }

    private boolean isIntentRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.example.mkoep.oledblack.OverlayShowingService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}

