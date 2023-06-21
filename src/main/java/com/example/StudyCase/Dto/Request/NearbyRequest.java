package com.example.StudyCase.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearbyRequest {
    private double longitude;
    private double latitude;
    private double radius;
}
