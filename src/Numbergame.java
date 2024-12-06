import java.util.Scanner;
public class Numbergame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;
        int totalRoundsWon = 0; // Tracks total rounds won
        int totalRoundsPlayed = 0; // Tracks total rounds played

        System.out.println("--------- Welcome to the Guessing Game ---------");

        while (playAgain) {
            totalRoundsPlayed++;
            int randomNumber = (int) ((Math.random() * 100) + 1); // Generate a random number between 1-100
            int maxGuesses = 8; // Max attempts for this round
            int attemptsUsed = 0; // Attempts used in this round

            System.out.println("-------- Guess a number between 1-100 --------- ");
            System.out.println("You have " + maxGuesses + " attempts to guess the number!");

            boolean roundWon = false;

            while (maxGuesses > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attemptsUsed++;

                if (userGuess == randomNumber) {
                    System.out.println("🎉 Winner Winner! You guessed it in " + attemptsUsed + " attempts.");
                    totalRoundsWon++;
                    roundWon = true;
                    break;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }

                maxGuesses--;

                if (maxGuesses == 0) {
                    System.out.println("💔 You've used all your attempts. The correct number was: " + randomNumber);
                }
            }

            if (roundWon) {
                System.out.println("🎊 Congratulations! You won this round.");
            } else {
                System.out.println("😢 Better luck next time!");
            }

            // Ask the user if they want to play another round
            System.out.print("Do you want to play again? (yes/no): ");
            String userResponse = sc.next().toLowerCase();

            if (!userResponse.equals("yes")) {
                playAgain = false;
            }

            System.out.println("\n--------------------------------------------");
        }

        // Display the user's final score
        System.out.println("🎮 Game Over!");
        System.out.println("Rounds Played: " + totalRoundsPlayed);
        System.out.println("Rounds Won: " + totalRoundsWon);
        System.out.println("Thanks for playing!");
        sc.close();
    }
}
