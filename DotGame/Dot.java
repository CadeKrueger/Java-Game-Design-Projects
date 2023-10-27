package DotGame;

import GameObjects.GameObject;

import java.awt.Color;
import java.awt.Graphics2D;
public class Dot extends GameObject {

    private int x;
    private int y;
    private Color dotColor;

    public Dot () {
        x = (int) (Math.random()*600);
        y = (int) (Math.random()*600);
        dotColor = Color.BLUE;
        updateObject.SubmitUpdateRegistration();
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        g2.setColor(dotColor);
        g2.fillOval(x, y, 50, 50);
    }

    @Override
    public void GameUpdate() {}

    public void SetColor(Color c) {
        this.dotColor = c;
    }
    public void SetX(int x) { this.x = x; }
    public void SetY(int y) { this.y = y; }

    public void Step() {
        if (x >= 800) { x = -50; }
        x += 10;
    }

    public void Initialize() {
        DotManager.Register(this);
        this.SetX((int) (Math.random()*600));
        this.SetY((int) (Math.random()*600));
        this.SetColor(Color.BLUE);
        drawObject.SubmitDrawRegistration();
    }

    public void Deinitialize() {
        drawObject.SubmitDrawDeregistration();
        DotManager.Deregister(this);
    }
}
