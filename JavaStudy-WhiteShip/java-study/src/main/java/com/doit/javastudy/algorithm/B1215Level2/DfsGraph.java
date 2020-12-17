package com.doit.javastudy.algorithm.B1215Level2;

public class DfsGraph {
	private int nV; // 정점의 개수
	private int[][] dfsGraph; // 그래프
	private boolean[] visitArr; // 정점 방문 여부 확인 배열

	public DfsGraph(int nV){
		this.nV = nV;
		/* 그래프 초기화
		*   put(int x, int y) 에서 입력되는 정점의 값은 0 이상의 정수이나
		*   배열의 index는 0부터 시작이므로
		*   ArrayIndexOutOfBoundsException 방지를 위해
		*   정점을 담는 인접행렬의 행과 열 size는 1을 더하여 초기화해줌 */
		this.dfsGraph = new int[this.nV+1][this.nV+1];

		/*  정점 방문 여부 확인 배열 초기화
		 * 그래프와 마찬가지로 정점의 개수에 +1하여 초기화 */
		this.visitArr = new boolean[this.nV+1];
	}
	public int[][] getGraph(){
		return this.dfsGraph;
	}
	// 그래프 추가 (양방향)
	public void put(int x, int y){
		// 정점 x와 y가 연결되어있음을 의미
		this.dfsGraph[x][y] = this.dfsGraph[y][x] = 1;
	}
	// 그래프 추가 (단방향)
	public void putSingle(int x, int y){
		this.dfsGraph[x][y] = 1;
	}
	// 그래프 출력 (인접행렬)
	public void printGraphToAdjArr(){
		for(int i = 0; i < this.dfsGraph.length; i++){
			for(int j = 0; j < this.dfsGraph[i].length; j++){
				System.out.println(this.dfsGraph[i][j]);
			}
			System.out.println();
		}
	}

	// 그래프 탐색 (재귀호출)
	public void dfs(int vIdx){
		/* dfs()에 들어온 vidx는 방문한 것이므로
		 * 방문배열의 해당 index값을 true로 바꿔주고 값을 출력함 */
		this.visitArr[vIdx] = true;
		System.out.println(vIdx);

		/* 인접 행렬로 구현된 그래프에서 정점의 개수(nV) 만큼 탐색 */
		for(int i =1; i < this.nV; i++){

			/* dfsGraph[][]의 해당 정점이 연결되어있는 것으로 표시되어 있으나
				(연결은 1로 표시) 방문 배열에서 방문하지 않은 상태( false)인 경우  */
			if(dfsGraph[vIdx][i] == 1 && visitArr[i] == false){
				dfs(i);
			}
		}
	}
}
