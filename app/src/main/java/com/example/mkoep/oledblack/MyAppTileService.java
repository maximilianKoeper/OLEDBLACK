package com.example.mkoep.oledblack;

import android.app.ActivityManager;
import android.content.Intent;
import android.service.quicksettings.TileService;
/**
 * Created by MKoep on 31.08.2017.
 */

public class MyAppTileService extends TileService {
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
    }

    @Override
    public void onClick() {
        super.onClick();
        if(!isIntentRunning()) {
            Intent svc = new Intent(this, OverlayShowingService.class);
            svc.putExtra("color", 0xff0000ff);
            startService(svc);
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