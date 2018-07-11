package study.nhatha.web.model;

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

  @XmlElements(@XmlElement(name = "movie", type = MovieLight.class, namespace = "http://movie.vn/MovieSchema"))
  private List<MovieLight> movies;

  public MovieList() {
  }

  public List<MovieLight> getMovies() {
    return movies;
  }

  public void setMovies(List<MovieLight> movies) {
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
