public class Card {
    int value;
    String suit;


    public Card(int v, String s){
        value = v;
        suit = s;
    }


    public String display(){
        return this.suit;
    }


}
