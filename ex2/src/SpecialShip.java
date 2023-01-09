import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;

/**
 * The Special Ship is spaceship extends from SpaceShip.java.
 * This ship pursuer after the closet ship, and always accelerate also if the ship is too
 * close to a enemy ship he will turn on the shield, and if the enemy is too close to the special ship
 * he will decrease one life from this ship. and this ship also shooting.
 * @author David Guedalia
 */
public class SpecialShip extends SpaceShip {
    /**
     * If the distance is less then 0.2 it will turn the shield.
     */
    private static final double DISTANCE_SHIELD = 0.2;
    /**
     * If the distance is less then 0.1 will kill the enemy.
     */
    private static final double DISTANCE_KILL = 0.1;
    /**
     * Does the actions of the Special ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        SpaceShip closestEnemy = game.getClosestShipTo(this);
        SpaceShipPhysics physicsEnemy = closestEnemy.getPhysics();
        double distanceEnemy = physics.distanceFrom(physicsEnemy);
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
        if (distanceEnemy < DISTANCE_SHIELD){
            shieldOn();
        }
        if (distanceEnemy < DISTANCE_KILL){
            if (!closestEnemy.shieldsWorks){
                killEnemy(closestEnemy);
            }
        }
        fire(game);
        actionEachRound();
    }
    /**
     * Given a SpaceShip object and reduce life from him.
     * @param enemy - enemy to decrease from him one life.
     */
    private void killEnemy(SpaceShip enemy){
        enemy.health --;
    }
    /**
     * Gets the image of Special ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    @Override
    public Image getImage() {
        if (!shieldsWorks){
            return GameGUI.SPACESHIP_IMAGE;
        }
        else {
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
    }
}
