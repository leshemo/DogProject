package VotingSystems;

import VoterPopulation.VoterList;

public class ApprovalVoting extends AbstractVotingSystem {

  public ApprovalVoting(VoterList voters) {
    super(voters);
    this.calculateResults();
  }

  public ApprovalVoting() {
    super();
    this.calculateResults();
  }

  //Steps:
  //If a utility meets a certain threshold, the agent is approving of that candidate
  //Add to a tally if an agent approves of a candidate
  //find candidate with highest tally and go from there.
  private void calculateResults() {
  }


}
