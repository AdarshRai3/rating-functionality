package com.bootcodingtask.rating_functionality.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MockdrivesResponse {
    private Integer mock_drive_id;
    private String title;
    private BigDecimal avg_rating;
    private String created_at;
    private List<String> questions;
}
