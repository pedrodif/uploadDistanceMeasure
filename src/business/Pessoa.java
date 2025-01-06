package business;

import java.time.LocalDate;

import enums.Escolaridade;
import enums.EstadoCivil;
import enums.Hobby;

public class Pessoa {

    private int peso;
    private String nome;
    private float altura;
    private LocalDate dtNascimento;
    private double alturaNormalizada, pesoNormalizado;

    private Hobby hobby;
    private EstadoCivil estadoCivil;
    private Escolaridade escolaridade;

    public Pessoa(){}

    public Pessoa(String nome, LocalDate dtNascimento, float altura, int peso, 
                  String hobby, String estadoCivil, String escolaridade) throws Exception {
        setNome(nome);               
        setDtNascimento(dtNascimento);
        setAltura(altura);           
        setPeso(peso);               
        setHobby(hobby);             
        setEstadoCivil(estadoCivil); 
        setEscolaridade(escolaridade);
        this.pesoNormalizado = 0;
        this.alturaNormalizada = 0.0f;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) throws Exception {
        if (nome.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\s]+")) {
            this.nome = nome;
        } else {
            throw new Exception("O nome não deve conter números ou símbolos.");
        }
    }

      public LocalDate getDtNascimento() {
        return this.dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) throws Exception {
        if (dtNascimento.isBefore(LocalDate.now())) {
            this.dtNascimento = dtNascimento;
        } else {
            throw new Exception("A data de nascimento deve ser anterior a data de hoje.");
        }
    }

    public float getAltura() {
        return this.altura;
    }

    public void setAltura(float altura) throws Exception {
        if (altura > 0 && altura <= 2.60) {
            this.altura = altura;
        } else {
            throw new Exception("A altura deve ser maior que 0 e menor que 2.60 metros");
        }
    }

    public double getAlturaNormalizada() {
        return alturaNormalizada;
    }
    
    public void setAlturaNormalizada(double alturaNormalizada) {
        this.alturaNormalizada = alturaNormalizada;
    }
    
    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) throws Exception {
        if (peso > 0 && peso < 600) {
            this.peso = peso;
        } else {
            throw new Exception("O peso deve ser maior que 0 e menor que 500.");
        }
    }

    public double getPesoNormalizado() {
        return pesoNormalizado;
    }
    
    public void setPesoNormalizado(double pesoNormalizado) {
        this.pesoNormalizado = pesoNormalizado;
    }

    public Hobby getHobby() {
        return this.hobby;
    }

    public void setHobby(String hobby) {
        try {
            this.hobby = Hobby.valueOf(hobby.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Hobby inválido.");
        }
    }

    public EstadoCivil getEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        try {
            this.estadoCivil = EstadoCivil.valueOf(estadoCivil.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado Civil inválido.");
        }
    }

    public Escolaridade getEscolaridade() {
        return this.escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        try {
            this.escolaridade = Escolaridade.valueOf(escolaridade.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Escolaridade inválida.");
        }
    }
}