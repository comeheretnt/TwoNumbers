package com.example.twonumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextA;
    private EditText editTextB;
    private ListView listViewHistory;
    private ArrayList<String> historyList;
    private ArrayAdapter<String> historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        listViewHistory = findViewById(R.id.listViewHistory);

        historyList = new ArrayList<>();
        historyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        listViewHistory.setAdapter(historyAdapter);

        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndAddHistory("+");
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndAddHistory("-");
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndAddHistory("*");
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndAddHistory("/");
            }
        });
    }

    private void calculateAndAddHistory(String operator) {
        String valueA = editTextA.getText().toString();
        String valueB = editTextB.getText().toString();

        if (!valueA.isEmpty() && !valueB.isEmpty()) {
            double numA = Double.parseDouble(valueA);
            double numB = Double.parseDouble(valueB);
            double result = 0;

            switch (operator) {
                case "+":
                    result = numA + numB;
                    break;
                case "-":
                    result = numA - numB;
                    break;
                case "*":
                    result = numA * numB;
                    break;
                case "/":
                    if (numB != 0) {
                        result = numA / numB;
                    } else {
                        return;
                    }
                    break;
            }

            String historyEntry = valueA + " " + operator + " " + valueB + " = " + result;
            historyList.add(0, historyEntry);
            historyAdapter.notifyDataSetChanged();
            editTextA.setText("");
            editTextB.setText("");
        }
    }
}