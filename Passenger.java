import java.util.*;

public abstract class Passenger implements INotification {
    protected String passengerId;
    protected String name;
    protected String email;
    protected String phone;
    protected List<Ticket> bookingHistory;
    
    public Passenger(String passengerId, String name, String email, String phone) {
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bookingHistory = new ArrayList<Ticket>();
    }
    public String getPassengerId() { return passengerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<Ticket> getBookingHistory() { return bookingHistory; }
    public abstract double calculateDiscount();
    public abstract String getPassengerType();
    
    public void addTicketToHistory(Ticket ticket) {
        bookingHistory.add(ticket);
    }
    
    public void displayBookingHistory() {
        System.out.println("\n=== Booking History for " + name + " (" + getPassengerType() + ") ===");
        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Ticket ticket : bookingHistory) {
                ticket.displayBriefInfo();
            }
        }
    }
    
    @Override
    public void sendNotification(String message) {
        System.out.println("📧 Notification to " + name + ": " + message);
    }
    
    @Override
    public String getContactInfo() {
        return "Email: " + email + ", Phone: " + phone;
    }
    
    
    
}