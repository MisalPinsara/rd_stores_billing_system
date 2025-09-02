
package com.japp.classes;
import com.japp.classes.QueryBuilder;
import java.util.List;
import java.util.ArrayList;

public class Cashier {
    
    private String username;
    private String password;
    private String name;
    private static String table = "cashier";
    
    public Cashier (String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getUserId(){
        
        return null;
    }
    
    public  boolean login(Cashier cashier) {
        
        boolean result;
        
        String[] columns = {"username", "password"};
        String[] values = null;
        Object[] attributes = {this.username};
        String condition = "username =  ?" ;
        
        QueryBuilder qbr = new QueryBuilder(columns, values, this.table, condition);
        String query = qbr.BuildSelectQuery();
        
        Dbhandler dbo = new Dbhandler(query );
        List<List<Object>> results = dbo.DbSelector(attributes);
        dbo.DbCloseConn();
        
        String gotUsername = "";
        String gotPassword = "";
        
        for (List<Object> col : results){
            gotUsername = (String) col.get(0);
            gotPassword = (String) col.get(1);
        }

        if(username.equals(gotUsername) && password.equals(gotPassword)){
            result = true;
        } else {
            result = false;
        }
        return result;  
    }
    
    public static String logout (){
        String farewell = "Successfull logget out";
        return farewell;
    }
    
    public boolean updatelogin(Cashier cashier){
        boolean result;
        
        String[] columns = {"username", "password"};
        String[] values = {"?", "?"};
        Object[] attributes = {this.username, this.password, this.name};
        String condition = "name = ?";
        
        QueryBuilder qb = new QueryBuilder(columns, values, table, condition);
        String query = qb.BuildUpdateQuery();
        
        Dbhandler dbo = new Dbhandler(query);
        int resultInt = dbo.Dbupdater(attributes);
        dbo.DbCloseConn();
        
        if(resultInt != 0){
            result = true;
        } else {
            result = false;
        }
        
        return result;
    }
    
}
