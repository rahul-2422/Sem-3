package utils;

import dataClass.itemData;

public abstract class baseClass {


    abstract void add(itemData item); //Customer: Cart, Seller: Inventory
    abstract void delete(int id);//Customer: Cart, Seller: Inventory
    abstract void update(String command,int id,String[] args);

    utilCrud crud;

    public baseClass() {
        crud=new utilCrud();
    }

    public void listCategories() //Rahul
    {
        crud.getCategoryList();
    }

    public void searchByName(String name) //darshak
    {
    
        crud.searchByName(name);

    }

    public void searchByItemId(int id) //kalyan
    {

        itemData itm = crud.searchById(id);
        System.out.println(utilCrud.line1);
        System.out.println(utilCrud.line2);
        System.out.println(utilCrud.line3);

        if(itm==null)
        {
            System.out.println("\nItem Not Found\n");
        }
        else
        {
            System.out.println(itm);
        }
        System.out.println(utilCrud.line4);
    }

    public void searchByItemCategory(String category) //Naman
    {
    
        crud.searchByCategory(category);
    }

    public void sort(int flag) //Tejas
    { 
        crud.sortByPrice(flag);
        //flag=1 ascedning
        //flag=-1 descending
    }

    public void showAllItems() //Rahul
    {

        crud.showAllItems();

    }

    public void filterPrice(int price,int flag) //Tejas
    {
        //flag=1 <=
        //flag=-1 >=
        crud.filterByPrice(flag, price);
    }

    
}
