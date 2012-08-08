package restshop.entities.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import restshop.entities.Naslov;

@XmlRootElement
public class NaslovList {
	
	private List<Naslov> naslovi;
	
	public NaslovList() {
	}

	public NaslovList(List<Naslov> naslovi) {
		this.naslovi = naslovi;
	}
	
	public List<Naslov> getNaslovi() {
		return naslovi;
	}

	public void setNaslovi(List<Naslov> naslovi) {
		this.naslovi = naslovi;
	}

}
