package com.doit.javastudy.algorithm.B1215Level2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DfsGraphTest {
	private DfsGraph dfsGraph;

	@BeforeEach
	public void 선작업(){
		dfsGraph = new DfsGraph(8);
		dfsGraph.put(1, 2);
		dfsGraph.put(1, 3);
		dfsGraph.put(2, 4);
		dfsGraph.put(2, 5);
		dfsGraph.put(3, 6);
		dfsGraph.put(4, 8);
		dfsGraph.put(5, 8);
		dfsGraph.put(6, 8);
		dfsGraph.put(7, 8);
	}


	@Test
	public void 장점_1부터_탐색(){
	    // 입력한 정점괴 긴산으로 구성된 인접행렬 출력
		dfsGraph.printGraphToAdjArr();
	    // when
		dfsGraph.dfs(1);
	    // then
	}

}