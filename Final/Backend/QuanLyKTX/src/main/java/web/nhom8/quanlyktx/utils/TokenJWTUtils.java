package web.nhom8.quanlyktx.utils;

import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;
import java.net.Authenticator;
import java.util.Date;

public class TokenJWTUtils {
    static final long   EXPIRATIONTIME  = 86_400_000; // 1 day
    static final String SECRET          = "ABCDEFGHIK";
    static final String TOKEN_PREFIX    = "Bearer";
    static final String HEADER_STRING   = "Authorization";

    public static String generateToken(String UserId, String RoleCode) {
        long expirationTime = EXPIRATIONTIME;
        return Jwts.builder()
                .setId(UserId)
                .setSubject(RoleCode)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static JWTParseObject getUserIdAndRoleCodeFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        JWTParseObject jwtParseObject = new JWTParseObject();

        jwtParseObject.setUserId(claims.getId());
        jwtParseObject.setRoleCode(claims.getSubject());
        return jwtParseObject;
    }

    public static boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken.replace(TOKEN_PREFIX, ""));
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        } catch (NullPointerException e) {
            System.out.println("Authorization is NULL");
        }
        return false;
    }
}
