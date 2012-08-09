package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Naslov {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_naslov;
	private String ulica;
	private int posta;
	private String kraj;
	private String drzava;
	
	@OneToOne(mappedBy="naslov")
	private Uporabnik uporabnik;
	@OneToOne(mappedBy="naslov")
	private Proizvajalec proizvajalec;
	
	public Naslov() {
	}
	
	@XmlAttribute
	public Long getId_naslov() {
		return id_naslov;
	}

	public void setId_naslov(Long id_naslov) {
		this.id_naslov = id_naslov;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getPosta() {
		return posta;
	}

	public void setPosta(int posta) {
		this.posta = posta;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public Uporabnik getUporabnik() {
		return uporabnik;
	}

	public void setUporabnik(Uporabnik uporabnik) {
		this.uporabnik = uporabnik;
	}

	public Proizvajalec getProizvajalec() {
		return proizvajalec;
	}

	public void setProizvajalec(Proizvajalec proizvajalec) {
		this.proizvajalec = proizvajalec;
	}

	@Override
	public String toString() {
		return "ID: "+id_naslov+" Uporabnik: "+uporabnik.getUp_ime()+" Proizvajalec: "+proizvajalec.getIme()+" Ulica: "+ulica+" Posta: "+posta+" Kraj: "+kraj+" Drzava: "+drzava;
	}

}
