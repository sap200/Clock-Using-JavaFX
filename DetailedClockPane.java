import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DetailedClockPane extends Pane {
     
  private int hour;
  private int minute;
  private int second;
  private double w = 250; // CLock Pane's width
  private double h = 250; // Clock Pane's height

 // Constructs a clock Pane with default time
  public DetailedClockPane() {
     setCurrentTime();
  }

 // Constructs a clockpane with given hour, minute and second
  public DetailedClockPane(int hour, int minute, int second) {
     this.hour = hour;
     this.minute = minute;
     this.second = second;
  }

 // Returns Hour of day
  public int getHour() {
    return hour;
  }

 // Sets the hour of day to specified hour
  public void setHour(int hour) {
    this.hour = hour;
    paintClock();
  }

 // Returns the minute
  public int getMinute() {
     return minute;
  }

 // Sets the minute to new Minute
  public void setMinute(int minute) {
     this.minute = minute;
     paintClock();
  }

 // Returns the second
  public int getSecond() {
     return second;
  }

 // sets the second to new second
  public void setSecond(int second) {
    this.second = second;
    paintClock();
  }

  // Returns the width of pane
  public double getW() {
     return w;
  }

  // sets the new width of pane
  public void setW(double w) {
     this.w = w;
     paintClock();
  }

 // Returns the height of the pane
  public double getH() {
    return h;
  }

 // Sets the height of the pane
  public void setH(double h) {
     this.h = h;
     paintClock();
  }

  // sets the current time
  public void setCurrentTime() {
    Calendar c = new GregorianCalendar();

    this.hour = c.get(Calendar.HOUR_OF_DAY);
    this.minute = c.get(Calendar.MINUTE);
    this.second = c.get(Calendar.SECOND);

    paintClock();
  }

 /** Paint clock */
  protected void paintClock() {
    /** Initialize parameters */
     double textBoundaryRadius = Math.min(w, h) * 0.5 * 0.65;
     double clockBoundaryRadius1 = Math.min(w, h) * 0.5 * 0.7;
     double clockBoundaryRadius = Math.min(w, h) * 0.5 * 0.75;
     double clockRadius = Math.min(w, h) * 0.8 * 0.5;
     double centerX = w / 2;
     double centerY = h / 2;

   // Draw outer circles
    Circle clock = new Circle(centerX, centerY, clockRadius);
    clock.setFill(Color.WHITE);
    clock.setStroke(Color.BLACK);
   // Draw inner circle
    Circle innerCircle = new Circle(centerX, centerY, clockBoundaryRadius);
    innerCircle.setFill(null);
    innerCircle.setStroke(Color.BLACK);
   // Draw more inner circle
    Circle innerCircle1 = new Circle(centerX, centerY, clockBoundaryRadius1);
    innerCircle1.setFill(null);
    innerCircle1.setStroke(Color.BLACK);
   // Draw textCircle
   Circle textCircle = new Circle(centerX, centerY, textBoundaryRadius);
   textCircle.setFill(null);
   textCircle.setStroke(Color.BLACK);

    /** Creating second line */
    double sLength = clockRadius * 0.8;
    double secondX = centerX + sLength * Math.sin((2 * Math.PI / 60) * second);
    double secondY = centerY - sLength * Math.cos((2 * Math.PI / 60) * second);
    Line sLine = new Line(centerX, centerY, secondX, secondY);
    sLine.setStroke(Color.RED);

   /** Creating minute line */
   double mLength = clockRadius * 0.65;
   double minuteX = centerX + mLength * Math.sin((minute + second / 60) * (2 * Math.PI / 60));
   double minuteY = centerY - mLength * Math.cos((minute + second / 60) * (2 * Math.PI / 60));
   Line mLine = new Line(centerX, centerY, minuteX, minuteY);
   mLine.setStroke(Color.BLUE);

  /** Creating hour line */
   double hLength = clockRadius * 0.5;
   double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60 ) * (2 * Math.PI / 12));
   double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60) * (2 * Math.PI / 12));
   Line hLine = new Line(centerX, centerY, hourX, hourY);
   hLine.setStroke(Color.GREEN);

   getChildren().clear();
   getChildren().addAll(clock, hLine, mLine, sLine);

   for(int i = 0; i < 60; i++) {
     double startX = centerX + clockRadius * Math.sin((2 * Math.PI / 60 )* i);
     double startY = centerY - clockRadius * Math.cos((2 * Math.PI / 60) * i);
     double endX = centerX + clockBoundaryRadius * Math.sin((2 * Math.PI / 60 )* i);
     double endY = centerY - clockBoundaryRadius * Math.cos((2 * Math.PI / 60 )* i);
     double endX1 = centerX + clockBoundaryRadius1 * Math.sin((2 * Math.PI / 60 )* i);
     double endY1 = centerY - clockBoundaryRadius1 * Math.cos((2 * Math.PI / 60 )* i);
      Line l;
     if( i % 5 != 0)
       l = new Line(startX, startY, endX, endY);
      else
       l = new Line(startX, startY, endX1, endY1);
    
     getChildren().add(l);
    
   }
  
   int k = 12;
   for(int i = 0; i < 12; i++) {
      if(k == 13)
        k = 1;
      double x = centerX + textBoundaryRadius * Math.sin((2 * Math.PI / 12 )* i);
      double y = centerY - textBoundaryRadius * Math.cos((2 * Math.PI / 12 )* i);
     if( k == 1 || k == 2 || k == 3 || k == 4 || k == 5 )
      getChildren().add(new Text(x - 5, y + 3, k + ""));
     else if(k == 9)
      getChildren().add(new Text(x, y + 4, k + ""));
     else if(k == 6)
      getChildren().add(new Text(x - 3, y + 1, k + ""));
     else if(k == 11)
       getChildren().add(new Text(x , y + 7 , k + ""));
     else if(k == 12)
      getChildren().add(new Text(x - 5, y + 7, k + ""));
     else 
       getChildren().add(new Text(x , y , k + ""));
      k++;
   }
 }
  

  

  
}