package VotingSystems;

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
  }
}
