import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import dataClass.itemData;
import utils.Customer;
import utils.Seller;
import utils.utilCrud;

public class eKart {

    private static void takeData()  //Naman
    {

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/database/items.csv"));
            String str;

            Seller abcd = new Seller();
            utilCrud util = new utilCrud();

            while ((str = br.readLine()) != null) {
                String[] data = str.split(",");

                itemData it = new itemData(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]),Integer.parseInt(data[3]), data[4], Double.parseDouble(data[5]));
                itemData tp =util.searchById(it.getItemId());

                if(tp!=null && (tp.equals(it)))
                {
                    if(!tp.getCategory().equals(it.getCategory())){
                        abcd.updateCategory(it.getItemId(), it.getCategory());
                    }
                    if(!tp.getItemName().equals(it.getItemName())){
                        abcd.updateName(it.getItemId(), it.getItemName());
                    }
                    if(tp.getQuantity() != it.getQuantity()){
                        abcd.updateCount(it.getItemId(), it.getQuantity());
                    }
                    if(tp.getPrice() != it.getPrice()){
                        abcd.updatePrice(it.getItemId(), it.getPrice());
                    }
                    if(tp.getDiscount() != it.getDiscount()){
                        abcd.updateDiscount(it.getItemId(), it.getDiscount());
                    }
                }
                else
                {
                    abcd.add(it);
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String fileName) throws IOException
    {
        String temp="src/help/"+fileName;
        BufferedReader br = new BufferedReader(new FileReader(temp));
        String temp121;
        System.out.println();
        while((temp121=br.readLine())!=null)
        {
            System.out.println(temp121);
        }
        System.out.println();
        br.close();
    }

    public static void main(String[] args) //Naman Tejas
    {
        
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------");
        
        Customer cus = new Customer();
        Seller sell = new Seller();
        utilCrud util = new utilCrud();

        System.out.println();
        System.out.println();
        
        int commandRun=0;
        
        //Customer: Naman
        //Seller: Tejas
        try{
            switch(args[0])
            {
                case "-customer":
                    switch(args[1])
                    {
                        case "-clear":
                            cus.clearCart();
                            commandRun=1;
                            break;
                        case "-h":
                            readFile("customerHelp.txt");
                            commandRun=1;
                            break;
                        case "-u":
                            cus.update(args[2],Integer.parseInt(args[3]), args);
                            commandRun=1;
                            break;
                        case "-add":
                            int id1 = Integer.parseInt(args[2]);
                    
                            int quantity=1;
                    
                            if(args.length>=4)
                            {
                                quantity=Integer.parseInt(args[3]);
                            }
                            itemData itm =util.searchById(id1);
                            itm.setQuantity(quantity);
                            cus.add(itm);
                            commandRun=1;
                            break;
                        case "-show":
                            cus.showCart();
                            commandRun=1;
                            break;
                        case "-del":
                            int id2 = Integer.parseInt(args[2]);
                            cus.delete(id2);
                            commandRun=1;
                            break;
                        case "-p":
                            cus.purchaseCart();
                            commandRun=1;
                            break;

                        case "-s":
                            switch(args[2])
                            {
                                case "-cat":
                                    String cat1="";
                                    for(int i=3;i<args.length;i++)
                                    {
                                        cat1+=args[i];
                                        if(i!=args.length-1)
                                        {
                                            cat1+=" ";
                                        }
                                    }
                                    cus.searchByItemCategory(cat1);
                                    commandRun=1;
                                    break;
                            
                                case "-a":  
                                    cus.filterPrice(Integer.parseInt(args[3]), -1);
                                    commandRun=1;
                                    break;  
                                case "-b":
                                    cus.filterPrice(Integer.parseInt(args[3]), 1);
                                    commandRun=1;
                                    break;  
                                case "-id":
                                    cus.searchByItemId(Integer.parseInt(args[3]));
                                    commandRun=1;
                                    break;
                                case "-name":
                                    cus.searchByName(args[3]);
                                    commandRun=1;
                                    break;
                            }
                            break;
                        case "-l":
                            if(args[2].equals("-categories"))
                            {
                                cus.listCategories();
                                commandRun=1;
                            }
                            break;
                        case "-sort":
                            int flag_temp=-1;
                            if(args[3].equals("-lh"))
                            {
                                flag_temp=1;
                            }
                            else{
                                flag_temp=-1;
                            }
                            cus.sort(flag_temp);
                            commandRun=1;
                            break;
                        case "-cmp":
                            cus.compare(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                            commandRun=1;
                            break;
                            
                        case "-showall":
                            cus.showAllItems();
                            commandRun=1;
                            break;
                    }
                    break;
                case "-seller":
                    switch(args[1])
                    {
                        case "-insertData":
                            takeData();
                            commandRun=1;
                            break;
                        case "-showall":
                            cus.showAllItems();
                            commandRun=1;
                            break;
                        case "-u":
                            sell.update(args[2], Integer.parseInt(args[3]), args);
                            commandRun=1;
                            break;
                        case "-l":
                            if(args[2].equals("-categories"))
                            {
                                sell.listCategories();
                                commandRun=1;
                            }
                            break;
                        case "-show":
                            if(args[2].equals("-b"))
                            {
                                sell.filterByQuantity(1, Integer.parseInt(args[3]));
                                commandRun=1;
                            }
                            else if(args[2].equals("-a"))
                            {
                                sell.filterByQuantity(-1, Integer.parseInt(args[3]));
                                commandRun=1;
                            }
                            else if(args[2].equals("-archive"))
                            {
                                sell.displayArchieve();
                                commandRun=1;
                            }
                            break;
                        case "-s":
                            switch(args[2])
                            {
                                case "-id":
                                    sell.searchByItemId(Integer.parseInt(args[3]));
                                    commandRun=1;
                                    break;
                                case "-cat":
                                    String cat12="";
                                    for(int i=3;i<args.length;i++)
                                    {
                                        cat12+=args[i];
                                        if(i!=args.length-1)
                                        {
                                            cat12+=" ";
                                        }
                                    }
                                    sell.searchByItemCategory(cat12);
                                    commandRun=1;
                                    break;
                                case "-a":  
                                    sell.filterPrice(Integer.parseInt(args[3]), -1);
                                    commandRun=1;
                                    break;  
                                case "-b":
                                    sell.filterPrice(Integer.parseInt(args[3]), 1);
                                    commandRun=1;
                                    break;
                                case "-name":
                                    cus.searchByName(args[3]);
                                    commandRun=1;
                                    break;
                                }
                                break;
                            case "-sort":
                                int flag_temp=-1;
                                if(args[3].equals("-lh"))
                                {
                                    flag_temp=1;
                                }
                                else{
                                    flag_temp=-1;
                                }
                                sell.sort(flag_temp);
                                commandRun=1;
                                break;
                            case "-del":
                                sell.delete(Integer.parseInt(args[2]));
                                commandRun=1;
                                break;
                            case "-h":
                                readFile("sellerFile.txt");
                                commandRun=1;
                                break;
                            case "-add":
                                String newNameAdd="";
                                String Newcategory="";
                                
                                int i=9;
                                
                                for(;!args[i].equals("-name");i++)
                                {
                                    Newcategory+=args[i];
                                    if(args[i+1]!="-name")
                                    {
                                        Newcategory+=" ";
                                    }
                                }
                                i++;
                                for(;i<args.length;i++)
                                {
                                    newNameAdd+=args[i];
                                    if(i!=args.length-1)
                                    {
                                        newNameAdd+=" ";
                                    }
                                }
                                itemData newItemData = new itemData(-1, newNameAdd, Integer.parseInt(args[5]), Integer.parseInt(args[3]), Newcategory, Double.parseDouble(args[7]));
                                sell.add(newItemData);
                                commandRun=1;
                                break;
                        }
                        break;
                        
                        default:
                            System.out.println("Enter Valid Command\nEnter \"-h\" for help");
                            break;
                        
                    }
                    
                }   
                catch(Exception e)
                {
                    //Display  Help
                    System.out.println("Enter Valid Command\nError Logged");
                    System.out.println("Enter \"-customer -h\" for help");
                    commandRun=1;   
                    //Logging the error
                    try {
                        PrintWriter writer = new PrintWriter("src/database/logger.txt");
                        e.printStackTrace(writer);
                        writer.close();
                    } catch (FileNotFoundException e1) {
            
                    }
                }
                finally{
                    
                    if(commandRun==0)
                    {
                        System.out.println("Enter \"-customer -h\" for help");
                    }
                    
                    System.out.println();
                    System.out.println("Thank you for using our system\n");
                    
                    System.out.println("\n---------------------------------------------------------------------------------------------------------------------------");
                }
       
    }

}
