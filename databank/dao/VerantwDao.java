/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

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
public class VerantwDao {
     
    private Connection con;
    
    public VerantwDao(Connection con) {
        this.con = con;
    }
    
    public boolean deleteVerantwoordelijke(int refnum) {
        PreparedStatement verwijderen = null;
        try {
            verwijderen = con.prepareStatement("delete from verantw where vref = ?;");
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
    
    public boolean updateVerantwoordelijke(Verantw verantwoordelijke) {
        PreparedStatement aanpassen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            aanpassen = con.prepareStatement("update verantw set titel = ?, "
                    + "voornaam = ?, naam1 = ?, naam2 = ?, straat = ?, postcode = ?, "
                    + "woonpl = ?, land = ?, telefoon = ?, gsm = ?, email = ?, "
                    + "taalcode = ?, princode = ?, verantw.update = ? where vref = ?;");
            aanpassen.setString(1, verantwoordelijke.getTitel());
            aanpassen.setString(2, verantwoordelijke.getVoornaam());
            aanpassen.setString(3, verantwoordelijke.getNaam1());
            aanpassen.setString(4, verantwoordelijke.getNaam2());
            aanpassen.setString(5, verantwoordelijke.getStraat());
            aanpassen.setString(6, verantwoordelijke.getPostcode());
            aanpassen.setString(7, verantwoordelijke.getWoonpl());
            aanpassen.setString(8, verantwoordelijke.getLand());
            aanpassen.setString(9, verantwoordelijke.getTelefoon());
            aanpassen.setString(10, verantwoordelijke.getGsm());
            aanpassen.setString(11, verantwoordelijke.getEmail());
            aanpassen.setString(12, verantwoordelijke.getTaalcode());
            aanpassen.setInt(13, verantwoordelijke.getPrincode());
            aanpassen.setDate(14, datum);
            aanpassen.setInt(15, verantwoordelijke.getVref());
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
    
    public Verantw getVerantwoordelijkeByVRef(int vref) {
        PreparedStatement verantwoordelijke = null;
        Verantw verantw = null;
        try {
            verantwoordelijke = con.prepareStatement("select * from verantw where vref = ?;");
            verantwoordelijke.setInt(1, vref);
            verantwoordelijke.execute();
            ResultSet r = verantwoordelijke.getResultSet();
            while(r.next()) {
            verantw = new Verantw();
            verantw.setVref(r.getInt("vref"));
            verantw.setTitel(r.getString("titel"));
            verantw.setVoornaam(r.getString("voornaam"));
            verantw.setNaam1(r.getString("naam1"));
            verantw.setNaam2(r.getString("naam2"));
            verantw.setStraat(r.getString("straat"));
            verantw.setPostcode(r.getString("postcode"));
            verantw.setWoonpl(r.getString("woonpl"));
            verantw.setLand(r.getString("land"));
            verantw.setTelefoon(r.getString("telefoon"));
            verantw.setGsm(r.getString("gsm"));
            verantw.setEmail(r.getString("email"));
            verantw.setTaalcode(r.getString("taalcode"));
            verantw.setPrincode(r.getInt("princode"));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                verantwoordelijke.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return verantw;
            }
        }
    }
    
    public boolean toevoegenVerantw(Verantw verantwoordelijke) {
        PreparedStatement toevoegen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            toevoegen = con.prepareStatement("insert into verantw values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            toevoegen.setInt(1, verantwoordelijke.getVref());
            toevoegen.setString(2, verantwoordelijke.getTitel());
            toevoegen.setString(3, verantwoordelijke.getVoornaam());
            toevoegen.setString(4, verantwoordelijke.getNaam1());
            toevoegen.setString(5, verantwoordelijke.getNaam2());
            toevoegen.setString(6, verantwoordelijke.getStraat());
            toevoegen.setString(7, verantwoordelijke.getPostcode());
            toevoegen.setString(8, verantwoordelijke.getWoonpl());
            toevoegen.setString(9, verantwoordelijke.getLand());
            toevoegen.setString(10, verantwoordelijke.getTelefoon());
            toevoegen.setString(11, verantwoordelijke.getGsm());
            toevoegen.setString(12, verantwoordelijke.getEmail());
            toevoegen.setString(13, verantwoordelijke.getTaalcode());
            toevoegen.setInt(14, verantwoordelijke.getPrincode());
            toevoegen.setDate(15, datum);
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
    
    public List geefAlleTitels() {
        PreparedStatement sqlTitels = null;
        List<String> titels = new ArrayList<String>();
        try {
            sqlTitels = con.prepareStatement("select distinct titel from verantw;");
            sqlTitels.executeQuery();
            ResultSet r = sqlTitels.getResultSet();
            while(r.next()) {
                String titel = r.getString("titel");
                if(titel != null && !titel.equals("")) {
                    titels.add(titel);
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                sqlTitels.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return titels;
            }
        }
    }
    
    public List zoekVerantwoordelijken(String kolom, String zoekterm) {
        PreparedStatement allen = null;
        List<Verantw> verantwoordelijken = new ArrayList<Verantw>();
        try {
            allen = con.prepareStatement("select * from verantw where " + kolom + " like ?;");
            allen.setString(1, zoekterm);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Verantw verantw = new Verantw();
                verantw.setVref(r.getInt("vref"));
                verantw.setTitel(r.getString("titel"));
                verantw.setVoornaam(r.getString("voornaam"));
                verantw.setNaam1(r.getString("naam1"));
                verantw.setNaam2(r.getString("naam2"));
                verantw.setStraat(r.getString("straat"));
                verantw.setPostcode(r.getString("postcode"));
                verantw.setWoonpl(r.getString("woonpl"));
                verantw.setLand(r.getString("land"));
                verantw.setTelefoon(r.getString("telefoon"));
                verantw.setGsm(r.getString("gsm"));
                verantw.setEmail(r.getString("email"));
                verantw.setTaalcode(r.getString("taalcode"));
                verantw.setPrincode(r.getInt("princode"));
                
                verantwoordelijken.add(verantw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return verantwoordelijken;
            }
        }
        
    }
    
    public List geefAlleVerantwoordelijken() {
        PreparedStatement allen = null;
        List<Verantw> verantwoordelijken = new ArrayList<Verantw>();
        try {
            allen = con.prepareStatement("select * from verantw;");
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Verantw verantw = new Verantw();
                verantw.setVref(r.getInt("vref"));
                verantw.setTitel(r.getString("titel"));
                verantw.setVoornaam(r.getString("voornaam"));
                verantw.setNaam1(r.getString("naam1"));
                verantw.setNaam2(r.getString("naam2"));
                verantw.setStraat(r.getString("straat"));
                verantw.setPostcode(r.getString("postcode"));
                verantw.setWoonpl(r.getString("woonpl"));
                verantw.setLand(r.getString("land"));
                verantw.setTelefoon(r.getString("telefoon"));
                verantw.setGsm(r.getString("gsm"));
                verantw.setEmail(r.getString("email"));
                verantw.setTaalcode(r.getString("taalcode"));
                verantw.setPrincode(r.getInt("princode"));
                
                verantwoordelijken.add(verantw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return verantwoordelijken;
            }
        }
        
    }
    
     public List geefAlleVerantwoordelijkenPerRegio(String postcode) {
        PreparedStatement allen = null;
        List<Verantw> verantwoordelijken = new ArrayList<Verantw>();
        try {
            allen = con.prepareStatement("select * from verantw where postcode = ?;");
            allen.setString(1, postcode);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Verantw verantw = new Verantw();
                verantw.setVref(r.getInt("vref"));
                verantw.setTitel(r.getString("titel"));
                verantw.setVoornaam(r.getString("voornaam"));
                verantw.setNaam1(r.getString("naam1"));
                verantw.setNaam2(r.getString("naam2"));
                verantw.setStraat(r.getString("straat"));
                verantw.setPostcode(r.getString("postcode"));
                verantw.setWoonpl(r.getString("woonpl"));
                verantw.setLand(r.getString("land"));
                verantw.setTelefoon(r.getString("telefoon"));
                verantw.setGsm(r.getString("gsm"));
                verantw.setEmail(r.getString("email"));
                verantw.setTaalcode(r.getString("taalcode"));
                verantw.setPrincode(r.getInt("princode"));
                
                verantwoordelijken.add(verantw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return verantwoordelijken;
            }
        }
        
    }
    
    public List getVerantwoordelijkeAlfabet(String alf) {
        PreparedStatement allen = null;
        List<Verantw> verantwoordelijken = new ArrayList<Verantw>();
        try {
            allen = con.prepareStatement("select * from verantw where naam1 like ? order by naam1, voornaam, naam2;");
            allen.setString(1, alf);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Verantw verantw = new Verantw();
                verantw.setVref(r.getInt("vref"));
                verantw.setTitel(r.getString("titel"));
                verantw.setVoornaam(r.getString("voornaam"));
                verantw.setNaam1(r.getString("naam1"));
                verantw.setNaam2(r.getString("naam2"));
                verantw.setStraat(r.getString("straat"));
                verantw.setPostcode(r.getString("postcode"));
                verantw.setWoonpl(r.getString("woonpl"));
                verantw.setLand(r.getString("land"));
                verantw.setTelefoon(r.getString("telefoon"));
                verantw.setGsm(r.getString("gsm"));
                verantw.setEmail(r.getString("email"));
                verantw.setTaalcode(r.getString("taalcode"));
                verantw.setPrincode(r.getInt("princode"));
                
                verantwoordelijken.add(verantw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return verantwoordelijken;
            }
        }
        
    }
}
