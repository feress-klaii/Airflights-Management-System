public class DomesticFlight extends Flight {
    
    public DomesticFlight(String flightNumber, String departure, String destination, 
                         DateTime departureTime, int totalSeats, double basePrice) {
        super(flightNumber, departure, destination, departureTime, totalSeats, basePrice);
    }
    
    @Override
    public double calculateTicketPrice(SeatClass seatClass) {
        double price = basePrice;
        switch(seatClass) {
            case BUSINESS:
                price *= 1.8;
                break;
            case FIRST_CLASS:
                price *= 2.5;
                break;
        }
        return price;
    }
    
    @Override
    public String getFlightType() {
        return "DOMESTIC FLIGHT";
    }
}