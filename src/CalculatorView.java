import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class CalculatorView extends JFrame {

    public CalculatorView(){

        //Default setting of frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400,450);
        this.setTitle("Calculator");
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
        Border rootBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        this.getRootPane().setBorder(rootBorder);

        //Create display
        JTextArea display = new JTextArea("hello");
        GridBagConstraints displayGBC = new GridBagConstraints();
        displayGBC.gridx = 0;
        displayGBC.gridy = 0;
        this.add(display, displayGBC);
        display.setPreferredSize(new Dimension(380,135));
        display.setBackground(Color.WHITE);
        Border displayBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        display.setBorder(displayBorder);

        //Create Button Panel
        JPanel buttonPanel = new JPanel();
        GridBagConstraints buttonPanelGBC = new GridBagConstraints();
        buttonPanelGBC.gridx = 0;
        buttonPanelGBC.gridy = 1;
        buttonPanelGBC.insets = new Insets(5,0,0,0);
        this.add(buttonPanel, buttonPanelGBC);
        buttonPanel.setPreferredSize(new Dimension(380,275));
        //buttonPanel.setBackground(Color.YELLOW);
        buttonPanel.setLayout(new GridLayout(5,4));

        //Create number buttons
        JButton[] numButton = new JButton[10];
        for(int i = 0; i < numButton.length; i++){
            numButton[i] = new JButton(String.valueOf(i));
        }

        //Create other buttons
        JButton plusMinusButton = new JButton("+/-");
        JButton dotButton = new JButton(".");
        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("x");
        JButton divideButton = new JButton("÷");
        JButton equalButton = new JButton("=");
        JButton cancelButton = new JButton("C");
        JButton emptyButton1 = new JButton();
        JButton emptyButton2 = new JButton();

        //Add buttons to panel
        buttonPanel.add(cancelButton);
        buttonPanel.add(emptyButton1);
        buttonPanel.add(emptyButton2);
        buttonPanel.add(divideButton);
        for(int i = 7; i <= 9; i++){
            buttonPanel.add(numButton[i]);
        }
        buttonPanel.add(multiplyButton);
        for(int i = 4; i <= 6; i++){
            buttonPanel.add(numButton[i]);
        }
        buttonPanel.add(subtractButton);
        for(int i = 1; i <= 3; i++){
            buttonPanel.add(numButton[i]);
        }
        buttonPanel.add(addButton);
        buttonPanel.add(plusMinusButton);
        buttonPanel.add(numButton[0]);
        buttonPanel.add(dotButton);
        buttonPanel.add(equalButton);

        //Set button font size
        for(int i = 0; i < buttonPanel.getComponentCount(); i++) {
            buttonPanel.getComponent(i).setFont(new Font("Arial", Font.BOLD, 20));
        }

        this.pack();
        this.setVisible(true);
    }
}
