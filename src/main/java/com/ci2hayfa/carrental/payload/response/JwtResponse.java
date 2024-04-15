package com.ci2hayfa.carrental.payload.response;

import com.ci2hayfa.carrental.security.services.UserDetailsImpl;

public class JwtResponse {
  private String token;
  private UserDetailsImpl user;

  public JwtResponse(String accessToken,UserDetailsImpl userDetails) {
    this.token = accessToken;
    this.user =  userDetails;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public UserDetailsImpl getUser() {
    return user;
  }

  public void setUser(UserDetailsImpl userDetails) {
    this.user = userDetails;
  }
}
