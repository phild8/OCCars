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
        mPricedEditText = (EditText) findViewById(R.id.carPriceEditText);
        mdDownPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);
        mThreeYearRadioButton = (RadioButton) findViewById(R.id.threeYearRadioButton);
        mFourYearRadioButton = (RadioButton) findViewById(R.id.fourYearRadioButton);
        mFiveYearRadioButton = (RadioButton) findViewById(R.id.fiveYearRadioButton);
    }

    private void collectCarLoanData()
    {
        mCarLoan.setPrice(Double.parseDouble((mPricedEditText.getText().toString())));
        mCarLoan.setPrice(Double.parseDouble((mdDownPaymentEditText.getText().toString())));

        if (mThreeYearRadioButton.isChecked())
            mCarLoan.setTerm(3);
        else if (mFourYearRadioButton.isChecked())
            mCarLoan.setTerm(4);
        else
            mCarLoan.setTerm(5);
    }


    protected void reportSummary(View v)
    {
        collectCarLoanData();
        String report = "Monthly Payment: " + mCarLoan.monthlyPayment() + "\n";
        // keep going, more report!!

        // Intents start new activities and can share data with them
        Intent LaunchLoanSummary = new Intent(this, LoanSummaryActivity.class);
        // Put data into the Intent for LoanSummary to recieve
        LaunchLoanSummary.putExtra("loanReport", report);
        // Start the second activity
        startActivity(LaunchLoanSummary);
    }
}
