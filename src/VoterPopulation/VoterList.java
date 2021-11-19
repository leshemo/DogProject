package VoterPopulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Voter List, and creating a voter List will create the population of agents, each
 * with their own utility values for each of the candidates, so that the voting systems can be run.
 */
public class VoterList implements IVoterList {
  private final int population;
  private final List<String> candidates;
  private final List<BasicAgent> agentList;
  private final double w;

  /**
   * Default constructor for a voter list.
   * @param population given population of agents
   * @param candidates given candidates
   * @param w factor by which to weigh the individual utilities for each candidate
   */
  public VoterList(int population, List<String> candidates, double w) {
    if (candidates == null || population < 0 || w < 0) {
      throw new IllegalArgumentException("VoterPopulation.VoterList candidates, population, or cannot be null or zero.");
    }
    this.population = population;
    this.candidates = candidates;
    this.agentList = new ArrayList<>();
    this.w = w;
    this.initAgentList();
  }

  public VoterList() {
    this.population = 10;
    this.candidates = Arrays.asList("A", "B", "C","D");
    this.agentList = new ArrayList<>();
    this.w = 0.5;
    this.initAgentList();
  }





  //to create all the agents for all the candidates
  private void initAgentList() {


    //runs through the entire population, creating agents
    for (int i = 0; i < this.population; i++) {
      List<Double> listOfXi = new ArrayList<>();
      List<Double> listOfAi = new ArrayList<>();
      Map<String, Double> votes = new HashMap<>();
      double xiSum = 0;

      //calculates r(i), x(i), and, after the for loop is done, the sum of x(i) used for normalization.
      //TODO: check to make sure the Math random and log calculate properly
      for (int j = 0; j < candidates.size(); j++) {
        double ri = Math.random();
        double xi = -Math.log(ri);
        listOfAi.add(ri);
        listOfXi.add(xi);
        xiSum += xi;

      }
      //calculates A(i), and uses the given w to weigh the utility for each candidate.
      //it then populates the agent's rankingMap with the votes, and creates the new Agent in the agent List.
      for (int k = 0; k < candidates.size(); k++) {
        double ai = listOfXi.get(k) / xiSum;
        double ui = Math.floor((listOfAi.get(k) * (1 - this.w) + ai * this.w) * 1000) / 1000;

        votes.put(candidates.get(k), ui);
      }
      this.agentList.add(new BasicAgent(votes));

    }

  }


  @Override
  public List<BasicAgent> getAgentList() {
    List<BasicAgent> copyAgentList = new ArrayList<>(this.agentList);
    return copyAgentList;
  }

  @Override
  public List<String> getCandidateList() {
    List<String> copyCandList = new ArrayList<>(this.candidates);
    return copyCandList;
  }
}
