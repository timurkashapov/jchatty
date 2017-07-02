package ru.geekbrains.chat.server.core;

/**
 * СЕРВЕР.
 * Базовая реализация чат-сервера для работы c клиентами чата.
 *
 * SERVER.
 * Basic server realisation for the chat working.
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
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
