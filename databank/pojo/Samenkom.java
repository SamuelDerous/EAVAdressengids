package databank.pojo;

import java.util.GregorianCalendar;

public class Samenkom {
	private Gemorg refnum;
	private String naam1, 
                naam2, 
                straat, 
                postcode, 
                woonpl, 
                telefoon, 
                dag;
	private GregorianCalendar uur, update;
	private int princode;
	
	public Gemorg getRefnum() {
		return refnum;
	}
	public void setRefnum(Gemorg refnum) {
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
	public String getDag() {
		return dag;
	}
	public void setDag(String dag) {
		this.dag = dag;
	}
	public GregorianCalendar getUur() {
		return uur;
	}
	public void setUur(GregorianCalendar uur) {
		this.uur = uur;
	}
	public GregorianCalendar getUpdate() {
		return update;
	}
	public void setUpdate(GregorianCalendar update) {
		this.update = update;
	}
	public int getPrincode() {
		return princode;
	}
	public void setPrincode(int princode) {
		this.princode = princode;
	}

	
}
