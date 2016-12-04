/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.pojo.Plaatsen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zenodotus
 */
public class PlaatsenDao {
    
    private Connection con;
    
    public PlaatsenDao(Connection con) {
        this.con = con;
    }
    
    public List geefAllePlaatsen() {
        PreparedStatement allen = null;
        List<Plaatsen> plaatsen = new ArrayList<Plaatsen>();
        try {
            allen = con.prepareStatement("select * from plaatsen ORDER BY sorteer;");
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Plaatsen plaats = new Plaatsen();
                plaats.setSorteer(r.getString("sorteer"));
                plaats.setPostcode(r.getString("postcode"));
                plaatsen.add(plaats);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return plaatsen;
            }
        }
        
    }
    
}
