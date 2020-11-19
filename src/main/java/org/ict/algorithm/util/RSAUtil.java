package org.ict.algorithm.util;

import com.google.common.collect.Maps;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @see <a href="http://www.ruanyifeng.com/blog/2013/06/rsa_algorithm_part_one.html"></a>
 * @see <a href="https://stackoverflow.com/questions/43840827/how-to-sign-and-validate-rsa-pkcs1-v2-0-signatures-in-java"></a>
 * https://www.javainterviewpoint.com/rsa-encryption-and-decryption/
 * https://crypto.stackexchange.com/questions/29354/why-not-use-oaep-for-signatures#:~:text=One%20good%20reason%20not%20to%20use%20RSAES-OAEP%20for,some%20RSAES-OAEP%20black%20box%20into%20a%20signing%20machine.
 * https://github.com/Pretius/java-rsa-signature/blob/master/src/pretius/rsasignature/RSASignature.java
 * https://github.com/andrewli315/Digital-Signature-with-RSA
 * https://stackoverflow.com/questions/43840827/how-to-sign-and-validate-rsa-pkcs1-v2-0-signatures-in-java
 *
 * RSA Signature Algorithms
 * RSASSA-PSS
 * SHA1WithRSA/PSS
 * SHA224WithRSA/PSS
 * SHA384WithRSA/PSS
 * SHA1withRSAandMGF1
 * SHA256withRSA
 * sha1WithRSA
 * sha384WithRSA
 * sha512WithRSA
 * md2WithRSA
 * md5WithRSA
 */
public class RSAUtil {

    private static Cipher cipher;

    private static final String KEY_TAG_PATTERN = "-----[A-Z ]+-----";
    private static final String RSA_ALGORITHM = "RSA";
    private static final String SHA1_WITH_RSA_ALGORITHM = "SHA1withRSA";

    static{
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //testEncryptDecrypt();
        testSign();
    }

    private static void testSign() {
        try {
            /*
            Map<String, String> map = generateSignKeyPair(1024);
            String publicKey = map.get("publicKey");
            String privateKey = map.get("privateKey");
            */
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDSqS+t56pRD7QStaEjk35DoUhNVor1Y30BSAEE1yjiGgOSxRVb5HYctH4nn4+IhJHNESV5J/8lSiH0wLfhG2KYDnHpl9ZX97sUjWxhXgYj0k6kWzbbQXlEpg4mXgk9FBqXQB0795wY/NLDJqXDT9ynOglY20if7ih2qGYH38H6xQIDAQAB";
            String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANKpL63nqlEPtBK1oSOTfkOhSE1WivVjfQFIAQTXKOIaA5LFFVvkdhy0fiefj4iEkc0RJXkn/yVKIfTAt+EbYpgOcemX1lf3uxSNbGFeBiPSTqRbNttBeUSmDiZeCT0UGpdAHTv3nBj80sMmpcNP3Kc6CVjbSJ/uKHaoZgffwfrFAgMBAAECgYAo1eMIMwAw9npRpiO2YcD4GyuI0l3dc4un0+1eotap/aDzsoCRb5f1uIc75xJLxGb++XPqKHatI9GxQCpk2Ioj1XEBhqELB9zGJ6dBofoOAL9AlvYGWl01WevZvOoKxy4dyi6CxsC0E14wTHrOqy04CY51o8E3w/ErtBH+n18AAQJBAPefYSBba+8cK079jWnvZy/NWzY2+P7LW9SxqR5787BqzsLOtH7ZLlPVBY8IaJSgvdepurMtRDeT6wAFGu81wAECQQDZya/4ZUlbAKTNUPp1JJnrGrrVOp447122QQKNgDwlR91HiZfPGW3NYPizw39d0M3XmlBoxlSEGlswfEbd5TrFAkAXRGSN88kqiI0ROQXmpFYfyb3+VCAFYPpZ+++WK7N0KjUPXPjcym9t9SA2lmWtQYVVFF+0olY8mquELvae74ABAkAcSVXRrVYZu7ur7xiYnmhfYNljWHm0a2KAiXELb9xf+zWCVRyiiWr5gd7LeljQlo4lsqU+9oODpOizI3EQ4PUBAkEA3AMIYQak31vBRsepMVVHI24bETbL37h8ICHuGb6zAJqsGQdD3qo4HWhKNQt66tMJ8TKOixQuzAVCq8/FINZgwQ==";

            System.out.println("publicKey:" + publicKey);
            System.out.println("privateKey:" + privateKey);

            String content = "hello world";
            String signature = sign(content, privateKey);
            boolean isVerified = verify(content, signature, publicKey);

            System.out.println("Content   : " + content);
            System.out.println("Signature : " + signature);
            System.out.println("Verified  : " + isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sign(String content, String privateKeyStr) throws GeneralSecurityException, UnsupportedEncodingException {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);

        Signature signer = Signature.getInstance(SHA1_WITH_RSA_ALGORITHM);
        signer.initSign(privateKey);
        signer.update(content.getBytes(UTF_8));

        // get the signature bytes
        byte[] signatureBytes = signer.sign();

        // encode the byte array into a String
        String signature = Base64.getEncoder().encodeToString(signatureBytes);

        // encode URL characters
        //signature = URLEncoder.encode(signature, UTF_8.name());
        return signature;
    }

    /**
     * Verifies content signature with the public key
     */
    public static boolean verify(String content, String signature, String publicKeyStr) throws GeneralSecurityException, UnsupportedEncodingException {
        PublicKey publicKey = getPublicKey(publicKeyStr);

        Signature verifier = Signature.getInstance(SHA1_WITH_RSA_ALGORITHM);
        verifier.initVerify(publicKey);
        verifier.update(content.getBytes(UTF_8));

        // decode URL characters
        //signature = URLDecoder.decode(signature, UTF_8.name());

        // decode the the String into a byte array
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return verifier.verify(signatureBytes);
    }

    /**
     * Returns {@link PrivateKey} based on the specified private key string
     */
    public static PrivateKey getPrivateKey(String privateKeyStr) throws GeneralSecurityException {
        byte[] privateKeyBytes = keyStringToBytes(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey privateKey = kf.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * Returns {@link PublicKey} based on the specified public key string
     */
    public static PublicKey getPublicKey(String publicKeyStr) throws GeneralSecurityException {
        byte[] publicKeyBytes = keyStringToBytes(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory kf = KeyFactory.getInstance(RSA_ALGORITHM);
        PublicKey publicKey = kf.generatePublic(keySpec);
        return publicKey;
    }

    private static byte[] keyStringToBytes(String keyString) {
        // remove unnecessary characters
        keyString = keyString
                .replaceAll(KEY_TAG_PATTERN, "")
                .replaceAll("\\s+", "");

        // decode string to base64 byte array
        return Base64.getDecoder().decode(keyString);
    }

    private static void testEncryptDecrypt() {
        String filePath = "D:\\workspace\\20200731-basic-algos\\src\\main\\resources\\";
        /*

        Map<String, String> map = generateKeyPair(filePath);
        String publicKey = map.get("publicKey");
        String privateKey = map.get("privateKey");
         */
        String publicKey = readFromFile(filePath  + "public.key");
        String privateKey = readFromFile(filePath + "private.key");

        String raw = "hello world";
        System.out.println("plain content:" + raw);
        String encryptStr = encrypt(publicKey, raw);
        System.out.println("encrypt content:" + encryptStr);
        String decryptStr = decrypt(privateKey, encryptStr);
        System.out.println("decrypt content:" + decryptStr);
    }

    public static void verifySign() throws Exception {
        //original data
        String data = "hello world";
        //Signature with baes64 encoded
        String b64sig = "Oebbp754ozaX+/539cRYomsZSOVhZ8L7NcNbLIw+hUWn0HMLvYjmK/B78ixMMQD+vk1zB6RRo2kyYeqoWka+FQ==";
        //public key with base64 encoded
        String b64pubkey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI5SXpw1SSsM3FN43JVKn4gb+oGXfjL7rCDluqydAyHZ8vV7ySqi8oM1CoHRC9U2ST7IldydsQ+4cjC9xfzexxcCAwEAAQ==";
        //transform the base64 string to byte[]
        byte[] pk = Base64.getDecoder().decode(b64pubkey);
        byte[] sig = Base64.getDecoder().decode(b64sig);
        System.out.println(new String(sig));
        //get PublicKey Object from byte[]
        X509EncodedKeySpec spec = new X509EncodedKeySpec( pk );
        KeyFactory kf = KeyFactory.getInstance( "RSA" );
        PublicKey pubKey = kf.generatePublic( spec );
        //verify the Signature
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify( pubKey );
        signature.update( data.getBytes() );
        boolean ret = signature.verify( sig );
        System.out.println(ret);
        return;
    }

    public static Map<String, String> generateSignKeyPair(int size) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(size);
        KeyPair keys;
        keys = keyGen.genKeyPair();
        byte[] publicKey = keys.getPublic().getEncoded();
        byte[] b64pubkey = Base64.getEncoder().encode(publicKey);

        byte[] privateKey = keys.getPrivate().getEncoded();
        byte[] b64prikey = Base64.getEncoder().encode(privateKey);
        Map<String, String> map = Maps.newHashMap();
        map.put("publicKey", new String(b64pubkey));
        map.put("privateKey", new String(b64prikey));
        return map;
    }

    public static String signSHA256RSA(String input, String privateKeyStr) throws Exception {
        return signRSA(input, privateKeyStr, "SHA256withRSA");
    }
    
    public static String signRSA(String input, String privateKeyStr, String signAlgorithm) throws Exception {
        //decode the private key from base64
        byte[] b1 = Base64.getDecoder().decode(privateKeyStr);
        //get PrivateKey Object from byte[]
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(spec);
        //generate signature with choosing algorithm
        Signature signature = Signature.getInstance(signAlgorithm);
        //init the private key
        signature.initSign(privateKey);
        //update the data which is going to be signed
        signature.update(input.getBytes());
        //sign the data
        byte[] s = signature.sign();
        return Base64.getEncoder().encodeToString(s);
    }

    public static String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        return (new BASE64Encoder()).encode(keyBytes);
    }

    public static Map<String,String> generateKeyPair(String filePath){
        Map<String,String> map = Maps.newHashMap();
        try {
            // Get an instance of the RSA key generator
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096);
            // Generate the KeyPair
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Get the public and private key
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Convert public and private key to string
            String publicKeyString = getKeyString(publicKey);
            String privateKeyString = getKeyString(privateKey);

            // Saving the Keys to the file
            writeToFile(filePath + "public.key", publicKeyString);
            writeToFile(filePath + "private.key", privateKeyString);

            map.put("publicKey", publicKeyString);
            map.put("privateKey", privateKeyString);
            return map;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private static void writeToFile(String filePath, String input) throws IOException {
        Path path = Paths.get(filePath);
        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(input);
        }
    }

    private static String readFromFile(String filePath) {
        String s = "";
        try {
            s = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    /*
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }*/

    public static String encrypt(PublicKey publicKey, String plainText){
        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] enBytes = cipher.doFinal(plainText.getBytes());
            return (new BASE64Encoder()).encode(enBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String fileEncrypt(String publicKeystore, String plainText){
        try {
            FileReader fr = new FileReader(publicKeystore);
            BufferedReader br = new BufferedReader(fr);
            String publicKeyString="";
            String str;
            while((str=br.readLine())!=null){
                publicKeyString+=str;
            }
            br.close();
            fr.close();
            cipher.init(Cipher.ENCRYPT_MODE,getPublicKey(publicKeyString));
            byte[] enBytes = cipher.doFinal(plainText.getBytes());
            return (new BASE64Encoder()).encode(enBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String publicKey, String plainText){
        try {
            cipher.init(Cipher.ENCRYPT_MODE,getPublicKey(publicKey));
            byte[] enBytes = cipher.doFinal(plainText.getBytes());
            return (new BASE64Encoder()).encode(enBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(PrivateKey privateKey, String enStr){
        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] deBytes = cipher.doFinal((new BASE64Decoder()).decodeBuffer(enStr));
            return new String(deBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String privateKey, String enStr){
        try {
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
            byte[] deBytes = cipher.doFinal((new BASE64Decoder()).decodeBuffer(enStr));
            return new String(deBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String fileDecrypt(String privateKeystore, String enStr){
        try {
            FileReader fr = new FileReader(privateKeystore);
            BufferedReader br = new BufferedReader(fr);
            String privateKeyString="";
            String str;
            while((str=br.readLine())!=null){
                privateKeyString+=str;
            }
            br.close();
            fr.close();
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKeyString));
            byte[] deBytes = cipher.doFinal((new BASE64Decoder()).decodeBuffer(enStr));
            return new String(deBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    public static void testRSA() {
        // Get an instance of the RSA key generator
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(4096);

        // Generate the KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the public and private key
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        RSAPublicKeySpec publicKeySpec = null;
        RSAPrivateKeySpec privateKeySpec = null;
        try {
            publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println("public key:" + publicKeySpec.toString());
        System.out.println("private key:" + privateKeySpec.toString());

        // Saving the Key to the file
        String pubKeyFile = "D:\\workspace\\20200731-basic-algos\\src\\main\\resources\\public.key";
        String priKeyFile = "D:\\workspace\\20200731-basic-algos\\src\\main\\resources\\private.key";
        try {
            saveKeyToFile(pubKeyFile, publicKeySpec.getModulus(), publicKeySpec.getPublicExponent());
            saveKeyToFile(priKeyFile, privateKeySpec.getModulus(), privateKeySpec.getPrivateExponent());

            String plainText = "hello friend";
            byte[] cipherTextArray = encrypt(plainText, pubKeyFile);
            String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
            System.out.println("Encrypted Text : " + encryptedText);

            // Decryption
            String decryptedText = decrypt(cipherTextArray, priKeyFile);
            System.out.println("Decrypted Text : " + decryptedText);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*
    private static byte[] encrypt (String plainText, PublicKey publicKey ) throws Exception {
        //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
        Cipher cipher = Cipher.getInstance("RSA");//"RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING"

        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plainText.getBytes()) ;

        return cipherText;
    }

    private static String decrypt (byte[] cipherTextArray, PrivateKey privateKey) throws Exception {
        //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
        Cipher cipher = Cipher.getInstance("RSA");

        //Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        //Perform Decryption
        byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);

        return new String(decryptedTextArray);
    }*/

    /*
    public static byte[] encrypt(String plainText, String fileName) throws Exception {
        Key publicKey = readKeyFromFile(fileName);

        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");//"RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING"

        // Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Perform Encryption
        byte[] cipherText = cipher.doFinal(plainText.getBytes());

        return cipherText;
    }

    public static Key readKeyFromFile(String keyFileName) throws IOException {
        Key key = null;
        InputStream inputStream = new FileInputStream(keyFileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
        try {
            BigInteger modulus = (BigInteger) objectInputStream.readObject();
            BigInteger exponent = (BigInteger) objectInputStream.readObject();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            if (keyFileName.startsWith("public"))
                key = keyFactory.generatePublic(new RSAPublicKeySpec(modulus, exponent));
            else
                key = keyFactory.generatePrivate(new RSAPrivateKeySpec(modulus, exponent));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            objectInputStream.close();
        }
        return key;
    }

    public static String decrypt(byte[] cipherTextArray, String fileName) throws Exception {
        Key privateKey = readKeyFromFile(fileName);

        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");//"RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING"

        // Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // Perform Decryption
        byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);

        return new String(decryptedTextArray);
    }

    public static void saveKeyToFile(String fileName, BigInteger modulus, BigInteger exponent) throws IOException{
        ObjectOutputStream ObjOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        try {
            ObjOutputStream.writeObject(modulus);
            ObjOutputStream.writeObject(exponent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ObjOutputStream.close();
        }
    }*/
}
