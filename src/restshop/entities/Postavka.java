package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Postavka {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_postavka;
	private int kolicina;
	
	@ManyToOne
	private Artikel artikel;
	@ManyToOne
	private Narocilo narocilo;
	
	public Long getId_postavka() {
		return id_postavka;
	}
	
	public void setId_postavka(Long id_postavka) {
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

}
