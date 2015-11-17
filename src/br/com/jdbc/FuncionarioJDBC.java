package br.com.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.conexao.Conexao;
import br.com.dao.FuncionarioDAO;
import br.com.model.Funcionario;
import br.com.tipo.StatusSetor;
import br.com.tipo.StatusFuncionario;
import br.com.util.DateUtil;

public class FuncionarioJDBC implements FuncionarioDAO {
	// CLASSE QUE GERENCIA TODOS AS AÇÕES DE PROPRIEDADE DA CLASSE FUNCIONARIO

	private Connection con;

	public FuncionarioJDBC() {
		// METODO QUE PERSISTE A CONEXÃO COM O BANCO DE DADOS
		con = Conexao.getCon();
	}

	@Override
	public void inserir(Funcionario funcionario) {
		// INSERIR FUNCIONARIO
		String sql = "insert into funcionario (nome, cpf, telefone, setor, usuario, senha, admissao, status) value (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, funcionario.getNome());
			pstmt.setString(2, funcionario.getCpf());
			pstmt.setString(3, funcionario.getTelefone());
			pstmt.setString(4, funcionario.getSetor().toString());
			pstmt.setString(5, funcionario.getUsuario());
			pstmt.setString(6, funcionario.getSenha());
			pstmt.setDate(7, Date.valueOf(funcionario.getAdmissao().toString()));
			pstmt.setString(8, funcionario.getStatus().toString());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Funcionario funcionario) {
		// ALTERAR UM FUNCIONARIO
		String sql = "update funcionario set nome = ?, cpf = ?, telefone = ?, setor = ?, usuario = ?, senha = ?, status = ? where codigo = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, funcionario.getNome());
			pstmt.setString(2, funcionario.getCpf());
			pstmt.setString(3, funcionario.getTelefone());
			pstmt.setString(4, funcionario.getSetor().toString());
			pstmt.setString(5, funcionario.getUsuario());
			pstmt.setString(6, funcionario.getSenha());
			pstmt.setString(7, funcionario.getStatus().toString());
			pstmt.setInt(8, funcionario.getCodigo());
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
		// BUSCAR TODOS OSFUNCIONARIOS
		Funcionario funcionario = null;

		String sql = "select * from funcionario where codigo = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// BUSCA TODOS OS REGISTROS NO BANCO E LISTA DE TODOS DE ACORDO
				// COM O QUE
				// FOI SOLICITADO
				funcionario = new Funcionario();
				funcionario.setCodigo(rs.getInt("codigo"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setSetor(StatusSetor.get(rs.getString("setor")));
				funcionario.setAdmissao(DateUtil.toLocalDate(
						rs.getString("admissao"), "yyyy-MM-dd"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setStatus(StatusFuncionario.get(rs
						.getString("status")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}

	@Override
	public Funcionario buscar(String usuario) {
		// BUSCAR TODOS OSFUNCIONARIOS
		Funcionario funcionario = null;

		String sql = "select * from funcionario where usuario = ? and status = 'ATIVO'";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usuario);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setCodigo(rs.getInt("codigo"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setSetor(StatusSetor.get(rs.getString("setor")));
				funcionario.setAdmissao(DateUtil.toLocalDate(
						rs.getString("admissao"), "yyyy-MM-dd"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setStatus(StatusFuncionario.get(rs
						.getString("status")));
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

		String sql = "select * from funcionario";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// BUSCA TODOS OS REGISTROS NO BANCO E LISTA DE TODOS OS
				// CADASTRO NO BANCO
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(rs.getInt("codigo"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setSetor(StatusSetor.get(rs.getString("setor")));
				funcionario.setAdmissao(DateUtil.toLocalDate(
						rs.getString("admissao"), "yyyy-MM-dd"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setUsuario(rs.getString("senha"));
				funcionario.setStatus(StatusFuncionario.get(rs
						.getString("status")));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

}
