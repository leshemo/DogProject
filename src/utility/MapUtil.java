package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class MapUtil {

  private MapUtil() {

  }

  //Makes the given ranking map ordered from high to low
  public static Map<String, Double> orderRanking(Map<String, Double> given) {
    List<Map.Entry<String, Double>> list = new ArrayList<>(given.entrySet());
    list.sort(Map.Entry.comparingByValue());
    Collections.reverse(list);

    Map<String, Double> result = new LinkedHashMap<>();
    for (Map.Entry<String, Double> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }

    return result;
  }

  public static Map<String, Integer> orderIntMap(Map<String, Integer> given) {
    List<Map.Entry<String, Integer>> list = new ArrayList<>(given.entrySet());
    list.sort(Map.Entry.comparingByValue());
    Collections.reverse(list);

    Map<String, Integer> result = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }

    return result;
  }

}
