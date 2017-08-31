package com.example.mkoep.oledblack;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import android.app.Activity;

public class OverlayShowingService extends Service implements OnClickListener {

    private Button overlayedButton;
    private WindowManager wm;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        overlayedButton = new Button(this);
        overlayedButton.setBackgroundColor(0x000000);
        overlayedButton.setOnClickListener(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_DIM_BEHIND| WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION | WindowManager.LayoutParams.	FLAG_IGNORE_CHEEK_PRESSES, PixelFormat.RGB_888);
        wm.addView(overlayedButton, params);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayedButton != null) {
            wm.removeView(overlayedButton);
            overlayedButton = null;
        }
       return;

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Overlay disabled", Toast.LENGTH_SHORT).show();
        if (overlayedButton != null) {
            wm.removeView(overlayedButton);
            overlayedButton = null;
        }
        //android.os.Process.killProcess(android.os.Process.myPid());
        //System.runFinalizersOnExit(true);
        //System.exit(0);
        return;
    }

}