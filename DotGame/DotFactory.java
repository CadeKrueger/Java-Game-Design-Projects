package DotGame;

import java.util.ArrayDeque;
import java.util.Queue;

public class DotFactory {
    private static Queue<Dot> recycledDots;

    public DotFactory() {
        recycledDots = new ArrayDeque<>();
    }

    public Dot GetDot() {
        Dot newDot = recycledDots.poll();
        if (newDot == null) {
            newDot = new Dot();
            System.out.println("Dot was created");
        } else { System.out.println("Dot was recycled"); }
        newDot.Initialize();
        return newDot;
    }

    public void RecycleDot(Dot deadDot) {
        recycledDots.add(deadDot);
        deadDot.Deinitialize();
        System.out.println("Dot was trashed");
    }
}
