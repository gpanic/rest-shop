package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Narocilo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_narocilo;
	private double skupaj;
	private String stanje;
	
	@ManyToOne
	private Uporabnik uporabnik;

	public Long getId_narocilo() {
		return id_narocilo;
	}

	public void setId_narocilo(Long id_narocilo) {
		this.id_narocilo = id_narocilo;
	}

	public double getSkupaj() {
		return skupaj;
	}

	public void setSkupaj(double skupaj) {
		this.skupaj = skupaj;
	}

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	public Uporabnik getUporabnik() {
		return uporabnik;
	}

	public void setUporabnik(Uporabnik uporabnik) {
		this.uporabnik = uporabnik;
	}
	
	@Override
	public String toString() {
		return "ID: "+id_narocilo+" Skupaj: "+skupaj+" Stanje: "+stanje+" Uporabnik: "+uporabnik.getUp_ime();
	}

}
