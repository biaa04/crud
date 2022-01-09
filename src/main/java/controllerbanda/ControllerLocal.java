package controllerbanda;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelbanda.DAOLocal;
import modelbanda.JavaBeansLocal;

@WebServlet(urlPatterns = { "/ControllerLocais", "/mainlocal", "/insertlocal", "/select", "/updatelocal" })
public class ControllerLocal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOLocal daolocal = new DAOLocal();
	JavaBeansLocal localjv = new JavaBeansLocal();

	public ControllerLocal() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		daolocal.testeConexão();
		String actionlocal = request.getServletPath();
		System.out.println(actionlocal);
		if (actionlocal.equals("/mainlocal")) {
			locais(request, response);
		} else if (actionlocal.equals("/insertlocal")) {
			novoLocal(request, response);
		} else if (actionlocal.equals("/select")) {
			listarLocal(request, response);
		} else if (actionlocal.equals("/updatetlocal")) {
			editarLocal(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void locais(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("local.jsp");
		ArrayList<JavaBeansLocal> listaL = daolocal.listarLocal();
		request.setAttribute("localLista", listaL);
		RequestDispatcher rdlocal = request.getRequestDispatcher("local.jsp");
		rdlocal.forward(request, response);

		for (int i = 0; i < listaL.size(); i++) {
			System.out.println(listaL.get(i).getIdlocal());
			System.out.println(listaL.get(i).getEndereco());
			System.out.println(listaL.get(i).getCapacidade());
		}

	}

	protected void novoLocal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("endereco"));
		System.out.println(request.getParameter("capacidade"));

		localjv.setEndereco(request.getParameter("endereco"));
		localjv.setCapacidade(request.getParameter("capacidade"));
		daolocal.inserirLocal(localjv);
		response.sendRedirect("mainlocal");

	}

	protected void listarLocal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idlocal = request.getParameter("idlocal");
		System.out.println(idlocal);
		localjv.setIdlocal(idlocal);
		daolocal.alterarLocal(localjv);
		request.setAttribute("idlocal", localjv.getIdlocal());
		request.setAttribute("endereco", localjv.getEndereco());
		request.setAttribute("capacidade", localjv.getCapacidade());
		RequestDispatcher rdlocal = request.getRequestDispatcher("editarlocal.jsp");
		rdlocal.forward(request, response);

		System.out.println(localjv.getIdlocal() + "eeee");
		System.out.println(localjv.getEndereco());
		System.out.println(localjv.getCapacidade());
	}

	protected void editarLocal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento

		System.out.println(request.getParameter("idlocal" + "c"));
		System.out.println(request.getParameter("endereco" + "C"));
		System.out.println(request.getParameter("capacidade" + "C"));

		// setar os novos valores nas varia´veis javabeans
		localjv.setIdlocal(request.getParameter("idlocal"));
		localjv.setEndereco(request.getParameter("endereco"));
		localjv.setCapacidade(request.getParameter("capacidade"));
		daolocal.alterarLocal(localjv);
		// redirecionar para o documento banda.jsp alterando as informações
		response.sendRedirect("mainlocal");
	}

}
