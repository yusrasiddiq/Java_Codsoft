import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuizApp {
    private JFrame frame;
    private JLabel questionLabel, timerLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton submitButton;
    private int currentQuestion = 0, score = 0, timeLeft = 15; // 15 seconds per question
    private Timer timer;

    private ArrayList<Question> questions;

    public QuizApp() {
        // Initialize questions
        questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Lisbon"}, 2));
        questions.add(new Question("Who developed Java?",
                new String[]{"Microsoft", "Sun Microsystems", "Google", "Apple"}, 1));
        questions.add(new Question("What is 5 + 3?",
                new String[]{"5", "8", "10", "15"}, 1));

        frame = new JFrame("Quiz Application");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel: Question + Timer
        JPanel topPanel = new JPanel(new BorderLayout());
        questionLabel = new JLabel("", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel = new JLabel("Time left: " + timeLeft + "s", JLabel.RIGHT);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        topPanel.add(questionLabel, BorderLayout.CENTER);
        topPanel.add(timerLabel, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);

        // Center Panel: Options
        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionGroup.add(options[i]);
            centerPanel.add(options[i]);
        }
        frame.add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel: Submit Button
        JPanel bottomPanel = new JPanel();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            timer.stop(); // Stop the timer when the answer is submitted
            checkAnswer();
        });
        bottomPanel.add(submitButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Start Quiz
        displayQuestion();
        startTimer();

        frame.setVisible(true);
    }

    private void displayQuestion() {
        if (currentQuestion < questions.size()) {
            Question q = questions.get(currentQuestion);
            questionLabel.setText("Q" + (currentQuestion + 1) + ": " + q.getQuestion());
            String[] opts = q.getOptions();
            for (int i = 0; i < 4; i++) {
                options[i].setText(opts[i]);
                options[i].setSelected(false);
            }
            timeLeft = 15;
            timerLabel.setText("Time left: " + timeLeft + "s");
        } else {
            endQuiz();
        }
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft + "s");
            if (timeLeft == 0) {
                timer.stop();
                checkAnswer();
            }
        });
        timer.start();
    }

    private void checkAnswer() {
        Question q = questions.get(currentQuestion);
        int correctOption = q.getCorrectOption();
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && i == correctOption) {
                score++;
            }
        }
        currentQuestion++;
        displayQuestion();
        startTimer();
    }

    private void endQuiz() {
        timer.stop();
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        JLabel resultLabel = new JLabel("Your Score: " + score + "/" + questions.size(), JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(resultLabel, BorderLayout.CENTER);

        JTextArea summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            summaryArea.append("Q" + (i + 1) + ": " + q.getQuestion() + "\n");
            summaryArea.append("Correct Answer: " + q.getOptions()[q.getCorrectOption()] + "\n\n");
        }
        frame.add(new JScrollPane(summaryArea), BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizApp::new);
    }
}

// Helper Class for Questions
class Question {
    private String question;
    private String[] options;
    private int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}


