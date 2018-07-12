package study.nhatha.web;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.nhatha.web.controller.MovieController;
import study.nhatha.web.error.MovieNotFoundException;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class WebApp {
  private static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
  private static final Logger LOGGER = LoggerFactory.getLogger(WebApp.class);

  public static void main( String[] args ) {
    configPort();
    configCors();
    configLog();

    after((request, response) -> response.type("text/html,text/xml,application/xml;charset=utf-8"));
    after((request, response) -> response.body(XML_DECLARATION + response.body()));

    get("/movies",                  new MovieController.AllMoviesHandler());
    get("/movies/:id",              new MovieController.MovieDetailHandler());
    get("/movies/page/:pageNumber", new MovieController.MoviesByPageAndTitleLike());

    exception(MovieNotFoundException.class, (exp, req, res) -> {
      res.status(404);
      res.type("text/html,text/xml,application/xml;charset=utf-8");
      res.body("<errors><reason>" + exp.getMessage() + "</reason></errors>");
    });

    exception(Exception.class, (exp, req, res) -> LOGGER.error(exp.getMessage()));
  }

  private static void configPort() {
    int port = 4567;

    if (System.getenv("PORT") != null) {
      port = Integer.parseInt(System.getenv("PORT"));
    }

    port(port);
  }

  private static void configLog() {
    before((request, response) -> LOGGER.info(String.format("[%s] %s", request.requestMethod(), request.uri())));
  }

  private static void configCors() {
    options("/*",
        (request, response) -> {
          String accessControlRequestHeaders = request
              .headers("Access-Control-Request-Headers");
          if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers",
                accessControlRequestHeaders);
          }

          String accessControlRequestMethod = request
              .headers("Access-Control-Request-Method");
          if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods",
                accessControlRequestMethod);
          }

          response.status(HttpStatus.NO_CONTENT_204);

          return "";
        });

    after((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
  }
}
