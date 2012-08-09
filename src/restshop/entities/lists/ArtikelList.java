package restshop.entities.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import restshop.entities.Artikel;

@XmlRootElement(name="artikli")
public class ArtikelList {
	
	private List<Artikel> artikli;
	
	public ArtikelList() {
	}

	public ArtikelList(List<Artikel> artikli) {
		super();
		this.artikli = artikli;
	}

	@XmlElement(name="artikel")
	public List<Artikel> getArtikli() {
		return artikli;
	}

	public void setArtikli(List<Artikel> artikli) {
		this.artikli = artikli;
	}
	
}
