package ru.geekbrains.network;

/**
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
 */
public interface ServerSocketThreadListener {

    /** */
    void onStartServerSocketThread();

    /** */
    void onStopServerSocketThread();

    /** */
    void onServerSocketThreadCreate();

    /** */
    void onTimeoutAccept();

    /** */
    void onAcceptSocket();

} // ServerSocketThreadListener
