package com.hmurillo.sensorproximidad;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity2 extends AppCompatActivity {
    Sensor miSensor;
    SensorManager administradorDeSensores;
    SensorEventListener disparadorEventoSensor;
    TextView lblValor;
    Button btnValor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lblValor = (TextView) findViewById(R.id.lblValorProximidad);
        btnValor = (Button) findViewById(R.id.btnValor);

        //INICIAMOS EL SENSOR

        administradorDeSensores = (SensorManager)getSystemService(SENSOR_SERVICE);
        miSensor = administradorDeSensores.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(miSensor==null){
            Toast.makeText(this, "Su dispositivo no tiene el sensor de proximidad", Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(this, "Sensor de Proximidad detectado", Toast.LENGTH_LONG).show();
        }
        disparadorEventoSensor = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                //Colocar el codigo de lo que queremos que haga nuestra app
                //cuando se acerque o se aleje el objeto del sensor
                lblValor.setText("Valor del sensor: "+ sensorEvent.values[0]);

                if(sensorEvent.values[0] < miSensor.getMaximumRange()){
                    //Condicion para determinar cuando se acerque
                    btnValor.setBackgroundColor(Color.GRAY);
                    btnValor.setText("Se ha acercado al sensor!");
                    btnValor.setTextColor(Color.RED);
                    setActivityBackgroundColor(Color.RED);
                    lblValor.setTextColor(Color.CYAN);
                }else{
                    //Que es lo que va hacer cuando se aleje
                    btnValor.setBackgroundColor(Color.GRAY);
                    btnValor.setText("Se ha elejado del sensor!");
                    btnValor.setTextColor(Color.GREEN);
                    setActivityBackgroundColor(Color.GREEN);
                    lblValor.setTextColor(Color.YELLOW);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        iniciarSensor();
    }
    public void iniciarSensor(){
        administradorDeSensores.registerListener(disparadorEventoSensor,miSensor,(2000*1000));
    }
    public void detenerSensor(){
        administradorDeSensores.unregisterListener(disparadorEventoSensor);
    }
    @Override
    protected void onPause() {
        detenerSensor();
        super.onPause();
    }
    @Override
    protected void onResume() {
        iniciarSensor();
        super.onResume();
    }
    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

}