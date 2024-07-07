/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{   
    private int itemCode;
    private Category category;
    private Type type;
    private double salePrice;
    private double buyPrice;
    
    public Item(int itemCode, Category category, Type type, double salePrice, double buyPrice) throws InvalidCategoryException
    {
        if(!isValidTypeCategory(type, category)) {
            throw new InvalidCategoryException(category + " category is invalid for the type: " + type);
        }
        this.itemCode = itemCode;
        this.category = category;
        this.type = type;
        this.salePrice = salePrice;
        this.buyPrice = buyPrice;
    }
    
    private boolean isValidTypeCategory(Type type, Category category){
        switch(category){
            case WOMAN:
                return type == Type.DRESS || type == Type.SKIRT || type == Type.PANTS || type == Type.SHOES; 
            case MAN:
                return type == Type.SHIRT || type == Type.SWIM_TRUNKS  || type == Type.PANTS || type == Type.SHOES;
            default:
                return false;
        }
    }
    
    private boolean isValidCategory(Category category) {
        return category == Category.WOMAN || category == Category.MAN;
    }
    
    public int getItemCode(){
        return itemCode;
    }
    
    public void setItemCode(int newItemCode){
        this.itemCode = newItemCode;
    }
    
    public Category getCategory(){
        return category;
    }
    
    public void setCategory(Category category) throws InvalidCategoryException{
        if(!isValidCategory(category)){
            throw new InvalidCategoryException("Invalid Category: " + category);
        }
        this.category = category;
    }
    
    public Type getType(){
        return type;
    }
    
    public void setType(Type type){
        this.type = type;
    }
    
    public double getSalePrice(){
        return salePrice;
    }
    
    public void setSalePrice(double newSalePrice){
        this.salePrice = newSalePrice;
    }
    
    public double getBuyPrice(){
        return buyPrice;
    }
    
    public void setBuyPrice(double newBuyPrice){
        this.buyPrice = newBuyPrice;
    }
}
