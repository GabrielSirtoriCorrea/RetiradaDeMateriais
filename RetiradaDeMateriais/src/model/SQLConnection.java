package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public SQLConnection(){
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + getClass().getResource("/model/RetiradaDeMateriais.db").toString());
            this.statement = this.connection.createStatement();

            this.statement.execute("CREATE TABLE IF NOT EXISTS Components (Id INTEGER PRIMARY KEY AUTOINCREMENT, component TEXT, qtdAvailable INTEGER, qtdUnavailable INTEGER);");
            this.statement.execute("CREATE TABLE IF NOT EXISTS Loan (Id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, componentId INTEGER, quantity INTEGER, loanDate DATE, devolutionDate DATE, status BOOLEAN);");

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

    public void insertLoan(String name, int componentId, int qtdComponent, Date loanDate, boolean status){
        try {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO Loan (name, componentId, quantity, loanDate, devolutionDate, status) VALUES (?,?,?,?,?,?);");
            this.preparedStatement.setString(1, name);
            this.preparedStatement.setInt(2, componentId);
            this.preparedStatement.setInt(3, qtdComponent);
            this.preparedStatement.setDate(4, loanDate);
            this.preparedStatement.setDate(5, null);
            this.preparedStatement.setBoolean(6, status);

            this.preparedStatement.executeUpdate();
                
            
        } catch (SQLException e) {
            System.out.println("Falha ao inserir dados na tabela");
            e.printStackTrace();
        }
    }

    public ResultSet getLoan(){
        try {
            resultSet = statement.executeQuery("SELECT * FROM Loan"); 
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar dados da tabela loan");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getLoan(String key, String value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setString(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela loan");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getLoan(String key, int value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela loan");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getLoan(String key, boolean value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setBoolean(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela loan");
            e.printStackTrace();
            return null;
        }
    }


    public void updateLoan(String key, String keyValue, String field, String fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setString(1, fieldValue);
            this.preparedStatement.setString(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, int keyValue, String field, String fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setString(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, String keyValue, String field, int fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, fieldValue);
            this.preparedStatement.setString(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, int keyValue, String field, int fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, int keyValue, String field, boolean fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setBoolean(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void updateLoan(String key, int keyValue, String field, Date fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Loan SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setDate(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void deleteLoan(String key, String value){
        try {
            this.preparedStatement = connection.prepareStatement("DELETE FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setString(1, value);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao deletar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }

    public void deleteLoan(String key, int value){
        try {
            this.preparedStatement = connection.prepareStatement("DELETE FROM Loan WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, value);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao deletar dados a partir de chave da tabela loan");
            e.printStackTrace();
        }
    }


    public void insertComponent(String component, int qtdAvailable, int qtdUnavailable){
        try {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO Components (component, qtdAvailable, qtdUnavailable) VALUES (?,?,?);");
            this.preparedStatement.setString(1, component);
            this.preparedStatement.setInt(2, qtdAvailable);
            this.preparedStatement.setInt(3, qtdUnavailable);

            this.preparedStatement.executeUpdate();
                
            
        } catch (SQLException e) {
            System.out.println("Falha ao inserir dados na tabela");
            e.printStackTrace();
        }
    }    

    public ResultSet getComponent(){
        try {
            this.resultSet = statement.executeQuery("SELECT * FROM Components"); 
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar dados da tabela Components");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getComponent(String key, String value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Components WHERE " + key + " = ?");
            this.preparedStatement.setString(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela Components");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getComponent(String key, int value){
        try {
            this.preparedStatement = connection.prepareStatement("SELECT * FROM Components WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, value);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela Components");
            e.printStackTrace();
            return null;
        }
    }

    public void updateComponent(String key, String keyValue, String field, String fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Components SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setString(1, fieldValue);
            this.preparedStatement.setString(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void updateComponent(String key, int keyValue, String field, String fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Components SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setString(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void updateComponent(String key, String keyValue, String field, int fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Components SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, fieldValue);
            this.preparedStatement.setString(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void updateComponent(String key, int keyValue, String field, int fieldValue){
        try {
            this.preparedStatement = connection.prepareStatement("UPDATE Components SET " + field + " = ? WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, fieldValue);
            this.preparedStatement.setInt(2, keyValue);
            this.preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void deleteComponent(String key, String value){
        try {
            this.preparedStatement = connection.prepareStatement("DELETE FROM Components WHERE " + key + " = ?");
            this.preparedStatement.setString(1, value);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

    public void deleteComponent(String key, int value){
        try {
            this.preparedStatement = connection.prepareStatement("DELETE FROM Components WHERE " + key + " = ?");
            this.preparedStatement.setInt(1, value);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados a partir de chave da tabela Components");
            e.printStackTrace();
        }
    }

}
