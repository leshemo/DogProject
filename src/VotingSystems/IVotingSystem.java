package VotingSystems;

import java.util.List;
import java.util.Map;

import VoterPopulation.BasicAgent;

/**
 * Interface representing voting systems, in which each one creates or takes in a VoterList and runs
 * the voting system on that list, returning results to a resultsList.
 */
public interface IVotingSystem {


  //method to return agent results, probably as a list of agent utilities of the results
  Map<BasicAgent, Double> getAgentResult();

  //method to return overall ranking of results, whether it be just a single winner (plurality), or
  //an entire ordering of candidates (Ranked)
  List<String> getVoteRanking();


  //to return overall utility for the result of a voting system using the utilitarian or Rawlsian
  //calculation we discussed (need to weight?)
  double getUtility(Double weight);

  double getProductUtility();

}
