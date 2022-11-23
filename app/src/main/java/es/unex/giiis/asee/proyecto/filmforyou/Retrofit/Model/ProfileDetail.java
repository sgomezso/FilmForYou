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

    public void setNombreU(String nombreU){this.nombreU=nombreU;}

    public void setEdad(int edad){this.edad=edad;}

    public void setGeneroFav(String generoFav){this.generoFav=generoFav;}

    public void setPeliculaFav(String peliculaFav){this.peliculaFav=peliculaFav;}

    public void setDirectorFav(String directorFav){this.directorFav=directorFav;}

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
