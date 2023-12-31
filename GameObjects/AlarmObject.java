package GameObjects;

import Commands.AlarmDeregistrationCommand;
import Commands.AlarmRegistrationCommand;
import Commands.RegistrationState;
import Helpers.Multimap;
import Managers.AlarmObjectManager;
import Managers.CommandBroker;
import Managers.GameManager;

import java.util.ArrayList;

public abstract class AlarmObject {

    public AlarmObject() {
        rData = new ArrayList<>(AlarmObjectManager.ALARM_NUMBER);
        for (int i = 0; i < AlarmObjectManager.ALARM_NUMBER; ++i) {
            rData.add(i, new RegistrationData());
            rData.get(i).rCmd = new AlarmRegistrationCommand(this, AlarmObjectManager.ALARM_ID.toAlarmId(i));
            rData.get(i).drCmd = new AlarmDeregistrationCommand(this, AlarmObjectManager.ALARM_ID.toAlarmId(i));
            rData.get(i).registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        }
    }



    public RegistrationState GetAlarmState(AlarmObjectManager.ALARM_ID al_id) {
        return rData.get(al_id.al_id).registrationState;
    }

    public void TriggerAlarm(AlarmObjectManager.ALARM_ID id) {
        rData.get(id.al_id).registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        switch(id) {
            case ALARM_0:
                Alarm0();
                break;
            case ALARM_1:
                Alarm1();
                break;
            case ALARM_2:
                Alarm2();
                break;
        }
    }

    public void Alarm0() {}
    public void Alarm1() {}
    public void Alarm2() {}

    public final void SubmitAlarmRegistration(long time, AlarmObjectManager.ALARM_ID al_id) {
        assert(rData.get(al_id.al_id).registrationState == RegistrationState.CURRENTLY_DEREGISTERED);

        rData.get(al_id.al_id).registrationState = RegistrationState.PENDING_REGISTRATION;
        rData.get(al_id.al_id).rCmd.UpdateTime(time);
        GameManager.SubmitCommand(rData.get(al_id.al_id).rCmd);

    }

    public final void SubmitAlarmDeregistration(AlarmObjectManager.ALARM_ID al_id) {
        if (rData.get(al_id.al_id).registrationState == RegistrationState.PENDING_DEREGISTRATION) return;
        assert(rData.get(al_id.al_id).registrationState == RegistrationState.CURRENTLY_REGISTERED);

        rData.get(al_id.al_id).registrationState = RegistrationState.PENDING_DEREGISTRATION;
        GameManager.SubmitCommand(rData.get(al_id.al_id).drCmd);
    }

    public final void AlarmRegistration(long time, AlarmObjectManager.ALARM_ID al_id) {
        assert(rData.get(al_id.al_id).registrationState == RegistrationState.PENDING_REGISTRATION);

        rData.get(al_id.al_id).registrationState = RegistrationState.CURRENTLY_REGISTERED;
        rData.get(al_id.al_id).mRef = GameManager.Register(time, al_id, this);
    }

    public final void AlarmDeregistration(AlarmObjectManager.ALARM_ID al_id) {
        if (rData.get(al_id.al_id).registrationState == RegistrationState.CURRENTLY_DEREGISTERED) return;
        assert(rData.get(al_id.al_id).registrationState == RegistrationState.PENDING_DEREGISTRATION);

        rData.get(al_id.al_id).registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        GameManager.Deregister(rData.get(al_id.al_id).mRef);
    }

    private class RegistrationData {
        RegistrationState registrationState;
        AlarmRegistrationCommand rCmd;
        AlarmDeregistrationCommand drCmd;
        Multimap.MultimapIterator mRef;
    }

    private ArrayList<RegistrationData> rData;
}
