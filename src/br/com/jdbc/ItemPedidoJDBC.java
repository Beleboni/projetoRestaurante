package br.com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.conexao.Conexao;
import br.com.dao.ItemPedidoDAO;
import br.com.dao.ProdutoDAO;
import br.com.model.ItemPedido;
import br.com.model.Pedido;
import br.com.model.Produto;
import br.com.tipo.StatusItemPedido;
import br.com.tipo.StatusProduto;
import br.com.tipo.TipoProduto;

public class ItemPedidoJDBC implements ItemPedidoDAO {

	// GERENCIAMENTO DE CADASTROR DE PRODUTO DENTO DO SISTEMA
	private Connection con;

	public ItemPedidoJDBC() {
		con = Conexao.getCon();
	}

	@Override
	public void inserir(ItemPedido itemPedido) {
		String sql = "insert into itempedido (codigoProduto, codigoPedido, status) values (?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemPedido.getProduto().getCodigo());
			pstmt.setInt(2, itemPedido.getPedido().getCodigo());
			pstmt.setString(3, itemPedido.getStatus().toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(ItemPedido entidade) {
		// Altera item pedido
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
				itemPedido.setStatus(StatusItemPedido.get(rs
						.getString("status")));
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
				produto.setStatus(StatusProduto.get(rs.getString("status")));
				produto.setTipoProduto(TipoProduto.get(rs
						.getString("tipoProduto")));
				produto.setValor(rs.getDouble("valor"));
				itemPedido.setProduto(produto);
				itemPedido.setPedido(new Pedido(rs.getInt("codigoItemPedido")));
				itemPedido.setStatus(StatusItemPedido.get(rs
						.getString("status")));
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
		String sql = "select p.codigo as codProduto, p.descricao as prodDescricao, p.valor as prodValor, "
				+ "p.tipoProduto as prodTipo, p.status as prodStatus, ip.status as itemStatus, ip.codigoItemPedido as codItem "
				+ "from produto p join itempedido ip on p.codigo = ip.codigoProduto "
				+ "join pedido pe on ip.codigoPedido = pe.codigoPedido where mesa = ? group by p.descricao";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mesa);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setCodigo(rs.getInt("codItem"));
				itemPedido.setStatus(StatusItemPedido.get(rs
						.getString("itemStatus")));

				Produto p = new Produto();
				p.setCodigo(rs.getInt("codProduto"));
				p.setDescricao(rs.getString("prodDescricao"));
				p.setTipoProduto(TipoProduto.get(rs.getString("prodTipo")));
				p.setStatus(StatusProduto.get(rs.getString("prodStatus")));
				p.setValor(rs.getDouble("prodValor"));

				itemPedido.setProduto(p);
				itens.add(itemPedido);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;
	}

	@Override
	public void alterarStatus(ItemPedido itemPedido) {
		String sql = "update itempedido set status = ? where codigoItemPedido = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemPedido.getStatus().toString());
			pstmt.setInt(2, itemPedido.getCodigo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
