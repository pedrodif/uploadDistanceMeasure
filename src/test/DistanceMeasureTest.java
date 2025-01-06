package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import business.Dataset;
import business.Pessoa;
import business.DistanceMeasure;

public class DistanceMeasureTest {
    private Dataset dataset;
    private Pessoa primeiraPessoa, segundaPessoa;

    @BeforeEach
    public void config() throws Exception {
        this.dataset = new Dataset();
        this.primeiraPessoa = new Pessoa("João Floreano", LocalDate.of(1990, 5, 10), 1.75f, 70, "Cinema", "Solteiro", "Superior");
        this.segundaPessoa = new Pessoa("Branca Letícia de Barros Motta", LocalDate.of(1985, 8, 20), 1.68f, 65, "Livro", "Casado", "Medio");
    }

    @Test
    void testCalcDistance() throws Exception {
        this.dataset.addPessoa(this.primeiraPessoa);
        this.dataset.addPessoa(this.segundaPessoa);

        this.dataset.normalizeField("altura");
        this.dataset.normalizeField("peso");

        double distancia = DistanceMeasure.calcDistance(this.primeiraPessoa, this.segundaPessoa);

        long anosDiferenca = java.time.temporal.ChronoUnit.YEARS.between(
            this.primeiraPessoa.getDtNascimento(), 
            this.segundaPessoa.getDtNascimento()
        );

        double somatarioReferencial = 
            3 + 
            Math.pow(this.primeiraPessoa.getAlturaNormalizada() - this.segundaPessoa.getAlturaNormalizada(), 2) + 
            Math.pow(this.primeiraPessoa.getPesoNormalizado() - this.segundaPessoa.getPesoNormalizado(), 2) + 
            Math.pow(anosDiferenca, 2);              
        
        double valorEsperado = Math.sqrt(somatarioReferencial / 6);
        assertEquals(valorEsperado, distancia, 0.1); 
    }
}
