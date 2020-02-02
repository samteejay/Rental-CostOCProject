/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openclassrooms.course.springboot.activitych3;

import com.openclassrooms.course.springboot.activitych3.domain.Rent;
import com.openclassrooms.course.springboot.activitych3.repository.RentRepository;
import com.openclassrooms.course.springboot.activitych3.service.CurrencyService;
import com.openclassrooms.course.springboot.activitych3.service.RentService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Omali
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RentServiceTest {
    @Mock
    private RentRepository rentRepositoryMock;

    @Mock
    private CurrencyService currencyServiceMock;

    @InjectMocks
    private RentService rentService;

    @Test
    public void testGetRentItemsReturnsAllItemsFromRepository() throws IOException {

        //Arrange
        Rent item1 = new Rent(1, "Sydney", "Australia", 1862.37);
        Rent item2 = new Rent(2, "Canberra", "Australia", 1303.22);
        List<Rent> mockItems = Arrays.asList(item1, item2);

        when(rentRepositoryMock.initializeCitiesList()).thenReturn(mockItems);

        //Act
        List<Rent> results = rentService.getRentItems();

        //Assert
        
        assertTrue(results.get(0).getCity().equals("Sydney"));
        assertTrue(results.get(1).getCity().equals("Canberra"));
    }

    
}
