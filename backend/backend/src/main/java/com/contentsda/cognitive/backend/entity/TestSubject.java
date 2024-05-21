package com.contentsda.cognitive.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "test_subject")
@Getter
@Setter
public class TestSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervision")
    private Supervision supervision;

    @OneToMany(mappedBy = "testSubject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestResult> testResultList;

    @OneToMany(mappedBy = "testSubject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestSubjectLog> testSubjectLogList;

}
