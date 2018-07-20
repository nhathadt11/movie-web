package study.nhatha.web.controller;

import spark.Request;
import spark.Response;
import spark.Route;
import study.nhatha.model.Movie;
import study.nhatha.repository.HibernateMovieRepository;
import study.nhatha.repository.MovieRepository;
import study.nhatha.web.error.MovieNotFoundException;
import study.nhatha.web.model.MovieLight;
import study.nhatha.web.model.MovieList;
import study.nhatha.web.util.XmlUtils;

import java.util.List;
import java.util.stream.Collectors;

public final class MovieController {
  private static int PAGE_SIZE = 20;
  private static MovieRepository movieRepository = HibernateMovieRepository.getInstance();

  public static class AllMoviesHandler implements Route {

    @Override
    public Object handle(Request request, Response response) {
      List<Movie> movies = movieRepository.all();

      return movies
          .stream()
          .map(MovieLight::fromMovie)
          .map(light -> XmlUtils.marshal(light, MovieLight.class))
          .collect(Collectors.joining());
    }
  }

  public static class MoviesByPageAndTitleLike implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
      int pageNumber = Integer.parseInt(request.params(":pageNumber"), 10);
      String title = request.queryParamOrDefault("title", "");

      List<MovieLight> movies = movieRepository
          .pageAndFullTextSearch(pageNumber, title)
          .stream()
          .map(MovieLight::fromMovie)
          .collect(Collectors.toList());
      long count = movieRepository.countByTitleFullTextSearch(title);

      MovieList movieList = new MovieList();
      movieList.setMovies(movies);
      movieList.setPageSize(PAGE_SIZE);
      movieList.setTotalCount(count);

      return XmlUtils.marshal(movieList, MovieList.class);
    }
  }

  public static class MovieDetailHandler implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
      long id = Long.parseLong(request.params(":id"));

      Movie movie = movieRepository
          .one(id)
          .orElseThrow(MovieNotFoundException::new);

      return XmlUtils.marshal(movie, Movie.class);
    }
  }

  private MovieController() {
  }
}
