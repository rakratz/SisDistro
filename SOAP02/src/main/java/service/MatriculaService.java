package service;

import java.util.List;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import model.Aluno;

@WebService
public interface MatriculaService {

    @WebMethod
    String cadastrarAluno(String nome, int matricula, double nota1, double nota2);

    @WebMethod
    Aluno buscarAluno(int matricula);

    @WebMethod
    List<Aluno> listarAlunos();
}
