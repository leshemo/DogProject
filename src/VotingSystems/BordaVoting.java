package VotingSystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import VoterPopulation.BasicAgent;
import VoterPopulation.IVoterList;
import VoterPopulation.VoterList;

import static utility.MapUtil.orderIntMap;
import static utility.MapUtil.orderRanking;

public class BordaVoting extends AbstractVotingSystem {
  private final boolean dowdall;

  public BordaVoting(IVoterList voters, boolean dowdall) {
    super(voters);
    this.dowdall = dowdall;
    this.calculateResult();
  }

  public BordaVoting(boolean dowdall) {
    super();
    this.dowdall = dowdall;
    this.calculateResult();
  }

  //Steps:
  //a lot like Plurality, except values for all candidates are changed, not just the tally of the
  //top candidate.
  //Questions:
  //Should Dowdall be a variation of this? i.e. something specified in the constructor, or a whole
  //new voting system?
  //TODO: Maybe put in a weight for points?
  private void calculateResult() {
    Map<String, Integer> bordaResult = new LinkedHashMap<>();
    Map<String, Double> dowdallResult = new LinkedHashMap<>();

    for (String cand : super.votes.getCandidateList()) {
      bordaResult.put(cand, 0);
      dowdallResult.put(cand,0.0);
    }

    for (BasicAgent b : super.votes.getAgentList()) {
      List<String> keys = new ArrayList<>(b.getRanking().keySet());
      for (int i = 0; i < b.getRanking().size(); i++) {

        String chosen = keys.get(i);

        //adds value to borda result map
        if (this.dowdall) {
          dowdallResult.put(chosen, dowdallResult.get(chosen) + 1 / (i + 1));
        } else {
          bordaResult.put(chosen, bordaResult.get(chosen) + bordaResult.size() - (i+ 1));

        }
      }
    }

    if (!this.dowdall) {
      super.findWinnerAndAddEachUtil(bordaResult);
    }

    else {
      //have to do this because findWinnerAndAddEachUtil only takes in a Map<String, Integer>;
      //inefficient, there must be ways to make better
      //TODO: FIX
//      Map.Entry<String, Double> maxEntry = null;
//
//      for (Map.Entry<String, Double> entry : dowdallResult.entrySet()) {
//        if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
//          maxEntry = entry;
//        }
//      }
//
//      this.winner.add(maxEntry.getKey());

      Map<String, Double> copyMap = orderRanking(dowdallResult);
      List<String> reverseOrderedKeys = new ArrayList<String>(copyMap.keySet());

      Collections.reverse(reverseOrderedKeys);
      for (String key : reverseOrderedKeys) {
        this.winner.add(key);
      }

      for (BasicAgent b : super.votes.getAgentList()) {
        super.resultList.put(b, b.getRanking().get(this.winner.get(0)));
      }
    }




  }
}
