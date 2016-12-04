/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.pojo.Gemorg;
import databank.pojo.Samenkom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author zenodotus
 */
public class SamenkomDao {
    
    private Connection con;
    
    public SamenkomDao(Connection con) {
        this.con = con;
    }
    
    public boolean deletesamenkomst(int refnum) {
        PreparedStatement verwijderen = null;
        try {
            verwijderen = con.prepareStatement("delete from samenkom where refnum = ?;");
            verwijderen.setInt(1, refnum);
            verwijderen.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                verwijderen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
     public boolean updateSamenkomst(Samenkom samenkomst) {
        PreparedStatement aanpassen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            GregorianCalendar gregTijd = samenkomst.getUur();
            java.sql.Time tijd = new java.sql.Time(gregTijd.getTimeInMillis());
            aanpassen = con.prepareStatement("update samenkom set naam1 = ?, naam2 = ?, straat = ?, postcode = ?, "
                    + "woonpl = ?, telefoon = ?, dag = ?, uur = ?, princode = ?, samenkom.update = ? where refnum = ?;");
            aanpassen.setString(1, samenkomst.getNaam1());
            aanpassen.setString(2, samenkomst.getNaam2());
            aanpassen.setString(3, samenkomst.getStraat());
            aanpassen.setString(4, samenkomst.getPostcode());
            aanpassen.setString(5, samenkomst.getWoonpl());
            aanpassen.setString(6, samenkomst.getTelefoon());
            aanpassen.setString(7, samenkomst.getDag());
            aanpassen.setTime(8, tijd);
            aanpassen.setInt(9, samenkomst.getPrincode());
            aanpassen.setDate(10, datum);
            aanpassen.setInt(11, samenkomst.getRefnum().getRefnum());
            aanpassen.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                aanpassen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public Samenkom getSamenkomstByRef(int refnum) {
        PreparedStatement samenkomst = null;
        Samenkom samenkom = null;
        try {
            samenkomst = con.prepareStatement("select * from samenkom where refnum = ?;");
            samenkomst.setInt(1, refnum);
            samenkomst.execute();
            ResultSet r = samenkomst.getResultSet();
            while(r.next()) {
            samenkom = new Samenkom();
            Gemorg gemorg = new Gemorg();
            gemorg.setRefnum(r.getInt("refnum"));
            samenkom.setRefnum(gemorg);
            samenkom.setNaam1(r.getString("naam1"));
            samenkom.setNaam2(r.getString("naam2"));
            samenkom.setStraat(r.getString("straat"));
            samenkom.setPostcode(r.getString("postcode"));
            samenkom.setWoonpl(r.getString("woonpl"));
            samenkom.setTelefoon(r.getString("telefoon"));
            samenkom.setDag(r.getString("dag"));
            java.util.Date tijd = r.getTime("uur");
            GregorianCalendar uur = new GregorianCalendar(0, 0, 0, tijd.getHours(), tijd.getMinutes());
            samenkom.setUur(uur);
            samenkom.setPrincode(r.getInt("princode"));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                samenkomst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return samenkom;
            }
        }
    }
    
    
    public boolean toevoegenSamenkom(Samenkom samenkomst) {
        PreparedStatement toevoegen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.util.Date time = new java.util.Date(samenkomst.getUur().getTimeInMillis());
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            java.sql.Time tijd = new java.sql.Time(time.getTime());
            toevoegen = con.prepareStatement("insert into samenkom values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            toevoegen.setInt(1, samenkomst.getRefnum().getRefnum());
            toevoegen.setString(2, samenkomst.getNaam1());
            toevoegen.setString(3, samenkomst.getNaam2());
            toevoegen.setString(4, samenkomst.getStraat());
            toevoegen.setString(5, samenkomst.getPostcode());
            toevoegen.setString(6, samenkomst.getWoonpl());
            toevoegen.setString(7, samenkomst.getTelefoon());
            toevoegen.setString(8, samenkomst.getDag());
            toevoegen.setTime(9, tijd);
            toevoegen.setInt(11, samenkomst.getPrincode());
            toevoegen.setDate(10, datum);
            toevoegen.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                toevoegen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public List zoekSamenkomsten(String kolom, String zoekterm) {
        PreparedStatement allen = null;
        List<Samenkom> samenkomsten = new ArrayList<Samenkom>();
        try {
            allen = con.prepareStatement("select * from samenkom where " + kolom + " like ?;");
            allen.setString(1, zoekterm);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Samenkom samenkomst = new Samenkom();
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                samenkomst.setRefnum(gemorg);
                samenkomst.setNaam1(r.getString("naam1"));
                samenkomst.setNaam2(r.getString("naam2"));
                samenkomst.setStraat(r.getString("straat"));
                samenkomst.setPostcode(r.getString("postcode"));
                samenkomst.setWoonpl(r.getString("woonpl"));
                samenkomst.setTelefoon(r.getString("telefoon"));
                samenkomst.setDag(r.getString("dag"));
                java.util.Date tijd = r.getTime("uur");
                GregorianCalendar uur = new GregorianCalendar(0, 0, 0, tijd.getHours(), tijd.getMinutes());
                samenkomst.setUur(uur);
                samenkomsten.add(samenkomst);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return samenkomsten;
            }
        }
        
    
    }
    
    public List geefAlleSamenkomsten() {
        PreparedStatement allen = null;
        List<Samenkom> samenkomsten = new ArrayList<Samenkom>();
        try {
            allen = con.prepareStatement("select * from samenkom;");
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Samenkom samenkomst = new Samenkom();
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                samenkomst.setRefnum(gemorg);
                samenkomst.setNaam1(r.getString("naam1"));
                samenkomst.setNaam2(r.getString("naam2"));
                samenkomst.setStraat(r.getString("straat"));
                samenkomst.setPostcode(r.getString("postcode"));
                samenkomst.setWoonpl(r.getString("woonpl"));
                samenkomst.setTelefoon(r.getString("telefoon"));
                samenkomst.setDag(r.getString("dag"));
                java.util.Date tijd = r.getTime("uur");
                GregorianCalendar uur = new GregorianCalendar(0, 0, 0, tijd.getHours(), tijd.getMinutes());
                samenkomst.setUur(uur);
                samenkomsten.add(samenkomst);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return samenkomsten;
            }
        }
        
    }
}
