public class PrimeNumberFinder {
    public Thread[] threads;

    private void createThreads(int numberOfThreads) {
        threads = new Thread[numberOfThreads];
    }

    public void findPrimeNumbers(int start, int end, int numberOfThreads) {
        createThreads(numberOfThreads);
        int count;
        for(int i = start; i <= end; i++) {

            count = 0;
            for(int j = 1; j <= i; j++) {

                if(i % j == 0)
                    count = count+1;
            }
            if(count == 2)
                System.out.println(i);
        }
    }

    public int sumNumbers() {
        int sum = 0;
        Thread t1 = new Thread();
        t1.start();
        return sum;
    }

    private int sum() {
        int sum = 0;
        for(int i = 0; i < 20000; i++) {
            sum++;
        }
        return sum;
    }
}
