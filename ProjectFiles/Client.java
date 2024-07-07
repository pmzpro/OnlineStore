import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Cliente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Client extends User
{
    private String name;
    private Category category;
    private List<StockItem> cart;
    
    public Client(String email, String password, String name, Category category) throws InvalidEmailException
    {
        super(email, password);
        this.name = name;
        this.category = category;
        this.cart = new ArrayList<>();
    }
    
    public List<StockItem> getCart() {
        return cart;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Category getCategory(){
        return category;
    }
    
    public void setCategory(Category category){
        this.category = category;
    }
    
     public void showMenu() {
        System.out.println("Client Menu: ");
        System.out.println("1. Search items");
        System.out.println("2. Add to cart");
        System.out.println("3. Remove from cart");
        System.out.println("4. Finalize purchase");
        System.out.println("5. Show purchase history");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");
    }
}
