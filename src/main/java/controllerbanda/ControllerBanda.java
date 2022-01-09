package controllerbanda;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelbanda.DAOBanda;
import modelbanda.JavaBeansBanda;

@WebServlet(urlPatterns = { "/ControllerBanda", "/main", "/insert", "/select", "/update", "/delete" })
public class ControllerBanda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOBanda daobanda = new DAOBanda();
	JavaBeansBanda bandajv = new JavaBeansBanda();

	public ControllerBanda() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		// daobanda.testeConexão();
		String action = request.getServletPath();
		System.out.println(action);
		if(action.equals("/main")){
			bandas(request,response);
		}else if(action.equals("/insert")){
			novaBanda(request,response);
		}else if(action.equals("/select")){
			listarBanda(request,response);
		}else if(action.equals("/update")){
			editarBanda(request,response);
		}else if(action.equals("/delete")){
			removerBanda(request,response);
		}else {
			response.sendRedirect("index.html");
		}
	}

	// Listar Bandas
	protected void bandas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.sendRedirect("banda.jsp");
		// Criando um objeto que irá receber os dados JavaBeansBanda
		//esse objeto excuta o me´todo listarBanda()--- controller->DAO
		ArrayList<JavaBeansBanda> lista = daobanda.listarBanda();
		// Encaminhar a lista ao documento banda.jsp
		request.setAttribute("bandaLista", lista);
		RequestDispatcher rdbanda = request.getRequestDispatcher("banda.jsp");
        rdbanda.forward(request, response);
        
     // teste de recebimento da lista
     		
     		  for (int i = 0; i < lista.size(); i++) {
     		  System.out.println(lista.get(i).getIdbanda());
     		  System.out.println(lista.get(i).getNome());
     		  System.out.println(lista.get(i).getGenero());
     		  }
     		  
     		 
	}
	
	protected void novaBanda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//teste de recebimentos dos dados do fomulário
		/*System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("genero"));
		response.sendRedirect("main");*/
		
		//setar as variáveis JavaBeans
		
		bandajv.setNome(request.getParameter("nome"));
		bandajv.setGenero(request.getParameter("genero"));
		
		daobanda.inserirBanda(bandajv);
		
		response.sendRedirect("main");

	}
	//editar banda -selecionar banda
	protected void listarBanda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Recebimento do id da banda que será editada
		String idbanda = request.getParameter("idbanda");
		System.out.println(idbanda);
		//controller -> javabeans
		bandajv.setIdbanda(idbanda);
		daobanda.selecionarBanda(bandajv);
		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idbanda", bandajv.getIdbanda());
		request.setAttribute("nome", bandajv.getNome());
		request.setAttribute("genero", bandajv.getGenero());
		//Encaminhar ao documento editarbanda.jsp
		RequestDispatcher rdbanda = request.getRequestDispatcher("editarbanda.jsp");
		rdbanda.forward(request, response);
		//teste de recebimento
		/*System.out.println(bandajv.getIdbanda());
		System.out.println(bandajv.getNome());
		System.out.println(bandajv.getGenero());*/
	}
	
	protected void editarBanda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//teste de recebimento
		/*System.out.println(request.getParameter("idbanda"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("genero"));*/
		
		//setar os novos valores nas varia´veis javabeans
		bandajv.setIdbanda(request.getParameter("idbanda"));
		bandajv.setNome(request.getParameter("nome"));
		bandajv.setGenero(request.getParameter("genero"));
		daobanda.alterarBanda(bandajv); 
		//redirecionar para o documento banda.jsp alterando as informações
		response.sendRedirect("main");
	}
	
	protected void removerBanda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//recebimento do id para ser excluido
		String idbanda = request.getParameter("idbanda");
		//teste
		//System.out.println(idbanda);
		//setar a variável idbanda  JV
		bandajv.setIdbanda(idbanda);
		daobanda.deletarBanda(bandajv);
		//redirecionar para o documento banda.jsp alterando as informações
				response.sendRedirect("main");
	}

}
