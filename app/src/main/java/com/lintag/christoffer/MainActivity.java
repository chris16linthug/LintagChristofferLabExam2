package com.lintag.christoffer;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText fName, lName, examOne, examTwo;
    TextView average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        examOne = findViewById(R.id.examOne);
        examTwo = findViewById(R.id.examTwo);
        average = findViewById(R.id.yourAverage);
    }

    public void saveData (View v){
        String first = fName.getText().toString();
        String last = lName.getText().toString();
        float one = Float.parseFloat(examOne.getText().toString());
        float two = Float.parseFloat(examTwo.getText().toString());
        float average = (one + two) / 2;
        SharedPreferences sp = getSharedPreferences("Data1",MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putString("firtname", first);
        writer.putString("lastname", last);
        writer.putFloat("average", average);
        writer.commit();
        Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();

    }

    public void loadData(View v){
        SharedPreferences pref = getSharedPreferences("Data1",MODE_PRIVATE);
        String myAverage = pref.getString("average", null);
        average.setText(myAverage);
    }

    public void saveExternal(View v){
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "external.txt");
        String first = fName.getText().toString();
        String last = lName.getText().toString();
        float one = Float.parseFloat(examOne.getText().toString());
        float two = Float.parseFloat(examTwo.getText().toString());
        float average = (one + two) / 2;
        String myaverage = Float.toString(average);
        FileOutputStream fos = null;
        try{
            fos =  new FileOutputStream(file);
            fos.write(first.getBytes());
            fos.write(last.getBytes());
            fos.write(myaverage.getBytes());
            Toast.makeText(this, "Data saved successfully...", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this, "Error writing in SD card...", Toast.LENGTH_LONG).show();
        }
    }

    public void loadExternal(View v){
        File folder = getExternalFilesDir("MyFolder");
        File file = new File(folder, "external.txt");

        float one = Float.parseFloat(examOne.getText().toString());
        float two = Float.parseFloat(examTwo.getText().toString());
        float myAverage = (one + two) / 2;

        average.setText(Float.toString(myAverage));
    }
}
