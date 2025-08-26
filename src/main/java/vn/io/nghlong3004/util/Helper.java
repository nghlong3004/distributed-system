package vn.io.nghlong3004.util;

import java.util.Random;

public class Helper {

  public static int rnd(int max) {
    return Math.abs(new Random().nextInt()) % max + 1;
  }

  public static String snakeCaseToCamelCase(String snakeCase) {
    StringBuilder camelCase = new StringBuilder();

    for (int i = 0; i < snakeCase.length(); ++i) {
      if (snakeCase.charAt(i) == '_') {
        if (i + 1 < snakeCase.length()) {
          camelCase.append(toUpperCase(snakeCase.charAt(++i)));
        } else {
          camelCase.append(snakeCase.charAt(i));
        }
      }
    }

    return camelCase.toString();
  }

  public static char toUpperCase(char charAt) {
    if (charAt >= 'a' && charAt <= 'z') {
      return (char) (charAt - 'a' + 'A');
    }
    return charAt;
  }

}
