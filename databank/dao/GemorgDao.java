/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.pojo.Denomin;
import databank.pojo.Gemorg;
import databank.pojo.Plaatsen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author zenodotus
 */
public class GemorgDao {
     private Connection con;
    
    public GemorgDao(Connection con) {
        this.con = con;
    }
    
    public List<String> getPlaatsen(String gemorg) {
        PreparedStatement lijst = null;
        List<String> gemorgs = new ArrayList<String>();
        try {
            lijst = con.prepareStatement("SELECT distinct gemorg.sorteer " + 
                                         "FROM EAVAdressengids.gemorg " +
                                         "where gemorg.sorteer is not null " +
                                         "order by gemorg.sorteer;");
            //lijst.setString(1, gemorg);
            lijst.executeQuery();
            ResultSet r = lijst.getResultSet();
            while(r.next()) {
                gemorgs.add(r.getString("gemorg.sorteer"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                lijst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorgs;
            }
        }
    }
    
    public boolean deleteGemorg(int refnum) {
        PreparedStatement verwijderen = null;
        try {
            verwijderen = con.prepareStatement("delete from gemorg where refnum = ?;");
            verwijderen.setInt(1, refnum);
            verwijderen.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                verwijderen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public Denomin getDenomin(int refnum) {
        PreparedStatement getGemorg = null;
        Denomin denominatie = null;
        try {
            getGemorg = con.prepareStatement("select denomin.* from denomin, gemorg where denomin.denomin = gemorg.denomin and gemorg.refnum = ?;");
            getGemorg.setInt(1, refnum);
            getGemorg.execute();
            ResultSet r = getGemorg.getResultSet();
            while(r.next()) {
                denominatie = new Denomin();
                denominatie.setDenomin(r.getInt("denomin.denomin"));
            denominatie.setNaam1(r.getString("denomin.naam1"));
            denominatie.setNaam2(r.getString("denomin.naam2"));
            denominatie.setStraat(r.getString("denomin.straat"));
            denominatie.setPostcode(r.getString("denomin.postcode"));
            denominatie.setWoonpl(r.getString("denomin.woonpl"));
            denominatie.setTelefoon(r.getString("denomin.telefoon"));
            denominatie.setGsm(r.getString("denomin.gsm"));
            denominatie.setEmail(r.getString("denomin.email"));
            denominatie.setLogo(r.getString("denomin.logo"));
            denominatie.setTaalcode(r.getString("denomin.taalcode"));
            denominatie.setPrincode(r.getInt("denomin.princode"));
            }
            
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fout", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                getGemorg.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return denominatie;
            }
        }
    }
    
    public boolean updateGemorg(Gemorg gemorg) {
        PreparedStatement aanpassen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            aanpassen = con.prepareStatement("update gemorg set naam1 = ?, naam2 = ?, straat = ?, postcode = ?, "
                    + "woonpl = ?, telefoon = ?, gsm = ?, email = ?, website = ?, "
                    + "eavlid = ?, gemorg = ?, denomin = ?, sorteer = ?,"
                    + "taalcode = ?, princode = ?, gemorg.update = ? where refnum = ?;");
            aanpassen.setString(1, gemorg.getNaam1());
            aanpassen.setString(2, gemorg.getNaam2());
            aanpassen.setString(3, gemorg.getStraat());
            aanpassen.setString(4, gemorg.getPostcode());
            aanpassen.setString(5, gemorg.getWoonpl());
            aanpassen.setString(6, gemorg.getTelefoon());
            aanpassen.setString(7, gemorg.getGsm());
            aanpassen.setString(8, gemorg.getEmail());
            aanpassen.setString(9, gemorg.getWebsite());
            aanpassen.setInt(10, gemorg.getEavlid());
            aanpassen.setString(11, gemorg.getGemorg());
            aanpassen.setInt(12, gemorg.getDenomin().getDenomin());
            aanpassen.setString(13, gemorg.getSorteer().getSorteer());
            aanpassen.setString(14, gemorg.getTaalcode());
            aanpassen.setInt(15, gemorg.getPrincode());
            aanpassen.setDate(16, datum);
            aanpassen.setInt(17, gemorg.getRefnum());
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
    
    public Gemorg getGemorgByRef(int ref) {
        PreparedStatement getGemorg = null;
        Gemorg gemorg = null;
        Denomin denomin = new Denomin();
        Plaatsen sorteer = new Plaatsen();
        try {
            getGemorg = con.prepareStatement("select * from gemorg where gemorg.refnum = ?;");
            getGemorg.setInt(1, ref);
            getGemorg.execute();
            ResultSet r = getGemorg.getResultSet();
            while(r.next()) {
                gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("gemorg.refnum"));
            gemorg.setNaam1(r.getString("gemorg.naam1"));
            gemorg.setNaam2(r.getString("gemorg.naam2"));
            gemorg.setStraat(r.getString("gemorg.straat"));
            gemorg.setPostcode(r.getString("gemorg.postcode"));
            gemorg.setWoonpl(r.getString("gemorg.woonpl"));
            gemorg.setTelefoon(r.getString("gemorg.telefoon"));
            gemorg.setGsm(r.getString("gemorg.gsm"));
            gemorg.setEmail(r.getString("gemorg.email"));
            gemorg.setWebsite(r.getString("gemorg.website"));
            gemorg.setEavlid(r.getInt("gemorg.eavlid"));
            gemorg.setGemorg(r.getString("gemorg.gemorg"));
            denomin.setDenomin(r.getInt("gemorg.denomin"));
            gemorg.setDenomin(denomin);
            sorteer.setSorteer(r.getString("gemorg.sorteer"));
            gemorg.setSorteer(sorteer);
            
            gemorg.setTaalcode(r.getString("gemorg.taalcode"));
            gemorg.setPrincode(r.getInt("gemorg.princode"));
            }
            
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fout", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                getGemorg.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorg;
            }
        }
    }
    
    
    
    public Gemorg getGemorgByName(String naam) {
        PreparedStatement getGemorg = null;
        Gemorg gemorg = null;
        Denomin denomin = new Denomin();
        Plaatsen sorteer = new Plaatsen();
        try {
            getGemorg = con.prepareStatement("select * from gemorg where gemorg.naam1 = ?;");
            getGemorg.setString(1, naam);
            getGemorg.execute();
            ResultSet r = getGemorg.getResultSet();
            while(r.next()) {
                gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("gemorg.refnum"));
            gemorg.setNaam1(r.getString("gemorg.naam1"));
            gemorg.setNaam2(r.getString("gemorg.naam2"));
            gemorg.setStraat(r.getString("gemorg.straat"));
            gemorg.setPostcode(r.getString("gemorg.postcode"));
            gemorg.setWoonpl(r.getString("gemorg.woonpl"));
            gemorg.setTelefoon(r.getString("gemorg.telefoon"));
            gemorg.setGsm(r.getString("gemorg.gsm"));
            gemorg.setEmail(r.getString("gemorg.email"));
            gemorg.setWebsite(r.getString("gemorg.website"));
            gemorg.setEavlid(r.getInt("gemorg.eavlid"));
            gemorg.setGemorg(r.getString("gemorg.gemorg"));
            denomin.setDenomin(r.getInt("gemorg.denomin"));
            //denomin.setNaam1(r.getString("denomin.naam1"));
            gemorg.setDenomin(denomin);
            sorteer.setSorteer(r.getString("gemorg.sorteer"));
            gemorg.setSorteer(sorteer);
            
            gemorg.setTaalcode(r.getString("gemorg.taalcode"));
            gemorg.setPrincode(r.getInt("gemorg.princode"));
            }
            
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fout", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                getGemorg.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorg;
            }
        }
    }
    
    public boolean toevoegenGemorg(Gemorg gemorg) {
        PreparedStatement toevoegen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            toevoegen = con.prepareStatement("insert into gemorg values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            toevoegen.setInt(1, gemorg.getRefnum());
            toevoegen.setString(2, gemorg.getNaam1());
            toevoegen.setString(3, gemorg.getNaam2());
            toevoegen.setString(4, gemorg.getStraat());
            toevoegen.setString(5, gemorg.getPostcode());
            toevoegen.setString(6, gemorg.getWoonpl());
            toevoegen.setString(7, gemorg.getTelefoon());
            toevoegen.setString(8, gemorg.getGsm());
            toevoegen.setString(9, gemorg.getEmail());
            toevoegen.setInt(10, gemorg.getEavlid());
            toevoegen.setString(11, gemorg.getGemorg());
            toevoegen.setInt(12, gemorg.getDenomin().getDenomin());
            toevoegen.setString(13, gemorg.getSorteer().getSorteer());
            toevoegen.setString(14, gemorg.getTaalcode());
            toevoegen.setInt(15, gemorg.getPrincode());
            toevoegen.setDate(16, datum);
            toevoegen.setString(17, gemorg.getWebsite());
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
    
    public List<Gemorg> zoekGemorg(String kolom, String zoekterm) {
        PreparedStatement allen = null;
        PreparedStatement denomins = null;
        List<Gemorg> gemorgs = new ArrayList<Gemorg>();
        try {
            allen = con.prepareStatement("select *, denomin.naam1 from gemorg, denomin where denomin.denomin = gemorg.denomin and " + kolom + " like ? order by refnum;");
            allen.setString(1, zoekterm);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                gemorg.setNaam1(r.getString("naam1"));
                gemorg.setNaam2(r.getString("naam2"));
                gemorg.setStraat(r.getString("straat"));
                gemorg.setPostcode(r.getString("postcode"));
                gemorg.setWoonpl(r.getString("woonpl"));
                gemorg.setTelefoon(r.getString("telefoon"));
                gemorg.setGsm(r.getString("gsm"));
                gemorg.setEmail(r.getString("email"));
                gemorg.setWebsite(r.getString("website"));
                gemorg.setEavlid(r.getInt("eavlid"));
                gemorg.setGemorg(r.getString("gemorg"));
                Denomin denomin = new Denomin();
                denomin.setDenomin(r.getInt("denomin"));
                denomin.setNaam1(r.getString("denomin.naam1"));
                Plaatsen sorteer = new Plaatsen();
                sorteer.setSorteer(r.getString("sorteer"));
                gemorg.setDenomin(denomin);
                gemorg.setSorteer(sorteer);
                gemorg.setTaalcode(r.getString("taalcode"));
                gemorg.setPrincode(r.getInt("princode"));
                
                gemorgs.add(gemorg);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fout", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorgs;
            }
        }
    }
    
        public List geefAlleGemorg() {
        PreparedStatement allen = null;
        PreparedStatement denomins = null;
        List<Gemorg> gemorgs = new ArrayList<Gemorg>();
        try {
            allen = con.prepareStatement("select * from gemorg order by refnum;");
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                gemorg.setNaam1(r.getString("naam1"));
                gemorg.setNaam2(r.getString("naam2"));
                gemorg.setStraat(r.getString("straat"));
                gemorg.setPostcode(r.getString("postcode"));
                gemorg.setWoonpl(r.getString("woonpl"));
                gemorg.setTelefoon(r.getString("telefoon"));
                gemorg.setGsm(r.getString("gsm"));
                gemorg.setEmail(r.getString("email"));
                gemorg.setWebsite(r.getString("website"));
                gemorg.setEavlid(r.getInt("eavlid"));
                gemorg.setGemorg(r.getString("gemorg"));
                Denomin denomin = new Denomin();
                denomin.setDenomin(r.getInt("denomin"));
                Plaatsen sorteer = new Plaatsen();
                sorteer.setSorteer(r.getString("sorteer"));
                gemorg.setDenomin(denomin);
                gemorg.setSorteer(sorteer);
                gemorg.setTaalcode(r.getString("taalcode"));
                gemorg.setPrincode(r.getInt("princode"));
                
                gemorgs.add(gemorg);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorgs;
            }
        }
        
    }
        
        public List geefAllen() {
        PreparedStatement allen = null;
        PreparedStatement denomins = null;
        List<Gemorg> gemorgs = new ArrayList<Gemorg>();
        try {
            allen = con.prepareStatement("select * from gemorg order by refnum;");
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                gemorg.setNaam1(r.getString("naam1"));
                gemorg.setNaam2(r.getString("naam2"));
                gemorg.setStraat(r.getString("straat"));
                gemorg.setPostcode(r.getString("postcode"));
                gemorg.setWoonpl(r.getString("woonpl"));
                gemorg.setTelefoon(r.getString("telefoon"));
                gemorg.setGsm(r.getString("gsm"));
                gemorg.setEmail(r.getString("email"));
                gemorg.setWebsite(r.getString("website"));
                gemorg.setEavlid(r.getInt("eavlid"));
                gemorg.setGemorg(r.getString("gemorg"));
                Denomin denomin = new Denomin();
                denomin.setDenomin(r.getInt("denomin"));
                Plaatsen sorteer = new Plaatsen();
                sorteer.setSorteer(r.getString("sorteer"));
                gemorg.setDenomin(denomin);
                gemorg.setSorteer(sorteer);
                gemorg.setTaalcode(r.getString("taalcode"));
                gemorg.setPrincode(r.getInt("princode"));
                
                gemorgs.add(gemorg);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorgs;
            }
        }
        
    }
        
        public List geefAlleKerken(String soort) {
        PreparedStatement allen = null;
        PreparedStatement denomins = null;
        List<Gemorg> gemorgs = new ArrayList<Gemorg>();
        try {
            allen = con.prepareStatement("select *, denomin.naam1 from gemorg, denomin where denomin.denomin = gemorg.denomin and gemorg = ? order by refnum;");
            allen.setString(1, soort);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                gemorg.setNaam1(r.getString("naam1"));
                gemorg.setNaam2(r.getString("naam2"));
                gemorg.setStraat(r.getString("straat"));
                gemorg.setPostcode(r.getString("postcode"));
                gemorg.setWoonpl(r.getString("woonpl"));
                gemorg.setTelefoon(r.getString("telefoon"));
                gemorg.setGsm(r.getString("Gsm"));
                gemorg.setEmail(r.getString("email"));
                gemorg.setWebsite(r.getString("website"));
                gemorg.setEavlid(r.getInt("eavlid"));
                gemorg.setGemorg(r.getString("gemorg"));
                Denomin denomin = new Denomin();
                denomin.setDenomin(r.getInt("denomin"));
                denomin.setNaam1(r.getString("denomin.naam1"));
                Plaatsen sorteer = new Plaatsen();
                sorteer.setSorteer(r.getString("sorteer"));
                gemorg.setDenomin(denomin);
                gemorg.setSorteer(sorteer);
                gemorg.setTaalcode(r.getString("taalcode"));
                gemorg.setPrincode(r.getInt("princode"));
                
                gemorgs.add(gemorg);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fout", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorgs;
            }
        }
        }
        
        public List geefAlleKerkenPerDenominatie(String soort, String naam) {
        PreparedStatement allen = null;
        PreparedStatement denomins = null;
        List<Gemorg> gemorgs = new ArrayList<Gemorg>();
        try {
            allen = con.prepareStatement("select *, denomin.naam1 from gemorg, denomin where denomin.denomin = gemorg.denomin and gemorg = ? and denomin.naam1 = ? order by refnum;");
            allen.setString(1, soort);
            allen.setString(2, naam);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                gemorg.setNaam1(r.getString("naam1"));
                gemorg.setNaam2(r.getString("naam2"));
                gemorg.setStraat(r.getString("straat"));
                gemorg.setPostcode(r.getString("postcode"));
                gemorg.setWoonpl(r.getString("woonpl"));
                gemorg.setTelefoon(r.getString("telefoon"));
                gemorg.setGsm(r.getString("gsm"));
                gemorg.setEmail(r.getString("email"));
                gemorg.setWebsite(r.getString("website"));
                gemorg.setEavlid(r.getInt("eavlid"));
                gemorg.setGemorg(r.getString("gemorg"));
                Denomin denomin = new Denomin();
                denomin.setDenomin(r.getInt("denomin"));
                denomin.setNaam1(r.getString("denomin.naam1"));
                Plaatsen sorteer = new Plaatsen();
                sorteer.setSorteer(r.getString("sorteer"));
                gemorg.setDenomin(denomin);
                gemorg.setSorteer(sorteer);
                gemorg.setTaalcode(r.getString("taalcode"));
                gemorg.setPrincode(r.getInt("princode"));
                
                gemorgs.add(gemorg);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fout", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorgs;
            }
        }
        }
        
        public List geefAlleLeden(String soort) {
        PreparedStatement allen = null;
        PreparedStatement denomins = null;
        List<Gemorg> gemorgs = new ArrayList<Gemorg>();
        try {
            allen = con.prepareStatement("select *, denomin.naam1 from gemorg, denomin where denomin.denomin = gemorg.denomin and gemorg = ? and eavlid = 1 order by refnum;");
            allen.setString(1, soort);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                gemorg.setNaam1(r.getString("naam1"));
                gemorg.setNaam2(r.getString("naam2"));
                gemorg.setStraat(r.getString("straat"));
                gemorg.setPostcode(r.getString("postcode"));
                gemorg.setWoonpl(r.getString("woonpl"));
                gemorg.setTelefoon(r.getString("telefoon"));
                gemorg.setGsm(r.getString("gsm"));
                gemorg.setEmail(r.getString("email"));
                gemorg.setWebsite(r.getString("website"));
                gemorg.setEavlid(r.getInt("eavlid"));
                gemorg.setGemorg(r.getString("gemorg"));
                Denomin denomin = new Denomin();
                denomin.setDenomin(r.getInt("denomin"));
                denomin.setNaam1(r.getString("denomin.naam1"));
                Plaatsen sorteer = new Plaatsen();
                sorteer.setSorteer(r.getString("sorteer"));
                gemorg.setDenomin(denomin);
                gemorg.setSorteer(sorteer);
                gemorg.setTaalcode(r.getString("taalcode"));
                gemorg.setPrincode(r.getInt("princode"));
                
                gemorgs.add(gemorg);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fout", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorgs;
            }
        }
        
    }
        
        public List geefAllePerPostcode(String soort, String postcode) {
        PreparedStatement allen = null;
        PreparedStatement denomins = null;
        List<Gemorg> gemorgs = new ArrayList<Gemorg>();
        try {
            allen = con.prepareStatement("select *, denomin.naam1 from gemorg, denomin, plaatsen where plaatsen.sorteer = gemorg.sorteer and denomin.denomin = gemorg.denomin and gemorg = ? and plaatsen.postcode = ? order by refnum;");
            allen.setString(1, soort);
            allen.setString(2, postcode);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                gemorg.setNaam1(r.getString("naam1"));
                gemorg.setNaam2(r.getString("naam2"));
                gemorg.setStraat(r.getString("straat"));
                gemorg.setPostcode(r.getString("postcode"));
                gemorg.setWoonpl(r.getString("woonpl"));
                gemorg.setTelefoon(r.getString("telefoon"));
                gemorg.setGsm(r.getString("gsm"));
                gemorg.setEmail(r.getString("email"));
                gemorg.setWebsite(r.getString("website"));
                gemorg.setEavlid(r.getInt("eavlid"));
                gemorg.setGemorg(r.getString("gemorg"));
                Denomin denomin = new Denomin();
                denomin.setDenomin(r.getInt("denomin"));
                denomin.setNaam1(r.getString("denomin.naam1"));
                Plaatsen sorteer = new Plaatsen();
                sorteer.setSorteer(r.getString("sorteer"));
                gemorg.setDenomin(denomin);
                gemorg.setSorteer(sorteer);
                gemorg.setTaalcode(r.getString("taalcode"));
                gemorg.setPrincode(r.getInt("princode"));
                
                gemorgs.add(gemorg);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fout", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorgs;
            }
        }
        }
        
        public List geefAlleGemorgGeordend() {
        PreparedStatement allen = null;
        PreparedStatement denomins = null;
        List<Gemorg> gemorgs = new ArrayList<Gemorg>();
        try {
            allen = con.prepareStatement("select *, denomin.naam1 from gemorg, denomin where denomin.denomin = gemorg.denomin order by gemorg.naam1;");
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("refnum"));
                gemorg.setNaam1(r.getString("naam1"));
                gemorg.setNaam2(r.getString("naam2"));
                gemorg.setStraat(r.getString("straat"));
                gemorg.setPostcode(r.getString("postcode"));
                gemorg.setWoonpl(r.getString("woonpl"));
                gemorg.setTelefoon(r.getString("telefoon"));
                gemorg.setGsm(r.getString("gsm"));
                gemorg.setEmail(r.getString("email"));
                gemorg.setWebsite(r.getString("website"));
                gemorg.setEavlid(r.getInt("eavlid"));
                gemorg.setGemorg(r.getString("gemorg"));
                Denomin denomin = new Denomin();
                denomin.setDenomin(r.getInt("denomin"));
                denomin.setNaam1(r.getString("denomin.naam1"));
                Plaatsen sorteer = new Plaatsen();
                sorteer.setSorteer(r.getString("sorteer"));
                gemorg.setDenomin(denomin);
                gemorg.setSorteer(sorteer);
                gemorg.setTaalcode(r.getString("taalcode"));
                gemorg.setPrincode(r.getInt("princode"));
                
                gemorgs.add(gemorg);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "fout", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                allen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                return gemorgs;
            }
        }
        
    }

}
