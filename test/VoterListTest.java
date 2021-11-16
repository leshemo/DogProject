import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for VoterList
 */
public class VoterListTest {

  IVoterList vl1;
  IVoterList vl2;
  IVoterList vl3;
  IVoterList vl4;

  int pop1;
  int pop2;
  int pop3;
  int pop4;

  double w1;
  double w2;

  List<String> cand1;
  List<String> cand2;
  List<String> cand3;
  List<String> cand4;


  @Before
  public void setUp() throws Exception {
    pop1 = 2;
    pop2 = 5;
    pop3 = 10;
    pop4 = 50;

    w1 = 0.2;
    w2 = 0.7;

    cand1 = Arrays.asList("a", "b", "c");
    cand2 = Arrays.asList("a", "b");
    cand3 = Arrays.asList("a", "b", "c", "e", "f", "g");
    cand4 = Arrays.asList("a", "b", "c", "rosalind franklin", "thundercat");

    vl1 = new VoterList(pop1, cand1, w1);
    vl2 = new VoterList(pop2, cand2, w1);
    vl3 = new VoterList(pop3, cand3, w2);
    vl4 = new VoterList(pop4, cand4, w2);


  }

  @Test(expected = IllegalArgumentException.class)
  public void badCandidates() {
    new VoterList(pop1, null, w1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badPop() {
    new VoterList(-1, cand1, w1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badW() {
    new VoterList(pop1, cand1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badCandPop() {
    new VoterList(-1, null, w1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badCandW() {
    new VoterList(pop1, null, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badPopW() {
    new VoterList(-1, cand1, -1);
  }

  @Test
  public void getAgentList() {
    for (int i = 0; i < vl1.getAgentList().size(); i++) {
      for (BasicAgent b : vl1.getAgentList()) {
        double agent1Ranking = b.getRanking().get(cand1.get(0));
        assertTrue(0 <= agent1Ranking && agent1Ranking <= 1);
      }
    }
    assertEquals(2, vl1.getAgentList().size());
  }
}