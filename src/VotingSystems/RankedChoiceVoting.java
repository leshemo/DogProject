package VotingSystems;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


import VoterPopulation.BasicAgent;
import VoterPopulation.IVoterList;
import VoterPopulation.VoterList;

public class RankedChoiceVoting extends AbstractVotingSystem {

  public RankedChoiceVoting(IVoterList votes) {
    super(votes);
    this.calculateResult();

  }

  public RankedChoiceVoting() {
    super();
    this.calculateResult();

  }

  //steps:
  //figure out percentages for each candidate
  //determine whether the top percentage is more than 50
  //      if yes, declare winner
  //      if no, eliminate lowest percentage and re-distribute votes (i.e. if people whose first
  //             choice was eliminated, their second choice would be used
  //repeat process
  //Questions:
  //    there's a sentence "Voters can choose to rank only some of the candidates, or even just one candidate"
  //      should we account for this? If we do, what happens when a person's only candidate is eliminated?
  private void calculateResult() {
    Map<String, Integer> rankedChoiceResult = new LinkedHashMap<>();

    for (String cand : super.votes.getCandidateList()) {
      rankedChoiceResult.put(cand, 0);
    }

   this.helpAssignAgentValues(rankedChoiceResult);

    while (!this.checkIfWon(rankedChoiceResult)) {
      //remove candidate with lowest value
      int min = Integer.MAX_VALUE;
      String toRemove = "";
      for(Map.Entry<String, Integer> entry : rankedChoiceResult.entrySet()) {
        if(entry.getValue() < min) {
          min = entry.getValue();
          toRemove = entry.getKey();
        }

      }
      rankedChoiceResult.remove(toRemove);
      rankedChoiceResult.replaceAll((String, Integer) -> 0);

      for (BasicAgent b : super.votes.getAgentList()) {

        Set<String> keys = b.getRanking().keySet();

        Optional<String> result =
                keys.stream().filter(obj -> rankedChoiceResult.containsKey(obj)).findFirst();
        rankedChoiceResult.put(result.get(), rankedChoiceResult.get(result.get()) + 1);
//        for (String k : keys) {
//          if (rankedChoiceResult.containsKey(k)) {
//            rankedChoiceResult.put(k, rankedChoiceResult.get(k) + 1);
//            break;
//          }
//        }
      }


    }

    super.findWinnerAndAddEachUtil(rankedChoiceResult);



  }

  //checks to see if any candidates have more than 50% of the votes
  private boolean checkIfWon(Map<String, Integer> map) {
    boolean raceWon = false;

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      double percentage = ((double) entry.getValue()) / super.votes.getAgentList().size();
      if (percentage > 0.5) {
        raceWon = true;
      }
    }
    return raceWon;
  }

  //tallies up votes of each agent, depending on whether the candidate has been eliminated.
  private void helpAssignAgentValues(Map<String, Integer> map) {
    for (BasicAgent b : super.votes.getAgentList()) {

      Map.Entry<String, Double> entry = b.getRanking().entrySet().iterator().next();
      String chosen = entry.getKey();



      if (map.containsKey(chosen)) {
          map.put(chosen, map.get(chosen) + 1);
        }



      }
    }
  }


