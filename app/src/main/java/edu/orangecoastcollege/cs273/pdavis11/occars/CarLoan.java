package edu.orangecoastcollege.cs273.pdavis11.occars;

/**
 * Created by pdavis11 on 9/14/2017.
 */

public class CarLoan {

    private static final double STATE_TAX = 0.08;
    private double mPrice;
    private double downPayment;
    private int mTerm;

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public int getTerm() {
        return mTerm;
    }

    public void setTerm(int term) {
        mTerm = term;
    }

    public double taxAmount()
    {
        return STATE_TAX * mPrice;
    }

    public double totalAmount()
    {
        return STATE_TAX + taxAmount();
    }

    public double borrowedAmount()
    {
        return mPrice - downPayment;
    }

    public double interestAmount()
    {
        double interestRate;
        switch (mTerm)
        {
            case 3:
                interestRate = 0.0462;
                break;
            case 4:
                interestRate = 0.0419;
                break;
            case 5:
                interestRate = 0.0416;
                break;
            default:
                // Should never be used
                interestRate = 0.10;
                break;
        }

        return borrowedAmount() * interestRate;
    }

    public double monthlyPayment()
    {
        return (borrowedAmount() + interestAmount()) / (mTerm * 12);
    }

}
