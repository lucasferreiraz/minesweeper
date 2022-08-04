package models;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import exceptions.ExitException;
import exceptions.ExplosionException;

public class ConsoleBoard {

    private Board board;
    private Scanner input = new Scanner(System.in);

    public ConsoleBoard(Board board) {
        this.board = board;

        runGame();
    }

    private void runGame() {
        try {
            boolean switchContinue = true;

            while(switchContinue){
                gameCicle();

                System.out.println("Another match? (Y/n) ");
                String res = input.nextLine();
    
                if ("n".equalsIgnoreCase(res)) {
                    switchContinue = false;
                } else {
                    board.reboot();
                }
            }

        } catch (ExitException e) {
            System.out.println("Goodbye!");
        } finally {
            input.close();
        }
    }

    private void gameCicle() {
        try {
            
            while(!board.goalAchieved()){
                System.out.println(board);
                String typed = captureTypedValue("Insert (x, y): ");

                Iterator<Integer> xy = Arrays.stream(typed.split(","))
                                                .map(e -> Integer.parseInt(e.trim()))
                                                .iterator();
                
                typed = captureTypedValue("1 - Open or 2 - (Un)mark: ");
                if ("1".equals(typed)) {
                    board.open(xy.next(), xy.next());
                } else if("2".equals(typed)){
                    board.switchMarking(xy.next(), xy.next());
                }

            }

            System.out.println("You WIN! :D");
        } catch (ExplosionException e) {
            System.out.println("You lost. :(");
        }
    }

    private String captureTypedValue(String text) {
        System.out.print(text);
        String typed = input.nextLine();

        if ("exit".equalsIgnoreCase(typed)) {
            throw new ExitException();
        }

        return typed;

    }   

}
