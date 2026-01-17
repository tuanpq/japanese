package io.github.tuanpq.japanese.grammar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/api/grammars", produces = "application/json")
public class GrammarController {

    private final GrammarService grammarService;

    public GrammarController(GrammarService grammarService) {
        this.grammarService = grammarService;
    }

    @GetMapping("/all")
    public List<Grammar> getAll() {
        List<Grammar> grammars = grammarService.findAllGrammars();
        return grammars;
    }

    @GetMapping("")
    public Page<Grammar> getAllGrammarsWithPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return grammarService.findAllGrammarsWithPaging(page, size);
    }

    @PostMapping("/register")
    public Grammar addGrammar(@RequestBody Grammar grammar) {
        return grammarService.addGrammar(grammar);
    }

    @PutMapping("/update/{id}")
    public Grammar updateGrammar(@PathVariable Long id, @RequestBody Grammar grammar) {
        return grammarService.updateGrammar(id, grammar);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGrammar(@PathVariable Long id) {
        grammarService.deleteGrammar(id);
    }

    @DeleteMapping("/deleteByIds")
    public void deleteAllGrammarsByIds(@RequestBody List<Long> ids) {
        grammarService.deleteGrammarsByIds(ids);
    }

}
