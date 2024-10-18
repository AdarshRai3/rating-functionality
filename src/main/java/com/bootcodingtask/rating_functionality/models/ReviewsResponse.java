package com.bootcodingtask.rating_functionality.models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ReviewsResponse {
    private Integer review_id;
    private Integer user_id;
    private Integer mock_drive_id;
    private BigDecimal rating;
    private String review;
    private String createdAt;
    private String updatedAt;
}
