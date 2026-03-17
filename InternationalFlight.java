public class InternationalFlight extends Flight {
    private boolean hasVisaService;
    public InternationalFlight(String flightNumber, String departure, String destination, 
                              DateTime departureTime, int totalSeats, double basePrice, 
                              boolean hasVisaService) {
        super(flightNumber, departure, destination, departureTime, totalSeats, basePrice);
        this.hasVisaService = hasVisaService;
    }
    
    @Override
public double calculateTicketPrice(SeatClass seatClass) {
    double price = basePrice;
    switch(seatClass) {
        case ECONOMY:  // ADD THIS CASE
            // Economy is base price, no multiplier needed
            break;
        case BUSINESS:
            price *= 2.2;
            break;
        case FIRST_CLASS:
            price *= 3.5;
            break;
    }
    if (hasVisaService) {
        price += 75.0; // Visa service fee
    }
    return price;
}
    
    @Override
    public String getFlightType() {
        return "INTERNATIONAL FLIGHT" + (hasVisaService ? " (Visa Service Available)" : "");
    }
    
    @Override
    protected void displayPricingInfo() {
        super.displayPricingInfo();
        if (hasVisaService) {
            System.out.println("✓ Includes visa assistance service (+$75.00)");
        }
        System.out.println("✓ In-flight entertainment included");
    }
    
    public boolean hasVisaService() {
        return hasVisaService;
    }
}