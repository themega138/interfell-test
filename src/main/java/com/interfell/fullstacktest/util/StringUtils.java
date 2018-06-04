package com.interfell.fullstacktest.util;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

  private StringUtils() {

  }

  public static String getDelimitedString(List<String> source, String delimiter) {
    if(source == null) {
      return null;
    }

    return source.stream().collect(Collectors.joining(delimiter));
  }

}
