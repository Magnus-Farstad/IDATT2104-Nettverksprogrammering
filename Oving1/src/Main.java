public class Main {
    public static void main(String[] args) {
        int start = 1;
        int end = 100;
        int nrOfThreads = 3;

        MyThreads myThreads = new MyThreads();
        myThreads.createMyThreads(nrOfThreads, start, end);
        myThreads.startAllThreads();


        try {
            myThreads.combineAllThreads(start, end);
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        myThreads.threads.get(myThreads.threads.size() - 1).printArrayWithoutZeros();
        //Arrays.stream(thread2.unsortedNumbers).forEach(System.out::println);
    }
}