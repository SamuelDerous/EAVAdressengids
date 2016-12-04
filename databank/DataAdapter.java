/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import creatie.EncryptionIni;
import creatie.Initialisatie;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author zenodotus
 */
public class DataAdapter {
    
    private Connection con = null;
    private static String driver = null;
    String gebruikersnaam;
    String wachtwoord;
    String host;
    String databank = "EAVAdressengids";

    public DataAdapter() {
        try {
            //Class.forName("org.apache.derby.jdbc.ClientDrive");
             gebruikersnaam = Initialisatie.getGegeven("DBGebruikersnaam");
             if(Initialisatie.getGegeven("DBWachtwoord") == null) {
                 wachtwoord = "";
             } else {
                wachtwoord = EncryptionIni.decrypt(Initialisatie.getGegeven("DBWachtwoord"));
             }
            host = Initialisatie.getGegeven("DBHost");
            String sourceUrl = "jdbc:mysql://" + host + "/EAVAdressengids?user=" +
                    gebruikersnaam + "&password=" + wachtwoord;
            driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            con = DriverManager.getConnection(sourceUrl);
           
        }
        catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Controleer de verbinding met uw databank!");
            JOptionPane.showMessageDialog(null, "Controleer de verbinding met uw databank!\nHet programma wordt afgesloten...", ex.getCause().toString(), JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (SQLException sql) {
            System.out.println("Er is een fout in uw SQL");
            JOptionPane.showMessageDialog(null, "Er is een fout in uw SQL", sql.getCause().toString(), JOptionPane.ERROR_MESSAGE);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Er is een algemene fout opgetreden.", e.getCause().toString(), JOptionPane.ERROR_MESSAGE);
            
        }
    }
     public DataAdapter(String host, String gebruikersnaam, String wachtwoord) {
        try {
            //Class.forName("org.apache.derby.jdbc.ClientDrive");
            String sourceUrl = "jdbc:mysql://" + host + "/EAVAdressengids?user=" + gebruikersnaam +
                    "&password=" + wachtwoord;
            driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            con = DriverManager.getConnection(sourceUrl);
         
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
           
            
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
           
        }
    }

    public Connection getConnection() {
        return con;
    }
    

    
}
