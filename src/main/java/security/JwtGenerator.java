package security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	//Metodo para generar el Token por medio de la autenticacion
	public String generarToken(Authentication authentication) {
		String username = authentication.getName();
		
		//Fecha de emision del Token
		Date tiempoActual = new Date();
		//Fecha de expiracion del token
		
		Date expiracionToken = new Date(tiempoActual.getTime() + security.ConstantsSecurity.JWT_EXPIRATION_TOKEN);

		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expiracionToken)
				.signWith(SignatureAlgorithm.HS512, security.ConstantsSecurity.JWT_SIGNATURE)
				.compact();
		
		return token;
	}
	
	//Metodo para extraer un username a partir de un token
	public String obtenerUsernameDeJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(ConstantsSecurity.JWT_SIGNATURE).parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	//Metodo para validar el token
	public Boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(ConstantsSecurity.JWT_SIGNATURE).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT ha expirado o esta incorrecto");
		}
	}
}
