package com.example.StudyCase.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NearbyRequest{
    private String longitude;
    private String latitude;
    private String radius;
}
