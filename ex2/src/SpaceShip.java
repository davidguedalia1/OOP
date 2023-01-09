import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *
 * @author David Guedalia
 */

public abstract class SpaceShip{
    /**
     *  The maximal energy starts at 210
     */
    private static final int MAX_ENERGY = 210;

    /**
     *  The current energy level begins at 190.
     */
    private static final int START_ENERGY = 190;

    /**
     *  Firing a shot costs 19 energy units.
     */
    private static final int FIRE_ENERGY = 19;

    /**
     *  Teleporting costs 140 energy.
     */
    private static final int TELEPORTING_ENERGY = 140;

    /**
     * If the ship’s shields are up in a certain round, they consume 3 energy units for that round.
     */
    private static final int SHIELD_ENERGY = 3;

    /**
     * When a ship "bashes" another, its maximal energy level goes up by 18.
     */
    private static final int INCREASE_BASH_ENERGY = 18;

    /**
     * Getting hit (either getting shot at or colliding) while the shields are down reduces the
     * maximal energy level by 10.
     */
    private static final int HIT_DECREASE_ENERGY = 10;

    /**
     * The health ship begins with 22.
     */
    private static final int START_HEALTH = 22;

    /**
     * After firing, the ship’s guns cannot be used for a period of 7 rounds.
     */
    private static final int DELAY_AFTER_FIRE = 7;

    /**
     * The current energy level is constantly charging: it goes up by 1 every round.
     */
    private static final int INC_EACH_ROUND = 1;

    /**
     * The number symbols turn right
     */
    protected static final int RIGHT = -1;
    /**
     * The number symbols turn right
     */
    protected static final int LEFT = 1;

    /**
     * The number symbols turn right
     */
    protected static final int STRAIGHT = 0;

    /**
     * check if the shield is on or off.
     */
    protected boolean shieldsWorks;

    /**
     * The current maximal energy of the ship.
     */
    protected int currentMaximalEnergy;

    /**
     * The current energy of the ship.
     */
    protected int currentEnergy;

    /**
     * The current health of the ship.
     */
    protected int health;

    /**
     * The current health of the ship.
     */
    protected SpaceShipPhysics physics;

    /**
     * The current health of the ship.
     */
    protected int roundsWithoutFire;

    /**
     * New SpaceShip, call the reset function, reset the fields of the beginning
     */
    protected SpaceShip() {
        reset();
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     *
     */
    protected void actionEachRound(){
        if (roundsWithoutFire <= DELAY_AFTER_FIRE){
            roundsWithoutFire ++;
        }
        if (currentEnergy + INC_EACH_ROUND <= currentMaximalEnergy){
            currentEnergy += INC_EACH_ROUND;
        }
        else {
            currentEnergy = currentMaximalEnergy;
        }
    }

        /**
         * This method is called every time a collision with this ship occurs
         */
    public void collidedWithAnotherShip() {
        if (shieldsWorks){
            currentMaximalEnergy += INCREASE_BASH_ENERGY;
            currentEnergy += INCREASE_BASH_ENERGY;
        }
        else {
            if (health > 0){
                health --;
            }
            currentMaximalEnergy -= HIT_DECREASE_ENERGY;
            if (currentMaximalEnergy < 0){
                currentMaximalEnergy = 0;
            }
        }
        if (currentEnergy > currentMaximalEnergy){
            currentEnergy = currentMaximalEnergy;
        }

    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        shieldsWorks = false;
        currentMaximalEnergy = MAX_ENERGY;
        currentEnergy = START_ENERGY;
        health = START_HEALTH;
        roundsWithoutFire = DELAY_AFTER_FIRE;
        physics = new SpaceShipPhysics();
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shieldsWorks){
            if (health > 0) {
                health --;
            }
            currentMaximalEnergy -= HIT_DECREASE_ENERGY;
            if (currentMaximalEnergy < 0){
                currentMaximalEnergy = 0;
            }
        }
        if (currentEnergy > currentMaximalEnergy){
            currentEnergy = currentMaximalEnergy;
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public abstract Image getImage();

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (currentEnergy >= FIRE_ENERGY && roundsWithoutFire >= DELAY_AFTER_FIRE){
            currentEnergy -= FIRE_ENERGY;
            game.addShot(physics);
            roundsWithoutFire = 0;
        }
    }
    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (currentEnergy >= SHIELD_ENERGY){
            currentEnergy -= SHIELD_ENERGY;
            shieldsWorks = true;
        }
        else {
            shieldsWorks = false;
        }
    }
    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (currentEnergy >= TELEPORTING_ENERGY){
            physics = new SpaceShipPhysics();
            currentEnergy -= TELEPORTING_ENERGY;
        }
    }
}
