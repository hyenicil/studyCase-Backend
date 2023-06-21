package com.example.StudyCase.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
//mapped ile bu sınıfı üst sınıf ilan eder.Alt sınıflar için yeniden kullanılabilir hale getiririz.
public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
