package VotingSystems;

import java.util.LinkedHashMap;
import java.util.Map;

import VoterPopulation.BasicAgent;
import VoterPopulation.IVoterList;
import VoterPopulation.VoterList;

public class AntipluralityVoting extends AbstractVotingSystem {

  public AntipluralityVoting(IVoterList voters) {
    super(voters);
    this.calculateResult();
  }

  public AntipluralityVoting() {
    super();
    this.calculateResult();
  }


  //Steps:
  //Grab lowest utility of agent's ranking and add it to the tally exactly like plurality
  //this time instead of grabbing the highest tally, grab the lowest
  private void calculateResult() {
    Map<String, Integer> antipluralityResult = new LinkedHashMap<>();

    for (String cand : super.votes.getCandidateList()) {
      antipluralityResult.put(cand, 0);
    }

    for (BasicAgent b : super.votes.getAgentList()) {
      //grab last entry in hashmap

      int count = 1;

      for (Map.Entry<String, Integer> entry : antipluralityResult.entrySet()) {

        if (count == antipluralityResult.size()) {
          Integer val = entry.getValue();
          entry.setValue(val + 1);
        }

        count++;
      }

    }

    Map.Entry<String, Integer> minEntry = null;

    for (Map.Entry<String, Integer> hash : antipluralityResult.entrySet()) {
      if (minEntry == null || hash.getValue() > hash.getValue()) {
        minEntry = hash;
      }
    }

    super.winner.add(minEntry.getKey());

    for (BasicAgent b : super.votes.getAgentList()) {
      super.resultList.put(b, b.getRanking().get(super.winner.get(0)));
    }
  }


}
