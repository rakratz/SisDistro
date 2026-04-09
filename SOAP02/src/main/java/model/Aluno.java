package model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Aluno {

    private String nome;
    private int matricula;
    private double nota1;
    private double nota2;

    public Aluno() {}

    // getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public double getNota1() { return nota1; }
    public void setNota1(double nota1) { this.nota1 = nota1; }

    public double getNota2() { return nota2; }
    public void setNota2(double nota2) { this.nota2 = nota2; }
}
