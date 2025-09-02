
package com.japp.classes;

public class QueryBuilder {
    
    private String[] columns;
    private String[] values;
    private String table;
    private String condition;

    public QueryBuilder(String[] columns, String[] values, String table, String conditions) {
        this.table = table;
        this.columns = columns;
        this.values = values;
        this.condition = conditions;
    }
    
    public String BuildInsertQuery(){
        StringBuilder query = new StringBuilder("INSERT INTO ");
        if(this.columns != null && this.columns.length != 0){
            query.append(this.table).append(" (").append(String.join(", ", this.columns)).append(")");
        } else {
            query.append(this.table);
        }
        if(this.values != null){
        query.append(" VALUES (").append(String.join(", ", this.values)).append(")");
        }
        return query.toString();
    }
    
    public String BuildSelectQuery(){
        StringBuilder query = new StringBuilder("SELECT ");
        if(this.columns != null && this.columns.length != 0){
            query.append(String.join(", ", this.columns));
        } else {
            query.append("*");
        }
        query.append(" FROM ").append(this.table);
        if(this.condition != null){
            query.append(" WHERE ").append(this.condition);
        }
        
        System.out.println("successfully created select query " + query.toString());
        return query.toString();
    }
    
    public String BuildUpdateQuery(){
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(this.table).append(" SET ");
        if(columns != null && this.columns.length != 0 && this.values != null && this.values.length !=0){
            for(int i = 0; i < this.columns.length; i++){
                query.append(this.columns[i]).append(" = ").append(this.values[i]);
                if(i < this.columns.length - 1){
                    query.append(", ");
                }
            }
            if(this.condition != null){
                query.append(" WHERE ").append(this.condition);
            }
        } else {
            if(this.condition != null){
                query.append(" WHERE ").append(this.condition);
            }
        }
        
        System.out.println("successfully created update query " + query.toString());
        return query.toString();
    }
    
    public String BuildDeleteQuery(){
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(this.table);
        if(this.condition != null){
                query.append(" WHERE ").append(this.condition);
         }
        
        System.out.println("successfully created delete query " + query.toString());
        return query.toString();
    }
    
    public String BuildJoinInsert(){
        String query = "SELECT b.bill_id, b.payment_id, b.billed_date, b.billed_time, " +
                              "SUM(bi.bill_item_quantity * i.unit_price) AS bill_amount " +
                              "FROM bill b " +
                              "JOIN bill_item bi ON b.bill_id = bi.bill_id " +
                              "JOIN item i ON bi.item_id = i.item_id " +
                              "WHERE b.billed_date BETWEEN ? AND ? " +
                              "GROUP BY b.bill_id, b.payment_id, b.billed_date, b.billed_time " +
                              "ORDER BY b.billed_date, b.billed_time";
        
        return query;
    }

}
