import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {
    JPanel mainPanel;
    JPanel topPanel;
    JPanel middlePanel;
    JPanel bottomPanel;

    JLabel titleLabel;
    JTextArea fortuneDisplay;
    JScrollPane scroller;
    JButton fortuneButton;
    JButton quitButton;

    private final ArrayList<String> fortunes = new ArrayList<>(Arrays.asList(
            "You will receive a compliment from a complete stranger.",
            "A truly astonishing event will occur in the next week.",
            "Your secret admirer will soon reveal themselves.",
            "The best way to predict the future is to create it.",
            "Beware of the person whose name starts with 'B'.",
            "An unexpected windfall of cash is coming your way.",
            "Ask your doctor if this life is right for you.",
            "You will soon be sitting on a fortune. Too bad it's not yours.",
            "Tomorrow, you will find a lost item that you didn't know you lost.",
            "You will be hungry again in one hour.",
            "A long-held grievance will finally be resolved.",
            "Your strong character will lead to a promotion."
    ));
    private int lastFortuneIndex = -1;
    private final Random generator = new Random();

    Font titleFont = new Font("Serif", Font.BOLD | Font.ITALIC, 48);
    Font fortuneFont = new Font("Monospaced", Font.PLAIN, 16);
    Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

    private static final String ICON_PATH = "fortune_teller_icon.png"; // Make sure your image is named this

    public FortuneTellerFrame() {
        super("The Oracle of Java");

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createTopPanel();
        createMiddlePanel();
        createBottomPanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSizeAndCenter();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);

        try {
            ImageIcon icon = new ImageIcon(ICON_PATH);
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);

            titleLabel = new JLabel("The Oracle's Vision", icon, JLabel.CENTER);
            titleLabel.setFont(titleFont);
            titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
            titleLabel.setHorizontalTextPosition(JLabel.CENTER);
            titleLabel.setForeground(Color.BLUE);

            topPanel.add(titleLabel);
        } catch (Exception e) {
            titleLabel = new JLabel("Fortune Teller (Image Not Found)", JLabel.CENTER);
            titleLabel.setFont(titleFont);
            topPanel.add(titleLabel);
            System.err.println("Error loading image: " + ICON_PATH);
        }
    }

    private void createMiddlePanel() {
        middlePanel = new JPanel();
        middlePanel.setBackground(Color.WHITE);

        fortuneDisplay = new JTextArea(10, 80);
        fortuneDisplay.setFont(fortuneFont);
        fortuneDisplay.setEditable(false);

        scroller = new JScrollPane(fortuneDisplay);

        middlePanel.add(scroller);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        fortuneButton = new JButton("Read My Fortune!");
        quitButton = new JButton("Quit");

        fortuneButton.setFont(buttonFont);
        quitButton.setFont(buttonFont);

        fortuneButton.addActionListener(e -> {
            String newFortune = getNewFortune();
            fortuneDisplay.append(newFortune + "\n");
            fortuneDisplay.setCaretPosition(fortuneDisplay.getDocument().getLength());
        });

        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        bottomPanel.add(fortuneButton);
        bottomPanel.add(quitButton);
    }

    private String getNewFortune() {
        int newIndex;

        do {
            newIndex = generator.nextInt(fortunes.size());
        } while (newIndex == lastFortuneIndex);

        lastFortuneIndex = newIndex;
        return fortunes.get(newIndex);
    }

    private void setSizeAndCenter() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = (int) (screenWidth * 0.75);
        int frameHeight = (int) (screenHeight * 0.6);

        setSize(frameWidth, frameHeight);

        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setLocation(x, y);
    }
}