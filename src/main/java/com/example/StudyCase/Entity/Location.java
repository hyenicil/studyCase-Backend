package com.example.StudyCase.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location extends Base{
    private String name;
    private String  latitude;
    private String longitude;
    private List<String> types;

}
