package DotGame;

import GameObjects.GameObject;
import Helpers.Keyboard;

import java.awt.event.KeyEvent;

public class DotDespawner extends GameObject {
    private boolean hasPressed = false;

    public DotDespawner() {
        updateObject.SubmitUpdateRegistration();
    }

    @Override
    public void GameUpdate() {
        if (!hasPressed && Keyboard.isKeyPressed(KeyEvent.VK_P)) {
            hasPressed = true;
            DotManager.Despawn();
        }else if (hasPressed && !(Keyboard.isKeyPressed(KeyEvent.VK_P))) {
            hasPressed = false;
        }
    }
}
