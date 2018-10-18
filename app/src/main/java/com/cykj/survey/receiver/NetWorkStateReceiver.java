package com.cykj.survey.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.cykj.survey.Constants;

public class NetWorkStateReceiver extends BroadcastReceiver {

    private static final String netACTION="android.net.conn.CONNECTIVITY_CHANGE";
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(netACTION)){
            if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false)){
                Constants.NETWORK_FLAG = true;
            }else {
                Constants.NETWORK_FLAG = false;
            }
        }

    }

}
