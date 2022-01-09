package modelbanda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOLocal {
	// parâmetro de conexão

		private String driver = "com.mysql.cj.jdbc.Driver";
		private String url = "jdbc:mysql://localhost:3306/sbl?useTimezone=true&serverTimezone=UTC";
		private String user = "root";
		private String password = "root";

		private Connection conectarlocal() {
			Connection conlocal = null;
			try {
				Class.forName(driver);
				conlocal = DriverManager.getConnection(url, user, password);
				return conlocal;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		
		public void inserirLocal(JavaBeansLocal localjv) {
			String createlocal = "insert into local(endereco,capacidade) values(?,?)";
			try {
				Connection conlocal = conectarlocal();
				PreparedStatement pstlocal = conlocal.prepareStatement(createlocal);
				pstlocal.setString(1, localjv.getEndereco());
				pstlocal.setString(2, localjv.getCapacidade());
				pstlocal.executeUpdate();
				conlocal.close();
				
			} catch (Exception e) {
				System.out.println(e+" No insertlocal");
			}
		}
		
		public ArrayList<JavaBeansLocal> listarLocal(){
			//Criando um objeto para enxergar as variáveis JavaBeansLocal
			ArrayList<JavaBeansLocal> localLista = new ArrayList();
			String readlocal = "select*from local order by endereco";
			
			try {
				Connection conlocal = conectarlocal();
				PreparedStatement pstlocal = conlocal.prepareStatement(readlocal);
				ResultSet rslocal = pstlocal.executeQuery();
				while(rslocal.next()) {
					String idlocal = rslocal.getString(1);
					String endereco = rslocal.getString(2);
					String capacidade = rslocal.getString(3);
					localLista.add(new JavaBeansLocal(idlocal,endereco,capacidade));
				}
				conlocal.close();
				return localLista;
				
			} catch (Exception e) {
				System.out.println(e+" No listarLocal");
				return null;
			}
			
			
		}
		
		//editar 
		
		public void selecionarLocal(JavaBeansLocal localjv) {
			String readlocal2 = "select*from local where idlocal=?";
			try {
				Connection conlocal = conectarlocal();
				PreparedStatement pstlocal = conlocal.prepareStatement(readlocal2);
				pstlocal.setString(1, localjv.getIdlocal());
				ResultSet rslocal = pstlocal.executeQuery();
				while(rslocal.next()) {
					localjv.setIdlocal(rslocal.getString(1));
					localjv.setEndereco(rslocal.getString(2));
					localjv.setCapacidade(rslocal.getString(3));
				}
				conlocal.close();
				
			} catch (Exception e) {
				System.out.println(e+" No selecionarLocal");
			}
		}
		
		public void alterarLocal(JavaBeansLocal localjv) {
			String updatelocal = "update local set endereco=?, capacidade=? where idlocal=?";
			try {
				Connection conlocal = conectarlocal();
				PreparedStatement pstlocal = conlocal.prepareStatement(updatelocal);
				pstlocal.setString(1, localjv.getEndereco());
				pstlocal.setString(2, localjv.getCapacidade());
				pstlocal.setString(3, localjv.getIdlocal());
				pstlocal.executeUpdate();
				conlocal.close();
				
			} catch (Exception e) {
				System.out.println(e+" no alterarLocal");
			}
		}
		
		
		public void testeConexão() {
			try {
				Connection con = conectarlocal();
				System.out.println(con+" conectado");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

}
