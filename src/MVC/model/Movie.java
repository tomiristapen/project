package MVC.model;

import java.util.List;

class Movie {
    private String title;
    private String genre;
    private List<Show> shows;

    public Movie() {
    }

    public Movie(String title, String genre, List<Show> shows) {
        this.title = title;
        this.genre = genre;
        this.shows = shows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }


    public void addShow(Show show) {
        this.shows.add(show);
    }

    public void removeShow(Show show) {
        this.shows.remove(show);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title: '" + title + '\'' +
                ", genre: '" + genre + '\'' +
                ", shows: " + shows +
                '}';
    }
}
