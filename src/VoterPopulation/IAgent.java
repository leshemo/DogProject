package VoterPopulation;

import java.util.Map;

/**
 * Representing an interface for an agent with utilities and rankings of candidates.
 */
public interface IAgent {

  /**
   * Getter for the ranking of an agent.
   *
   * @return ranking of an agent
   */
  public Map<String, Double> getRanking();


}
