/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openclassrooms.course.springboot.activitych3.repository;

import com.openclassrooms.course.springboot.activitych3.domain.Rent;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Omali
 */
@Service
public class RentRepository {

    private List<Rent> rents = new ArrayList<>();

    public List<Rent> initializeCitiesList() throws IOException {
        InputStream resource = new ClassPathResource("rents.csv").getInputStream();
        rents = new CsvToBeanBuilder<Rent>(new BufferedReader(
                new InputStreamReader(resource))).withType(Rent.class).build().parse();
        
        return rents;
    }
    
    public Rent findByCity(String city) {
        return rents.stream().filter(r -> r.getCity().equalsIgnoreCase(city)).findFirst().orElse(null);
    }
}
