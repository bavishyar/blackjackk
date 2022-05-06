public class Player {

    Card[] cards = new Card[10];
    int number_of_cards = 0;
    int total;
    int bet_amount;
    boolean is_dealer = false;

    public void add_card(Card card){
        cards[number_of_cards] = card;
        total = total + card.value;
        number_of_cards = number_of_cards + 1;
    }

    public void show_cards(){
        System.out.println("");
        if(is_dealer){
            System.out.println("Dealer Cards");
        }else{
            System.out.print("Player Cards :");
        }

        System.out.print("[");
        for(int i=0; i<number_of_cards; i++){
            System.out.print(cards[i].suit+" ");
        }
        System.out.print("]");
        System.out.print(" Total " + total);
        System.out.println(" ");
    }
}
