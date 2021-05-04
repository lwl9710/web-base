package indi.chime.base.security.utils;

import indi.chime.base.utils.IOUtil;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtil {
    public static final int PUBLIC_KEY_MODE = 1;

    public static final int PRIVATE_KEY_MODE = 2;

    private static final String ALGORITHM_NAME = "RSA";

    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    public static boolean persistentKey(Key key, String filePath) {
        byte[] encoded = key.getEncoded();
        IOUtil.writeStringForFile(ENCODER.encodeToString(encoded), filePath);
        return true;
    }

    public static Key readFileForKey(String filepath, int mode) {
        try {
            byte[] bytes = DECODER.decode(IOUtil.readFileForString(filepath));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            if(mode == PUBLIC_KEY_MODE) {
                return keyFactory.generatePublic(new X509EncodedKeySpec(bytes));
            }
            if(mode == PRIVATE_KEY_MODE) {
                return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bytes));
            }
            return null;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}