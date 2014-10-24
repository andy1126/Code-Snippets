/**
 * Usage:
 * init() will generate a pair of keys, use getter to retrieve
 * To store keys in database, call getEncoded() to get byte[]
 * To translate a byte[] into key, call static methods
 * getPrivateFromEncoded() or getPublicFromEncoded()
 */
public class KeyGenerator {
	final private static int keyLength = 1024;
	Key publicKey;
	Key privateKey;
	
	public void init(){
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(keyLength);
			KeyPair kp = kpg.genKeyPair();
			publicKey = kp.getPublic();
			privateKey = kp.getPrivate();
			
		} catch (NoSuchAlgorithmException e) {
			System.err.println("NoSuchAlgorithmException");
			e.printStackTrace();
		}
	}
	
	public static Key getPublicFromEncoded(byte[] encoded) {
		PublicKey publicKey = null;
		try {
			publicKey = KeyFactory.getInstance("RSA").generatePublic(
					new X509EncodedKeySpec(encoded));
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	public static Key getPrivateFromEncoded(byte[] encoded) {
		Key privateKey = null;
		try {
			privateKey = KeyFactory.getInstance("RSA").generatePrivate(
					new PKCS8EncodedKeySpec(encoded));
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	public Key getPublicKey() {
		return publicKey;
	}

	public Key getPrivateKey() {
		return privateKey;
	}