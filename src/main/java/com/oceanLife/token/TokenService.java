package com.oceanLife.token;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.oceanLife.dto.LoginRequest;
import com.oceanLife.dto.LoginResponse;
import com.oceanLife.utils.UserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class TokenService {

	private Key secretKey;
	private JwtParser jwtParser;
	
    @Autowired
    private AuthenticationProvider authenticationProvider;
	
    @Value("${jwt.secret}")
    private String secret;
	
    @PostConstruct
    private void init() {
        String key = secret;
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
        
        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public LoginResponse createToken(LoginRequest request) {
    	
        // 封裝帳密
        Authentication authToken = new UsernamePasswordAuthenticationToken(request.getUserAccount(), request.getUserPass());
        
        // 執行帳密認證
        authToken = authenticationProvider.authenticate(authToken);
        
        // 認證成功後取得結果
//        UserDetails userDetails = (UserDetails) authToken.getPrincipal();
        // 以自定義的 UserDetail 接收已認證的使用者
        UserDetail userDetail = (UserDetail) authToken.getPrincipal();
    	
        // 產生 token
//        String accessToken = createAccessToken(userDetails.getUsername());
        String accessToken = createAccessToken(userDetail.getUsername());
        
        String refreshToken = createRefreshToken(userDetail.getUsername());

        // 添加更多資料給登入方
        LoginResponse res = new LoginResponse();
        res.setAccessToken(accessToken);
        res.setRefreshToken(refreshToken);
        
        res.setUserId(userDetail.getId());
        res.setUserAccount(userDetail.getAccount());
        res.setUserRole(userDetail.getRole());

        return res;
    }

    // 創立token
    private String createAccessToken(String username) {
        // 有效時間（毫秒）
        long expirationMillis = Instant.now()
                .plusSeconds(10 * 3600) // 10小時轉換為秒數
                .toEpochMilli();

        // 設置標準內容與自定義內容
        Claims claims = Jwts.claims();
        claims.setSubject("Access Token");
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(expirationMillis));
        claims.put("username", username);

        // 簽名後產生 token
        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }
    
    
    // 創立 refresh token
    private String createRefreshToken(String username) {
        long expirationMillis = Instant.now()
                .plusSeconds(600)
                .getEpochSecond()
                * 1000;

        Claims claims = Jwts.claims();
        claims.setSubject("Refresh Token");
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(expirationMillis));
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }
    
    
    public String refreshAccessToken(String refreshToken) {
        Map<String, Object> payload = parseToken(refreshToken);
        String username = (String) payload.get("username");
        
        return createAccessToken(username);
    }
    
    
    // 解析token
    public Map<String, Object> parseToken(String token) {
    	System.out.println("token= "+token);
    	
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return new HashMap<>(claims);
    }
    
    
    
}
