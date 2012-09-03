package restshop;

import restshop.dao.StanjeDAO;
import restshop.dao.UporabnikDAO;
import restshop.dao.UporabnikVlogaDAO;
import restshop.dao.VlogaDAO;
import restshop.entities.Stanje;
import restshop.entities.Uporabnik;
import restshop.entities.UporabnikVloga;
import restshop.entities.Vloga;

public class Initializator {
	
	public static void main(String[] args) {
		
		VlogaDAO vdao=new VlogaDAO();
		UporabnikDAO udao=new UporabnikDAO();
		UporabnikVlogaDAO uvdao=new UporabnikVlogaDAO();
		StanjeDAO sdao=new StanjeDAO();
		
		Vloga v1=new Vloga();
		v1.setNaziv("admin");
		vdao.create(v1);
		
		Vloga v2=new Vloga();
		v2 .setNaziv("uporabnik");
		vdao.create(v2);
		
		Uporabnik u1=new Uporabnik();
		u1.setUp_ime("admin");
		u1.setGeslo("admin");
		udao.create(u1);
		
		Uporabnik u2=new Uporabnik();
		u2.setUp_ime("uporabnik");
		u2.setGeslo("uporabnik");
		udao.create(u2);
		
		UporabnikVloga uv1=new UporabnikVloga();
		uv1.setUporabnik(u1);
		uv1.setUp_ime(u1.getUp_ime());
		uv1.setVloga(v1);
		uv1.setNaziv(v1.getNaziv());
		uvdao.create(uv1);
		
		UporabnikVloga uv2=new UporabnikVloga();
		uv2.setUporabnik(u1);
		uv2.setUp_ime(u1.getUp_ime());
		uv2.setVloga(v2);
		uv2.setNaziv(v2.getNaziv());
		uvdao.create(uv2);
		
		UporabnikVloga uv3=new UporabnikVloga();
		uv3.setUporabnik(u2);
		uv3.setUp_ime(u2.getUp_ime());
		uv3.setVloga(v2);
		uv3.setNaziv(v2.getNaziv());
		uvdao.create(uv3);
		
		Stanje s1=new Stanje();
		s1.setNaziv("kosarica");
		sdao.create(s1);
		
		Stanje s2=new Stanje();
		s2.setNaziv("narocilo");
		sdao.create(s2);
		
		Stanje s3=new Stanje();
		s3.setNaziv("potrjeno");
		sdao.create(s3);
		
		Stanje s4=new Stanje();
		s4.setNaziv("preklicano");
		sdao.create(s4);
		
		Stanje s5=new Stanje();
		s5.setNaziv("koncano");
		sdao.create(s5);
		
		System.out.println("DATABASE INITIALIZED");
		
	}

}
