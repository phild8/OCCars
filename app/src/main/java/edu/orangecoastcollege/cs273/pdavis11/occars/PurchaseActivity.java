package edu.orangecoastcollege.cs273.pdavis11.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import java.text.NumberFormat;

/**
 *
 * @author Phillip Davis
 *
 */
public class PurchaseActivity extends AppCompatActivity {

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

    // Connections to VIEW
    private EditText mPricedEditText;
    private EditText mdDownPaymentEditText;
    private RadioButton mThreeYearRadioButton;
    private RadioButton mFourYearRadioButton;
    private RadioButton mFiveYearRadioButton;


    // Connections to the MODEL
    private CarLoan mCarLoan = new CarLoan();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        // Use findViewById to connect controller to each view
        // TextView/EditView
        mPricedEditText = findViewById(R.id.carPriceEditText);
        mdDownPaymentEditText = findViewById(R.id.downPaymentEditText);

        // Loan terms RadioButtons
        mThreeYearRadioButton = findViewById(R.id.threeYearButton);
        mFourYearRadioButton = findViewById(R.id.fourYearButton);
        mFiveYearRadioButton = findViewById(R.id.fiveYearButton);
    }
    //Toast.makeText(PurchaseActivity.this, "Please enter a 'price' and a 'down payment' amount.", Toast.LENGTH_LONG).show();

    public void collectCarLoanData(View view) {

        if (TextUtils.isEmpty(mPricedEditText.getText().toString()) && TextUtils.isEmpty(mdDownPaymentEditText.getText().toString()))
            Toast.makeText(PurchaseActivity.this, "Please enter a 'price' and a 'down payment' amount.", Toast.LENGTH_LONG).show();
        else if (TextUtils.isEmpty(mPricedEditText.getText().toString()))
            Toast.makeText(PurchaseActivity.this, "Please enter a 'price' amount.", Toast.LENGTH_LONG).show();
        else if (TextUtils.isEmpty(mdDownPaymentEditText.getText().toString()))
            Toast.makeText(PurchaseActivity.this, "Please enter a 'down payment' amount.", Toast.LENGTH_LONG).show();
        else if (!mThreeYearRadioButton.isChecked() && !mFourYearRadioButton.isChecked() && !mFiveYearRadioButton.isChecked())
            Toast.makeText(PurchaseActivity.this, "Please select a 'term' amount.", Toast.LENGTH_LONG).show();
        else {
            mCarLoan.setPrice(Double.parseDouble((mPricedEditText.getText().toString())));
            mCarLoan.setDownPayment(Double.parseDouble((mdDownPaymentEditText.getText().toString())));

            if (mThreeYearRadioButton.isChecked())
                mCarLoan.setTerm(3);
            else if (mFourYearRadioButton.isChecked())
                mCarLoan.setTerm(4);
            else if (mFiveYearRadioButton.isChecked())
                mCarLoan.setTerm(5);
        }
    }


    public void reportSummary(View view) {

        collectCarLoanData(view);

        String report = "Monthly Payment:  " + currency.format(mCarLoan.monthlyPayment()) + "\n\n"
                      + "Car Sticker Price:  " + currency.format(mCarLoan.getPrice()) + "\n"
                      + "You will put down:  " + currency.format(mCarLoan.getDownPayment()) + "\n"
                      + "Taxed Amt:  " + currency.format(mCarLoan.taxAmount()) + "\n"
                      + "Your Cost:  " + currency.format(mCarLoan.totalAmount()) + "\n"
                      + "Borrowed Amount:  " + currency.format(mCarLoan.borrowedAmount()) + "\n"
                      + "Interest Amount:  " + currency.format(mCarLoan.interestAmount()) + "\n";


        // Intents start new activities and can share data with them
        Intent LaunchLoanSummary = new Intent(this, LoanSummaryActivity.class);
        // Put data into the Intent for LoanSummary to receive
        LaunchLoanSummary.putExtra("loanReport", report);
        // Start the second activity
        startActivity(LaunchLoanSummary);
    }
}