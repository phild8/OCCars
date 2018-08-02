package edu.orangecoastcollege.cs273.pdavis11.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 *
 * @author Phillip Davis
 *
 */
public class PurchaseActivity extends AppCompatActivity {

   // private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

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
        mPricedEditText = (EditText) findViewById(R.id.carPriceEditText);
        mdDownPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);

        // Loan terms RadioButtons
        mThreeYearRadioButton = (RadioButton) findViewById(R.id.threeYearButton);
        mFourYearRadioButton = (RadioButton) findViewById(R.id.fourYearButton);
        mFiveYearRadioButton = (RadioButton) findViewById(R.id.fiveYearButton);
    }

    public void collectCarLoanData(View view)
    {
        mCarLoan.setPrice(Double.parseDouble((mPricedEditText.getText().toString())));
        mCarLoan.setDownPayment(Double.parseDouble((mdDownPaymentEditText.getText().toString())));

        if (mThreeYearRadioButton.isChecked())
            mCarLoan.setTerm(3);
        else if (mFourYearRadioButton.isChecked())
            mCarLoan.setTerm(4);
        else
            mCarLoan.setTerm(5);
    }


    public void reportSummary(View view)
    {
        collectCarLoanData(view);

        String report = "Monthly Payment: " + mCarLoan.monthlyPayment() + "\n\n"
            + "Car Sticker Price:  $" + mCarLoan.getPrice() + "\n"
            + "You will put down:  $" + mCarLoan.getDownPayment() + "\n"
            + "Taxed Amt:  $" + mCarLoan.taxAmount() + "\n"
            + "Your Cost:  $" + mCarLoan.totalAmount() + "\n"
            + "Borrowed Amount:  $" + mCarLoan.borrowedAmount() + "\n"
            + "Interest Amount:  $" + mCarLoan.interestAmount() + "\n";


        // Intents start new activities and can share data with them
        Intent LaunchLoanSummary = new Intent(this, LoanSummaryActivity.class);
        // Put data into the Intent for LoanSummary to receive
        LaunchLoanSummary.putExtra("loanReport", report);
        // Start the second activity
        startActivity(LaunchLoanSummary);
    }
}
