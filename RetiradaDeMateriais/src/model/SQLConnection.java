package model;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    public SQLConnection(String path){
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            this.statement = this.connection.createStatement();

            this.statement.execute("CREATE TABLE IF NOT EXISTS Loan (loanId INTEGER PRIMARY KEY, name TEXT, componentId INTEGER, quantity INTEGER, loanDate DATE, devolutionDate DATE, status BOOLEAN);");
            this.statement.execute("CREATE TABLE IF NOT EXISTS Components (componentId INTEGER PRIMARY KEY, component TEXT, qtdAvailable INTEGER, qtdUnavailable INTEGER);");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco");
            e.printStackTrace();
        }
    }

    public boolean close(){
        try {
            if(!this.statement.isClosed()){
                this.statement.close();
            
                if(!this.connection.isClosed()){
                    this.connection.close();
                }

                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao fechar banco");
            return false;
        }
    }

}
