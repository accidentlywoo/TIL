import java.util.ArrayList;
import java.util.List;

/*
	1. Linked List에대해 공부하세요잉
	2. 정수를 저장하는 ListNode 클래스를 구현하라
	3. ListNode add(ListNode head, ListNode nodeToAdd, int position)
	4. ListNode remove(ListNode head, int positionToRemove)
	5. boolean contains(ListNode head, ListNode nodeTocheck)
 */

/**
 * Doble Linked List
 */
public class ListNode {
	private int size;

	public ListNode(){ size = 0; }

	private int[] getNode(){
		return new int[3];
	}

	/**
	 * add end of list
	 * @param data
	 * @return
	 */
	public ListNode add(int data){
		int[] node = getNode();
		if(listLen-1 > 0) {
			node[0] = list.get(listLen-1)[2];
			node[2] = list.get(listLen-1)[2] ++;
		}
		else {
			node[0] = 1;
			node[2] = 0;
		}
		node[1] = data;
		list.add(node);
		listLen++;
		return this;
	}

	/**
	 *
	 * @param head -> position
	 * @param data
	 * @param position == 0 ? front : end
	 * @return
	 */
	public ListNode add(int head, int data, int position){
		for(int i = 0; i < listLen; i++) {
			int[] ints = getNode();
			if(list.get(i)[0] == head){
				ints[0] = list.get(i)[0];
				ints[2] = list.get(i)[2];
				for (int j = head+position+1; j < listLen; j++ ){
					list.get(j)[0] += 1;
					list.get(j)[2] += 1;
				}
			}else{
				ints[0] = list.get(listLen-1)[0];
				ints[2] = list.get(listLen-1)[2];
			}

			ints[1] = data;
			listLen++;
			list.add(ints);
		}

		return this;
	}

	public ListNode remove(int head, int data, int position){

		return this;
	}

	public boolean contains(ListNode head, ListNode nodeTocheck){
		return false;
	}
}
