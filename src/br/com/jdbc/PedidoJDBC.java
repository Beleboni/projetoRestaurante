package br.com.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.conexao.Conexao;
import br.com.dao.PedidoDAO;
import br.com.model.Pedido;
import br.com.tipo.StatusPedido;


public class PedidoJDBC implements PedidoDAO{

	//GERENCIAMENTO DE CADASTRO DE PRODUTO DENTO DO SISTEMA
	private Connection con;
		
	public PedidoJDBC() {
		con = Conexao.getCon();
	}
	
	@Override
	public void inserir(Pedido pedido) {
		String sql = "insert into pedido (mesa, dataPedido, status, total) values (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pedido.getMesa());
			pstmt.setString(2,pedido.getDataPedido().toString());
			pstmt.setString(3, pedido.getStatus().toString());
			pstmt.setDouble(4, Double.parseDouble(pedido.getTotal().toString()));
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Pedido pedido) {
		String sql = "update pedido set mesa=?, dataPedido= ?, status=?, total = ? where codigoPedido = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pedido.getMesa());
			pstmt.setDate(2, Date.valueOf(pedido.getDataPedido().toString()));
			pstmt.setString(3, pedido.getStatus().toString());
			pstmt.setDouble(4, Double.parseDouble(pedido.getTotal().toString()));
			pstmt.setInt(5, pedido.getCodigo());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void excluir(Pedido pedido) {
		// EXCLUIR UM PEDIDO
		String sql = "delete from pedido where codigoPedido = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pedido.getCodigo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Pedido buscar(Integer id) {
		Pedido pedido = null;
		
		String sql = "select * from pedido where mesa = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				pedido = new Pedido();
				pedido.setCodigo(rs.getInt("codigoPedido"));
				pedido.setMesa(rs.getString("mesa"));
				pedido.setDataPedido(LocalDate.parse((rs.getString("dataPedido"))));
				pedido.setStatus(StatusPedido.get(rs.getString("status")));
				pedido.setTotal(rs.getDouble("total"));
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
				pedido.setCodigo(rs.getInt("codigoPedido"));
				pedido.setMesa(rs.getString("mesa"));
				pedido.setDataPedido(LocalDate.parse(rs.getDate("dataPedido").toString()));
				pedido.setStatus(StatusPedido.get(rs.getString("status")));
				pedido.setTotal(rs.getDouble("total"));
				pedidos.add(pedido);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return pedidos;
	}

	@Override
	public Integer ultimoPedidoId() {
		Integer codigo = null;
		
		String sql = "select max(codigoPedido) as codigoPedido from pedido";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			codigo =  rs.getInt("codigoPedido");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigo;
	}

	@Override
	public List<Pedido> pedidoEmProcesso() {
		List<Pedido> listaFiltro = new ArrayList<>();
		for(Pedido ped: this.todos()){
			ped.getStatus();
			if(ped.getStatus() == StatusPedido.PROCESSANDO){
				listaFiltro.add(ped);
			}
		}
		return listaFiltro;
	}

	@Override
	public List<Pedido> pedidoPronto() {
		List<Pedido> listaFiltro = new ArrayList<>();
		for(Pedido ped: this.todos()){
			if(ped.isPronto() == StatusPedido.CAIXA)
				listaFiltro.add(ped);
		}
		return listaFiltro;
	}

	@Override
	public List<Pedido> todosPedidosConcluidos() {
List<Pedido> pedidos = new ArrayList<Pedido>();
		
		String sql = "select * from pedido where status = 'CONCLUIDO'";
		
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Pedido pedido = new Pedido();
				pedido.setCodigo(rs.getInt("codigoPedido"));
				pedido.setMesa(rs.getString("mesa"));
				pedido.setDataPedido(LocalDate.parse(rs.getDate("dataPedido").toString()));
				pedido.setStatus(StatusPedido.get(rs.getString("status")));
				pedido.setTotal(rs.getDouble("total"));
				pedidos.add(pedido);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return pedidos;
	}

	
	

}
