package Commands;

import GameObjects.UpdateObject;

public class UpdateRegistrationCommand extends RegistrationCommand {

    private UpdateObject pUpdate = null;

    public UpdateRegistrationCommand(UpdateObject uobj) {
        pUpdate = uobj;
    }

    public void Execute() {
        pUpdate.SceneRegistration();
    }
}
