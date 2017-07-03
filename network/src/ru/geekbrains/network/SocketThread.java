package ru.geekbrains.network;

/**
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
 */
public class SocketThread extends Thread {

    /** */
    public SocketThread(){

        start();
    } // SocketThread()

    /** */
    @Override
    public void run() {
        System.out.printf("SocketThread запущен");
        // TO-DO

    } // run()
} // SocketThread
