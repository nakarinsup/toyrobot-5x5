public class Robot {

    private int posX;
    private int posY;

    public enum CompassDirection {
        NORTH,
        EAST,
        WEST,
        SOUTH
    }

    private CompassDirection facingDirection;


    public Robot(int posX, int posY, CompassDirection facingDirection) {
        this.posX = posX;
        this.posY = posY;
        this.facingDirection = facingDirection;
    }

    public CompassDirection getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(CompassDirection facingDirection) {
        this.facingDirection = facingDirection;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * move forward according to the current facing direction
     *
     * @return false if cannot move
     */
    public boolean moveForward() {
        return RobotUtility.moveRobot(this);
    }

    /**
     * let the robot turn left, facing direction will be changed accordingly
     */
    public void turnLeft() {
        RobotUtility.turnLeftRobot(this);
    }

    /**
     * let the robot turn right, facing direction will be changed accordingly
     */
    public void turnRight() {
        RobotUtility.turnRightRobot(this);
    }
}
