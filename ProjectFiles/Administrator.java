
/**
 * Write a description of class Administrador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Administrator extends User
{
    private boolean isGlobalAdmin;
    
    public Administrator(String email, String password, boolean isGlobalAdmin) throws InvalidEmailException
    {
        super(email, password);
        this.isGlobalAdmin = isGlobalAdmin;
    }
        
    public boolean isGlobalAdmin(){
        return isGlobalAdmin;
    }
    
    public void setGlobalAdmin(boolean admin){
        isGlobalAdmin = admin;
    }
    
    @Override
    public void showMenu() {
        System.out.println("Administrator Menu: ");
        System.out.println("1. Add item");
        System.out.println("2. Remove item");
        System.out.println("3. Update item");
        System.out.println("4. Change item details");
        System.out.println("5. Change item stock");
        System.out.println("6. Show items with low stock");
        System.out.println("7. Show items sold");
        if (isGlobalAdmin) {
            System.out.println("8. Export sales data");
        }
        System.out.println((isGlobalAdmin ? "9" : "8") + ". Logout");
        System.out.print("Choose an option: ");
    }
}
