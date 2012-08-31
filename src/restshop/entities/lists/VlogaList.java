package restshop.entities.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import restshop.entities.Vloga;

@XmlRootElement(name="vloge")
public class VlogaList {
	
	private List<Vloga> vloge;
	
	public VlogaList() {
	}

	public VlogaList(List<Vloga> vloge) {
		this.vloge = vloge;
	}

	@XmlElement(name="naslov")
	public List<Vloga> getVloge() {
		return vloge;
	}

	public void setVloge(List<Vloga> vloge) {
		this.vloge = vloge;
	}

}
