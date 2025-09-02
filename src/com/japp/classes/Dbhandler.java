
package com.japp.classes;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Dbhandler {
    
    private Connection conn;
    private String query;
    
    public Dbhandler(String query) {
        
        this.query = query;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/rd_stores_db", "root", "Misal@sqlroot0228");
            System.out.println("successfully connected to the database");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public int Dbinserter(Object[] attributes){
        int resultInt = 0;
        try(PreparedStatement pst = conn.prepareStatement(this.query)){
            
            for(int i = 0; i<attributes.length;i++){
                Object value = attributes[i];
                if(value instanceof String){
                    pst.setString(i + 1, (String) value);
                } else if(value instanceof Integer){
                    pst.setInt(i + 1, (Integer) value);
                } else if(value instanceof Double){
                    pst.setDouble(i + 1, (Double) value);
                } else if(value instanceof LocalDate){
                    LocalDate localdate = (LocalDate) value;
                    pst.setDate(i + 1, java.sql.Date.valueOf(localdate));
                } else if(value instanceof LocalTime){
                    LocalTime localtime = (LocalTime) value;
                    pst.setTime(i + 1, java.sql.Time.valueOf(localtime));
                }
            }
            
            resultInt = pst.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        System.out.println(resultInt + " Rows have been inserted ");
        return resultInt;
    }
    
    public List<List<Object>> DbSelector(Object[] attributes){
        List<List<Object>> allRows = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement(this.query)){
            
            if(attributes.length != 0){
                for(int i = 0; i<attributes.length;i++){
                    Object value = attributes[i];
                    if(value instanceof String){
                        pst.setString(i + 1, (String) value);
                    } else if(value instanceof Integer){
                        pst.setInt(i + 1, (Integer) value);
                    } else if(value instanceof Double){
                        pst.setDouble(i + 1, (Double) value);
                    } else if(value instanceof LocalDate){
                        LocalDate localdate = (LocalDate) value;
                        pst.setDate(i + 1, java.sql.Date.valueOf(localdate));
                    } else if(value instanceof LocalTime){
                        LocalTime localtime = (LocalTime) value;
                        pst.setTime(i + 1, java.sql.Time.valueOf(localtime));
                    }
                }
            }
            
            try(ResultSet rs = pst.executeQuery()){
                
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();
                        
                while(rs.next()){
                    List<Object> row = new ArrayList<>();
                    for(int i = 1; i <= columnCount; i++){
                        row.add(rs.getObject(i));
                    }
                    allRows.add(row);
                }
            } 
            } catch (SQLException e){
                e.printStackTrace();
            } 
        System.out.println("data selected from database");
        return allRows;
    }
    
    public int Dbupdater(Object[] attributes){
        int resultInt = 0;
        try(PreparedStatement pst = conn.prepareStatement(query)){
            
            for(int i = 0; i<attributes.length; i++){
                Object value = attributes[i];
                if(value instanceof String){
                    pst.setString(i + 1, (String) value);
                }else if(value instanceof Integer){
                    pst.setInt(i + 1, (Integer) value);
                }else if(value instanceof Double){
                    pst.setDouble(i + 1, (Double) value);
                }else if(value instanceof LocalDate){
                    LocalDate localdate = (LocalDate) value;
                    pst.setDate(i + 1, java.sql.Date.valueOf(localdate));
                } else if(value instanceof LocalTime){
                    LocalTime localtime = (LocalTime) value;
                    pst.setTime(i + 1, java.sql.Time.valueOf(localtime));
                }
            }
            resultInt = pst.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        System.out.println(resultInt + " Rows have been updated ");
        return resultInt;
    }
    
    public int Dbdeleter(Object[] attributes){
        int resultInt = 0;
        try(PreparedStatement pst = conn.prepareStatement(query)){
            
            for(int i = 0; i<attributes.length; i++){
                Object value = attributes[i];
                if(value instanceof String){
                    pst.setString(i + 1, (String) value);
                }else if(value instanceof Integer){
                    pst.setInt(i + 1, (Integer) value);
                }else if(value instanceof Double){
                    pst.setDouble(i + 1, (Double) value);
                }else if(value instanceof LocalDate){
                    LocalDate localdate = (LocalDate) value;
                    pst.setDate(i + 1, java.sql.Date.valueOf(localdate));
                } else if(value instanceof LocalTime){
                    LocalTime localtime = (LocalTime) value;
                    pst.setTime(i + 1, java.sql.Time.valueOf(localtime));
                }
            }
            resultInt = pst.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        System.out.println(resultInt + " Rows have been deleted ");
        return resultInt;
    }
    
    public int DbBatchInserter(List<List<Object>> dataRows){
        int resultInt = 0;
        try(PreparedStatement pst = conn.prepareStatement(query)){
            for(List<Object> row : dataRows){
                for(int i = 0; i<row.size(); i++){
                    Object value = row.get(i);
                    if (value instanceof String) {
                        pst.setString(i + 1, (String) value);
                    } else if (value instanceof Integer) {
                        pst.setInt(i + 1, (Integer) value);
                    } else if (value instanceof Double) {
                        pst.setDouble(i + 1, (Double) value);
                    } else if (value instanceof LocalDate) {
                        pst.setDate(i + 1, java.sql.Date.valueOf((LocalDate) value));
                    } else if (value instanceof LocalTime) {
                        pst.setTime(i + 1, java.sql.Time.valueOf((LocalTime) value));
                    }
                }
                pst.addBatch();
            }
            int[] results = pst.executeBatch();
            for(int result : results){
                resultInt+=result;
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println(resultInt + "rows have been insterted");
        return resultInt;
    }

    public void DbCloseConn(){
        try{
            if(conn!=null && !conn.isClosed()){
                conn.close();
                System.out.println("successfully closed the connection with database");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
