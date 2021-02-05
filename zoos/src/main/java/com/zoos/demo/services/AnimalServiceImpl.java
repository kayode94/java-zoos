package com.zoos.demo.services;

import com.zoos.demo.repositories.AnimalRepository;
import com.zoos.demo.views.CountAnimalInZoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService
{
    @Autowired
    private AnimalRepository animalrepos;

    @Override
    public ArrayList<CountAnimalInZoo> getCountAnimalInZoo()
    {
        return animalrepos.getCountAnimalInZoo();
    }
}
