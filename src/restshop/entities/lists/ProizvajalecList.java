package restshop.entities.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import restshop.entities.Proizvajalec;

@XmlRootElement(name="proizvajalci")
public class ProizvajalecList {
	
	private List<Proizvajalec> proizvajalci;
	
	public ProizvajalecList() {
	}

	public ProizvajalecList(List<Proizvajalec> proizvajalci) {
		this.proizvajalci = proizvajalci;
	}

	@XmlElement(name="proizvajalec")
	public List<Proizvajalec> getProizvajalci() {
		return proizvajalci;
	}

	public void setProizvajalci(List<Proizvajalec> proizvajalci) {
		this.proizvajalci = proizvajalci;
	}
	
	

}
