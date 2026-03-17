public interface IReservable {
    boolean reserveSeat(String seatNumber);
    boolean cancelReservation(String seatNumber);
    int getAvailableSeats();
}