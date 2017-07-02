package ru.geekbrains.chat.server.core;

/**
 * СЕРВЕР.
 * Базовая реализация сервера для работы чата.
 *
 * SERVER.
 * Basic server realisation for the chat working.
 *
 * Created by shako on 28-Jun-17.
 */
public class ChatServer {

    /** */
    private final ChatServerListener eventListener;

    /**
     * Сконструировать сервер с экземпляром слушателя событий.
     * Construct the server with event listener instance.
     * @param eventListener event listener.
     */
    public ChatServer(ChatServerListener eventListener) {

        this.eventListener = eventListener;
    } // ChatServer

    /**
     * Запуск сервера.
     * Start the server.
     *
     * @param port порт для подключения соединения с удалённым хостом.
     */
    public void startListening(int port) {
        putLog("Сервер запущен: " + port);
    } // startListening

    /**
     * Отключится от всех клиентских хостов.
     * Disconnect with all client hosts.
     */
    public void dropAllClients() {
        putLog("Соединение разорвано");
    } // dropAllClients

    /**
     * Остановить работу сервера.
     * Stop server.
     */
    public void stopListening() {
        putLog("Сервер остановлен");
    } // stopListening

//    /**
//     * Установка слушателя событий сервера.
//     * Set the "listener" for server events.
//     *
//     * @param eventListener listener.
//     */
//    public void setEventListener(ChatServerListener eventListener) {
//        this.eventListener = eventListener;
//    } // setServerListener

    /**
     * Журналирование событий сервера.
     * Logging of server events.
     *
     * @param msg event message.
     */
    private void putLog(String msg) {

        if (eventListener != null ) {
            eventListener.onChatServerLog(this, msg);
        }
    } // putLog

} // ChatServer
