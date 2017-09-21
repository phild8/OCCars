package edu.orangecoastcollege.cs273.pdavis11.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LoanSummaryActivity extends AppCompatActivity {

    private TextView mMontlyPaymentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        mMontlyPaymentTextView = (TextView) findViewById(R.id.MonthlyPaymentTextView);

        // Receive the intent (from PurchaseActivity)
        // Populate the TextView with data

        Intent intentFromPurchase = getIntent();
        String report = intentFromPurchase.getStringExtra("loanReport");

        // Fill your TextView with data from the report
        mMontlyPaymentTextView.setText(report);
    }

}
