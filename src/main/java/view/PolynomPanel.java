package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PolynomPanel extends JPanel {
    public static final int FIRST_POLYNOM = 1;
    public static final int SECOND_POLYNOM = 2;
    public static final int OUTPUT_POLYNOM = 3;

    private String titleMessage;
    private String alertMessage;

    private JLabel polynomTitle;
    private JTextField polynomField;
    private JButton polynomClear;
    private JLabel plynomAlert;

    public PolynomPanel(String titleMessage) {
        this.titleMessage = titleMessage;
        this.alertMessage = null;
        this.createPolynomPanel();
    }

    public void createPolynomPanel() {
        this.setUpPanel();
        this.setUpPolynomTitleZone();
        this.setUpPolynomInteractiveZone();
        this.setUpAlertMessageZone();
    }

    private void setUpPanel() {
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    private void setUpPolynomTitleZone() {
        polynomTitle = new JLabel(this.titleMessage);
        polynomTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        polynomTitle.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(polynomTitle);
    }

    private void setUpPolynomInteractiveZone() {
        JPanel polynomInteractivePanel = new JPanel();
        polynomInteractivePanel.setLayout(new BoxLayout(polynomInteractivePanel, BoxLayout.X_AXIS));
        this.add(polynomInteractivePanel);

        polynomField = new JTextField();
        polynomField.setFont(new Font("Courier New", Font.PLAIN, 20));
        polynomInteractivePanel.add(polynomField);

        polynomClear = new JButton("CLEAR");
        ImageIcon clearImage = new ImageIcon("src/main/resources/images/clear.png");
        polynomClear.setIcon(clearImage);
        polynomClear.setFont(new Font("Tahoma", Font.PLAIN, 18));
        polynomClear.setHorizontalAlignment(SwingConstants.RIGHT);
        polynomClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        polynomInteractivePanel.add(polynomClear);
    }

    private void setUpAlertMessageZone() {
        plynomAlert = new JLabel(this.alertMessage);
        plynomAlert.setFont(new Font("Tahoma", Font.PLAIN, 15));
        plynomAlert.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(plynomAlert);
    }

    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
        this.polynomTitle.setText(titleMessage);
    }

    public void setAlertMessage(String alertMessage, Color alertColor) {
        this.alertMessage = alertMessage;
        this.plynomAlert.setText(alertMessage);
        this.plynomAlert.setForeground(alertColor);
        this.clearAlertMessage();
    }

    private void clearAlertMessage() {
        ScheduledExecutorService clearMessage = Executors.newSingleThreadScheduledExecutor();
        clearMessage.schedule(() -> setAlertMessage("", Color.WHITE), 3000, TimeUnit.MILLISECONDS);
    }

    public void disablePolynomField() {
        this.polynomField.setEditable(false);
    }

    public JTextField getPolynomInput() {
        return polynomField;
    }

    public void setPolynomInput(String newOutput) {
        this.polynomField.setText(newOutput);
    }

    public JButton getPolynomClear() {
        return polynomClear;
    }

}