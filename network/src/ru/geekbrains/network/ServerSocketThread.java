package ru.geekbrains.network;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
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
