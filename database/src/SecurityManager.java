/**
 * JChatty project.
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
 */
interface SecurityManager {

    /**
     * Существует такой ник или нет?
     */
    void getNick(String login, String password);

} // SecurityManager
