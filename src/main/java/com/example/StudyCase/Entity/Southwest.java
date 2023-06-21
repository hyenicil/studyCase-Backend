package com.example.StudyCase.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "southwest")
public class Southwest extends Base {
    private double latitude;
    private double longitude;
}
