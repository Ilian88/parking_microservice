package com.imarkov.payment.model.dao;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    Long id;

    @Id
    @SequenceGenerator(
            name = "base_seq",
            sequenceName = "base_sequence"

    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
