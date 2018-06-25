package study.nhatha.web;

import study.nhatha.web.controller.MovieController;
import study.nhatha.web.error.MovieNotFoundException;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;

/**
 * Hello world!
 *
 */
public class WebApp {
  public static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";

  public static void main( String[] args ) {
    after((request, response) -> response.type("text/html,text/xml,application/xml;charset=utf-8"));
    after((request, response) -> response.body(XML_DECLARATION + "<movies>" + response.body() + "</movies>"));

    get("/movies",      new MovieController.AllMoviesHandler());
    get("/movies/:id",  new MovieController.MovieDetailHandler());

    exception(MovieNotFoundException.class, (exp, req, res) -> {
      res.status(404);
      res.type("text/html;charset=utf-8");
      res.body("<errors><reason>" + exp.getMessage() + "</reason></errors>");
    });
  }
}
