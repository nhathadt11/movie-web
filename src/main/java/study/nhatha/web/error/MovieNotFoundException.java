package study.nhatha.web.error;

public class MovieNotFoundException extends Exception {
  public MovieNotFoundException() {
    super("Movie cannot be found");
  }
}
