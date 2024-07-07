public class StockItem {
    private int quantity;
    private Item item;
    
    public StockItem(Item item, int quantity) throws InvalidQuantityException {
        if(quantity < 0){
            throw new InvalidQuantityException("The quantity can't be less than 0");
        }
        this.item = item;
        this.quantity = quantity;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity) throws InvalidQuantityException{
    if(quantity < 0){
            throw new InvalidQuantityException("The quantity can't be less than 0");
        }    
    this.quantity = quantity;
    }
    
    public Item getItem(){
        return item;
    }
    
     @Override
    public String toString() {
        return item.getItemCode() + " - " + item.getType() + ": " + quantity + " units";
    }
}
