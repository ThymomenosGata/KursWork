package org.wordy.kurswork;

import android.app.Application;

public class NetworkUtil extends Application {

    private static NetworkUtil mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized NetworkUtil getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkChangeReceiver.ConnectivityReceiverListener listener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener;
    }

}
