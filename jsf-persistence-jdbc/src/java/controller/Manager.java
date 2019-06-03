/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.util.Random;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Bishop
 */
@ManagedBean 
public class Manager {
    ResultSet rs;
    private String value1, value2, value3, type;
    private Integer numbervalue, number; 
    private String orderID;
    
    public Manager(){}

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }
   
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumbervalue() {
        return numbervalue;
    }

    public void setNumbervalue(Integer numbervalue) {
        this.numbervalue = numbervalue;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    
    
    
    public String prepare() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/general","george","2721");
        Statement stmt = con.createStatement();
        Random rand = new Random();       
        number = rand.nextInt(1000)+100;
        orderID = "CONFID"+number;
   
        if (value1.equals("") || value2.equals("") || value3.equals("")){
            return "error";
        } else {
            String check = "SELECT * FROM records WHERE stringval1 = '"+value1+"' AND "
                    + "stringval2 = '"+value2+"' AND stringval3 = '"+value3+"'";
            rs = stmt.executeQuery(check);
            if (rs.next()){
                /* If a record already exists */
                String fieldCheck1 = (String) rs.getString(1);
                String fieldCheck2 = (String) rs.getString(2);
                String fieldCheck3 = (String) rs.getString(3);
                if (fieldCheck1.equals(value1) || fieldCheck2.equals(value2)
                        || fieldCheck3.equals(value3)){
                        orderID = "";
                        return "exists";       
                } 
            } else {  
                    String query = "INSERT INTO records VALUES ('"+value1+"','"+value2+"',"
                            + "'"+value3+"',"+numbervalue+",'"+type+"','"+orderID+"')";
                    stmt.executeUpdate(query);
                    System.out.println("Query executed successfully.");
                    return "confirm";
            }
         
        }
        return "error";
    }
    
    
}
