
# ICP 9

## General
- options include jalepenos, mushrooms, pepperoni
- thank you

### submitAndSendMail function

```javascript
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
```
