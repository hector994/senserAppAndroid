package com.hmurillo.sensorproximidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button proximidad, luminosidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proximidad = findViewById(R.id.button);
        proximidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresoProximidad();
            }
        });

        luminosidad = findViewById(R.id.button2);
        luminosidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresoLuminosidad();
            }
        });
    }
    private void ingresoProximidad() {
        Intent inten1 = new Intent(MainActivity.this, MainActivity2.class);
        Toast.makeText(this,"Ir a Proximidad...",Toast.LENGTH_LONG).show();
        startActivity(inten1);
    }
    private void ingresoLuminosidad() {
        Intent inten2 = new Intent(MainActivity.this, MainActivity3.class);
        Toast.makeText(this,"Ir a Luminosidad...",Toast.LENGTH_LONG).show();
        startActivity(inten2);
    }
}
