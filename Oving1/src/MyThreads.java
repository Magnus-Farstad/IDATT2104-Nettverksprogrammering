import java.util.ArrayList;

public class MyThreads {
    ArrayList<MyThread> threads;
    int[] indexes;

    public MyThreads() {
        threads = new ArrayList<>();
    }

    public void createMyThreads(int nrOfThreads, int start, int end) {
        int[] indexes = splitWorkload(start, end, nrOfThreads);
        for(int i = 0; i < nrOfThreads * 2; i++) {
            threads.add(new MyThread(indexes[i], indexes[i + 1]));
            i++;
        }
    }

    public void startAllThreads() {
        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
    }

    private int[] splitWorkload(int start, int end, int nrOfThreads) {
        int splitValue = end / nrOfThreads;
        indexes = new int[nrOfThreads * 2];
        indexes[0] = start;
        indexes[1] = splitValue - 1;
        for(int i = 2; i < indexes.length; i++) {
            indexes[i] = indexes[i - 1] + 1;
            indexes[i + 1] = indexes[i] + splitValue;
            i++;
        }
        indexes[indexes.length - 1] = end;
        return indexes;
    }

    public void combineAllThreads(int start, int end) throws InterruptedException {
        for(int j = 0; j < threads.size(); j++) {
            threads.get(j).join();
        }

        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).printArrayWithoutZeros();
        }

        /*
        int slicePoint = (start + end) / threads.size();
        for(int i = start; i <= slicePoint - 2; i++) {
            if(threads.get(0).unsortedNumbers[i] != 0) {
                threads.get(1).unsortedNumbers[i] = threads.get(0).unsortedNumbers[i];
            }
        }

         */
    }
}
