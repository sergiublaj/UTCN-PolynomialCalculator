package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PolynomialPanel extends JPanel {
    public static final int FIRST_POLYNOMIAL = 1;
    public static final int SECOND_POLYNOMIAL = 2;
    public static final int OUTPUT_POLYNOMIAL = 3;

    private String titleMessage;
    private String alertMessage;

    private JLabel polynomialTitle;
    private JTextField polynomialField;
    private JButton polynomialClear;
    private JLabel polynomialAlert;

    public PolynomialPanel(String titleMessage) {
        this.titleMessage = titleMessage;
        this.alertMessage = null;
        this.createPolynomialPanel();
    }

    public void createPolynomialPanel() {
        this.setUpPanel();
        this.setUpPolynomialTitleZone();
        this.setUpPolynomialInteractiveZone();
        this.setUpAlertMessageZone();
    }

    private void setUpPanel() {
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    private void setUpPolynomialTitleZone() {
        polynomialTitle = new JLabel(this.titleMessage);
        polynomialTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        polynomialTitle.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(polynomialTitle);
    }

    private void setUpPolynomialInteractiveZone() {
        JPanel polynomialInteractivePanel = new JPanel();
        polynomialInteractivePanel.setLayout(new BoxLayout(polynomialInteractivePanel, BoxLayout.X_AXIS));
        this.add(polynomialInteractivePanel);

        polynomialField = new JTextField();
        polynomialField.setFont(new Font("Courier New", Font.PLAIN, 20));
        polynomialInteractivePanel.add(polynomialField);

        polynomialClear = new JButton("CLEAR");
        polynomialClear.setIcon(new ImageIcon("src/main/resources/images/clear.png"));
        polynomialClear.setFont(new Font("Tahoma", Font.PLAIN, 18));
        polynomialClear.setHorizontalAlignment(SwingConstants.RIGHT);
        polynomialClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        polynomialInteractivePanel.add(polynomialClear);
    }

    private void setUpAlertMessageZone() {
        polynomialAlert = new JLabel(this.alertMessage);
        polynomialAlert.setFont(new Font("Tahoma", Font.PLAIN, 15));
        polynomialAlert.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(polynomialAlert);
    }

    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
        this.polynomialTitle.setText(titleMessage);
    }

    public void setAlertMessage(String alertMessage, Color alertColor) {
        this.alertMessage = alertMessage;
        this.polynomialAlert.setText(alertMessage);
        this.polynomialAlert.setForeground(alertColor);
        this.clearAlertMessage();
    }

    // In cazul unei erori afisate sub zona de input, functia de mai jos
    // Va curata dupa 3 secunde mesajul de eroare
    private void clearAlertMessage() {
        ScheduledExecutorService clearMessage = Executors.newSingleThreadScheduledExecutor();
        clearMessage.schedule(() -> setAlertMessage("", Color.WHITE), 3000, TimeUnit.MILLISECONDS);
    }

    public void disablePolynomialField() {
        this.polynomialField.setEditable(false);
    }

    public JTextField getPolynomialInput() {
        return polynomialField;
    }

    public void setPolynomialInput(String newOutput) {
        this.polynomialField.setText(newOutput);
    }

    public JButton getPolynomialClear() {
        return polynomialClear;
    }

}