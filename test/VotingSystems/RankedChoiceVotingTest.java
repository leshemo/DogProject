package VotingSystems;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import VoterPopulation.IVoterList;
import VoterPopulation.VoterList;

import static org.junit.Assert.*;

public class RankedChoiceVotingTest {

  IVoterList v1;
  IVoterList v2;
  IVoterList v3;

  List<String> cand1;
  List<String> cand2;


  IVotingSystem s1;
  IVotingSystem s2;
  IVotingSystem s3;





  @Before
  public void setUp() throws Exception {


    v1 = new VoterList();


  }

  @Test
  public void testCalculate() {
    v1 = new VoterList(1000000, Arrays.asList("A", "B", "C", "D"), 0.5);
    s1 = new RankedChoiceVoting(v1);
    List<String> expected = Arrays.asList("A");

    assertEquals(s1.getVoteRanking(), expected);
  }

  @Test
  public void testUtility() {
    s1 = new RankedChoiceVoting();
    Double expected = 0.3;

    assertEquals(s1.getUtility(.5), expected, .001);
  }

}