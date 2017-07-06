package ru.geekbrains.network;

import java.net.Socket;

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
    public SocketThread(String str, Socket skt){

        // TO-DO

    } // SocketThread(String str, Socket skt)

    /** */
    @Override
    public void run() {
        System.out.printf("SocketThread запущен");
        // TO-DO

    } // run()
} // SocketThread
