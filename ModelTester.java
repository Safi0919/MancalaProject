import java.util.Scanner;

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
