package study.nhatha.web;

import study.nhatha.web.controller.MovieController;

import static spark.Spark.after;
import static spark.Spark.get;

/**
 * Hello world!
 *
 */
public class WebApp {
  public static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";

  public static void main( String[] args ) {
    after((request, response) -> response.header("Content-Type", "text/xml,application/xml"));
    after((request, response) -> response.body(XML_DECLARATION + "<movies>" + response.body() + "</movies>"));

    get("/movies", new MovieController.AllMoviesHandler());
  }
}
