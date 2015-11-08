package br.com.jdbc;

import java.util.ArrayList;
import java.util.List;

import br.com.conexao.Conexao;
import br.com.dao.ProdutoDAO;
import br.com.model.Produto;
import br.com.tipo.StatusProduto;
import br.com.tipo.TipoProduto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ProdutoJDBC implements ProdutoDAO {
	
	//GERENCIAMENTO DE CADASTROR DE PRODUTO DENTO DO SISTEMA
	private Connection con;
	
	public ProdutoJDBC(){
		con = Conexao.getCon();
	}

	@Override
	public void inserir(Produto	produto) {
		// INSERINDO PRODUTO
		String sql = "insert into produto (tipoProduto, valor, descricao, status) value(?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, produto.getTipoProduto().toString());
			pstmt.setDouble(2, produto.getValor());
			pstmt.setString(3, produto.getDescricao());	
			pstmt.setString(4, produto.getStatus().toString());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void alterar(Produto produto) {
		//ALTERAR PRODUTO
		String sql = "update produto set tipoProduto=?, valor=?, descricao=?, status=? where codigo = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, produto.getTipoProduto().toString());
			pstmt.setDouble(2, produto.getValor());
			pstmt.setString(3, produto.getDescricao());	
			pstmt.setString(4, produto.getStatus().toString());
			pstmt.setInt(5, produto.getCodigo());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Produto entidade) {
		// EXCLUIR UM PRODUTO
		
	}

	@Override
	public Produto buscar(Integer id) {
		// BUSCAR TODOS OS PRODUTOS
		Produto produto = null;
		
		String sql = "select * from produto where codigo = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setTipoProduto(TipoProduto.get(rs.getString("tipoProduto")));
				produto.setValor(rs.getDouble("valor"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setStatus(StatusProduto.get(rs.getString("status")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produto;
	}

	@Override
	public List<Produto> todos() {
		// LISTAR TODOS OS PRODUTO
		List<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "select * from produto";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Produto produto = new Produto();
				produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setTipoProduto(TipoProduto.get(rs.getString("tipoProduto")));
				produto.setValor(rs.getDouble("valor"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setStatus(StatusProduto.get(rs.getString("status")));
				produtos.add(produto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return produtos;
	}
	
	@Override
	public List<Produto> listarPor(TipoProduto tipoProduto) {
		// LISTAR TODOS OS PRODUTO
		List<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "SELECT * FROM produto where tipoProduto = ? and status = 'ATIVO'";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tipoProduto.toString());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Produto produto = new Produto();
				produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setTipoProduto(TipoProduto.get(rs.getString("tipoProduto")));
				produto.setValor(rs.getDouble("valor"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setStatus(StatusProduto.get(rs.getString("status")));
				produtos.add(produto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return produtos;
	}

}
