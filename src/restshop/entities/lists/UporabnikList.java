package restshop.entities.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import restshop.entities.Uporabnik;

@XmlRootElement(name="uporabniki")
public class UporabnikList {
	
	private List<Uporabnik> uporabniki;
	
	public UporabnikList() {
	}

	public UporabnikList(List<Uporabnik> uporabniki) {
		super();
		this.uporabniki = uporabniki;
	}

	@XmlElement(name="uporabnik")
	public List<Uporabnik> getUporabniki() {
		return uporabniki;
	}

	public void setUporabniki(List<Uporabnik> uporabniki) {
		this.uporabniki = uporabniki;
	}

}
