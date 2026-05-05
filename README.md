# 🛫 Airflights Management System

A comprehensive Java-based airline reservation and flight management system that provides an interactive platform for managing flights, passengers, bookings, and tickets.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [System Architecture](#system-architecture)
- [Class Descriptions](#class-descriptions)
- [Sample Data](#sample-data)

## 🎯 Overview

The Airflights Management System is a command-line application designed to manage airline operations, including flight scheduling, passenger management, ticket bookings, cancellations, and payment processing. The system supports both domestic and international flights with multiple passenger tiers and various seat classes.

## ✨ Features

### Core Functionality
- ✅ **Flight Management**: Add, display, and manage flights (domestic & international)
- ✅ **Passenger Management**: Register and manage passengers with different tiers
- ✅ **Ticket Booking**: Reserve seats with flexible seat class and meal preferences
- ✅ **Ticket Cancellation**: Cancel bookings with automatic refund calculations
- ✅ **Payment Processing**: Secure payment processing for ticket purchases
- ✅ **Flight Status Management**: Update and track flight status in real-time
- ✅ **Seat Management**: View available seats and manage seat reservations
- ✅ **Passenger History**: Track booking history for each passenger
- ✅ **Notifications**: Send confirmation and status update notifications to passengers

### Passenger Types
- **Regular Passenger**: Standard passengers with loyalty point accumulation
- **VIP Passenger**: Premium passengers with special privileges and multiple tiers (Gold, Platinum)

### Seat Classes
- Economy
- Business
- First Class

### Meal Preferences
- Vegetarian
- Non-Vegetarian
- Vegan

### Flight Status Options
- Scheduled
- Boarding
- In-Flight
- Delayed
- Cancelled
- Landed

## 📁 Project Structure

```
Airflights-Management-System/
├── Main.java                    # Entry point with interactive CLI menu
├── AirlineReservation.java      # Core airline management system
├── Flight.java                  # Abstract base class for flights
├── DomesticFlight.java          # Domestic flight implementation
├── InternationalFlight.java     # International flight implementation
├── Passenger.java               # Abstract base class for passengers
├── RegularPassenger.java        # Regular passenger implementation
├── VIPPassenger.java            # VIP passenger implementation
├── Ticket.java                  # Ticket representation
├── PaymentProcessor.java        # Payment processing
├── DateTime.java                # Date/time utility class
├── SeatClass.java               # Seat class enumeration
├── MealType.java                # Meal preference enumeration
├── FlightStatus.java            # Flight status enumeration
├── PaymentStatus.java           # Payment status enumeration
├── IReservable.java             # Interface for reservation operations
├── IPayment.java                # Interface for payment operations
└── INotification.java           # Interface for notification operations
```

## 🔧 Technologies

- **Language**: Java
- **Build System**: No external dependencies (pure Java)
- **Design Patterns**: 
  - Abstract Factory (Flight and Passenger types)
  - Strategy Pattern (Payment and Notification)
  - Composite Pattern (Ticket management)

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt

### Installation

1. Clone the repository:
```bash
git clone https://github.com/feress-klaii/Airflights-Management-System.git
cd Airflights-Management-System
```

2. Compile all Java files:
```bash
javac *.java
```

3. Run the application:
```bash
java Main
```

## 📖 Usage

### Main Menu
When you launch the application, you'll see the following menu:

```
=== MAIN MENU ===
1. Display All Flights
2. Display All Passengers
3. Book a Ticket
4. Cancel a Ticket
5. Display Ticket Details
6. Display Flight Status
7. Display Available Seats
8. Display Passenger History
9. Update Flight Status
10. Exit
```

### Common Operations

#### Viewing Flights
- Select option **1** to view all available flights with routes, departure times, and pricing

#### Viewing Passengers
- Select option **2** to view all registered passengers with their details

#### Booking a Ticket
- Select option **3**
- Enter Passenger ID (e.g., `P001`)
- Enter Flight Number (e.g., `DA101`)
- Enter Seat Number (e.g., `12A`)
- Select Seat Class (1: Economy, 2: Business, 3: First Class)
- Select Meal Preference (1: Vegetarian, 2: Non-Vegetarian, 3: Vegan)
- System processes payment and confirms booking

#### Cancelling a Ticket
- Select option **4**
- Enter Ticket ID (e.g., `TKT1000`)
- System processes 80% refund and sends notification

#### Checking Ticket Details
- Select option **5**
- Enter Ticket ID to view complete booking information

#### Viewing Flight Status
- Select option **6**
- Enter Flight Number to view detailed flight information

#### Checking Available Seats
- Select option **7**
- Enter Flight Number to see seat availability

#### Viewing Passenger Booking History
- Select option **8**
- Enter Passenger ID to view all bookings made by the passenger

#### Updating Flight Status
- Select option **9**
- Enter Flight Number
- Select new status from available options
- All passengers on the flight receive notifications

## 🏗️ System Architecture

### Design Principles
- **Object-Oriented Programming**: Uses inheritance and polymorphism
- **Encapsulation**: Private attributes with public accessors
- **Interface-Based Design**: Defines contracts for key operations
- **Separation of Concerns**: Each class has a single responsibility

### Key Interfaces
- **IReservable**: Defines seat reservation operations
- **IPayment**: Defines payment processing
- **INotification**: Defines notification system

## 📚 Class Descriptions

### Main Classes

| Class | Description |
|-------|-------------|
| `AirlineReservation` | Core system managing flights, passengers, and bookings |
| `Flight` | Abstract base class for all flight types |
| `DomesticFlight` | Implementation for domestic flights with standard pricing |
| `InternationalFlight` | Implementation for international flights with visa/passport handling |
| `Passenger` | Abstract base class for passenger types |
| `RegularPassenger` | Standard passengers with loyalty points |
| `VIPPassenger` | Premium passengers with tier-based benefits |
| `Ticket` | Represents a booking with payment details |
| `PaymentProcessor` | Handles payment processing and validation |

### Utility Classes

| Class | Description |
|-------|-------------|
| `DateTime` | Manages date and time information |
| `SeatClass` | Enum for seat class types |
| `MealType` | Enum for meal preferences |
| `FlightStatus` | Enum for flight status states |
| `PaymentStatus` | Enum for payment states |

## 💾 Sample Data

The system initializes with sample data for testing:

### Flights
- **DA101**: New York → Los Angeles (Domestic)
- **IA202**: New York → London (International)
- **DA303**: Chicago → Miami (Domestic)

### Passengers
- **P001**: John Doe (Regular)
- **P002**: Jane Smith (VIP - Gold)
- **P003**: Bob Wilson (VIP - Platinum)

## 🎓 Learning Objectives

This project demonstrates:
- Abstract classes and interfaces in Java
- Polymorphism and inheritance
- ArrayList data structure usage
- Exception handling and input validation
- Enumeration usage
- Object-oriented design patterns
- User interface design with CLI

## 📝 Example Workflow

1. Start the application → System displays welcome message
2. View available flights (Option 1)
3. Register or view existing passengers (Option 2)
4. Book a ticket for a passenger on a selected flight (Option 3)
5. View booked ticket details (Option 5)
6. Update flight status if needed (Option 9)
7. Check seat availability (Option 7)
8. Cancel booking if required (Option 4)

## 🔮 Future Enhancements

- Database integration (SQL/NoSQL)
- GUI implementation using Swing/JavaFX
- Web API using Spring Boot
- Real-time flight tracking
- Advanced payment methods (credit card validation, etc.)
- Baggage management system
- Loyalty rewards program
- Email/SMS notifications

## 📄 License

This project is open source and available under the MIT License.

## 👤 Author

**feress-klaii** - [GitHub Profile](https://github.com/feress-klaii)

## 🤝 Contributing

Contributions are welcome! Feel free to fork this repository and submit pull requests.

## 📞 Support

For issues, questions, or suggestions, please open an issue on the GitHub repository.

---

**Last Updated**: May 2026  
**Status**: Active Development
