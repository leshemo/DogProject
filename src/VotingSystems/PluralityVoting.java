package VotingSystems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import VoterPopulation.BasicAgent;
import VoterPopulation.VoterList;

public class PluralityVoting implements IVotingSystem {
  private final VoterList votes;
  private final Map<String, Double> resultList = new HashMap<>();

  public PluralityVoting(VoterList votes) {
    this.votes = votes;
    this.calculateResult();
  }

  public PluralityVoting() {
    this.votes = new VoterList();
    this.calculateResult();
  }

  //needs to grab the first candidate of each agent, and tally them up, returning that tally to the
  //resultList
  private final void calculateResult() {

    for (BasicAgent b : this.votes.getAgentList()) {


    }
  }

  @Override
  public Map<BasicAgent, Double> getAgentResult() {
    return null;
  }

  @Override
  public List<String> getVoteRanking() {
    return null;
  }
}
