import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a basic agent, which only has utilities for specific rankings.
 * The number of candidates will be created elsewhere (probs a controller of some kind), and
 * a unit simplex class or library will determine the utilities for each candidate.
 */
public class BasicAgent implements IAgent {
  private final Map<String, Integer> rankingMap;

  public BasicAgent(Map<String, Integer> rankingMap) {
    this.rankingMap = rankingMap;
  }

  @Override
  public Map<String, Integer> getRanking() {
    Map<String, Integer> copyMap = new HashMap<>();

    for (Map.Entry<String, Integer> entry : this.rankingMap.entrySet()) {
      copyMap.put(entry.getKey(),
              entry.getValue());
    }
    return copyMap;
  }
}
