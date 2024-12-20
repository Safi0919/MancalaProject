# Mancala Game

This repository contains the implementation of the Mancala game, a two-player strategy board game, using Java Swing for the graphical user interface (GUI). The project follows the Model-View-Controller (MVC) architecture and includes customizable strategies for rendering the game board.

## Features

- **Graphical User Interface**: The game features an interactive GUI built with Java Swing.
- **Strategy Pattern**: Users can select between different board styles (e.g., Black and White, America).
- **Dynamic Rendering**: The game dynamically renders the board, pits, mancala, and labels based on the selected strategy.
- **Game Controls**: Includes options for undoing moves and displaying the winner at the end of the game.

## Project Structure

The project is organized into the following components:

- **Model**: Represents the state and logic of the game, including the distribution of stones and determining the winner.
- **View**: Handles the graphical representation of the game board and user interactions.
- **Controller**: Manages the interaction between the model and the view, handling user input and updating the game state.
- **Strategies**: Defines different styles for rendering the game board.

## Classes Overview

### MancalaView
- Manages the GUI components and renders the game board.
- Provides buttons for strategy selection and pit interactions.
- Displays error messages and the winner of the game.

### MancalaController
- Handles user inputs and updates the game model accordingly.
- Manages the communication between the view and the model.

### Model
- Stores the state of the game, including the number of stones in each pit.
- Determines the game rules and logic.

### Strategy
- Defines the rendering styles for the game board.
- Includes classes such as `BlackAndWhite` and `America`.

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/mancala-game.git
   ```
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
3. Compile and run the `Main` class to start the game.
4. Select a board style and start playing!

## How to Play

1. **Objective**: Capture more stones than your opponent.
2. **Pits and Mancalas**:
   - Each player has 6 pits and a Mancala (store) on their side.
   - Stones are distributed clockwise from the selected pit.
3. **Winning**:
   - The game ends when all pits on one side are empty.
   - The player with the most stones in their Mancala wins.

## Requirements

- Java 8 or higher

## Future Improvements

- Add additional board styles.
- Enhance game logic to support advanced Mancala rules.
- Implement multiplayer support over a network.

## Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request if you have ideas for improving the game.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgments

- Thanks to the team members who contributed to this project.
- Inspired by the traditional Mancala game played worldwide.

