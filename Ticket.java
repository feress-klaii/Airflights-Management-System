public class Ticket implements IPayment {
    private String ticketId;
    private Passenger passenger;
    private Flight flight;
    private String seatNumber;
    private SeatClass seatClass;
    private double price;
    private PaymentStatus paymentStatus;
    private String transactionId;
    private MealType mealPreference;
    private boolean isCancelled;
    
    public Ticket(String ticketId, Passenger passenger, Flight flight, 
                  String seatNumber, SeatClass seatClass, MealType mealPreference) {
        this.ticketId = ticketId;
        this.passenger = passenger;
        this.flight = flight;
        this.seatNumber = seatNumber.toUpperCase();
        this.seatClass = seatClass;
        this.mealPreference = mealPreference;
        this.paymentStatus = PaymentStatus.PENDING;
        this.transactionId = "TXN" + System.currentTimeMillis();
        this.isCancelled = false;
        
        calculatePrice();
    }
    // Getters
    public String getTicketId() { return ticketId; }
    public Passenger getPassenger() { return passenger; }
    public Flight getFlight() { return flight; }
    public String getSeatNumber() { return seatNumber; }
    public SeatClass getSeatClass() { return seatClass; }
    public double getPrice() { return price; }
    public boolean isCancelled() { return isCancelled; }
}
    
    private void calculatePrice() {
        double basePrice = flight.calculateTicketPrice(seatClass);
        double discount = passenger.calculateDiscount();
        this.price = basePrice * (1 - discount);
    }
    
    @Override
    public boolean processPayment(double amount) {
        if (Math.abs(amount - price) < 0.01) {
            paymentStatus = PaymentStatus.COMPLETED;
            System.out.println("💳 Payment of $" + String.format("%.2f", amount) + " processed successfully!");
            return true;
        }
        paymentStatus = PaymentStatus.FAILED;
        System.out.println("✗ Payment failed! Amount mismatch.");
        return false;
    }
    
    @Override
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    
    @Override
    public String getTransactionId() {
        return transactionId;
    }
    
    public void cancel() {
        this.isCancelled = true;
        this.paymentStatus = PaymentStatus.REFUNDED;
    }
    
    public void displayTicketDetails() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            TICKET DETAILS");
        System.out.println("=".repeat(50));
        System.out.println("Ticket ID:      " + ticketId);
        System.out.println("Passenger:      " + passenger.getName() + " (" + passenger.getPassengerType() + ")");
        System.out.println("Flight:         " + flight.getFlightNumber() + " - " + flight.getFlightType());
        System.out.println("Route:          " + flight.getDeparture() + " → " + flight.getDestination());
        System.out.println("Departure:      " + flight.getDepartureTime());
        System.out.println("Seat:           " + seatNumber + " (" + seatClass + " Class)");
        System.out.println("Meal:           " + mealPreference);
        System.out.println("-".repeat(50));
        System.out.println("Base Price:     $" + String.format("%.2f", flight.calculateTicketPrice(seatClass)));
        System.out.println("Discount:       " + String.format("%.1f", passenger.calculateDiscount() * 100) + "%");
        System.out.println("Final Price:    $" + String.format("%.2f", price));
        System.out.println("Payment Status: " + paymentStatus);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Status:         " + (isCancelled ? "CANCELLED" : "CONFIRMED"));
        System.out.println("=".repeat(50));
    }
    
    public void displayBriefInfo() {
        System.out.printf("%-10s | %-8s | %-15s → %-15s | $%-8.2f | %s%n",
            ticketId, flight.getFlightNumber(), 
            flight.getDeparture(), flight.getDestination(),
            price, seatClass);
    }
    
    