import java.util.Arrays;

class ArraySlice {
    public int[] solution(int[] array, int[][] commands) {
        int len = commands.length;
        
        int[] answer = new int[len];
        for(int i = 0; i < len; i++){
            int[] copy = Arrays.copyOfRange(array, commands[i][0] -1, commands[i][1] );
            Arrays.sort(copy);
            answer[i] = copy[commands[i][2] -1];
        }
        return answer;
    }
}