package VotingSystems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import VoterPopulation.BasicAgent;
import VoterPopulation.VoterList;

public class PluralityVoting implements IVotingSystem {
  private final VoterList votes;
  private Map<String, Integer> pluralityResult = new HashMap<>(); //might not be good choice
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
  //pluralityResult list
  private final void calculateResult() {
    //put all the candidates into the pluralityResult list, then for each basic agent, get their
    //top utility and add it to the appropriate result in plurality list

    for (String cand : this.votes.getCandidateList()) {
      this.pluralityResult.put(cand, 0);
    }

    for (BasicAgent b : this.votes.getAgentList()) {
      //grab first entry in hashmap
      String chosen = b.getRanking().get("FIRST");
      for (int i = 0; i < this.pluralityResult.size(); i++) {
        if (chosen.equals(this.pluralityResult.get(i))) {  //same thing as above fix
         this.pluralityResult.get()
        }
        this.pluralityResult.get(chosen) =+ 1;
      }


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
