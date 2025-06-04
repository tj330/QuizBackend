package com.tj330.quizapp.service;

import com.tj330.quizapp.exception.ResourceNotFoundException;
import com.tj330.quizapp.model.Question;
import com.tj330.quizapp.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findQuestionsByCategory(category);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestionById(Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));

        questionRepository.delete(question);
    }


    public Question updateQuestionById(Integer id, Question question) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        existingQuestion .setContent(question.getContent());
        existingQuestion .setOption1(question.getOption1());
        existingQuestion .setOption2(question.getOption2());
        existingQuestion .setOption3(question.getOption3());
        existingQuestion .setOption4(question.getOption4());
        existingQuestion .setLevel(question.getLevel());
        existingQuestion .setAnswer(question.getAnswer());
        existingQuestion .setCategory(question.getCategory());
        return questionRepository.save(existingQuestion);
    }
}
