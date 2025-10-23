package com.example.lab8_20202132.Beans;

public class libro {
    private int id;
    private String titulo;
    private String autor;
    private int paginas;
    private int premios;
    private editorial editorial_id;
    private genero genero_id;
    private genero genero;
    private editorial editorial;

    public libro() {
    }

    public libro(int id, String titulo, String autor, int paginas, int premios, editorial editorial_id,
            genero genero_id) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.premios = premios;
        this.editorial_id = editorial_id;
        this.genero_id = genero_id;
        this.editorial = editorial;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getPremios() {
        return premios;
    }

    public void setPremios(int premios) {
        this.premios = premios;
    }

    public editorial getEditorial_id() {
        return editorial_id;
    }

    public void setEditorial_id(editorial editorial_id) {
        this.editorial_id = editorial_id;
    }

    public genero getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(genero genero_id) {
        this.genero_id = genero_id;
    }

    public genero getGenero() {
        return genero;
    }

    public void setGenero(genero genero) {
        this.genero = genero;
    }

    public editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(editorial editorial) {
        this.editorial = editorial;
    }
}
