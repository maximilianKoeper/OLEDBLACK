package com.example.mkoep.oledblack;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;

import static android.view.WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF;


public class OverlayShowingService extends Service implements OnClickListener {

    private View  Overlay;
    private WindowManager wm;

    public static int col;
    public static int pf;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        Overlay = new View(this);
        Overlay.setBackgroundColor(col);
        Overlay.setOnClickListener(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_DIM_BEHIND| WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION | WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES , pf);
        params.screenBrightness = BRIGHTNESS_OVERRIDE_OFF;
        try {
            wm.addView(Overlay, params);
        }

        catch (Exception e){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Show alert dialog to the user saying a separate permission is needed
            // Launch the settings activity if the user prefers
            Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            startActivity(myIntent);
        }
    }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Overlay != null) {
            wm.removeView(Overlay);
            Overlay = null;
        }

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Overlay disabled", Toast.LENGTH_SHORT).show();
        if (Overlay != null) {
            wm.removeView(Overlay);
            Overlay = null;
        }
    }

}