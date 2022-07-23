package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Loan {
    private int id;
    private String name;
    private String component;
    private int quantity;
    private String loanDate;
    private String devolutionDate = null;
    private String status;
    private SQLConnection sqlConnection;
    private SimpleDateFormat dateFormat;
    private ResultSet result;

    public Loan(int id, String name, int componentId, int quantity, Date loanDate, Date devolutionDate,
            boolean status) throws SQLException {
            
        this.sqlConnection = new SQLConnection(); 
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");    

        result = sqlConnection.getComponent("Id", componentId);

        while(result.next()){
            this.component = result.getString("component");
            
        }

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.loanDate = dateFormat.format(loanDate);
        if(devolutionDate != null){
            this.devolutionDate = dateFormat.format(devolutionDate);
        }else{
            this.devolutionDate = "-";
        }

        if(status){
            this.status = "Em andamento";
        }else{
            this.status = "Devolvido";
        }
        
    }
    
    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComponent() {
        return this.component;
    }
    public void setcomponent(String component) {
        this.component = component;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getLoanDate() {
        return this.loanDate;
    }
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }
    public String getDevolutionDate() {
        return this.devolutionDate;
    }
    public void setDevolutionDate(String devolutionDate) {
        this.devolutionDate = devolutionDate;
    }
    public String isStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    } 

}
