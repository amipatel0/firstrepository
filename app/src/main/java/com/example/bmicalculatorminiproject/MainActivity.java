package com.example.bmicalculatorminiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView Bmi,bmiResult,status;
    Button calculate;
    EditText eAge,eWeight,eHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eWeight=(EditText) findViewById(R.id.edtWeight);
        eHeight=(EditText) findViewById(R.id.edtHeight);
        bmiResult=(TextView)findViewById(R.id.bmi);
        status=(TextView)findViewById(R.id.status);
        calculate=(Button)findViewById(R.id.calculate_btn);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    double weight=Double.parseDouble(String.valueOf(eWeight.getText()));
                    double height=Double.parseDouble(String.valueOf(eHeight.getText()));
                    double bmiCalc = Double.parseDouble(String.valueOf(weight / (height * height)));
                    bmiResult.setText(String.valueOf("Your BMI is:"+bmiCalc));
                if (bmiCalc < 18.5) {
                    status.setText("UnderWeight");
                } else if (bmiCalc >=18.5 && bmiCalc < 24.9) {
                    status.setText("Normal Weight");
                } else if (bmiCalc >=24.9 && bmiCalc < 30) {
                    status.setText("OverWeight");
                }
                else if (bmiCalc>=30){
                    status.setText( "Obesity");
                }
            }
        });
    }

}