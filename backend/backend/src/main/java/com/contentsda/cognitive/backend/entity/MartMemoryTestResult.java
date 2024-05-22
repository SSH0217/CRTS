package com.contentsda.cognitive.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mart_memory_test_result")
@Getter
@Setter
public class MartMemoryTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mart_memory_result1", nullable = false)
    private Boolean martMemoryResult1;

    @Column(name = "mart_memory_result2", nullable = false)
    private Boolean martMemoryResult2;

    @Column(name = "mart_memory_result3", nullable = false)
    private Boolean martMemoryResult3;

    @Column(name = "correct_item1", nullable = false)
    private Integer correctItem1;

    @Column(name = "correct_item2", nullable = false)
    private Integer correctItem2;

    @Column(name = "correct_item3", nullable = false)
    private Integer correctItem3;

    @Column(name = "mart_remember_result", nullable = false)
    private Boolean martRememberResult;

    @Column(name = "remember_correct_item", nullable = false)
    private Integer rememberCorrectItem;

    @OneToOne(mappedBy = "martMemoryTestResult", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ATestResult aTestResult;

}
