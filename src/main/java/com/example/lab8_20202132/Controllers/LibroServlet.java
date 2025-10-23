package com.example.lab8_20202132.Controllers;

import com.example.lab8_20202132.Daos.LibroDao;
import com.example.lab8_20202132.Beans.libro;
import com.example.lab8_20202132.Beans.editorial;
import com.example.lab8_20202132.Beans.genero;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LibroServlet", value = "/LibroServlet")
public class LibroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        LibroDao libroDao = new LibroDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                ArrayList<libro> listaLibros = libroDao.listar();
                request.setAttribute("lista", listaLibros);
                view = request.getRequestDispatcher("libros/lista.jsp");
                view.forward(request, response);
                break;
            case "Crear":
                view = request.getRequestDispatcher("libros/crear.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                String libroID = request.getParameter("id");
                if (libroDao.obtenerLibro(libroID) != null) {
                    libroDao.borrarLibro(libroID);
                }
                response.sendRedirect(request.getContextPath() + "/LibroServlet");
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        LibroDao libroDao = new LibroDao();

        switch (action) {
            case "crear":
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");
                int paginas = Integer.parseInt(request.getParameter("paginas"));
                int premios = Integer.parseInt(request.getParameter("premios"));
                int editorialId = Integer.parseInt(request.getParameter("editorial_id"));
                int generoId = Integer.parseInt(request.getParameter("genero_id"));

                libro nuevoLibro = new libro();
                nuevoLibro.setTitulo(titulo);
                nuevoLibro.setAutor(autor);
                nuevoLibro.setPaginas(paginas);
                nuevoLibro.setPremios(premios);
                editorial editorial = new editorial();
                genero genero = new genero();
                editorial.setId(editorialId);
                genero.setId(generoId);
                nuevoLibro.setEditorial_id(editorial);
                nuevoLibro.setGenero_id(genero);

                libroDao.crear(nuevoLibro);
                response.sendRedirect(request.getContextPath() + "/LibroServlet");
                break;
            case "eliminar":
                String libroID = request.getParameter("id");
                if (libroDao.obtenerLibro(libroID) != null) {
                    libroDao.borrarLibro(libroID);
                }
                response.sendRedirect(request.getContextPath() + "/LibroServlet");
                break;
        }
    }
}
