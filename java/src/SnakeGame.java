public class SnakeGame {
    /*Attributes*/
    private int length; /*Stores the length of the snake.*/
    private boolean[][] game; /*Stores the final game state.*/
    private int[] headPosition; /*Stores the location of the snake's head.*/
    private static int exhaustiveChecks; /*Counts # of positions checked using exhaustive searching.*/
    private static int recursiveChecks; /*Counts # of positions checked using recursive searching.*/
    private int neighbors; /*Counts how many neighbors cell has*/
    private int[] tailFound; /*an array that holds the position(x,y) of the tail and the snake length.*/
    private int[] foundWork; /*an array that is a copy of tailFound but used in the findTailRecursive() method.*/
    private int[] a; /* array that stores the position of the current cell being passed in the findTailRecursive() method.*/
    private int[] b; /* array that stores the position of the previous cell being passed in the findTailRecursive() method.*/

    /*Default Constructor*/
    public SnakeGame(){
        this.game = new boolean[1][1]; /* Initializing a 1 x 1 board. */
    }

    /*Constructor*/
    public SnakeGame(boolean[][] array, int x, int y){
        headPosition = new int[2];
        this.headPosition[0] = x;
        this.headPosition[1] = y;
        tailFound = new int[3];
        foundWork = new int[3];
        a = new int[2];
        b = new int[2];

        length = 0; /*starting length is one due to head position already existing*/

        this.game = new boolean[array.length][array[0].length];

        for(int i = 0; i < array.length; i++){
            for(int k = 0; k < array[0].length; k++){
                this.game[i][k] = array[i][k]; /*Populating initial array's contents to the board game.*/
            }
        }
    }

    /*Getters*/
    public static int getRecursiveChecks(){ /*Gets the current state of the recursiveChecks counter.*/
        return recursiveChecks;
    }

    public static int getExhaustiveChecks(){ /*Gets the current state of the exhaustiveChecks counter.*/
        return exhaustiveChecks;
    }
    /*Methods*/
    private void resetCounters(){  /* resets both the exhaustiveChecks and recursiveChecks counters to 0.*/
        exhaustiveChecks = 0;
        recursiveChecks = 0;
        length = 0;
    }

    public int[] findTailExhaustive(){
        resetCounters();

        /*Start checking each cell of the board*/
        for(int i = 0; i < this.game.length; i++){
            for(int k = 0; k < this.game[0].length; k++){
                neighbors = 0; /*neighbors resets to 0 for each cell being checked*/
                exhaustiveChecks++; /*increases due to cell needing to be checked*/

                 /*Is this cell true?*/
                if(this.game[i][k] == true){
                    /*check if it has one or more neighbors*/
                    if(k+1 < this.game[0].length){ /* RIGHT SQUARE*/
                        if(this.game[i][k+1] == true){
                            neighbors++;
                        }
                    }
                    if(k-1 >= 0){ /*LEFT SQUARE*/
                        if(this.game[i][k-1] == true){
                            neighbors++;
                        }
                    }
                    if(i+1 < this.game.length){ /*BOTTOM SQUARE*/
                        if(this.game[i+1][k] == true){
                            neighbors++;
                        }
                    }
                    if(i-1 >= 0){ /*TOP SQUARE*/
                        if(this.game[i-1][k] == true){
                            neighbors++;
                        }
                    }


     /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
                    /*If the cell has zero neighbors then the snake is the head AND tail itself and not valid.*/
                    if(neighbors == 0){
                        System.out.println("This snake is not valid.");
                        tailFound[0] = headPosition[0];
                        tailFound[1] = headPosition[1];
                        length = 1;
                        tailFound[2] = length;
                        return tailFound;
                    }

                    if(neighbors >= 1){
                        length++;
                    }
                    /*If the cell has 1 neighbor check if its either the head or tail*/
                    if(i == headPosition[0] && k == headPosition[1] && neighbors == 1){
                        continue; /*it is not the tail*/
                    }
                    if(neighbors == 1){ /*update TailFound with tail position in array.*/
                        tailFound[0] = i;
                        tailFound[1] = k;
                    }
                    /* If the cell has 2 or more neighbors it is neither the head or tail, continue on to next cell.*/
                    if(neighbors == 2){
                        continue;
                    }


                }else{
                    /*If cell is false, move on to the next cell*/
                    continue;
                }
            }
        }
        tailFound[2] = length; /*update TailFound with length of snake after search is complete.*/
        System.out.println("Tail is at " + tailFound[0] + "," + tailFound[1]);
        System.out.println("Number of exhaustive checks: " + getExhaustiveChecks());
        return tailFound;
    }


    public int[] findTailRecursive(){
        resetCounters();
        return  findTailRecursive(headPosition, headPosition);
    }

    private int[] findTailRecursive(int[] currentPosition, int[] previousPosition){
        recursiveChecks++;
        a = currentPosition;
        b = previousPosition;
        int x = a[0]; /*holding current x position;*/
        int y = a[1]; /*holding current y position;*/
        System.out.println("Check is starting at game[" + x + "]" + "[" + y + "]");
        System.out.println();

        if(x+1 < this.game.length){ //*BOTTOM SQUARE*//*
            if( x+1 == b[0]){
                System.out.println("PASS");
            }
            else if(this.game[x+1][y] == true){
                length++;
                b = new int[]{x,y};
                a = new int[]{x+1, y};
                System.out.println("Previous position is now [" + b[0] + "]" + "[" + b[1] + "]");
                System.out.println("Current position is now [" + a[0] + "]" + "[" + a[1] + "]");
                System.out.println();
                return findTailRecursive(a,b);

            }
        }
        if(y+1 < this.game[x].length && b[1] != y+1){ //* RIGHT SQUARE*//*
            if(this.game[x][y+1] == true){
                length++;
                b = currentPosition;
                a = new int[] {x, y+1};
                System.out.println("Previous position is now [" + b[0] + "]" + "[" + b[1] + "]");
                System.out.println("Current position is now [" + a[0] + "]" + "[" + a[1] + "]");
                System.out.println();
                return findTailRecursive(a,b);
            }
        }
        if(y-1 >= 0 && b[1] != y-1){ //*LEFT SQUARE*//*
            if(this.game[x][y-1] == true){
                length++;
                b = currentPosition;
                a = new int[]{x,y-1};
                System.out.println("Previous position is now [" + b[0] + "]" + "[" + b[1] + "]");
                System.out.println("Current position is now [" + a[0] + "]" + "[" + a[1] + "]");
                System.out.println();
                return findTailRecursive(a,b);
            }
        }
        if(x-1 >= 0){ //*TOP SQUARE*//*
            if(this.game[x-1][y] == true){
                length++;
                b = currentPosition;
                a = new int[]{x-1, y};
                System.out.println("Previous position is now [" + b[0] + "]" + "[" + b[1] + "]");
                System.out.println("Current position is now [" + a[0] + "]" + "[" + a[1] + "]");
                System.out.println();
                return findTailRecursive(a,b);
            }
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        /* if all cases for neighbors fail, then it IS the tail.*/
        length++;

        System.out.println("Number of recursive checks: " + getRecursiveChecks());
        if(getRecursiveChecks() == 1){
            System.out.println("The snake is not valid.");
        }
        foundWork = new int[]{x,y,length};
        System.out.println("Tail is at " + foundWork[0] + "," + foundWork[1]);

        return  foundWork;

    }

}

