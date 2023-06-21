package com.example.StudyCase.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "geometry")
public class Geometry extends Base {
    @OneToOne
    @JoinColumn(name = "location_ıd")
    private Location location;
    private Viewport viewport;



}
