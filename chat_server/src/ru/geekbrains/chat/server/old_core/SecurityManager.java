package ru.geekbrains.chat.server.core;

public interface SecurityManager {

    void init();
    String getNick(String login, String password);
    void dispose();
}