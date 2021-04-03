package movieApp.command;

import movieApp.Booking;
import movieApp.Seat;
import movieApp.Showtimes;
import movieApp.storage.Database;
import movieApp.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class CancelBooking {

    private ArrayList<Booking> currentBookings = null;
    private User user = null;
    private static Scanner sc = new Scanner(System.in);

    public CancelBooking(User user, ArrayList<Booking> bookings) {
        this.user = user;
        currentBookings = bookings;
    }

    public void cancelOneBooking() {
        int thisBookingNumber = getBookingNumber();
        resetSeatStatus(currentBookings.get(thisBookingNumber-1));
        currentBookings.remove(thisBookingNumber-1);
        System.out.println("Booking Number " + thisBookingNumber + " has been removed successfully.");
        Database.updateBookings();
    }

    private void resetSeatStatus(Booking booking) {
        Showtimes showtimes = booking.getShowtimes();
        ArrayList<Seat> seats = booking.getSeats();
        for(int i=0;i<seats.size();i++){
            showtimes.setSeatStatus(seats.get(i).getRow()-1, seats.get(i).getRow()-1, false);
            System.out.println((seats.get(i).getRow()-1) + " , " + (seats.get(i).getRow()-1));
        }
    }

    private int getBookingNumber() {
        int booking_choice=0;
        while ((booking_choice < 1) || (booking_choice > currentBookings.size())) {
            System.out.println("Please indicate the booking number to cancel:");
            if (!sc.hasNextInt()) {
                System.out.println("Please input an integer.\n");
                sc.next();
                continue;
            }
            booking_choice = sc.nextInt();
            if ((booking_choice < 1) ||(booking_choice > currentBookings.size())) {
                System.out.println("Please input an integer between 1 and "+ currentBookings.size() +".\n");
            }
        }
        return booking_choice;
    }
}
