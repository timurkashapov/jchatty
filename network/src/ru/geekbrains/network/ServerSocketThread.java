package ru.geekbrains.network;

import java.net.Socket;
import java.net.ServerSocket;

import java.io.IOException;

/**
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
 */
public class ServerSocketThread extends Thread {

    private final int port;

    /** */
    public ServerSocketThread(String name, int port){

        super(name);
        this.port = port;
        start();
    } // ServerSocketThread()

    /** */
    @Override
    public void run() {

        System.out.printf("ServerSocketThread запущен");

        try(ServerSocket srvSkt = new ServerSocket(port);
            Socket skt = srvSkt.accept()
        ){

            if(skt.isConnected()) {

                while( ! isInterrupted() ) {
                    System.out.printf("сервер работает");
                }
            }

        } catch(IOException e) {
                    throw new RuntimeException();
        }

        System.out.printf("ServerSocketThread остановлен");
    } // run()
} // ServerSocketThread
