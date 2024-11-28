package grafikus.notebook;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadsController {

    @FXML private Button startButton;
    @FXML private Label thread_one_txt;
    @FXML private Label thread_two_txt;
    @FXML private Label thread_three_txt;

    class Szal1 extends Thread {
        @Override
        public void run() {
            int counter1 = 0;
            while (true) {
                String message1 = "Egyik szál: (min)" + counter1;
                Platform.runLater(() -> thread_one_txt.setText(message1));
                counter1=(counter1==60)? 0 : +1;
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Szal2 extends Thread {
        @Override
        public void run() {
            int counter2 = 0;
            while (true) {
                String message2 = "Másik szál: (sec)" + counter2;
                Platform.runLater(() -> thread_two_txt.setText(message2));
                counter2=(counter2==60)? 0 : +1;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Szal3 extends Thread {
        @Override
        public void run() {
            int counter3 = 0;
            while (true) {
                String message3 = "Harmadik szál (ms): " + counter3;
                Platform.runLater(() -> thread_three_txt.setText(message3));
                counter3=(counter3==1000)? 0 : +1;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
    @FXML
    private void onStartButtonClick() {
        Szal1 szal1 = new Szal1();
        Szal2 szal2 = new Szal2();

        szal1.start();
        szal2.start();
    }
}
