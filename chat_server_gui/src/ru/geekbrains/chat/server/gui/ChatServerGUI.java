package ru.geekbrains.chat.server.gui;

//
import ru.geekbrains.network.NetworkConstants;

//
import ru.geekbrains.chat.server.new_core.ChatServer;
import ru.geekbrains.chat.server.new_core.ChatServerListener;

//
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

// GUI компоненты.
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

// Компоновка и расположение gui компонентов
import java.awt.GridLayout;
import java.awt.BorderLayout;

// Обработка событий
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JChatty project.
 *
 *
 *
 *
 *
 * ГРАФИЧЕСКИЙ ИНТЕРФЕЙС СЕРВЕРА.
 * GRAPHIC USER INTERFACE OF SERVER.
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
 */
public class ChatServerGUI extends JFrame implements ActionListener, ChatServerListener, Thread.UncaughtExceptionHandler {

    /**
     * X и Y коорддинаты окна.
     * Window X and Y coordinates.
     */
    private final int
            X_WINDOW_POS = 800,
            Y_WINDOW_POS = 200;

    /**
     * Ширина и высота окна.
     * Window width and height.
     */
    private final int
            WIDTH = 600,
            HEIGHT = 200;

    /**
     * Заголовок окна.
     * Title of window.
     */
    private final String TITLE = "jchatty server";

    /**
     * Панели для компонентов.
     * Panels for components.
     */
    private final JPanel
            northPnl = new JPanel(new GridLayout(1, 3)),
            centerPnl = new JPanel();

    /**
     * Заголовки кнопок управления.
     * Title of button controls.
     */
    /* CONTROLS TITLES */
    private final String
            START_LISTENING = "Start listening",
            STOP_LISTENING = "Stop listening",
            DROP_ALL_CONNECTIONS = "Drop all connections";

    /**
     * Кнопки управления.
     * Controls button.
     */
    private final JButton
            btn_Start = new JButton(START_LISTENING),
            btn_Stop = new JButton(STOP_LISTENING),
            btn_DropAll = new JButton(DROP_ALL_CONNECTIONS);

    /**
     * Отображение текстовых служебных сообщений сервера.
     * Viewer the server txtarea_Log text messages.
     */
    private final JTextArea txtarea_Log = new JTextArea();

    /**
     * Сервер чата.
     * Server of chat.
     */
    private final ChatServer server = new ChatServer(this);

    /** */
    private final int PORT = NetworkConstants.PORT;

    /** */
    private ChatServerGUI() {

        /*
        Заменяем стандартный обработчик исключений текущим классом
        для улавливания "непойманных исключений" вручную.
         */
        Thread.setDefaultUncaughtExceptionHandler(this);


        // По закрытию окна - завершаем приложение.
        // --------------------------------------------------------
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Инициализация окна.
        // --------------------------------------------------------
        setBounds(X_WINDOW_POS, Y_WINDOW_POS, WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle(TITLE);
        setAlwaysOnTop(false);
        setResizable(false);
        // --------------------------------------------------------

        // Добавление контента и обработка событий
        // --------------------------------------------------------
        btn_Start.setText(START_LISTENING);
        btn_Stop.setText(STOP_LISTENING);
        btn_DropAll.setText(DROP_ALL_CONNECTIONS);

        // Слушатели событий для кнопок.
        btn_Start.addActionListener(this);
        btn_Stop.addActionListener(this);
        btn_DropAll.addActionListener(this);

        northPnl.add(btn_Start);
        northPnl.add(btn_Stop);
        northPnl.add(btn_DropAll);


        txtarea_Log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(txtarea_Log);

        add(scrollLog, BorderLayout.CENTER);
        add(northPnl, BorderLayout.NORTH);
        // --------------------------------------------------------

        setVisible(true);

        // Обработка "скользких исключений" через текущий класс.
        // throw new RuntimeException(" * Ой * ");

    } // ChatServerGUI()

    // TEST /////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * ТЕСТИРОВАНИЕ.
     */
    public static void main(String[] args) {

        // Потокобезопасность +
        //
        SwingUtilities.invokeLater( new Runnable() {

            @Override
            public void run() {
                new ChatServerGUI();
            }
        } );
    } // main()
    // TEST /////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Вызывается когда совершено действие.
     * Invoked when an action occurs.
     *
     * @param e event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object srcEvent = e.getSource();

        if(srcEvent == btn_Start) {

            server.startListening(PORT);

            // Тренировка обработки "скользких исключений" через текущий класс.
            // throw new RuntimeException(" * Ой * ");

        } else if(srcEvent == btn_Stop) {

            server.stopListening();
        } else if(srcEvent == btn_DropAll) {

            server.dropAllClients();
        } else {

            // Надежность +
            //
            JOptionPane.showMessageDialog(this, srcEvent, "Unknown event" + srcEvent, JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Unknown source event = " + srcEvent);
        }

    } // actionPerformed()

    /**
     *
     * @param server текущий сервер.
     * @param msg сообщение от сервера.
     */
    @Override
    public void onChatServerLog(ChatServer server, String msg) {

        // Потокобезопасность +
        //
        SwingUtilities.invokeLater( new Runnable() {

            @Override
            public void run() {

                // +
                // Append txtarea_Log message.
                txtarea_Log.append(msg + "\n");

                // Auto scroll to end of the text area.
                txtarea_Log.setCaretPosition(txtarea_Log.getDocument().getLength());
            }
        });
    } // onChatServerLog

    /**
     * ОБРАБОТКА "СКОЛЬЗСКИХ ИСКЛЮЧЕНИЙ".
     *
     * PROCESSING THE UNCAUGHT EXCEPTIONS.
     * Method invoked when the given thread terminates due to the
     * given uncaught exception.
     * <p>Any exception thrown by this method will be ignored by the
     * Java Virtual Machine.
     *
     * @param t the thread
     * @param e the exception
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        // Лонируем в консоль и в gui.
        System.out.println("Поймали");
        txtarea_Log.append("Поймали " + e + " ");

        // Логируем стек исключений в консоль.
        e.printStackTrace();

        // Создаем массив для доступа к элементам стека исключений.
        StackTraceElement[] stackTraceElements = e.getStackTrace();

        // Хранение сообщения для отладки / тестирования.
        String msg;

        // Формирование сообщения.
        // 1. Если стек исключений пуст
        // 2. В остальных случаях формируем сообщение по шаблону.
        if(stackTraceElements.length == 0) {

            msg = "Пустой stackTraceElements";
        } else {

            msg = e.getClass().getCanonicalName() + ": " + e.getMessage() + "\n" + stackTraceElements[0];
        }

        // Выводим графическое окно для предупреждения об ишибке.
        JOptionPane.showMessageDialog(null, msg, "Exception!", JOptionPane.ERROR_MESSAGE);

        // Аварийное завершение приложения с кодом ошибки (1).
        System.exit(1);

    } // uncaughtException

} // ChatServerGUI
