package io.github.tuanpq.japanese.grammar;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/grammars", produces = "application/json")
public class GrammarController {

    private final GrammarService grammarService;

    public GrammarController(GrammarService grammarService) {
        this.grammarService = grammarService;
    }

    @GetMapping
    public List<Grammar> getAll() {
        List<Grammar> grammars = grammarService.findAll();
        return grammars;
    }

}
