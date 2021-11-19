package VotingSystems;

import java.util.Map;

import VoterPopulation.BasicAgent;

/**
 * Interface representing voting systems, in which each one creates or takes in a VoterList and runs
 * the voting system on that list, returning results to a resultsList.
 */
public interface IVotingSystem {


  //method to return results, probably as a list of agent utilities of the results
  public Map<BasicAgent, Integer> getVotingResult();

}
