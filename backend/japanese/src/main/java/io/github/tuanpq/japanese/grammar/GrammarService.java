package io.github.tuanpq.japanese.grammar;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GrammarService {

    private final GrammarRepository grammarRepository;

    public GrammarService(GrammarRepository grammarRepository) {
        this.grammarRepository = grammarRepository;
    }

    public List<Grammar> findAll() {
        return grammarRepository.findAll();
    }

}
