
import java.util.Stack;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author azizu
 */
public class ButtonHandler implements ActionListener {

    GUI ref;
    String previousNumebr;
    double previousResult;
    String previousActionCommand;
    Stack<String> expressionTokens;

    public ButtonHandler(GUI gui) {
        ref = gui;
        previousNumebr = null;
        previousResult = Double.MAX_VALUE;
        previousActionCommand = null;
        expressionTokens = new Stack<String>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = ref.tfLarge.getText();
        double number = Double.parseDouble(str);
        double num1;

        switch (e.getActionCommand()) {
            case "0": {
                if(isOperator(previousActionCommand)){
                    writeToLargeTextField("0");
                } else if (number != 0) {
                    writeToLargeTextField(str + "0");
                } else if (str.indexOf(".") != -1) {
                    writeToLargeTextField(str + "0");
                }
                previousActionCommand = "0";
                break;
            }
            case "1": {
                writeDigit("1");
                break;
            }
            case "2": {
                writeDigit("2");
                break;
            }
            case "3": {
                writeDigit("3");
                break;
            }
            case "4": {
                writeDigit("4");
                break;
            }
            case "5": {
                writeDigit("5");
                break;
            }
            case "6": {
                writeDigit("6");
                break;
            }
            case "7": {
                writeDigit("7");
                break;
            }
            case "8": {
                writeDigit("8");
                break;
            }
            case "9": {
                writeDigit("9");
                break;
            }
            case "%": {
                operate("%");
               break;
            }
            case "sqrt": {
                double num = Double.parseDouble(str);
                writeToLargeTextField(getResultString(Math.sqrt(num)));
                ref.tfSmall.setText("");
                expressionTokens.clear();
                break;
            }
            case "x^2": {
                double num = Double.parseDouble(str);
                writeToLargeTextField(getResultString(num*num));
                ref.tfSmall.setText("");
                expressionTokens.clear();
                break;
            }
            case "1/x": {
                double num = Double.parseDouble(str);
                writeToLargeTextField(getResultString(1/num));
                ref.tfSmall.setText("");
                expressionTokens.clear();
                break;
            }
            case "<-X": {
                if(!isOperand(previousActionCommand)){
                    return;
                }
                int len = ref.tfLarge.getText().length();
                if (len == 1) {
                    displayResultOnLargeTextField("0");
                } else {
                    displayResultOnLargeTextField(ref.tfLarge.getText().substring(0, len - 1));
                }
                break;
            }
            case "C": {
                ref.tfLarge.setText("0");
                ref.tfSmall.setText("");
                expressionTokens.clear();
                previousActionCommand = null;
                previousNumebr = null;
                previousResult = Double.MAX_VALUE;
                break;
            }
            case "CE": {
                ref.tfLarge.setText("0");
                previousActionCommand = "CE";
                break;
            }
            case "*": {
                operate("*");
                break;
            }
            case "/": {
                operate("/");
                break;
            }
            case "-": {
                operate("-");
                break;
            }
            case "+": {
                operate("+");
                break;
            }
            case "=": {
                if (previousNumebr == null) {
                    return;
                }
                expressionTokens.push(str);
                
                if (previousActionCommand == "=") {
                    expressionTokens.pop();
                    num1 = Double.parseDouble(expressionTokens.peek());
                } else if (previousResult == Double.MAX_VALUE) {
                    num1 = Double.parseDouble(previousNumebr);
                } else {
                    num1 = previousResult;
                }

                ref.tfSmall.setText("");
                switch (expressionTokens.get(expressionTokens.size() - 2)) {
                    case "+": {
                        double res = num1 + number;
                        displayResultOnLargeTextField(getResultString(res));
                        previousResult = res;
                        break;
                    }
                    case "-": {
                        double res = num1 - number;
                        displayResultOnLargeTextField(getResultString(res));
                        previousResult = res;
                        break;
                    }
                    case "×": {
                        double res = num1 * number;
                        displayResultOnLargeTextField(getResultString(res));
                        previousResult = res;
                        break;
                    }
                    case "÷": {
                        double res = num1 / number;
                        displayResultOnLargeTextField(getResultString(res));
                        previousResult = res;
                        break;
                    }
                }
                if(previousActionCommand == "="){
                      
                    String num = expressionTokens.get(expressionTokens.size() - 1);
                    String opp = expressionTokens.get(expressionTokens.size() - 2);
                    
                    expressionTokens.clear();
                    expressionTokens.push(previousResult + "");
                    expressionTokens.push(opp);
                    expressionTokens.push(num);
                }
                previousActionCommand = "=";
                break;
            }
            case ".": {
                if (isOperator(previousActionCommand)) {
                    writeToLargeTextField("0.");
                } else if (str.indexOf(".") == -1) {
                    writeToLargeTextField(str + ".");
                }
                previousActionCommand = ".";
                break;
            }
            case "+/-": {
                if(Double.parseDouble(str) == 0.0){
                    return;
                }
                writeToLargeTextField(getResultString(-1 * Double.parseDouble(str)));
                break;
            }
            case "M+": {
                if(ref.memory == null){
                    ref.memory = str;
                    ref.btnMC.setEnabled(true);
                    ref.btnM.setEnabled(true);
                    ref.btnMR.setEnabled(true);
                }else{
                    ref.memory = getResultString(Double.parseDouble(str) + Double.parseDouble(ref.memory));
                }
                previousActionCommand = "M+";
                break;
            }
            case "M-": {
                if(ref.memory == null){
                    ref.memory = getResultString((0.0 - Double.parseDouble(str)));
                    ref.btnMC.setEnabled(true);
                    ref.btnM.setEnabled(true);
                    ref.btnMR.setEnabled(true);
                }else{
                    ref.memory = getResultString(Double.parseDouble(ref.memory) - Double.parseDouble(str));
                }
                previousActionCommand = "M-";
                break;
            }
            case "MC": {
                ref.btnMC.setEnabled(false);
                ref.btnM.setEnabled(false);
                ref.btnMR.setEnabled(false);
                previousActionCommand = "MC";
                ref.memory = null;
                break;
            }
            case "MR": {
                writeToLargeTextField(ref.memory);
                previousActionCommand = "MR";
                break;
            }
            case "MS": {
                ref.memory = str;
                ref.btnMC.setEnabled(true);
                ref.btnM.setEnabled(true);
                ref.btnMR.setEnabled(true);
                previousActionCommand = "MS";
                break;
            }
        }
    }

    private void writeDigit(String dig) {

        String str = ref.tfLarge.getText();
        double number = Double.parseDouble(str);
        if (str.indexOf('.') != -1 && number == 0) {
            writeToLargeTextField(str + dig);
        } else if ((number == 0 || isOperator(previousActionCommand) || isMemoryOperation(previousActionCommand))) {
            writeToLargeTextField(dig);
        } else {
            writeToLargeTextField(str + dig);
        }
        previousActionCommand = dig;
    }

    private void operate(String opp) {//÷×√
        String str = ref.tfLarge.getText();

        String operationSymbol = opp;
        if (opp == "*") {
            operationSymbol = "×";
        } else if (opp == "/") {
            operationSymbol = "÷";
        }
        
        if (previousActionCommand == opp) {
            previousNumebr = str;
            previousActionCommand = opp;
            return;
        } else if (previousActionCommand == "=") {
            expressionTokens.clear();
        } else if (isOperator(previousActionCommand)) {//change operation
            expressionTokens.pop();
            expressionTokens.push(operationSymbol);
            ref.tfSmall.setText(getTextForSmallField());

            previousNumebr = str;
            previousActionCommand = opp;
            return;
        }
        if (!ref.tfSmall.getText().isEmpty()) {

            expressionTokens.push(str);
            expressionTokens.push(operationSymbol);

            double res = evaluateExpression();

            ref.tfSmall.setText(getTextForSmallField());

            displayResultOnLargeTextField(getResultString(res));

            previousResult = res;
        } else {
            expressionTokens.push(str);
            expressionTokens.push(operationSymbol);
            ref.tfSmall.setText(getTextForSmallField());
        }

        previousNumebr = str;
        previousActionCommand = opp;
    }

    private boolean isMemoryOperation(String str) {
        return str == "MC" || str == "MR";
    }
    
    private boolean isOperator(String str) {
        return str == "+" || str == "-" || str == "/" || str == "*" || str == "=";
    }
    
    private boolean isOperand(String str) {
        return str == "0" || str == "1" || str == "2" || str == "3" || str == "4" || str == "5" || str == "6" || str == "7" || str == "8" || str == "9";
    }

    private String getTextForSmallField() {
        String smallFieldText = expressionTokens.toString().replace(',', ' ').replace('[', ' ').replace(']', ' ');
        return smallFieldText;
    }

    private String getResultString(double res) {
        if (res % 1 == 0) {
            return new DecimalFormat("#").format(Math.floor(res)) + "";
        } else {
            return "" + res;
        }
    }

    private double evaluateExpression() {
        System.out.println(expressionTokens.toString());
        int i = 1;
        double result = Double.parseDouble(expressionTokens.get(0));
        double num1;
        String operator = "";
        while (i < expressionTokens.size() - 1) {
            operator = expressionTokens.get(i);
            num1 = Double.parseDouble(expressionTokens.get(i + 1));
            switch (operator) {
                case "+": {
                    result = result + num1;
                    break;
                }
                case "-": {
                    result = result - num1;
                    break;
                }
                case "×": {
                    result = result * num1;
                    break;
                }
                case "÷": {
                    result = result / num1;
                    break;
                }
            }
            i = i + 2;
        }

        return result;
    }
    
    private void displayResultOnLargeTextField(String res) {
       if (isOperator(previousActionCommand)) {
            ref.tfLarge.setText("");
        }
        int len = res.length();
        if (res.indexOf(".") != -1) {
            len--;
        }
        if (len < 10) {
            ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 50));
        } else if (len == 10) {
            ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 47));
        } else if (len == 11) {
            ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 42));
        } else if (len == 12) {
            ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 40));
        } else if (len == 13) {
            ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 37));
        } else if (len == 14) {
            ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 34));
        } else if (len == 15) {
            ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 32));
        } else if (len == 16) {
            ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 29));
        }else if(len > 16){
            ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 50));
            ref.tfLarge.setText(String.format("%6.1e", Double.parseDouble(res)));
            return;
        }
        ref.tfLarge.setText(res);
    }

    private void writeToLargeTextField(String text) {
        if (isOperator(previousActionCommand)) {
            ref.tfLarge.setText("");
        }
        int len = text.length();
        int fontSize = 50;
        if (text.indexOf(".") != -1) {
            len--;
        }
        if (len == 16) {
            fontSize = 32;
        } else if (len == 15) {
            fontSize = 32;
        } else if (len == 14) {
            fontSize = 34;
        } else if (len == 13) {
            fontSize = 37;
        } else if (len == 12) {
            fontSize = 40;
        } else if (len == 11) {
            fontSize = 42;
        } else if (len == 10) {
            fontSize = 47;
        } else if (len < 10) {
            fontSize = 50;
        }else{
            return;
        }
        ref.tfLarge.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
        ref.tfLarge.setText(text);
    }

}
