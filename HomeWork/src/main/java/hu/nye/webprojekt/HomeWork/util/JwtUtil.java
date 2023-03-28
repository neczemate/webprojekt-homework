package hu.nye.webprojekt.HomeWork.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.nye.webprojekt.HomeWork.config.JwtConfigurationProperties;
import org.assertj.core.util.DateUtil;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String USERNAME_CLAIM = "username";

    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    private final DateUtil dateUtil;
    private final JwtConfigurationProperties jwtConfigurationProperties;

    public JwtUtil(Algorithm algorithm, JWTVerifier jwtVerifier, DateUtil dateUtil,
                   JwtConfigurationProperties jwtConfigurationProperties) {
        this.algorithm = algorithm;
        this.jwtVerifier = jwtVerifier;
        this.dateUtil = dateUtil;
        this.jwtConfigurationProperties = jwtConfigurationProperties;
    }

    /**
     * Creates and signs a new JWT with the provided username in it.
     *
     * @param username the username which will be the part of the JWT as a claim
     * @return a new signed JWT
     */
    public String createAndSignToken(String username) {
        return JWT.create()
                .withIssuer(jwtConfigurationProperties.getIssuer())
                .withClaim(USERNAME_CLAIM, username)
                .withExpiresAt(createExpirationDate())
                .sign(algorithm);
    }


    public String verifyAndDecodeToken(String token) {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim(USERNAME_CLAIM).asString();
    }

    private Date createExpirationDate() {
        return Date.from(
                dateUtil.now().toInstant().plus(jwtConfigurationProperties.getTokenValidityInMinutes(), ChronoUnit.MINUTES)
        );
    }

}

