package utils;

import dataClass.itemData;

public class Customer extends baseClass {

    @Override
    public void add(itemData item) //Tejas
    {

        utilCrud util = new utilCrud();
        boolean added = util.addItemCart(item);
        if(!added)
        {
            System.out.println("Quantity of items does not exist in inventory");
        }        
        else
        {
            System.out.println("Item added to Cart!");
        }
    }
    
    @Override
    public void delete(int id) //Tejas
    {

        utilCrud util = new utilCrud();

        if(!util.deleteItemCart(id))
        {   
            System.out.println("Enter valid item id");
        }        
        else
        {
            System.out.println("Item Deleted from Cart Sucessfully");
        }
    }

    @Override
    public void update(String command,int id,String[] args) //Naman
    {
        utilCrud util = new utilCrud();
        itemData id1 = util.searchById(id);

        id1.setQuantity(Integer.parseInt(args[4]));

        if(util.updateCart(id1)==true)
        {
            System.out.println("Cart Updated!");
        }
        else
        {
            if(id1.getQuantity()==0)
            {
                System.out.println("Item Removed!");
            }
            else
            {
                System.out.println("Operation Failed\nNot enough Quantity in inventory or Item not present in cart");
            }
        }

    }

    public void showCart() //Naman
    {
        utilCrud crud = new utilCrud();
        
        crud.showCart();
    }
    
    public void clearCart() //Darshak
    {
        utilCrud crud = new utilCrud();
        crud.clearCart();

        System.out.println("Cart Cleared!");

    }

    public void purchaseCart() //Rahul
    {
        utilCrud util = new utilCrud();

        util.purchaseCart();

    }

    public void compare(int id1, int id2) //kalyan
    {
        
        utilCrud crud = new utilCrud();

        itemData itm1 = crud.searchById(id1);

        itemData itm2 = crud.searchById(id2);

        System.out.println(utilCrud.line1);
        System.out.println(utilCrud.line2);
        System.out.println(utilCrud.line3);
        
        if(itm1==null)
        {
            String temp = String.format("| Item not found with id: %-5d        \t\t\t\t\t\t\t\t\t\t\t\t\t    |", id1);
            System.out.println(temp);
        }
        else
        {
            System.out.println(itm1);
        }
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
        if(itm2==null)
        {
            String temp = String.format("| Item not found with id: %-5d        \t\t\t\t\t\t\t\t\t\t\t\t\t    |", id2);
            System.out.println(temp);
        }
        else
        {
            System.out.println(itm2);
        }

        System.out.println(utilCrud.line4);
    }  
    
}
