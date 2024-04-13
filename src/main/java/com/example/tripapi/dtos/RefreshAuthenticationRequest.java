package com.example.tripapi.dtos;



import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshAuthenticationRequest {
    @JsonProperty("refresh_token")
    private String refreshToken;
}
