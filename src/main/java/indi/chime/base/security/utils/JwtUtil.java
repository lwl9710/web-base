package indi.chime.base.security.utils;

import indi.chime.base.security.entities.RsaEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final int EXPIRE_TIME = 7200 * 1000;

    private static final RsaEntity RSAENTITY;

    static {
        String classPath = ClassLoader.getSystemResource("").getPath();
        System.out.println(classPath);
        String publicKeyPath = classPath + "/keys/public.key";
        String privateKeyPath = classPath + "/keys/private.key";
        RSAENTITY = new RsaEntity(publicKeyPath, privateKeyPath);
    }


    public static String createJwtToken(Map<String, Object> claims) {
        long expiretion = System.currentTimeMillis() + EXPIRE_TIME;
        return Jwts.builder()
            .setExpiration(new Date(expiretion))
            .addClaims(claims)
            .signWith(SignatureAlgorithm.RS256, RSAENTITY.getPrivateKey())
            .compact();
    }

    public static Map<String, Object> getJwtTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(RSAENTITY.getPublicKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
