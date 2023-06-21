package com.example.StudyCase.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
public class Location extends Base{
    private double latitude;
    private double longitude;
//    private double radius; Buradamı yoksa ana kısımda mı kullansam emin olamadım.
}
