import java.util.Scanner;
//list of moves for game to end: A1, B3, B6, A1, B5, A1, B6, B1, A6, B4, A1, B6, B2, A1, B6, B5, B6, B1, A2, B3, A5, B6, B5, A6, A4, B6, B4, B6, B3, A6, A5, Player B wins
public class ModelTester {
    public static void main(String[] args){
        Model model = new Model(4);
        Scanner scanner = new Scanner(System.in);
        String input; int pitChosen;
        model.printBoard();
        while(model.getEndState() == 0){
            input = scanner.nextLine();
            if(input.equals("undo")){
                if(!model.undo()){
                    System.out.println("Illegal undo");
                }

            }
            else{
                if(input.charAt(0) == 'A'){
                    pitChosen = input.charAt(1) - '0' - 1;
                }
                else{
                    pitChosen = input.charAt(1) - '0' + 7 - 1;
                }
                if(!model.distributeStones(pitChosen)){
                    System.out.println("Illegal Move");
                }
            }
            model.printPreviousBoard();
            model.printBoard();
        
        }
        scanner.close();
        if(model.getEndState() == 'C'){
            System.out.println("Tie");
        }
        else{
            System.out.println("Player " + model.getEndState() + " won!");
        }
    }
}

/*
 * game tested:
 * 
 * Current Board: (Undo Count = 0 Move Count = 0)
Player A's Turn
  4 4 4 4 4 4 
0                 0
  4 4 4 4 4 4 
A1
Previous Board:
Player A's Turn
  4 4 4 4 4 4 
0                 0
  4 4 4 4 4 4 
Current Board: (Undo Count = 0 Move Count = 1)
Player B's Turn
  4 4 4 4 4 4 
0                 0
  0 5 5 5 5 4 
B3
Previous Board:
Player B's Turn
  4 4 4 4 4 4 
0                 0
  0 5 5 5 5 4 
Current Board: (Undo Count = 0 Move Count = 2)
Player B's Turn
  5 5 5 0 4 4 
1                 0
  0 5 5 5 5 4 
B6
Previous Board:
Player B's Turn
  5 5 5 0 4 4 
1                 0
  0 5 5 5 5 4 
Current Board: (Undo Count = 0 Move Count = 3)
Player A's Turn
  0 5 5 0 4 4 
2                 0
  1 6 6 6 5 4 
A1
Previous Board:
Player A's Turn
  0 5 5 0 4 4 
2                 0
  1 6 6 6 5 4 
Current Board: (Undo Count = 0 Move Count = 4)
Player B's Turn
  0 5 5 0 4 4 
2                 0
  0 7 6 6 5 4 
B5
Previous Board:
Player B's Turn
  0 5 5 0 4 4 
2                 0
  0 7 6 6 5 4 
Current Board: (Undo Count = 0 Move Count = 5)
Player A's Turn
  1 0 5 0 4 4 
3                 0
  1 8 7 6 5 4 
A1
Previous Board:
Player A's Turn
  1 0 5 0 4 4 
3                 0
  1 8 7 6 5 4 
Current Board: (Undo Count = 0 Move Count = 6)
Player B's Turn
  1 0 5 0 4 4 
3                 0
  0 9 7 6 5 4 
B6
Previous Board:
Player B's Turn
  1 0 5 0 4 4 
3                 0
  0 9 7 6 5 4 
Current Board: (Undo Count = 0 Move Count = 7)
Player B's Turn
  0 0 5 0 4 4 
4                 0
  0 9 7 6 5 4 
B1
Previous Board:
Player B's Turn
  0 0 5 0 4 4 
4                 0
  0 9 7 6 5 4 
Current Board: (Undo Count = 0 Move Count = 8)
Player A's Turn
  0 0 6 1 5 0 
14                 0
  0 0 7 6 5 4 
A6
Previous Board:
Player A's Turn
  0 0 6 1 5 0 
14                 0
  0 0 7 6 5 4 
Current Board: (Undo Count = 0 Move Count = 9)
Player B's Turn
  0 0 6 2 6 1 
14                 1
  0 0 7 6 5 0 
B4
Previous Board:
Player B's Turn
  0 0 6 2 6 1 
14                 1
  0 0 7 6 5 0 
Current Board: (Undo Count = 0 Move Count = 10)
Player A's Turn
  1 1 0 2 6 1 
15                 1
  1 1 8 6 5 0 
A1
Previous Board:
Player A's Turn
  1 1 0 2 6 1 
15                 1
  1 1 8 6 5 0 
Current Board: (Undo Count = 0 Move Count = 11)
Player B's Turn
  1 1 0 2 6 1 
15                 1
  0 2 8 6 5 0 
B6
Previous Board:
Player B's Turn
  1 1 0 2 6 1 
15                 1
  0 2 8 6 5 0 
Current Board: (Undo Count = 0 Move Count = 12)
Player B's Turn
  0 1 0 2 6 1 
16                 1
  0 2 8 6 5 0 
B2
Previous Board:
Player B's Turn
  0 1 0 2 6 1 
16                 1
  0 2 8 6 5 0 
Current Board: (Undo Count = 0 Move Count = 13)
Player A's Turn
  1 2 1 3 0 1 
17                 1
  1 2 8 6 5 0 
A1
Previous Board:
Player A's Turn
  1 2 1 3 0 1 
17                 1
  1 2 8 6 5 0 
Current Board: (Undo Count = 0 Move Count = 14)
Player B's Turn
  1 2 1 3 0 1 
17                 1
  0 3 8 6 5 0 
B6
Previous Board:
Player B's Turn
  1 2 1 3 0 1 
17                 1
  0 3 8 6 5 0 
Current Board: (Undo Count = 0 Move Count = 15)
Player B's Turn
  0 2 1 3 0 1 
18                 1
  0 3 8 6 5 0 
B5
Previous Board:
Player B's Turn
  0 2 1 3 0 1 
18                 1
  0 3 8 6 5 0 
Current Board: (Undo Count = 0 Move Count = 16)
Player B's Turn
  1 0 1 3 0 1 
19                 1
  0 3 8 6 5 0 
B6
Previous Board:
Player B's Turn
  1 0 1 3 0 1 
19                 1
  0 3 8 6 5 0 
Current Board: (Undo Count = 0 Move Count = 17)
Player B's Turn
  0 0 1 3 0 1 
20                 1
  0 3 8 6 5 0 
B1
Previous Board:
Player B's Turn
  0 0 1 3 0 1 
20                 1
  0 3 8 6 5 0 
Current Board: (Undo Count = 0 Move Count = 18)
Player A's Turn
  0 0 1 3 0 0 
26                 1
  0 3 8 6 0 0 
A2
Previous Board:
Player A's Turn
  0 0 1 3 0 0 
26                 1
  0 3 8 6 0 0 
Current Board: (Undo Count = 0 Move Count = 19)
Player B's Turn
  0 0 1 3 0 0 
26                 1
  0 0 9 7 1 0 
B3
Previous Board:
Player B's Turn
  0 0 1 3 0 0 
26                 1
  0 0 9 7 1 0 
Current Board: (Undo Count = 0 Move Count = 20)
Player A's Turn
  1 1 2 0 0 0 
26                 1
  0 0 9 7 1 0 
A5
Previous Board:
Player A's Turn
  1 1 2 0 0 0 
26                 1
  0 0 9 7 1 0 
Current Board: (Undo Count = 0 Move Count = 21)
Player B's Turn
  1 1 2 0 0 0 
26                 1
  0 0 9 7 0 1 
B6
Previous Board:
Player B's Turn
  1 1 2 0 0 0 
26                 1
  0 0 9 7 0 1 
Current Board: (Undo Count = 0 Move Count = 22)
Player B's Turn
  0 1 2 0 0 0 
27                 1
  0 0 9 7 0 1 
B5
Previous Board:
Player B's Turn
  0 1 2 0 0 0 
27                 1
  0 0 9 7 0 1 
Current Board: (Undo Count = 0 Move Count = 23)
Player A's Turn
  1 0 2 0 0 0 
27                 1
  0 0 9 7 0 1 
A6
Previous Board:
Player A's Turn
  1 0 2 0 0 0 
27                 1
  0 0 9 7 0 1 
Current Board: (Undo Count = 0 Move Count = 24)
Player A's Turn
  1 0 2 0 0 0 
27                 2
  0 0 9 7 0 0 
A4
Previous Board:
Player A's Turn
  1 0 2 0 0 0 
27                 2
  0 0 9 7 0 0 
Current Board: (Undo Count = 0 Move Count = 25)
Player B's Turn
  1 0 3 1 1 1 
27                 3
  0 0 9 0 1 1 
B6
Previous Board:
Player B's Turn
  1 0 3 1 1 1 
27                 3
  0 0 9 0 1 1 
Current Board: (Undo Count = 0 Move Count = 26)
Player B's Turn
  0 0 3 1 1 1 
28                 3
  0 0 9 0 1 1 
B4
Previous Board:
Player B's Turn
  0 0 3 1 1 1 
28                 3
  0 0 9 0 1 1 
Current Board: (Undo Count = 0 Move Count = 27)
Player B's Turn
  1 1 0 1 1 1 
29                 3
  0 0 9 0 1 1 
B6
Previous Board:
Player B's Turn
  1 1 0 1 1 1 
29                 3
  0 0 9 0 1 1 
Current Board: (Undo Count = 0 Move Count = 28)
Player B's Turn
  0 1 0 1 1 1 
30                 3
  0 0 9 0 1 1 
B3
Previous Board:
Player B's Turn
  0 1 0 1 1 1 
30                 3
  0 0 9 0 1 1 
Current Board: (Undo Count = 0 Move Count = 29)
Player A's Turn
  0 1 0 0 1 1 
40                 3
  0 0 0 0 1 1 
A6
Previous Board:
Player A's Turn
  0 1 0 0 1 1 
40                 3
  0 0 0 0 1 1 
Current Board: (Undo Count = 0 Move Count = 30)
Player A's Turn
  0 1 0 0 1 1 
40                 4
  0 0 0 0 1 0 
A5
Previous Board:
Player A's Turn
  0 1 0 0 1 1 
40                 4
  0 0 0 0 1 0 
Current Board: (Undo Count = 0 Move Count = 31)
Player B's Turn
  0 0 0 0 0 0 
42                 6
  0 0 0 0 0 0 
Player B won!
 * 
 */