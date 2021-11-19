package VoterPopulation;

import java.util.List;

/**
 * Interface for a voter list, where an instance of the class would have the fields: number of Candidates, possibleDiscrimination, etc.
 */
public interface IVoterList {

  //method that returns the list
  public List<BasicAgent> getAgentList();
}
