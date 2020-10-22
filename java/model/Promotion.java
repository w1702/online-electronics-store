package model;

public class Promotion {
    private String promoCode;
    private double discountValue;

    public Promotion(String promoCode, double discountValue){
        this.promoCode = promoCode;
        this.discountValue = discountValue;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }
}
