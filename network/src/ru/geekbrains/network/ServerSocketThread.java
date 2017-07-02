package ru.geekbrains.network;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by shako on 29-Jun-17.
 */
public class ServerSocketThread extends Thread {


    /**
     *
     */
    @Override
    public void run() {

        try( ServerSocket serverSocket = new ServerSocket(8189);
        ) {

        } catch(IOException e) {
            e.printStackTrace();
        }



    }
}
