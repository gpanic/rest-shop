package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class UporabnikVloga {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_uporabnikvloga;
	private String up_ime;
	private String naziv;
	
	@ManyToOne
	private Vloga vloga;
	@ManyToOne
	private Uporabnik uporabnik;
	
	public UporabnikVloga() {
	}

	public int getId_uporabnikvloga() {
		return id_uporabnikvloga;
	}

	public void setId_uporabnikvloga(int id_uporabnikvloga) {
		this.id_uporabnikvloga = id_uporabnikvloga;
	}

	public String getUp_ime() {
		return up_ime;
	}

	public void setUp_ime(String up_ime) {
		this.up_ime = up_ime;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Vloga getVloga() {
		return vloga;
	}

	public void setVloga(Vloga vloga) {
		this.vloga = vloga;
	}

	public Uporabnik getUporabnik() {
		return uporabnik;
	}

	public void setUporabnik(Uporabnik uporabnik) {
		this.uporabnik = uporabnik;
	}
	
	@Override
	public String toString() {
		return "ID: "+id_uporabnikvloga+" up_ime: "+up_ime+" vloga: "+naziv;
	}

}
