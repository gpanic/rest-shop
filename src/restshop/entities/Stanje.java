package restshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Stanje {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_stanje;
	@Column(unique=true, nullable=false)
	private String naziv;
	private String opis;
	
	public Stanje() {
	}

	@XmlAttribute
	public int getId_stanje() {
		return id_stanje;
	}

	public void setId_stanje(int id_stanje) {
		this.id_stanje = id_stanje;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
}
