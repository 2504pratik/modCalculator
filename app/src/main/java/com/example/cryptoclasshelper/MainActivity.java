package com.example.cryptoclasshelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputLayout baseIL = findViewById(R.id.baseTF);
        TextInputEditText baseET = baseIL.findViewById(R.id.baseET);

        TextInputLayout exponentIL = findViewById(R.id.exponentTF);
        TextInputEditText exponentET = exponentIL.findViewById(R.id.exponentET);

        TextInputLayout divisorIL = findViewById(R.id.divisorTF);
        TextInputEditText divisorET = divisorIL.findViewById(R.id.divisorET);

        Button calculateBtn = findViewById(R.id.calculateBtn);
        calculateBtn.setOnClickListener(v -> {
            String baseStr = Objects.requireNonNull(baseET.getText()).toString().trim();
            String exponentStr = Objects.requireNonNull(exponentET.getText()).toString().trim();
            String divisorStr = Objects.requireNonNull(divisorET.getText()).toString().trim();

            if (!baseStr.isEmpty() && !exponentStr.isEmpty() && !divisorStr.isEmpty()) {
                int base = Integer.parseInt(baseStr);
                int exponent = Integer.parseInt(exponentStr);
                int divisor = Integer.parseInt(divisorStr);

                int result = calculatePowerMod(base, exponent, divisor);

                TextView resultTv = findViewById(R.id.resultTv);
                resultTv.setText(String.valueOf(result));
            } else {
                Toast.makeText(MainActivity.this, "enter valid input value", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static int calculatePowerMod(int base, int exponent, int modulus) {
        if (exponent == 0) {
            return 1;
        } else if (exponent == 1) {
            return base % modulus;
        } else {
            // Calculate the power mod recursively
            int powerMod = calculatePowerMod(base, exponent / 2, modulus);
            powerMod = (powerMod * powerMod) % modulus;

            // If the exponent is odd, multiply the result by the base
            if (exponent % 2 == 1) {
                powerMod = (powerMod * base) % modulus;
            }
            return powerMod;
        }
    }
}
