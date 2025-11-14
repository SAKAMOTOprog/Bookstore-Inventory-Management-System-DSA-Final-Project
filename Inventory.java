import java.util.*;

public class Inventory {
    private static LinkedList<Book> inventory = new LinkedList<>();
    private static Queue<String> orderQueue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Bookstore Inventory Management System!");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Sort books by title");
            System.out.println("4. Search for a book by title");
            System.out.println("5. Add a customer order to the queue");
            System.out.println("6. Process the next customer order");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook(scanner);
                case 2 -> displayAllBooks();
                case 3 -> sortBooksByTitle();
                case 4 -> searchBookByTitle(scanner);
                case 5 -> addOrderToQueue(scanner);
                case 6 -> processNextOrder();
                case 7 -> {
                    System.out.println("Thank you for using the Bookstore Inventory Management System!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter book price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        inventory.add(new Book(title, author, isbn, price));
        System.out.println("Book added successfully!");
    }

    private static void displayAllBooks() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("--- All Books in Inventory ---");
            for (Book book : inventory) {
                System.out.println(book);
            }
        }
    }

    private static void sortBooksByTitle() {
        System.out.println("Sorting books by title...");
        for (int i = 0; i < inventory.size() - 1; i++) {
            for (int j = 0; j < inventory.size() - i - 1; j++) {
                if (inventory.get(j).getTitle().compareToIgnoreCase(inventory.get(j + 1).getTitle()) > 0) {
                    Book temp = inventory.get(j);
                    inventory.set(j, inventory.get(j + 1));
                    inventory.set(j + 1, temp);
                }
            }
        }
        System.out.println("Books sorted successfully!");
    }

    private static void searchBookByTitle(Scanner scanner) {
        System.out.print("Enter the title of the book to search for: ");
        String searchTitle = scanner.nextLine();
        boolean found = false;

        for (Book book : inventory) {
            if (book.getTitle().equalsIgnoreCase(searchTitle)) {
                System.out.println("Book found:\n" + book);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    private static void addOrderToQueue(Scanner scanner) {
        System.out.print("Enter the title of the book to order: ");
        String orderTitle = scanner.nextLine();
        orderQueue.add(orderTitle);
        System.out.println("Order for \"" + orderTitle + "\" has been added to the queue.");
    }

    private static void processNextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders to process.");
        } else {
            String processedOrder = orderQueue.poll();
            System.out.println("Processed order for: " + processedOrder);
        }
    }
}
