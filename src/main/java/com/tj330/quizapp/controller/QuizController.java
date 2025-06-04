package com.tj330.quizapp.controller;

import com.tj330.quizapp.model.QuestionWrapper;
import com.tj330.quizapp.model.Quiz;
import com.tj330.quizapp.model.Response;
import com.tj330.quizapp.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@AllArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        quizService.createQuiz(category, numQ, title);

        return ResponseEntity.status(HttpStatus.CREATED).body("Quiz created");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        quizService.getQuizQuestions(id);

        return ResponseEntity.status(HttpStatus.OK).body(quizService.getQuizQuestions(id));
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response) {
        int mark = quizService.calculateResult(id, response);
        return ResponseEntity.status(HttpStatus.OK).body(mark);
    }
}
