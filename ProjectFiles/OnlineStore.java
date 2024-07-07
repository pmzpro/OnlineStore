import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OnlineStore implements OnlineStoreInterface {
    private ArrayList<User> users;
    private ArrayList<StockItem> stockItems;
    private ArrayList<StockItem> soldItems;

    public OnlineStore() {
        users = new ArrayList<>();
        stockItems = new ArrayList<>();
        soldItems = new ArrayList<>();

        try {
            users.add(new Administrator("adm1@vestbem.com", "123", false));
            users.add(new Administrator("adm2@vestbem.com", "123", true));
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void registerClient(String email, String password, String name, Category category) {
        try {
            if (getUserByEmail(email) == null) {
                users.add(new Client(email, password, name, category));
                System.out.println("Client registered successfully.");
            } else {
                System.out.println("Email already exists.");
            }
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        }
    }

    public User login(String email, String password) {
        User user = getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new IllegalArgumentException("Invalid email or password.");
        }
    }

    public void addItem(Item item, int quantity) {
        StockItem stockItem = getStockItemByItemCode(item.getItemCode());
        if (stockItem != null) {
            try {
                stockItem.setQuantity(stockItem.getQuantity() + quantity);
            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                stockItems.add(new StockItem(item, quantity));
                System.out.println("Item added to stock successfully.");
            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void removeItem(int itemCode) {
        StockItem stockItem = getStockItemByItemCode(itemCode);
        if (stockItem != null) {
            stockItems.remove(stockItem);
            System.out.println("Item removed from stock successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    public void updateItem(int itemCode, Type type, Category category, double salePrice, double buyPrice) throws InvalidCategoryException {
        StockItem stockItem = getStockItemByItemCode(itemCode);
        if (stockItem != null) {
            Item item = stockItem.getItem();
            item.setType(type);
            item.setCategory(category);
            item.setSalePrice(salePrice);
            item.setBuyPrice(buyPrice);
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    public void displayItemsWithLessStock(int threshold) {
        System.out.println("Items with stock less than " + threshold + ":");
        for (StockItem stockItem : stockItems) {
            if (stockItem.getQuantity() < threshold) {
                System.out.println(stockItem.getItem().getItemCode() + " - " + stockItem.getItem().getType() + ": " + stockItem.getQuantity() + " units");
            }
        }
    }

    public void displayItemsWithStock() {
        System.out.println("Items in stock:");
        for (StockItem stockItem : stockItems) {
            System.out.println(stockItem.getItem().getItemCode() + " - " + stockItem.getItem().getType() + ": " + stockItem.getQuantity() + " units");
        }
    }

    public void addItemToCart(Client client, int itemCode, int quantity) {
        StockItem stockItem = getStockItemByItemCode(itemCode);
        if (stockItem != null && stockItem.getQuantity() >= quantity) {
            try {
                stockItem.setQuantity(stockItem.getQuantity() - quantity);
                client.getCart().add(new StockItem(stockItem.getItem(), quantity));
                System.out.println("Item added to cart successfully.");
            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Item not available in the requested quantity.");
        }
    }

    public void checkout(Client client) {
        double total = 0;
        for (StockItem item : soldItems) {
            if (item.getItem().getCategory() == client.getCategory()) {
                    total += item.getItem().getSalePrice() * item.getQuantity();
            }
        }
        System.out.println("Purchase completed. Total amount: $" + total);
        soldItems.clear();
    }

    public void displayItemsBought(Client client) {
        System.out.println("Items bought by " + client.getName() + ":");
        for (StockItem item : soldItems) {
            if (item.getItem().getCategory() == client.getCategory()) {
                System.out.println(item.getItem().getItemCode() + " - " + item.getItem().getType() + ": " + item.getQuantity() + " units");
            }
        }
    }

    public void displayItemsSold() {
        System.out.println("Sold items:");
        for (StockItem item : soldItems) {
            System.out.println(item.getItem().getItemCode() + " - " + item.getItem().getType() + ": " + item.getQuantity() + " units");
        }
    }

    public void exportItemsSold() {
        String fileName = "sold_items.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Sold items:");
            writer.newLine();
            for (StockItem item : soldItems) {
                String line = item.getItem().getItemCode() + " - " + item.getItem().getType() + ": " + item.getQuantity() + " units";
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void changeItem(int itemCode, double salePrice, double buyPrice) {
        StockItem stockItem = getStockItemByItemCode(itemCode);
        if (stockItem != null) {
            Item item = stockItem.getItem();
            item.setSalePrice(salePrice);
            item.setBuyPrice(buyPrice);
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    public void changeStockItem(int itemCode, int newQuantity) {
        StockItem stockItem = getStockItemByItemCode(itemCode);
        if (stockItem != null) {
            try {
                stockItem.setQuantity(newQuantity);
                System.out.println("Stock item quantity changed successfully.");
            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Item not found.");
        }
    }

    public void removeItemFromCart(Client client, int itemCode, int quantity) {
        StockItem soldItem = getSoldItemByItemCode(itemCode);
        if (soldItem != null && soldItem.getQuantity() >= quantity) {
            try {
                soldItem.setQuantity(soldItem.getQuantity() - quantity);
                if (soldItem.getQuantity() == 0) {
                    soldItems.remove(soldItem);
                }
                StockItem stockItem = getStockItemByItemCode(itemCode);
                if (stockItem != null) {
                    stockItem.setQuantity(stockItem.getQuantity() + quantity);
                } else {
                    stockItems.add(new StockItem(soldItem.getItem(), quantity));
                }
                System.out.println("Item removed from cart successfully.");
            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Item not found in the cart or not enough quantity.");
        }
    }

    private User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    private StockItem getStockItemByItemCode(int itemCode) {
        for (StockItem stockItem : stockItems) {
            if (stockItem.getItem().getItemCode() == itemCode) {
                return stockItem;
            }
        }
        return null;
    }

    private StockItem getSoldItemByItemCode(int itemCode) {
        for (StockItem soldItem : soldItems) {
            if (soldItem.getItem().getItemCode() == itemCode) {
                return soldItem;
            }
        }
        return null;
    }
}