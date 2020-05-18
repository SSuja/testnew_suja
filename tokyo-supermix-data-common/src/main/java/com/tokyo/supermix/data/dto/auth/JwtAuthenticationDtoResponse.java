package com.tokyo.supermix.data.dto.auth;

public class JwtAuthenticationDtoResponse {private String accessToken;
private String tokenType = "Bearer";

public JwtAuthenticationDtoResponse(String accessToken) {
    this.accessToken = accessToken;
}

public String getAccessToken() {
    return accessToken;
}

public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
}

public String getTokenType() {
    return tokenType;
}

public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
}

}
