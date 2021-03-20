package com.doit.javastudy.work;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//		int sum = members.stream().map(member -> member.getPenaltyCount()).reduce(0, (a, b) -> a + b);
//		int sum = members.stream().map(Member::getPenaltyCount).reduce(0,  Integer::sum);
//		int sum = members.stream().mapToInt(Member::getPenaltyCount).sum();

//		int sum = members.stream().collect(Collectors.summingInt(Member::getPenaltyCount));
		int sum;
		Stream<Member> stream = members.stream();
//		stream.forEach(s -> outterClass.sum += s.getPenaltyCount());
//		System.out.println("기영이네 페널티 합은 " + outterClass.sum);
//		members.stream().map(c->c.getPenaltyCount()).forEach(s->System.out.println("기영이네 패널티의 합은 : "+s));
	}
}
