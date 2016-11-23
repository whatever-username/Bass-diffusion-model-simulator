import java.awt.*;

/**
 * Created by Innokentiy on 20.09.2016.
 */
public class Test {
    public static void main(String[] args) {
        Dimension a = new Dimension(2,1);
        Dimension b = new Dimension(1,1);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.getHeight());

    }
}
