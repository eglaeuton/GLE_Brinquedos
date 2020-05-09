
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Funcionario;

public class FuncionarioDAO {
    Connection con = null;
    public FuncionarioDAO(){
        con = new Conexao().conectar();
    }
     public String inserir(Funcionario f){
        String status = "Funcionario inserido com sucesso!";
        String sql = "INSERT INTO funcionario (cpf, nome, telefone, email, cargo) values (?,?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, f.getCpf());
            ps.setString(2, f.getNome());
            ps.setString(3, f.getTelefone());
            ps.setString(4, f.getEmail());
            ps.setString(5, f.getCargo());
            
            int n = ps.executeUpdate();
            if(n == 0){
                status = "Erro ao inserir";
            }
        }catch(Exception e){
            status = "CPF já cadastrado!";
        }
        return status;
    }
     public ArrayList<Funcionario> listar(){
        ArrayList<Funcionario> funcionarios = new ArrayList();
        try{
            String sql = "SELECT cpf,nome,telefone,email,cargo FROM funcionario";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setCpf(rs.getString(1));
                f.setNome(rs.getString(2));
                f.setTelefone(rs.getString(3));
                f.setEmail(rs.getString(4));
                f.setCargo(rs.getString(5));
                
                funcionarios.add(f);
            }
            return funcionarios;
        }catch(Exception e){
            return null;
        }
    }
        public String atualizar(Funcionario f){
        String status = "Funcionario atualizado com sucesso!";
        String sql = "UPDATE funcionario SET nome = ?, telefone = ?, email = ?, cargo = ? WHERE cpf = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getTelefone());
            ps.setString(3, f.getEmail());
            ps.setString(4, f.getCargo());
            ps.setString(5, f.getCpf());
            ps.executeUpdate();
            
        }catch(Exception e){
            status = "Erro ao atualizar o cliente";
        }
        return status;
    }
        public String excluir(Funcionario f){
        String status = "Funcionario excluido com sucesso!";
        try{
            String sql = "DELETE FROM funcionario WHERE cpf = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, f.getCpf());
            ps.executeUpdate();
        }catch(Exception e){
            status = e.getMessage();
        }
        return status;
    }
}