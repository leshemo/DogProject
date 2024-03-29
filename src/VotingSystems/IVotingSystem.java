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


  //to return overall utility for the result of a voting system, where the first value in the list
  //will be the avg utility, then rawlsian utility, then the computed utility.
  List<Double> getUtility(Double weight);


  //returns the product utility for the result of a voting system
  double getProductUtility();


  //returns the O'Reilly Score for each agent in a result of a voting system
  List<Double> getOnlyWinnerUtility();

  //returns the product of O'Reilly scores for all agents
  double getOnlyWinnerProductUtility();

}
