
package com.japp.classes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class Report {
    
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate generateDate;
    
    public Report(LocalDate startDate, LocalDate endDate, LocalDate generateDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.generateDate = generateDate;
    }
    
    public List<List<Object>> generateReport() {
        List<List<Object>> reportData = new ArrayList<>();
        
        String[] columns = null;
        String[] values = null;
        Object[] attributes = {this.startDate, this.endDate};
        String condition = null;
        
        QueryBuilder qbr = new QueryBuilder(columns, values, "", condition);
        String query = qbr.BuildJoinInsert();
        
        Dbhandler dbo = new Dbhandler(query);
        reportData = dbo.DbSelector(attributes);
        dbo.DbCloseConn();
        
        return reportData;
    }

}
