package VotingSystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import VoterPopulation.BasicAgent;
import VoterPopulation.VoterList;

public class PluralityVoting extends AbstractVotingSystem {
//  private List<String> winner = new ArrayList<>();
//  private final Map<BasicAgent, Double> resultList = new LinkedHashMap<>();

  public PluralityVoting(VoterList votes) {
    super(votes);
    this.calculateResult();

  }

  public PluralityVoting() {
    super();
    this.calculateResult();

  }

  //needs to grab the first candidate of each agent, and tally them up, returning that tally to the
  //pluralityResult list
  public final void calculateResult() {
    Map<String, Integer> pluralityResult = new LinkedHashMap<>();
    //put all the candidates into the pluralityResult list, then for each basic agent, get their
    //top utility and add it to the appropriate result in plurality list

    for (String cand : super.votes.getCandidateList()) {
      pluralityResult.put(cand, 0);
    }

    for (BasicAgent b : super.votes.getAgentList()) {
      //grab first entry in hashmap
      // Optional<String> firstKey = b.getRanking().keySet().stream().findFirst();


      Map.Entry<String, Double> entry = b.getRanking().entrySet().iterator().next();
      String chosen = entry.getKey();


      //adds value to plurality result map
      pluralityResult.put(chosen, pluralityResult.get(chosen) + 1);

    }

    Map.Entry<String, Integer> maxEntry = null;

    for (Map.Entry<String, Integer> entry : pluralityResult.entrySet()) {
      if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
        maxEntry = entry;
      }
    }

    super.winner.add(maxEntry.getKey());

    for (BasicAgent b : super.votes.getAgentList()) {
      super.resultList.put(b, b.getRanking().get(super.winner.get(0)));
    }


  }
//
//  @Override
//  public Map<BasicAgent, Double> getAgentResult() {
//    Map<BasicAgent, Double> copyMap = new HashMap<>();
//
//    for (Map.Entry<BasicAgent, Double> entry : this.resultList.entrySet()) {
//      copyMap.put(entry.getKey(),
//              entry.getValue());
//    }
//    return copyMap;
//  }
//
//  @Override
//  public List<String> getVoteRanking() {
//    List<String> copyWinner = new ArrayList<>(this.winner);
//
//    return copyWinner;
//  }
//
//
//  @Override
//  public double getUtility(Double weight) {
//    Double avgUtility = 0.0;
//    for (Map.Entry<BasicAgent, Double> entry : this.resultList.entrySet()) {
//      avgUtility += entry.getValue();
//    }
//    avgUtility = avgUtility / this.resultList.size();
//
//    Double rawlsUtility = 0.0;
//    //need to get the 20% lowest utilities and average them for rawlsian utility
//    Double bottomPercentSize = this.resultList.size() * 0.2;
//
//    //make a new map so that you can grab min each time and remove it so that the new min will be the
//    // next closest one
//    List<Double> values = new ArrayList<>(this.resultList.values());
//    Collections.sort(values);
//    for (int i = 0; i < bottomPercentSize; i++) {
//      rawlsUtility += values.get(i);
//    }
//
//    rawlsUtility = rawlsUtility / bottomPercentSize;
//
//    return  (weight * avgUtility) + ((1 - weight) * rawlsUtility);
//  }
}
