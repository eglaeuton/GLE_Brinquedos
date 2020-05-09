
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProdutoDAO {
    Connection con = null;
    public ProdutoDAO(){
        con = new Conexao().conectar();
    }
        public String inserir(Produto p){
        String status = "Produto inserido com sucesso!";
        String sql = "INSERT INTO produto (nome, valor) values (?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getValor());
            
            int n = ps.executeUpdate();
            if(n == 0){
                status = "Erro ao inserir";
            }
        }catch(Exception e){
            status = e.getMessage();
        }
        return status;
    }
}
