package VotingSystems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import VoterPopulation.BasicAgent;
import VoterPopulation.VoterList;

public class BordaVoting extends AbstractVotingSystem {

  public BordaVoting(VoterList voters) {
    super(voters);
    this.calculateResult();
  }

  public BordaVoting() {
    super();
    this.calculateResult();
  }

  //Steps:
  //a lot like Plurality, except values for all candidates are changed, not just the tally of the
  //top candidate.
  //Questions:
  //Should Dowdall be a variation of this? i.e. something specified in the constructor, or a whole
  //new voting system?
  private void calculateResult() {
    Map<String, Integer> bordaResult = new LinkedHashMap<>();

    for (String cand : super.votes.getCandidateList()) {
      bordaResult.put(cand, 0);
    }

    for (BasicAgent b : super.votes.getAgentList()) {
      List<String> keys = new ArrayList<>(b.getRanking().keySet());
      for (int i = 0; i < b.getRanking().size(); i++) {

        String chosen = keys.get(i);

        //adds value to borda result map
        bordaResult.put(chosen, bordaResult.get(chosen) + bordaResult.size() - (i+ 1));
      }
    }

    super.findWinnerAndAddEachUtil(bordaResult);



  }
}
