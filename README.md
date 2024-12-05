# Project 5: Not-From-Scratch Nonogram Player

> Programming isn't about what you know; it's about what you can figure out.
> 
> &mdash;Chris Pine, in *[Learn to Program](https://pine.fm/LearnToProgram/)*

This program creates [nonograms](https://en.wikipedia.org/wiki/Nonogram) from [binary images](https://en.wikipedia.org/wiki/Binary_image).
and allows to play those nonograms. 

The program can run with zero or one command line argument. In both cases, a
default puzzle file is loaded. You will need to modify your
**Run Configuration** arguments to test out different cell sizes.

Running the program with no arguments opens the default puzzle with a cell
size of 30:

```console
java Main
```

![No Argument Nonogram++](img/nonogram-no-args.PNG)

Running the program with a single argument of 50 opens the default puzzle with a
cell size of 50:

```console
java Main 50
```

![One-Arg Argument Nonogram++](img/nonogram-arg-50.PNG)

Furthermore, you can load in different puzzles by clicking the **Load** button.
You can load in puzzles using the format defined in Project 4.<sup id="a1">[1](#f1)</sup>
For testing, we have included a few minimal puzzle files that do not contain
grid values at the bottom of the file (i.e., they only include the row count,
column count, row projections, and column projections).

Note that the background color of the clues in the first and last rows and the
first and last columns is midnight green instead of white.
This indicates that the states of the cells in these rows and columns solve the
clue numbers. In this case, the numbers are 0, so they are trivially solved by
the initial (empty) states.

Left-clicking on an empty cell changes its state to filled, which is represented
by the color blue. Right-clicking changes the state to marked, which is
represented by a red X. Marked cells are treated as empty when the game checks
whether a row or column is solved, but the marks can be used by the player to
label cells that they know are empty.

The next image shows the puzzle after changing the states of 8 cells to filled.
The new states solve the clues in the third row and fourth column, so the
background color of these clues changed to green. The leftmost filled cell
contradicts the clue in the first column, so its background color changed to
white.

The cells in the bottom row and right column have all been marked. This does not affect 
the clues, but it helps to show the constraints on the other cells.
For instance, if we also mark the cells in the top row and left column, it
becomes clear that the cells in the sixth row must all be filled.

![partly solved puzzle](img/partly-solved-puzzle.png)

The next image shows the puzzle with all but two of the clues solved.
Filling the cell in the eighth row and the eighth column will solve the puzzle.
Note that some of the filled cells in the previous image were incorrect, even though the background color of the corresponding clues was green.
Individual row and column clues usually have multiple solutions, and the background color will turn green for all of them.
To solve the entire puzzle, however, the clues for all the rows and columns must be solved simultaneously.<sup id="a2">[2](#f2)</sup>

![almost solved puzzle](img/almost-solved-puzzle.png)

The final image shows the behavior of the program when the player solves the
puzzle. An [Alert](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/Alert.html)
is displayed to notify the player. All of the marked cells are changed to empty
cells, and the color of the white square in the top-left corner turns green.
Additionally, the player can no longer change the states of the cells.

<img src="img/solve.gif" alt="Puzzle Solve Example" width="425"/>

### Load Button

As previously mentioned, the **Load** button is used to load in a puzzle file.
When a new puzzle is loaded, the grid is resized to accommodate the new puzzle's
width and height. Furthermore, any existing marks are removed.

![Load Button Example](img/load.gif)

### Reset Button

The **Reset** button is used to clear all cells by setting them to the empty
state. If the puzzle was solved, the **Reset** button allows the player to solve
the puzzle again. After resetting a solved puzzle, the player should be able to
change cell states again.

<img src="img/reset.gif" alt="Reset Button Example" width="425"/>


## Program Design

For this project there is a UML diagram.
<img src="uml.png" alt="uml diagram"/>
