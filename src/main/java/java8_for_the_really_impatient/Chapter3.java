package java8_for_the_really_impatient;

import java.util.function.BiFunction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Chapter3 extends Application {

  /**
   * 3.4 Returning functions instead of just simple values in Java
   *
   * Exercise 6
   */
  public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
    int width = (int) in.getWidth();
    int height = (int) in.getHeight();
    WritableImage out = new WritableImage(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        out.getPixelWriter().setColor(x, y,
            f.apply(in.getPixelReader().getColor(x, y), arg));
      }
    }
    return out;
  }

  @Override
  public void start(Stage stage) throws Exception {
    Image image = new Image("queen-mary.png");
    Image newImage = transform(image,
        (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.5
    );
    stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(newImage))));
    stage.show();
  }


  public static void main(String... args) {
    Application.launch(args);
  }


  /** 3.5 Composition: saves the storage of an intermediate stage. */

}
