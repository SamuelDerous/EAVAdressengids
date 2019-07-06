/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.pojo.Codes;
import databank.pojo.Denomin;
import databank.pojo.Gemorg;
import databank.pojo.Plaatsen;
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
public class CodesDao {
    
    private Connection con;
    
    public CodesDao(Connection con) {
        this.con = con;
    }
    
    public List<String> geefAllePlaatsen(String gemorg) {
        PreparedStatement lijst = null;
        List<String> plaatsen = new ArrayList<String>();
        try {
            lijst = con.prepareStatement("SELECT distinct gemorg.sorteer from codes, gemorg\n" +
                                        "where gemorg.refnum = codes.refnum\n" +
                                        "and gemorg.sorteer is not null " +
                                        //"and gemorg.gemorg = ? " +
                                        "order by gemorg.sorteer;");
            //lijst.setString(1, gemorg);
            lijst.executeQuery();
            ResultSet r = lijst.getResultSet();
            while(r.next()) {
                plaatsen.add(r.getString("gemorg.sorteer"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                lijst.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return plaatsen;
            }
        }
    }
    
    public List getVerantwoordelijkeAlfabet(String alf) {
        PreparedStatement allen = null;
        List<Codes> verantwoordelijken = new ArrayList<Codes>();
        try {
            allen = con.prepareStatement("select codes.vref, verantw.titel, verantw.voornaam, verantw.naam1, verantw.naam2, verantw.straat, verantw.postcode, verantw.woonpl, "
                    + "verantw.land, verantw.telefoon, verantw.gsm, verantw.email, verantw.taalcode, verantw.princode, "
                    + "gemorg.naam1, gemorg.sorteer, gemorg.gemorg from codes, verantw, gemorg where codes.vref = verantw.vref "
                    + "and gemorg.refnum = codes.refnum and verantw.princode = 1 and verantw.naam1 like ? order by verantw.naam1, verantw.voornaam, verantw.naam2;");
            allen.setString(1, alf);
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Codes code = new Codes();
                Verantw verantw = new Verantw();
                verantw.setVref(r.getInt("codes.vref"));
                verantw.setTitel(r.getString("verantw.titel"));
                verantw.setVoornaam(r.getString("verantw.voornaam"));
                verantw.setNaam1(r.getString("verantw.naam1"));
                verantw.setNaam2(r.getString("verantw.naam2"));
                verantw.setStraat(r.getString("verantw.straat"));
                verantw.setPostcode(r.getString("verantw.postcode"));
                verantw.setWoonpl(r.getString("verantw.woonpl"));
                verantw.setLand(r.getString("verantw.land"));
                verantw.setTelefoon(r.getString("verantw.telefoon"));
                verantw.setGsm(r.getString("verantw.gsm"));
                verantw.setEmail(r.getString("verantw.email"));
                verantw.setTaalcode(r.getString("verantw.taalcode"));
                verantw.setPrincode(r.getInt("verantw.princode"));
                code.setVref(verantw);
                Gemorg gemorg = new Gemorg();
                Plaatsen plaats = new Plaatsen();
                plaats.setSorteer(r.getString("gemorg.sorteer"));
                gemorg.setSorteer(plaats);
                gemorg.setGemorg(r.getString("gemorg.gemorg"));
                gemorg.setNaam1(r.getString("gemorg.naam1"));
                code.setRefnum(gemorg);
                verantwoordelijken.add(code);
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
    
    public List<Integer> aantalVerantw(String gemorg) {
        PreparedStatement lijst = null;
        List<Integer> verantwoordelijken = new ArrayList<Integer>();
        try {
            lijst = con.prepareStatement("SELECT gemorg.sorteer, gemorg.naam1, count(verantw.naam1) as verantwoordelijken " +
                                         "FROM eav_adressengids.codes, " +
                                         "eav_adressengids.samenkom, " +
                                         "eav_adressengids.verantw, " +
                                         "eav_adressengids.gemorg " +
                                         "where codes.vref = verantw.vref " +
                                         "and gemorg.refnum = samenkom.refnum " +
                                         "and gemorg.refnum = codes.refnum " +
                                         "and gemorg.gemorg = ? " +
                                         "group by gemorg.sorteer, gemorg.naam1 " +
                                         "order by gemorg.sorteer, gemorg.naam1;");
            lijst.setString(1, gemorg);
            lijst.executeQuery();
            ResultSet r = lijst.getResultSet();
            while(r.next()) {
                verantwoordelijken.add(r.getInt("verantwoordelijken"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                lijst.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return verantwoordelijken;
            }
        }
    }
    
    public List<Integer> aantalVerantw2(String gemorg) {
        PreparedStatement lijst = null;
        List<Integer> verantwoordelijken = new ArrayList<Integer>();
        try {
            lijst = con.prepareStatement("SELECT gemorg.sorteer, gemorg.naam1, count(verantw.naam1) as verantwoordelijken " +
                                         "FROM eav_adressengids.codes, " +
                                         "eav_adressengids.verantw, " +
                                         "eav_adressengids.gemorg " +
                                         "where codes.vref = verantw.vref " +
                                         "and gemorg.refnum = codes.refnum " +
                                         "and gemorg.gemorg = ? " +
                                         "group by gemorg.sorteer, gemorg.naam1 " +
                                         "order by gemorg.sorteer, gemorg.naam1;");
            lijst.setString(1, gemorg);
            lijst.executeQuery();
            ResultSet r = lijst.getResultSet();
            while(r.next()) {
                verantwoordelijken.add(r.getInt("verantwoordelijken"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                lijst.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return verantwoordelijken;
            }
        }
    }
    
     
    public List organisatiesPerPlaats(String district, String org) {
        PreparedStatement lijst = null;
        List<Codes> adressen = new ArrayList<Codes>();
        try {
            lijst = con.prepareStatement("SELECT distinct gemorg.refnum, gemorg.naam1, gemorg.straat, gemorg.postcode, gemorg.woonpl, "
                                        + " gemorg.telefoon, gemorg.email, gemorg.website, gemorg.sorteer, gemorg.gemorg,\n"
                                        + " denomin.logo " +
                                         "FROM eav_adressengids.codes, \n" +
                                         "eav_adressengids.verantw, \n" +
                                         "eav_adressengids.denomin,\n" +
                                         "eav_adressengids.gemorg \n" +
                                         "where codes.vref = verantw.vref \n" +
                                         "and denomin.denomin = codes.denomin " +
                                         "and gemorg.gemorg = ?\n" +
                                         "and gemorg.refnum = codes.refnum \n" +
                                         "and gemorg.sorteer = ?\n" +
                                         "order by gemorg.sorteer, gemorg.naam1;");
            lijst.setString(1, org);
            lijst.setString(2, district);
            lijst.executeQuery();
            ResultSet r = lijst.getResultSet();
            while(r.next()) {
                Codes codes = new Codes();
                Gemorg gemorg = new Gemorg();
                Plaatsen plaats = new Plaatsen();
                gemorg.setRefnum(r.getInt("gemorg.refnum"));
                gemorg.setNaam1(r.getString("gemorg.naam1"));
                gemorg.setStraat(r.getString("gemorg.straat"));
                gemorg.setPostcode(r.getString("gemorg.postcode"));
                gemorg.setWoonpl(r.getString("gemorg.woonpl"));
                gemorg.setTelefoon(r.getString("gemorg.telefoon"));
                gemorg.setEmail(r.getString("gemorg.email"));
                gemorg.setWebsite(r.getString("gemorg.website"));
                plaats.setSorteer(r.getString("gemorg.sorteer"));
                gemorg.setGemorg(r.getString("gemorg.gemorg"));
                gemorg.setSorteer(plaats);
                Denomin denominatie = new Denomin();
                denominatie.setLogo(r.getString("denomin.logo"));
                codes.setDenomin(denominatie);
                codes.setRefnum(gemorg);
                
                adressen.add(codes);
                
                
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                lijst.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return adressen;
            }
        }
    }
    
    public List organisaties(String org) {
        PreparedStatement lijst = null;
        List<Codes> adressen = new ArrayList<Codes>();
        try {
            lijst = con.prepareStatement("SELECT distinct gemorg.refnum, gemorg.naam1, gemorg.straat, gemorg.postcode, gemorg.woonpl, "
                                        + " gemorg.telefoon, gemorg.email, gemorg.website, gemorg.gemorg,\n"
                                        + " denomin.logo " +
                                         "FROM eav_adressengids.codes, \n" +
                                         "eav_adressengids.verantw, \n" +
                                         "eav_adressengids.denomin,\n" +
                                         "eav_adressengids.gemorg \n" +
                                         "where codes.vref = verantw.vref \n" +
                                         "and denomin.denomin = codes.denomin " +
                                         "and gemorg.gemorg = ?\n" +
                                         "and gemorg.refnum = codes.refnum \n" +
                                         "order by gemorg.naam1;");
            lijst.setString(1, org);
            lijst.executeQuery();
            ResultSet r = lijst.getResultSet();
            while(r.next()) {
                Codes codes = new Codes();
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("gemorg.refnum"));
                gemorg.setNaam1(r.getString("gemorg.naam1"));
                gemorg.setStraat(r.getString("gemorg.straat"));
                gemorg.setPostcode(r.getString("gemorg.postcode"));
                gemorg.setWoonpl(r.getString("gemorg.woonpl"));
                gemorg.setTelefoon(r.getString("gemorg.telefoon"));
                gemorg.setEmail(r.getString("gemorg.email"));
                gemorg.setWebsite(r.getString("gemorg.website"));
                gemorg.setGemorg(r.getString("gemorg.gemorg"));
                Denomin denominatie = new Denomin();
                denominatie.setLogo(r.getString("denomin.logo"));
                codes.setDenomin(denominatie);
                codes.setRefnum(gemorg);
                
                adressen.add(codes);
                
                
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                lijst.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return adressen;
            }
        }
    }
    public List getVerantwPerKerk(int refnum, String org) {
        PreparedStatement lijst = null;
        List<Codes> adressen = new ArrayList<Codes>();
        try {
            lijst = con.prepareStatement("SELECT distinct gemorg.refnum, gemorg.naam1, gemorg.straat, gemorg.postcode, gemorg.woonpl, gemorg.telefoon, gemorg.email, gemorg.website, gemorg.sorteer,\n" +
                                         "verantw.titel, verantw.naam1, verantw.voornaam, verantw.naam2, verantw.straat, verantw.postcode, verantw.woonpl, verantw.telefoon, verantw.email,\n" +
                                         "codes.funccode, codes.funccod2, codes.volgnum \n" +
                                         "FROM eav_adressengids.codes, \n" +
                                         "eav_adressengids.verantw, \n" +
                                         "eav_adressengids.gemorg \n" +
                                         "where codes.vref = verantw.vref \n" +
                                         "and gemorg.gemorg = ?\n" +
                                         "and gemorg.refnum = codes.refnum \n" +
                                         "and gemorg.refnum = ?\n" +
                                         "order by codes.volgnum, gemorg.sorteer, gemorg.naam1;");
            lijst.setString(1, org);
            lijst.setInt(2, refnum);
            lijst.executeQuery();
            ResultSet r = lijst.getResultSet();
            while(r.next()) {
                Codes codes = new Codes();
                Verantw verantwoordelijken = new Verantw();
                Gemorg gemorg = new Gemorg();
                Plaatsen plaats = new Plaatsen();
                gemorg.setRefnum(r.getInt("gemorg.refnum"));
                gemorg.setNaam1(r.getString("gemorg.naam1"));
                gemorg.setStraat(r.getString("gemorg.straat"));
                gemorg.setPostcode(r.getString("gemorg.postcode"));
                gemorg.setWoonpl(r.getString("gemorg.woonpl"));
                gemorg.setTelefoon(r.getString("gemorg.telefoon"));
                gemorg.setEmail(r.getString("gemorg.email"));
                gemorg.setWebsite(r.getString("gemorg.website"));
                plaats.setSorteer(r.getString("gemorg.sorteer"));
                gemorg.setSorteer(plaats);
                verantwoordelijken.setNaam1(r.getString("verantw.naam1"));
                verantwoordelijken.setNaam2(r.getString("verantw.naam2"));
                verantwoordelijken.setVoornaam(r.getString("verantw.voornaam"));
                verantwoordelijken.setStraat(r.getString("verantw.straat"));
                verantwoordelijken.setPostcode(r.getString("verantw.postcode"));
                verantwoordelijken.setWoonpl(r.getString("verantw.woonpl"));
                verantwoordelijken.setTelefoon(r.getString("verantw.telefoon"));
                codes.setFunccode(r.getString("codes.funccode"));
                codes.setFunccod2(r.getString("codes.funccod2"));
                //codes.setVolgnum(r.getInt("codes.volgnum"));
                codes.setVref(verantwoordelijken);
                codes.setRefnum(gemorg);
                
                adressen.add(codes);
                
                
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                lijst.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return adressen;
            }
        }
    }
    
    public List getVerantwPerKerk(String kerk) {
        PreparedStatement lijst = null;
        List<Codes> adressen = new ArrayList<Codes>();
        try {
            lijst = con.prepareStatement("SELECT distinct gemorg.refnum, gemorg.naam1, gemorg.straat, gemorg.postcode, gemorg.woonpl, gemorg.telefoon, gemorg.email, gemorg.website, gemorg.sorteer,\n" +
                                         "verantw.titel, verantw.naam1, verantw.voornaam, verantw.naam2, verantw.straat, verantw.postcode, verantw.woonpl, verantw.telefoon, verantw.email,\n" +
                                         "codes.id, codes.volgnum, codes.funccode, codes.funccod2\n" +
                                         "FROM eav_adressengids.codes, \n" +
                                         "eav_adressengids.verantw, \n" +
                                         "eav_adressengids.gemorg \n" +
                                         "where codes.vref = verantw.vref \n" +
                                         "and gemorg.refnum = codes.refnum \n" +
                                         "and gemorg.naam1 = ?\n" +
                                         "order by gemorg.sorteer, gemorg.naam1;");
            lijst.setString(1, kerk);
            lijst.executeQuery();
            ResultSet r = lijst.getResultSet();
            while(r.next()) {
                Codes codes = new Codes();
                Verantw verantwoordelijken = new Verantw();
                Gemorg gemorg = new Gemorg();
                Plaatsen plaats = new Plaatsen();
                gemorg.setRefnum(r.getInt("gemorg.refnum"));
                gemorg.setNaam1(r.getString("gemorg.naam1"));
                gemorg.setStraat(r.getString("gemorg.straat"));
                gemorg.setPostcode(r.getString("gemorg.postcode"));
                gemorg.setWoonpl(r.getString("gemorg.woonpl"));
                gemorg.setTelefoon(r.getString("gemorg.telefoon"));
                gemorg.setEmail(r.getString("gemorg.email"));
                gemorg.setWebsite(r.getString("gemorg.website"));
                plaats.setSorteer(r.getString("gemorg.sorteer"));
                gemorg.setSorteer(plaats);
                verantwoordelijken.setNaam1(r.getString("verantw.naam1"));
                verantwoordelijken.setNaam2(r.getString("verantw.naam2"));
                verantwoordelijken.setVoornaam(r.getString("verantw.voornaam"));
                verantwoordelijken.setStraat(r.getString("verantw.straat"));
                verantwoordelijken.setPostcode(r.getString("verantw.postcode"));
                verantwoordelijken.setWoonpl(r.getString("verantw.woonpl"));
                verantwoordelijken.setTelefoon(r.getString("verantw.telefoon"));
                codes.setId(r.getInt("codes.id"));
                codes.setVolgnum(r.getInt("codes.volgnum"));
                codes.setFunccode(r.getString("codes.funccode"));
                codes.setFunccod2(r.getString("codes.funccod2"));
                //codes.setVolgnum(r.getInt("codes.volgnum"));
                codes.setVref(verantwoordelijken);
                codes.setRefnum(gemorg);
                
                adressen.add(codes);
                
                
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                lijst.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return adressen;
            }
        }
    }
    public int getHighest() {
        PreparedStatement hoogste = null;
        int hoog = 0;
        try {
            hoogste = con.prepareStatement("select max(id) as hoogste from codes");
            hoogste.executeQuery();
            ResultSet r = hoogste.getResultSet();
            while(r.next()) {
                hoog = r.getInt("hoogste");
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                hoogste.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return hoog;
            }
        }
    }
    
    public void toevoegenCode(Codes code) {
        PreparedStatement toevoegen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            toevoegen = con.prepareStatement("insert into codes values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            toevoegen.setInt(1, code.getId());
            toevoegen.setInt(2, code.getVref().getVref());
            toevoegen.setInt(3, code.getVolgnum());
            toevoegen.setInt(4, code.getRefnum().getRefnum());
            toevoegen.setInt(5, code.getDenomin().getDenomin());
            toevoegen.setString(6, code.getFunccode());
            toevoegen.setInt(7, code.getPrincode());
            toevoegen.setString(8, code.getClientnr());
            toevoegen.setDate(9, datum);
            toevoegen.setString(10, code.getFunccod2());
            toevoegen.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                toevoegen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public List<Codes> getGemorgsbyVref(int vref) {
        PreparedStatement strSQL = null;
        List<Codes> codes = new ArrayList<Codes>();
        try {
            strSQL = con.prepareStatement("SELECT gemorg.refnum, gemorg.naam1, id, funccode, clientnr, funccod2, volgnum, codes.princode FROM eav_adressengids.codes, eav_adressengids.gemorg where codes.refnum = gemorg.refnum and codes.vref = ?;");
            strSQL.setInt(1, vref);
            strSQL.execute();
            ResultSet r = strSQL.getResultSet();
            while(r.next()) {
                Codes code = new Codes();
                Gemorg gemorg = new Gemorg();
                gemorg.setRefnum(r.getInt("gemorg.refnum"));
                gemorg.setNaam1(r.getString("gemorg.naam1"));
                code.setId(r.getInt("id"));
                code.setRefnum(gemorg);
                code.setFunccode(r.getString("funccode"));
                code.setClientnr(r.getString("clientnr"));
                code.setFunccod2(r.getString("funccod2"));
                code.setVolgnum(r.getInt("volgnum"));
                code.setPrincode(r.getInt("codes.princode"));
                
                codes.add(code);
            } 
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                strSQL.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                return codes;
            }
        }
    }
    
    public void deleteCodes(int id) {
        PreparedStatement verwijderen = null;
        try {
            verwijderen = con.prepareStatement("delete from codes where id = ?;");
            verwijderen.setInt(1, id);
            verwijderen.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                verwijderen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void updateCodes(Codes code) {
        PreparedStatement aanpassen = null;
        try {
            GregorianCalendar update = new GregorianCalendar();
            java.sql.Date datum = new java.sql.Date(update.getTimeInMillis());
            aanpassen = con.prepareStatement("update codes set volgnum = ?, "
                    + "funccode = ?, princode = ?, clientnr = ?, codes.update = ?, funccod2 = ? where id = ?;");
            aanpassen.setInt(1, code.getVolgnum());
            aanpassen.setString(2, code.getFunccode());
            aanpassen.setInt(3, code.getPrincode());
            aanpassen.setString(4, code.getClientnr());
            aanpassen.setDate(5, datum);
            aanpassen.setString(6, code.getFunccod2());
            aanpassen.setInt(7, code.getId());
            aanpassen.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                aanpassen.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public List geefAlleVerantwoordelijken() {
        PreparedStatement allen = null;
        List<Codes> verantwoordelijken = new ArrayList<Codes>();
        try {
            allen = con.prepareStatement("SELECT distinct verantw.naam1, verantw.voornaam, verantw.naam2, codes.volgnum, "
                    + "verantw.telefoon, gemorg.sorteer FROM eav_adressengids.codes, gemorg, verantw where codes.refnum = gemorg.refnum "
                    + "and verantw.vref = codes.vref order by verantw.naam1;");
            allen.execute();
            ResultSet r = allen.getResultSet();
            while(r.next()) {
                Verantw verantw = new Verantw();
                Codes code = new Codes();
                Gemorg gemorg = new Gemorg();
                Plaatsen sorteer = new Plaatsen();
                verantw.setNaam1(r.getString("verantw.naam1"));
                verantw.setVoornaam(r.getString("verantw.voornaam"));
                verantw.setNaam2(r.getString("verantw.naam2"));
                verantw.setTelefoon(r.getString("verantw.telefoon"));
                sorteer.setSorteer(r.getString("gemorg.sorteer"));
                gemorg.setSorteer(sorteer);
                code.setRefnum(gemorg);
                code.setVref(verantw);
                            
                
                verantwoordelijken.add(code);
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
