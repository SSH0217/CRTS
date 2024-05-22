package com.contentsda.cognitive.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "call_attention_test_result")
@Getter
@Setter
public class CallAttentionTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "call_result", nullable = false)
    private Boolean callResult;

    @Column(name = "call_count", nullable = false)
    private Integer callCount;

    @OneToOne(mappedBy = "callAttentionTestResult", cascade = CascadeType.ALL)
    @JsonManagedReference
    private BTestResult bTestResult;
}
