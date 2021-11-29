package VotingSystems;

import VoterPopulation.VoterList;

public class RankedChoiceVoting extends AbstractVotingSystem {

  public RankedChoiceVoting(VoterList votes) {
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


  }
}
