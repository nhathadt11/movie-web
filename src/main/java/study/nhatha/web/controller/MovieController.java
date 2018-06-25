package study.nhatha.web.controller;

import spark.Request;
import spark.Response;
import spark.Route;
import study.nhatha.model.Movie;
import study.nhatha.repository.HibernateMovieRepository;
import study.nhatha.repository.MovieRepository;
import study.nhatha.web.error.MovieNotFoundException;
import study.nhatha.web.util.XmlUtils;

import java.util.List;
import java.util.stream.Collectors;

public final class MovieController {
  private static MovieRepository movieRepository = HibernateMovieRepository.getInstance();

  public static class AllMoviesHandler implements Route {

    @Override
    public Object handle(Request request, Response response) {
      List<Movie> movies = movieRepository.all();

      return movies
          .stream()
          .map(movie -> XmlUtils.marshal(movie, Movie.class))
          .collect(Collectors.joining());
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
