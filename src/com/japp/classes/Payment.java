
package com.japp.classes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Payment {

    private LocalDate paymentDate;
    private LocalTime paymentTime;
    private double paidAmount;
    private static String table = "payment";
    private static int cashierID = 00000000001;
    
    public Payment(LocalDate paymentDate, LocalTime paymentTime, double paidAmount){
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.paidAmount = paidAmount;
    }
    
    public int makePayment() {
        List<List<Object>> rows = new ArrayList<>();
        int paymentID = 0;
        
        String[] columns = {"cashier_id", "payment_date", "payment_time", "paid_amount"};
        String[] values = {"?", "?", "?", "?"};
        Object[] attributes = {cashierID, this.paymentDate, this.paymentTime, this.paidAmount};
        String condition = null;
        
        QueryBuilder qbr = new QueryBuilder(columns, values, table, condition);
        String query = qbr.BuildInsertQuery();
        
        Dbhandler dbo = new Dbhandler(query);
        int resultInt = dbo.Dbinserter(attributes);
        dbo.DbCloseConn();
        System.out.println("payment saved to the database");
        
        String[] columns2 = {"payment_id"};
        String[] values2 = null;
        Object[] attributes2 = {this.paymentTime, this.paidAmount};
        String condition2 = "payment_time = ? AND paid_amount = ?";
        
        QueryBuilder qbr2 = new QueryBuilder(columns2, values2, table, condition2);
        String query2 = qbr2.BuildSelectQuery();
        
        Dbhandler dbo2 = new Dbhandler(query2);
        rows = dbo2.DbSelector(attributes2);
        dbo2.DbCloseConn();
        
        String stringPaymentID = "";
        for(List<Object> cols : rows){
             stringPaymentID = (String) cols.get(0).toString();
        }
        paymentID = Integer.parseInt(stringPaymentID);
        
        return paymentID;
    }
 
}
