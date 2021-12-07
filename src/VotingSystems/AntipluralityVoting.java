package VotingSystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import VoterPopulation.BasicAgent;
import VoterPopulation.IVoterList;
import VoterPopulation.VoterList;

import static utility.MapUtil.orderIntMap;

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

    List<Map.Entry<String, Integer>> list = new ArrayList<>(antipluralityResult.entrySet());
    list.sort(Map.Entry.comparingByValue());

    Map<String, Integer> result = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }
    for (String key : result.keySet()) {
      super.winner.add(key);
    }

    for (BasicAgent b : super.votes.getAgentList()) {
      super.resultList.put(b, super.calculateOReilly(b));
    }
  }


}
