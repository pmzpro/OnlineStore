import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();

        try {
            // Teste 1: Criação de Itens
            System.out.println("Teste 1: Criação de Itens");
            items.add(new Item(1, Category.WOMAN, Type.DRESS, 50.0, 30.0));
            items.add(new Item(2, Category.WOMAN, Type.SKIRT, 40.0, 20.0));
            items.add(new Item(3, Category.MAN, Type.PANTS, 60.0, 35.0));
            items.add(new Item(4, Category.WOMAN, Type.SHOES, 80.0, 45.0));
            items.add(new Item(5, Category.MAN, Type.SHIRT, 70.0, 40.0));
            items.add(new Item(6, Category.MAN, Type.SWIM_TRUNKS, 25.0, 15.0));
            for (Item item : items) {
                printItemDetails(item);
            }

            // Teste 2: Criação de Administradores e Clientes
            System.out.println("Teste 2: Criação de Administradores e Clientes");
            Administrator admin1 = new Administrator("adm1@vestbem.com", "123", false);
            Administrator admin2 = new Administrator("adm2@vestbem.com", "123", true);
            Client client1 = new Client("jose@example.com", "123", "José Miguel", Category.MAN);
            Client client2 = new Client("maria@example.com", "123", "Maria Silva", Category.WOMAN);
            System.out.println("Administradores e clientes criados com sucesso.");

            // Teste 3: Email Inválido
            System.out.println("Teste 3: Teste de Email Inválido");
            try {
                Client invalidClient = new Client("invalidEmail", "123", "Invalid User", Category.MAN);
            } catch (InvalidEmailException e) {
                System.out.println("Email inválido detectado: " + e.getMessage());
            }
        } catch (InvalidCategoryException | InvalidEmailException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void printItemDetails(Item item) {
        System.out.println("Item criado: ");
        System.out.println("Código do Item: " + item.getItemCode());
        System.out.println("Categoria: " + item.getCategory());
        System.out.println("Tipo: " + item.getType());
        System.out.println("Preço de Venda: " + item.getSalePrice());
        System.out.println("Preço de Compra: " + item.getBuyPrice());
        System.out.println();
    }
}
