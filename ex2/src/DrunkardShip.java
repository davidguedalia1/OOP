import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;

/**
 * The Drunkard Ship is spaceship extends from SpaceShip.java.
 * Its pilot had a tad too much to drink, the space ship doing turns
 * based on random basis, also shooting in angle of 0.6 radians.
 * and turn on the shield in probability of 0.5 and distance less then 0.5.
 * @author David Guedalia
 */
public class DrunkardShip extends SpaceShip{
    /**
     * The distance less from 0.5 the ship will "try" turn on the shield.
     */
    private static final double RISK_DISTANCE = 0.5;
    /**
     * If angle to the nearest ship is less than 0.6 radians will shoot.
     */
    private static final double HIT_ANGLE = 0.6;

    @Override
    public void doAction(SpaceWars game) {
        double rand = Math.random();
        SpaceShipPhysics physicsEnemy = game.getClosestShipTo(this).getPhysics();
        double distanceEnemy = physics.distanceFrom(physicsEnemy);
        double angleEnemy = physics.angleTo(physicsEnemy);
        if (rand > 0.67){
            physics.move(true, LEFT);
        }
        else if (rand < 0.33){
            physics.move(true, RIGHT);
        }
        else{
            physics.move(true, STRAIGHT);
        }
        if (angleEnemy < HIT_ANGLE && angleEnemy > -HIT_ANGLE && rand > 0.2){
            fire(game);
        }
        if (distanceEnemy < RISK_DISTANCE && rand >0.5){
            shieldOn();
        }
        else {
            shieldsWorks = false;
        }
        actionEachRound();
    }

    /**
     * Gets the image of Drunkard ship. This method should return the image of the
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