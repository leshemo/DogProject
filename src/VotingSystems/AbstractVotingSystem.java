package VotingSystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import VoterPopulation.BasicAgent;
import VoterPopulation.IVoterList;
import VoterPopulation.VoterList;

import static utility.MapUtil.orderIntMap;
import static utility.MapUtil.orderRanking;

/**
 * Abstract class for a voting system. Meant to avoid code duplication, because the public methods
 * of the IVotingSystem interface stay the same no matter the implementation. The only thing that
 * changes between voting systems is the protected calculateResult method, which actually calculates
 * the results of a vote.
 */
public abstract class AbstractVotingSystem implements IVotingSystem {
  protected final IVoterList votes;
  protected List<String> winner = new ArrayList<>();
  protected final Map<BasicAgent, Double> resultList = new LinkedHashMap<>();


  public AbstractVotingSystem(IVoterList votes) {
    this.votes = votes;
  }

  public AbstractVotingSystem() {
    this.votes = new VoterList();

  }

  protected void findWinnerAndAddEachUtil(Map<String, Integer> givenList) {
    //TODO: change to putting entire ranking into winner
    Map<String, Integer> copyMap = orderIntMap(givenList);

    for (String key : copyMap.keySet()) {
      this.winner.add(key);
    }

//    Map.Entry<String, Integer> maxEntry = null;
//
//    for (Map.Entry<String, Integer> entry : givenList.entrySet()) {
//      if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
//        maxEntry = entry;
//      }
//    }
//
//    this.winner.add(maxEntry.getKey());

    for (BasicAgent b : this.votes.getAgentList()) {
      //b.getRanking().get(this.winner.get(0));
      this.resultList.put(b, this.calculateOReilly(b));
    }

  }

  //calculates the O'Reilly score for a particular agent
  //for the entire ranking of agent
  protected double calculateOReilly(BasicAgent b) {
    double oreillyScore = 0.0;
    //for each candidate in ranking, get utility value and subtract from it each of the lower other
    //candidate utilities.
    //TODO: DEBUG, something is wrong here with the iteration
    for (int curCand = 0; curCand < this.winner.size(); curCand++) {
      for (int otherCand = curCand + 1; otherCand < this.winner.size(); otherCand++) {
        oreillyScore += b.getRanking().get(this.winner.get(curCand)) - b.getRanking().get(this.winner.get(otherCand));
      }

    }
    return oreillyScore;

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

  //TODO: change to be based on oreilly, and change so that it returns List<avgUtility, rawlsUtility, computedUtility>
  //TODO: avgUtility - all the oreilly utilities divided by population
  //TODO: rawlsUtility - avgUtility of only the bottom 20 percent unsatisfied
  //TODO: computedUtility - computed utility based on avgUtility, rawsUtility, and the given weight
  @Override
  public List<Double> getUtility(Double weight) {
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

    double computedUtility =  (weight * avgUtility) + ((1 - weight) * rawlsUtility);

    return null;
  }

  //TODO: change to be based on O'Reilly
  @Override
  public double getProductUtility() {
    List<Double> utilList = new ArrayList<>(this.resultList.values());
    double product = 1;
    for (int i = 0; i < utilList.size(); i++) {
      product = product * utilList.get(i);
    }
    return product;
  }

  //TODO: IS THIS NECESSARY? Could we just change the above getUtility and getProductUtility?

  //TODO:
  @Override
  public List<Double> getOnlyWinnerUtility() {
    //for every agent, do the relationship thingy for all candidates

    return null;
  }

  //TODO:
  @Override
  public double getOnlyWinnerProductUtility() {
    return 0.0;
  }
}
