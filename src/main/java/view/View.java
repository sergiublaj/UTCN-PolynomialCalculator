package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    public static final String APP_TITLE = "Polynomial calculator";
    public static final String FIRST_TITLE = "Enter first polynomial";
    public static final String SECOND_TITLE = "Enter second polynomial";
    public static final String OUTPUT_TITLE = "Result";

    public static final int ADD_BTN = 1;
    public static final int SUB_BTN = 2;
    public static final int MUL_BTN = 3;
    public static final int DIV_BTN = 4;
    public static final int DER_BTN = 5;
    public static final int INT_BTN = 6;

    private static final int APP_WIDTH = 900;
    private static final int APP_HEIGHT = 600;
    private static final int IMAGE_WIDTH = 150;
    private static final int IMAGE_HEIGHT = 500;
    private static final int BUTTONS_GAP = 15;

    private JPanel contentPanel;
    private JPanel imagePanel;
    private JPanel mainPanel;
    private JPanel operationsPanel;

    private PolynomialPanel firstPolynomial;
    private PolynomialPanel secondPolynomial;
    private PolynomialPanel outputPolynomial;

    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton deriveButton;
    private JButton integrateButton;

    public View() {
        this.setUpFrame();
        this.setUpContentPanel();
        this.addLeftSideInformation();
        this.addMainPanelComponents();
    }

    private void setUpFrame() {
        // DOAR PT AL DOILEA MONITOR
        // this.setLocation(2300, 250);

        this.setMinimumSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        this.setResizable(false);
        this.setTitle(APP_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setUpContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.LIGHT_GRAY);
        this.setContentPane(contentPanel);
    }

    private void addLeftSideInformation() {
        this.setUpImagePanel();
        this.addMathImage();
        this.addAuthorInfo();
    }

    private void setUpImagePanel() {
        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        imagePanel.setLayout(new BorderLayout());
        contentPanel.add(imagePanel, BorderLayout.WEST);
    }

    private void addMathImage() {
        JLabel mathImage = new JLabel();
        mathImage.setIcon(new ImageIcon("src/main/resources/images/math.jpeg"));
        imagePanel.add(mathImage, BorderLayout.CENTER);
    }

    private void addAuthorInfo() {
        JLabel shortInfo = new JLabel("<html>Ver: 1.17_18.55<br>" +
                "@ author Blaj Sergiu<br>" +
                "@ group 30225</html>");
        shortInfo.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
        shortInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
        shortInfo.setForeground(Color.WHITE);
        shortInfo.setBackground(Color.BLACK);
        shortInfo.setOpaque(true);
        imagePanel.add(shortInfo, BorderLayout.SOUTH);
    }

    private void addMainPanelComponents() {
        this.setUpAppPanel();
        this.addAppTitle();

        firstPolynomial = new PolynomialPanel(FIRST_TITLE);
        mainPanel.add(firstPolynomial);

        secondPolynomial = new PolynomialPanel(SECOND_TITLE);
        mainPanel.add(secondPolynomial);

        this.addButtonsPanel();

        outputPolynomial = new PolynomialPanel(OUTPUT_TITLE);
        outputPolynomial.disablePolynomialField();
        mainPanel.add(outputPolynomial);
    }

    private void setUpAppPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));
        contentPanel.add(mainPanel, BorderLayout.CENTER);
    }

    private void addAppTitle() {
        JLabel appTitle = new JLabel(APP_TITLE.toUpperCase());
        appTitle.setFont(new Font("Impact", Font.BOLD, 50));
        appTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(appTitle);
    }

    private void addButtonsPanel() {
        this.setUpButtonsPanel();
        this.setUpButtons();
    }

    private void setUpButtonsPanel() {
        operationsPanel = new JPanel();
        operationsPanel.setLayout(new GridLayout(1, 4, BUTTONS_GAP, 0 ));
        operationsPanel.setBorder(new EmptyBorder(0, BUTTONS_GAP, 0, BUTTONS_GAP));
        mainPanel.add(operationsPanel);
    }

    private void setUpButtons() {
        this.addAdditionButton();
        this.addSubtractionButton();
        this.addMultiplicationButton();
        this.addDivisionButton();
        this.addDerivationButton();
        this.addIntegrationButton();
    }

    private void addAdditionButton() {
        addButton = new JButton();
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setIcon(new ImageIcon("src/main/resources/images/addition.png"));
        operationsPanel.add(addButton);
    }

    private void addSubtractionButton() {
        subtractButton = new JButton();
        subtractButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        subtractButton.setIcon(new ImageIcon("src/main/resources/images/subtraction.png"));
        operationsPanel.add(subtractButton);
    }

    public void addMultiplicationButton() {
        multiplyButton = new JButton();
        multiplyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        multiplyButton.setIcon(new ImageIcon("src/main/resources/images/multiplication.png"));
        operationsPanel.add(multiplyButton);
    }

    public void addDivisionButton() {
        divideButton = new JButton();
        divideButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        divideButton.setIcon(new ImageIcon("src/main/resources/images/division.png"));
        operationsPanel.add(divideButton);
    }

    public void addDerivationButton() {
        deriveButton = new JButton();
        deriveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deriveButton.setIcon(new ImageIcon("src/main/resources/images/derivation.png"));
        operationsPanel.add(deriveButton);
    }

    public void addIntegrationButton() {
        integrateButton = new JButton();
        integrateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        integrateButton.setIcon(new ImageIcon("src/main/resources/images/integration.png"));
        operationsPanel.add(integrateButton);
    }

    public void addOperationListener(int operationNb, ActionListener crtEvent) {
        if(operationNb == ADD_BTN) {
            addButton.addActionListener(crtEvent);
        } else if(operationNb == SUB_BTN) {
            subtractButton.addActionListener(crtEvent);
        } else if(operationNb == MUL_BTN){
            multiplyButton.addActionListener(crtEvent);
        } else if(operationNb == DIV_BTN){
            divideButton.addActionListener(crtEvent);
        } else if(operationNb == DER_BTN){
            deriveButton.addActionListener(crtEvent);
        } else {
            integrateButton.addActionListener(crtEvent);
        }
    }

    public void addClearListener(int fieldNb, ActionListener crtEvent) {
        if(fieldNb == PolynomialPanel.FIRST_POLYNOMIAL) {
            firstPolynomial.getPolynomialClear().addActionListener(crtEvent);
        } else if(fieldNb == PolynomialPanel.SECOND_POLYNOMIAL) {
            secondPolynomial.getPolynomialClear().addActionListener(crtEvent);
        } else {
            outputPolynomial.getPolynomialClear().addActionListener(crtEvent);
        }
    }

    public void setTitleMessage(int fieldNb, String titleMessage) {
        if(fieldNb == PolynomialPanel.FIRST_POLYNOMIAL) {
            firstPolynomial.setTitleMessage(titleMessage);
        } else if(fieldNb == PolynomialPanel.SECOND_POLYNOMIAL) {
            secondPolynomial.setTitleMessage(titleMessage);
        } else {
            outputPolynomial.setTitleMessage(titleMessage);
        }
    }

    public String getInput(int fieldNb) {
        if(fieldNb == PolynomialPanel.FIRST_POLYNOMIAL) {
            return firstPolynomial.getPolynomialInput().getText();
        } else if (fieldNb == PolynomialPanel.SECOND_POLYNOMIAL){
            return secondPolynomial.getPolynomialInput().getText();
        } else {
            return outputPolynomial.getPolynomialInput().getText();
        }
    }

    public void setInput(int fieldNb, String outputMessage) {
        if(fieldNb == PolynomialPanel.FIRST_POLYNOMIAL) {
            firstPolynomial.setPolynomialInput(outputMessage);
        } else if (fieldNb == PolynomialPanel.SECOND_POLYNOMIAL) {
            secondPolynomial.setPolynomialInput(outputMessage);
        } else {
            outputPolynomial.setPolynomialInput(outputMessage);
        }
    }

    public void setAlertMessage(int fieldNb, String alertMessage, Color alertColor) {
        if(fieldNb == PolynomialPanel.FIRST_POLYNOMIAL) {
            firstPolynomial.setAlertMessage(alertMessage, alertColor);
        } else if(fieldNb == PolynomialPanel.SECOND_POLYNOMIAL) {
            secondPolynomial.setAlertMessage(alertMessage, alertColor);
        } else {
            outputPolynomial.setAlertMessage(alertMessage, alertColor);
        }
    }
}