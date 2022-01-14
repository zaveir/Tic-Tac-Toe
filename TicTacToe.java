import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe
{
    private static String[][] board = {{" ", "|", " ", "|", " "},
                                        {"-", "+", "-", "+", "-"},
                                        {" ", "|", " ", "|", " "},
                                        {"-", "+", "-", "+", "-"},
                                        {" ", "|", " ", "|", " "}};
    private static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    private static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static String checkWinner()
    {
        ArrayList<Integer> topRow = new ArrayList<Integer>();
        topRow.add(1);
        topRow.add(2);
        topRow.add(3);

        ArrayList<Integer> midRow = new ArrayList<Integer>();
        midRow.add(4);
        midRow.add(5);
        midRow.add(6);

        ArrayList<Integer> botRow = new ArrayList<Integer>();
        botRow.add(7);
        botRow.add(8);
        botRow.add(9);

        ArrayList<Integer> leftCol = new ArrayList<Integer>();
        leftCol.add(1);
        leftCol.add(4);
        leftCol.add(7);

        ArrayList<Integer> midCol = new ArrayList<Integer>();
        midCol.add(2);
        midCol.add(5);
        midCol.add(8);

        ArrayList<Integer> rightCol = new ArrayList<Integer>();
        rightCol.add(3);
        rightCol.add(6);
        rightCol.add(9);

        ArrayList<Integer> negDiag = new ArrayList<Integer>();
        negDiag.add(1);
        negDiag.add(5);
        negDiag.add(9);

        ArrayList<Integer> posDiag = new ArrayList<Integer>();
        posDiag.add(3);
        posDiag.add(5);
        posDiag.add(7);

        ArrayList<ArrayList<Integer>> winConditions = new ArrayList<ArrayList<Integer>>();
        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(botRow);
        winConditions.add(leftCol);
        winConditions.add(midCol);
        winConditions.add(rightCol);
        winConditions.add(negDiag);
        winConditions.add(posDiag);

        for (ArrayList<Integer> l : winConditions)
        {
            if (playerPositions.containsAll(l))
            {
                return "Congratulations, you won!";
            }
            else if (cpuPositions.containsAll(l))
            {
                return "Sorry, you lost.";
            }
        }

        if (playerPositions.size() + cpuPositions.size() == 9)
        {
            return "Tie.";
        }

        return "";

    }

    public static void main(String[] args)
    {
        printBoard();

        while (true)
        {
            // player moves
            Scanner s = new Scanner(System.in);
            System.out.println("Enter your move (1-9)");
            int playerPos = s.nextInt();

            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos))
            {
                System.out.println("Invalid move! Enter an open position.");
                playerPos = s.nextInt();
            }

            makeMove(playerPos, "player");
            String result = checkWinner();
            if (result.length() > 0)
            {
                System.out.println(result);
                printBoard();
                break;
            }
            
            // CPU moves
            int cpuPos = (int) (9 * Math.random()) + 1;

            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos))
            {
                cpuPos = (int) (9 * Math.random()) + 1;
            }

            makeMove(cpuPos, "cpu");
            result = checkWinner();
            if (result.length() > 0)
            {
                System.out.println(result);
                printBoard();
                break;
            }

            printBoard();
        }

    }

    public static void printBoard()
    {
        
        for (String[] row : board)
        {
            for (String symbol : row)
            {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

    public static void makeMove(int pos, String user)
    {
        String symbol = " ";

        if (user.equals("player"))
        {
            symbol = "X";
            playerPositions.add(pos);
        }
        else if (user.equals("cpu"))
        {
            symbol = "O";
            cpuPositions.add(pos);
        }

        switch(pos) 
        {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;
        }
    }
}
