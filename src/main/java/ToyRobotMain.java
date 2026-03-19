import java.util.Scanner;

public class ToyRobotMain {

    public static void toyRobotRun() {

        Robot robot = new Robot(-1, -1, null);
        Scanner scanner = new Scanner(System.in);

        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("Please enter robot command\n");
        strBuffer.append("PLACE x,y,f\n");
        strBuffer.append("MOVE\n");
        strBuffer.append("LEFT\n");
        strBuffer.append("RIGHT\n");
        strBuffer.append("REPORT\n");
        strBuffer.append("q - quit, for exit program\n");

        ScreenDisplay screen = new ScreenDisplay();

        boolean exitProgram = false;
        boolean shouldRefresh = true;

        do {
            if (shouldRefresh) {
                screen.draw(robot);
                System.out.print(strBuffer);
            }

            String command = scanner.nextLine().trim();

            //compare "PLACE " with whitespace afterward is intended for ensure the command must exactly be "PLACE" (ignore case)
            if (command.toUpperCase().startsWith("PLACE ")) {
                if (RobotUtility.placeRobot(command, robot)) {
                    shouldRefresh = true;
                } else {
                    System.out.println("PLACE command invalid, x, y must be 0-4, facing direction can be 'NORTH', 'EAST', 'WEST', 'SOUTH'");
                    System.out.println("Example valid PLACE command : \"PLACE 2,3,EAST\" ");
                    shouldRefresh = false;
                }
            } else if (command.equalsIgnoreCase("REPORT") || command.equalsIgnoreCase("MOVE") ||
                    command.equalsIgnoreCase("LEFT") || command.equalsIgnoreCase("RIGHT")) {

                if (!RobotUtility.isRobotValid(robot)) {
                    System.out.println("PLACE command must be executed first");
                    shouldRefresh = false;
                } else if (command.equalsIgnoreCase("REPORT")) {
                    System.out.println("Robot current position is : " + robot.getPosX() + ", " + robot.getPosY() + ", " + robot.getFacingDirection());
                    shouldRefresh = false;
                } else if (command.equalsIgnoreCase("MOVE")) {
                    boolean moveSuccess = robot.moveForward();
                    if (moveSuccess) {
                        shouldRefresh = true;
                    } else {
                        System.out.println("MOVE command is ignored because robot will fall from table!!!");
                        shouldRefresh = false;
                    }
                } else if (command.equalsIgnoreCase("LEFT")) {
                    robot.turnLeft();
                    shouldRefresh = true;
                } else if (command.equalsIgnoreCase("RIGHT")) {
                    robot.turnRight();
                    shouldRefresh = true;
                }
            } else if (command.equalsIgnoreCase("q") || command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit")) {
                exitProgram = true;
            } else {
                System.out.println("Invalid command");
                shouldRefresh = false;
            }

        } while (!exitProgram);

        scanner.close();

    }


    public static void main(String[] args) {
        toyRobotRun();
    }
}
