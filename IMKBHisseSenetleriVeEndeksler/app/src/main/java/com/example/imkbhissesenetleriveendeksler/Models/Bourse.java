package com.example.imkbhissesenetleriveendeksler.Models;

public class Bourse implements Comparable<Bourse>{

	private String sembol;
	private double fiyat;
	private double fark;
	private int hacim;
	private double alis;
	private double satis;
	private String degisim;

	public Bourse(String sembol, double fiyat, double fark, int hacim, double alis, double satis, String degisim) {
		this.sembol = sembol;
		this.fiyat = fiyat;
		this.fark = fark;
		this.hacim = hacim;
		this.alis = alis;
		this.satis = satis;
		this.degisim = degisim;
	}

	public int compareTo(Bourse gelen) {//sort işlemi için eklendi.
		if(gelen.getHacim() == hacim) {
			return 1;
		} else if(gelen.getHacim() > hacim) {
			return 0;
		} else {
			return -1;
		}
	}


	public String getSembol() {
		return sembol;
	}

	public void setSembol(String sembol) {
		this.sembol = sembol;
	}

	public double getFiyat() {
		return fiyat;
	}

	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}

	public double getFark() {
		return fark;
	}

	public void setFark(double fark) {
		this.fark = fark;
	}

	public int getHacim() {
		return hacim;
	}

	public void setHacim(int hacim) {
		this.hacim = hacim;
	}

	public double getAlis() {
		return alis;
	}

	public void setAlis(double alis) {
		this.alis = alis;
	}

	public double getSatis() {
		return satis;
	}

	public void setSatis(double satis) {
		this.satis = satis;
	}

	public String getDegisim() {
		return degisim;
	}

	public void setDegisim(String degisim) {
		this.degisim = degisim;
	}

	@Override
	public String toString() {
		return "Bourse{" +
				"sembol='" + sembol + '\'' +
				", fiyat=" + fiyat +
				", fark=" + fark +
				", hacim=" + hacim +
				", alis=" + alis +
				", satis=" + satis +
				", degisim='" + degisim + '\'' +
				'}';
	}
}
