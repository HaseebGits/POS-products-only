import java.util.List;

class Sale{
    private String transactionId;
    private List<Product> items;
    private double total;
    private double calculateTotal(){
        double sum=0;
        for(Product p: items){
            sum+=p.getPrice();
        }
        return sum;
    }
    public Sale(String transactionId, List<Product> items){
        this.transactionId=transactionId;
        this.items=items;
        this.total = calculateTotal();
    }

    public String getTransactionId(){
        return transactionId;
    }
    public List<Product> getItems(){
        return items;
    }
    public double getTotal(){
        return total;
    }

}
