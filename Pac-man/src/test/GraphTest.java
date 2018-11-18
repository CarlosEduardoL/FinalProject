package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import graphTAD.Graph;

class GraphTest {
	
	Graph<Integer, Integer> graph;

	@BeforeEach
	void setUp() {
		graph = new Graph<>(false);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
