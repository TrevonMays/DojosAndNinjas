package com.example.DojosAndNinjas.services;

import com.example.DojosAndNinjas.models.Dojo;
import com.example.DojosAndNinjas.repository.DojoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DojoServices {
    private final DojoRepository dojoRepository;

    public DojoServices(DojoRepository dojoRepository) {
        this.dojoRepository = dojoRepository;
    }


    public List<Dojo> getAll() {
        return (List<Dojo>) dojoRepository.findAll();
    }

    public void create(Dojo dojo) {
        dojoRepository.save(dojo);
    }

    public Dojo getOne(Long id){
        Optional<Dojo> optionalDojo = dojoRepository.findById(id);
        return optionalDojo.orElse(null);

    }
}
