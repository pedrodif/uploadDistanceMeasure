package business;

public class DistanceMeasure {

    public static double calcDistance(Pessoa primeiraPessoa, Pessoa segundaPessoa) {
        double somatorioDistancia = 0;

        somatorioDistancia += primeiraPessoa.getHobby().equals(segundaPessoa.getHobby()) ? 0 : 1;
        somatorioDistancia += primeiraPessoa.getEstadoCivil().equals(segundaPessoa.getEstadoCivil()) ? 0 : 1;
        somatorioDistancia += primeiraPessoa.getEscolaridade().equals(segundaPessoa.getEscolaridade()) ? 0 : 1;

        somatorioDistancia += Math.pow(primeiraPessoa.getAlturaNormalizada() - segundaPessoa.getAlturaNormalizada(), 2);
        somatorioDistancia += Math.pow(primeiraPessoa.getPesoNormalizado() - segundaPessoa.getPesoNormalizado(), 2);

        long anosDiferenca = java.time.temporal.ChronoUnit.YEARS.between(
            primeiraPessoa.getDtNascimento(), 
            segundaPessoa.getDtNascimento()
        );
        somatorioDistancia += Math.pow(anosDiferenca, 2); 

        return Math.sqrt(somatorioDistancia / 6);
    }
}