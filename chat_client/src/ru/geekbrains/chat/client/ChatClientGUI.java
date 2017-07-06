package ru.geekbrains.chat.client;

// Зависимость от модуля network.
// * получение константных значений для подключения к сети и чату.
import ru.geekbrains.network.NetworkConstants;

import ru.geekbrains.network.SocketThread;

//
import java.net.Socket;

//
import java.io.IOException;

//
import javax.swing.SwingUtilities;

// GUI компоненты
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

//
import java.awt.GridLayout;
import java.awt.BorderLayout;

//
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

// Обработка событий
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * JChatty project.
 *
 *
 *
 *
 *
 *
 * ГРАФИЧЕСКИЙ ИНТЕРФЕЙС КЛИЕНТА ЧАТА.
 * GRAPHIC USER INTERFACE OF CHAT CLIENT.
 *
 * @author Aleksey Stepchenko.
 * @author Timur Kashapov.
 */
public class ChatClientGUI extends JFrame implements ActionListener, KeyListener, Thread.UncaughtExceptionHandler {

    /**
     * Сервер чата.
     * Server of chat.S
     */
    // private final ChatServer server = new ChatServer(this);

    /**
     * Данные для сетевого подключения.
     * Data for network connection.
     */
    private final String
            IP   = NetworkConstants.IP;

    private final int
            PORT = NetworkConstants.PORT;

    /**
     * Пользовательские данные.
     * User data.
     */
    private final String
            USER_LOGIN = NetworkConstants.USER_LOGIN,
            USER_PASS  = NetworkConstants.USER_PASS,
            USER_NICK  = NetworkConstants.USER_NICK;

    /**
     * X и Y коорддинаты окна.
     * Window X and Y coordinates.
     */
    private final int
            WINDOW_X_POS = 900,
            WINDOW_Y_POS = 400;

    /**
     * Ширина и высота окна.
     * Window width and height.
     */
    private final int
            WINDOW_WIDTH  = 640,
            WINDOW_HEIGHT = 480;

    /**
     * Заголовки компонентов.
     * Title of components.
     */
    // Главное окно
    private final String
            WINDOW_TITLE = "jchatty client";
    // Кнопки
    private final String
            BTN_SEND_MSG_TITLE        = "SEND",
            BTN_LOGIN_TITLE           = "LOGIN",
            BTN_DISCONNECT_TITLE      = "DISCONNECT";
    // Лейблы
    private final String
            LBL_TITLE_ADDITIONAL_INFO = "Дополнительная информация: Спама нет! :)";
    // Чек боксы
    private final String
            CHKBX_ALWAYS_ON_TOP_TITLE = "Always on top";
    // Текстовые поля
    private final String
            TXTFLD_WELCOME_FOR_TYPING = "Напишите сюда свое сообщение",
            TXTFLD_CHAT_MEMBERS_TITLE = "Участники чата",
            TXTFLD_CHAT_AREA          = "ЧАТ";

    /**
     * Панели для расположения и объединения компонентов.
     * Panels for layout and union of components.
     */
    private final JPanel
            northPnl  = new JPanel(new GridLayout(2, 4)),
            southPnl  = new JPanel(new GridLayout(1, 4)),
            eastPnl   = new JPanel(new GridLayout(10, 1)),
            westPnl   = new JPanel(new GridLayout()),
            centerPnl = new JPanel(new GridLayout());

    /**
     * Компоненты управления.
     * Кнопки, элементы управления, лейблы.
     * Controls.
     */
    // Кнопки
    private final JButton
            btn_SendMsg         = new JButton(BTN_SEND_MSG_TITLE),
            btn_Login           = new JButton(BTN_LOGIN_TITLE),
            btn_Disconnect      = new JButton(BTN_DISCONNECT_TITLE);
    // Чек боксы
    private final JCheckBox
            chkbx_AlwaysOnTop   = new JCheckBox(CHKBX_ALWAYS_ON_TOP_TITLE);
    // Текстовые поля
    private final JTextField
            txtfld_WriteMsg     = new JTextField(TXTFLD_WELCOME_FOR_TYPING),
            txtfld_ChatMembers  = new JTextField(TXTFLD_CHAT_MEMBERS_TITLE),
            txtfld_ChatArea     = new JTextField(TXTFLD_CHAT_AREA);
    // Лейблы
    private final JLabel
            lbl_AdditionalInfo  = new JLabel(LBL_TITLE_ADDITIONAL_INFO);
    // Скроллы
    private final JScrollPane
            scrll_ChatMembers   = new JScrollPane(),
            scrll_ChatArea      = new JScrollPane();

    /**
     * Текстовые поля ввода данных для сетевого подключения и авторизации пользователя.
     * Text fields input data for network connection and user authorisation.
     */
    private final JTextField
            txtfld_InternetAddress = new JTextField(IP),
            txtfld_Port            = new JTextField( String.valueOf(PORT) ),
            txtfld_Login           = new JTextField(USER_LOGIN),
            psfld_Password         = new JPasswordField(USER_PASS);

    /**
     * Отображение текстовых служебных сообщений сервера.
     * Viewer the server log text messages.
     */
//    private final JTextArea log = new JTextArea();

    /** */
    SocketThread sktThrd;


    // TEST /////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * ТЕСТИРОВАНИЕ.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {

        // Запускаем конструктор в потоке AWT / EDT ?
        //
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new ChatClientGUI();
            }
        });
    } // main()
    // TEST /////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Инициализация окна.
     * Window initialization.
     */
    private ChatClientGUI() {

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
        setBounds(WINDOW_X_POS, WINDOW_Y_POS, WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setTitle(WINDOW_TITLE);
        setAlwaysOnTop(false);
        setResizable(false);
        // --------------------------------------------------------


        // Содержимое окна
        // --------------------------------------------------------

        // Панель для Дополнительной информации.
        // NORTH
        this.add(northPnl, BorderLayout.NORTH);

        chkbx_AlwaysOnTop.setEnabled(true);
        lbl_AdditionalInfo.setEnabled(false);
        lbl_AdditionalInfo.setHorizontalAlignment(SwingConstants.CENTER);

        northPnl.add(chkbx_AlwaysOnTop);
        northPnl.add(lbl_AdditionalInfo);

        northPnl.setVisible(false);

        // Панель для написания сообщений и его отправки.
        // SOUTH
        add(southPnl, BorderLayout.SOUTH);
        southPnl.add(txtfld_WriteMsg);
        southPnl.add(btn_SendMsg);
        southPnl.setVisible(false);

        // Панель для отображения участников чата.
        // WEST
        add(westPnl, BorderLayout.WEST);
        txtfld_ChatMembers.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        westPnl.add(txtfld_ChatMembers);
        westPnl.setVisible(false);

        // Панель для отображения информации о текущем сетевом соединении.
        // EAST
        add(eastPnl, BorderLayout.EAST);

        txtfld_InternetAddress.setEditable(false);
        txtfld_InternetAddress.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        txtfld_Port.setEditable(false);
        txtfld_Port.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        txtfld_ChatArea.setEnabled(false);
        txtfld_ChatMembers.setEnabled(false);

        btn_Disconnect.setEnabled(false);

        eastPnl.add(txtfld_InternetAddress);
        eastPnl.add(txtfld_Port);
        eastPnl.add(new JLabel());
        eastPnl.add(txtfld_Login);
        eastPnl.add(psfld_Password);
        eastPnl.add(btn_Login);
        eastPnl.add(btn_Disconnect);

        // Основная панель для отображения текста чата.
        // CENTER
        add(centerPnl, BorderLayout.CENTER);
        txtfld_ChatArea.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        centerPnl.add(txtfld_ChatArea);
        // --------------------------------------------------------


        // Слушатели событий для компонентов.
        // --------------------------------------------------------
        // Слушатели кнопок
        //
        btn_Login.addActionListener(this);
        btn_Disconnect.addActionListener(this);
        btn_SendMsg.addActionListener(this);
        chkbx_AlwaysOnTop.addActionListener(this);

        btn_SendMsg.addKeyListener(this);
        // --------------------------------------------------------

        setVisible(true);

        //
        //
        // ТЕСТИРОВАНИЕ Обработки "скользких исключений" через текущий класс.
        //
        // throw new RuntimeException(" * Ой * ");
        //
        //

    } // ChatClientGUI()

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
        // log.append("Поймали " + e + " ");

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

    } // uncaughtException()

    /**
     *
     * Метод выполняется на совершенные действия пользователя.
     * Invoked when an action occurs.
     *
     * @param e event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent srcOfEvent = (JComponent) e.getSource();

        if (srcOfEvent == btn_Login) {

            southPnl.setVisible(true);
            northPnl.setVisible(true);
            westPnl.setVisible(true);

            btn_Disconnect.setEnabled(true);

            txtfld_ChatArea.setHorizontalAlignment(SwingConstants.LEFT);

            chkbx_AlwaysOnTop.setEnabled(true);
            txtfld_ChatArea.setEnabled(true);

            sendMsg("login");

            //
            sktThrd = new SocketThread();

            try {

                connect();
            } catch(IOException exception) {

                exception.printStackTrace();
            }

        } else if(srcOfEvent == btn_Disconnect) {

            southPnl.setVisible(false);
            northPnl.setVisible(false);
            westPnl.setVisible(false);

            txtfld_ChatArea.setText("");
            txtfld_ChatArea.setEnabled(false);

            sendMsg("disconnect");

            disconnect();

        } else if(srcOfEvent == btn_SendMsg) {

            sendMsg("send message ...");

        } else if (srcOfEvent == chkbx_AlwaysOnTop) {

            setAlwaysOnTop(chkbx_AlwaysOnTop.isSelected());
            sendMsg("always on top");
        } else {

            throw new RuntimeException();
        } // if

    } // actionPerformed()

    /** */
    public void connect() throws IOException {

        Socket socket = new Socket(IP, PORT);

    } // connect()

    /** */
    public void disconnect() {

    } // disconnect()

    /** */
    public void sendMsg(String msg) {
        System.out.printf("\n%s", msg);
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e event.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e event.
     */
    @Override
    public void keyPressed(KeyEvent e) {

        Object component = e.getSource();

        if(component == btn_SendMsg) {

        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e event.
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
} // ChatClientGUI
