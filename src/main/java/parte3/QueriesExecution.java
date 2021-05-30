package parte3;

import java.util.List;

public class QueriesExecution 
{
    public static void main(String[] args) 
    {
        // Aluno para consulta sem filtro
        AlunoDAO alunoDAO = new AlunoDAO();
        // Aluno para consulta com filtro
        AlunoDAO alunoDAO2 = new AlunoDAO();
        //ALuno para inserção
        // AlunoDAO alunoDAO3 = new AlunoDAO();
        //Aluno para inserçao
        AlunoDAO alunoDAO4 = new AlunoDAO();


        // Inserção de novos dados
        // Aluno alunoParaInsercao1 = new Aluno(
        //     "Joao",
        //     43,
        //     "SE"
        // );
        // alunoDAO3.create(alunoParaInsercao1);
        // Aluno alunoParaInsercao2 = new Aluno(
        //     "Aurelio",
        //     43,
        //     "SE"
        // );
        // alunoDAO3.create(alunoParaInsercao2);

        // Aluno alunoParaInsercao3 = new Aluno(
        //     "Gerlaine",
        //     43,
        //     "SE"
        // );
        // alunoDAO3.create(alunoParaInsercao3);

        // Deletar novo aluno
        alunoDAO4.delete(7);
        alunoDAO4.delete(8);
        alunoDAO4.delete(9);
        

        // Consulta 
        List<Aluno> alunos = alunoDAO.list();
        int cont = 1;
        for(Aluno a: alunos)
        {
            System.out.println("-----------------------------------------");
            System.out.println("-> Aluno " + cont);

            System.out.print("-> " + a.getId() + ", ");
            System.out.print(a.getNome() + ", ");
            System.out.print(a.getIdade() + ", ");
            System.out.println(a.getEstado());

            cont++;
        }
        System.out.println("-----------------------------------------");   
        
        // consulta com filtro
        Aluno alunoConsultaFiltro = alunoDAO2.getById(2);
        System.out.println("Id: " + alunoConsultaFiltro.getId());    
        System.out.println("Nome: " + alunoConsultaFiltro.getNome());    
        System.out.println("Idade: " + alunoConsultaFiltro.getIdade());    
        System.out.println("Estado " + alunoConsultaFiltro.getEstado());  
        


        
        


    }


    
}
