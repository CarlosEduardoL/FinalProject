/**
 * 
 */
package graphTest;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import graphTAD.Graph;

/**
 * @author Nelson Lopez
 *
 */
public class GraphTest {
	
	private Graph graph;
	@SuppressWarnings("rawtypes")
	void setupStage1() {
		graph = new Graph(false);
	}
	

	@Test
	void testStandardDeviation() {
		setupStage1();
		
		try {

			
			System.out.println(game.getMatches().size());
			for (int i = 0; i <game.getMatches().size(); i++) {
				System.out.println(game.getMatches().get(i).getFirst());
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

