/**
 * This class has a single static method (createSpaceships(String[])),
 * It is used by the supplied driver to create all the spaceship objects
 * according to the command line arguments.
 */
public class SpaceShipFactory {
    /**
     * The Human Ship - presents by h.
     */
    private static final char HUMAN_SHIP ='h';
    /**
     * The Runner Ship - presents by r.
     */
    private static final char RUNNER_SHIP ='r';
    /**
     * The Aggressive Ship - presents by a.
     */
    private static final char AGGRESSIVE_SHIP ='a';
    /**
     * The Basher Ship - presents by b.
     */
    private static final char BASHER_SHIP ='b';
    /**
     * The Drunkard Ship - presents by d.
     */
    private static final char DRUNKARD_SHIP = 'd';
    /**
     * The Special Ship - presents by s.
     */
    private static final char SPECIAL_SHIP ='s';

    /**
     * This method build an array of SpaceShip object contains the user input.
     * @param args - The input og the user.
     * @return - An array of the space ship in the current game.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] shipArray = new SpaceShip[args.length];
        for (int i = 0; i < args.length; i++) {

            switch (args[i].charAt(0)) {
                case HUMAN_SHIP:
                    shipArray[i] = new HumanShip();
                    break;
                case RUNNER_SHIP:
                    shipArray[i] = new RunnerShip();
                    break;
                case AGGRESSIVE_SHIP:
                    shipArray[i] = new AggressiveShip();
                    break;
                case BASHER_SHIP:
                    shipArray[i] = new BasherShip();
                    break;
                case DRUNKARD_SHIP:
                    shipArray[i] = new DrunkardShip();
                    break;
                case SPECIAL_SHIP:
                    shipArray[i] = new SpecialShip();
                    break;
            }
        }
        return shipArray;
    }
}
