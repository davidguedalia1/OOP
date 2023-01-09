import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;

/**
 * The Basher Ship is spaceship extends from SpaceShip.java, this ship attempts to collide with other ships.
 * It will always accelerate, and will constantly turn towards the closest ship.
 * If it gets within a distance of 0.19 units (inclusive) from another ship, it will attempt to turn on its shields.
 * @author David Guedalia
 */
public class BasherShip extends SpaceShip{
    /**
     *  If it gets within a distance of 0.19 units from another ship.
     */
    private static final double RISK_DISTANCE = 0.19;

    /**
     * Does the actions of the Busher ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        SpaceShipPhysics physicsEnemy = game.getClosestShipTo(this).getPhysics();
        double distanceEnemy = physics.distanceFrom(physicsEnemy);
        double angleEnemy = physics.angleTo(physicsEnemy);
        if (angleEnemy < 0){
            physics.move(true, RIGHT);
        }
        else if (angleEnemy > 0){
            physics.move(true, LEFT);
        }
        else {
            physics.move(true, STRAIGHT);
        }
        if (distanceEnemy <= RISK_DISTANCE){
            shieldOn();
        }
        else {
            shieldsWorks = false;
        }
        actionEachRound();
    }

    /**
     * Gets the image of Busher ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * @return the image of this ship.
     */
    @Override
    public Image getImage() {
        if (shieldsWorks){
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        else {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }
}
