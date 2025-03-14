package com.ipi.jva350.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ipi.jva350.model.SalarieAideADomicile;

@SpringBootTest
public class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository salarieAideADomicileRepository;

    @Test
    void testPartCongesPrisTotauxAnneeNMoins1() {
        // Insérer des données où la somme des congés pris et acquis n'est pas nulle
        SalarieAideADomicile salarie1 = new SalarieAideADomicile();
        salarie1.setCongesPayesPrisAnneeNMoins1(10);  // Congés pris égaux à 10
        salarie1.setCongesPayesAcquisAnneeNMoins1(20);  // Congés acquis égaux à 20
        salarieAideADomicileRepository.save(salarie1);

        // Insérer un autre salarié pour tester l'agrégation
        SalarieAideADomicile salarie2 = new SalarieAideADomicile();
        salarie2.setCongesPayesPrisAnneeNMoins1(5);  // Congés pris égaux à 5
        salarie2.setCongesPayesAcquisAnneeNMoins1(15);  // Congés acquis égaux à 15
        salarieAideADomicileRepository.save(salarie2);

        // Appeler la méthode partCongesPrisTotauxAnneeNMoins1
        Double result = salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1();
        
        double roundedResult = Math.round(result * 1000.0) / 1000.0;  // Arrondi à 3 chiffres
        assertEquals(0.429, roundedResult, 0.001);  // Vérification avec une tolérance de 0.001

    }


}
