
package com.japp.classes;
import java.util.ArrayList;
import java.util.List;

public class Item {
    
    private String itemName;
    private double unitPrice;
    private static String table = "item";
    private static int cashierId = 00000000001;
    private static int itemId;
    
    public Item(String itemName, double unitPrice) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }
    
    public static void getItemID(int itemID){
           itemId = itemID;
    }
    
    public static List<List<Object>> itemLister(){
        List<List<Object>> itemList;
        
        String[] columns = {"item_id", "item_name", "unit_price"};
        String[] values = {};
        Object[] attributes = {};
        String condition = null;
        
        QueryBuilder qbr = new QueryBuilder(columns, values, table, condition);
        String query = qbr.BuildSelectQuery();
        
        Dbhandler dbo = new Dbhandler(query);
        itemList = dbo.DbSelector(attributes);
        dbo.DbCloseConn();
        
        return itemList;
    }
    
    public boolean addItem(){
        List<List<Object>> list;
        boolean result;
        
        String[] columns = {"item_name"};
        String[] values = null;
        Object[] attributes = {this.itemName};
        String condition = "item_name = ?";
        
        QueryBuilder qbr = new QueryBuilder(columns, values, table, condition);
        String query = qbr.BuildSelectQuery();
        
        Dbhandler dbo = new Dbhandler(query);
        list = dbo.DbSelector(attributes);
        dbo.DbCloseConn();
        
        if(list.isEmpty()){
            String[] columns2 = {"cashier_id" ,"item_name", "unit_price"};
            String[] values2 = {"?", "?", "?"};
            Object[] attributes2 = {cashierId ,this.itemName, this.unitPrice};
            String condition2 = "";

            QueryBuilder qbr2 = new QueryBuilder(columns2, values2, table, condition2);
            String query2 = qbr2.BuildInsertQuery();

            Dbhandler dbo2 = new Dbhandler(query2);
            int resultInt = dbo2.Dbinserter(attributes2);
            dbo2.DbCloseConn();

            if(resultInt !=0){
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }

    public boolean updateItem(){
        boolean result;
        String[] columns = {"item_name", "unit_price"};
        String[] values = {"?", "?"};
        Object[] attributes = {this.itemName, this.unitPrice, itemId};
        String condition = "item_id=?";
        
        QueryBuilder qbr = new QueryBuilder(columns, values, table, condition);
        String query = qbr.BuildUpdateQuery();
        
        Dbhandler dbo = new Dbhandler(query);
        int resultInt = dbo.Dbupdater(attributes);
        dbo.DbCloseConn();
        
        if(resultInt !=0){
            result = true;
        } else {
            result = false;
        }
        
        return result;
    }
    
    public boolean removeItem(){
        boolean result;
        String[] columns = {};
        String[] values = {};
        Object[] attributes = {itemId};
        String condition = "item_id=?";
        
        QueryBuilder qbr = new QueryBuilder(columns, values, table, condition);
        String query = qbr.BuildDeleteQuery();
        
        Dbhandler dbo = new Dbhandler(query);
        int resultInt = dbo.Dbdeleter(attributes);
        dbo.DbCloseConn();
        
        if(resultInt !=0){
            result = true;
        } else {
            result = false;
        }
        
        return result;
    }
}
