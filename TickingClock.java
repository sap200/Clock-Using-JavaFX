import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class TickingClock extends Application {
  @Override
  public void start(Stage primaryStage) {
    Pane pane = new Pane();

     DetailedClockPane clock = new DetailedClockPane();
    String str = clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond();
    EventHandler<ActionEvent> handler1 = e -> { clock.setCurrentTime();  };
    
    Timeline animation = new Timeline(new KeyFrame(new Duration(1000), handler1));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
    pane.getChildren().addAll(clock);

    Scene scene = new Scene(pane, 250, 250);
    primaryStage.setTitle("Ticking Clock"); 
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}