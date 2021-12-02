package VotingSystems;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import VoterPopulation.IVoterList;
import VoterPopulation.VoterList;

import static org.junit.Assert.*;

public class ApprovalVotingTest {
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
    s1 = new ApprovalVoting(0.5);
    List<String> expected = Arrays.asList("A");

    assertEquals(s1.getVoteRanking(), expected);
  }

  @Test
  public void testUtility() {
    s1 = new ApprovalVoting(0.5);
    Double expected = 0.3;

   // assertEquals(s1.getUtility(.5), expected, .001);
  }

}