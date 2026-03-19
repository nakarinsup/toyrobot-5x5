import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class RobotUtilityTest {

    private static Stream<Robot> provideValidRobotObjects() {
        return Stream.of(new Robot(0, 0, Robot.CompassDirection.NORTH),
                new Robot(0, 4, Robot.CompassDirection.WEST),
                new Robot(4, 0, Robot.CompassDirection.SOUTH),
                new Robot(4, 4, Robot.CompassDirection.EAST),
                new Robot(2, 3, Robot.CompassDirection.SOUTH));
    }

    @ParameterizedTest
    @MethodSource("provideValidRobotObjects")
    void testIsRobotValid_Valid(Robot robot) {
        Assertions.assertTrue(RobotUtility.isRobotValid(robot));
    }

    private static Stream<Robot> provideInvalidRobotObjects() {
        return Stream.of(new Robot(0, 0, null),
                new Robot(2, 3, null),
                new Robot(0, 5, Robot.CompassDirection.WEST),
                new Robot(5, 0, Robot.CompassDirection.SOUTH),
                new Robot(4, 5, Robot.CompassDirection.EAST),
                new Robot(5, 5, Robot.CompassDirection.EAST),
                new Robot(-1, 0, Robot.CompassDirection.NORTH),
                new Robot(0, -1, Robot.CompassDirection.EAST),
                new Robot(-1, -1, Robot.CompassDirection.WEST),
                new Robot(-100, 0, Robot.CompassDirection.WEST),
                new Robot(0, -100, Robot.CompassDirection.WEST),
                new Robot(-100, -100, Robot.CompassDirection.WEST),
                new Robot(100, 0, Robot.CompassDirection.WEST),
                new Robot(0, 100, Robot.CompassDirection.WEST),
                new Robot(100, 100, Robot.CompassDirection.WEST)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidRobotObjects")
    void testIsRobotValid_Invalid(Robot robot) {
        Assertions.assertFalse(RobotUtility.isRobotValid(robot));
    }

    private static Stream<Arguments> provideMoveRobotSuccess() {
        return Stream.of(Arguments.of(new Robot(0, 0, Robot.CompassDirection.NORTH), 0, 1),
                Arguments.of(new Robot(0, 0, Robot.CompassDirection.EAST), 1, 0),
                Arguments.of(new Robot(0, 4, Robot.CompassDirection.SOUTH), 0, 3),
                Arguments.of(new Robot(0, 4, Robot.CompassDirection.EAST), 1, 4),
                Arguments.of(new Robot(2, 3, Robot.CompassDirection.NORTH), 2, 4),
                Arguments.of(new Robot(2, 3, Robot.CompassDirection.EAST), 3, 3),
                Arguments.of(new Robot(2, 3, Robot.CompassDirection.WEST), 1, 3),
                Arguments.of(new Robot(2, 3, Robot.CompassDirection.SOUTH), 2, 2),
                Arguments.of(new Robot(4, 4, Robot.CompassDirection.WEST), 3, 4),
                Arguments.of(new Robot(4, 4, Robot.CompassDirection.SOUTH), 4, 3),
                Arguments.of(new Robot(4, 0, Robot.CompassDirection.WEST), 3, 0),
                Arguments.of(new Robot(4, 0, Robot.CompassDirection.NORTH), 4, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMoveRobotSuccess")
    void testMoveRobot_Success(Robot robot, int expectedPosX, int expectedPosY) {
        Robot.CompassDirection facingBeforeMove = robot.getFacingDirection();

        //act
        Assertions.assertTrue(RobotUtility.moveRobot(robot));

        Assertions.assertEquals(expectedPosX, robot.getPosX());
        Assertions.assertEquals(expectedPosY, robot.getPosY());
        Assertions.assertEquals(facingBeforeMove, robot.getFacingDirection());
    }

    private static Stream<Robot> provideMoveRobotFail() {
        return Stream.of(new Robot(0, 0, Robot.CompassDirection.SOUTH),
                new Robot(0, 0, Robot.CompassDirection.WEST),
                new Robot(0, 4, Robot.CompassDirection.NORTH),
                new Robot(0, 4, Robot.CompassDirection.WEST),
                new Robot(4, 4, Robot.CompassDirection.EAST),
                new Robot(4, 4, Robot.CompassDirection.NORTH),
                new Robot(4, 0, Robot.CompassDirection.EAST),
                new Robot(4, 0, Robot.CompassDirection.SOUTH)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMoveRobotFail")
    void testMoveRobot_Fail(Robot robot) {
        int posXBeforeMove = robot.getPosX();
        int posYBeforeMove = robot.getPosY();
        Robot.CompassDirection facingBeforeMove = robot.getFacingDirection();

        //act
        Assertions.assertFalse(RobotUtility.moveRobot(robot));

        //expected no change on robot position and facing direction
        Assertions.assertEquals(posXBeforeMove, robot.getPosX());
        Assertions.assertEquals(posYBeforeMove, robot.getPosY());
        Assertions.assertEquals(facingBeforeMove, robot.getFacingDirection());
    }

    private static Stream<String> provideValidPlaceRobotCommand() {
        return Stream.of("PLACE 0,0,SOUTH",
                "PLACE 0,4,NORTH",
                "PLACE 4,0,EAST",
                "PLACE 4,4,WEST",
                "PLACE 2,3,NORTH",
                "PLACE 1,2,EAST",
                "PLACE 1,4,WEST",
                "PLACE 4,1,SOUTH",
                "place 4,1,south",       //command can be lower case (case is ignored)
                "place 4,1,n",          //also support first later of compass n,w,e,s instead of full word
                "PlAcE 4,1, s",
                "plaCe    4  ,1   ,   e",
                "pLAcE   4,  1, w"
        );
    }


    @ParameterizedTest
    @MethodSource("provideValidPlaceRobotCommand")
    void testPlaceRobot_Success(String command) {
        Robot dummyRobot = new Robot(0, 0, Robot.CompassDirection.NORTH);
        Assertions.assertTrue(RobotUtility.placeRobot(command, dummyRobot));
    }

    private static Stream<String> provideInvalidPlaceRobotCommand() {
        return Stream.of("PLACE 123",
                "PLACE 1,2",
                "PLACE 1,2,3",
                "PLACE 1,2,3,NORTH",
                "PLACE -1,2,NORTH",
                "PLACE 0,-1,NORTH",
                "PLACE -1,-1,EAST",
                "PLACE 5,0,NORTH",
                "PLACE 0,5,NORTH",
                "PLACE 5,5,NORTH",
                "PLACE 0,5,SOUTH",
                "PLACE 1,2,NORTHONS",
                "PLACE 1,2,EASTERN",
                "PLACE 1,2,WESTGATE",
                "PLACE 1,2,SOUTHEAST",
                "PLACES 1,2, NORTH",         //command must exactly "place" or "PLACE"
                "PLACEME 1,2, NORTH",
                "PLACEMEEE 1,2, NORTH"
        );
    }


    @ParameterizedTest
    @MethodSource("provideInvalidPlaceRobotCommand")
    void testPlaceRobot_Fail(String command) {
        Assertions.assertFalse(RobotUtility.placeRobot(command, new Robot(0, 0, Robot.CompassDirection.NORTH)));
    }
}
