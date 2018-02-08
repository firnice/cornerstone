package com.cornerstone;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cornerstone.domain.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * @author: liyl
 * @date: 2018/2/7 上午11:30
 * @since 1.0.0
 */
public class TokenAuthenticationService {

    static final long EXPIRATIONTIME = 432_000_000;     // 5天
    static final String SECRET = "P@ssw02d";            // JWT密码
    static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key

    // JWT生成方法
    public static void addAuthentication(HttpServletResponse response, String username) {



        KeyPair keyPair = JwtRsaUtil.getInstance().getKeyPair("private");

        // 生成JWT
        String JWT = Jwts.builder()
            // 保存权限（角色）
            .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
            // 用户名写入标题
            .setSubject(username)
            // 有效期设置
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            // 签名设置
            //.signWith(SignatureAlgorithm.HS512, SECRET)
            .signWith(SignatureAlgorithm.RS256, keyPair.getPrivate())
            .compact();

        // 将 JWT 写入 body
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().println(Result.fillResultString(0, "", JWT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // JWT验证方法
    public static Authentication getAuthentication(HttpServletRequest request) {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);


        //获取签名私钥
        KeyPair keyPair = JwtRsaUtil.getInstance().getPublicPair("public");

        
        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                // 验签
                .setSigningKey(keyPair.getPublic())
                // 去掉 Bearer
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();

            // 拿用户名
            String user = claims.getSubject();

            // 得到 权限（角色）
            List<GrantedAuthority> authorities =  AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

            // 返回验证令牌
            return user != null ?
                new UsernamePasswordAuthenticationToken(user, null, authorities) :
                null;
        }
        return null;
    }
}
