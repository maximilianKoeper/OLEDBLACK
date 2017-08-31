package com.example.mkoep.oledblack;

import android.content.Intent;
import android.service.quicksettings.TileService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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
        Intent svc = new Intent(this, OverlayShowingService.class);
        startService(svc);

        //Start main activity
        //startActivity(new Intent(this, MainActivity.class));
    }
}