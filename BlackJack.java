import java.util.Scanner;

/**
 * The BlackJack.
 * 
 * <-- Need to add betting system, blackjack insta-win, Ask to play again after bust!, playagain  --> 
 * add play again to rules
 * 
 * @author Arda Kurtoglu
 * @version 14.12.2020
 */

public class blackJack 
{
    public static int randomizer( int rangeMin, int rangeMax) {
        int randomNumber;
        int range;
        
        range = rangeMax - rangeMin + 1;
        
        randomNumber = (int) (Math.random() * range) + rangeMin;
        
        return randomNumber;
    }
    
    
    //pointcount without ace point with ace
    public static int pointIncludingAce( int aceCount, int point) {
        int pointGoingToBePrinted;
        
        pointGoingToBePrinted = point;
        
        if( aceCount == 1) {
            if( point <= 10) {
                pointGoingToBePrinted += 11;
            }
            else {
                pointGoingToBePrinted += 1;
            }
        }
        else if( aceCount == 2) {
            if( point <= 9) {
                pointGoingToBePrinted += 12;
            }
            else {
                pointGoingToBePrinted += 2;
            }
        }
        else if( aceCount == 3) {
            if( point <= 8) {
                pointGoingToBePrinted += 13;
            }
            else {
                pointGoingToBePrinted += 3;
            }
        }
        else if( aceCount == 4) {
            if( point <= 7) {
                pointGoingToBePrinted += 14;
            }
            else {
                pointGoingToBePrinted += 3;
            }
        }
        return pointGoingToBePrinted;
    }
    
    public static void main( String[] args)
    {
        Scanner scan = new Scanner( System.in);
        
        //Constants
        final String TOP_OF_THE_CARD = "┌───┐";
        final String MIDDLE_OF_THE_CARD = "|";
        final String BOTTOM_OF_THE_CARD = "└───┘";
        final String DRAW_WORD = "draw";
        final String DRAW_LETTER = "d";
        final String FOLD_WORD = "fold";
        final String FOLD_LETTER = "f";
        final String PLAY_AGAIN_LETTER = "p";
        final String PLAY_AGAIN_WORD1 = "play again";
        final String PLAY_AGAIN_WORD2 = "play";
        final String YES = "yes";
     //   final String NO = "no";
        
        //---Variables---//
        
        //Strings
        String dealersFirstCard;
        String nextMove;
        
        //Booleans
        boolean playAgain;
        boolean fold;
        boolean firstRound;
        boolean drawCard;
        boolean GO_ON;
        boolean bust;
        
        //Integers
        int tutorial;
        int counter;
        int cardChooser;
        int dealersPoint;
        int playersPoint;
        int dealersAceCount;
        int playersAceCount;
        int dealersCardCount;
        int playersCardCount;
        int dealersFirstCardPoint;
        
        //Arrays
        int pointList[] = 
        { 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
          0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, };
        
        String cardList[] = 
        {"A♠ ", "2♠ ", "3♠ ", "4♠ ", "5♠ ", "6♠ ", "7♠ ", "8♠ ", "9♠ ", "10♠", "J♠ ", "Q♠ ", "K♠ ",
         "A♦ ", "2♦ ", "3♦ ", "4♦ ", "5♦ ", "6♦ ", "7♦ ", "8♦ ", "9♦ ", "10♦", "J♦ ", "Q♦ ", "K♦ ",
         "A♥ ", "2♥ ", "3♥ ", "4♥ ", "5♥ ", "6♥ ", "7♥ ", "8♥ ", "9♥ ", "10♥", "J♥ ", "Q♥ ", "K♥ ",
         "A♣ ", "2♣ ", "3♣ ", "4♣ ", "5♣ ", "6♣ ", "7♣ ", "8♣ ", "9♣ ", "10♣", "J♣ ", "Q♣ ", "K♣ ",};
         
        System.out.println( "[Welcome to Java Blackjack (ver : 0.1)]");
        System.out.println( "[To learn how to play, press 0; to start the game press 1]");
        tutorial = scan.nextInt();
        
        if(tutorial == 0) {
            System.out.println( "┌────────────────────────────────────┐");
            System.out.println( "|  In blackjack you need to get as   |");
            System.out.println( "| close as you can to 21 points. But |");
            System.out.println( "| if you pass 21 points you will be  |");
            System.out.println( "| busted(i.e. Game Over!) The points |");
            System.out.println( "|  corresponds to the card numbers.  |");
            System.out.println( "| cards with pictures have 10 points.|");
            System.out.println( "| Ace cards can have either 1 or 11  |");
            System.out.println( "| points. [Draw a card][Fold] these  |");
            System.out.println( "| boxes indicate what you are capable|");
            System.out.println( "| to do in the following move. Type  |");
            System.out.println( "| d or draw and f or fold to do each |");
            System.out.println( "| Thank you for playing the game and |");
            System.out.println( "|        of course: Have Fun!!       |");
            System.out.println( "└────────────────────────────────────┘");
        }
        
        do 
        {
            dealersFirstCard = "";
            
            drawCard = false;
            playAgain = false;
            firstRound = true;
            bust = false;
            fold = false;
            GO_ON = true;
            
            dealersPoint = 0;
            playersPoint = 0;
            dealersAceCount = 0;
            playersAceCount = 0;
            dealersCardCount = 0;
            playersCardCount = 0;
            dealersFirstCardPoint = 0;
            
            
            //If the card is dealers' card it is represented with "D" else if it is players card it is "P"
            //Unused cards are represented with "a".
            String dealerOrPlayer[] = 
            {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", 
             "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", 
             "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a",};
             
            do {
                System.out.println( "");
                
                //Draws 2 cards to dealer only in the first round.
                if( firstRound) {
                    do {
                        cardChooser = randomizer( 0, 51);
                    } while( dealerOrPlayer[ cardChooser] != "a");
                    
                    dealerOrPlayer[ cardChooser] = "D";
                    dealersPoint = dealersPoint + pointList[ cardChooser];
                    
                    if( pointList[ cardChooser] == 0) {
                        dealersFirstCardPoint = 11;
                        dealersAceCount ++;
                    }
                    else {
                        dealersFirstCardPoint = pointList[ cardChooser];
                    }
                    
                    dealersCardCount ++;
                    dealersFirstCard = cardList[ cardChooser];
                    
                    do {
                        cardChooser = randomizer( 0, 51);
                    } while( dealerOrPlayer[ cardChooser] != "a");
                    
                    dealerOrPlayer[ cardChooser] = "D";
                    dealersPoint = dealersPoint + pointList[ cardChooser];
                    
                    if(pointList[ cardChooser] == 0) {
                        dealersAceCount ++;
                    }
                    dealersCardCount ++;
                }
                
                if( firstRound) {
                    for( counter = 1; counter <= 2; counter ++) {
                        do {
                            cardChooser = randomizer( 0, 51);
                        } while( dealerOrPlayer[ cardChooser] != "a");
                        
                        dealerOrPlayer[ cardChooser] = "P";
                        playersPoint = playersPoint + pointList[ cardChooser];
                        if( pointList[ cardChooser] == 0) {
                            playersAceCount ++;
                        }
                    }
                    playersCardCount = playersCardCount + 2;
                }
                firstRound = false;
                
                if( !fold && drawCard) {
                    do {
                        cardChooser = randomizer( 0, 51);
                    } while( dealerOrPlayer[ cardChooser] != "a");
                    
                    dealerOrPlayer[ cardChooser] = "P";
                    playersPoint = playersPoint + pointList[ cardChooser];
                    if( pointList[ cardChooser] == 0) {
                        playersAceCount ++;
                    }
                    
                    playersCardCount ++;
                    drawCard = false;
                }
                
                if( !fold) {
                    System.out.println( "[Dealer's Hand]");
                    System.out.println( TOP_OF_THE_CARD + " " + TOP_OF_THE_CARD);
                    
                    System.out.println( MIDDLE_OF_THE_CARD + dealersFirstCard + MIDDLE_OF_THE_CARD + " " + MIDDLE_OF_THE_CARD + "###" + MIDDLE_OF_THE_CARD);
                    
                    System.out.println( BOTTOM_OF_THE_CARD + " " + BOTTOM_OF_THE_CARD);
                    System.out.println( "[Dealer's total point: " + dealersFirstCardPoint +"]");
                    System.out.println( "");
                    
                    //Top part of the card.
                    System.out.println( "[Your hand]");
                    for( counter = 1; counter <= playersCardCount; counter ++) {
                        System.out.print( TOP_OF_THE_CARD + " ");
                    }
                    System.out.println( "");
                                        
                    //Mid part of the card.
                    for( counter = 0; counter <= 51; counter ++) {
                        if(dealerOrPlayer[counter] == "P" ) {
                            System.out.print( MIDDLE_OF_THE_CARD + cardList[counter] + MIDDLE_OF_THE_CARD + " ");
                        }
                    }
                    System.out.println( "");
                                                        
                    for( counter = 1; counter <= playersCardCount; counter ++) {
                        System.out.print( BOTTOM_OF_THE_CARD + " ");
                    }
                    //Can change this with the method acepointcalculator
                    System.out.println( "");
                                    
                    System.out.println( "[Your total point: " + pointIncludingAce( playersAceCount, playersPoint) +"]");
                }
                
                if( pointIncludingAce( playersAceCount, playersPoint) > 21) {
                    System.out.println( "[Bust!]");
                    bust = true;
                }
                 //16 constants
                if( fold)
                {
                    while( pointIncludingAce( dealersAceCount, dealersPoint) <= 16) {
                        do {
                            cardChooser = randomizer( 0, 51);
                        } while( dealerOrPlayer[ cardChooser] != "a");
                        
                        if( pointList[ cardChooser] == 0) {
                            dealersAceCount ++;
                        }
                        dealerOrPlayer[ cardChooser] = "D";
                        dealersPoint = dealersPoint + pointList[ cardChooser];
                        dealersCardCount ++;
                    }
                        
                        System.out.println(" [Dealer's Hand]");
                        
                        for( counter = 1; counter <= dealersCardCount; counter ++) {
                            System.out.print( TOP_OF_THE_CARD + " ");
                        }
                        System.out.println( "");
                        
                        for( counter = 0; counter <= 51; counter ++) {
                            if(dealerOrPlayer[counter] == "D" ) {
                                System.out.print( MIDDLE_OF_THE_CARD + cardList[counter] + MIDDLE_OF_THE_CARD + " ");
                            }
                        }
                        System.out.println( "");
                        
                        for( counter = 1; counter <= dealersCardCount; counter ++) {
                            System.out.print( BOTTOM_OF_THE_CARD + " ");
                        }
                        System.out.println( "");
                        
                        if( pointIncludingAce( dealersAceCount, dealersPoint) > 21) {
                            System.out.println( "[Dealer busted]");
                            System.out.println( "[ You won!]");
                        }
                        
                        else if( pointIncludingAce( dealersAceCount, dealersPoint) < pointIncludingAce( playersAceCount, playersPoint)) {
                            System.out.println( "[You won]");;
                        }
                        
                        else if( pointIncludingAce( dealersAceCount, dealersPoint) > pointIncludingAce( playersAceCount, playersPoint)) {
                            System.out.println( "[You lose]");
                        }
                        else if(  pointIncludingAce( dealersAceCount, dealersPoint) == pointIncludingAce( playersAceCount, playersPoint)) {
                            System.out.println( "[Tie]");
                        }
                        
                        GO_ON = false;
                    }
                if( !fold) {
                    System.out.println( "[Draw a card][Fold]");
                    nextMove = scan.next();
                    System.out.println( "");
                    
                    while( !(nextMove.equalsIgnoreCase(FOLD_LETTER) || nextMove.equalsIgnoreCase(FOLD_WORD) || nextMove.equalsIgnoreCase(DRAW_LETTER) || nextMove.equalsIgnoreCase(DRAW_WORD))) {
                        System.out.println( "You have entered wrong input. Please try again.");
                        nextMove = scan.next();
                    }
                    if( nextMove.equalsIgnoreCase(FOLD_LETTER) || nextMove.equalsIgnoreCase(FOLD_WORD)) {
                        fold = true;
                        drawCard = false;
                    }
                    else if( nextMove.equalsIgnoreCase(DRAW_LETTER) || nextMove.equalsIgnoreCase(DRAW_WORD)) {
                        fold = false;
                        drawCard = true;
                    }
                    GO_ON = true;
                }
            } while( GO_ON && !bust);
            
            System.out.println( "");
            System.out.println( "[Do you want to play again?]");
            nextMove = scan.next();
            if( nextMove.equalsIgnoreCase(PLAY_AGAIN_LETTER) || nextMove.equalsIgnoreCase(PLAY_AGAIN_WORD1) || nextMove.equalsIgnoreCase(PLAY_AGAIN_WORD2) || nextMove.equalsIgnoreCase(YES)) {
                playAgain = true;
            }
            
        } while( playAgain);
        scan.close();
    }
}
