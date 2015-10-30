package br.com.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.conexao.Conexao;
import br.com.dao.PedidoDAO;
import br.com.model.Pedido;

public class PedidoJDBC implements PedidoDAO{

	//GERENCIAMENTO DE CADASTRO DE PRODUTO DENTO DO SISTEMA
	private Connection con;
		
	public PedidoJDBC() {
		con = Conexao.getCon();
	}
	
	@Override
	public void inserir(Pedido pedido) {
		String sql = "insert into pedido (mesa, dataPedido, status) values (?, ?, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pedido.getMesa());
			pstmt.setDate(2,Date.valueOf(pedido.getDataPedido()));
			pstmt.setBoolean(3, pedido.getStatus());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Pedido pedido) {
		String sql = "update pedido set mesa=?, status=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pedido.getMesa());
			pstmt.setBoolean(2, pedido.getStatus());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void excluir(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pedido buscar(Integer id) {
		Pedido pedido = null;
		
		String sql = "select * from pedido where codigo = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				pedido = new Pedido();
				pedido.setCodigo(rs.getInt("codigo"));
				pedido.setMesa(rs.getString("mesa"));
				pedido.setStatus(rs.getBoolean("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedido;
	}

	@Override
	public List<Pedido> todos() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		String sql = "select * from pedido";
		
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Pedido pedido = new Pedido();
				pedido.setCodigo(rs.getInt("codigo"));
				pedido.setMesa(rs.getString("mesa"));
				pedido.setStatus(rs.getBoolean("status"));
				pedidos.add(pedido);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return pedidos;
	}

}
