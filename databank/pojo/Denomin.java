package databank.pojo;

import java.util.GregorianCalendar;

public class Denomin {
	private int denomin;
	private String naam1, 
                naam2, 
                straat, 
                postcode, 
                woonpl, 
                telefoon, 
                gsm, 
                email;
	private String logo;
        private String taalcode;
	private int princode;
	private GregorianCalendar update;
	
	
	public int getDenomin() {
		return denomin;
	}
	public void setDenomin(int denomin) {
		this.denomin = denomin;
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
	public String getTelefoon() {
		return telefoon;
	}
	public void setTelefoon(String telefoon) {
		this.telefoon = telefoon;
	}
	public String getGsm() {
		return gsm;
	}
	public void setGsm(String faxnum) {
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	

}
