package com.contentsda.cognitive.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "b_test_result")
@Getter
@Setter
public class BTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "test_result_id", unique = true)
    private TestResult testResult;

    @OneToOne
    @JoinColumn(name = "paint_memory_test_result_id", unique = true)
    private PaintMemoryTestResult paintMemoryTestResult;

    @OneToOne
    @JoinColumn(name = "desk_visuospatial_test_result_id", unique = true)
    private DeskVisuospatialTestResult deskVisuospatialTestResult;

    @OneToOne
    @JoinColumn(name = "call_attention_test_result_id", unique = true)
    private CallAttentionTestResult callAttentionTestResult;

    @OneToOne
    @JoinColumn(name = "washing_machine_execute_test_result_id", unique = true)
    private WashingMachineExecuteTestResult washingMachineExecuteTestResult;
}
