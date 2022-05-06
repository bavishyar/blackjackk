// Bavishya Rajasekar 
// March 23, 2022
// Black Jack 
// Period 4 

/*
  The program uses aspects such as Strings, Objects, Scanners and Arrays to Make crads from numbers, Randomize the card order, and Remove used cards from the deck to create the Black Jack card game. 
*/

import java.util.Random;
import java.util.Scanner;


public class BlackJack {


    static final char[] SUITES = {'\u2660','\u2764','\u2663','\u2666'};
    static final String[] SPECIALS  = {"A","J","Q","K"};
    static final int      BASE      = 9;

    //this finds out the length of Specials variable(4). It adds the base card 9 with four types of Suites; (4 + 9) * 4 = 52.
    static final int DECK_SIZE = (SPECIALS.length + BASE) * SUITES.length;

    static  Player player = new Player();
    static  Player dealer = new Player();

    static Card[] DECK = suffle(create_new_deck(DECK_SIZE));

    static int card_index = 0;

    public static void main(String[] args) {

        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("  Let's play Blackjack!");
        System.out.println("--------------------------------------");
        System.out.println();
        dealer.is_dealer = true;



        while (true){
            bet(player);
            initial_draw_card();
            play();
            System.out.println(" ");
            System.out.println("Do you want to play again? [Y or N]");
            Scanner scn = new Scanner(System.in);
            String decision = scn.nextLine().toUpperCase();
            if(decision.equals("N")){
                System.out.println("Okay,Thanks for playing!!!");
                break;
            }
            reset();
        }


    }

    private static void reset() {
        player = new Player();
        dealer = new Player();
        dealer.is_dealer=true;
        DECK = suffle(create_new_deck(DECK_SIZE));
        card_index = 0;
    }

    public static boolean isBlackJack(int value)
    {
        return value == 21;
    }

    private static void play() {

        player.show_cards();

        // Check Player and dealer is blackjack
        if(isBlackJack(player.total) && isBlackJack(dealer.total)){
            push();
        }else if(isBlackJack(player.total)){
            System.out.println("You have BlackJack");
            win();
        }else if(isBlackJack(dealer.total)){
            dealer.show_cards();
            lose();
        }else{
            Scanner scn = new Scanner(System.in);
            System.out.println("Would you like to Hit or Stand? [H or S]");
            String decision = scn.nextLine().toUpperCase();

            while(decision.equals("H")){
                player.add_card(DECK[card_index++]);
                player.show_cards();

                if(bust(player.total)){
                    System.out.println("You're bust!");
                    lose();
                    break;
                }

                System.out.println("Would you like to Hit or Stand? [H or S]");
                decision = scn.nextLine().toUpperCase();
            }

            if(decision.equals("S")){

                while (dealer.total < 16){
                    dealer.add_card(DECK[card_index++]);
                }
                dealer.show_cards();

                if(dealer.total > 21){
                    win();
                }else{
                    if(dealer.total == player.total){
                        push();
                    }else if(dealer.total > player.total){
                        lose();
                    }else{
                        win();
                    }
                }

            }
        }





    }

    private static boolean bust(int value) {

        return value > 21;
    }

    private static void push() {
        System.out.println("It's a push!");
    }

    private static void lose(){
        System.out.println("Sorry, you lose!");
    }

    public static void win() {
        System.out.println("Congratulations, you win!");
    }

    public static void bet(Player player){
        Scanner scn = new Scanner(System.in);
        System.out.println("How much you want to bet?");
        player.bet_amount = scn.nextInt();

    }

    public static void initial_draw_card(){
        // 2 Cards for player
        player.add_card(DECK[card_index++]);
        player.add_card(DECK[card_index++]);
        // 2 Cards for Dealer
        dealer.add_card(DECK[card_index++]);
        dealer.add_card(DECK[card_index++]);
    }


    /*
     * This function create a new deck with sequence value
     */
    public static Card[] create_new_deck(int size) {
        // Create String Array for Deck Size
        Card[] deck = new Card[size];

        // Creating randome card and assign to each deck index.
        for(int i = 0; i < size; i++){
            Card card = makeCard(i);
            deck[i] = card;

        }

        System.out.println();
        System.out.println("THE NEW DECK OF CARDS ARE CREATED!");
        return deck;
    }

    /*
     * This function creates the card for each suite and assing the values.
     */

    public static Card makeCard(int seed){
        // Create Card index [0-12]
        int card_index = seed % (BASE + SPECIALS.length);
        // Create Suit Index [0-3]
        int suits_index = seed / (BASE + SUITES.length);

        String name = "";
        int val = 0;
        // If Card Index is 0 or 11, 12, 13 then consider as special card.
        if(card_index == 0){
            name = SPECIALS[card_index];
            val = 11;
        }else if(card_index > BASE){
            name =  SPECIALS[card_index-BASE];
            val = 10;
        }else{
            name = "" + (card_index + 1);
            val = (card_index + 1);
        }
        String card_name = SUITES[suits_index] + name;
        return new Card(val, card_name);
    }


    /*
     This function used to suffle the deck of cards using Random function and using temp variable to swap the cards.
    */
    public static Card[] suffle(Card[] deck){
        Random SHUFFLER  = new Random();
        for(int i=0; i < deck.length; i++){

            Card card1 = deck[i];
            Card temp = card1;
            int random_number = SHUFFLER.nextInt(deck.length);
            deck[i] = deck[random_number];
            deck[random_number] = temp;
        }

        // for(int i =0; i < deck.length; i++){
        //     System.out.println(i + " ----> " +deck[i]);
        // }
        return deck;


    }


}

