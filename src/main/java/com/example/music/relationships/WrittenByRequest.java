package com.example.music.relationships;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class WrittenByRequest {

    private Integer artistId;
    private Integer itemId;

}
