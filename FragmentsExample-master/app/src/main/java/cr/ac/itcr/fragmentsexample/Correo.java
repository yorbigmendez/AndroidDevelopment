package cr.ac.itcr.fragmentsexample;//package net.sgoliver.android.fragments;

public class Correo 
{
	private String de;
	private String asunto;
	private String texto;
	private String meGusta;
	private int img;

	public Correo(String de, String asunto, String texto, int pImg){
		this.de = de;
		this.asunto = asunto;
		this.texto = texto;
		this.meGusta = "";
		this.img = pImg;
	}
	
	public String getDe(){
		return de;
	}
	
	public String getAsunto(){
		return asunto;
	}
	
	public String getTexto(){
		return texto;
	}

	public String getMeGusta() {
		return meGusta;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setMeGusta(String meGusta) {
		this.meGusta = meGusta;
	}

	public int getImg() {
		return img;
	}

}
