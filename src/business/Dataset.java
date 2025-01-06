package business;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Dataset {
    public static int MAX_PESSOAS = 100;
    public ArrayList<Pessoa> pessoas;

    public Dataset(){
        this.pessoas = new ArrayList<Pessoa>();
    }

    public boolean addPessoa(Pessoa pessoa) throws Exception {
        if (this.pessoas.size() < MAX_PESSOAS) {
            return this.pessoas.add(pessoa);
        } else {
            throw new ArrayIndexOutOfBoundsException("Excede o número máximo de cadastros permitidos");
        }
    }

    public boolean removePessoa(Pessoa pessoa) {
        return this.pessoas.remove(pessoa);
    }

    public boolean removePessoaByName(String nome) {
        return this.pessoas.removeIf(pessoa -> pessoa.getNome().equals(nome));
    }

    public boolean replacePessoa(Pessoa pessoaRemovida, Pessoa pessoaAdicionada) throws Exception {
        if (this.pessoas.remove(pessoaRemovida)) {
            return this.pessoas.add(pessoaAdicionada);
        } else {
            throw new Exception("Pessoa não encontrada para substituição.");
        }
    }

    public Pessoa getPessoaByName(String nome) {
        return this.pessoas.stream().filter(pessoa -> pessoa.getNome().equals(nome)).findFirst().get();
    }

    public ArrayList<Pessoa> getAll() {
        this.pessoas.sort(Comparator.comparing(Pessoa::getNome, String.CASE_INSENSITIVE_ORDER));
        return this.pessoas;
    }

    public int size() {
        return this.pessoas.size();
    }

    private Stream<Float> mapearAltura() {
        return this.pessoas.stream().map(Pessoa::getAltura);
    }

    public float maxAltura() {
        return mapearAltura().max(Float::compare).get();
    }

    public float minAltura() {
        return mapearAltura().min(Float::compare).get();
    }

    public float avgAltura() {
        return mapearAltura().reduce(0.0f, Float::sum) / size();
    }

    private Stream<Integer> mapearPeso() {
        return this.pessoas.stream().map(Pessoa::getPeso);
    }

    public int maxPeso() {
        return this.mapearPeso().max(Integer::compare).get();
    }

    public int minPeso() {
        return this.mapearPeso().min(Integer::compare).get();
    }

    public float avgPeso() {
        return mapearPeso().reduce(0, Integer::sum) / size();
    }

    public List<Double> calcDistanceVector(Pessoa pessoa) {
        return this.pessoas.stream()
                .map(pessoaRecuperada -> DistanceMeasure.calcDistance(pessoaRecuperada, pessoa))
                .collect(Collectors.toList());
    }

    public List<Pessoa> getSimilar(Pessoa pessoa, int n) {
        return this.pessoas.stream()
                .filter(pessoaRecuperada -> !pessoaRecuperada.equals(pessoa))
                .sorted(Comparator.comparingDouble(pessoaRecuperada -> DistanceMeasure.calcDistance(pessoaRecuperada, pessoa)))
                .limit(n)
                .collect(Collectors.toList());
    }
    
    public void normalizeField(String fieldName) throws Exception {
        double min = Double.POSITIVE_INFINITY, max = Double.NEGATIVE_INFINITY;
    
        for (Pessoa pessoa : this.pessoas) {
            double value;
            
            switch (fieldName) {
                case "altura":
                    value = pessoa.getAltura();
                    break;
                case "peso":
                    value = pessoa.getPeso();
                    break;
                default:
                    throw new IllegalArgumentException("Campo não suportado para normalização: " + fieldName);
            }
    
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
    
        if (min == max) {
            throw new Exception("Valores mínimos e máximos iguais. Não é possível normalizar.");
        }
    
        for (Pessoa pessoa : this.pessoas) {
            double valorNormalizado = 0;
    
            switch (fieldName) {
                case "altura":
                    valorNormalizado = (pessoa.getAltura() - min) / (max - min);
                    pessoa.setAlturaNormalizada(valorNormalizado);
                    break;
                case "peso":
                    valorNormalizado = (pessoa.getPeso() - min) / (max - min);
                    pessoa.setPesoNormalizado(valorNormalizado);
                    break;
            }
        }
    }
}
