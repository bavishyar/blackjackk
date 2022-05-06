public class Deck{
  
  static final String[] SUITES    = {"♠","♥","♦","♣"};
  static final String[] SPECIALS  = {"A","J","Q","K"};
  static final int      BASE      = 9;
  static final int      SIZE      = 52;

  private Card[] cards;
  
  public Deck(){
    this.cards = new Card[SIZE];
  }
}