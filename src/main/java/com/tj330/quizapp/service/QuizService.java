package com.tj330.quizapp.service;

import com.tj330.quizapp.exception.ResourceNotFoundException;
import com.tj330.quizapp.model.Question;
import com.tj330.quizapp.model.QuestionWrapper;
import com.tj330.quizapp.model.Quiz;
import com.tj330.quizapp.model.Response;
import com.tj330.quizapp.repository.QuestionRepository;
import com.tj330.quizapp.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public Quiz createQuiz(String category, Integer numQ, String title) {
        List<Question> questions = questionRepository.findRandomNoOfQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return quizRepository.save(quiz);

    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isEmpty()) {
            throw new ResourceNotFoundException("Quiz with id " + id + "not found");
        }

        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionsWrapped = new ArrayList<>();

        for (Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getContent(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsWrapped.add(qw);
        }

        return questionsWrapped;
    }

    public Integer calculateResult(Integer id, List<Response> response) {
        Quiz quiz = quizRepository.findById(id).get();

        List<Question> questions = quiz.getQuestions();

        int mark = 0, question = 0;

        for (Response r : response) {
            if (r.getResponse().equals(questions.get(question).getAnswer())) {
                mark++;
            }
            question++;
        }

        return mark;
    }
}
