package com.example.cw1809;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private boolean isResultCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isResultCalc = false;
        Resources res = this.getResources();
        GridLayout buttonsGreed = findViewById(R.id.buttonsGrid);

        ArrayList<Button> calcBtn = new ArrayList<Button>();
        calcBtn.add(findViewById(R.id.buttonMultiply));
        calcBtn.add(findViewById(R.id.buttonDivide));
        calcBtn.add(findViewById(R.id.buttonAdd));
        calcBtn.add(findViewById(R.id.buttonSubtract));
        calcBtn.add(findViewById(R.id.buttonEqual));

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(0);

        for (int i = 0; i < buttonsGreed.getChildCount(); i++) {
            View child = buttonsGreed.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setPadding(button.getPaddingLeft(),0,button.getPaddingRight(),0);
                button.setWidth((int) res.getDimension(R.dimen.bnt_width));
                button.setHeight((int) res.getDimension(R.dimen.bnt_height));
                button.setTextSize(res.getDimension(R.dimen.text_size));
                button.setBackground(drawable);
                if (calcBtn.contains(button)) {
                    ViewCompat.setBackgroundTintList(button, ColorStateList.valueOf(
                            res.getColor(R.color.red, null)));
                    button.setTextColor(res.getColor(R.color.white, null));
                } else {
                    ViewCompat.setBackgroundTintList(button, ColorStateList.valueOf(
                            res.getColor(R.color.black, null)));
                    button.setTextColor(res.getColor(R.color.black, null));
                }
            }
        }
    }

    public void onClick(View view) {
        TextView textView = findViewById(R.id.inputResult);
        textView.setTextSize(getResources().getDimension(R.dimen.text_size));
        if (isResultCalc) {
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
                String[] numbers = result.split("\\s");
                if(Arrays.stream(numbers).count()>1){
                    calculate(view,true);
                }
                else{
                    textView.setText(String.valueOf(Double.parseDouble(result)*-1));
                }
            }
        } else if (id == R.id.buttonEqual) {
            calculate(view,false);
        } else {
            throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    @SuppressLint("SetTextI18n")
    public void calculate(View view, boolean isNegative) {
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
                            textView.setText(String.valueOf(isNegative?resultCalc*-1:resultCalc));
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