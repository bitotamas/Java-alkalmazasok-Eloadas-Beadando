package grafikus.notebook;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadsController {

    public Label resetLabel;
    @FXML
    private Button stopButton;
    @FXML
    private Button startButton;
    @FXML
    private Label thread_one_txt;
    @FXML
    private Label thread_two_txt;
    @FXML
    private Label thread_three_txt;
    @FXML
    public Button clearButton;

    private volatile boolean running = false;
    private Szal1 szal1;
    private Szal2 szal2;
    private Szal3 szal3;

    private int counter1 = 0;
    private int counter2 = 0;
    private int counter3 = 0;

    class Szal1 extends Thread {
        @Override
        public void run() {
            while (running) {
                String message1 = counter1 + " (min)";
                Platform.runLater(() -> thread_one_txt.setText(message1));
                counter1 = (counter1 == 59) ? 0 : counter1 + 1;
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Állítsuk a megszakítási flaget
                    break; // Kilépés a ciklusból
                }
            }
        }
    }
    class Szal2 extends Thread {
        @Override
        public void run() {
            while (running) {
                String message2 = counter2 + " (sec)";
                Platform.runLater(() -> thread_two_txt.setText(message2));
                counter2 = (counter2 == 59) ? 0 : counter2 + 1;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Állítsuk a megszakítási flaget
                    break; // Kilépés a ciklusból
                }
            }
        }
    }
    class Szal3 extends Thread {
        @Override
        public void run() {
            while (running) {
                String message3 = "0." +counter3 + " (sec)";
                Platform.runLater(() -> thread_three_txt.setText(message3));
                counter3 = (counter3 == 10) ? 1 : counter3 + 1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Állítsuk a megszakítási flaget
                    break; // Kilépés a ciklusból
                }
            }
        }
    }


    @FXML
    private void onStartButtonClick() {
        if (!running) {
            running = true;
            szal1 = new Szal1();
            szal2 = new Szal2();
            szal3 = new Szal3();

            szal1.start();
            szal2.start();
            szal3.start();
        }
    }

    @FXML
    public void onStopButtonClick(ActionEvent actionEvent) {
        running = false;
        if (szal1 != null) szal1.interrupt();
        if (szal2 != null) szal2.interrupt();
        if (szal3 != null) szal3.interrupt();
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
