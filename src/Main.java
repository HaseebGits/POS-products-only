import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

class Product{
    private String id;
    private String name;
    private double price;
    public Product(String id, String name, double price ){
        this.id=id;
        this.name=name;
        this.price=price;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}
class ProductManage{
 private HashMap<String,Product> products= new HashMap<>();
 private  final String File_Name = "product.txt";  // i use final keyword so that it cannot be change afterward
 public ProductManage(){
     loadFile();
 }
    private void loadFile() {  // this method trying to load data from file into map
        try (BufferedReader reader = new BufferedReader(new FileReader(File_Name))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(",");
                if (part.length == 3) {
                    String id = part[0];
                    String name = part[1];
                    double price = Double.parseDouble(part[2]);
                    products.put(id, new Product(id, name, price));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing product data found.");
        }
    }

    private void saveFile(){// this method save product data from memory into file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(File_Name))) {
            for (Product product : products.values()) {
                writer.write(product.getId() + "," +
                        product.getName() + "," +
                        product.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(" Error to save  product data.");
        }
    }
    public void addProduct(String id, String name, double price){ // add products
     if(products.containsKey(id)){
         System.out.println("Product already exist");
     }
     if(price <0){
         System.out.println("Price must be positive number");
     }
     Product product = new Product(id,name,price);  // i have read aboit this this is an object instantation just like type of composition aggregation.
         products.put(id,product);
         saveFile();
        System.out.println("Product added successfully");
    }
    public void updateProduct(String id, String newName, double newPrice){ // update product
     Product product = products.get(id);
     if(product==null){
         System.out.println("Product not found");
     }
     if(newPrice <0){
         System.out.println("Price must be positive number");
     }
     product.setName(newName);
     product.setPrice(newPrice);
     saveFile();
        System.out.println("Product update successfully");
    }
    public void deleteProduct(String id){ // delete product
     if(products.remove(id)!=null){
         System.out.println("Product deleted");
     }
     else{
         System.out.println("Product not found");
     }
    }
   public void viewAllProducts(){ // display all product
     if(products.isEmpty()){
         System.out.println("No products found ");
     }
     else {
         for(Product product:products.values()){
             System.out.println("ID:"+ product.getId() +"    "+    "Name :" + product.getName() + "    " +      "Price:$" + product.getPrice());
         }
     }
   }
   public void viewByid(String id){// display product by id
     Product product =products.get(id);
       if (product != null) {
           System.out.println("ID:"+ product.getId() +"    "+    "Name :" + product.getName() + "    " +      "Price:$" + product.getPrice());

       }
       else{
           System.out.println("Product not fund");
       }
   }
}
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
            System.out.println("Click 6 to any number to exit the program");
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
                    String IdtoView= scanner.nextLine();
                    obj.viewByid(IdtoView);
                    break;
                default:
                    scanner.close();
                    System.out.println("Program terminate");
            }

        }
    }
}
