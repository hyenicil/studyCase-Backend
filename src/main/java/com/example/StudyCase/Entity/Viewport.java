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
@Table(name = "viewport")
public class Viewport extends Base{
    @OneToOne
    @JoinColumn(name = "northeast_ıd")
    private Northeast northeast;
    @OneToOne
    @JoinColumn(name = "southwest_ıd")
    private Southwest southwest;
}
