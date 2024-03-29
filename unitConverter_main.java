package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceUnitSpinner, targetUnitSpinner;
    private EditText sourceValueEditText, resultEditText;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        targetUnitSpinner = findViewById(R.id.targetUnitSpinner);
        sourceValueEditText = findViewById(R.id.sourceValueEditText);
        resultEditText = findViewById(R.id.resultEditText);
        convertButton = findViewById(R.id.convertButton);

        // Set up unit spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.length_units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(adapter);
        targetUnitSpinner.setAdapter(adapter);


        convertButton.setOnClickListener(view -> performConversion());
    }

    private void performConversion() {

        String sourceValueStr = sourceValueEditText.getText().toString();
        double sourceValue;
        try {
            sourceValue = Double.parseDouble(sourceValueStr);
        } catch (NumberFormatException e) {
            resultEditText.setText(R.string.invalid_input);
            return;
        }


        String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
        String targetUnit = targetUnitSpinner.getSelectedItem().toString();


        double convertedValue = UnitConverter.convert(sourceUnit, targetUnit, sourceValue);


        resultEditText.setText(String.valueOf(convertedValue));
    }
}

class UnitConverter {
    static double convert(String sourceUnit, String targetUnit, double value) {
        switch (sourceUnit) {
            case "Meter":
                switch (targetUnit) {
                    case "Centimeter":
                        return value * 100;
                    default:
                        return value;
                }
            case "Centimeter":
                switch (targetUnit) {
                    case "Meter":
                        return value / 100;
                    default:
                        return value;
                }
            default:
                return value;
        }
    }
}
