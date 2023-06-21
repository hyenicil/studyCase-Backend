package com.example.StudyCase.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "geometry")
public class Geometry extends Base {
    @OneToOne
    @JoinColumn(name = "location_ıd")
    private Location location;
    @OneToOne
    @JoinColumn(name = "viewport_ıd")
    private Viewport viewport;



}
