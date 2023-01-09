import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;

/**
 * The Runner Ship is spaceship extends from SpaceShip.java attempts to run away from the fight.
 * It will always accelerate, and will constantly turn away from the closest ship.
 * If the nearest ship is closer than 0.25 units, and if its angle to the Runner is less than 0.23 radians,
 * the Runner feels threatened and will attempt to teleport
 * @author David Guedalia
 */
public class RunnerShip extends SpaceShip{
    /**
     *  If the nearest ship is closer than 0.25 units.
     */
    private static final double RISK_DISTANCE = 0.25;
    /**
     *  If its angle to the Runner is less than 0.23 radians.
     */
    private static final double RISK_ANGLE = 0.23;

    /**
     * Does the actions of the Runner ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        SpaceShipPhysics physicsEnemy = game.getClosestShipTo(this).getPhysics();
        double distanceEnemy = physics.distanceFrom(physicsEnemy);
        double angleEnemy = physics.angleTo(physicsEnemy);
        if (distanceEnemy < RISK_DISTANCE)
            if (angleEnemy < RISK_ANGLE && angleEnemy > -RISK_ANGLE){
                teleport();
        }
        if (angleEnemy < 0){
            physics.move(true, LEFT);
        }
        else {
            physics.move(true, RIGHT);
        }
        actionEachRound();
    }

    /**
     * Gets the image of Runner ship. This method should return the image of the
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
