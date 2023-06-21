package com.example.StudyCase.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Table(name = "location")
public class Location extends Base{
    private double latitude;
    private double longitude;
//    private double radius; Buradamı yoksa ana kısımda mı kullansam emin olamadım.
}
