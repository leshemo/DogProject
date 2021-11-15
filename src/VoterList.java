import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Voter List, and creating a voter List will create the population of agents, each
 * with their own utility values for each of the candidates, so that the voting systems can be run.
 */
public class VoterList implements IVoterList{
  private final int population;
  private final List<String> candidates;
  private final List<BasicAgent> agentList;

  /**
   * Default constructor for a voter list.
   * @param population given population of agents
   * @param candidates given candidates
   */
  public VoterList(int population, List<String> candidates) {
    this.population = population;
    this.candidates = candidates;
    this.agentList = new ArrayList<>();
  }

  //method for simplex shit
  



  //to create each agent
  private void initAgentList() {

  }


  @Override
  public List<BasicAgent> getAgentList() {
    return null;
  }
}
