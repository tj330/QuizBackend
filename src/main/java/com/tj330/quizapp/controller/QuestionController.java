package com.tj330.quizapp.controller;

import com.tj330.quizapp.model.Question;
import com.tj330.quizapp.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestions());
    }

    @GetMapping("/questions/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionsByCategory(category));
    }

    @PostMapping("/question")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.saveQuestion(question));
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<Question> updateQuestionById(@PathVariable Integer id, @RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.updateQuestionById(id, question));
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable Integer id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
