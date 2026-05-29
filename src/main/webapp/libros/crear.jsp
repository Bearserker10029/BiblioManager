<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<com.example.BiblioManager.Beans.genero> generos = (ArrayList<com.example.BiblioManager.Beans.genero>) request.getAttribute("generos");
   if (generos == null) { generos = new ArrayList<>(); }
   ArrayList<com.example.BiblioManager.Beans.editorial> editoriales = (ArrayList<com.example.BiblioManager.Beans.editorial>) request.getAttribute("editoriales");
   if (editoriales == null) { editoriales = new ArrayList<>(); }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
        <title>Crear un nuevo libro</title>
    </head>
    <body>
        <div class='container'>
            <h1 class='mb-3'>Crear un nuevo libro</h1>
            <form method="post" action="<%=request.getContextPath()%>/LibroServlet">
                <input type="hidden" name="action" value="crear">
                <div class="mb-3">
                    <label>Título</label>
                    <input type="text" class="form-control" name="titulo" required>
                </div>
                <div class="mb-3">
                    <label>Autor</label>
                    <input type="text" class="form-control" name="autor" required>
                </div>
                <div class="mb-3">
                    <label>Páginas</label>
                    <input type="number" class="form-control" name="paginas" required>
                </div>
                <div class="mb-3">
                    <label>Premios</label>
                    <input type="number" class="form-control" name="premios" value="0" required>
                </div>
                <div class="mb-3">
                    <label>Género</label>
                    <select class="form-select" name="genero_id" required>
                        <option value="">Seleccione un género</option>
                        <% for (com.example.BiblioManager.Beans.genero genero : generos) { %>
                            <option value="<%=genero.getId()%>"><%=genero.getNombre()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label>Editorial</label>
                    <select class="form-select" name="editorial_id" required>
                        <option value="">Seleccione una editorial</option>
                        <% for (com.example.BiblioManager.Beans.editorial editorial : editoriales) { %>
                            <option value="<%=editorial.getId()%>"><%=editorial.getName()%></option>
                        <% } %>
                    </select>
                </div>
                <a href="<%=request.getContextPath()%>/LibroServlet" class="btn btn-danger">Regresar</a>
                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
    </body>
</html>