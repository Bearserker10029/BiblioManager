<%@ page import="com.example.BiblioManager.Beans.libro" %>
    <%@ page import="java.util.ArrayList" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <% ArrayList<libro> lista = (ArrayList<libro>) request.getAttribute("lista");
                    if (lista == null) {
                    lista = new ArrayList<>();
                        }
                        %>
                        <!DOCTYPE html>
                        <html>

                        <head>
                            <jsp:include page="../includes/bootstrap_header.jsp" />
                            <title>Listar Libros</title>
                        </head>

                        <body>
                            <div class='container'>

                                <h1 class='mb-3'>Lista de libros</h1>
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>">Home</a>
                                        </li>
                                        <li class="breadcrumb-item active">Libros</li>
                                    </ol>
                                </nav>
                                <a class="btn btn-primary mb-3"
                                    href="<%=request.getContextPath()%>/LibroServlet?action=Crear">Crear
                                    Libro</a>
                                <table class="table">
                                    <tr>
                                        <th>libro ID</th>
                                        <th>titulo</th>
                                        <th>autor</th>
                                        <th>paginas</th>
                                        <th>premios</th>
                                        <th>genero</th>
                                        <th>editorial</th>
                                    </tr>
                                    <% for (libro libro : lista) { %>
                                        <tr>
                                            <td>
                                                <%=libro.getId()%>
                                            </td>
                                            <td>
                                                <%=libro.getTitulo()%>
                                            </td>
                                            <td>
                                                <%=libro.getAutor()%>
                                            </td>
                                            <td>
                                                <%=libro.getPaginas()%>
                                            </td>
                                            <td>
                                                <%=libro.getPremios()%>
                                            </td>
                                            <td>
                                                <%=libro.getGenero().getNombre()%>
                                            </td>
                                            <td>
                                                <%=libro.getEditorial().getName()%>
                                            </td>

                                            <td>
                                                <a class="btn btn-danger"
                                                    href="<%=request.getContextPath()%>/LibroServlet?action=borrar&id=<%=libro.getId()%>">
                                                    <i class="bi bi-trash3"></i>
                                                </a>
                                            </td>
                                        </tr>
                                        <% } %>
                                </table>
                            </div>
                            <jsp:include page="../includes/bootstrap_footer.jsp" />
                        </body>

                        </html>