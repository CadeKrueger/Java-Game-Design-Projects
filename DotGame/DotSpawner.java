package DotGame;

import GameObjects.GameObject;
import Helpers.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DotSpawner extends GameObject {
    private boolean hasPressed = false;

    public DotSpawner() {
        updateObject.SubmitUpdateRegistration();
    }

    @Override
    public void GameUpdate() {
        if (!hasPressed && Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
            hasPressed = true;
            Dot d = DotManager.Spawn();
            d.SetColor(Color.RED);
        }else if (hasPressed && !(Keyboard.isKeyPressed(KeyEvent.VK_SPACE))) {
            hasPressed = false;
        }
    }
}
