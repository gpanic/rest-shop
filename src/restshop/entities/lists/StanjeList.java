package restshop.entities.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import restshop.entities.Stanje;

@XmlRootElement(name="stanja")
public class StanjeList {

	private List<Stanje> stanja;
	
	public StanjeList() {
	}

	public StanjeList(List<Stanje> stanja) {
		this.stanja = stanja;
	}
	
	@XmlElement(name="stanje")
	public List<Stanje> getStanja() {
		return stanja;
	}

	public void setStanja(List<Stanje> stanja) {
		this.stanja = stanja;
	}
	
}
