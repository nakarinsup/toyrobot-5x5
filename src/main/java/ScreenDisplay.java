/**
 * class for draw robot and table in console screen
 */
public class ScreenDisplay {

    private void printDummyMultipleNewLines() {
        for (int i = 0; i < 20; ++i) {
            System.out.println();
        }
    }

    /**
     * draw robot and table in console screen. if robot is null or position or facing direction invalid, only table will be drawn
     *
     * @param robot
     */
    public void draw(Robot robot) {
        StringBuffer tableStr = new StringBuffer();

        int drawRobotRound = -1;
        if (robot != null && RobotUtility.isRobotPositionValid(robot.getPosX(), robot.getPosY())
                && robot.getFacingDirection() != null) {
            drawRobotRound = (5 - 1) - robot.getPosY();
        }

        for (int i = 0; i < 5; ++i) {
            tableStr.append("-------------------------------\n");
            if (i != drawRobotRound) {
                tableStr.append("|     |     |     |     |     |\n");
            } else {
                int posX = robot.getPosX();
                for (int j = 0; j < 5; ++j) {
                    if (j == posX) {
                        if (robot.getFacingDirection() == Robot.CompassDirection.NORTH) {
                            tableStr.append("|  ^  ");
                        } else if (robot.getFacingDirection() == Robot.CompassDirection.EAST) {
                            tableStr.append("|  >  ");
                        } else if (robot.getFacingDirection() == Robot.CompassDirection.WEST) {
                            tableStr.append("|  <  ");
                        } else if (robot.getFacingDirection() == Robot.CompassDirection.SOUTH) {
                            tableStr.append("|  v  ");
                        }
                    } else {
                        tableStr.append("|     ");
                    }
                }
                tableStr.append("|\n");
            }
        }
        tableStr.append("-------------------------------\n");

        printDummyMultipleNewLines();
        System.out.print(tableStr.toString());
    }

}
