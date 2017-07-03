package ru.geekbrains.chat.server.old_core;

import ru.geekbrains.network.ServerSocketThread;
import ru.geekbrains.network.ServerSocketThreadListener;
import ru.geekbrains.network.SocketThread;
import ru.geekbrains.network.SocketThreadListener;

import java.net.Socket;

import java.util.Vector;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
 */
public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {

    //
    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss: ");

    //
    private final ChatServerListener eventListener;

    //
    private final SecurityManager securityManager;

    //
    private ServerSocketThread serverSocketThread;

    //
    private final Vector<SocketThread> clients = new Vector<>();

    /** */
    public ChatServer(ChatServerListener eventListener, SecurityManager securityManager) {
        this.eventListener = eventListener;
        this.securityManager = securityManager;
    } // ChatServer()

    /** */
    public void startListening(int port) {

        if(serverSocketThread != null && serverSocketThread.isAlive()) {

            // putLog("Сервер уже запущен.");

            return;
        }
        serverSocketThread = new ServerSocketThread("ServerSocketThread", port);

        /*
        ???
         */
        // securityManager.init();

    } // startListening()

    /** */
    public void dropAllClients() {

        // putLog("dropAllClients");

    } // dropAllClients()

    /** */
    public void stopListening() {
        if(serverSocketThread == null || !serverSocketThread.isAlive()) {

            // putLog("Сервер не запущен.");

            return;
        }
        serverSocketThread.interrupt();

        /*
        ???
         */
        // securityManager.dispose();

    } // stopListening()

     /*

    N E W

     */
    // ServerSocketThread =================================================================================

    /** */
    @Override
    public void onStartServerSocketThread() {

    }

    /** */
    @Override
    public void onStopServerSocketThread() {

    }

    /** */
    @Override
    public void onServerSocketThreadCreate() {

    }

    /** */
    @Override
    public void onTimeoutAccept() {

    }

    /** */
    @Override
    public void onAcceptSocket() {

    }


    /*

    O L D

     */
    // ServerSocketThread =================================================================================

//    /** */
//    public void onStartServerSocketThread(ServerSocketThread thread) {
//        putLog("started...");
//    }
//
//    /** */
//    public void onStopServerSocketThread(ServerSocketThread thread) {
//        putLog("stopped.");
//    }
//
//    /** */
//    public void onReadyServerSocketThread(ServerSocketThread thread, ServerSocket serverSocket) {
//        putLog("ServerSocket is ready...");
//    }
//
//    /** */
//    public void onTimeOutAccept(ServerSocketThread thread, ServerSocket serverSocket) {
////        putLog("accept() timeout");
//    }
//
//    /** */
//    public void onAcceptedSocket(ServerSocketThread thread, ServerSocket serverSocket, Socket socket) {
//        putLog("Client connected: " + socket);
//        String threadName = "Socket thread: " + socket.getInetAddress() + ":" + socket.getPort();
//        new SocketThread(this, threadName, socket);
//    }
//
//    /** */
//    public void onExceptionServerSocketThread(ServerSocketThread thread, Exception e) {
//        putLog("Exception: " + e.getClass().getName() + ": " + e.getMessage());
//    }
//
//    /** */
//    private synchronized void putLog(String msg) {
//        String msgLog = dateFormat.format(System.currentTimeMillis()) +
//                Thread.currentThread().getName() + ": " + msg;
//        eventListener.onChatServerLog(this, msgLog);
//    }


    /*

    O L D

     */

    // SocketThread =================================================================================

    /** */
    @Override
    public synchronized void onStartSocketThread(SocketThread socketThread) {

        // putLog("started...");

    }

    /** */
    @Override
    public synchronized void onStopSocketThread(SocketThread socketThread) {

        clients.remove(socketThread);

    }

    /** */
    @Override
    public synchronized void onReadySocketThread(SocketThread socketThread, Socket socket) {

        // putLog("Socket is ready.");

        clients.add(socketThread);
    }

    /** */
    @Override
    public synchronized void onReceiveString(SocketThread socketThread, Socket socket, String value) {

        for (int i = 0; i < clients.size(); i++) {
            // clients.get(i).sendMsg(value);
        }
    }

    /** */
    @Override
    public synchronized void onExceptionSocketThread(SocketThread socketThread, Socket socket, Exception e) {

        // putLog("Exception: " + e.getClass().getName() + ": " + e.getMessage());

    }

} // ChatServer
