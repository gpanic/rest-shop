package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Uporabnik {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_uporabnik;
	private String up_ime;
	private String e_naslov;
	private String geslo;
	private String ime;
	private String priimek;
		
	@OneToOne
	private Naslov naslov;
	
	public Long getId_uporabnik() {
		return id_uporabnik;
	}

	public void setId_uporabnik(Long id_uporabnik) {
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

	@Override
	public String toString() {
		return "ID: "+id_uporabnik+"Up. ime: "+up_ime+" E-posta: "+e_naslov;
	}

}
