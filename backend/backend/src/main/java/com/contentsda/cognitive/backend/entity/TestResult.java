package com.contentsda.cognitive.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_result")
@Setter
@Getter
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_start_time", nullable = false)
    private LocalDateTime testStartTime;

    @Column(name = "test_end_time", nullable = false)
    private LocalDateTime testEndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_subject")
    @JsonBackReference
    private TestSubject testSubject;

    @OneToOne(mappedBy = "testResult", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ATestResult aTestResult;

    @OneToOne(mappedBy = "testResult", cascade = CascadeType.ALL)
    @JsonManagedReference
    private BTestResult bTestResult;

    @OneToOne(mappedBy = "testResult", cascade = CascadeType.ALL)
    @JsonManagedReference
    private CTestResult cTestResult;
}
