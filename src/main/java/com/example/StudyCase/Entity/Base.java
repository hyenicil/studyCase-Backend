package com.example.StudyCase.Entity;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@RequiredArgsConstructor
public class Base {
    @Id
    private int id;
}
