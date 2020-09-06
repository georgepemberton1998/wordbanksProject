package com.example.fundamentalsproject.rest;

import com.example.fundamentalsproject.domain.UserWord;
import com.example.fundamentalsproject.dto.UserWordDTO;
import com.example.fundamentalsproject.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("34.89.77.230/Word")
    public ResponseEntity<List<UserWordDTO>> getAllWords(){
        return ResponseEntity.ok(this.wordService.readAllWords());
    }

    @PostMapping("34.89.77.230/createWord")
    public ResponseEntity<UserWordDTO> createWord(@RequestBody UserWord word){
        return new ResponseEntity<UserWordDTO>(this.wordService.createWord(word), HttpStatus.CREATED);
    }

    @DeleteMapping("34.89.77.230/delete/{id}")
    public ResponseEntity<?> deleteWord(@PathVariable Long id){
        return this.wordService.deleteWordById(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("34.89.77.230/getWordById/{id}")
    public ResponseEntity<UserWordDTO> getWordById(@PathVariable Long id){
        return ResponseEntity.ok(this.wordService.findWordById(id));
    }

    @PutMapping("34.89.77.230/updateWord/{id}")
    public ResponseEntity<UserWordDTO> updateWord(@PathVariable Long id, @RequestBody UserWord word){
        return ResponseEntity.ok(this.wordService.updateWord(id, word));
    }

}
