package grafikus.notebook;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadsController {

    @FXML
    public Label resetLabel;
    @FXML
    private Label thread_one_txt;
    @FXML
    private Label thread_two_txt;
    @FXML
    private Label thread_three_txt;
    @FXML
    public Button clearButton;

    private volatile boolean running = false;
    private Thread1 thread1;
    private Thread2 thread2;
    private Thread3 thread3;

    private int counter1 = 0;
    private int counter2 = 0;
    private int counter3 = 0;

    class Thread1 extends Thread {
        @Override
        public void run() {
            while (running) {
                String message1 = counter1 + " (min)";
                Platform.runLater(() -> thread_one_txt.setText(message1));
                counter1 = (counter1 == 59) ? 0 : counter1 + 1;
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    class Thread2 extends Thread {
        @Override
        public void run() {
            while (running) {
                String message2 = counter2 + " (sec)";
                Platform.runLater(() -> thread_two_txt.setText(message2));
                counter2 = (counter2 == 59) ? 0 : counter2 + 1;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    class Thread3 extends Thread {
        @Override
        public void run() {
            while (running) {
                String message3 = "0." +counter3 + " (sec)";
                Platform.runLater(() -> thread_three_txt.setText(message3));
                counter3 = (counter3 == 10) ? 1 : counter3 + 1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }


    @FXML
    private void onStartButtonClick() {
        if (!running) {
            running = true;
            thread1 = new Thread1();
            thread2 = new Thread2();
            thread3 = new Thread3();

            thread1.start();
            thread2.start();
            thread3.start();
        }
    }

    @FXML
    public void onStopButtonClick(ActionEvent actionEvent) {
        running = false;
        if (thread1 != null) thread1.interrupt();
        if (thread2 != null) thread2.interrupt();
        if (thread3 != null) thread3.interrupt();
        counter1 = 0;
        counter2 = 0;
        counter3 = 0;
        Platform.runLater(() -> {
            thread_one_txt.setText("0 (min)");
            thread_two_txt.setText("0 (sec)");
            thread_three_txt.setText("0 (ms)");
        });
    }



}
