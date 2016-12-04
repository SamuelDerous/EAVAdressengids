package databank.pojo;

import java.util.GregorianCalendar;

public class Codes {

	private int id, 
                volgnum;
	private Denomin denomin;
	private Verantw vref;
	private Gemorg refnum;
	private String funccode;
	private int princode;
	private String clientnr;
	private GregorianCalendar update;
	private String funccod2;
	
        public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVolgnum() {
		return volgnum;
	}
	public void setVolgnum(int volgnum) {
		this.volgnum = volgnum;
	}
	public Denomin getDenomin() {
		return denomin;
	}
	public void setDenomin(Denomin denomin) {
		this.denomin = denomin;
	}
	public Verantw getVref() {
		return vref;
	}
	public void setVref(Verantw vref) {
		this.vref = vref;
	}
	public Gemorg getRefnum() {
		return refnum;
	}
	public void setRefnum(Gemorg refnum) {
		this.refnum = refnum;
	}
	public String getFunccode() {
		return funccode;
	}
	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}
	public int getPrincode() {
		return princode;
	}
	public void setPrincode(int princode) {
		this.princode = princode;
	}
	public String getClientnr() {
		return clientnr;
	}
	public void setClientnr(String clientnr) {
		this.clientnr = clientnr;
	}
	public GregorianCalendar getUpdate() {
		return update;
	}
	public void setUpdate(GregorianCalendar update) {
		this.update = update;
	}
	public String getFunccod2() {
		return funccod2;
	}
	public void setFunccod2(String funccod2) {
		this.funccod2 = funccod2;
	}
	
	
}
