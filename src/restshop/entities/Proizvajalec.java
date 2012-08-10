package restshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Proizvajalec {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_proizvajalec;
	private String ime;
	private String spletna_stran;
	
	@OneToOne
	private Naslov naslov;
	
	public Proizvajalec() {
	}

	@XmlAttribute
	public Long getId_proizvajalec() {
		return id_proizvajalec;
	}

	public void setId_proizvajalec(Long id_proizvajalec) {
		this.id_proizvajalec = id_proizvajalec;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getSpletna_stran() {
		return spletna_stran;
	}

	public void setSpletna_stran(String spletna_stran) {
		this.spletna_stran = spletna_stran;
	}

	public Naslov getNaslov() {
		return naslov;
	}

	public void setNaslov(Naslov naslov) {
		this.naslov = naslov;
	}
	
	@Override
	public String toString() {
		return "ID: "+id_proizvajalec+" Ime: "+ime+" WWW: "+spletna_stran;
	}

}
