package Commands;

import GameObjects.DrawObject;

public class DrawDeregistrationCommand extends RegistrationCommand {

    private DrawObject pDraw = null;
    public DrawDeregistrationCommand(DrawObject dobj) {
        pDraw = dobj;
    }

    public void Execute() {
        pDraw.SceneDeregistration();
    }
}
