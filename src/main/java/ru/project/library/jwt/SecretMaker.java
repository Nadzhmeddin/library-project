package ru.project.library.jwt;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.HexFormat;

public class SecretMaker {
    public static void main(String[] args) {
        SecretKey key = Jwts.SIG.HS512.key().build();
        String hexKey = HexFormat.of().formatHex(key.getEncoded());
        System.out.println("key: " + hexKey);
    }
}
