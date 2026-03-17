import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AirlineReservation airline = new AirlineReservation("Heritage Airlines");
        
        // Add sample flights
        airline.addFlight(new DomesticFlight("DA101", "New York", "Los Angeles", 
            new DateTime(2024, 12, 15, 8, 0), 150, 200.0));
        airline.addFlight(new InternationalFlight("IA202", "New York", "London", 
            new DateTime(2024, 12, 16, 14, 30), 200, 500.0, true));
        airline.addFlight(new DomesticFlight("DA303", "Chicago", "Miami", 
            new DateTime(2024, 12, 17, 10, 15), 100, 180.0));
        
        // Add sample passengers
        airline.addPassenger(new RegularPassenger("P001", "John Doe", "john@email.com", "1234567890"));
        airline.addPassenger(new VIPPassenger("P002", "Jane Smith", "jane@email.com", "9876543210", "Gold"));
        airline.addPassenger(new VIPPassenger("P003", "Bob Wilson", "bob@email.com", "5551234567", "Platinum"));
        
        System.out.println("=== WELCOME TO HERITAGE AIRLINES ===");
        System.out.println("System initialized with sample data.");
        System.out.println("Flights: DA101, IA202, DA303");
        System.out.println("Passengers: P001 (Regular), P002 (VIP Gold), P003 (VIP Platinum)");
        
        boolean running = true;
        while (running) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Display All Flights");
            System.out.println("2. Display All Passengers");
            System.out.println("3. Book a Ticket");
            System.out.println("4. Cancel a Ticket");
            System.out.println("5. Display Ticket Details");
            System.out.println("6. Display Flight Status");
            System.out.println("7. Display Available Seats");
            System.out.println("8. Display Passenger History");
            System.out.println("9. Update Flight Status");
            System.out.println("10. Exit");
            System.out.print("Choose an option (1-10): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        airline.displayAllFlights();
                        break;
                    case 2:
                        airline.displayAllPassengers();
                        break;
                    case 3:
                        System.out.print("Enter Passenger ID: ");
                        String passengerId = scanner.nextLine();
                        System.out.print("Enter Flight Number: ");
                        String flightNo = scanner.nextLine();
                        System.out.print("Enter Seat Number (e.g., 12A): ");
                        String seatNo = scanner.nextLine();
                        
                        Passenger passenger = airline.findPassenger(passengerId);
                        Flight flight = airline.findFlight(flightNo);
                        
                        if (passenger != null && flight != null) {
                            System.out.print("Select Seat Class (1. Economy, 2. Business, 3. First Class): ");
                            int seatChoice = scanner.nextInt();
                            scanner.nextLine();
                            SeatClass seatClass = SeatClass.ECONOMY;
                            switch(seatChoice) {
                                case 1: seatClass = SeatClass.ECONOMY; break;
                                case 2: seatClass = SeatClass.BUSINESS; break;
                                case 3: seatClass = SeatClass.FIRST_CLASS; break;
                            }
                            
                            System.out.print("Select Meal Preference (1. Vegetarian, 2. Non-Vegetarian, 3. Vegan): ");
                            int mealChoice = scanner.nextInt();
                            scanner.nextLine();
                            MealType mealType = MealType.VEGETARIAN;
                            switch(mealChoice) {
                                case 1: mealType = MealType.VEGETARIAN; break;
                                case 2: mealType = MealType.NON_VEGETARIAN; break;
                                case 3: mealType = MealType.VEGAN; break;
                            }
                            
                            Ticket ticket = airline.bookTicket(passenger, flight, seatNo, seatClass, mealType);
                            if (ticket != null) {
                                System.out.println("\n✓ Ticket booked successfully!");
                                ticket.displayTicketDetails();
                            } else {
                                System.out.println("\n✗ Booking failed! Seat might be taken or flight full.");
                            }
                        } else {
                            System.out.println("\n✗ Passenger or Flight not found!");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Ticket ID: ");
                        String ticketId = scanner.nextLine();
                        if (airline.cancelTicket(ticketId)) {
                            System.out.println("\n✓ Ticket cancelled successfully!");
                        } else {
                            System.out.println("\n✗ Ticket not found!");
                        }
                        break;
                    case 5:
                        System.out.print("Enter Ticket ID: ");
                        ticketId = scanner.nextLine();
                        airline.displayTicketDetails(ticketId);
                        break;
                    case 6:
                        System.out.print("Enter Flight Number: ");
                        flightNo = scanner.nextLine();
                        airline.displayFlightStatus(flightNo);
                        break;
                    case 7:
                        System.out.print("Enter Flight Number: ");
                        flightNo = scanner.nextLine();
                        airline.displayAvailableSeats(flightNo);
                        break;
                    case 8:
                        System.out.print("Enter Passenger ID: ");
                        passengerId = scanner.nextLine();
                        airline.displayPassengerHistory(passengerId);
                        break;
                    case 9:
                        System.out.print("Enter Flight Number: ");
                        flightNo = scanner.nextLine();
                        System.out.println("Select Status: 1. Scheduled, 2. Boarding, 3. In-Flight, 4. Delayed, 5. Cancelled, 6. Landed");
                        System.out.print("Choice: ");
                        int statusChoice = scanner.nextInt();
                        scanner.nextLine();
                        
                        FlightStatus newStatus = FlightStatus.SCHEDULED;
                        switch(statusChoice) {
                            case 1: newStatus = FlightStatus.SCHEDULED; break;
                            case 2: newStatus = FlightStatus.BOARDING; break;
                            case 3: newStatus = FlightStatus.IN_FLIGHT; break;
                            case 4: newStatus = FlightStatus.DELAYED; break;
                            case 5: newStatus = FlightStatus.CANCELLED; break;
                            case 6: newStatus = FlightStatus.LANDED; break;
                        }
                        
                        if (airline.updateFlightStatus(flightNo, newStatus)) {
                            System.out.println("\n✓ Flight status updated!");
                        } else {
                            System.out.println("\n✗ Flight not found!");
                        }
                        break;
                    case 10:
                        running = false;
                        System.out.println("\nThank you for using Heritage Airlines!");
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("\n✗ Invalid option! Please enter 1-10.");
                }
            } catch (Exception e) {
                System.out.println("\n✗ Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}