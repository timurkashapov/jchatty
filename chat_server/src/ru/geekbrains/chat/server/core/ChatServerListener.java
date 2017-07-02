package ru.geekbrains.chat.server.core;

/**
 * "Слушатель" для обработки событий сервера.
 * Listener for server events.
 *
 * Created by shako on 28-Jun-17.
 */
public interface ChatServerListener {

    // ???
    void onChatServerLog(ChatServer server, String msg);

} // ChatServerListener
