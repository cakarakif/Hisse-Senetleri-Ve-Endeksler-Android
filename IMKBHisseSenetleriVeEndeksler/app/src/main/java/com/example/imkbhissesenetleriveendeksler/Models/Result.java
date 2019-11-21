package com.example.imkbhissesenetleriveendeksler.Models;

public class Result {
	private String sembol;
	private double fiyat;
	private double fark;
	private int hacim;
	private double alis;
	private double satis;
	private String degisim;
	private double günlük_düsük;
	private double günlük_yüksek;
	private int adet;
	private double tavan;
	private double taban;

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

	public double getGünlük_düsük() {
		return günlük_düsük;
	}

	public void setGünlük_düsük(double günlük_düsük) {
		this.günlük_düsük = günlük_düsük;
	}

	public double getGünlük_yüksek() {
		return günlük_yüksek;
	}

	public void setGünlük_yüksek(double günlük_yüksek) {
		this.günlük_yüksek = günlük_yüksek;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public double getTavan() {
		return tavan;
	}

	public void setTavan(double tavan) {
		this.tavan = tavan;
	}

	public double getTaban() {
		return taban;
	}

	public void setTaban(double taban) {
		this.taban = taban;
	}

	@Override
	public String toString() {
		return "Result{" +
				"sembol='" + sembol + '\'' +
				", fiyat=" + fiyat +
				", fark=" + fark +
				", hacim=" + hacim +
				", alis=" + alis +
				", satis=" + satis +
				", degisim='" + degisim + '\'' +
				", günlük_düsük=" + günlük_düsük +
				", günlük_yüksek=" + günlük_yüksek +
				", adet=" + adet +
				", tavan=" + tavan +
				", taban=" + taban +
				'}';
	}
}
