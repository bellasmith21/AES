import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {
	private static final String ALGO = "AES";
	private byte[] keyValue;
	
	public AES(String key) {
		keyValue = key.getBytes();
	}
	
	public String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}
	
	public String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
	
	private Key generateKey() throws Exception{
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}
	
	public static String randomKey() {
		String k = "";
		for(int i = 0; i < 16; i++) {
			k += (Character.toString((char)(Math.random()*128)));
		}
		return k;
	}
	
	public static void main(String args[]) {
		try {
			String key = randomKey();
			System.out.println("Key: "+key);
			AES aes = new AES(key);
			String encdata = aes.encrypt("Isabella Smith");
			System.out.println("Encrypted Data - " + encdata);
			String decdata = aes.decrypt(encdata);
			System.out.println("Decrypted Data - " + decdata);
		} catch (Exception ex) {
			Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
