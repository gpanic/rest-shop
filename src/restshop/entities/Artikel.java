package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Artikel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_artikel;
	private String naziv;
	private Double cena;
	
	@ManyToOne
	private Proizvajalec proizvajalec;

	public Artikel() {
	}
	
	@XmlAttribute
	public int getId_artikel() {
		return id_artikel;
	}

	public void setId_artikel(int id_artikel) {
		this.id_artikel = id_artikel;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Proizvajalec getProizvajalec() {
		return proizvajalec;
	}

	public void setProizvajalec(Proizvajalec proizvajalec) {
		this.proizvajalec = proizvajalec;
	}

}
