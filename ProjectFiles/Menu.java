import java.util.Scanner;

public class Menu {
    private OnlineStore store;
    private Scanner scanner;
    
    public Menu() {
        this.store = new OnlineStore();
        this.scanner = new Scanner(System.in);
    }
    
    public Menu(OnlineStore store) {
        this.store = store;
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() throws InvalidCategoryException {
        while (true) {
            System.out.println("Welcome to the best store VestBem");
            System.out.println("Main Menu: ");
            System.out.println("1. Login");
            System.out.println("2. Register new client");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    registerClient();
                    break;
                case 3:
                    System.out.println("Good bye! Thank you for visiting us!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void login() throws InvalidCategoryException {
        System.out.println();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        try {
            User user = store.login(email, password);
            if (user instanceof Client) {
                showClientMenu((Client) user);
            } else if (user instanceof Administrator) {
                showAdminMenu((Administrator) user);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerClient() {
        System.out.println();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category (WOMAN/MAN): ");
        Category category = Category.valueOf(scanner.nextLine().toUpperCase());
        store.registerClient(email, password, name, category);
    }

    private void showClientMenu(Client client) {
        while (true) {
            System.out.println();
            client.showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    store.displayItemsWithStock();
                    break;
                case 2:
                    addItemToCart(client);
                    break;
                case 3:
                    removeItemFromCart(client);
                    break;
                case 4:
                    store.checkout(client);
                    break;
                case 5:
                    store.displayItemsBought(client);
                    break;
                case 6:
                    System.out.println("Good bye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addItemToCart(Client client) {
        System.out.println();
        System.out.print("Enter item code: ");
        int itemCode = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        store.addItemToCart(client, itemCode, quantity);
    }

    private void removeItemFromCart(Client client) {
        System.out.println();
        System.out.print("Enter item code: ");
        int itemCode = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        store.removeItemFromCart(client, itemCode, quantity);
    }

    private void showAdminMenu(Administrator admin) throws InvalidCategoryException {
        while (true) {
            System.out.println();
            admin.showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    changeItem();
                    break;
                case 5:
                    changeStockItem();
                    break;
                case 6:
                    displayItemsWithLessStock();
                    break;
                case 7:
                    store.displayItemsSold();
                    break;
                case 8:
                    if (admin.isGlobalAdmin()) {
                        store.exportItemsSold();
                        break;
                    }
                    return;
                case 9:
                    System.out.println("Good bye!");
                    return;
                    
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addItem() {
        System.out.println();
        System.out.print("Enter item code: ");
        int itemCode = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter category (WOMAN/MAN): ");
        Category category = Category.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter type (DRESS, SKIRT, SHIRT, SWIM_TRUNKS, PANTS, SHOES): ");
        Type type = Type.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter sale price: ");
        double salePrice = scanner.nextDouble();
        System.out.print("Enter buy price: ");
        double buyPrice = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        try {
            Item item = new Item(itemCode, category, type, salePrice, buyPrice);
            store.addItem(item, quantity);
        } catch (InvalidCategoryException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeItem() {
        System.out.println();
        System.out.print("Enter item code to remove: ");
        int itemCode = scanner.nextInt();
        store.removeItem(itemCode);
    }

    private void updateItem() throws InvalidCategoryException {
        System.out.println();
        System.out.print("Enter item code to update: ");
        int itemCode = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new category (WOMAN/MAN): ");
        Category category = Category.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter new type (DRESS, SKIRT, SHIRT, SWIM_TRUNKS, PANTS, SHOES): ");
        Type type = Type.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter new sale price: ");
        double salePrice = scanner.nextDouble();
        System.out.print("Enter new buy price: ");
        double buyPrice = scanner.nextDouble();
        store.updateItem(itemCode, type, category, salePrice, buyPrice);
    }

    private void displayItemsWithLessStock() {
        System.out.print("Enter stock threshold: ");
        int threshold = scanner.nextInt();
        store.displayItemsWithLessStock(threshold);
    }

    private void changeItem() {
    System.out.println();
    System.out.print("Enter item code to change: ");
    int itemCode = scanner.nextInt();
    System.out.print("Enter new sale price: ");
    double salePrice = scanner.nextDouble();
    System.out.print("Enter new buy price: ");
    double buyPrice = scanner.nextDouble();
    store.changeItem(itemCode, salePrice, buyPrice);
    }

    private void changeStockItem() {
        System.out.println();
        System.out.print("Enter item code to change stock quantity: ");
        int itemCode = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();
        store.changeStockItem(itemCode, newQuantity);
    }
}