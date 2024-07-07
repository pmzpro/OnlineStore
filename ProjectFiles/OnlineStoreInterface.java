public interface OnlineStoreInterface {
    void registerClient(String email, String password, String name, Category category);
    User login(String email, String password);
    void addItem(Item item, int quantity);
    void removeItem(int itemCode);
    void updateItem(int itemCode, Type type, Category category, double salePrice, double buyPrice) throws InvalidCategoryException;
    void displayItemsWithLessStock(int threshold);
    void displayItemsWithStock();
    void addItemToCart(Client client, int itemCode, int quantity);
    void checkout(Client client);
    void displayItemsBought(Client client);
    void displayItemsSold();
    void exportItemsSold();
    void changeItem(int itemCode, double salePrice, double buyPrice);
    void changeStockItem(int itemCode, int quantity);
    void removeItemFromCart(Client client, int itemCode, int quantity);
}
