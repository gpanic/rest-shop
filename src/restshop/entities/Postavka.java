package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Postavka {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_postavka;
	private int kolicina;
	
	@ManyToOne
	private Artikel artikel;
	@ManyToOne
	private Narocilo narocilo;
	
	public Postavka() {
	}
	
	@XmlElement
	public int getId_postavka() {
		return id_postavka;
	}
	
	public void setId_postavka(int id_postavka) {
		this.id_postavka = id_postavka;
	}
	
	public int getKolicina() {
		return kolicina;
	}
	
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	public Artikel getArtikel() {
		return artikel;
	}
	
	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}
	
	public Narocilo getNarocilo() {
		return narocilo;
	}
	
	public void setNarocilo(Narocilo narocilo) {
		this.narocilo = narocilo;
	}
	
	@Override
	public String toString() {
		return "ID: "+id_postavka+" Narocilo ID: "+narocilo.getId_narocilo()+" Artikel: "+artikel.getNaziv()+" Kolicina: "+kolicina;
	}

}
