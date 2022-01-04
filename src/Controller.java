import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;
    private String operation = "";
    private String firstNumberString = "0", secondNumberString = "";
    private float firstNumber;
    private float secondNumber;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        //set initial entry to 0
        view.setDisplay("0");

        for (JButton button : view.getNumberButton()) {
            view.addAction(button, new numberButtonListener());
        }
        view.addAction(view.getPlusMinusButton(), new plusMinusButtonListener());
        view.addAction(view.getDotButton(), new dotButtonListener());
        view.addAction(view.getAddButton(), new operationButtonListener());
        view.addAction(view.getSubtractButton(), new operationButtonListener());
        view.addAction(view.getMultiplyButton(), new operationButtonListener());
        view.addAction(view.getDivideButton(), new operationButtonListener());
        view.addAction(view.getEqualButton(), new equalButtonListener());
        view.addAction(view.getClearButton(), new clearButtonListener());
        view.addAction(view.getClearEntryButton(), new clearEntryButtonListener());
        view.addAction(view.getBackspaceButton(), new blackSpaceButtonListener());

    }

    public class numberButtonListener implements ActionListener {

        private ActionEvent e;

        @Override
        public void actionPerformed(ActionEvent e) {
            this.e = e;
            if (operation.equals("")) {
                firstNumberString = concatNumberString(firstNumberString);
            } else {
                secondNumberString = concatNumberString(secondNumberString);
            }
            view.setDisplay(getExpression());
        }

        public String concatNumberString(String s) {
            //Ensure entry not start with zero
            if (s.equals("0")) {
                if (!e.getActionCommand().equals("0")) {
                    s = "";
                    s += e.getActionCommand();
                }
            } else {
                s += e.getActionCommand();
            }
            return s;
        }
    }

    public class plusMinusButtonListener implements ActionListener {

        private ActionEvent e;

        @Override
        public void actionPerformed(ActionEvent e) {
            this.e = e;
            if (operation.equals("")) {
                firstNumberString = toggleMinus(firstNumberString);
            } else {
                secondNumberString = toggleMinus(secondNumberString);
            }
            view.setDisplay(getExpression());
        }

        public String toggleMinus(String s) {
            if (!s.equals("0") && !s.equals("")) {
                if (s.contains("-")) {
                    s = s.substring(1);
                } else {
                    s = "-" + s;
                }
            }
            return s;
        }
    }


    public class dotButtonListener implements ActionListener {

        private ActionEvent e;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (operation.equals("")) {
                firstNumberString = addDecimal(firstNumberString);
            } else {
                secondNumberString = addDecimal(secondNumberString);
            }
            view.setDisplay(getExpression());
        }

        public String addDecimal(String s) {
            if (!s.contains(".")) {
                s += ".";
            }
            return s;
        }
    }

    public class operationButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (operation == "÷" && secondNumberString.equals("0")) {
                view.setDisplay("Cannot divide by zero");
                reset();
            } else {
                if (!secondNumberString.equals("")) {
                    calculate();
                    operation = e.getActionCommand();
                    secondNumberString = "";
                    view.setDisplay(getExpression());
                } else {
                    operation = e.getActionCommand();
                    view.setDisplay(getExpression());
                }
            }
        }
    }

    public class equalButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!operation.equals("") && !secondNumberString.equals("")) {
                calculate();
            }
            view.setDisplay("=" + firstNumberString);
            reset();
        }
    }

    public class clearButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
            view.setDisplay(getExpression());
        }
    }

    public class clearEntryButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (operation.equals("")) {
                firstNumberString = "0";
            } else {
                if (secondNumberString.equals("")) {
                    operation = "";
                } else {
                    secondNumberString = "";
                }
            }
            view.setDisplay(getExpression());
        }
    }

    public class blackSpaceButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (operation.equals("")) {
                if (firstNumberString.length() == 1 || firstNumberString.length() == 2 && firstNumberString.contains("-")) {
                    firstNumberString = "0";
                } else {
                    firstNumberString = firstNumberString.substring(0, firstNumberString.length() - 1);
                }
            } else if (!secondNumberString.equals("")) {
                if (secondNumberString.length() == 2 && secondNumberString.contains("-")){
                    secondNumberString = "";
                }
                else{
                    secondNumberString = secondNumberString.substring(0, secondNumberString.length() - 1);
                }
            }
            view.setDisplay(getExpression());
        }
    }

    public String getExpression() {
        return firstNumberString + operation + secondNumberString;
    }

    public void calculate() {
        firstNumber = Float.parseFloat(firstNumberString);
        secondNumber = Float.parseFloat(secondNumberString);
        System.out.println(firstNumber);
        System.out.println(secondNumber);
        switch (operation) {
            case ("+"):
                firstNumber = model.addition(firstNumber, secondNumber);
                break;
            case ("-"):
                firstNumber = model.subtraction(firstNumber, secondNumber);
                break;
            case ("x"):
                firstNumber = model.multiplication(firstNumber, secondNumber);
                break;
            case ("÷"):
                firstNumber = model.division(firstNumber, secondNumber);
                break;
        }
        //Remove 0 decimal places if result is a whole number
        System.out.println(firstNumber);
        if (firstNumber % 1 == 0) {
            firstNumberString = String.format("%.0f", firstNumber);
        } else {
            firstNumberString = String.valueOf(firstNumber);
        }
    }

    public void reset() {
        operation = "";
        firstNumberString = "0";
        secondNumberString = "";
        firstNumber = 0;
        secondNumber = 0;
    }
}