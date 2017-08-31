package com.example.mkoep.oledblack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {

    Switch switchButton, switchButton2;
    Intent svc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OverlayShowingService.col = 0xff000000;


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

    }

    public void startOverlay(){
        svc = new Intent(this, OverlayShowingService.class);
        svc.putExtra("color", 0xff0000ff);
        startService(svc);


    }
    public void stopOverlay(){
        stopService(svc);

    }


}

