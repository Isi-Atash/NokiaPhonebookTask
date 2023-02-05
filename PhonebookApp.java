import java.util.Scanner;

public class PhonebookApp {
    private static Phonebook phonebook = new Phonebook("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Phonebook Entry");
            System.out.println("2. Delete Phonebook Entry");
            System.out.println("3. Search Phonebook by Subscriber");
            System.out.println("4. Search Phonebook by Phone Number");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Subscriber: ");
                    String subscriber = scanner.next();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.next();
                    try {
                        phonebook.addEntry(subscriber, phoneNumber);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Subscriber: ");
                    subscriber = scanner.next();
                    System.out.print("Enter Phone Number: ");
                    phoneNumber = scanner.next();
                    try {
                        phonebook.deleteEntry(subscriber, phoneNumber);
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter Subscriber: ");
                    subscriber = scanner.next();

                    for (PhonebookDataType entry : phonebook.phonebookEntries) {
                        if (entry.GetSubscriber().equals(subscriber)) {
                            System.out.println("Subscriber: " + entry.GetSubscriber());
                            for (String number : entry.GetPhoneNumbers()) {
                                System.out.println("Phone Number: " + number);
                            }
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter Phone Number: ");
                    phoneNumber = scanner.next();
                    subscriber = phonebook.searchPhonebookByPhoneNumber(phoneNumber);
                    if (subscriber == null) {
                        System.out.println("Phone Number not found.");
                    } else {
                        System.out.println("Subscriber: " + subscriber);
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    // close scanner
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!");
                    break;
            }
        }
    }
}