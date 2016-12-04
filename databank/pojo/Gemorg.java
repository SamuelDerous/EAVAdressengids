package databank.pojo;

import java.util.GregorianCalendar;

public class Gemorg {
	private int refnum;
	private String naam1, 
                naam2, 
                straat, 
                postcode, 
                woonpl, 
                telefoon, 
                gsm, 
                email;
	private int eavlid;
	private String gemorg;
	private Denomin denomin;
	private Plaatsen sorteer;
	private String taalcode;
	private int princode;
	private GregorianCalendar update;
	private String website;
	
	public int getRefnum() {
		return refnum;
	}
	public void setRefnum(int refnum) {
		this.refnum = refnum;
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
	public void setGsm(String gsm) {
		this.gsm = gsm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEavlid() {
		return eavlid;
	}
	public void setEavlid(int eavlid) {
		this.eavlid = eavlid;
	}
	public String getGemorg() {
		return gemorg;
	}
	public void setGemorg(String gemorg) {
		this.gemorg = gemorg;
	}
	public Denomin getDenomin() {
		return denomin;
	}
	public void setDenomin(Denomin denomin) {
		this.denomin = denomin;
	}
	public Plaatsen getSorteer() {
		return sorteer;
	}
	public void setSorteer(Plaatsen sorteer) {
		this.sorteer = sorteer;
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	

}
