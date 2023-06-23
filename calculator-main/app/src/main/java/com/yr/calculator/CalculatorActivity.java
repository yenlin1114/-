package com.yr.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView resultText;
    private Button allClean, factorial, eq, point, posNeg;
    private String displayText, memory, currentOperator;
    private boolean secondNumberStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        showHelloMessage();

        calculator = new Calculator();

        resultText = findViewById(R.id.result);
        allClean = findViewById(R.id.all_clean);
        factorial = findViewById(R.id.factorial);
        eq = findViewById(R.id.eq);
        point = findViewById(R.id.point);
        posNeg = findViewById(R.id.pos_neg);
        Button[] numbers = new Button[]{
                findViewById(R.id.zero),
                findViewById(R.id.one),
                findViewById(R.id.two),
                findViewById(R.id.three),
                findViewById(R.id.four),
                findViewById(R.id.five),
                findViewById(R.id.six),
                findViewById(R.id.seven),
                findViewById(R.id.eight),
                findViewById(R.id.nine),
        };
        Button[] basicOperators = new Button[]{
                findViewById(R.id.add),
                findViewById(R.id.minus),
                findViewById(R.id.multiply),
                findViewById(R.id.divide)
        };

        allClean.setOnClickListener(allCleanListener);
        factorial.setOnClickListener(factorialListener);
        eq.setOnClickListener(eqListener);
        point.setOnClickListener(pointListener);
        posNeg.setOnClickListener(posNegListener);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i].setOnClickListener(numberListener);
        }
        for (int i = 0; i < basicOperators.length; i++) {
            basicOperators[i].setOnClickListener(basicOperateListener);
        }

        init();
    }

    /**
     * 初始化變數
     */
    private void init() {
        displayText = "0";
        memory = "";
        currentOperator = "";
        secondNumberStart = false;
        resultText.setText(displayText);
    }

    /**
     * 印出歡迎訊息
     */
    private void showHelloMessage() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView helloMessage = findViewById(R.id.welcome_message);
        helloMessage.setText(name + "，您好");
    }

    /**
     * 全部清除
     */
    private View.OnClickListener allCleanListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            init();
        }
    };

    /**
     * 正負號
     */
    private View.OnClickListener posNegListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (secondNumberStart) {
                displayText = "-0";
                secondNumberStart = false;
            } else if (displayText.charAt(0) == '-') {
                displayText = displayText.substring(1);
            } else {
                displayText = "-" + displayText;
            }
            resultText.setText(displayText);
        }
    };

    /**
     * 小數點
     */
    private View.OnClickListener pointListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (displayText.indexOf('.') == -1) {
                displayText += ".";
            }
            resultText.setText(displayText);
        }
    };

    /**
     * 階乘運算
     */
    private View.OnClickListener factorialListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            displayText = calculator.factorial(displayText);
            resultText.setText(displayText);
        }
    };

    /**
     * 基本運算符(加、減、乘、除)
     */
    private View.OnClickListener basicOperateListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!memory.equals("")) {
                displayText = calculator.basicOperate(currentOperator, memory, displayText);
                resultText.setText(displayText);
            }
            memory = displayText;
            secondNumberStart = true;
            Button b = (Button) view;
            currentOperator = b.getText().toString();
        }
    };

    /**
     * 等號
     */
    private View.OnClickListener eqListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!memory.equals("")) {
                displayText = calculator.basicOperate(currentOperator, memory, displayText);
                resultText.setText(displayText);
            }
            memory = "";
            currentOperator = "";
        }
    };

    /**
     * 數字鍵
     */
    private View.OnClickListener numberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            String digit = btn.getText().toString();
            if (secondNumberStart) {
                displayText = digit;
                secondNumberStart = false;
            } else if (displayText.equals("-0")) {
                displayText = "-" + digit;
            } else if (displayText.equals("0")) {
                displayText = digit;
            } else if (!currentOperator.equals("")) {
                displayText += digit;
            } else {
                displayText += digit;
            }
            resultText.setText(displayText);
        }
    };
}