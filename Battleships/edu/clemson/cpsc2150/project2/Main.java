package edu.clemson.cpsc2150.project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Owner on 9/20/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException{
        //setting up variables for the game
        //Grid ocean1 = new ArrayGrid();
        Grid ocean1 = new BoundedSetGrid();
        ocean1.setGridDimensions(10, 10);

        Grid ocean2 = new BoundedSetGrid();
        ocean2.setGridDimensions(10, 10);

        Status hitRegister;

        Ship[] fleet1 = new Ship[5];
        fleet1[0] = new ShipImpl(ShipType.CARRIER);
        fleet1[1] = new ShipImpl(ShipType.BATTLESHIP);
        fleet1[2] = new ShipImpl(ShipType.SUBMARINE);
        fleet1[3] = new ShipImpl(ShipType.CRUISER);
        fleet1[4] = new ShipImpl(ShipType.DESTROYER);

        Ship[] fleet2 = new Ship[5];
        fleet2[0] = new ShipImpl(ShipType.CARRIER);
        fleet2[1] = new ShipImpl(ShipType.BATTLESHIP);
        fleet2[2] = new ShipImpl(ShipType.SUBMARINE);
        fleet2[3] = new ShipImpl(ShipType.CRUISER);
        fleet2[4] = new ShipImpl(ShipType.DESTROYER);


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String line;
        String[] numStrings;
        Coordinate coordinate = new Coordinate(0, 0);
        //int x = 0;
        //int y = 0;
        Direction direction = Direction.RIGHT;
        boolean incorrectInput;
        boolean running = true;




        //PLAYER 1 PLACING SHIPS
        System.out.println("PLAYER 1 TURN:");
        for (int i = 0; i < fleet1.length; ++i) {
            incorrectInput = true;

            while (incorrectInput) {
                ocean1.displayGrid(true);

                System.out.print("Place your " + fleet1[i] + ": ");
                line = reader.readLine();
                numStrings = line.split(", ");
                coordinate.row = Integer.parseInt(numStrings[1]);
                coordinate.column = Integer.parseInt(numStrings[0]);

                System.out.println("");
                System.out.print("Choose direction(d/r): ");
                line = reader.readLine();

                if (line.equals("d"))
                    direction = Direction.DOWN;
                else if (line.equals("r"))
                    direction = Direction.RIGHT;

                fleet1[i].setCoordinates(coordinate, direction);
                if (ocean1.isConflictingShipPlacement(fleet1[i]))
                    incorrectInput = true;
                else {
                    incorrectInput = false;
                    ocean1.placeShip(fleet1[i]);
                    System.out.println("");
                }
            }
        }

        //PLAYER 2 PLACING SHIPS
        System.out.println("PLAYER 2 TURN:");
        for (int i = 0; i < fleet2.length; ++i) {

            incorrectInput = true;

            while (incorrectInput) {
                ocean2.displayGrid(true);
                System.out.print("Place your " + fleet2[i] + ": ");

                line = reader.readLine();
                numStrings = line.split(", ");

                coordinate.row = Integer.parseInt(numStrings[1]);
                coordinate.column = Integer.parseInt(numStrings[0]);

                System.out.println("");
                System.out.print("Choose direction(d/r): ");
                line = reader.readLine();

                if (line.equals("d"))
                    direction = Direction.DOWN;
                else if (line.equals("r"))
                    direction = Direction.RIGHT;
                fleet2[i].setCoordinates(coordinate, direction);
                if (ocean2.isConflictingShipPlacement(fleet2[i]))
                {
                    System.out.println("Impossible ship placement, please try again.");
                    incorrectInput = true;
                }
                else {
                    incorrectInput = false;
                    ocean2.placeShip(fleet2[i]);
                    System.out.println("");
                }
            }
        }

        //GAME STARTS
        while (running) {
            //PLAYER 1 TAKES TURN
            coordinate = new Coordinate(0, 0);
            System.out.println("*************************************");
            ocean2.displayGrid(false);
            System.out.println("*************************************");
            System.out.println("PLAYER 1 TURN: ");
            incorrectInput = true;
            while (incorrectInput) {
                System.out.print("Take a shot: ");
                line = reader.readLine();
                numStrings = line.split(", ");
                coordinate.row = Integer.parseInt(numStrings[1]);
                coordinate.column = Integer.parseInt(numStrings[0]);

                if (ocean2.hasBeenAttempted(coordinate)) {
                    System.out.println("That position has already been selected. Please choose another");
                    incorrectInput = true;
                }
                else {
                    incorrectInput = false;
                }
            }

            System.out.println(coordinate);
            hitRegister = ocean2.shoot(coordinate);


            //output based on the shot just placed
            if (hitRegister == Status.MISS)
                System.out.println("Miss!");

            else if (hitRegister == Status.HIT)
                System.out.println("Hit!");

            else if (hitRegister == Status.SUNK) {
                    System.out.println("Hit!");
                    System.out.print("Player 2's " + ocean2.getLastSunkShip()+ " has sunk!");
                    System.out.println("");
                }
                //testing for end of game

                running = !ocean2.isGameOver();
                if (!running) {
                    System.out.println("Player 1 wins!");
                    ocean1.displayGrid(true);
                }


            //PLAYER 2 TAKES TURN
            if(running) {
                coordinate = new Coordinate(0, 0);
                System.out.println("*************************************");
                ocean1.displayGrid(false);
                System.out.println("*************************************");
                System.out.println("PLAYER 2 TURN: ");
                incorrectInput = true;
                while (incorrectInput) {
                    System.out.print("Take a shot: ");
                    line = reader.readLine();
                    numStrings = line.split(", ");
                    coordinate.row = Integer.parseInt(numStrings[1]);
                    coordinate.column = Integer.parseInt(numStrings[0]);

                    if (ocean1.hasBeenAttempted(coordinate)) {
                        System.out.println("That position has already been selected. Please choose another");
                        incorrectInput = true;
                    }
                    else {
                        incorrectInput = false;
                    }
                }

                System.out.println(coordinate);
                hitRegister = ocean1.shoot(coordinate);


                //output based on the shot just placed
                if (hitRegister == Status.MISS)
                    System.out.println("Miss!");
                else if (hitRegister == Status.HIT)
                    System.out.println("Hit!");
                else if (hitRegister == Status.SUNK) {
                    System.out.println("Hit!");
                    System.out.print("Player 1's " + ocean1.getLastSunkShip() + " has sunk!");
                    System.out.println("");
                }

                //testing for end of game
                running = !ocean1.isGameOver();
                if (!running) {
                    System.out.println("Player 2 wins!");
                    ocean2.displayGrid(true);
                }

            }
        }
        //Game Ends
        System.out.println("Game Over.");
    }
}
