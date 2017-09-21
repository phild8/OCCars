package edu.orangecoastcollege.cs273.pdavis11.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
        // TextView/EditView
        mPricedEditText = (EditText) findViewById(R.id.carPriceEditText);
        mdDownPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);

        // Loan terms RadioButtons
        mThreeYearRadioButton = (RadioButton) findViewById(R.id.threeYearButton);
        mFourYearRadioButton = (RadioButton) findViewById(R.id.fourYearButton);
        mFiveYearRadioButton = (RadioButton) findViewById(R.id.fiveYearButton);

        // Price and down payment Text Watchers
        mPricedEditText.addTextChangedListener(carPriceTextWatcher);
        mdDownPaymentEditText.addTextChangedListener(downPaymentTextWatcher);

    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.threeYearButton:
                if (checked)
                    mThreeYearRadioButton.isChecked();
                    break;
            case R.id.fourYearButton:
                if (checked)
                    mFourYearRadioButton.isChecked();
                    break;
            case R.id.fiveYearButton:
                if (checked)
                    mFiveYearRadioButton.isChecked();
                    break;
        }
    }

    private final TextWatcher carPriceTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence text, int i, int i1, int i2) {
            try{
                double carPrice = Double.parseDouble(text.toString());

                mCarLoan.setPrice(carPrice);


            } catch (NumberFormatException e){
                mCarLoan.setPrice(0);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    private final TextWatcher downPaymentTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence text, int i, int i1, int i2) {
            try {
                double downPayment = Double.parseDouble(text.toString());

                mCarLoan.setDownPayment(downPayment);
            } catch (NumberFormatException e) {
                mCarLoan.setPrice(0);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

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

        String report = "Monthly Payment: " + mCarLoan.monthlyPayment() + "\n\n";
        report += "Car Sticker Price:  $" + mCarLoan.getPrice() + "\n";
        report += "You will put down:  $" + mCarLoan.getDownPayment() + "\n";
        report += "Taxed Amt:  $" + mCarLoan.taxAmount() + "\n";
        report += "Your Cost:  $" + mCarLoan.totalAmount() + "\n";
        report += "Borrowed Amount:  $" + mCarLoan.borrowedAmount() + "\n";
        report += "Interest Amount:  $" + mCarLoan.interestAmount() + "\n";


        // Intents start new activities and can share data with them
        Intent LaunchLoanSummary = new Intent(this, LoanSummaryActivity.class);
        // Put data into the Intent for LoanSummary to receive
        LaunchLoanSummary.putExtra("loanReport", report);
        // Start the second activity
        startActivity(LaunchLoanSummary);
    }
}
