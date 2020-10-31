package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitAndSendMail(String name, View view) {
        // Write the relevant code for triggering email
        String[] SEND_TO = {"zjktzy@umsystem.edu"};
        String[] SEND_FROM = {"zjktzy@umsystem.edu"};

        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        mailIntent.setData(Uri.parse("mailto:"));
        mailIntent.setType("text/plain");
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Order");

        Intent summaryIntent = new Intent(MainActivity.this, SummaryActivity.class);

        // check if whipped Pepperoni is selected
        CheckBox mushroom = (CheckBox) findViewById(R.id.mushroom_checked);
        boolean hasMushroom = mushroom.isChecked();

        // check if Sausage is selected
        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni_checked);
        boolean hasPepperoni = pepperoni.isChecked();

        // check if Ham is selected
        CheckBox jalepeno = (CheckBox) findViewById(R.id.jalepeno_checked);
        boolean hasJalepeno = jalepeno.isChecked();

        float price = calculatePrice(hasMushroom, hasPepperoni, hasJalepeno);
        mailIntent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(name, hasMushroom, hasPepperoni, hasJalepeno, price));

        startActivity(Intent.createChooser(mailIntent, "Sending"));
        finish();

    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasMushroom, boolean hasPepperoni, boolean hasJalepeno, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                 contains_mushroom + boolToString(hasMushroom) + "\n" +
                contains_pepperoni + boolToString(hasPepperoni) + "\n" +
                contains_jalepeno + boolToString(hasJalepeno) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasMushroom, boolean hasPepperoni, boolean hasJalepeno) {
        int basePrice = PIZZA_PRICE;
        if (hasMushroom) {
            basePrice += MUSHROOM_PRICE;
        }
        if (hasPepperoni) {
            basePrice += PEPPERONI_PRICE;
        }
        if (hasJalepeno) {
            basePrice += JALEPENO_PRICE;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}