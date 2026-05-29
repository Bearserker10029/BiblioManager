<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<com.example.BiblioManager.Beans.libro> lista = (ArrayList<com.example.BiblioManager.Beans.libro>) request.getAttribute("lista");
   if (lista == null) { lista = new ArrayList<>(); }
   ArrayList<com.example.BiblioManager.Beans.genero> generos = (ArrayList<com.example.BiblioManager.Beans.genero>) request.getAttribute("generos");
   if (generos == null) { generos = new ArrayList<>(); }
   ArrayList<com.example.BiblioManager.Beans.editorial> editoriales = (ArrayList<com.example.BiblioManager.Beans.editorial>) request.getAttribute("editoriales");
   if (editoriales == null) { editoriales = new ArrayList<>(); }
%>
<html>
    <head>
        <title>Lista de Libros</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="clearfix mt-3 mt-2">
                <h1 class="float-start link-dark">Lista de Libros</h1>
                <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/LibroServlet?action=Crear">Crear libro</a>
            </div>
            <hr/>
            <form method="get" action="<%=request.getContextPath()%>/LibroServlet" class="row g-3 mb-3">
                <div class="col-md-4">
                    <label class="form-label">Género</label>
                    <select class="form-select" name="generoId">
                        <option value="">Todos los géneros</option>
                        <% for (com.example.BiblioManager.Beans.genero genero : generos) { %>
                            <option value="<%=genero.getId()%>" <%= request.getParameter("generoId") != null && request.getParameter("generoId").equals(String.valueOf(genero.getId())) ? "selected" : "" %>><%=genero.getNombre()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Editorial</label>
                    <select class="form-select" name="editorialId">
                        <option value="">Todas las editoriales</option>
                        <% for (com.example.BiblioManager.Beans.editorial editorial : editoriales) { %>
                            <option value="<%=editorial.getId()%>" <%= request.getParameter("editorialId") != null && request.getParameter("editorialId").equals(String.valueOf(editorial.getId())) ? "selected" : "" %>><%=editorial.getName()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="col-md-4 d-flex align-items-end">
                    <button type="submit" class="btn btn-primary me-2">Filtrar</button>
                    <a class="btn btn-secondary" href="<%=request.getContextPath()%>/LibroServlet">Limpiar</a>
                </div>
            </form>
            <table class="table table-striped mt-3">
                <tr class="table-primary">
                    <th>ID</th>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Páginas</th>
                    <th>Premios</th>
                    <th>Género</th>
                    <th>Editorial</th>
                    <th></th>
                </tr>
                <% for (com.example.BiblioManager.Beans.libro libro : lista) { %>
                <tr>
                    <td><%=libro.getId()%></td>
                    <td><%=libro.getTitulo()%></td>
                    <td><%=libro.getAutor()%></td>
                    <td><%=libro.getPaginas()%></td>
                    <td><%=libro.getPremios()%></td>
                    <td><%=libro.getGenero().getNombre()%></td>
                    <td><%=libro.getEditorial().getName()%></td>
                    <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/LibroServlet?action=borrar&id=<%= libro.getId() %>">Borrar</a></td>
                </tr>
                <% } %>
            </table>
        </div>
    </body>
</html>