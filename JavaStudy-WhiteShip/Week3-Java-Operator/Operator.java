public class Operator{
    public static void main(String[] args){
        int start = Integer.MAX_VALUE;
        int end = Integer.MAX_VALUE;
        // int end = 2100000000;
        System.out.println("binary total : " + intToBinaryString(start+end));

        int mid = (start + end) / 2; // 멀티 쓰레드 프로그래밍 중에 스택 오버플로우가 발생할 수 있다
        System.out.println("Stck Over Flow ! : " + mid);
        System.out.println("binary mid : " + intToBinaryString(mid));
        mid = start + (end - start) / 2; // 스택 오버플로우 회피
        System.out.println("Stack Over Flow Evasion : " + mid);
        System.out.println("binary mid : " + intToBinaryString(mid));

        mid = (start + end) >> 1;
        System.out.println("Ssap Gangi fail: " + mid);
        System.out.println("binary mid fail: " + intToBinaryString(mid));

        mid = (start + end) >>> 1;
        System.out.println("Ssap Gangi : " + mid);
        System.out.println("binary mid : " + intToBinaryString(mid));

        // -- bite가 바로 버려지는지 테스트해봄 --
        int start2 = start + 1;
        System.out.println("binary start2 : " + intToBinaryString(start2));
        int end2 = end + 1;

        mid = (start2 + end2) >> 1;
        System.out.println("2 Ssap Gangi : " + mid);
        System.out.println("binary mid : " + intToBinaryString(mid));

        mid = (start2 + end2) >>> 1;
        System.out.println("2 Ssap Gangi : " + mid);
        System.out.println("binary mid : " + intToBinaryString(mid));

        System.out.println("힝구힝구 : "+Integer.toBinaryString(mid)); // 양수 앞자리 짜름 주의
    }

    static String intToBinaryString(int b){
        String builder = "";
        for(int i = 0; i < 32; i ++){
            builder+=((0x80000000 >>> i) & b) == 0 ? '0' : '1';
            if(i != 15 && i%4 == 0)
                builder+='_';
        }
        return builder;
    }
}