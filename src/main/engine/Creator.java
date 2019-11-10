package engine;

import engine.exceptions.AlgorithmException;

import javax.crypto.Cipher;
import java.security.NoSuchAlgorithmException;

public class Creator implements CipherCreator {
    public Encryptor createEncryptor(String algorithm, String mode, String key, byte[] iv) throws AlgorithmException {
        // iv is allowed to be null in same cases
        if(algorithm == null || mode == null || key == null){
            throw new IllegalArgumentException("Null-value has been passed.");
        }

        switch (algorithm){
            case "AES":{
                return new AES(mode, key, iv, Cipher.ENCRYPT_MODE);
            }
            case "DES":{
                return new DES(mode, key, iv, Cipher.ENCRYPT_MODE);
            }
            default:{
                throw new AlgorithmException("Not supported algorithm!");
            }

        }
    }

    public Decryptor createDecryptor(String algorithm, String mode, String key, byte[] iv) throws AlgorithmException {
        // iv is allowed to be null in same cases
        if(algorithm == null || mode == null || key == null){
            throw new IllegalArgumentException("Null-value has been passed.");
        }

        switch (algorithm){
            case "AES":{
                return new AES(mode, key, iv, Cipher.DECRYPT_MODE);
            }
            case "DES":{
                return new DES(mode, key, iv, Cipher.DECRYPT_MODE);
            }
            default: {
                throw new AlgorithmException("Not supported algorithm!");
            }
        }
    }
}
