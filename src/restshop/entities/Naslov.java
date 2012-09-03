package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Naslov {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_naslov;
	private String ulica;
	private int posta;
	private String kraj;
	private String drzava;
	
	public Naslov() {
	}
	
	@XmlAttribute
	public int getId_naslov() {
		return id_naslov;
	}

	public void setId_naslov(int id_naslov) {
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

}
