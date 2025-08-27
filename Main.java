
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManage obj = new ProductManage();

        while (true) {
            System.out.println("------------Point Of Sale (POS) System------------------");
            System.out.println("Click 1 for Product Operations");
            System.out.println("Click 2 for Sales Operation");
            System.out.println("Click 0 for Exit");
            System.out.println("Enter your choice:");
            String firstChoice = scanner.nextLine();
            switch (firstChoice) {
                case "1":
                    productMenubar(scanner, obj);
                    break;
                case "2":
                    saleMenubar(scanner, obj);
                    break;
                case "0":
                    System.out.println("Exiting------");
                    return;
                default:
                    System.out.println("Invalid input");


            }
        }
    }
    public static  void productMenubar(Scanner scanner, ProductManage obj) {
        while (true) {
            System.out.println("\n----- Product Operations -----");
            System.out.println("1. Add product");
            System.out.println("2. Update product");
            System.out.println("3. Delete product");
            System.out.println("4. View all products");
            System.out.println("5. View product by ID");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            String ch = scanner.nextLine();

            switch (ch) {
                case "1":
                    System.out.println("Enter Product Id:");
                    String addId = scanner.nextLine();
                    System.out.println("Enter Product Name:");
                    String addName = scanner.nextLine();
                    System.out.println("Enter Product Price:");
                    double addPrice = scanner.nextDouble();
                    scanner.nextLine();
                    obj.addProduct(addId, addName, addPrice);
                    break;
                case "2":
                    System.out.println("Enter Product Id to update:");
                    String updateId = scanner.nextLine();
                    System.out.println("Enter new name:");
                    String updatedName = scanner.nextLine();
                    System.out.println("Enter new price:");
                    double updatePrice = scanner.nextDouble();
                    scanner.nextLine();
                    obj.updateProduct(updateId, updatedName, updatePrice);
                    break;
                case "3":
                    System.out.println("Enter Product Id to delete:");
                    String deleteId = scanner.nextLine();
                    obj.deleteProduct(deleteId);
                    break;
                case "4":
                    obj.viewAllProducts();
                    break;
                case "5":
                    System.out.println("Enter Product Id to view:");
                    String viewId = scanner.nextLine();
                    obj.viewByid(viewId);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public static void saleMenubar(Scanner scanner,ProductManage obj){
        while (true) {
            System.out.println("\n----- Sales  Operations -----");
            System.out.println("1. Add to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. CLear Cart");
            System.out.println("4. Complete Sale");
            System.out.println("5. View Sale");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            String ch = scanner.nextLine();
           switch(ch){
               case "1":
                   System.out.print("Enter Product Id to add to cart: ");
                   String cartId = scanner.nextLine();
                   obj.addTocart(cartId);
                   break;
               case "2":
                   obj.viewCart();
                   break;
               case "3":
                   obj.clearCart();
                   break;
               case "4":
                   obj.completeSale();
                   break;
               case "5":
                   obj.viewAllsale();
                   break;
               case "0":
                   return;
               default:
                   System.out.println("Invalid Choice");

           }
        }
    }
}


