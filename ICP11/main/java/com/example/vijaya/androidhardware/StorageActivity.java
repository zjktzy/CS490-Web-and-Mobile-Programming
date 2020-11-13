package com.example.vijaya.androidhardware;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {
    EditText txt_content;
    EditText contenttoDisplay;
    String FILENAME = "MyAppStorage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txt_content = (EditText) findViewById(R.id.id_txt_mycontent);
        contenttoDisplay = (EditText) findViewById(R.id.id_txt_display);
    }

    public void saveTofile(View v) throws IOException {

        // ICP Task4: Write the code to save the text

        String message = String.valueOf(txt_content.getText());

        FileOutputStream ostream = openFileOutput(FILENAME, Context.MODE_APPEND);
        ostream.write(message.getBytes());
        ostream.close();

    }

    public void retrieveFromFile(View v) throws IOException {

        // ICP Task4: Write the code to display the above saved text
        FileInputStream istream = openFileInput(FILENAME);
        InputStreamReader istream_reader = new InputStreamReader(istream);
        BufferedReader buffered_reader = new BufferedReader (istream_reader);
        String data = "";
        StringBuilder string_builder = new StringBuilder();

        //build string from input
        while ((data = buffered_reader.readLine()) != null) {
            string_builder.append(data);
        }

        istream.close();

        String output = string_builder.toString();

        //display data
        contenttoDisplay.setText(output);

        contenttoDisplay.setVisibility(View.VISIBLE);

    }
}
