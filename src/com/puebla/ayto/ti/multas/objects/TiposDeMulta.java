package com.puebla.ayto.ti.multas.objects;

public class TiposDeMulta {

	private int id;
	private String tip_multa;
	private String descripcion;
	
	

	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id = id;
	}	
	
	
	
	public String getTip_multa(){
		return tip_multa;
	}	
	public void setTipo(String tip_multa){
	  this.tip_multa = tip_multa;
	}	
	
	
	
	public String getDescripcion(){
		return descripcion;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}	
	
	
}
