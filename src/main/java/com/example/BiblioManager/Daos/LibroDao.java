package com.example.BiblioManager.Daos;

import java.sql.*;
import java.util.ArrayList;

import com.example.BiblioManager.Beans.genero;
import com.example.BiblioManager.Beans.libro;
import com.example.BiblioManager.Beans.editorial;

public class LibroDao extends DaoBase {
    public libro obtenerLibro(String id) {
        libro libro = null;
        try {
            Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from libro where id = ?");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                libro = new libro();
                libro.setId(rs.getInt(1));
                libro.setTitulo(rs.getString(2));
                libro.setAutor(rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libro;
    }

    public void borrarLibro(String id) {
        try {
            Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("delete from libro where id = ?");
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<libro> listar() {
        ArrayList<libro> list = new ArrayList<>();
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from libro inner join genero g on libro.genero_id = g.id inner join editorial e on libro.editorial_id = e.id");

            while (rs.next()) {
                libro libro = new libro();
                libro.setId(rs.getInt(1));
                libro.setTitulo(rs.getString(2));
                libro.setAutor(rs.getString(3));
                libro.setPaginas(rs.getInt(4));
                libro.setPremios(rs.getInt(5));
                genero genero = new genero();
                genero.setId(rs.getInt("g.id"));
                genero.setNombre(rs.getString("g.nombre"));
                libro.setGenero(genero);
                editorial editorial = new editorial();
                editorial.setId(rs.getInt("e.id"));
                editorial.setName(rs.getString("e.nombre"));
                libro.setEditorial(editorial);
                list.add(libro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public libro crear(libro libro) {
        try {
            Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into libro (titulo, autor, paginas, premios, genero_id, editorial_id) values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setInt(3, libro.getPaginas());
            pstmt.setInt(4, libro.getPremios());
            pstmt.setInt(5, libro.getGenero_id().getId());
            pstmt.setInt(6, libro.getEditorial_id().getId());
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                libro.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libro;
    }

    public libro eliminar(libro libro) {
        try {
            Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("delete from libro where id = ?");
            pstmt.setInt(1, libro.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libro;
    }

    public ArrayList<genero> listarGeneros() {
        ArrayList<genero> lista = new ArrayList<>();
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id, nombre from genero");
            while (rs.next()) {
                genero genero = new genero();
                genero.setId(rs.getInt(1));
                genero.setNombre(rs.getString(2));
                lista.add(genero);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public ArrayList<editorial> listarEditoriales() {
        ArrayList<editorial> lista = new ArrayList<>();
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id, nombre from editorial");
            while (rs.next()) {
                editorial editorial = new editorial();
                editorial.setId(rs.getInt(1));
                editorial.setName(rs.getString(2));
                lista.add(editorial);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public ArrayList<libro> listarPorFiltros(Integer generoId, Integer editorialId) {
        ArrayList<libro> list = new ArrayList<>();
        try {
            Connection conn = this.getConnection();
            String sql = "select * from libro inner join genero g on libro.genero_id = g.id inner join editorial e on libro.editorial_id = e.id WHERE 1=1";
            if (generoId != null) sql += " AND libro.genero_id = " + generoId;
            if (editorialId != null) sql += " AND libro.editorial_id = " + editorialId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                libro libro = new libro();
                libro.setId(rs.getInt(1));
                libro.setTitulo(rs.getString(2));
                libro.setAutor(rs.getString(3));
                libro.setPaginas(rs.getInt(4));
                libro.setPremios(rs.getInt(5));
                genero genero = new genero();
                genero.setId(rs.getInt("g.id"));
                genero.setNombre(rs.getString("g.nombre"));
                libro.setGenero(genero);
                editorial editorial = new editorial();
                editorial.setId(rs.getInt("e.id"));
                editorial.setName(rs.getString("e.nombre"));
                libro.setEditorial(editorial);
                list.add(libro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
