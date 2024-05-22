package com.contentsda.cognitive.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "a_test_result")
@Getter
@Setter
public class ATestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "test_result_id", unique = true)
    @JsonBackReference
    private TestResult testResult;

    @OneToOne
    @JoinColumn(name = "mart_memory_test_result", unique = true)
    @JsonBackReference
    private MartMemoryTestResult martMemoryTestResult;

    @OneToOne
    @JoinColumn(name = "separate_visuospatial_test_result", unique = true)
    @JsonBackReference
    private SeparateVisuospatialTestResult separateVisuospatialTestResult;

    @OneToOne
    @JoinColumn(name = "meal_attention_test_result", unique = true)
    @JsonBackReference
    private MealAttentionTestResult mealAttentionTestResult;

    @OneToOne
    @JoinColumn(name = "microwave_execute_test_result", unique = true)
    @JsonBackReference
    private MicrowaveExecuteTestResult microwaveExecuteTestResult;
}
