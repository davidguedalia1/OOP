import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;

/**
 * The Aggressive Ship is spaceship extends from SpaceShip.java.
 * This ship pursues other ships and tries to fire at them. It will always accelerate,
 * and turn towards the nearest ship. When its angle to the nearest ship is less than 0.21 radians,
 * it will try to fire.
 * @author David Guedalia
 */
public class AggressiveShip extends SpaceShip{

    /**
     * If angle to the nearest ship is less than 0.21 radians.
     */
    private static final double HIT_ANGLE = 0.21;

    /**
     * Does the actions of the Aggressive ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        SpaceShipPhysics physicsEnemy = game.getClosestShipTo(this).getPhysics();
        double angleEnemy = physics.angleTo(physicsEnemy);
        if (angleEnemy > 0){
            physics.move(true, LEFT);
        }
        else if (angleEnemy < 0){
            physics.move(true, RIGHT);
        }
        else {
            physics.move(true, STRAIGHT);
        }
        if (angleEnemy < HIT_ANGLE && angleEnemy > -HIT_ANGLE){
            fire(game);
        }
        actionEachRound();
    }

    /**
     * Gets the image of Aggressive ship. This method should return the image of the
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
