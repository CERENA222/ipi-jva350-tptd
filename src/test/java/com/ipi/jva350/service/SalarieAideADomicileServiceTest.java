package com.ipi.jva350.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ipi.jva350.repository.SalarieAideADomicileRepository;

public class SalarieAideADomicileServiceTest {
	@Mock
    private SalarieAideADomicileRepository salarieAideADomicileRepository;  

    @InjectMocks
    private SalarieAideADomicileService salarieAideADomicileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  
    }

    @Test
    void testCalculeLimiteEntrepriseCongesPermis() {
        
        LocalDate moisEnCours = LocalDate.of(2024, 3, 1);
        double congesPayesAcquisAnneeNMoins1 = 25; 
        LocalDate moisDebutContrat = LocalDate.of(2020, 6, 1);
        LocalDate premierJourDeConge = LocalDate.of(2024, 7, 15);
        LocalDate dernierJourDeConge = LocalDate.of(2024, 8, 15);

       
        when(salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(0.2);
        
        
        long resultat = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(
            moisEnCours, congesPayesAcquisAnneeNMoins1, moisDebutContrat, premierJourDeConge, dernierJourDeConge);
        
       
        assertEquals(14, resultat); 
    }
    @Test
    void testCalculeLimiteEntrepriseCongesPermisAvecMoinsDeConges() {
       
        LocalDate moisEnCours = LocalDate.of(2024, 3, 1);
        double congesPayesAcquisAnneeNMoins1 = 15; 
        LocalDate moisDebutContrat = LocalDate.of(2020, 6, 1);
        LocalDate premierJourDeConge = LocalDate.of(2024, 7, 1);
        LocalDate dernierJourDeConge = LocalDate.of(2024, 7, 31);

        
        when(salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(0.3);

      
        long resultat = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(
            moisEnCours, congesPayesAcquisAnneeNMoins1, moisDebutContrat, premierJourDeConge, dernierJourDeConge);

        
        assertEquals(7, resultat);
    }

}
