package ru.geekbrains.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 *
 *
 *
 * Created by shako on 29-Jun-17.
 */
public class SimpleServer {

    /** */
    private SimpleServer() {

    }

    /** */
    public static void main(String[] args) {

        //
        // -------------------------------------------------------------------------------
        try( ServerSocket serverSocket = new ServerSocket(8189);
             Socket socket = serverSocket.accept()) {



            DataInputStream in   = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while(true) {

                out.writeUTF(in.readUTF());

            } // while


        } catch ( IOException e) {
            e.printStackTrace();
        }
        // -------------------------------------------------------------------------------
    } // main()
} // SimpleServer
