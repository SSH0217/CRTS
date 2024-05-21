package com.contentsda.cognitive.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "microwave_execute_test_result")
@Getter
@Setter
public class MicrowaveExecuteTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "microwave_result", nullable = false)
    private Boolean microwaveResult;

    @Column(name = "microwave_time", nullable = false)
    private Integer microwaveTime;

    @OneToOne(mappedBy = "microwaveExecuteTestResult", cascade = CascadeType.ALL)
    private ATestResult aTestResult;

    @OneToMany(mappedBy = "microwaveExecuteTestResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExecuteLog> executeLogList;
}
