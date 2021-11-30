package VotingSystems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import VoterPopulation.BasicAgent;
import VoterPopulation.IVoterList;
import VoterPopulation.VoterList;

public class ApprovalVoting extends AbstractVotingSystem {
  private final double approvalThreshold;

  public ApprovalVoting(IVoterList voters, Double approvalThreshold) {
    super(voters);
    if (approvalThreshold < 0.0 || approvalThreshold > 1.0) {
      throw new IllegalArgumentException("approval threshold is invalid.");
    }
    this.approvalThreshold = approvalThreshold;
    this.calculateResults();
  }

  public ApprovalVoting(Double approvalThreshold) {
    super();
    if (approvalThreshold < 0.0 || approvalThreshold > 1.0) {
      throw new IllegalArgumentException("approval threshold is invalid.");
    }
    this.approvalThreshold = approvalThreshold;
    this.calculateResults();
  }

  //Steps:
  //If a utility meets a certain threshold, the agent is approving of that candidate
  //Add to a tally if an agent approves of a candidate
  //find candidate with highest tally and go from there.
  private void calculateResults() {
    Map<String, Integer> approvalResult = new LinkedHashMap<>();

    for (String cand : super.votes.getCandidateList()) {
      approvalResult.put(cand, 0);

    }

    for (BasicAgent b : super.votes.getAgentList()) {

      List<String> keys = new ArrayList<>(b.getRanking().keySet());
      for (int i = 0; i < b.getRanking().size(); i++) {

        String chosen = keys.get(i);

        //checks to see if the utility is greater than the threshold.
        if (b.getRanking().get(chosen) > this.approvalThreshold) {
          approvalResult.put(chosen, approvalResult.get(chosen) + 1);

        }
      }
    }

      super.findWinnerAndAddEachUtil(approvalResult);

  }
}
