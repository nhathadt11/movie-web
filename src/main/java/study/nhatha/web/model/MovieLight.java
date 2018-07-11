package study.nhatha.web.model;

import study.nhatha.model.Movie;

import javax.persistence.Column;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

import static study.nhatha.web.util.StringUtils.truncate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "title",
    "year",
    "plot",
    "image"
})
@XmlRootElement(name = "movie")
public class MovieLight implements Serializable {

  @XmlElement(required = true)
  protected Long id;

  @XmlElement(required = true)
  @Column(columnDefinition = "text")
  protected String title;

  @XmlSchemaType(name = "unsignedShort")
  protected int year;

  @XmlElement(required = true)
  @Column(columnDefinition = "text")
  protected String plot;

  @XmlElement(required = true)
  @Column(columnDefinition = "text")
  protected String image;

  public MovieLight() {
  }

  public MovieLight(Long id, String title, int year, String plot, String image) {
    this.id = id;
    this.title = title;
    this.year = year;
    this.plot = plot;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getPlot() {
    return plot;
  }

  public void setPlot(String plot) {
    this.plot = plot;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public static MovieLight fromMovie(Movie movie) {
    return new MovieLight(
        movie.getId(),
        movie.getTitle(),
        movie.getYear(),
        truncate(movie.getPlot(), 105),
        movie.getImage()
    );
  }
}
