package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model;

public class ProfileDetail {
    public String nombreU;
    public int edad;
    public String generoFav;
    public String peliculaFav;
    public String directorFav;

    public String getNombreU() {
        return nombreU;
    }

    public int getEdad() {
        return edad;
    }

    public String getGeneroFav() {
        return generoFav;
    }

    public String getPeliculaFav() {
        return peliculaFav;
    }

    public String getDirectorFav() {
        return directorFav;
    }

    @Override
    public String toString() {
        return "ProfileDetail{" +
                "nombre='" + nombreU + '\'' +
                ", edad='" + edad + '\'' +
                ", genero favorito='" + generoFav + '\'' +
                ", pelicula favorita='" + peliculaFav + '\'' +
                ", director favorito='" + directorFav +
                '}';
    }
}
