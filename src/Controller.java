import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;
    private String entry = "0";
    private String expression = "";
    private String operation = "";
    private float firstNumber, secondNumber = 0;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        //set initial entry to 0
        view.setDisplay("0");

        for (JButton button : view.getNumberButton()) {
            view.addAction(button, new numberButtonListener());
        }
        view.addAction(view.getDotButton(), new dotButtonListener());
        view.addAction(view.getPlusMinusButton(), new plusMinusButtonListener());
        view.addAction(view.getAddButton(), new operationButtonListener());
        view.addAction(view.getSubtractButton(), new operationButtonListener());
        view.addAction(view.getMultiplyButton(), new operationButtonListener());
        view.addAction(view.getDivideButton(), new operationButtonListener());

    }

    public class numberButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Ensure entry not start with zero
            if (entry.equals("0")) {
                if (!e.getActionCommand().equals("0")) {
                    entry = "";
                    entry += e.getActionCommand();
                }
            } else {
                entry += e.getActionCommand();
            }
            view.setDisplay(entry);
        }
    }

    public class plusMinusButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (entry.contains("-")) {
                entry = entry.substring(1);
            } else {
                entry = "-" + entry;
            }
            view.setDisplay(entry);
        }
    }

    public class dotButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!entry.contains(".")) {
                entry += ".";
                view.setDisplay(entry);
            }
        }
    }

    public class operationButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (entry.matches(".*[+\\-x÷].*")) {
                firstNumber = Float.parseFloat(entry.split("[+\\-x÷]")[0]);
                secondNumber = Float.parseFloat(entry.split("[+\\-x÷]")[1]);
                switch (e.getActionCommand()) {
                    case ("+"):
                        firstNumber = model.addition(firstNumber,secondNumber);
                        break;
                    case ("-"):
                        firstNumber = model.subtraction(firstNumber,secondNumber);
                        break;
                    case ("x"):
                        firstNumber = model.multiplication(firstNumber,secondNumber);
                        break;
                    case ("÷"):
                        firstNumber = model.division(firstNumber,secondNumber);
                        break;
                }
                //Remove decimal places if the result is a whole number
                if(firstNumber % 1 == 0){
                    entry = String.format("%.0f",firstNumber) + e.getActionCommand();
                } else {
                    entry = String.valueOf(firstNumber) + e.getActionCommand();
                }

            } else {
                switch (e.getActionCommand()) {
                    case ("+"):
                        entry += "+";
                        break;
                    case ("-"):
                        entry += "-";
                        break;
                    case ("x"):
                        entry += "x";
                        break;
                    case ("÷"):
                        entry += "÷";
                        break;
                }
            }
            view.setDisplay(entry);
        }
    }
}