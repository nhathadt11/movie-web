package study.nhatha.web.model;

import study.nhatha.model.Movie;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "movies", namespace = "http://movie.vn/MovieListSchema")
@XmlType(name = "", propOrder = {
    "movies"
})
public class MovieList {

  @XmlAttribute(name = "totalCount", required = true, namespace = "http://movie.vn/MovieListSchema")
  private long totalCount;
  @XmlAttribute(name = "pageSize", required = true, namespace = "http://movie.vn/MovieListSchema")
  private int pageSize;
  @XmlElements(@XmlElement(name = "movie", type = Movie.class, namespace = "http://movie.vn/MovieSchema"))
  private List<Movie> movies;

  public MovieList() {
  }

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
