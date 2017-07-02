package ru.geekbrains.network;

/**
 * Created by shako on 29-Jun-17.
 */
public interface ServerSocketThreadListener {




    void onStartServerSocketThread();
    void onStopServerSocketThread();

    void onServerSocketThreadCreate();
    void onTimeoutAccept();
    void onAcceptSocket();


}
