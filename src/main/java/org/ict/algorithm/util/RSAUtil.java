package org.ict.algorithm.util;

import com.google.common.collect.Maps;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.util.Map;

/**
 * @see <a href="http://www.ruanyifeng.com/blog/2013/06/rsa_algorithm_part_one.html"></a>
 * @see <a href="https://stackoverflow.com/questions/43840827/how-to-sign-and-validate-rsa-pkcs1-v2-0-signatures-in-java"></a>
 * https://www.javainterviewpoint.com/rsa-encryption-and-decryption/
 * https://crypto.stackexchange.com/questions/29354/why-not-use-oaep-for-signatures#:~:text=One%20good%20reason%20not%20to%20use%20RSAES-OAEP%20for,some%20RSAES-OAEP%20black%20box%20into%20a%20signing%20machine.
 * https://github.com/Pretius/java-rsa-signature/blob/master/src/pretius/rsasignature/RSASignature.java
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
        testEncryptDecrypt();
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
        Signature signature = Signature.getInstance( "SHA256withRSA" );
        signature.initVerify( pubKey );
        signature.update( data.getBytes() );
        boolean ret = signature.verify( sig );
        System.out.println(ret);
        return;
    }

    public static Map<String, String> generateSignKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
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
    }

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
