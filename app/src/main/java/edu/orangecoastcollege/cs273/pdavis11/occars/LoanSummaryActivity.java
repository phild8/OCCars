package edu.orangecoastcollege.cs273.pdavis11.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LoanSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        TextView mMonthlyPaymentTextView = (TextView) findViewById(R.id.MonthlyPaymentTextView);

        // Receive the intent (from PurchaseActivity)
        // Populate the TextView with data

        Intent intentFromPurchase = getIntent();
        String report = intentFromPurchase.getStringExtra("loanReport");

        // Fill your TextView with data from the report
        mMonthlyPaymentTextView.setText(report);
    }

    public void returnToPrevious(View v)
    {
        finish();
    }


}
