package com.himel.androiddeveloper3005.sensorone;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List list;
    SensorManager sensorManager = null;
    TextView xyz_values;
    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            xyz_values.setText("X :" + values[0] + "\nY :" + values[1] + "\nZ :"+ values[2]);


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        xyz_values = findViewById(R.id.show_xyz_value);
        list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size() > 0){
            sensorManager.registerListener(sensorEventListener, (Sensor) list.get(0),SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this, "No ACCELEROMETER Sensor Here", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        if(list.size()>0){
            sensorManager.unregisterListener(sensorEventListener);
        }
        super.onStop();
    }
}
