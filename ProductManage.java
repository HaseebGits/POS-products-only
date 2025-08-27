import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ProductManage {
    private HashMap<String, Product> products = new HashMap<>();
    private final String File_Name = "product.txt";
    private final String Sales_File = "sales.txt";// i use final keyword so that it cannot be change afterward
    private List<Product> currentCart = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();
    private Sale lastSale = null;
    private int transactionCounter = 1; // counter for transaction ids

    public ProductManage() {
        loadFile();
    }

    public void addTocart(String id) {
        Product product = products.get(id);
        if (product == null) {
            System.out.println("Product not found, cannot added to cart");
        }
        currentCart.add(product);
        System.out.println("Product added to cart:" + product.getName());
    }

    public void viewCart() {
        if (currentCart.isEmpty()) {
            System.out.println("Cart is empty");
        }
        for (Product product : currentCart) {
            System.out.println("ID:" + product.getId() + "    " + "Name :" + product.getName() + "    " + "Price:$" + product.getPrice());

        }
    }

    public void clearCart() {
        currentCart.clear();
        System.out.println("Cart Cleared");
    }

    public void completeSale() {
        if (currentCart.isEmpty()) {
            System.out.println("Cart is Empty, cannot proceed complete sale");
        }
        String transactionId = "TXN-" + transactionCounter++;
        Sale sale = new Sale(transactionId, new ArrayList<>(currentCart));// i create copy of currentcart to sure the sale object has values of the products at the time of sale
        sales.add(sale);
        lastSale = sale;
        System.out.println("Sale Completed");
        System.out.println("Reciept");
        System.out.println("Transaction Id " + transactionId);
        for (Product p : sale.getItems()) {
            System.out.println("Products: " + p.getName() + "Price: $" + p.getPrice());
        }
        System.out.println("Total: $" + sale.getTotal());
        currentCart.clear();
        saveSaleToFile(sale);
    }
    public void saveSaleToFile(Sale sale) {// this method help to write data on file for future use
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Sales_File,true))){//here using true because it append the data in file i have learn from online
         writer.write("Transaction ID:"+ sale.getTransactionId());
         writer.newLine();
         for(Product p: sale.getItems()){
             writer.write("Product:   " + p.getName() + "Price: &" + p.getPrice());
             writer.newLine();
         }
         writer.write("Total amount: $"+ sale.getTotal());
         writer.newLine();
            System.out.println("---------------------------");
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadSalesFiles(){//this method load data from file
        try (BufferedReader reader = new BufferedReader(new FileReader(Sales_File))) {
            String line;
            System.out.println("\nPrevious Sale ");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No previous sale found.");
        }
    }
        public void viewAllsale() {
        if (sales.isEmpty()) {
            System.out.println("No sales found");
            return;
        }
        System.out.println("All Sales are:");
        for (Sale s : sales) {
            System.out.println("Transaction ID: " + s.getTransactionId() + "  Total: $" + s.getTotal());
        }
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
            System.out.println("No  product data found.");
        }
    }

    private void saveFile(){// this method save product data from memory into file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(File_Name))) {
            for (Product product : products.values()) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getPrice());
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
        Product product = new Product(id,name,price);  // i have read about it this, this is an object instantation just like type of composition aggregation.
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
