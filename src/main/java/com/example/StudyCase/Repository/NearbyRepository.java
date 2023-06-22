package com.example.StudyCase.Repository;

import com.example.StudyCase.Entity.Nearby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NearbyRepository extends JpaRepository<Nearby, Integer> {
    boolean existsByLatitudeAndLongitudeAndRadius(String latituda, String longitude,int radius);

    Nearby findByLatitudeAndLongitudeAndRadius(String latitude, String longitude, int radius);
}
