package restshop.entities.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import restshop.entities.UporabnikVloga;

@XmlRootElement(name="uporabnikVloge")
public class UporabnikVlogaList {
	
	private List<UporabnikVloga> uporabnikove_vloge;
	
	public UporabnikVlogaList() {
	}

	public UporabnikVlogaList(List<UporabnikVloga> uporabnikove_vloge) {
		super();
		this.uporabnikove_vloge = uporabnikove_vloge;
	}

	@XmlElement(name="uporabnikVloga")
	public List<UporabnikVloga> getUporabnikove_vloge() {
		return uporabnikove_vloge;
	}

	public void setUporabnikove_vloge(List<UporabnikVloga> uporabnikove_vloge) {
		this.uporabnikove_vloge = uporabnikove_vloge;
	}

}
