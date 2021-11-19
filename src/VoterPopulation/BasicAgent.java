package VoterPopulation;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a basic agent, which only has utilities for specific rankings.
 * The number of candidates will be created elsewhere (probs a controller of some kind), and
 * a unit simplex class or library will determine the utilities for each candidate.
 */
public class BasicAgent implements IAgent {
  private final Map<String, Double> rankingMap;

  /**
   * Default constructor
   * @param rankingMap String is the candidate, Integer is the utility of the agent for that candidate.
   */
  public BasicAgent(Map<String, Double> rankingMap) {
    this.rankingMap = rankingMap;
  }

  @Override
  public Map<String, Double> getRanking() {
    Map<String, Double> copyMap = new HashMap<>();

    for (Map.Entry<String, Double> entry : this.rankingMap.entrySet()) {
      copyMap.put(entry.getKey(),
              entry.getValue());
    }
    return copyMap;
  }
}
