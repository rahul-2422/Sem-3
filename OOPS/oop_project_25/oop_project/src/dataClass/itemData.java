package dataClass;

public class itemData{

    private int itemId;
    private String itemName;
    private int price;
    private int quantity;
    private String category;
    private double discount;
    private double discountPrice;

    public itemData(int itemId, String itemName, int price, int quantity, String category, double discount) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.discount = discount;
        this.discountPrice=discountedPrice(discount,this.price);
    }

    public double discountedPrice(double discount,int price)
    {
        double output = price - (0.01*discount*price);
        return output;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        this.discountPrice = discountedPrice(this.discount,price);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        this.discountPrice = discountedPrice(discount, getPrice());
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return String.format("|  %-3d  |   %-40s \t|  %-5d\t|    %-5d  \t|  %-15s  |   %4.2f  \t  |   %-10.2f    |",getItemId(),getItemName(),getQuantity(),getPrice(),getCategory(),getDiscount(),getDiscountPrice());
    }

    @Override
    public boolean equals(Object obj) {
        
        itemData itm = (itemData) obj;
        if(this.itemId==itm.itemId)
        {
            return true;
        }
        return false;
    }


}
