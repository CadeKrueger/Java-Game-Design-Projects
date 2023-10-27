package DotGame;

import GameObjects.GameObject;
import Helpers.CONSTANTS;
import Managers.AlarmObjectManager;

import java.util.ArrayList;

public class DotManager extends GameObject {

    private static DotManager instance;
    private synchronized static DotManager getInstance() {
        if (instance == null) {
            instance = new DotManager();
        }
        return instance;
    }

    private ArrayList<Dot> liveDots;
    private final long STEP_TIME = CONSTANTS.SECOND / 8;
    public DotSpawner dotSpawner;
    public DotDespawner dotDespawner;
    public DotFactory dotFactory;

    public DotManager() {}

    public static void Initialize() {getInstance()._Initialize(); }

    private void _Initialize() {
        liveDots = new ArrayList<>();
        dotSpawner = new DotSpawner();
        dotDespawner = new DotDespawner();
        dotFactory = new DotFactory();
        alarmObject.SubmitAlarmRegistration(STEP_TIME, AlarmObjectManager.ALARM_ID.ALARM_0);
    }

    public static void Step() { getInstance()._Step(); }

    private void _Step() {
        if (liveDots.size() < 5) {
            dotFactory.GetDot();
        }
        for (Dot d : liveDots) {
            d.Step();
        }
    }

    public static Dot Spawn() { return getInstance()._Spawn(); }

    private Dot _Spawn() {
        return dotFactory.GetDot();
    }

    public static void Despawn() { getInstance()._Despawn(); }

    private void _Despawn() {
        int i = (int) (Math.random() * liveDots.size());
        dotFactory.RecycleDot(liveDots.get(i));
    }

    @Override
    public void GameAlarm0() {
        alarmObject.SubmitAlarmRegistration(STEP_TIME, AlarmObjectManager.ALARM_ID.ALARM_0);
        Step();
        System.out.println(liveDots.size());
    }

    public static void Register(Dot d) { getInstance()._Register(d);}
    public static void Deregister(Dot d) {getInstance()._Deregister(d);}

    private void _Register(Dot d) {
        liveDots.add(d);
    }
    private void _Deregister(Dot d) {
        liveDots.remove(d);
    }
}
