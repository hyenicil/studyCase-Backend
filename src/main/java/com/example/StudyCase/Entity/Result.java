package com.example.StudyCase.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Table(name = "result")
public class Result extends Base{
    private String name;
    @OneToOne
    @JoinColumn(name = "geometry_ıd")
    private Geometry geometry;
    //Burada normalde bir tane diye düşünülebilir ancak adreste geçerli bu durum.Bir bana için düşünürsek aynı konumdalar sayılabilir.
    private ArrayList<String> types;
    private String vicinity;
    
}