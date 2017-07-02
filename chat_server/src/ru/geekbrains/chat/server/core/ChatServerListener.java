package ru.geekbrains.chat.server.core;

/**
 * "Слушатель" для обработки событий сервера.
 * Listener for server events.
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
 *
 */
public interface ChatServerListener {

    // ???
    void onChatServerLog(ChatServer server, String msg);

} // ChatServerListener
