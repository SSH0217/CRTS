package com.contentsda.cognitive.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "c_test_result")
@Getter
@Setter
public class CTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "test_result_id", unique = true)
    @JsonBackReference
    private TestResult testResult;

    @Column(name = "memory_score", nullable = false)
    private Integer memoryScore;

    @Column(name = "visuospatial_score", nullable = false)
    private Integer visuospatialScore;

    @Column(name = "remember_score", nullable = false)
    private Integer rememberScore;

    @Column(name = "attention_score", nullable = false)
    private Integer attentionScore;

    @Column(name = "execute_score", nullable = false)
    private Integer executeScore;

    @OneToMany(mappedBy = "cTestResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExecuteLog> executeLogList;

}
