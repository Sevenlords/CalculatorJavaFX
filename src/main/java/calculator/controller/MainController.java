package calculator.controller;

import calculator.model.MainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainController {

    @FXML
    private TextField numField;
    private MainModel model = new MainModel();
    private double num1, num2;
    private String value = "";
    private static String localValue = "";
    private boolean start = true;
    private double dResult;

    //
    @FXML
    private void pressNum(ActionEvent event) {
        if (start) {
            numField.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        numField.setText(numField.getText().concat(value));
    }

    //
    @FXML
    private void pressOperation(ActionEvent event) {

        value = ((Button) event.getSource()).getText();

        if ("+".equals(value) || "-".equals(value) || "×".equals(value) || "÷".equals(value) || "x^y".equals(value)) {
            localValue = ((Button) event.getSource()).getText();
        }

        if ("=".equals(value)) {

            num2 = Double.parseDouble(numField.getText());
            dResult = model.calculated(num1, num2, localValue);
            goToNumField(dResult);

        } else if ("1/x".equals(value) || "√".equals(value)) {

            num1 = Double.parseDouble(numField.getText());
            dResult = model.calculated(num1, value);
            goToNumField(dResult);

        } else if ("%".equals(value)) {

            num2 = Double.parseDouble(numField.getText());
            dResult = model.percent(num1, num2, localValue);
            goToNumField(dResult);

        } else {
            num1 = Double.parseDouble(numField.getText());
        }

        start = true;
    }

    //Reformat result from double to string and set to TextField
    private void goToNumField(double res) {
        String sRes = String.valueOf(res);

        if (sRes.substring(sRes.length() - 2, sRes.length()).equals(".0")) {
            numField.setText(sRes.substring(0, sRes.length() - 2));
        } else numField.setText(sRes);
    }

    //Method to reform number
    @FXML
    private void ActionOnTheField(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        Pattern pCorrectNumber = Pattern.compile("[-0-9]+");
        Pattern pPositive = Pattern.compile("[0-9.]+");
        Pattern pNegative = Pattern.compile("^-.*");

        Matcher mCorrectNumber = pCorrectNumber.matcher(numField.getText());
        Matcher mPositive = pPositive.matcher(numField.getText());
        Matcher mNegative = pNegative.matcher(numField.getText());

        switch (value) {
            case ".":
                if (mCorrectNumber.matches())
                    numField.setText(numField.getText().concat("."));
                break;
            case "C":
                numField.setText("");
                break;
            case "←":
                if (!numField.getText().isEmpty())
                    numField.setText(numField.getText().substring(0, numField.getText().length() - 1));
                break;
            case "±":
                if (mPositive.matches()) {
                    numField.setText("-".concat(numField.getText()));
                } else if (mNegative.matches()) {
                    numField.setText(numField.getText().substring(1));
                }
                break;
        }
    }
}

