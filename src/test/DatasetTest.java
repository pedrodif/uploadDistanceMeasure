package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Pessoa;
import business.Dataset;

public class DatasetTest {
    private Dataset dataset;
    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private Pessoa pessoa3;
    private Pessoa pessoa4;
    private Pessoa pessoa5;
    private Pessoa pessoa6;

    @BeforeEach
    public void configCenario() throws Exception {
        this.dataset = new Dataset();
        this.pessoa1 = new Pessoa("João", LocalDate.of(1990, 1, 1), 1.75f, 70, "Cinema", "Solteiro", "Superior");
        this.pessoa2 = new Pessoa("Maria", LocalDate.of(1985, 5, 15), 1.65f, 60, "Game", "Casado", "Medio");
        this.pessoa3 = new Pessoa("Carlos", LocalDate.of(1988, 10, 12), 1.80f, 80, "Esporte", "Divorciado", "Superior");   
        
        this.pessoa4 = new Pessoa("Denise", LocalDate.of(1990, 1, 1), 1.75f, 70, "Livro", "Solteiro", "Superior");
        this.pessoa5 = new Pessoa("Miguel", LocalDate.of(1985, 1, 1), 1.68f, 65, "Livro", "Casado", "Medio");
        this.pessoa6 = new Pessoa("Danica", LocalDate.of(2000, 1, 1), 1.80f, 75, "Arte", "Solteiro", "Superior");
    }
 
    @Test
    void testAddPessoaSucesso() throws Exception {
        assertTrue(this.dataset.addPessoa(this.pessoa1));
    }

    @Test
    void testAddPessoaExcedeLimite() throws Exception {
        for (int i = 0; i < Dataset.MAX_PESSOAS; i++) {
            this.dataset.addPessoa(new Pessoa("Pessoa", LocalDate.of(2000, 1, 1), 1.70f, 65, "Musica", "Solteiro", "Superior"));
        }
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.dataset.addPessoa(this.pessoa1));
        assertEquals("Excede o número máximo de cadastros permitidos", exception.getMessage());
    }

    @Test
    void testRemovePessoa() throws Exception {
        this.dataset.addPessoa(this.pessoa1);
        assertTrue(this.dataset.removePessoa(this.pessoa1));
    }

    @Test
    void testRemovePessoaByName() throws Exception {
        this.dataset.addPessoa(this.pessoa1);     
        assertTrue(this.dataset.removePessoaByName("João"));
    }

    @Test
    void testReplacePessoa() throws Exception {        
        this.dataset.addPessoa(this.pessoa1);
        assertTrue(this.dataset.replacePessoa(this.pessoa1, this.pessoa2));
    }

    @Test
    void testReplacePessoaErro() throws Exception {      
        this.dataset.addPessoa(this.pessoa2);
        Exception exception = assertThrows(Exception.class, () -> this.dataset.replacePessoa(this.pessoa1, this.pessoa3));
        assertEquals("Pessoa não encontrada para substituição.", exception.getMessage());
    }

    @Test
    void testGetPessoaByName() throws Exception {
        this.dataset.addPessoa(this.pessoa1);        
        assertEquals("João", this.dataset.getPessoaByName("João").getNome());
    }

    @Test
    void testMaxAltura() throws Exception {
        this.dataset.addPessoa(this.pessoa1);
        this.dataset.addPessoa(this.pessoa2);
        assertEquals(1.75f, this.dataset.maxAltura());
    }

    @Test
    void testMinAltura() throws Exception {
        this.dataset.addPessoa(this.pessoa1);
        this.dataset.addPessoa(this.pessoa2);
        assertEquals(1.65f, this.dataset.minAltura());
    }

    @Test
    void testAvgAltura() throws Exception {
        this.dataset.addPessoa(this.pessoa1);
        this.dataset.addPessoa(this.pessoa2);
        assertEquals(1.70f, this.dataset.avgAltura());
    }

    @Test
    void testMaxPeso() throws Exception {
        this.dataset.addPessoa(this.pessoa1);
        this.dataset.addPessoa(this.pessoa2);
        assertEquals(70, this.dataset.maxPeso());
    }

    @Test
    void testMinPeso() throws Exception {
        this.dataset.addPessoa(this.pessoa1);
        this.dataset.addPessoa(this.pessoa2);
        assertEquals(60, this.dataset.minPeso());
    }

    @Test
    void testAvgPeso() throws Exception {
        this.dataset.addPessoa(this.pessoa1);
        this.dataset.addPessoa(this.pessoa2);
        assertEquals(65, this.dataset.avgPeso());
    }

    @Test
    void testCalcDistanceVector() throws Exception {
        this.dataset.addPessoa(this.pessoa4);
        this.dataset.addPessoa(this.pessoa5);
        this.dataset.addPessoa(this.pessoa6);

        List<Double> distancias = dataset.calcDistanceVector(this.pessoa4);
        assertEquals(0.0, distancias.get(0), 0);
    }

    @Test
    void testGetSimilar() throws Exception {
        this.dataset.addPessoa(this.pessoa4); 
        this.dataset.addPessoa(this.pessoa5); 
        this.dataset.addPessoa(this.pessoa6); 

        this.dataset.normalizeField("altura");
        this.dataset.normalizeField("peso");

        List<Pessoa> similares = dataset.getSimilar(this.pessoa4, 2);
        assertEquals("Miguel", similares.get(0).getNome());
    }

    @Test
    void testNormalizeFieldAltura() throws Exception {
        this.dataset.addPessoa(this.pessoa4); 
        this.dataset.addPessoa(this.pessoa5); 
        this.dataset.addPessoa(this.pessoa6); 

        this.dataset.normalizeField("altura");

        assertEquals(0.0, this.pessoa5.getAlturaNormalizada());
        assertEquals(1.0, this.pessoa6.getAlturaNormalizada());
    }

    @Test
    void testNormalizeFieldPeso() throws Exception {
        this.dataset.addPessoa(this.pessoa4); 
        this.dataset.addPessoa(this.pessoa5); 
        this.dataset.addPessoa(this.pessoa6); 

        this.dataset.normalizeField("peso");

        assertEquals(0.0, this.pessoa5.getPesoNormalizado());
        assertEquals(1.0, this.pessoa6.getPesoNormalizado());
    }
}

