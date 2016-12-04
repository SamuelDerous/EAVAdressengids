package databank.pojo;

import java.util.GregorianCalendar;

public class Verantw {

    private int vref;
    private String titel,
            voornaam, 
            naam1, 
            naam2, 
            straat, 
            postcode, 
            woonpl, 
            land, 
            telefoon, 
            gsm, 
            email, 
            taalcode;
    private int princode;
    private GregorianCalendar update;

    public int getVref() {
        return vref;
    }

    public void setVref(int vref) {
        this.vref = vref;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getNaam1() {
        return naam1;
    }

    public void setNaam1(String naam1) {
        this.naam1 = naam1;
    }

    public String getNaam2() {
        return naam2;
    }

    public void setNaam2(String naam2) {
        this.naam2 = naam2;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonpl() {
        return woonpl;
    }

    public void setWoonpl(String woonpl) {
        this.woonpl = woonpl;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaalcode() {
        return taalcode;
    }

    public void setTaalcode(String taalcode) {
        this.taalcode = taalcode;
    }

    public int getPrincode() {
        return princode;
    }

    public void setPrincode(int princode) {
        this.princode = princode;
    }

    public GregorianCalendar getUpdate() {
        return update;
    }

    public void setUpdate(GregorianCalendar update) {
        this.update = update;
    }

}
