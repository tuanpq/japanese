package io.github.tuanpq.japanese.grammar;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "grammar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Example> examples = new ArrayList<>();

}
