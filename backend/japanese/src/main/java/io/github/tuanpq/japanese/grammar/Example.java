package io.github.tuanpq.japanese.grammar;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "example")
public class Example {
    
    @Id
    private Long id;

    @Column(name = "sentence")
    private String sentence;

    @Column(name = "meaning")
    private String meaning;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "grammar_id")
    private Grammar grammar;

}
