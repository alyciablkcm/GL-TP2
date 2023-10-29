package theatricalplays;

public class Customer {
    public String name;
    public int customerNumber;
    public int loyaltyPoints;

    public Customer(String name, int customerNumber,int loyaltyPoints) {
        this.name = name;
        this.customerNumber = customerNumber;
        this.loyaltyPoints = loyaltyPoints;
    }

    public void deductLoyaltyPoints(int pointsToDeduct) {
        if (pointsToDeduct < 0) {
            throw new IllegalArgumentException("Points to deduct must be a positive value.");
        }
        loyaltyPoints -= pointsToDeduct;
    }

}
