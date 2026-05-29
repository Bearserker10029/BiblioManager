package com.example.BiblioManager.Controllers;

import com.example.BiblioManager.Daos.LibroDao;
import com.example.BiblioManager.Beans.libro;
import com.example.BiblioManager.Beans.editorial;
import com.example.BiblioManager.Beans.genero;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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
                String generoIdStr = request.getParameter("generoId");
                String editorialIdStr = request.getParameter("editorialId");
                Integer generoId = (generoIdStr != null && !generoIdStr.isEmpty()) ? Integer.parseInt(generoIdStr) : null;
                Integer editorialId = (editorialIdStr != null && !editorialIdStr.isEmpty()) ? Integer.parseInt(editorialIdStr) : null;

                ArrayList<libro> listaLibros = libroDao.listarPorFiltros(generoId, editorialId);
                ArrayList<genero> generos = libroDao.listarGeneros();
                ArrayList<editorial> editoriales = libroDao.listarEditoriales();

                request.setAttribute("lista", listaLibros);
                request.setAttribute("generos", generos);
                request.setAttribute("editoriales", editoriales);
                view = request.getRequestDispatcher("libros/lista.jsp");
                view.forward(request, response);
                break;
            case "Crear":
                ArrayList<genero> generosCrear = libroDao.listarGeneros();
                ArrayList<editorial> editorialesCrear = libroDao.listarEditoriales();
                request.setAttribute("generos", generosCrear);
                request.setAttribute("editoriales", editorialesCrear);
                view = request.getRequestDispatcher("libros/crear.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                String libroID = request.getParameter("id");
                libro libro = libroDao.obtenerLibro(libroID);
                if (libro != null && libro.getPremios() == 0) {
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

                genero genero = new genero();
                genero.setId(generoId);
                nuevoLibro.setGenero_id(genero);

                editorial editorial = new editorial();
                editorial.setId(editorialId);
                nuevoLibro.setEditorial_id(editorial);

                libroDao.crear(nuevoLibro);
                response.sendRedirect(request.getContextPath() + "/LibroServlet");
                break;
        }
    }
}