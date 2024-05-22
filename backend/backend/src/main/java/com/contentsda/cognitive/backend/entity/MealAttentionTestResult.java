package com.contentsda.cognitive.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "meal_attention_test_result")
@Getter
@Setter
public class MealAttentionTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meal_result", nullable = false)
    private Boolean mealResult;

    @Column(name = "meal_count", nullable = false)
    private Integer mealCount;

    @OneToOne(mappedBy = "mealAttentionTestResult", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ATestResult aTestResult;
}
