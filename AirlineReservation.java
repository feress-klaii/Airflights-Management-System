import java.util.*;

public class AirlineReservation {
    private String airlineName;
    private List<Flight> flights;
    private List<Passenger> passengers;
    private List<Ticket> tickets;
    private int ticketCounter;
    
    public AirlineReservation(String airlineName) {
        this.airlineName = airlineName;
        this.flights = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.ticketCounter = 1000;
    }
    
    public void addFlight(Flight flight) {
        flights.add(flight);
    }
    
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }
    
    public Ticket bookTicket(Passenger passenger, Flight flight, String seatNumber, 
                            SeatClass seatClass, MealType mealPreference) {
        if (flight.reserveSeat(seatNumber)) {
            String ticketId = "TKT" + ticketCounter++;
            Ticket ticket = new Ticket(ticketId, passenger, flight, seatNumber, seatClass, mealPreference);
            
            // Process payment
            if (ticket.processPayment(ticket.getPrice())) {
                tickets.add(ticket);
                passenger.addTicketToHistory(ticket);
                
                // Add loyalty points for regular passengers
                if (passenger instanceof RegularPassenger) {
                    ((RegularPassenger) passenger).addLoyaltyPoints(100);
                }
                
                passenger.sendNotification("🎫 Your ticket " + ticketId + " has been confirmed!");
                passenger.sendNotification("Flight: " + flight.getFlightNumber() + 
                                         " | Seat: " + seatNumber + 
                                         " | Departure: " + flight.getDepartureTime());
                return ticket;
            } else {
                flight.cancelReservation(seatNumber);
            }
        }
        return null;
    }
    
    public boolean cancelTicket(String ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equals(ticketId)) {
                Flight flight = ticket.getFlight();
                flight.cancelReservation(ticket.getSeatNumber());
                ticket.cancel();
                ticket.getPassenger().sendNotification("❌ Your ticket " + ticketId + " has been cancelled.");
                ticket.getPassenger().sendNotification("Refund of $" + 
                    String.format("%.2f", ticket.getPrice() * 0.8) + " will be processed within 7 days.");
                return true;
            }
        }
        return false;
    }
    
    public void displayAllFlights() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                         ALL FLIGHTS");
        System.out.println("=".repeat(80));
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
        } else {
            for (Flight flight : flights) {
                flight.displayFlightInfo();
                System.out.println();
            }
        }
        System.out.println("=".repeat(80));
    }
    
    public void displayAllPassengers() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                       ALL PASSENGERS");
        System.out.println("=".repeat(70));
        if (passengers.isEmpty()) {
            System.out.println("No passengers registered.");
        } else {
            System.out.printf("%-10s | %-20s | %-25s | %-15s%n", 
                "ID", "Name", "Email", "Type");
            System.out.println("-".repeat(70));
            for (Passenger passenger : passengers) {
                System.out.printf("%-10s | %-20s | %-25s | %-15s%n",
                    passenger.getPassengerId(),
                    passenger.getName(),
                    passenger.getEmail(),
                    passenger.getPassengerType());
            }
        }
        System.out.println("=".repeat(70));
    }
    
    public void displayTicketDetails(String ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equals(ticketId)) {
                ticket.displayTicketDetails();
                return;
            }
        }
        System.out.println("\n✗ Ticket not found!");
    }
    
    public void displayFlightStatus(String flightNumber) {
        Flight flight = findFlight(flightNumber);
        if (flight != null) {
            flight.displayFlightInfo();
        } else {
            System.out.println("\n✗ Flight not found!");
        }
    }
    
    public void displayAvailableSeats(String flightNumber) {
        Flight flight = findFlight(flightNumber);
        if (flight != null) {
            int available = flight.getAvailableSeats();
            System.out.println("\n=== Available Seats for Flight " + flightNumber + " ===");
            System.out.println("Available: " + available + " / " + (available + flight.getBookedSeats().size()));
            if (available > 0) {
                System.out.println("✓ Seats available for booking!");
            } else {
                System.out.println("✗ Flight is fully booked!");
            }
        } else {
            System.out.println("\n✗ Flight not found!");
        }
    }
    
    public void displayPassengerHistory(String passengerId) {
        Passenger passenger = findPassenger(passengerId);
        if (passenger != null) {
            passenger.displayBookingHistory();
        } else {
            System.out.println("\n✗ Passenger not found!");
        }
    }
    
    public boolean updateFlightStatus(String flightNumber, FlightStatus newStatus) {
        Flight flight = findFlight(flightNumber);
        if (flight != null) {
            flight.setStatus(newStatus);
            
            // Notify all passengers with tickets on this flight
            for (Ticket ticket : tickets) {
                if (ticket.getFlight().getFlightNumber().equals(flightNumber) && !ticket.isCancelled()) {
                    ticket.getPassenger().sendNotification(
                        "Flight " + flightNumber + " status updated to: " + newStatus);
                }
            }
            return true;
        }
        return false;
    }
    
    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
    
    public Passenger findPassenger(String passengerId) {
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerId().equalsIgnoreCase(passengerId)) {
                return passenger;
            }
        }
        return null;
    }
    
    public String getAirlineName() {
        return airlineName;
    }
}