package com.example.StudyCase.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Location extends Base{
    @OneToOne
    @JoinColumn(name = "nearbyId")
    private Nearby nearby;
    private String name;
    private String  latitude;
    private String longitude;
    private List<String> types;


}
