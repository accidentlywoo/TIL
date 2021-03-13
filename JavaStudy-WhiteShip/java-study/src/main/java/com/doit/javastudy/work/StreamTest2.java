package com.doit.javastudy.work;

import java.util.Arrays;
import java.util.List;

public class StreamTest2 {
	class Member{
		private String name;
		private int penaltyCount;
		private String nickName;

		public Member(String name, int penaltyCount, String nickName) {
			this.name = name;
			this.penaltyCount = penaltyCount;
			this.nickName = nickName;
		}

		public int getPenaltyCount() {
			return penaltyCount;
		}
	}
	public static void main(String[] args) {
		StreamTest2 outterClass = new StreamTest2();
		List<StreamTest2.Member> members = Arrays.asList(
				outterClass.new Member("기영이", 2, "바트심슨"),
				outterClass.new Member("기철이", 5, "불꽃드립"),
				outterClass.new Member("오덕", 0, "자라나라머리머"),
				outterClass.new Member("이말룡", 6, "머선129")
		);
//		int sum = members.stream().mapToInt(e -> e.penaltyCount).sum();
		int sum = members.stream().mapToInt(Member::getPenaltyCount).sum();
		System.out.println(sum);
	}
}
