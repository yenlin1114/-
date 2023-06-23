package com.yr.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextBtn = findViewById(R.id.next_button);
        nextBtn.setOnClickListener(nextPage);
    }

    /**
     * 檢查是否有輸入名字
     */
    private String getName() {
        TextInputLayout nameText = findViewById(R.id.name);
        String name = nameText.getEditText().getText().toString();

        if (name.equals("")) return "";
        return name;
    }

    /**
     * 下一頁
     */
    View.OnClickListener nextPage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = getName();
            if (!name.equals("")) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("請輸入名字")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
    };
}