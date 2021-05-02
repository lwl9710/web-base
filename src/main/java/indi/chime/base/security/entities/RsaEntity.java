package indi.chime.base.security.entities;

import java.security.*;

public class RsaEntity {
    private static final String ALGORITHM_NAME = "RSA";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    public RsaEntity() {
    }

    public RsaEntity(int size) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_NAME);
            keyPairGenerator.initialize(size);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
