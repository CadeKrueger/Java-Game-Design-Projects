package DotGame;

import Helpers.Scene;

public class DotScene extends Scene {
    public Background b;

    @Override
    public void Initialize() {
        DotManager.Initialize();
        b = new Background();
    }
}
