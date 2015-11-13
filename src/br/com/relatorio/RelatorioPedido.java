package br.com.relatorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.conexao.Conexao;
import br.com.factory.DaoFactory;
import br.com.model.Pedido;

public class RelatorioPedido {
	
	public static void main(String[] args){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("mesa", 3);
		
		//new RelatorioPedido().viewReport("src/br.com.relatorio/Relatorio_Pedidos.jasper", Conexao.getCon(), parametros);
		
		new RelatorioUtil().gerarPdf("src/br/com/relatorio/Relatorio_Pedidos.jasper", Conexao.getCon(), parametros);
		
		List<Pedido> pedidos = DaoFactory.getDaoFactory().pedidoDAO().todosPedidosConcluidos();
		
		new RelatorioUtil().compileViewReport("src/br/com/relatorio/Relatorio_Pedidos.jasper", Conexao.getCon(), parametros);
	}

	

}
