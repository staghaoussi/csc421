public class NimState extends State{
    public int coins;      // 13 coins max

    public String toString(){
        return "Coins left: " + this.coins;
    }

    public NimState(){
        this.coins = 13;
        this.player = 1; 
    }

    public NimState(NimState state){
        this.coins = state.coins;
        this.player = state.player;
    }

}