package com.example.StudyCase.Repository;


import com.example.StudyCase.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Boolean existsByLatitudeAndLongitude(String latitude, String longitude);
}
