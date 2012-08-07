package restshop;

import restshop.dao.NaslovDAO;
public class Test {
	
	public static void main(String[] args) {
		NaslovDAO ndao=new NaslovDAO();
		System.out.println(ndao.read(1));
	}
	
}
