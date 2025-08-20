
import java.util.Scanner;


public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ProductManage obj= new ProductManage();
        while(true){
            System.out.println("------------Point Of Sale (POS) System------------------");
            System.out.println("Click 1 for add product");
            System.out.println("Click 2 for update product");
            System.out.println("Click 3 for delete product");
            System.out.println("Click 4 for viewing all products");
            System.out.println("Click 5 for view product by id");
            System.out.println("Click 6 for add to cart");
            System.out.println("Click 7 for view cart");
            System.out.println("Click 8 for clear sale");
            System.out.println("Click 9 for  complete sales");
            System.out.println("Click 10 for all sales");
            System.out.println("Enter choice\n");
            String ch =scanner.nextLine();
            switch(ch){
                case "1":
                    System.out.println("Enter Product Id");
                    String addId= scanner.nextLine();
                    System.out.println("Enter Product Name");
                    String addName= scanner.nextLine();
                    System.out.println("Enter price of Product");
                    double addPrice = scanner.nextDouble();
                    obj.addProduct(addId,addName,addPrice);
                    break;
                case "2":
                    System.out.println("Enter product id to update");
                    String updateId= scanner.nextLine();
                    System.out.println("Enter new product name");
                    String updatedName= scanner.nextLine();
                    System.out.println("Enter new product price");
                    double updatePrice= scanner.nextDouble();
                    obj.updateProduct(updateId,updatedName,updatePrice);
                    break;
                case "3":
                    System.out.println("Enter product id to delete product");
                    String deleteId= scanner.nextLine();
                    obj.deleteProduct(deleteId);
                    break;
                case "4":
                    System.out.println("The Products are");
                    obj.viewAllProducts();
                    break;
                case "5":
                    System.out.println("Enter Id of product to view");
                    String idtoView= scanner.nextLine();
                    obj.viewByid(idtoView);
                    break;
                case "6":
                    System.out.print("Enter Product Id to add to cart: ");
                    String cartId = scanner.nextLine();
                    obj.addTocart(cartId);
                    break;

                case "7":
                    obj.viewCart();
                    break;

                case "8":
                    obj.clearCart();
                    break;

                case "9":
                    obj.completeSale();
                    break;

                case "10":
                    obj.viewAllsale();
                    break;
                default:
//                    scanner.close();
                    System.out.println("Program terminate");
            }

        }
    }
}
