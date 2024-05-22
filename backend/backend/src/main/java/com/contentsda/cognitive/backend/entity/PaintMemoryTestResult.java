package com.contentsda.cognitive.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "paint_memory_test_result")
@Getter
@Setter
public class PaintMemoryTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paint_memory_result1", nullable = false)
    private Boolean paintMemoryResult1;

    @Column(name = "paint_memory_result2", nullable = false)
    private Boolean paintMemoryResult2;

    @Column(name = "paint_memory_result3", nullable = false)
    private Boolean paintMemoryResult3;

    @Column(name = "correct_paint1", nullable = false)
    private Integer correctPaint1;

    @Column(name = "correct_paint2", nullable = false)
    private Integer correctPaint2;

    @Column(name = "correct_paint3", nullable = false)
    private Integer correctPaint3;

    @Column(name = "paint_remember_Result", nullable = false)
    private Boolean paintRememberResult;

    @Column(name = "remember_correct_paint", nullable = false)
    private Integer rememberCorrectPaint;

    @OneToOne(mappedBy = "paintMemoryTestResult", cascade = CascadeType.ALL)
    @JsonManagedReference
    private BTestResult bTestResult;
}
