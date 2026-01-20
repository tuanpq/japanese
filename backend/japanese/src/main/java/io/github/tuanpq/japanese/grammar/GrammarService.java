package io.github.tuanpq.japanese.grammar;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GrammarService {

    private final GrammarRepository grammarRepository;

    public GrammarService(GrammarRepository grammarRepository) {
        this.grammarRepository = grammarRepository;
    }

    public List<Grammar> findAllGrammars() {
        return grammarRepository.findAll();
    }

    public Optional<Grammar> findGrammarById(Long id) {
        return grammarRepository.findById(id);
    }

    public Page<Grammar> findGrammars(int page, int size) {
        return grammarRepository.findAll(PageRequest.of(page, size));
    }

    public Grammar addGrammar(Grammar grammar) {
        return grammarRepository.save(grammar);
    }

    public Grammar updateGrammar(Long id, Grammar grammar) {
        grammar.setId(id);
        return grammarRepository.save(grammar);
    }

    public void deleteGrammar(Long id) {
        grammarRepository.deleteById(id);
    }

    public void deleteGrammarsByIds(List<Long> ids) {
        grammarRepository.deleteAllById(ids);
    }

}
