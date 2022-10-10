package com.example.DojosAndNinjas.services;

import com.example.DojosAndNinjas.models.Ninja;
import com.example.DojosAndNinjas.repository.NinjaRepository;
import org.springframework.stereotype.Service;

@Service
public class NinjaServices {

    private final NinjaRepository ninjaRepository;
    public NinjaServices(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    public void create(Ninja ninja) {
        ninjaRepository.save(ninja);
    }
}


