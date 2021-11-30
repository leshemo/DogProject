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

    super.findWinnerAndAddEachUtil(pluralityResult);

  }

}
