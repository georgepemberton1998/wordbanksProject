package com.example.fundamentalsproject.service;

import com.example.fundamentalsproject.domain.UserWord;
import com.example.fundamentalsproject.dto.userWordDTO;
import com.example.fundamentalsproject.exceptions.WordNotFoundException;
import com.example.fundamentalsproject.repo.WordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    private final WordRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public WordService(WordRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    private userWordDTO mapToDTO(UserWord userWord) {
        return this.mapper.map(userWord, userWordDTO.class);
    }

    public List<userWordDTO> readAllWords(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public userWordDTO createWord(UserWord userWord){
        return this.mapToDTO(this.repo.save(userWord));
    }

    public userWordDTO findWordById(Long id){
        return this.mapToDTO(this.repo.findById(id)
                .orElseThrow(WordNotFoundException::new));
    }

    public userWordDTO updateWord(Long id, UserWord userWord){
        UserWord update = this.repo.findById(id).orElseThrow(WordNotFoundException::new);
        update.setWord(userWord.getWord());
        update.setDefinition(userWord.getDefinition());
        return this.mapToDTO(this.repo.save(userWord));
    }

    public Boolean deleteWordById(Long id){
        if(!this.repo.existsById(id)){
            throw new WordNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
