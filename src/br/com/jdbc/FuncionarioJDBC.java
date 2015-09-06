package br.com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.conexao.Conexao;
import br.com.dao.FuncionarioDAO;
import br.com.model.Funcionario;
import br.com.model.Setor;

public class FuncionarioJDBC implements FuncionarioDAO {
	//CLASSE QUE GERENCIA TODOS AS A��ES DE PROPRIEDADE DA CLASSE FUNCIONARIO
	
	private Connection con;
	
	public FuncionarioJDBC() {
		// METODO QUE PERSISTE A CONEX�O COM O BANCO DE DADOS
		con = Conexao.getCon();
	}
	
	
	@Override
	public void inserir(Funcionario funcionario) {
		// INSERIR FUNCIONARIO
		String sql = "insert into funcionario (nome, cpf, telefone, setor, usuario, senha, status) value (?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, funcionario.getNome());
			pstmt.setString(2, funcionario.getCpf());
			pstmt.setInt(3, funcionario.getTelefone());
			pstmt.setString(4, funcionario.getSetor().toString());
			pstmt.setString(5, funcionario.getUsuario());
			pstmt.setString(6, funcionario.getSenha());
			pstmt.setBoolean(7, funcionario.getStatus());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void alterar(Funcionario funcionario) {
		// ALTERAR UM FUNCIONARIO
		String sql = "update funcionario set nome = ?, cpf = ?, telefone = ?, setor = ?, usuario = ?, senha = ?, status = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, funcionario.getNome());
			pstmt.setString(2, funcionario.getCpf());
			pstmt.setInt(3, funcionario.getTelefone());
			pstmt.setString(4, funcionario.getSetor().toString());
			pstmt.setString(5, funcionario.getUsuario());
			pstmt.setString(6, funcionario.getSenha());
			pstmt.setBoolean(7, funcionario.getStatus());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(Funcionario funcionario) {
		// EXCLUIR UM FUNCIONARIO
		String sql = "delete from funcionario where codigo = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, funcionario.getCodigo());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Funcionario buscar(Integer id) {
		//BUSCAR TODOS OSFUNCIONARIOS
			Funcionario funcionario = null;
			
			String sql =  "select * from funcionario where codigo = ?";
			
			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					//BUSCA TODOS OS REGISTROS NO BANCO E LISTA DE TODOS DE ACORDO COM O QUE
					//FOI SOLICITADO
					funcionario = new Funcionario();
					funcionario.setCodigo(rs.getInt("codigo"));
					funcionario.setNome(rs.getString("nome"));
					funcionario.setCpf(rs.getString("cpf"));
					funcionario.setTelefone(rs.getInt("telefone"));
					funcionario.setSetor(Setor.get(rs.getString("setor")));
					funcionario.setUsuario(rs.getString("usuario"));
					funcionario.setUsuario(rs.getString("senha"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return funcionario;
	}

	@Override
	public List<Funcionario> todos() {
		// LISTA TODOS OS O FUNCIONARIOS DENTRO DA TABELA
		List<Funcionario> funcionarios = new ArrayList<>();
		
		String sql =  "select * from funcionario";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				//BUSCA TODOS OS REGISTROS NO BANCO E LISTA DE TODOS OS CADASTRO NO BANCO
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(rs.getInt("codigo"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setTelefone(rs.getInt("telefone"));
				funcionario.setSetor(Setor.get(rs.getString("setor")));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setUsuario(rs.getString("senha"));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

}