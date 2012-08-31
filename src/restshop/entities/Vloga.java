package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Vloga {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_vloga;
	private String naziv;
	private String opis;
	
	public Vloga() {
	}
	
	@XmlAttribute
	public Long getId_vloga() {
		return id_vloga;
	}
	
	public void setId_vloga(Long id_vloga) {
		this.id_vloga = id_vloga;
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
	
	@Override
	public String toString() {
		return naziv;
	}

}
