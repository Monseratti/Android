package com.example.cw1809;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean isResultCalc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isResultCalc = false;
    }

    public void onClick(View view) {
        TextView textView = findViewById(R.id.inputResult);
        if(isResultCalc){
            textView.setText("");
            isResultCalc = false;
        }
        int id = view.getId();
        if (id == R.id.button0) {
            textView.append("0");
        } else if (id == R.id.button1) {
            textView.append("1");
        } else if (id == R.id.button2) {
            textView.append("2");
        } else if (id == R.id.button3) {
            textView.append("3");
        } else if (id == R.id.button4) {
            textView.append("4");
        } else if (id == R.id.button5) {
            textView.append("5");
        } else if (id == R.id.button6) {
            textView.append("6");
        } else if (id == R.id.button7) {
            textView.append("7");
        } else if (id == R.id.button8) {
            textView.append("8");
        } else if (id == R.id.button9) {
            textView.append("9");
        } else if (id == R.id.buttonAdd) {
            textView.append(" + ");
        } else if (id == R.id.buttonSubtract) {
            textView.append(" - ");
        } else if (id == R.id.buttonMultiply) {
            textView.append(" * ");
        } else if (id == R.id.buttonDivide) {
            textView.append(" / ");
        } else if (id == R.id.buttonDot) {
            textView.append(".");
        } else if (id == R.id.buttonMod) {
            textView.append(" % ");
        } else if (id == R.id.buttonClear) {
            textView.setText("");
        } else if (id == R.id.buttonBack) {
            String result = textView.getText().toString();
            if (result.length() > 0) {
                result = result.substring(0, result.length() - 1);
                textView.setText(result);
            }
        } else if (id == R.id.buttonEqual) {
            calculate(view);
        } else {
            throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
    @SuppressLint("SetTextI18n")
    public void calculate(View view) {
        TextView textView = findViewById(R.id.inputResult);
        String result = textView.getText().toString();
        //check if the input is empty
        if (result.length() == 0) {
            textView.setText("0");
        } else {
            //check if the input is valid
            if (result.matches(".*[0-9]+.*")) {
                //check if the input is a number
                if (result.matches("[0-9]+")) {
                    textView.setText(result);
                } else {
                    //check if the input is a decimal number
                    if (result.matches("[0-9]+\\.[0-9]+")) {
                        textView.setText(result);
                    } else {
                        //check if the input is a valid expression include + - * / %
                        if (result.matches("[0-9]+\\s[+\\-*/%]\\s[0-9]+")) {
                            String[] numbers = result.split("\\s");
                            double num1 = Double.parseDouble(numbers[0]);
                            double num2 = Double.parseDouble(numbers[2]);
                            double resultCalc = 0;
                            String operator = numbers[1];
                            switch (operator) {
                                case "+":
                                    resultCalc = num1 + num2;
                                    break;
                                case "-":
                                    resultCalc = num1 - num2;
                                    break;
                                case "*":
                                    resultCalc = num1 * num2;
                                    break;
                                case "/":
                                    resultCalc = num1 / num2;
                                    break;
                                case "%":
                                    resultCalc = num1 % num2;
                                    break;
                                default:
                                    break;
                            }
                            textView.setText(String.valueOf(resultCalc));
                        } else {
                            textView.setText("Invalid Expression");
                        }
                    }
                }
            } else {
                textView.setText("Invalid Expression");
            }
        }
        isResultCalc = true;
    }

}