package service;

import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;

@WebService(endpointInterface = "service.MatriculaService")
public class MatriculaServiceImpl implements MatriculaService {

    private static List<Aluno> alunos = new ArrayList<>();

    @Override
    public String cadastrarAluno(String nome, int matricula, double nota1, double nota2) {

        Aluno aluno = new Aluno(nome, matricula, nota1, nota2);
        alunos.add(aluno);

        return "Aluno cadastrado com sucesso!";
    }

    @Override
    public Aluno buscarAluno(int matricula) {

        for (Aluno a : alunos) {
            if (a.getMatricula() == matricula) {
                return a;
            }
        }
        return null;
    }

    @Override
    public List<Aluno> listarAlunos() {
        return alunos;
    }
}
