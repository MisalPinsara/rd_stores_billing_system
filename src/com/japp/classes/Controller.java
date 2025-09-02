
package com.japp.classes;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Controller {
    
    public static boolean isDouble(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean logIn(String userInt, String passInt){
        boolean result;
        Cashier cashier = new Cashier(userInt, passInt, null);
        result = cashier.login(cashier);
        return result;
    }
    
    public static String logOut(){
        String farewell = Cashier.logout();
        return farewell;
    }
    
    public static boolean updateLogin(String name, String newUsername, String newPassword){
        boolean result;
        Cashier cashier = new Cashier(newUsername, newPassword, name);
        result = cashier.updatelogin(cashier);
        return result;
    }
    
    public static List<List<Object>> itemLister(){
        List<List<Object>> itemList = Item.itemLister();
        return itemList;
    }
    
    public static void getItemID(Object[] rowData){
        int itemID = Integer.parseInt(rowData[0].toString());
        Item.getItemID(itemID);
    }
    
    public static boolean insertItem(String itemName, double unitPrice){
        boolean result;
        Item item = new Item(itemName, unitPrice);
        result = item.addItem();
        return result;
    }
   
    public static boolean updateItem(String itemName, double unitPrice){
        boolean result;
        Item item = new Item(itemName, unitPrice);
        result = item.updateItem();
        return result;
    }
    
    public static boolean removeItem(){
        boolean result;
        Item item = new Item(null, 0);
        result = item.removeItem();
        return result;
    }
    
    public static Object[] getItemRowData(Object[] rowData, double itemQty){
        Cart cart = new Cart(0, rowData, null, itemQty);
        Object[] cartData = cart.addToCart();
        return cartData;
    }

    public static String getTotal(double[] values){
        double[] calValues = values;
        Cart cart = new Cart(0.0, null, calValues, 0.0);
        String cartTotal = cart.calCartTotal();
        return cartTotal;
    }
    
    public static boolean removeFromCart(){
        boolean result;
        Cart cart = new Cart(0.0, null, null, 0.0);
        result = cart.removeFromCart();
        return result;
    }
    
    public static int makePayment(double cash){
        int PaymentID;
        LocalDate paidDate = LocalDate.now();
        LocalTime paidTime = LocalTime.now();
        Payment payment = new Payment(paidDate, paidTime, cash);
        PaymentID = payment.makePayment();
        return PaymentID;
    }
    
    public static int saveBill(int paymentID){
        LocalDate billedDate = LocalDate.now();
        LocalTime billedTime = LocalTime.now();
        Bill bill = new Bill(paymentID, billedDate, billedTime);
        int billID = bill.saveBill();
        return billID;
    }
    
    public static void saveBillItems(List<List<Object>> billItems, int billID){
        int resultInt = 0;
        String table = "bill_item";
        String[] columns = {"bill_id", "item_id", "bill_item_quantity"};
        String[] values = {"?", "?", "?"};
        String condition = null;
        
        QueryBuilder qbr = new QueryBuilder(columns, values, table, condition);
        String query = qbr.BuildInsertQuery();
        
        List<List<Object>> batchData = new ArrayList<>();
        for(List<Object> cols : billItems){
            List<Object> row = new ArrayList<>();
            row.add(billID);
            row.add(cols.get(0));
            row.add(cols.get(3));
            batchData.add(row);
        }
        Dbhandler dbo = new Dbhandler(query);
        resultInt = dbo.DbBatchInserter(batchData);
        dbo.DbCloseConn();
        
        if(resultInt != 0){
            System.out.println("Bill Item List have been inserted");
        } else {
            System.out.println("error while inserting Bill Item List");
        }
    }
    
    public static List<List<Object>> getDates(LocalDate fromDate, LocalDate toDate){
        List<List<Object>> results;
        LocalDate generateDate = LocalDate.now();
        Report report = new Report(fromDate, toDate, generateDate);
        results = report.generateReport();
        return results;
    }
        
}
