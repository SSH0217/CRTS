package com.contentsda.cognitive.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "execute_log")
@Getter
@Setter
public class ExecuteLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log_num", nullable = false)
    private Long logNum;

    @Column(name = "log", nullable = false)
    private String log;

    @Column(name = "log_time", nullable = false)
    private LocalDateTime logTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "microwave_execute_test_result_id")
    private MicrowaveExecuteTestResult microwaveExecuteTestResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "washing_machine_execute_test_result_id")
    private WashingMachineExecuteTestResult washingMachineExecuteTestResult;
}
