package parte3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO 
{
    // 1. Consulta 
    public List<Aluno> list()
    {
        //Preparar lista que irá retorna após consultar o banco de dados
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection())
        {
            PreparedStatement prst =  conn.prepareStatement("SELECT * FROM aluno");

            ResultSet rs = prst.executeQuery();

            while(rs.next())
            {
                Aluno aluno = new Aluno();

                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
                
                alunos.add(aluno);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alunos;
    }   

    // 1.1 Consulta com filtro 
    public Aluno getById(int id)
    {
        // Preparar objeto aluno para receber os dados do banco de dados
        Aluno aluno = new Aluno();

        try(Connection conn = ConnectionFactory.getConnection())
        {
            // Prepara consulta sql 
            String sql = "SELECT * FROM aluno WHERE id = ?";

            // Preparar o statement com os parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql); 
            // a sintaxe de comando está correto, essa interrogação tem que ficar ai
            stmt.setInt(1, id); 
            // aqui passamos o valor(id) que será passado no lugar da interrogaçaõ.
            // O número 1 indica qual interrogação será substituida pelo comando, caso houvesse outras

            // Execultar a consulta e amarzenar o retorno dentro do objeto rs
            ResultSet rs = stmt.executeQuery();

            // Guardar os valores retornados da tabela aluno no objeto aluno
            if(rs.next())
            {
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }


        }catch(SQLException e)
        {
            System.out.println("Listagem de alunos falhou!!!");
            e.printStackTrace();
        }

        // retornar aluno encontrado na tabela
        return aluno;
    }

    public void create(Aluno aluno)
    {
        try(Connection conn = ConnectionFactory.getConnection())
        {
            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES (?, ?, ?)";

            // Preparar o statement com os parametros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());

            //Executar a inserção e armazenar o número de linha afetados
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção bem sucedida!!! Foi adicionado " + rowsAffected + " linha.");
        }
        catch(SQLException e)
        {
            System.out.println("Falha na conexao");
            e.printStackTrace();
        }
    }



    public void delete(int id)
    {
        try(Connection conn = ConnectionFactory.getConnection())
        {
            // Preparar o SQL para deletar 
            String sql = "DELETE FROM aluno WHERE id = ?";

            // Preparar statement com os parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            // Execulta delete e armazena o numero de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Delete BEM SUCEDIDO! " + rowsAffected + " linha.");

        }catch(SQLException e)
        {
            System.out.println("Delete Falhou");
            e.printStackTrace();
        }
    }
}
