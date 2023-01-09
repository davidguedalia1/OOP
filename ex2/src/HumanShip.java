import oop.ex2.GameGUI;

import java.awt.*;
/**
 * The Human Ship is Controlled by the user. The keys are: left-arrow and right-arrow to
 * turn, up-arrow to accelerate, ’d’ to fire a shot, ’s’ to turn on the shield, ’a’ to teleport.
 * This class extends from SpaceShip class.
 * @author David Guedalia
 */
public class HumanShip extends SpaceShip{

    /**
     * Does the actions of the Human ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        GameGUI gui = game.getGUI();
        if (gui.isTeleportPressed()){
            teleport();
        }
        int direction = STRAIGHT;
        if (gui.isLeftPressed() && !gui.isRightPressed()){
            direction = LEFT;
        }
        else if (!gui.isLeftPressed() && gui.isRightPressed()){
            direction = RIGHT;
        }
        physics.move(gui.isUpPressed(), direction);
        if (gui.isShieldsPressed()){
            shieldOn();
        }
        if (gui.isShotPressed()){
            fire(game);
        }
        actionEachRound();
    }

    /**
     * Gets the image of Human ship. This method should return the image of the
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
