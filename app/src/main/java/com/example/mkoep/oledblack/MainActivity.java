package com.example.mkoep.oledblack;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Switch switchButton, switchButton2;
    Intent svc;

    EditText red;
    EditText green;
    EditText blue;
    Button button;


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

    public void setColor(){
        int i = Integer.parseInt(red.getText().toString());
        int j = Integer.parseInt(green.getText().toString());
        int k = Integer.parseInt(blue.getText().toString());
        button.setBackgroundColor(Color.rgb(i, j, k));
        OverlayShowingService.col = Color.rgb(i, j, k);
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

