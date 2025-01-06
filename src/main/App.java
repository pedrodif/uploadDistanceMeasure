package main;

import java.time.LocalDate;
import java.util.List;

import business.Dataset;
import business.Pessoa;

public class App {
    public static void main(String[] args) throws Exception {
        Dataset dataset = new Dataset();

        dataset.addPessoa(new Pessoa("Alice Braga", LocalDate.of(1990, 1, 1), 1.65f, 63, "arte", "solteiro", "medio"));
        dataset.addPessoa(new Pessoa("Bob Dylan", LocalDate.of(1991, 5, 15), 1.66f, 67, "musica", "casado", "medio"));
        dataset.addPessoa(new Pessoa("Carol Aird ", LocalDate.of(1993, 8, 23), 1.66f, 62, "musica", "divorciado", "medio"));
        dataset.addPessoa(new Pessoa("David beckham", LocalDate.of(1998, 3, 10), 1.64f, 66, "livro", "solteiro", "fundamental"));
        dataset.addPessoa(new Pessoa("Eva Longoria", LocalDate.of(1988, 12, 30), 1.60f, 55, "esporte", "separado", "superior"));

        Pessoa pessoaReferencia = new Pessoa("Zara Larson", LocalDate.of(1992, 7, 7), 1.68f, 68, "musica", "casado", "medio");
        dataset.addPessoa(pessoaReferencia);

        dataset.normalizeField("altura");
        dataset.normalizeField("peso");
        
        List<Pessoa> similares = dataset.getSimilar(pessoaReferencia, 3);
        System.out.println("Pessoas mais similares:");
        for (Pessoa pessoa : similares) {
            System.out.println(pessoa.getNome());
        }
    }
}
