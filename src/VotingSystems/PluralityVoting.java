package VotingSystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import VoterPopulation.BasicAgent;
import VoterPopulation.VoterList;

public class PluralityVoting implements IVotingSystem {
  private final VoterList votes;
  private Map<String, Integer> pluralityResult = new LinkedHashMap<>(); //might not be good choice
  private List<String> winner = new ArrayList<>();
  private final Map<BasicAgent, Double> resultList = new LinkedHashMap<>();

  public PluralityVoting(VoterList votes) {
    this.votes = votes;
    this.calculateResult();
  }

  public PluralityVoting() {
    this.votes = new VoterList();
    this.calculateResult();
  }

  //needs to grab the first candidate of each agent, and tally them up, returning that tally to the
  //pluralityResult list
  private final void calculateResult() {
    //put all the candidates into the pluralityResult list, then for each basic agent, get their
    //top utility and add it to the appropriate result in plurality list

    for (String cand : this.votes.getCandidateList()) {
      this.pluralityResult.put(cand, 0);
    }

    for (BasicAgent b : this.votes.getAgentList()) {
      //grab first entry in hashmap
     // Optional<String> firstKey = b.getRanking().keySet().stream().findFirst();


      Map.Entry<String,Double> entry = b.getRanking().entrySet().iterator().next();
      String chosen = entry.getKey();


      //adds value to plurality result map
      this.pluralityResult.put(chosen, this.pluralityResult.get(chosen) + 1);

    }

    Map.Entry<String, Integer> maxEntry = null;

    for (Map.Entry<String, Integer> entry : this.pluralityResult.entrySet())
    {
      if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
      {
        maxEntry = entry;
      }
    }

    this.winner.add(maxEntry.getKey());

    for (BasicAgent b : this.votes.getAgentList()) {
      this.resultList.put(b, b.getRanking().get(this.winner.get(0)));
    }


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

    //make a new map so you can grab min each time and remove it so that the new min will be the
    // next closest one
    Map<BasicAgent, Double> copyMap = new HashMap<>();
    for (Map.Entry<BasicAgent, Double> entry : this.resultList.entrySet()) {
      copyMap.put(entry.getKey(),
              entry.getValue());
    }

    for (int i = 0; i < bottomPercentSize; i++) {
      Map.Entry<BasicAgent, Double> min = null;
      for (Map.Entry<BasicAgent, Double> entry : copyMap.entrySet()) {
        if (min == null || min.getValue() > entry.getValue()) {
          min = entry;
        }
      }

      rawlsUtility += copyMap.remove(min.getKey());;

    }
    rawlsUtility = rawlsUtility / bottomPercentSize;


    return (weight * avgUtility) + ((1 - weight) * rawlsUtility)  ;
  }
}
