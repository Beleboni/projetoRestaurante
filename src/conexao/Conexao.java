package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection connection;
	
	static{
		String endereco = "jdbc:mysql://localhost:3306/bancoRestaurante";
		String usuario = "root";
		String senha = "";
	
	try{
		connection = DriverManager.getConnection(endereco, usuario, senha);
	} catch(SQLException e ){
		System.out.println("Erro ao se conectar com o banco de dados");
		e.printStackTrace();
		
		}
	}
	
	public static Connection getCon(){
		return connection;
	}
	
}
