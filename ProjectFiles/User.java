public abstract class User
{
    protected String email;
    protected String password;
    
    /**
     * Constructor for objects of class Utilizadores
     */
    public User(String email, String password) throws InvalidEmailException
    {   
        if(!email.contains("@")){
            throw new InvalidEmailException("Invalid email: " + email);
        }
        this.email = email;
        this.password = password;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public abstract void showMenu();
}
