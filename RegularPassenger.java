public class RegularPassenger extends Passenger {
    private int loyaltyPoints;
    
    public RegularPassenger(String passengerId, String name, String email, String phone) {
        super(passengerId, name, email, phone);
        this.loyaltyPoints = 0;
    }
    
    @Override
    public double calculateDiscount() {
        // Base 5% discount + 0.5% for every 1000 loyalty points
        return 0.05 + (loyaltyPoints / 1000.0 * 0.005);
    }
    
    @Override
    public String getPassengerType() {
        return "Regular Passenger" + (loyaltyPoints > 0 ? " (" + loyaltyPoints + " points)" : "");
    }
    
    public void addLoyaltyPoints(int points) {
        loyaltyPoints += points;
        System.out.println("🎯 " + name + " earned " + points + " loyalty points. Total: " + loyaltyPoints);
    }
    
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}