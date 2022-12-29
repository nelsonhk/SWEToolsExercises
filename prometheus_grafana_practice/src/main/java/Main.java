
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {

    // Range of random values to be inserted and removed from BST
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 300;

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {

        // An example Metric defined
//        Counter numberOfIterations = Counter.build()
//                .namespace("java")
//                .name("number_of_iterations")
//                .help("Counts the number of attempted inserts and removes")
//                .register();

        Counter failedRemoves = Counter.build()
                .namespace("mc")
                .name("failed_removes")
                .help("increments when the server fails to remove a number for any reason")
                .register();

        Counter failedAdds = Counter.build()
                .namespace("mc")
                .name("failed_adds")
                .help("increments when the server fails to add a number for any reason")
                .register();

        Gauge numberOfNodes = Gauge.build()
                .namespace("mc")
                .name("number_of_nodes")
                .help("number of BST nodes currently on the server")
                .register();

        Summary runAddMethodTime = Summary.build()
                .namespace("mc")
                .name("run_add_method_time")
                .help(" timer to measure how long it takes for the add method to run")
                .register();


        BST<Integer> tree = new BST<>();

        // Define the thread that contains all the processes that the server runs through
        Thread bgThread = new Thread(() -> {
            while (true) {
                try {
                    // this increments the numberOfIterations by one
//                    numberOfIterations.inc();

                    // Two random values to insert or remove from the BST are selected
                    int randomAdd = randomNumber();
                    int randomRemove = randomNumber();

                    /*
                     The server attempts to add the random value.
                     Returns true if the server added a node in the BST.
                     Returns false if a node was not added.
                     */
                    Summary.Timer timer;
                    timer = runAddMethodTime.startTimer();
                    if (tree.add(randomAdd)) {
                        timer.observeDuration();
                        numberOfNodes.inc();
                    } else {
                        timer.observeDuration();
                        failedAdds.inc();
                    };

                    /*
                    The server attempts to remove the random given value
                    IF IT FAILS, it will throw a FailedRemoveException
                    IF IT SUCCEEDS it won't throw any exception
                    */
                    try{
                        tree.remove(randomRemove);
                    }catch (FailedRemoveException e){
                        failedRemoves.inc();
                    }


                    // The server waits for 1000 milliseconds
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        bgThread.start();

        // start the server on the specified port
        try {
            HTTPServer server = new HTTPServer(SERVER_PORT);
            System.out.println("Prometheus exporter running on port " + SERVER_PORT);
        } catch (IOException e) {
            System.out.println("Prometheus exporter was unable to start");
            e.printStackTrace();
        }
    }

    /**
     * Function that selects a random number.
     * @return the randomly selected integer between MIN_VALUE and MAX_VALUE
     */
    private static int randomNumber() {
        return (int)(Math.random() * ((MAX_VALUE - MIN_VALUE) + 1)) + MIN_VALUE;
    }

}