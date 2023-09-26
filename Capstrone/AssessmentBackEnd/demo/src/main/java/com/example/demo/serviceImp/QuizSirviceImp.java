package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.QuizDto;
import com.example.demo.dto.QuizUpdateDto;
import com.example.demo.entity.Quiz;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.QuizRepo;
import com.example.demo.service.QuizService;

/**
 * quiz service interface.
 */
@Service
public class QuizSirviceImp implements QuizService {
    /**
     * auto wiring quiz repository.
     */
    @Autowired
    private QuizRepo quizRepo;
    /**
     * auto wiring quiz category repository.
     */
    @Autowired
    private CategoryRepo categoryRepo;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuizSirviceImp.class);

    /**
     * add quiz method.
     * @param quiz quiz
     * @result quiz
     */
    @Override
    public final QuizDto addQuiz(final Quiz quiz) {
        if (categoryRepo.findById(quiz.getCate().getCategoryId()).isPresent()) {
            if (!quizRepo.findQuizByName(quiz.getTopicName()).isPresent()) {
                QuizDto quizDto = new QuizDto();
                quizDto.setQuizId(quiz.getQuizId());
                quizDto.setTopicDescription(quiz.getTopicDescription());
                quizDto.setTopicName(quiz.getTopicName());
                quizDto.setCategoryId(quiz.getCate().getCategoryId());
                quizDto.setTimer(quiz.getTimer());
                quizRepo.save(quiz);
                LOGGER.info("add question");
                return quizDto;
            } else {
                LOGGER.error("Topic is already exist," + "enter a new topic");
                throw new AlreadyExistException(
                        "Topic is already exist," + "enter a new topic");
            }
        } else {
            LOGGER.error("Category is not present");
            throw new NotFoundException("Category is not present");
        }
    }
    /**
     * get quiz method.
     * @param id quiz id
     * @result quiz
     */
    @Override
    public final Optional<QuizDto> getQuiz(final int id) {
        if (quizRepo.findAll().size() != 0) {
            if (quizRepo.findById(id).isPresent()) {
                QuizDto quizDto = new QuizDto();
                Optional<Quiz> q = quizRepo.findById(id);
                Quiz quiz = q.get();
                quizDto.setQuizId(quiz.getQuizId());
                quizDto.setTopicDescription(quiz.getTopicDescription());
                quizDto.setTopicName(quiz.getTopicName());
                quizDto.setCategoryId(quiz.getCate().getCategoryId());
                quizDto.setTimer(quiz.getTimer());
                LOGGER.info("get quiz by id");
                return Optional.of(quizDto);
            } else {
                LOGGER.error("wrong quiz id");
                throw new NotFoundException("wrong quiz id");
            }
        } else {
            LOGGER.error("no quiz is present");
            throw new AllNotFoundException("no quiz is present");
        }
    }
    /**
     * find all method.
     * @return quiz
     */
    @Override
    public final List<QuizDto> findAll() {
        if (quizRepo.findAll().size() != 0) {
            List<Quiz> quiz = quizRepo.findAll();
            List<QuizDto> quizDto = convertToDto(quiz);
            return quizDto;
        } else {
            throw new AllNotFoundException("no quiz is present");
        }
    }
    /**
     * converToDto method.
     * @param l quiz list
     * @return quizDto
     */
    private List<QuizDto> convertToDto(final List<Quiz> l) {
        List<QuizDto> quizDto = new ArrayList<>();
        for (Quiz quiz : l) {
            QuizDto qd = new QuizDto();
            qd.setQuizId(quiz.getQuizId());
            qd.setTopicDescription(quiz.getTopicDescription());
            qd.setTopicName(quiz.getTopicName());
            qd.setCategoryId(quiz.getCate().getCategoryId());
            qd.setTimer(quiz.getTimer());
            quizDto.add(qd);
        }
        return quizDto;
    }
    /**
     * delete quiz method.
     * @param id quiz id
     */
    @Override
    public final void deleteQuiz(final int id) {
        if (quizRepo.findAll().size() != 0) {
            if (quizRepo.findById(id).isPresent()) {
                quizRepo.deleteById(id);
                LOGGER.info("delete quiz");
            } else {
                LOGGER.error("wrong quiz id");
                throw new NotFoundException("wrong quiz id");
            }
        } else {
            LOGGER.error("no quiz is present");
            throw new AllNotFoundException("no quiz is present");
        }
    }
    /**
     * update quiz method.
     * @param id quiz id
     * @param q  quiz
     * @result quiz
     */
    @Override
    public final QuizUpdateDto updateQuiz(final QuizUpdateDto q, final int id) {
        if (quizRepo.findAll().size() != 0) {
            Optional<Quiz> existingQuiz = quizRepo.findById(id);
            if (existingQuiz.isPresent()) {
                Quiz exiQuiz = existingQuiz.get();
                exiQuiz.setTopicName(q.getTopicName());
                exiQuiz.setTopicDescription(q.getTopicDescription());
                exiQuiz.setTimer(q.getTimer());
                quizRepo.save(exiQuiz);
                LOGGER.info("update quiz");
                return q;
            } else {
                LOGGER.error("wrong quiz id");
                throw new NotFoundException("wrong quiz id");
            }
        } else {
            LOGGER.error("no quiz is present");
            throw new AllNotFoundException("no quiz is present");
        }
    }
    /**
     * find quiz by id method.
     * @param id quiz id
     * @result quiz
     */
    @Override
    public final List<QuizDto> findQuizById(final int id) {
        if (quizRepo.findAll().size() != 0) {
            if (quizRepo.findQuizById(id).size() != 0) {
                List<Quiz> quiz = quizRepo.findQuizById(id);
                List<QuizDto> quizDto = convertToDto(quiz);
                LOGGER.info("Find quiz by category id");
                return quizDto;
            } else {
                LOGGER.error("wrong category id");
                throw new NotFoundException("wrong category id");
            }
        } else {
            LOGGER.error("no quiz is present");
            throw new AllNotFoundException("no quiz is present");
        }

    }
    /**
     * find quiz by name method.
     * @param name quiz name
     * @return quiz
     */
    @Override
    public final Optional<QuizDto> findQuizByName(final String name) {
        if (quizRepo.findAll().size() != 0) {
            if (quizRepo.findQuizByName(name).isPresent()) {
                QuizDto quizDto = new QuizDto();
                Optional<Quiz> quizz = quizRepo.findQuizByName(name);
                Quiz quiz = quizz.get();
                quizDto.setQuizId(quiz.getQuizId());
                quizDto.setTopicDescription(quiz.getTopicDescription());
                quizDto.setTopicName(quiz.getTopicName());
                quizDto.setCategoryId(quiz.getCate().getCategoryId());
                quizDto.setTimer(quiz.getTimer());
                LOGGER.info("find quiz by name");
                return Optional.of(quizDto);
            } else {
                LOGGER.error("topic is not found");
                throw new NotFoundException("topic is not found");
            }
        } else {
            LOGGER.error("no quiz is present");
            throw new AllNotFoundException("no quiz is present");
        }
    }
}
