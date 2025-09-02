
package com.japp.classes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    
    private int paymentID;
    private LocalDate billedDate;
    private LocalTime billedTime;
    private static String table = "bill";
    
    public Bill(int paymentID, LocalDate billedDate, LocalTime billedTime) {
        this.paymentID = paymentID;
        this.billedDate = billedDate;
        this.billedTime = billedTime;
    }
    
    public int saveBill(){
        List<List<Object>> rows = new ArrayList<>();
        int billID;
        
        String[] columns = {"payment_id", "billed_date", "billed_time"};
        String[] values = {"?", "?", "?"};
        Object[] attributes = {this.paymentID, this.billedDate, this.billedTime};
        String condition = null;
        
        QueryBuilder qbr = new QueryBuilder(columns, values, table, condition);
        String query = qbr.BuildInsertQuery();
        
        Dbhandler dbo  = new Dbhandler(query);
        dbo.Dbinserter(attributes);
        dbo.DbCloseConn();
        
        String[] columns2 = {"bill_id"};
        String[] values2 = null;
        Object[] attributes2 = {this.paymentID};
        String condition2 = "payment_id = ?";
        
        QueryBuilder qbr2 = new QueryBuilder(columns2, values2, table, condition2);
        String query2 = qbr2.BuildSelectQuery();
        
        Dbhandler dbo2 = new Dbhandler(query2);
        rows = dbo2.DbSelector(attributes2);
        dbo2.DbCloseConn();
        
        String stringBillId = "";
        for(List<Object> cols : rows){
            stringBillId = (String) cols.get(0).toString();
        }
        billID = Integer.parseInt(stringBillId);
        
        return billID;
    }
    
}
