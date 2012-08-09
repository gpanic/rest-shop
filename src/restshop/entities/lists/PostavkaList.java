package restshop.entities.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import restshop.entities.Postavka;

@XmlRootElement(name="postavke")
public class PostavkaList {
	
	private List<Postavka> postavke;
	
	public PostavkaList() {
	}
	
	public PostavkaList(List<Postavka> postavke) {
		super();
		this.postavke = postavke;
	}

	@XmlElement(name="postavka")
	public List<Postavka> getPostavke() {
		return postavke;
	}

	public void setPostavke(List<Postavka> postavke) {
		this.postavke = postavke;
	}

}
