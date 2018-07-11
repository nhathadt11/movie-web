package study.nhatha.web.util;

public final class StringUtils {
  public static String truncate(String content, int letterCount) {
    if (!isVisibleContent(content)) {
      throw new IllegalArgumentException("No visible content found");
    }

    if (letterCount <= 0) {
      throw new IllegalArgumentException("Letter count must be a positive integer");
    }

    int actualLength = content.length();

    return content.substring(0, letterCount < actualLength ? letterCount : actualLength);
  }

  public static boolean isVisibleContent(String content) {
    return (content != null) && !content.trim().isEmpty();
  }
}
