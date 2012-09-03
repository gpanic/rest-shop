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
public class Narocilo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_narocilo;
	
	@ManyToOne(optional=false)
	private Stanje stanje;
	@ManyToOne(optional=false)
	private Uporabnik uporabnik;
	
	public Narocilo() {
	}
	
	@XmlAttribute
	public int getId_narocilo() {
		return id_narocilo;
	}
	
	public void setId_narocilo(int id_narocilo) {
		this.id_narocilo = id_narocilo;
	}

	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	public Uporabnik getUporabnik() {
		return uporabnik;
	}

	public void setUporabnik(Uporabnik uporabnik) {
		this.uporabnik = uporabnik;
	}

}
