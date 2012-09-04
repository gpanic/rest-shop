package restshop.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	
	private String algorithm;
	
	public Hasher(String algorithm) {
		this.algorithm=algorithm;
	}
	
	public String hash(String s) {
		String hash=null;
		try {
			MessageDigest md=MessageDigest.getInstance(algorithm);
			md.update(s.getBytes("UTF-8"));
			byte[] digest=md.digest();
			hash=new BigInteger(1, digest).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hash;
	}

}
