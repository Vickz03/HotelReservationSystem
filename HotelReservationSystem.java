import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;
    double price;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void freeRoom() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - $" + price + " - " + 
               (isAvailable ? "Available" : "Booked");
    }
}

class Reservation {
    String customerName;
    Room room;
    boolean isPaid;

    public Reservation(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
        this.isPaid = false;
    }

    public void makePayment() {
        isPaid = true;
    }

    @Override
    public String toString() {
        return "Reservation: " + customerName + " - " + room + " - Payment: " + (isPaid ? "Completed" : "Pending");
    }
}

public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        while (true) {
            System.out.println("\n1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayAvailableRooms();
                case 2 -> makeReservation();
                case 3 -> viewReservations();
                case 4 -> {
                    System.out.println("Exiting the system. Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Single", 50.0));
        rooms.add(new Room(201, "Double", 80.0));
        rooms.add(new Room(202, "Double", 80.0));
        rooms.add(new Room(301, "Suite", 150.0));
    }

    private static void displayAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation() {
        System.out.print("\nEnter your name: ");
        String customerName = scanner.nextLine();

        displayAvailableRooms();
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();

        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                room.bookRoom();
                Reservation reservation = new Reservation(customerName, room);
                reservations.add(reservation);

                System.out.print("Proceed with payment? (yes/no): ");
                String paymentChoice = scanner.next();
                if (paymentChoice.equalsIgnoreCase("yes")) {
                    reservation.makePayment();
                    System.out.println("Payment successful. Room booked!");
                } else {
                    System.out.println("Room reserved. Payment pending.");
                }
                return;
            }
        }
        System.out.println("Room not available or invalid room number.");
    }

    private static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("\nNo reservations found.");
        } else {
            System.out.println("\nReservations:");
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }
}
