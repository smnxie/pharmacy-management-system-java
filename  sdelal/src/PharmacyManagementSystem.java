import java.util.Scanner;

public class PharmacyManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MedicineService medicineService = new MedicineService();
        UserService userService = new UserService();

        while (true) {
            System.out.println("\n=== Pharmacy Management System ===");
            System.out.println("1. Add Medicine");
            System.out.println("2. View Medicines");
            System.out.println("3. Purchase Medicine");
            System.out.println("4. Add User (Admin Only)");
            System.out.println("5. Block User (Admin Only)");
            System.out.println("6. View Users");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter medicine name: ");
                    String medName = scanner.nextLine();
                    System.out.print("Enter manufacturer: ");
                    String manufacturer = scanner.nextLine();
                    System.out.print("Enter batch number: ");
                    String batchNumber = scanner.nextLine();
                    System.out.print("Enter price: ");
                    int price = scanner.nextInt();
                    scanner.nextLine();

                    Medicine newMedicine = new Medicine(0, medName, manufacturer, batchNumber, price);
                    medicineService.addMedicine(newMedicine);
                    System.out.println("Medicine added successfully!");
                    break;

                case 2:
                    System.out.println("\nList of Available Medicines:");
                    for (Medicine medicine : medicineService.getAllMedicines()) {
                        System.out.println("Name: " + medicine.getName() +
                                ", Manufacturer: " + medicine.getManufacturer() +
                                ", Batch: " + medicine.getBatchNumber() +
                                ", Price: $" + medicine.getPrice());
                    }
                    break;

                case 3:
                    System.out.print("Enter batch number of medicine to purchase: ");
                    String batchNumToPurchase = scanner.nextLine();
                    if (medicineService.purchaseMedicine(batchNumToPurchase)) {
                        System.out.println("Medicine purchased successfully!");
                    } else {
                        System.out.println("Medicine not found or out of stock!");
                    }
                    break;

                case 4:
                    System.out.print("Enter admin password: ");
                    String adminPass = scanner.nextLine();
                    if (!adminPass.equals("admin123")) {
                        System.out.println("Incorrect password! Access denied.");
                        break;
                    }
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter user type (Admin/Customer): ");
                    String userType = scanner.nextLine();

                    User newUser = new User(0, userName, userType, false);
                    userService.addUser(newUser);
                    System.out.println("User added successfully!");
                    break;

                case 5:
                    System.out.print("Enter admin password: ");
                    String adminPass2 = scanner.nextLine();
                    if (!adminPass2.equals("admin123")) {
                        System.out.println("Incorrect password! Access denied.");
                        break;
                    }
                    System.out.print("Enter user name to block: ");
                    String blockName = scanner.nextLine();
                    if (userService.blockUser(blockName)) {
                        System.out.println("User blocked successfully!");
                    } else {
                        System.out.println("User not found!");
                    }
                    break;

                case 6:
                    System.out.println("\nList of Users:");
                    for (User user : userService.getAllUsers()) {
                        String status = user.isBlocked() ? "Blocked" : "Active";
                        System.out.println("Name: " + user.getName() +
                                ", Type: " + user.getUserType() +
                                ", Status: " + status);
                    }
                    break;

                case 7:
                    System.out.println("Exiting Pharmacy Management System...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

