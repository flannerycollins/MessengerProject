/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package messenger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Kira
 */
public class Messenger {

    //The constructor for our messenger. It is empty for now.
    public Messenger(){
        
    }
    
    
    
    //The below method registers a new user in the system. It returns true if the user registered successfully.
    //This may be changed to void later if we decide we don't need it to return anything.
    public boolean Register(){
        boolean registered = false;
        boolean alreadyExists = false;
        Scanner scan = new Scanner(System.in);
        String username = "";
        String password = "";
        String confirm = "";
        String sql = "";
        int result = 0;
        
        try {
            //Connect to the system.
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");
            
            //Tell mySQL that we want to use the database called mydb (where our users table is).
            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            //System.out.println("Result of SQL update: " + result);



            //Here is where we start asking the user for things. It would be cool to put these into some sort of
            //form type thing, but we can work that out later.
            System.out.println("Enter your desired username:");
            username = scan.nextLine();

            System.out.println("Enter your password:");
            password = scan.nextLine();

            System.out.println("Confirm your password:");
            confirm = scan.nextLine();


            //Here we make sure the two passwords are the same (to try and make sure that the user didn't mistype their
            //password so that they can log in later). Loops until the user enters two passwords that match.
            while(!confirm.equals(password)){
                System.out.println("Those passwords did not match.");
                System.out.println("Enter your password:");
                password = scan.nextLine();

                System.out.println("Confirm your password:");
                confirm = scan.nextLine();
            }
            
            
            //Here we are checking to see if the desired username already exists.
            statement = conn.createStatement();
            sql = ("SELECT userids FROM users WHERE usernames = '" 
                    + username +"';");
            ResultSet rs1 = statement.executeQuery(sql);
            alreadyExists = rs1.next();
            
            //If the username is not already taken, assign a userID and add the user to the table.
            if(!alreadyExists){
              //Here we are finding the highest userID in the table so that we can assign an ID to the new user.
              //We assign a new ID by adding 1 to the highestID.
              statement = conn.createStatement();
              sql = "SELECT MAX(userids) AS userids FROM users;";
              //System.out.println(sql);
              ResultSet rs = statement.executeQuery(sql);
              rs.next();
              int highestID = rs.getInt("userids");
              //System.out.println(highestID + " is the highest ID");
              int newID = highestID + 1;
              //System.out.println(newID + " is the new ID");
            
              statement = conn.createStatement();
              sql = ("INSERT INTO users (usernames, userids, password, firstname, lastName) VALUES ('" 
                      + username + "', " + newID + ", '"+ password +"', '', '');");
              System.out.println(sql);
              result = statement.executeUpdate(sql);
              System.out.println("Registration successful. You may now log in.");
              registered = true;
              return registered;
            }
            //If the name is taken, let the user know.
            else{
                System.out.println("That username already exists.");
            }
        } catch (SQLException ex) {
	    // handle any SQL errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
        }
        //If we get here, registration has failed. Let the user know.
        System.out.println("Registration failed.");
        return registered;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Messenger messenger = new Messenger();
        
        messenger.Register();
    }
    
}
