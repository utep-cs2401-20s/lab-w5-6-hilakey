public class SnakeGame {
    /*Attributes*/
    private int length; /*Stores the length of the snake.*/
    private boolean[][] game; /*Stores the final game state.*/
    private int[] headPosition; /*Stores the location of the snake's head.*/
    private static int exhaustiveChecks; /*Counts # of positions checked using exhaustive searching.*/
    private static int recursiveChecks; /*Counts # of positions checked using recursive searching.*/
    private int neighbors = 0; /*Counts how many neighbors cell has*/
    private int[] tailFound;

    /*Default Constructor*/
    public SnakeGame(){
        this.game = new boolean[1][1]; /* Initializing a 1 x 1 board. */
    }

    /*Constructor*/
    public SnakeGame(boolean[][] array, int x, int y){
        this.headPosition[0] = x;
        this.headPosition[1] = y;
        /*TODO: INITIALIZE game to array size (not guaranteed a square*/
        tailFound = new int[3];
        length = 1; /*starting length is one due to head position already existing*/

        for(int i = 0; i < array.length; i++){
            for(int j = 0; i < array[0].length; j++){
                game[i][j] = array[i][j]; /*Copying initial array's contents to the board game.*/
            }
        }
    }

    /*Getters*/
    private static int getRecursiveChecks(){ /*Gets the current state of the recursiveChecks counter.*/
        return -1;
    }

    private static int getExhaustiveChecks(){ /*Gets the current state of the exhaustiveChecks counter.*/
        return -1;
    }
    /*Methods*/
    private void resetCounters(){  /* resets both the exhaustiveChecks and recursiveChecks counters to 0.*/
        exhaustiveChecks = 0;
        recursiveChecks = 0;
        length = 0;
    }

    public int[] findTailExhaustive(){
        resetCounters();
        neighbors = 0; /*neighbors resets to 0 for each cell being checked*/

        /*Start checking each cell of the board*/
        for(int i = 0; i < game.length; i++){
            for(int j = 0; j < game[0].length; j++){
                exhaustiveChecks++; /*increases due to cell needing to be checked*/
                 /*Is this cell true?*/
                if(game[i][j] == true){
                    /*check if it has one or more neighbors*/
                    if(j+1 < game[0].length){ /* LEFT SQUARE*/
                        if(game[i][j+1] == true){
                            neighbors++;
                        }
                    }
                    if(j-1 >= 0){ /*RIGHT SQUARE*/
                        if(game[i][j-1] == true){
                            neighbors++;
                        }
                    }
                    if(i+1 < game.length){ /*BOTTOM SQUARE*/
                        if(game[i+1][j] == true){
                            neighbors++;
                        }
                    }
                    if(i-1 >= 0){ /*TOP SQUARE*/
                        if(game[i-1][j] == true){
                            neighbors++;
                        }
                    }
                    if(neighbors >= 1){
                        length++;
                    }
                    /*If the cell has 1 neighbor check if its either the head or tail*/
                    if(i == headPosition[0] && j == headPosition[1] && neighbors == 1){
                        continue; /*it is not the tail*/
                    }
                    if(neighbors == 1){ /*update TailFound with tail position in array.*/
                        tailFound[0] = i;
                        tailFound[1] = j;
                    }
                    /* If the cell has 2 or more neighbors it is neither the head or tail, continue on to next cell.*/
                    if(neighbors >= 2){
                        continue;
                    }

                }else{
                    /*If cell is false, move on to the next cell*/
                    continue;
                }
            }
        }
        tailFound[2] = length; /*update TailFound with length of snake after search is complete.*/
        return tailFound;
    }



}

