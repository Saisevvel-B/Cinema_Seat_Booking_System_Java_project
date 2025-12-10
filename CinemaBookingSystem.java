import java.util.ArrayList;
import java.util.Scanner;

public class CinemaBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> bookedSeats = new ArrayList<Integer>();

        int totalSeats = 20;
        double pricePerTicket = 120;
        int offerLimit = 5;
        int totalBooked = 0;
        double totalEarnings = 0;

        int choice;
        do {
            System.out.println("\n-- CINEMA SEAT BOOKING SYSTEM --");
            System.out.println("1. View Available Seats");
            System.out.println("2. Book Seats");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Seats:");
                    for (int i = 1; i <= totalSeats; i++) {
                        if (!bookedSeats.contains(i)) {
                            System.out.print(i + " ");
                        }
                    }
                    System.out.println();
                    break;

                case 2:
                    System.out.print("How many seats do you want to book? ");
                    int count = sc.nextInt();

                    ArrayList<Integer> seatsToBook = new ArrayList<Integer>();
                    for (int i = 0; i < count; i++) {
                        System.out.print("Enter seat number " + (i + 1) + ": ");
                        int seatNum = sc.nextInt();

                        while (seatNum < 1 || seatNum > totalSeats || bookedSeats.contains(seatNum)) {
                            if (seatNum < 1 || seatNum > totalSeats) {
                                System.out.print("Invalid seat number! Enter again: ");
                            } else {
                                System.out.print("Seat " + seatNum + " already booked! Choose another seat: ");
                            }
                            seatNum = sc.nextInt();
                        }

                        bookedSeats.add(seatNum);
                        seatsToBook.add(seatNum);
                        totalBooked++;
                    }

                    // Booking summary
                    System.out.println("\nSeats " + seatsToBook + " booked successfully!");
                    System.out.println("Price per ticket: " + pricePerTicket);

                    double totalAmount = seatsToBook.size() * pricePerTicket;
                    double discount = 0;

                    int remainingOffersBefore = offerLimit - (totalBooked - seatsToBook.size());
                    int offerSeats = Math.min(seatsToBook.size(), Math.max(0, remainingOffersBefore));

                    if (offerSeats > 0) {
                        discount = offerSeats * pricePerTicket * 0.1;
                        if (offerSeats == seatsToBook.size()) {
                            System.out.println("Congratulations! You got 10% off on all your tickets!");
                        } else {
                            System.out.println("Congratulations! Out of " + seatsToBook.size() +
                                    " tickets, first " + offerSeats + " got 10% offer!");
                        }
                    }

                    totalAmount -= discount;
                    totalEarnings += totalAmount;

                    System.out.println("Total Amount to Pay: " + totalAmount);
                    System.out.println("Thank you for booking! Enjoy your movie!");
                    break;

                case 3:
                    System.out.print("Enter seat number to cancel: ");
                    int seatNum = sc.nextInt();
                    if (bookedSeats.contains(seatNum)) {
                        bookedSeats.remove((Integer) seatNum);
                        totalBooked--;
                        System.out.println("Seat " + seatNum + " cancelled successfully.");
                    } else {
                        System.out.println("Seat " + seatNum + " is not booked yet!");
                    }
                    break;

                case 4:
                    System.out.println(" Thank you for using the Cinema Booking System!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 4);
    }
}