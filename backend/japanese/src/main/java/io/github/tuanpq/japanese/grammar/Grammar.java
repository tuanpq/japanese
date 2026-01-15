package io.github.tuanpq.japanese.grammar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "grammar")
public class Grammar {
    
    @Id
    private Long id;

    @Column(name = "jlpt_level", nullable = false)
    private String jlptLevel;

    @Column(name = "expression", nullable = false)
    private String expression;

    @Column(name = "explanation", nullable = false)
    private String explanation;

}
