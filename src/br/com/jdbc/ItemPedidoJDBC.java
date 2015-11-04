package br.com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import br.com.conexao.Conexao;
import br.com.dao.ItemPedidoDAO;
import br.com.dao.ProdutoDAO;
import br.com.model.ItemPedido;
import br.com.model.Pedido;
import br.com.model.Produto;
import br.com.tipo.TipoProduto;

public class ItemPedidoJDBC implements ItemPedidoDAO {

	// GERENCIAMENTO DE CADASTROR DE PRODUTO DENTO DO SISTEMA
	private Connection con;

	public ItemPedidoJDBC() {
		con = Conexao.getCon();
	}

	@Override
	public void inserir(ItemPedido itemPedido) {
		String sql = "insert into itempedido (codigoProduto, codigoPedido) values (?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemPedido.getProduto().getCodigo());
			pstmt.setInt(2, itemPedido.getPedido().getCodigo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(ItemPedido itemPedido) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(ItemPedido itemPedido) {
		// EXCLUIR UM PEDIDO

	}

	@Override
	public ItemPedido buscar(Integer id) {
		// BUSCAR UM PEDIDO
		ProdutoDAO produtoDAO = new ProdutoJDBC();
		ItemPedido itemPedido = null;
		String sql = "select * from itemPedido where codigoItemPedido =?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				itemPedido = new ItemPedido();
				itemPedido.setCodigo(rs.getInt("codigoItemPedido"));
				itemPedido.setProduto(produtoDAO.buscar(rs
						.getInt("codigoProduto")));
				itemPedido.setPedido(new Pedido(rs.getInt("codigoPedido")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemPedido;
	}

	@Override
	public List<ItemPedido> todos() {
		List<ItemPedido> itens = new ArrayList<>();
		String sql = "select * from pedido";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setCodigo(rs.getInt("codigoItemPedido"));
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigProduto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setStatus(rs.getBoolean("status"));
				produto.setTipoProduto(TipoProduto.get(rs
						.getString("tipoProduto")));
				produto.setValor(rs.getDouble("valor"));
				itemPedido.setProduto(produto);
				itemPedido.setPedido(new Pedido(rs.getInt("codigoItemPedido")));
				itens.add(itemPedido);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;
	}

	@Override
	public List<ItemPedido> itensPedidoPorMesa(Integer mesa) {
		List<ItemPedido> itens = new ArrayList<>();
		String sql = "select * from produto p join itempedido ip on p.codigo = ip.codigoProduto "
				+ "join pedido pe on ip.codigoPedido = pe.codigoPedido where mesa = ? group by p.descricao";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mesa);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ItemPedido itemPedido = new ItemPedido();
				Produto p = new Produto();
				p.setCodigo(rs.getInt("codigoProduto"));
				p.setDescricao(rs.getString("descricao"));
				p.setTipoProduto(TipoProduto.get(rs.getString("tipoProduto")));
				p.setStatus(rs.getBoolean("status"));
				p.setValor(rs.getDouble("valor"));
				
				itemPedido.setProduto(p);
				itens.add(itemPedido);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;
	}

}
