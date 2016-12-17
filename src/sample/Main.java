package sample;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Scanner;
public class Main extends Application {
    public static int N;
    static int deltay=15;
    static int deltax=25;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("SimplePyramyde");
        Group root = new Group();
        Canvas canvas = new Canvas(maxposition(N)*deltax, N*deltay);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private  void drawShapes(GraphicsContext gc) {
        int initial_x =0;
        int initial_y =0;
        int start_value = 1;
        int const_shift = shift_x(N);
        int fontsize =10;
        int textxoffset=3;
        int textyoffset=10;
        gc.setFont(new Font("arial", fontsize));
        for (int i = 1; i<=N; i++){
            for (int j = 1; j<=(maxposition(N)-maxposition(i))/2; j++) {
                gc.setFill(Color.LIGHTSLATEGRAY);
                gc.fillRect(initial_x, initial_y, const_shift, deltay);
                initial_x = initial_x + const_shift;
            }
            gc.setFill(Color.RED);
            //render numbers
            for (int z=1; z<=maxposition(i);z++) {
                gc.setFill(Color.WHITE);
                if (yellow_back(start_value)) gc.setFill(Color.YELLOW);
                gc.fillRect(initial_x, initial_y, const_shift, deltay);
                gc.setFill(Color.BLACK);
                gc.fillText(String.valueOf(start_value++),initial_x+textxoffset,initial_y+textyoffset);
                initial_x=initial_x+const_shift;
            }
            //insert spaces
            for (int j = 1; j<=(maxposition(N)-maxposition(i))/2; j++) {
                gc.setFill(Color.LIGHTSLATEGRAY);
                gc.fillRect(initial_x, initial_y, const_shift, deltay);
                initial_x = initial_x + const_shift;
            }
            initial_y=initial_y+deltay;
            initial_x =0;
        }
    }
    public static void main(String[] args) {
        System.out.print("enter N ");
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        System.out.print(N);
        launch(args);
    }

    boolean yellow_back(int A)
    {
        if (A == 1) return false;
        for ( int i = 2; i<A; i++)
        {
            if ((A % i) == 0) return false;
        };
        return true;
    }

    int shift_x(int N) {//shift (width 1 cell)
        return expo_counter(maxposition(N))*deltax/2;
    }

    int maxposition(int A)
    {
        return (A * 2 - 1);
    }

    int expo_counter(int A) {
        int result = 1;
        while (true) {
            A = Math.abs(A / 10);
            System.out.print("b:");
            System.out.println(A);
            if (A == 0) return result;
            result++;
        }
    }
}
