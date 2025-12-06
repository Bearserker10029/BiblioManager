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

        return null;
    }

    public libro eliminar(libro libro) {
        return null;
    }

}
