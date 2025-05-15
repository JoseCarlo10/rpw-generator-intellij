import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.util.Objects;

public class RandomPasswordGenerator implements ActionListener {
    JFrame frame;
    JPanel mainPanel;
    JPanel loadingPanel;

    JLabel useUpperCase;
    JLabel useLowerCase;
    JLabel useNumeric;
    JLabel useSpecialChar;

    JRadioButton enableButtonUpperCase;
    JRadioButton disableButtonUpperCase;

    JRadioButton enableButtonLowerCase;
    JRadioButton disableButtonLowerCase;

    JRadioButton enableButtonNumeric;
    JRadioButton disableButtonNumeric;

    JRadioButton enableButtonSpecialChar;
    JRadioButton disableButtonSpecialChar;

    ButtonGroup groupUpperCase;
    ButtonGroup groupLowerCase;
    ButtonGroup groupNumeric;
    ButtonGroup groupSpecialChar;

    JSlider slider;
    JLabel sliderValue;
    JButton generatePasswordbutton;
    JButton revealPasswordbutton;
    JProgressBar progressBar;

    int windowHeight = 490;
    int windowWidth = 500;
    static int modify;

    String finalPassword = "";
    String tier;
    String[] tierMessages;

    RandomPasswordGenerator() {

        frame = new JFrame("Random Password Generator");
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/RPG icon.png"));
        frame.setIconImage(appIcon);
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, windowWidth, windowHeight);
        mainPanel.setBackground(new Color(255, 217, 102));
        frame.setSize(windowWidth, windowHeight);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Labels
        useUpperCase = new JLabel("Use Uppercase?");
        useUpperCase.setBounds(20, 50, 120, 30);
        useLowerCase = new JLabel("Use Lowercase?");
        useLowerCase.setBounds(20, 90, 120, 30);
        useNumeric = new JLabel("Use Numbers?");
        useNumeric.setBounds(20, 130, 120, 30);
        useSpecialChar = new JLabel("Use Special Characters?");
        useSpecialChar.setBounds(20, 170, 160, 30);

        // Radio Buttons
        enableButtonUpperCase = new JRadioButton("Enable");
        disableButtonUpperCase = new JRadioButton("Disable", true);
        enableButtonLowerCase = new JRadioButton("Enable");
        disableButtonLowerCase = new JRadioButton("Disable", true);
        enableButtonNumeric = new JRadioButton("Enable");
        disableButtonNumeric = new JRadioButton("Disable", true);
        enableButtonSpecialChar = new JRadioButton("Enable");
        disableButtonSpecialChar = new JRadioButton("Disable", true);

        // Grouping
        groupUpperCase = new ButtonGroup();
        groupUpperCase.add(enableButtonUpperCase);
        groupUpperCase.add(disableButtonUpperCase);
        groupLowerCase = new ButtonGroup();
        groupLowerCase.add(enableButtonLowerCase);
        groupLowerCase.add(disableButtonLowerCase);
        groupNumeric = new ButtonGroup();
        groupNumeric.add(enableButtonNumeric);
        groupNumeric.add(disableButtonNumeric);
        groupSpecialChar = new ButtonGroup();
        groupSpecialChar.add(enableButtonSpecialChar);
        groupSpecialChar.add(disableButtonSpecialChar);

        // Positioning Radio Buttons
        enableButtonUpperCase.setBounds(170, 50, 100, 30);
        disableButtonUpperCase.setBounds(270, 50, 100, 30);
        enableButtonLowerCase.setBounds(170, 90, 100, 30);
        disableButtonLowerCase.setBounds(270, 90, 100, 30);
        enableButtonNumeric.setBounds(170, 130, 100, 30);
        disableButtonNumeric.setBounds(270, 130, 100, 30);
        enableButtonSpecialChar.setBounds(170, 170, 100, 30);
        disableButtonSpecialChar.setBounds(270, 170, 100, 30);

        // Slider
        slider = new JSlider(JSlider.HORIZONTAL, 6, 30, 6);
        slider.setMajorTickSpacing(6);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBounds(170, 210, 300, 60);
        sliderValue = new JLabel("Password Length: " + slider.getValue());
        sliderValue.setBounds(20, 210, 300, 30);

        // Buttons
        generatePasswordbutton = new JButton("Generate Password");
        generatePasswordbutton.setBounds(160, 290, 160, 50);
        generatePasswordbutton.setFocusable(false);
        generatePasswordbutton.addActionListener(this);
        generatePasswordbutton.setEnabled(false);

        // Loading panel
        loadingPanel = new JPanel();
        loadingPanel.setLayout(null);
        loadingPanel.setBounds(10, 282, 465, 160);
        loadingPanel.setBackground(new Color(255, 111, 97));
        loadingPanel.setVisible(false);

        // Reveal button
        revealPasswordbutton = new JButton("Reveal Password");
        revealPasswordbutton.setBounds(155, 100, 150, 40);
        revealPasswordbutton.setFocusable(false);
        revealPasswordbutton.addActionListener(this);
        revealPasswordbutton.setVisible(false);

        // Action Listeners for enabling Generate button
        ActionListener checkListener = e -> checkCondition();
        enableButtonUpperCase.addActionListener(checkListener);
        disableButtonUpperCase.addActionListener(checkListener);
        enableButtonLowerCase.addActionListener(checkListener);
        disableButtonLowerCase.addActionListener(checkListener);
        enableButtonNumeric.addActionListener(checkListener);
        disableButtonNumeric.addActionListener(checkListener);
        enableButtonSpecialChar.addActionListener(checkListener);
        disableButtonSpecialChar.addActionListener(checkListener);

        enableButtonUpperCase.setFocusable(false);
        disableButtonUpperCase.setFocusable(false);
        enableButtonLowerCase.setFocusable(false);
        disableButtonLowerCase.setFocusable(false);
        enableButtonNumeric.setFocusable(false);
        disableButtonNumeric.setFocusable(false);
        enableButtonSpecialChar.setFocusable(false);
        disableButtonSpecialChar.setFocusable(false);

        slider.addChangeListener(e -> sliderValue.setText("Selected Length: " + slider.getValue()));

        // Panel setup
        loadingPanel.add(revealPasswordbutton);
        mainPanel.add(useUpperCase);
        mainPanel.add(enableButtonUpperCase);
        mainPanel.add(disableButtonUpperCase);
        mainPanel.add(useLowerCase);
        mainPanel.add(enableButtonLowerCase);
        mainPanel.add(disableButtonLowerCase);
        mainPanel.add(useNumeric);
        mainPanel.add(enableButtonNumeric);
        mainPanel.add(disableButtonNumeric);
        mainPanel.add(useSpecialChar);
        mainPanel.add(enableButtonSpecialChar);
        mainPanel.add(disableButtonSpecialChar);
        mainPanel.add(sliderValue);
        mainPanel.add(slider);
        mainPanel.add(generatePasswordbutton);
        mainPanel.add(loadingPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generatePasswordbutton) {
            submit();
            calculate();
        } else if (e.getSource() == revealPasswordbutton) {
            frame.dispose();
            if (tier.equals("Tier 1")) {
                new TierOne(finalPassword);
            } else if (tier.equals("Tier 2")) {
                new TierTwo(finalPassword);
            } else if (tier.equals("Tier 3")) {
                new TierThree(finalPassword);
            } else if (tier.equals("Tier 4")) {
                new TierFour(finalPassword);
            } else if (tier.equals("Tier 5")) {
                new TierFive(finalPassword);
            }
        }
    }

    public void checkCondition() {
        generatePasswordbutton.setEnabled(
                enableButtonSpecialChar.isSelected() ||
                        enableButtonLowerCase.isSelected() ||
                        enableButtonUpperCase.isSelected() ||
                        enableButtonNumeric.isSelected()
        );
    }

    public void submit() {
        generatePasswordbutton.setVisible(false);
        slider.setEnabled(false);
        loadingPanel.setVisible(true);
        enableButtonUpperCase.setEnabled(false);
        disableButtonUpperCase.setEnabled(false);
        enableButtonLowerCase.setEnabled(false);
        disableButtonLowerCase.setEnabled(false);
        enableButtonNumeric.setEnabled(false);
        disableButtonNumeric.setEnabled(false);
        enableButtonSpecialChar.setEnabled(false);
        disableButtonSpecialChar.setEnabled(false);
    }

    public void calculate() {
        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        String specialChars = "!@#$%^&*()-_=+[]{};:,.<>?/";
        String numberChars = "1234567890";

        String combinedChars = "";
        int passwordLength = slider.getValue();

        boolean isUpper = enableButtonUpperCase.isSelected();
        boolean isLower = enableButtonLowerCase.isSelected();
        boolean isNumeric = enableButtonNumeric.isSelected();
        boolean isSpecial = enableButtonSpecialChar.isSelected();

        if (isUpper) combinedChars += upperCaseChars;
        if (isLower) combinedChars += lowerCaseChars;
        if (isNumeric) combinedChars += numberChars;
        if (isSpecial) combinedChars += specialChars;

        // Count how many types are enabled
        int enabledCount = 0;
        if (isUpper) enabledCount++;
        if (isLower) enabledCount++;
        if (isNumeric) enabledCount++;
        if (isSpecial) enabledCount++;

        int loadingTime = 4000; // default
        tier = "Tier 1";

        // Tier Logic
        if (enabledCount == 1 && passwordLength >= 6 && passwordLength <= 10) {
            tier = "Tier 1";
        } else if ((enabledCount == 1 && passwordLength >= 11 && passwordLength <= 16) ||
                (enabledCount == 2 && passwordLength >= 6 && passwordLength <= 15)) {
            loadingTime = 6000;
            tier = "Tier 2";
            modify = (enabledCount == 2 && passwordLength >= 11 && passwordLength <= 13) ? 260 : 270;
        } else if ((enabledCount == 2 && passwordLength >= 16 && passwordLength <= 20) ||
                (enabledCount == 1 && passwordLength >= 17)) {
            loadingTime = 9000;
            tier = "Tier 3";
            modify = (enabledCount == 2 && passwordLength <= 20) ? 420 : 390;
        } else if (enabledCount == 3 && passwordLength >= 11 && passwordLength <= 20) {
            loadingTime = 13000;
            tier = "Tier 4";
        } else if (enabledCount == 4 && passwordLength >= 21) {
            loadingTime = 15000;
            tier = "Tier 5";
            modify = (passwordLength <= 26) ? 200 : 170;
        }

        System.out.println("Password Length: " + passwordLength);
        System.out.println("Loading Tier: " + tier);

        // Generate password
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            int index = (int) (Math.random() * combinedChars.length());
            password.append(combinedChars.charAt(index));
        }
        finalPassword = password.toString();

        loadingProgress(loadingTime, tier);
    }


    public void loadingProgress(int duration, String tier) {
        JLabel label = new JLabel("Status: ");
        label.setBounds(20, 10, 400, 30);
        loadingPanel.add(label);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(30, 50, 400, 30);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        loadingPanel.add(progressBar);

        String[] names = {"Donald Trump", "Vladimir Putin", "Google", "Elon Musk",
                            "The Rock", "Giga Chad", "Shrek", "John Wick", "Mr. Beast",
                            "Your Mom", "ChatGPT", "Dwayne 'The Rock' Johnson", "One Punch Man",
                            "Batman", "Rick Astley", "Minecraft Steve", "NPC #420", "Bob the Builder",
                            "Keanu Reeves", "Walter White", "Sigma Male", "The IRS", "Area 51 Security",
                            "Thanos", "Skynet", "The Matrix", "Your Crush"
        };

        String randomName = names[(int) (Math.random() * names.length)];

        switch (tier) {
            case "Tier 1": tierMessages = new String[]{"Generating Password..."}; break;
            case "Tier 2": tierMessages = new String[]{"Generating Password...", "Shuffling Characters..."}; break;
            case "Tier 3": tierMessages = new String[]{"Analyzing Complexity...", "Generating Password...", "Shuffling Characters..."}; break;
            case "Tier 4": tierMessages = new String[]{"Analyzing Complexity...", "Fetching Requirements...", "Generating Password...", "Shuffling Characters..."}; break;
            case "Tier 5": tierMessages = new String[]{"Analyzing Complexity...", "Fetching Requirements...", "Generating Password...", "Shuffling Characters...", "Flexing Your Password to " + randomName}; break;
            default: tierMessages = new String[]{"Generating Password..."};
        }

        int delay = duration / 50;
        Timer timer = new Timer(delay, null);
        final int[] progress = {0};

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progress[0] += 2;
                progressBar.setValue(progress[0]);

                if (tier.equals("Tier 1")) {
                    label.setText(tierMessages[0]);
                } else if (tier.equals("Tier 2")) {
                    label.setText(progress[0] < 50 ? tierMessages[0] : tierMessages[1]);
                } else if (tier.equals("Tier 3")) {
                    if (progress[0] < 33) label.setText(tierMessages[0]);
                    else if (progress[0] < 66) label.setText(tierMessages[1]);
                    else label.setText(tierMessages[2]);
                } else if (tier.equals("Tier 4")) {
                    if (progress[0] < 25) label.setText(tierMessages[0]);
                    else if (progress[0] < 50) label.setText(tierMessages[1]);
                    else if (progress[0] < 75) label.setText(tierMessages[2]);
                    else label.setText(tierMessages[3]);
                } else if (tier.equals("Tier 5")) {
                    if (progress[0] < 20) label.setText(tierMessages[0]);
                    else if (progress[0] < 40) label.setText(tierMessages[1]);
                    else if (progress[0] < 60) label.setText(tierMessages[2]);
                    else if (progress[0] < 80) label.setText(tierMessages[3]);
                    else label.setText(tierMessages[4]);
                }

                if (progress[0] >= 100) {
                    timer.stop();
                    revealPasswordbutton.setVisible(true);
                    label.setText("Done!");
                }
            }
        });
        timer.start();
    }
    
}

// ResultFrame to display the password
class TierOne extends JFrame implements ActionListener {
    private final JLabel label;
    private final JButton copy, tryAgain;

    // Assuming width and height are defined elsewhere or as constants.
    private static final int width = 600;
    private static final int height = 400;

    public TierOne(String password) {
        super("Generated Password");
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/RPG icon.png"));
        setIconImage(appIcon);
        setLayout(null);
        setSize(width + 13, height + 36);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon giveIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/To Give is to Receive.jpg")));

        if (giveIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
            giveIcon = new ImageIcon("path/to/default_image.jpg");  // Fallback in case the image is not found
        }

        Image giveIcon_scaledImage = giveIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        giveIcon = new ImageIcon(giveIcon_scaledImage);

        JLabel giveIcon_label = new JLabel(giveIcon);
        giveIcon_label.setBounds(0, 0, width, height); // Adjusted to scaled image size

        // Password display label setup
        label = new JLabel(password);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Dimension newSize = label.getPreferredSize();
        label.setBounds(170, 220, newSize.width, newSize.height);

        // Copy password button
        copy = new JButton("Copy Password");
        copy.setLayout(null);
        copy.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        copy.setFocusable(false);
        copy.setBounds(350, 340, 110, 30);
        copy.addActionListener(this);

        // Try again button
        tryAgain = new JButton("Try Again");
        tryAgain.setLayout(null);
        tryAgain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tryAgain.setFocusable(false);
        tryAgain.setBounds(475, 340, 110, 30);
        tryAgain.addActionListener(this);

        // Adding components to the frame
        add(copy);
        add(tryAgain);
        add(label);
        add(giveIcon_label);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copy) {
            String textToCopy = label.getText();  // Get the password from JLabel
            StringSelection selection = new StringSelection(textToCopy);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        }
        if (e.getSource() == tryAgain) {
            this.dispose();
            new RandomPasswordGenerator();
        }
    }
}

class TierTwo extends JFrame implements ActionListener {
    private final JLabel label;
    private final JButton copy, tryAgain;

    int width = 630;
    int height = 480;
    TierTwo(String password) {
        super("Generated Password");
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/RPG icon.png"));
        setIconImage(appIcon);
        setLayout(null);
        setSize(width+12, height+36);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon wsIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Will Smith.jpg")));

        if (wsIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
            wsIcon = new ImageIcon("path/to/default_image.jpg");  // Fallback in case the image is not found
        }

        Image wsIcon_scaledImage = wsIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        wsIcon = new ImageIcon(wsIcon_scaledImage);

        JLabel wsIcon_label = new JLabel(wsIcon);
        wsIcon_label.setBounds(0, 0, width, height); // Adjusted to scaled image size


        label = new JLabel(password);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK); // White text for visibility
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Dimension newSize = label.getPreferredSize();
        label.setBounds(RandomPasswordGenerator.modify, 220, newSize.width, newSize.height);
        label.revalidate();
        label.repaint();

        copy = new JButton("Copy Password");
        copy.setLayout(null);
        copy.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        copy.setFocusable(false);
        copy.setBounds(370, 430, 110, 30);
        copy.addActionListener(this);

        tryAgain = new JButton("Try Again");
        tryAgain.setLayout(null);
        tryAgain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tryAgain.setFocusable(false);
        tryAgain.setBounds(495, 430, 110, 30);
        tryAgain.addActionListener(this);

        add(copy);
        add(tryAgain);
        add(label);
        add(wsIcon_label);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copy) {
            String textToCopy = label.getText();  // Get the password from JLabel
            StringSelection selection = new StringSelection(textToCopy);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        }
        if (e.getSource() == tryAgain) {
            this.dispose();
            new RandomPasswordGenerator();
        }
    }
}

class TierThree extends JFrame implements ActionListener {
    private final JLabel label;
    private final JButton copy, tryAgain;

    int width = 640;
    int height = 416;
    TierThree(String password) {
        super("Generated Password");
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/RPG icon.png"));
        setIconImage(appIcon);
        setLayout(null);
        setSize(width+10, height+37);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon tjIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Tom and Jerry.jpg")));

        if (tjIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
            tjIcon = new ImageIcon("path/to/default_image.jpg");  // Fallback in case the image is not found
        }

        Image tjIcon_scaledImage = tjIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        tjIcon = new ImageIcon(tjIcon_scaledImage);

        JLabel tjIcon_label = new JLabel(tjIcon);
        tjIcon_label.setBounds(0, 0, width, height); // Adjusted to scaled image size


        label = new JLabel(password);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK); // White text for visibility
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Dimension newSize = label.getPreferredSize();
        label.setBounds(RandomPasswordGenerator.modify, 200, newSize.width, newSize.height);
        label.revalidate();
        label.repaint();

        copy = new JButton("Copy Password");
        copy.setLayout(null);
        copy.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        copy.setFocusable(false);
        copy.setBounds(390, 370, 110, 30);
        copy.addActionListener(this);

        tryAgain = new JButton("Try Again");
        tryAgain.setLayout(null);
        tryAgain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tryAgain.setFocusable(false);
        tryAgain.setBounds(510, 370, 110, 30);
        tryAgain.addActionListener(this);

        add(copy);
        add(tryAgain);
        add(label);
        add(tjIcon_label);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copy) {
            String textToCopy = label.getText();  // Get the password from JLabel
            StringSelection selection = new StringSelection(textToCopy);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        }
        if (e.getSource() == tryAgain) {
            this.dispose();
            new RandomPasswordGenerator();
        }
    }
}

class TierFour extends JFrame implements ActionListener {
    private final JLabel label;
    private final JButton copy, tryAgain;

    int width = 630;
    int height = 480;
    TierFour(String password) {
        super("Generated Password");
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/RPG icon.png"));
        setIconImage(appIcon);
        setLayout(null);
        setSize(width+11, height+35);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon skeletorIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Skeletor.jpg")));

        if (skeletorIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
            skeletorIcon = new ImageIcon("path/to/default_image.jpg");  // Fallback in case the image is not found
        }

        Image skeletorIcon_scaledImage = skeletorIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        skeletorIcon = new ImageIcon(skeletorIcon_scaledImage);

        JLabel skeletorIcon_label = new JLabel(skeletorIcon);
        skeletorIcon_label.setBounds(0, 0, width, height); // Adjusted to scaled image size


        label = new JLabel(password);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK); // White text for visibility
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Dimension newSize = label.getPreferredSize();
        label.setBounds(20, 20, newSize.width, newSize.height);
        label.revalidate();
        label.repaint();

        copy = new JButton("Copy Password");
        copy.setLayout(null);
        copy.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        copy.setFocusable(false);
        copy.setBounds(380, 430, 110, 30);
        copy.addActionListener(this);

        tryAgain = new JButton("Try Again");
        tryAgain.setLayout(null);
        tryAgain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tryAgain.setFocusable(false);
        tryAgain.setBounds(500, 430, 110, 30);
        tryAgain.addActionListener(this);

        add(copy);
        add(tryAgain);
        add(label);
        add(skeletorIcon_label);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copy) {
            String textToCopy = label.getText();  // Get the password from JLabel
            StringSelection selection = new StringSelection(textToCopy);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        }
        if (e.getSource() == tryAgain) {
            this.dispose();
            new RandomPasswordGenerator();
        }
    }
}

class TierFive extends JFrame implements ActionListener {
    private final JLabel label;
    private final JButton copy, tryAgain;

    int width = 630;
    int height = 480;
    TierFive(String password) {
        super("Generated Password");
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/RPG icon.png"));
        setIconImage(appIcon);
        setLayout(null);
        setSize(width+11, height+35);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon ptcIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Point To Center.jpg")));

        if (ptcIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
            ptcIcon = new ImageIcon("path/to/default_image.jpg");  // Fallback in case the image is not found
        }

        Image ptcIcon_scaledImage = ptcIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        ptcIcon = new ImageIcon(ptcIcon_scaledImage);

        JLabel ptcIcon_label = new JLabel(ptcIcon);
        ptcIcon_label.setBounds(0, 0, width, height); // Adjusted to scaled image size


        label = new JLabel(password);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK); // White text for visibility
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Dimension newSize = label.getPreferredSize();
        label.setBounds(RandomPasswordGenerator.modify, 250, newSize.width, newSize.height);
        label.revalidate();
        label.repaint();

        copy = new JButton("Copy Password");
        copy.setLayout(null);
        copy.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        copy.setFocusable(false);
        copy.setBounds(380, 430, 110, 30);
        copy.addActionListener(this);

        tryAgain = new JButton("Try Again");
        tryAgain.setLayout(null);
        tryAgain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tryAgain.setFocusable(false);
        tryAgain.setBounds(505, 430, 110, 30);
        tryAgain.addActionListener(this);

        add(copy);
        add(tryAgain);
        add(label);
        add(ptcIcon_label);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copy) {
            String textToCopy = label.getText();  // Get the password from JLabel
            StringSelection selection = new StringSelection(textToCopy);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        }
        if (e.getSource() == tryAgain) {
            this.dispose();
            new RandomPasswordGenerator();
        }
    }
}