import java.util.*;

public abstract class Flight implements IReservable {
    protected String flightNumber;
    protected String departure;
    protected String destination;
    protected DateTime departureTime;
    protected int totalSeats;
    protected int bookedSeats;
    protected FlightStatus status;
    protected List<String> bookedSeatNumbers;
    protected double basePrice;
    
    public Flight(String flightNumber, String departure, String destination, 
                 DateTime departureTime, int totalSeats, double basePrice) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
        this.status = FlightStatus.SCHEDULED;
        this.bookedSeatNumbers = new ArrayList<>();
        this.basePrice = basePrice;
    }
    
    public abstract double calculateTicketPrice(SeatClass seatClass);
    public abstract String getFlightType();
    
    @Override
    public boolean reserveSeat(String seatNumber) {
        if (bookedSeats < totalSeats && !bookedSeatNumbers.contains(seatNumber.toUpperCase())) {
            bookedSeats++;
            bookedSeatNumbers.add(seatNumber.toUpperCase());
            return true;
        }
        return false;
    }
    
    @Override
    public boolean cancelReservation(String seatNumber) {
        if (bookedSeatNumbers.contains(seatNumber.toUpperCase())) {
            bookedSeats--;
            bookedSeatNumbers.remove(seatNumber.toUpperCase());
            return true;
        }
        return false;
    }
    
    @Override
    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }
    
    public void setStatus(FlightStatus status) {
        this.status = status;
    }
    
    public void displayFlightInfo() {
        System.out.println("\n=== FLIGHT INFORMATION ===");
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Type: " + getFlightType());
        System.out.println("Route: " + departure + " → " + destination);
        System.out.println("Departure: " + departureTime);
        System.out.println("Status: " + status);
        System.out.println("Seats Available: " + getAvailableSeats() + "/" + totalSeats);
        System.out.println("Base Price: $" + String.format("%.2f", basePrice));
        displayPricingInfo();
    }
    
    protected void displayPricingInfo() {
        System.out.println("\nPricing Information:");
        System.out.println("Economy Class: $" + String.format("%.2f", calculateTicketPrice(SeatClass.ECONOMY)));
        System.out.println("Business Class: $" + String.format("%.2f", calculateTicketPrice(SeatClass.BUSINESS)));
        System.out.println("First Class: $" + String.format("%.2f", calculateTicketPrice(SeatClass.FIRST_CLASS)));
    }
    
    // Getters
    public String getFlightNumber() { return flightNumber; }
    public String getDeparture() { return departure; }
    public String getDestination() { return destination; }
    public DateTime getDepartureTime() { return departureTime; }
    public FlightStatus getStatus() { return status; }
    public double getBasePrice() { return basePrice; }
    public List<String> getBookedSeats() { return bookedSeatNumbers; }
}