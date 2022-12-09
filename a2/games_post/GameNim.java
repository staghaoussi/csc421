import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
// Sylvain Taghaoussi

public class GameNim extends Game{
    
    // assign NimState to object instance
    public GameNim(){
        this.currentState = new NimState();
    }

    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0; 

    // implement following functions
    // isWinState
    // isStuckState
    // getSuccessors
    // eval

    public boolean isWinState(State state){
        // cast to NimState, check if coins == 1, if so then win state!
        NimState nim_state = (NimState) state;

        return (nim_state.coins==1);
    }

    public boolean isStuckState(State state){
        // since the players have the option of picking 1,2,3 coins
        // there will never be a state where the player has no option
        // therefore it is impossible to get "stuck"
        return false;
    }

    // this function retrieves the "children" or successors states, first check for win state
    public Set<State> getSuccessors(State state){
        
        // check if winstate
        if(isWinState(state)){
            return null;
        } 

        // use Hash set data structure so we can store all possible successor states, find them then return
        else {
        
            Set<State> suc_states = new HashSet<State>();
            NimState cur_nim = (NimState) state;

            // loop from 1-3, for all possible state selections
            int i = 1;
            while(i < 4){
                NimState newNimState = new NimState(cur_nim);
                newNimState.player = (newNimState.player == 1 ? 0 : 1);
                newNimState.coins = newNimState.coins - i;
                suc_states.add(newNimState);
                i += 1;
            }

            return suc_states;
        }
        
    }

    // copied function from gameTicTacToe.java, eval
    public double eval(State state) 
    {   
    	if(isWinState(state)) {
    		//player who made last move
    		int previous_player = (state.player==0 ? 1 : 0);
    	
	    	if (previous_player==0) //computer wins
	            return WinningScore;
	    	else //human wins
	            return LosingScore;
    	}

        return NeutralScore;
    }


    // function that "runs" the game, ie collects input from human and feeds it to appropriate functions
    public static void main(String[] args) throws Exception {

        // initialize game
        Game game = new GameNim();
        Search search = new Search(game);
        int depth = 13;

        //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            NimState nextState = null;

            switch(game.currentState.player){
                // Human
                case 1:
                    // get human's move
                    System.out.println("Human's turn: Type amount of stones taken...(1-3)?");
                    int val = Integer.parseInt( in.readLine());
                    // detect valid move, only allow move to process once move is valid
                    while(1>val || val>3) {
                        System.out.println("Human's turn: you can only take (1-3) stones...");
                        System.out.println("Invalid entry -> " + val);
                        val = Integer.parseInt( in.readLine());
                    }
                    // process move
                    nextState = new NimState((NimState) game.currentState);
                    nextState.player = 1;
                    nextState.coins = nextState.coins - val;
                    System.out.println("Human's State: \n" + nextState);
                break;
                
                // Computer
                case 0:
                    // conduct process to find best successor state, assign it to nextState
                    nextState = new NimState((NimState) search.bestSuccessorState(depth));
                    nextState.player = 0;
                    System.out.println("Computer's State: \n" + nextState);
                break;
                
                
            }

            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player==0 ? 1 : 0);
            
            //Who wins?
            if ( game.isWinState(game.currentState) ) {
            
            	if (game.currentState.player == 1) //i.e. last move was by the computer
            		System.out.println("Computer wins!");
            	else
            		System.out.println("You win!");
            	break;
            }
            
            // check stuck state
            if ( game.isStuckState(game.currentState) ) { 
            	System.out.println("Cat's game!");
            	break;
            }
            
            // System.out.println("player next state -> " + nextState.player);

        }
    }
}
