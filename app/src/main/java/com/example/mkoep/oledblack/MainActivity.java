package com.example.mkoep.oledblack;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Color;
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
                setColor();
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
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_reset) {
            OverlayShowingService.col = 0xff000000;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setColor(){
        try {
            int i = Integer.parseInt(red.getText().toString());
            int j = Integer.parseInt(green.getText().toString());
            int k = Integer.parseInt(blue.getText().toString());

            int rgb ;

            if(i<=255 && j <= 255 && k<=255) {
                try {
                    rgb = Color.rgb(i, j, k);
                    button.setBackgroundColor(rgb);
                    OverlayShowingService.col = rgb;
                } catch (Exception e) {
                    CharSequence text = "Not RGB either!";

                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e){
            CharSequence text = "Not RGB!";

            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    public void startOverlay(){
        if(!isIntentRunning()) {
            svc = new Intent(this, OverlayShowingService.class);
            svc.putExtra("color", 0xff0000ff);
            startService(svc);
        }
    }
    public void stopOverlay(){
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

