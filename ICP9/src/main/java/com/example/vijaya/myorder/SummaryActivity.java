package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final String contains_mushroom = "Contains Mushroom";
    final String contains_pepperoni = "Contains Pepperoni";
    final String contains_jalepeno = "Contains Jalepeno";


    final int PIZZA_PRICE = 5;
    final int MUSHROOM_PRICE = 1;
    final int PEPPERONI_PRICE = 1;
    final int JALEPENO_PRICE = 1;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        //Variable Initialization with Intents from the MainActivity which have user data of Orders.
        String username = getIntent().getStringExtra("USER NAME");
        String summaryInfo = getIntent().getStringExtra("INGREDIENTS");

        // Printing Data using Text Views that we got through Intents
        TextView name = (TextView) findViewById(R.id.dearUserText);
        name.setText("Dear " + " " + username);
        TextView summary = (TextView) findViewById(R.id.summaryText);
        //Summary of Orders that we got through Intents
        summary.setText(summaryInfo);
        //Order Button Initialization
        Button orderButton = (Button) findViewById(R.id.returnToOrderButton);

    }
    public void returnToOrder (View view){
        //Function which return to the MainActivity that from where we can Order
        Intent returnToOrderIntent = new Intent(SummaryActivity.this,MainActivity.class);
        startActivity(returnToOrderIntent);
    }
}