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
