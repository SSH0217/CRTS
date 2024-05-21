package com.contentsda.cognitive.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "separate_visuospatial_test_result")
@Getter
@Setter
public class SeparateVisuospatialTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "separate_result", nullable = false)
    private Boolean separateResult;

    @Column(name = "separate_count", nullable = false)
    private Integer separateCount;

    @Column(name = "separate_plastic_count", nullable = false)
    private Integer separatePlasticCount;

    @Column(name = "separate_can_count", nullable = false)
    private Integer separateCanCount;

    @Column(name = "separate_glass_count", nullable = false)
    private Integer separateGlassCount;

    @Column(name = "separate_vinyl_count", nullable = false)
    private Integer separateVinylCount;

    @Column(name = "separate_time", nullable = false)
    private Integer separateTime;

    @OneToOne(mappedBy = "separateVisuospatialTestResult", cascade = CascadeType.ALL)
    private ATestResult aTestResult;
}
