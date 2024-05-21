package com.contentsda.cognitive.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "washing_machine_execute_test_result")
@Getter
@Setter
public class WashingMachineExecuteTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "washing_machine_Result", nullable = false)
    private Boolean washingMachineResult;

    @Column(name = "washing_machine_time", nullable = false)
    private Integer washingMachineTime;

    @OneToOne(mappedBy = "washingMachineExecuteTestResult", cascade = CascadeType.ALL)
    private BTestResult bTestResult;

    @OneToMany(mappedBy = "washingMachineExecuteTestResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExecuteLog> executeLogList;
}
