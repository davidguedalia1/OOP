import org.junit.runners.Suite;
import org.junit.runner.RunWith;

/**
 * This class run the all tests - LockerTest, LongTermTest, SpaceShipTest.
 * @author David Guedalia
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        LockerTest.class,
        SpaceshipTest.class,
        LongTermTest.class,

})

public class SpaceshipDepositoryTest {
}