package com.contentsda.cognitive.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_subject_log")
@Getter
@Setter
public class TestSubjectLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log_num", nullable = false)
    private Long logNum;

    @Column(name = "log", nullable = false)
    private String log;

    @Column(name = "log_created_date", nullable = false)
    private LocalDateTime logCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_subject_id")
    @JsonBackReference
    private TestSubject testSubject;
}
