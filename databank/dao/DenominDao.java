/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.pojo.Denomin;
import databank.pojo.Verantw;
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
public class DenominDao {
    
    private Connection con;
    
    public DenominDao(Connection con) {
        this.con = con;
    }
    
    public boolean deleteDenominatie(int refnum) {
        PreparedStatement verwijderen = null;
        try {
            verwijderen = con.prepareStatement("delete from denomin where denomin.denomin = ?;");
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
    
    public List<Denomin> zoekDenominatie(String kolom, String zoekterm) {
        PreparedStatement allen = null;
        List<Denomin> denominaties = new ArrayList<Denomin>();
        try {
            allen = con.prepareStatement("select * from denomin where " + kolom + " like ?;");
            allen.setString(1, zoekterm);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Denomin denominatie = new Denomin();
                denominatie.setDenomin(r.getInt("denomin"));
                denominatie.setNaam1(r.getString("naam1"));
                denominatie.setNaam2(r.getString("naam2"));
                denominatie.setStraat(r.getString("straat"));
                denominatie.setPostcode(r.getString("postcode"));
                denominatie.setWoonpl(r.getString("woonpl"));
                denominatie.setTelefoon(r.getString("telefoon"));
                denominatie.setGsm(r.getString("gsm"));
                denominatie.setEmail(r.getString("email"));
                denominatie.setTaalcode(r.getString("taalcode"));
                denominatie.setPrincode(r.getInt("princode"));
                denominatie.setLogo(r.getString("logo"));
                
                denominaties.add(denominatie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return denominaties;
            }
        }
        
    
    }
    
    public boolean updateDenominatie(Denomin denominatie) {
        PreparedStatement aanpassen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            aanpassen = con.prepareStatement("update denomin set naam1 = ?, naam2 = ?, straat = ?, postcode = ?, "
                    + "woonpl = ?, telefoon = ?, gsm = ?, email = ?, "
                    + "logo = ?, taalcode = ?, princode = ?, denomin.update = ? where denomin = ?;");
            aanpassen.setString(1, denominatie.getNaam1());
            aanpassen.setString(2, denominatie.getNaam2());
            aanpassen.setString(3, denominatie.getStraat());
            aanpassen.setString(4, denominatie.getPostcode());
            aanpassen.setString(5, denominatie.getWoonpl());
            aanpassen.setString(6, denominatie.getTelefoon());
            aanpassen.setString(7, denominatie.getGsm());
            aanpassen.setString(8, denominatie.getEmail());
            aanpassen.setString(9, denominatie.getLogo());
            aanpassen.setString(10, denominatie.getTaalcode());
            aanpassen.setInt(11, denominatie.getPrincode());
            aanpassen.setDate(12, datum);
            aanpassen.setInt(13, denominatie.getDenomin());
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
    
    public Denomin getDenominatieByNaam1(String naam) {
        PreparedStatement denomin = null;
        Denomin denominatie = new Denomin();
        try {
            denomin = con.prepareStatement("select * from denomin where naam1 = ?;");
            denomin.setString(1, naam);
            denomin.execute();
            ResultSet r = denomin.getResultSet();
            while(r.next()) {
            denominatie.setDenomin(r.getInt("denomin"));
            denominatie.setNaam1(r.getString("naam1"));
            denominatie.setNaam2(r.getString("naam2"));
            denominatie.setStraat(r.getString("straat"));
            denominatie.setPostcode(r.getString("postcode"));
            denominatie.setWoonpl(r.getString("woonpl"));
            denominatie.setTelefoon(r.getString("telefoon"));
            denominatie.setGsm(r.getString("gsm"));
            denominatie.setEmail(r.getString("email"));
            denominatie.setLogo(r.getString("logo"));
            denominatie.setTaalcode(r.getString("taalcode"));
            denominatie.setPrincode(r.getInt("princode"));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                denomin.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return denominatie;
            }
        }
    }
    
    
    public Denomin getDenominatieByRef(int refnum) {
        PreparedStatement denomin = null;
        Denomin denominatie = null;
        try {
            denomin = con.prepareStatement("select * from denomin where denomin = ?;");
            denomin.setInt(1, refnum);
            denomin.execute();
            ResultSet r = denomin.getResultSet();
            while(r.next()) {
            denominatie = new Denomin();
            denominatie.setDenomin(r.getInt("denomin"));
            denominatie.setNaam1(r.getString("naam1"));
            denominatie.setNaam2(r.getString("naam2"));
            denominatie.setStraat(r.getString("straat"));
            denominatie.setPostcode(r.getString("postcode"));
            denominatie.setWoonpl(r.getString("woonpl"));
            denominatie.setTelefoon(r.getString("telefoon"));
            denominatie.setGsm(r.getString("gsm"));
            denominatie.setEmail(r.getString("email"));
            denominatie.setLogo(r.getString("logo"));
            denominatie.setTaalcode(r.getString("taalcode"));
            denominatie.setPrincode(r.getInt("princode"));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                denomin.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return denominatie;
            }
        }
    }
    
    
    
    public boolean toevoegenDenomin(Denomin denominatie) {
        PreparedStatement toevoegen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            toevoegen = con.prepareStatement("insert into denomin values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            toevoegen.setInt(1, denominatie.getDenomin());
            toevoegen.setString(2, denominatie.getNaam1());
            toevoegen.setString(3, denominatie.getNaam2());
            toevoegen.setString(4, denominatie.getStraat());
            toevoegen.setString(5, denominatie.getPostcode());
            toevoegen.setString(6, denominatie.getWoonpl());
            toevoegen.setString(7, denominatie.getTelefoon());
            toevoegen.setString(8, denominatie.getGsm());
            toevoegen.setString(9, denominatie.getEmail());
            toevoegen.setString(10, denominatie.getLogo());
            toevoegen.setString(11, denominatie.getTaalcode());
            toevoegen.setInt(12, denominatie.getPrincode());
            toevoegen.setDate(13, datum);
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
    
    public List geefAlleDenominaties() {
        PreparedStatement allen = null;
        List<Denomin> denominaties = new ArrayList<Denomin>();
        try {
            allen = con.prepareStatement("select * from denomin;");
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Denomin denominatie = new Denomin();
                denominatie.setDenomin(r.getInt("denomin"));
                denominatie.setNaam1(r.getString("naam1"));
                denominatie.setNaam2(r.getString("naam2"));
                denominatie.setStraat(r.getString("straat"));
                denominatie.setPostcode(r.getString("postcode"));
                denominatie.setWoonpl(r.getString("woonpl"));
                denominatie.setTelefoon(r.getString("telefoon"));
                denominatie.setGsm(r.getString("gsm"));
                denominatie.setEmail(r.getString("email"));
                denominatie.setTaalcode(r.getString("taalcode"));
                denominatie.setPrincode(r.getInt("princode"));
                denominatie.setLogo(r.getString("logo"));
                
                denominaties.add(denominatie);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause().toString());
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return denominaties;
            }
        }
        
    }
}
