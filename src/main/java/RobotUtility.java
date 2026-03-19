import java.util.Locale;

/**
 * Utility to implement detail of robot validation and operation
 */
public class RobotUtility {

    /**
     * check if robot position and facing direction are valid
     *
     * @param robot
     * @return true if valid
     */
    public static boolean isRobotValid(Robot robot){
        return isRobotPositionValid(robot.getPosX(), robot.getPosY()) && robot.getFacingDirection() != null;
    }

    /**
     * check if robot position is valid
     * @param posX
     * @param posY
     * @return true if valid
     */
    public static boolean isRobotPositionValid(int posX, int posY){
        if(posX < 0 || posX >= 5 || posY < 0 || posY >= 5){
            return false;
        }
        return true;
    }

    /**
     * move forward the robot according to the current facing direction
     *
     * @param robot
     * @return false if robot cannot move, or move command was ignored. true if move success.
     */
    public static boolean moveRobot(Robot robot) {
        if (robot.getFacingDirection() == null) {
            return false;
        }

        if (robot.getFacingDirection() == Robot.CompassDirection.NORTH) {
            if (isRobotPositionValid(robot.getPosX(), robot.getPosY() + 1)) {
                robot.setPosY(robot.getPosY() + 1);
                return true;
            } else {
                return false;
            }
        } else if (robot.getFacingDirection() == Robot.CompassDirection.SOUTH) {
            if (isRobotPositionValid(robot.getPosX(), robot.getPosY() - 1)) {
                robot.setPosY(robot.getPosY() - 1);
                return true;
            } else {
                return false;
            }
        } else if (robot.getFacingDirection() == Robot.CompassDirection.EAST) {
            if (isRobotPositionValid(robot.getPosX() + 1, robot.getPosY())) {
                robot.setPosX(robot.getPosX() + 1);
                return true;
            } else {
                return false;
            }
        } else if (robot.getFacingDirection() == Robot.CompassDirection.WEST) {
            if (isRobotPositionValid(robot.getPosX() - 1, robot.getPosY())) {
                robot.setPosX(robot.getPosX() - 1);
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /** execute robot to turn left
     *
     * @param robot
     */
    public static void turnLeftRobot(Robot robot){
        if(robot.getFacingDirection() == Robot.CompassDirection.NORTH){
            robot.setFacingDirection(Robot.CompassDirection.WEST);
        }else if(robot.getFacingDirection() == Robot.CompassDirection.WEST){
            robot.setFacingDirection(Robot.CompassDirection.SOUTH);
        }else if(robot.getFacingDirection() == Robot.CompassDirection.SOUTH){
            robot.setFacingDirection(Robot.CompassDirection.EAST);
        }else if(robot.getFacingDirection() == Robot.CompassDirection.EAST) {
            robot.setFacingDirection(Robot.CompassDirection.NORTH);
        }
    }

    /** execute robot to turn right
     *
     * @param robot
     */
    public static void turnRightRobot(Robot robot){
        if(robot.getFacingDirection() == Robot.CompassDirection.NORTH){
            robot.setFacingDirection(Robot.CompassDirection.EAST);
        }else if(robot.getFacingDirection() == Robot.CompassDirection.EAST){
            robot.setFacingDirection(Robot.CompassDirection.SOUTH);
        }else if(robot.getFacingDirection() == Robot.CompassDirection.SOUTH){
            robot.setFacingDirection(Robot.CompassDirection.WEST);
        }else if(robot.getFacingDirection() == Robot.CompassDirection.WEST) {
            robot.setFacingDirection(Robot.CompassDirection.NORTH);
        }
    }


    /**
     * place the robot on the table
     *
     * @param command String command from user input, format is "PLACE x,y,f" where x, y are integers and f is robot facing compass direction
     * @param robot robot object to be placed by set position and orientation (facing direction)
     * @return true if placing success
     */
    public static boolean placeRobot(String command, Robot robot){
        //compare "PLACE " with whitespace afterward is intended for ensure the command must exactly be "PLACE" (ignore case)
        if(!command.toUpperCase().startsWith("PLACE ")){
            return false;
        }

        String[] posXYF = command.substring(6).split(",");  //substring start index 6 to skip "place<space>"
        if(posXYF.length != 3 ){
            return false;
        }

        int posX = -1;
        int posY = -1;
        Robot.CompassDirection facingDirection = null;

        try{
            posX = Integer.parseInt(posXYF[0].trim());
            posY = Integer.parseInt(posXYF[1].trim());

            if(!isRobotPositionValid(posX, posY)){
                return false;
            }

            String directionLetter = posXYF[2].trim().toUpperCase(Locale.ENGLISH);
            if( !directionLetter.equals("NORTH") && !directionLetter.equals("WEST")
                    && !directionLetter.equals("EAST") && !directionLetter.equals("SOUTH")
                    && !directionLetter.equals("N") && !directionLetter.equals("W")
                    && !directionLetter.equals("E") && !directionLetter.equals("S")){
                return false;
            }

            switch(directionLetter.charAt(0)){
                case 'N': facingDirection = Robot.CompassDirection.NORTH; break;
                case 'W': facingDirection = Robot.CompassDirection.WEST; break;
                case 'E': facingDirection = Robot.CompassDirection.EAST; break;
                case 'S': facingDirection = Robot.CompassDirection.SOUTH; break;
            }

        }catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
            return false;
        }

        robot.setPosX(posX);
        robot.setPosY(posY);
        robot.setFacingDirection(facingDirection);

        return true;
    }
}
