import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JTextArea display;
    private JButton[] numberButton;
    private JButton plusMinusButton;
    private JButton dotButton;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton equalButton;
    private JButton clearButton;
    private JButton clearEntryButton;
    private JButton backspaceButton;

    public View() {

        //Default setting of frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 450);
        this.setTitle("Calculator");
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
        Border rootBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.getRootPane().setBorder(rootBorder);

        //Create display
        display = new JTextArea();
        GridBagConstraints displayGBC = new GridBagConstraints();
        displayGBC.gridx = 0;
        displayGBC.gridy = 0;
        this.add(display, displayGBC);
        display.setPreferredSize(new Dimension(380, 135));
        display.setBackground(Color.WHITE);
        Border displayBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        display.setBorder(displayBorder);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setLineWrap(true);

        //Create Button Panel
        JPanel buttonPanel = new JPanel();
        GridBagConstraints buttonPanelGBC = new GridBagConstraints();
        buttonPanelGBC.gridx = 0;
        buttonPanelGBC.gridy = 1;
        buttonPanelGBC.insets = new Insets(5, 0, 0, 0);
        this.add(buttonPanel, buttonPanelGBC);
        buttonPanel.setPreferredSize(new Dimension(380, 275));
        //buttonPanel.setBackground(Color.YELLOW);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //Create number buttons
        numberButton = new JButton[10];
        for (int i = 0; i < numberButton.length; i++) {
            numberButton[i] = new JButton(String.valueOf(i));
        }

        //Create other buttons
        plusMinusButton = new JButton("+/-");
        dotButton = new JButton(".");
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("x");
        divideButton = new JButton("÷");
        equalButton = new JButton("=");
        clearButton = new JButton("C");
        clearEntryButton = new JButton("CE");
        backspaceButton = new JButton("\u232B");

        //Add buttons to panel
        buttonPanel.add(clearButton);
        buttonPanel.add(clearEntryButton);
        buttonPanel.add(backspaceButton);
        buttonPanel.add(divideButton);
        for (int i = 7; i <= 9; i++) {
            buttonPanel.add(numberButton[i]);
        }
        buttonPanel.add(multiplyButton);
        for (int i = 4; i <= 6; i++) {
            buttonPanel.add(numberButton[i]);
        }
        buttonPanel.add(subtractButton);
        for (int i = 1; i <= 3; i++) {
            buttonPanel.add(numberButton[i]);
        }
        buttonPanel.add(addButton);
        buttonPanel.add(plusMinusButton);
        buttonPanel.add(numberButton[0]);
        buttonPanel.add(dotButton);
        buttonPanel.add(equalButton);

        //Set button font size
        for (int i = 0; i < buttonPanel.getComponentCount(); i++) {
            buttonPanel.getComponent(i).setFont(new Font("", Font.BOLD, 20));
        }

        this.pack();
        this.setVisible(true);
    }

    //Add action listener from controller to a button
    public void addAction(JButton button, ActionListener action) {
        button.addActionListener(action);
    }

    public void setDisplay(String text) {
        display.setText(text);
    }

    public JButton[] getNumberButton() {
        return numberButton;
    }

    public JButton getPlusMinusButton() {
        return plusMinusButton;
    }

    public JButton getDotButton() {
        return dotButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getSubtractButton() {
        return subtractButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDivideButton() {
        return divideButton;
    }

    public JButton getEqualButton() {
        return equalButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getClearEntryButton() {
        return clearEntryButton;
    }

    public JButton getBackspaceButton() {
        return backspaceButton;
    }
}