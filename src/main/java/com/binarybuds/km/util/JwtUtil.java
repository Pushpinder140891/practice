/*
 * BinaryBuds Limited CONFIDENTIAL
 * Unpublished Copyright (c) 2008-2020 BinaryBuds, All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of BinaryBuds. The intellectual and technical concepts contained
 * herein are proprietary to BinaryBuds and may be covered by U.K. and Foreign Patents, patents in process, and are protected by trade secret and copyright law.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
 * from BinaryBuds.  Access to the source code contained herein is hereby forbidden to anyone except current BinaryBuds employees, managers or contractors who have executed
 * Confidentiality and Non-disclosure agreements explicitly covering such access.
 *
 * The copyright notice above does not evidence any actual or intended publication or disclosure  of  this source code, which includes
 * information that is confidential and/or proprietary, and is a trade secret, of  BinaryBuds. ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC  PERFORMANCE,
 * OR PUBLIC DISPLAY OF OR THROUGH USE  OF THIS  SOURCE CODE  WITHOUT  THE EXPRESS WRITTEN CONSENT OF BinaryBuds IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
 * LAWS AND INTERNATIONAL TREATIES.  THE RECEIPT OR POSSESSION OF  THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS
 * TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR SELL ANYTHING THAT IT  MAY DESCRIBE, IN WHOLE OR IN PART.
 *
 */

package com.binarybuds.km.util;

import com.binarybuds.km.dao.model.UserModel;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
  private final String secretKey = "mysecretkeysecureenoughtoauthenticate";
  private long accessTokenValidity = 60 * 60 * 1000;
  private final JwtParser jwtParser;
  private static final String TOKEN_HEADER = "Authorization";
  private static final String TOKEN_PREFIX = "Bearer ";

  public JwtUtil() {
    this.jwtParser = Jwts.parserBuilder().setSigningKey(getSigningKey()).build();
  }

  private SecretKey getSigningKey() {
    byte[] keyBytes = this.secretKey.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String createToken(UserModel userModel) {
    Claims claims = Jwts.claims().setSubject(userModel.getEmailAddress());
    claims.put("firstName", userModel.getFirstName());
    claims.put("lastName", userModel.getLastName());
    Date tokenCreateTime = new Date();
    Date tokenValidity = new Date(tokenCreateTime.getTime() + Duration.ofMinutes(accessTokenValidity).toMillis());
    return Jwts.builder().setClaims(claims).setExpiration(tokenValidity).signWith(getSigningKey()).compact();
  }

  private Claims parseJwtClaims(String token) {
    return jwtParser.parseClaimsJws(token).getBody();
  }

  public Claims resolveClaims(HttpServletRequest req) {
    try {
      String token = resolveToken(req);
      if (token != null) {
        return parseJwtClaims(token);
      }
      return null;
    } catch (ExpiredJwtException ex) {
      req.setAttribute("expired", ex.getMessage());
      throw ex;
    } catch (Exception ex) {
      req.setAttribute("invalid", ex.getMessage());
      throw ex;
    }
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(TOKEN_HEADER);
    if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
      return bearerToken.substring(TOKEN_PREFIX.length());
    }
    return null;
  }

  public boolean validateClaims(Claims claims) throws AuthenticationException {
    try {
      return claims.getExpiration().after(new Date());
    } catch (Exception e) {
      throw e;
    }
  }

  public String getEmail(Claims claims) {
    return claims.getSubject();
  }

  private List<String> getRoles(Claims claims) {
    return (List<String>) claims.get("roles");
  }

}