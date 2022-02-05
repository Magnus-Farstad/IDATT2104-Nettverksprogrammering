public class MyThread extends Thread{
    //public int amount = 0;
    private final int start;
    private final int end;
    public int[] unsortedNumbers;

    public MyThread(int start, int end) {
        this.start = start;
        this.end = end;
        this.unsortedNumbers = new int[end];
    }

    public void run() {
        int count;
        for(int i = start; i <= end; i++) {

            count = 0;
            for(int j = 1; j <= i; j++) {

                if(i % j == 0)
                    count = count+1;
            }
            if(count == 2) {
                this.unsortedNumbers[i] = i;
            }

        }
    }

    public void printArrayWithoutZeros() {
        for(int i = 0; i < unsortedNumbers.length; i++) {
            if(unsortedNumbers[i] != 0) System.out.println(i);
        }
    }
}
