package com.puebla.ayto.ti.multas.objects;

public class Multa {

	private int id;
	private int multa_id;
	private String multa;
	private int rango_importe_ini;
	private int rango_importe_fin;
	private String fundamento;
	private int tipo;
	private int frecuencia;
	private boolean frecuente;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getMulta_id() {
		return multa_id;
	}
	public void setMulta_id(int multa_id) {
		this.multa_id = multa_id;
	}
	
	public String getMulta() {
		return multa;
	 }
	public void setMulta(String multa) {
		this.multa = multa;
	}
	
	public int getRango_importe_ini() {
		return rango_importe_ini;
	}
	public void setRango_importe_ini(int importe) {
		this.rango_importe_ini =  importe;
	}
	
	public int getRango_importe_fin() {
		return rango_importe_fin;
	}
	public void setRango_importe_fin(int importe) {
		this.rango_importe_fin =  importe;
	}
	
	
	public String getFundamento() {
		return fundamento;
	}
	public void setFundamento(String fundamento) {
		this.fundamento = fundamento;
	}
	
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public int getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuecia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	
	public boolean getFrecuente() {
		return frecuente;
	}
	public void setFrecuente(boolean frecuente) {
		this.frecuente = frecuente;
	}
	
	
}
