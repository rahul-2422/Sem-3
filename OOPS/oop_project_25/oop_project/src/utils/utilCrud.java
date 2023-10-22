package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import dataClass.itemData;

public class utilCrud {

    Properties connectionProps;
    Connection con;

    public static String line1="+-------+-----------------------------------------------+---------------+---------------+-------------------+-------------+-----------------+";
    public static String line2="|ItemId\t|              ItemName                 \t|  Quantity  \t|    Price  \t|     Category      |  Discount   | DiscountedPrice |";
    public static String line3=line1;

    public static String line4=line1;

    public utilCrud() {

        connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "Shrahul@2422");
        String URL = "jdbc:mysql://localhost:3306/oop_project";

        try {
            con = DriverManager.getConnection(URL,connectionProps);
        } 
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        
    }

    public void addItemInventory(itemData Item) //darshak
    {
        //Item Id is Auto Incremented and Primary Key
        String SQL = "insert into Items(itemName,price,quantity,category,discount,discountedPrice) values(?,?,?,?,?,?)";

        try(PreparedStatement stmt = con.prepareStatement(SQL)){
          
            
            stmt.setString(1, Item.getItemName());
            stmt.setInt(2, Item.getPrice());
            stmt.setInt(3, Item.getQuantity());
            stmt.setString(4, Item.getCategory());
            stmt.setDouble(5, Item.getDiscount());
            stmt.setDouble(6, Item.getDiscountPrice());
            stmt.executeUpdate();
            // System.out.println(rowsAffected);

        }
        catch(Exception e){
            try {
                con.rollback();
            } 
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void deleteItemInventory(int id) //kalyan
    {
        String SQL = "delete from items where itemId = ?";

        try(PreparedStatement stmt = con.prepareStatement(SQL)){
          
            stmt.setInt(1, id);
            stmt.executeUpdate();

            // System.out.println(rowsAffected);

        }
        catch(Exception e){
        
            e.printStackTrace();
        }
    }

    public void getCategoryList() //Rahul
    {
    
        System.out.println("All The Categories-\n");
        System.out.println("+---------------------------------------+");
        System.out.println("|Category                             \t|");
        System.out.println("+---------------------------------------+");
        
        try (PreparedStatement stmt = con.prepareStatement("select category from Items group by category")) 
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                System.out.println(String.format("|%-30s      \t|",rs.getString("category")));
            }
            System.out.println("+---------------------------------------+");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public itemData searchById(int id1) //kalyan
    {       
        itemData itm=null;
        try  
        {
            PreparedStatement stmt = con.prepareStatement("select * from Items where itemId =?");
            stmt.setInt(1, id1);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                itm=new itemData(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("category"), rs.getDouble("discount"));
                return itm;
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return itm;
    }

    public void searchByCategory(String cat) //Darshak
    {       
    
        try  
        {
            PreparedStatement stmt = con.prepareStatement("select * from Items where category =?");
            stmt.setString(1, cat);
            ResultSet rs = stmt.executeQuery();

            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);

            int count=0;

            while (rs.next())
            {
                itemData temp=new itemData(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("category"), rs.getDouble("discount"));
                System.out.println(temp);
                count++;
            }
            if(count==0)
            {
                System.out.println("\nItem Not Found!\n");
            }
            System.out.println(line4);

        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchByName(String name) //Naman
    {      
        int count=0;
        try  
        {
            PreparedStatement stmt = con.prepareStatement("select * from Items where itemName like '%"+name+"%'");
            ResultSet rs = stmt.executeQuery();
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            while (rs.next())
            {
                itemData temp123=new itemData(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("category"), rs.getDouble("discount"));
                System.out.println(temp123);
                count++;
            }
            if(count==0)
            {
                System.out.println("\nItem not found!\n");
            }
            System.out.println(line4);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showAllItems() //Rahul
    {
        try  
        {
            PreparedStatement stmt = con.prepareStatement("select * from Items");
            ResultSet rs = stmt.executeQuery();
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            while (rs.next())
            {
                itemData itm=new itemData(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("category"), rs.getDouble("discount"));
                System.out.println(itm);
            }
            System.out.println(line4);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
     
    }

    public void showArchive() //Darshak
    {
        try  
        {
            System.out.println("+-----------------------+--------+----------+-----------+");
            System.out.println("|    Date and Time      |IteamId | Quantity |   Cost    |");
            System.out.println("+-----------------------+--------+----------+-----------+");
            
            PreparedStatement stmt1 = con.prepareStatement("select COUNT(*) as count from Archive"); //How many rows in table
            ResultSet rs = stmt1.executeQuery();
            int count=-1;
            if(rs.next())
            {
                count=rs.getInt("count");
            }
            if(count==0)
            {
                System.out.println("Archive Table Empty!");
                System.out.println("----------------------------------\n");
                return;
            }
            
            
            
            PreparedStatement stmt = con.prepareStatement("select * from Archive");
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String output = String.format("|  %s  |  %3d   |  %4d    |  %7.2f |", rs.getString("datetime"),rs.getInt("itemId"),rs.getInt("quantity"),rs.getDouble("cost"));
                System.out.println(output);
            }
            System.out.println("+-----------------------+--------+----------+-----------+");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
   
    }

    public void insertArchive(ArrayList<Integer> ids, ArrayList<Integer> quantity, ArrayList<Double> cost) //Tejas
    {
        try  
        {

            PreparedStatement stmt = con.prepareStatement("insert into Archive values(now(),?,?,?)");

            for(int i=0;i<ids.size();i++)
            {
                stmt.setInt(1, ids.get(i));
                stmt.setInt(2, quantity.get(i));
                stmt.setDouble(3, cost.get(i));
                stmt.executeUpdate();
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortByPrice(int flag) //Tejas
    {
        try  
        {
            String command = "select * from Items order by discountedPrice";

            if(flag==1)
            {
                command+=" ASC";
            }
            else
            {
                command+=" DESC";
            }

            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            
            PreparedStatement stmt = con.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                itemData itm=new itemData(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("category"), rs.getDouble("discount"));
                System.out.println(itm);
            }
            System.out.println(line4);

        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void filterByPrice(int flag,int price) //Tejas
    {

        try  
        {
            String command1 = "select * from Items where discountedPrice ";
            String command2 = " ? order by discountedPrice ASC";
            
            if(flag==1)
            {
                command1+="<=";
            }
            else
            {
                command1+=">=";
            }
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            PreparedStatement stmt = con.prepareStatement(command1+command2);
            stmt.setInt(1, price);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                itemData itm=new itemData(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("category"), rs.getDouble("discount"));
                System.out.println(itm);
            }

            System.out.println(line4);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int updateNameInventory(int id,String newName) //Rahul
    {
        String SQL = "update items set itemName=? where itemId=?";

        try{
            
            PreparedStatement stmt = con.prepareStatement(SQL);

            stmt.setString(1, newName);
            stmt.setInt(2, id);
            
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected>=1)
            {
                return 1;
            }
            else
            {
                return 0;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return 0;

    }

    public int updatePriceInventory(int id,int newPrice) //Naman
    {   
        String SQL = "update items set price=?, discountedPrice=? where itemId=?";

        itemData itm = searchById(id);
        itm.setPrice(newPrice);

        try{
            
            PreparedStatement stmt = con.prepareStatement(SQL);

            stmt.setInt(1, newPrice);
            stmt.setDouble(2, itm.getDiscountPrice());
            stmt.setInt(3, id);
            
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected>=1)
            {
                return 1;
            }
            else
            {
                return 0;
            }
            // System.out.println(rowsAffected);

        }
        catch(Exception e){

            e.printStackTrace();
        }

        return 0;
    }

    public int updateCategoryInventory(int id,String newCategory) //Rahul
    {   
        String SQL = "update items set category=? where itemId=?";

        try{
            
            PreparedStatement stmt = con.prepareStatement(SQL);

            stmt.setString(1, newCategory);
            stmt.setInt(2, id);
           
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected>=1)
            {
                return 1;
            }
            else
            {
                return 0;
            }

        }
        catch(Exception e){
            
            e.printStackTrace();
        }

        return 0;
    }

    public int updateCountInventory(int id,int newCount) //kalyan
    {   
        String SQL = "update items set quantity=? where itemId=?";

        try{
            
            PreparedStatement stmt = con.prepareStatement(SQL);

            stmt.setInt(1, newCount);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected>=1)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public int updateDiscountInventory(int id,double newDiscount) //Rahul
    {   
        itemData itm = searchById(id);

        itm.setDiscount(newDiscount);

        String SQL = "update items set discount=?,discountedPrice=? where itemId=?";

        try{
            
            PreparedStatement stmt = con.prepareStatement(SQL);

            stmt.setDouble(1, newDiscount);
            stmt.setDouble(2, itm.getDiscountPrice());
            stmt.setInt(3, id);

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected>=1)
            {
                return 1;
            }
            else
            {
                return 0;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public void filterByQuantity(int flag,int quantity) //Darshak
    {
        try  
        {
            String command1 = "select * from Items where quantity ";
            String command2 = " ? order by quantity ASC";

            if(flag==1)
            {
                command1+="<=";
            }
            else
            {
                command1+=">=";
            }

            PreparedStatement stmt = con.prepareStatement(command1+command2);
            stmt.setInt(1, quantity);

            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                itemData itm=new itemData(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("category"), rs.getDouble("discount"));
                System.out.println(itm);
            }
            System.out.println(line4);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Customer
    public boolean addItemCart(itemData item) //Tejas
    {
       
        itemData itm1 = searchById(item.getItemId());

        if(itm1.getQuantity()<item.getQuantity())
        {
            return false;
        }

        try{
            String sql="select quantity as count,cost from cart where itemId=?";
            PreparedStatement smtt = con.prepareStatement(sql);
            smtt.setInt(1, item.getItemId());
            int count=-1;
            double cost=-1;
            ResultSet st = smtt.executeQuery();
            while(st.next())
            {
                count=st.getInt("count");
                cost=st.getDouble("cost");
            }

            if(count!=-1) //When item exists in cart
            {
                cost+=item.getDiscountPrice()*item.getQuantity();
                count+=item.getQuantity();
                String SQL1="update cart set cost=?,quantity=? where itemId=?";
                PreparedStatement stmt = con.prepareStatement(SQL1);
                stmt.setDouble(1, cost);
                stmt.setInt(2, count);
                stmt.setInt(3, item.getItemId());
                stmt.executeUpdate();
                updateCountInventory(item.getItemId(), itm1.getQuantity()-item.getQuantity());
                return true;
            }

            String SQL = "insert into cart values(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt(1, item.getItemId());
            stmt.setInt(2, item.getQuantity());
            stmt.setDouble(3, item.getDiscountPrice()*item.getQuantity());

            stmt.executeUpdate();

            updateCountInventory(item.getItemId(), itm1.getQuantity()-item.getQuantity());
            // System.out.println(rowsAffected);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCart(itemData item) //Naman
    {
        boolean temp = deleteItemCart(item.getItemId());
        if(temp==true && item.getQuantity()!=0)
        {
            return addItemCart(item);
        }
        else
        {
            return false;
        }
    }

    public boolean deleteItemCart(int id) //Tejas
    {
        String SQL1 = "select quantity from cart where itemId=?"; 
        String SQL = "delete from cart where itemId=?";

        itemData itm1 = searchById(id);

        int count=-1;
        try{
          
            PreparedStatement stmt = con.prepareStatement(SQL1);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                count=rs.getInt("quantity");
            }
            // System.out.println("Count: "+ count);
            if(count==-1)
            {
                return false;
            }
            
            stmt = con.prepareStatement(SQL);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            updateCountInventory(id, itm1.getQuantity()+count);
            // System.out.println(rowsAffected);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public void showCart() //Naman
    {
        String SQL = "select * from cart";
        try  
        {
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            double totalCost=0;

            System.out.println("+----------------------------------------------------------------------------+");
            System.out.println("|ItemId\t |           Item Name                      |  Quantity |   Cost     |");
            System.out.println("+----------------------------------------------------------------------------+");

            while (rs.next())
            {
                itemData itm = searchById(rs.getInt("itemId"));
                String abc = String.format("|   %-3d  | %-40s |    %-5d  | %-10.2f |",itm.getItemId(),itm.getItemName(),rs.getInt("quantity"),rs.getDouble("cost"));
                System.out.println(abc);
                totalCost+=rs.getDouble("cost");
            }
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.println("\nTotal Cost Of Cart = "+totalCost);
            System.out.println("---------------------------------------");
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearCart() //Darshak
    {
        String sql = "select itemId from cart";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                deleteItemCart(rs.getInt("itemId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void purchaseCart() //Rahul
    {
        String SQL="select COUNT(*) as count from cart";

        try {

            PreparedStatement stmt = con.prepareStatement(SQL);

            ResultSet rs = stmt.executeQuery();
            int temp=0;
            while(rs.next())
            {
                temp = rs.getInt("count");
            }

            showCart();

            if(temp==0)
            {
                System.out.println("------------Cart is Empty. Nothing to purschase. Please add some items to cart------------");
            }
            else
            {
                System.out.println("------------Purchase succesfull. Thank you for shopping------------");
            }


            //Storing cart in archive table
            SQL="select * from cart";
            stmt = con.prepareStatement(SQL);
            ResultSet rs1 = stmt.executeQuery();
            ArrayList<Integer> ids = new ArrayList<>();
            ArrayList<Integer> quantity = new ArrayList<>();
            ArrayList<Double> cost = new ArrayList<>();

            while(rs1.next())
            {
                ids.add(rs1.getInt("itemId"));
                quantity.add(rs1.getInt("quantity"));
                cost.add(rs1.getDouble("cost"));
            }
            
            insertArchive(ids, quantity, cost);
            // Upto Here

            SQL="truncate cart";
            stmt=con.prepareStatement(SQL);
            stmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
