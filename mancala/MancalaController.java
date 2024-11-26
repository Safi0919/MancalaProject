package mancala;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MancalaController {
    private MancalaModel model;
    private MancalaView view;

    public MancalaController(MancalaModel model, MancalaView view) {
        this.model = model;
        this.view = view;

        // Event listeners for pits and undo buttons
        view.addPitListeners(new PitClickListener());
        view.addUndoButtonListener(new UndoButtonListener());
    }

    // Class to handle pit clicks
    private class PitClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pitId = e.getActionCommand(); // Example: "A1", "B6"

            boolean validMove = model.makeMove(pitId);

            // Update the move if the move was valid
            if (validMove) {
                view.updateBoard(model.getBoardState());
                view.updateCurrentPlayer(model.getCurrentPlayer());

                // Check if the game is over
                if (model.isGameOver()) {
                    view.displayWinner(model.getWinner());
                }
            } else {
                view.showErrorMessage("Invalid move! Try again.");
            }
        }
    }

    // Class that handles button clicks for undo
    private class UndoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean undoSuccessful = model.undoMove();

            if (undoSuccessful) {
                view.updateBoard(model.getBoardState());
                view.updateCurrentPlayer(model.getCurrentPlayer());
            } else {
                view.showErrorMessage("No more undos available!");
            }
        }
    }
}
