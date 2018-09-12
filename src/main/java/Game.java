
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {


    public void game(){

            try {

                InputStreamReader input = new InputStreamReader( System.in );
                BufferedReader br = new BufferedReader( input );
                System.out.print( "Create a new game (Y/N)? " );
                char c = br.readLine().toUpperCase().charAt(0);
                if ( c == 'Y' ) System.out.println( "Creating a new game (:" );
                else if ( c == 'N' ) {
                    System.out.println( "Exiting :(" );
                    return;
                } else {
                    System.out.println( "Entered unstable input\n\tExiting...");
                    return;
                }
                prepareGame();
                System.out.println( "Starting a new game!" );
            } catch ( IOException e ) {
                e.printStackTrace();
            }

    }

    private void prepareGame(){
        //Words words = new Words( "easy-text.txt" );

        try {
            //words.addWords();
        } catch ( NullPointerException e ) {
            System.err.println( "Error due to invalid previous node" );
            e.printStackTrace();
        }
    }
}


*/
