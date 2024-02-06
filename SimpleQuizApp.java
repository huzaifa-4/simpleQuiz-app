import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleQuizApp extends JFrame {

    private int score = 0;
    private int currentQuestionIndex = 0;

    private final String[] questions = {
            "1. What is the capital of Italy?",
            "2. Which programming language is often used for Android app development?",
            "3. Who is the author of 'To Kill a Mockingbird'?"
    };

    public final String[][] options = {
            {"A. Paris", "B. Berlin", "C. Rome", "D. Madrid"},
            {"A. Java", "B. C++", "C. Python", "D. Swift"},
            {"A. Harper Lee", "B. J.K. Rowling", "C. George Orwell", "D. Ernest Hemingway"}
    };

    private final char[] correctAnswers = {'C', 'A', 'A'};

    private JLabel questionLabel;
    private ButtonGroup optionGroup;

    public SimpleQuizApp() {
        setTitle("Simple Java Quiz");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();

        displayQuestion();

        setVisible(true);
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        optionGroup = new ButtonGroup();
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        add(optionsPanel, BorderLayout.CENTER);

        for (int i = 0; i < 4; i++) {
            JRadioButton optionButton = new JRadioButton();
            optionButton.addActionListener(new OptionSelectedListener());
            optionGroup.add(optionButton);
            optionsPanel.add(optionButton);
        }

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonListener());
        add(nextButton, BorderLayout.SOUTH);
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);

            for (int i = 0; i < 4; i++) {
                optionGroup.getElements().nextElement().setText(options[currentQuestionIndex][i]);
            }
        } else {
            showResult();
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Completed!\nYour Score: " + score + " out of 3",
                "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private class OptionSelectedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedOption = (JRadioButton) e.getSource();
            char selectedAnswer = selectedOption.getText().charAt(0);

            if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
                score++;
            }
        }
    }

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentQuestionIndex++;
            optionGroup.clearSelection();
            displayQuestion();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleQuizApp());
    }
}