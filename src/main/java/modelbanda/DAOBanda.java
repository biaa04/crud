package modelbanda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOBanda {
	// parâmetro de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/sbl?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";

	private Connection conectarbanda() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	//CRUD
	
	public void inserirBanda(JavaBeansBanda bandajv) {
		String create = "insert into banda(nome, genero) values(?,?)";
		try {
			//abrir a conexão com o banco
			Connection con = conectarbanda();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, bandajv.getNome());
			pst.setString(2, bandajv.getGenero());
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e+" No insert no DAO");
		}
	}
	
	public ArrayList<JavaBeansBanda> listarBanda(){
	//Criando um objeto para enxergar as variáveis JavaBeansBanda
		ArrayList<JavaBeansBanda> bandaLista = new ArrayList<>();
		String read = "select*from banda order by nome";
		
		try {
			Connection con = conectarbanda();
			PreparedStatement pst = con.prepareStatement(read);
			//o resultado da query é armavenado no objeto rsbanda
			ResultSet rsbanda = pst.executeQuery(); 
			while(rsbanda.next()) {
				String idbanda = rsbanda.getString(1);
				String nome = rsbanda.getString(2);
				String genero = rsbanda.getString(3);
				//O objeto bandaLista adiciona nas variáveis javabaenasbanda o conteúdo
				//das variáveis de apoio acima
				bandaLista.add(new JavaBeansBanda(idbanda,nome,genero));
			}
			con.close();
			return bandaLista;
		} catch (Exception e) {
			System.out.println(e+" no listar no DAO");
			return null;
		}
	}
	
	//editar - selecionar banda
	
	 public void selecionarBanda(JavaBeansBanda bandajv) {
		 String read2 = "select*from banda where idbanda=?";
		 try {
			 Connection con = conectarbanda();
			 PreparedStatement pst = con.prepareStatement(read2);
			 pst.setString(1, bandajv.getIdbanda());
			 ResultSet rsbanda = pst.executeQuery();
			 while (rsbanda.next()) {
				 //setar as variáveis jb
				 bandajv.setIdbanda(rsbanda.getString(1));
				 bandajv.setNome(rsbanda.getString(2));
				 bandajv.setGenero(rsbanda.getString(3));
			 }
			 con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		 
	 }
	 
	 public void alterarBanda(JavaBeansBanda bandajv) {
		 String update = "update banda set nome=?, genero=? where idbanda=?";
		 try {
			 Connection con = conectarbanda();
			 PreparedStatement pst = con.prepareStatement(update);
			 pst.setString(1, bandajv.getNome());
			 pst.setString(2, bandajv.getGenero());
			 pst.setString(3, bandajv.getIdbanda());
			 pst.executeUpdate();
			 con.close();
			
		} catch (Exception e) {
			System.out.println(e+" no alterarBanda no dao");
		}
	 }
	 
	 public void deletarBanda(JavaBeansBanda bandajv) {
		 String delete = "delete from banda where idbanda=?";
		 try {
			 Connection con = conectarbanda();
			 PreparedStatement pst = con.prepareStatement(delete);
			 pst.setString(1, bandajv.getIdbanda());
			 pst.executeUpdate();
			 con.close();

		
		} catch (Exception e) {
			System.out.println(e+" no deletarBAnda no dao");
		}
	 }

	/* teste

	public void testeConexão() {
		try {
			Connection con = conectarbanda();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}*/

}
