package utils;

import dataClass.itemData;

public class Seller extends baseClass {

    @Override
    public void add(itemData item) //darshak
    {

        utilCrud crud = new utilCrud();
        crud.addItemInventory(item);

        System.out.println("Item Added to Inventory Succesfully!");

    }

    @Override
    public void delete(int id) //kalyan
    {

        utilCrud crud = new utilCrud();
        crud.deleteItemInventory(id);

        System.out.println("Item Deleted Succesfully!");

    }

    @Override
    public void update(String command, int id, String[] args) //Tejas
    {
        
        Seller sell = new Seller();
        switch (command) {
            case "-p":
                sell.updatePrice(id, Integer.parseInt(args[4]));
                break;
            case "-c":
                String cat123 = "";
                for (int i = 4; i < args.length; i++) {
                    cat123 += args[i];
                    if (i != args.length - 1) {
                        cat123 += " ";
                    }
                }
                sell.updateCategory(Integer.parseInt(args[3]), cat123);
                break;

            case "-count":
                sell.updateCount(id, Integer.parseInt(args[4]));
                break;
            case "-dis":
                sell.updateDiscount(Integer.parseInt(args[3]), Double.parseDouble(args[4]));
                break;
            case "-name":
                String newName123 = "";
                for (int i = 4; i < args.length; i++) {
                    newName123 += args[i];
                    if (i != args.length - 1) {
                        newName123 += " ";
                    }
                }
                sell.updateName(Integer.parseInt(args[3]), newName123);
                break;
        }
    }

    public void updatePrice(int id, int newPrice) //Naman
    {

        utilCrud util = new utilCrud();
        int val =util.updatePriceInventory(id, newPrice);

        if(val==0)
        {
            System.out.println("Update Price Operation Failed!");
            return;
        }

        System.out.println("Item Price Updated!");

    }

    public void updateCategory(int id, String newCategory) //Rahul
    {

        utilCrud util = new utilCrud();
        int val = util.updateCategoryInventory(id, newCategory);
        if(val==0)
        {
            System.out.println("Update Category Operation Failed!");
            return;
        }
        System.out.println("Update Category Updated!");

    }

    public void updateCount(int id, int newCount) //kalyan
    {

        utilCrud util = new utilCrud();

        int val =util.updateCountInventory(id, newCount);
        if(val==0)
        {
            System.out.println("Update Count Operation Failed!");
            return;
        }
        System.out.println("Item Count Updated!");

    }

    public void updateName(int id, String newName) //Rahul
    {
        utilCrud util = new utilCrud();
        int temp = util.updateNameInventory(id, newName);
        if (newName.length() > 40 || temp == 0) 
        {
            System.out.println("Update Operation Operation Failed");
        } 
        else 
        {
            System.out.println("Item Name Updated!");
        }

    }

    public void updateDiscount(int id, double discount) //Rahul
    {

        utilCrud util = new utilCrud();
        int val=util.updateDiscountInventory(id, discount);

        if(val==0)
        {
            System.out.println("Update Discount Operation Failed!");
            return;
        }
        System.out.println("Item Discount Updated!");

    }

    public void filterByQuantity(int flag, int quantity) //Darshak
    {
        utilCrud obj = new utilCrud();
        //flag=1 <=
        //flag=-1 >=
        obj.filterByQuantity(flag, quantity);
    }

    public void displayArchieve() //Darshak
    {
        utilCrud util = new utilCrud();
        util.showArchive();
    }

}
