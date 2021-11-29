package VotingSystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import VoterPopulation.BasicAgent;
import VoterPopulation.VoterList;

/**
 * Abstract class for a voting system. Meant to avoid code duplication, because the public methods
 * of the IVotingSystem interface stay the same no matter the implementation. The only thing that
 * changes between voting systems is the protected calculateResult method, which actually calculates
 * the results of a vote.
 */
public abstract class AbstractVotingSystem implements IVotingSystem {
  protected final VoterList votes;
  protected List<String> winner = new ArrayList<>();
  protected final Map<BasicAgent, Double> resultList = new LinkedHashMap<>();

  public AbstractVotingSystem(VoterList votes) {
    this.votes = votes;
  }

  public AbstractVotingSystem() {
    this.votes = new VoterList();

  }


  @Override
  public Map<BasicAgent, Double> getAgentResult() {
    Map<BasicAgent, Double> copyMap = new HashMap<>();

    for (Map.Entry<BasicAgent, Double> entry : this.resultList.entrySet()) {
      copyMap.put(entry.getKey(),
              entry.getValue());
    }
    return copyMap;
  }

  @Override
  public List<String> getVoteRanking() {
    List<String> copyWinner = new ArrayList<>(this.winner);

    return copyWinner;
  }

  @Override
  public double getUtility(Double weight) {
    Double avgUtility = 0.0;
    for (Map.Entry<BasicAgent, Double> entry : this.resultList.entrySet()) {
      avgUtility += entry.getValue();
    }
    avgUtility = avgUtility / this.resultList.size();

    Double rawlsUtility = 0.0;
    //need to get the 20% lowest utilities and average them for rawlsian utility
    Double bottomPercentSize = this.resultList.size() * 0.2;

    //make a new map so that you can grab min each time and remove it so that the new min will be the
    // next closest one
    List<Double> valuesOnly = new ArrayList<>(this.resultList.values());
    Collections.sort(valuesOnly);
    for (int i = 0; i < bottomPercentSize; i++) {
      rawlsUtility += valuesOnly.get(i);
    }

    rawlsUtility = rawlsUtility / bottomPercentSize;

    return (weight * avgUtility) + ((1 - weight) * rawlsUtility);
  }
}