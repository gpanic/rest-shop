package restshop;

import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import restshop.dao.ArtikelDAO;
import restshop.dao.NaslovDAO;
import restshop.dao.ProizvajalecDAO;
import restshop.dao.UporabnikDAO;
import restshop.entities.Artikel;
import restshop.entities.Naslov;
import restshop.entities.Proizvajalec;
import restshop.entities.Uporabnik;
import restshop.security.Hasher;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONJAXBContext;
import com.sun.jersey.api.json.JSONMarshaller;

public class Test {
	
	public static void main(String[] args) {
		
		/*
		service.delete();
		System.out.println("FINISHED");
		*/
		
		/*
		try {
			JAXBContext jaxbContext=JAXBContext.newInstance(Naslov.class);
			Marshaller jaxbMarshaller=jaxbContext.createMarshaller();
			
			JSONMarshaller jm=JSONJAXBContext.getJSONMarshaller(jaxbMarshaller, jaxbContext);
			StringWriter sw=new StringWriter();
			jm.marshallToJSON(n, sw);

			ClientResponse res=service.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, sw.toString());
			System.out.println(res.toString());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		/*
		Client client=Client.create();
		WebResource service=client.resource("http://localhost:8080/rest-shop/proizvajalci/1");
		
		Naslov n2=new Naslov();
		n2.setId_naslov(3);
		
		Proizvajalec n=new Proizvajalec();
		n.setIme("nekiproizvajalec PUT");
		n.setNaslov(n2);
		
		try {
			JAXBContext jaxbContext=JAXBContext.newInstance(Uporabnik.class);
			Marshaller jaxbMarshaller=jaxbContext.createMarshaller();
			
			JSONMarshaller jm=JSONJAXBContext.getJSONMarshaller(jaxbMarshaller);
			StringWriter sw=new StringWriter();
			jm.marshallToJSON(n, sw);
			
			System.out.println(sw);

			//ClientResponse res=service.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, sw.toString());
			//System.out.println(res);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	
	}
	
}
