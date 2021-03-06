package restshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Uporabnik {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_uporabnik;
	@Column(unique=true, nullable=false)
	private String up_ime;
	private String e_naslov;
	private String geslo;
	private String ime;
	private String priimek;
		
	@OneToOne
	private Naslov naslov;
	
	public Uporabnik() {
	}
	
	@XmlAttribute
	public int getId_uporabnik() {
		return id_uporabnik;
	}

	public void setId_uporabnik(int id_uporabnik) {
		this.id_uporabnik = id_uporabnik;
	}

	public String getUp_ime() {
		return up_ime;
	}

	public void setUp_ime(String up_ime) {
		this.up_ime = up_ime;
	}

	public String getE_naslov() {
		return e_naslov;
	}

	public void setE_naslov(String e_naslov) {
		this.e_naslov = e_naslov;
	}

	public String getGeslo() {
		return geslo;
	}

	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPriimek() {
		return priimek;
	}

	public void setPriimek(String priimek) {
		this.priimek = priimek;
	}

	public Naslov getNaslov() {
		return naslov;
	}

	public void setNaslov(Naslov naslov) {
		this.naslov = naslov;
	}

}
