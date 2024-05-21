package com.contentsda.cognitive.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "desk_visuospatial_test_result")
@Getter
@Setter
public class DeskVisuospatialTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desk_result", nullable = false)
    private Boolean deskResult;

    @Column(name = "desk_count", nullable = false)
    private Integer deskCount;

    @Column(name = "desk_time", nullable = false)
    private Integer deskTime;

    @Column(name = "desk_book_Result", nullable = false)
    private Boolean deskBookResult;

    @Column(name = "desk_book_count", nullable = false)
    private Integer deskBookCount;

    @Column(name = "desk_writing_Result", nullable = false)
    private Boolean deskWritingResult;

    @Column(name = "desk_writing_count", nullable = false)
    private Integer deskWritingCount;

    @OneToOne(mappedBy = "deskVisuospatialTestResult", cascade = CascadeType.ALL)
    private BTestResult bTestResult;
}
