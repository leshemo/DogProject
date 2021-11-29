package VotingSystems;

import VoterPopulation.VoterList;

public class AntipluralityVoting extends AbstractVotingSystem {

  public AntipluralityVoting(VoterList voters) {
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
  }


}
