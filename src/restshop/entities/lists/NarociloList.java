package restshop.entities.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import restshop.entities.Narocilo;

@XmlRootElement(name="narocila")
public class NarociloList {
	
	private List<Narocilo> narocila;
	
	public NarociloList() {
	}

	public NarociloList(List<Narocilo> narocila) {
		this.narocila = narocila;
	}

	@XmlElement(name="narocilo")
	public List<Narocilo> getNarocila() {
		return narocila;
	}

	public void setNarocila(List<Narocilo> narocila) {
		this.narocila = narocila;
	}
	
	

}
