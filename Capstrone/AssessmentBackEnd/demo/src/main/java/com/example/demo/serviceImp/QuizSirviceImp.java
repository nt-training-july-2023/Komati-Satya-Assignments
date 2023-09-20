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
    private QuizRepo qr;
    /**
     * auto wiring quiz category repository.
     */
    @Autowired
    private CategoryRepo cr;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuizSirviceImp.class);
    /**
     * constructor.
     * @param repo quiz repository
     * @param categoryRepo category repository
     */
    public QuizSirviceImp(final QuizRepo repo,
            final CategoryRepo categoryRepo) {
        this.qr = repo;
        this.cr = categoryRepo;
    }
    /**
     * add quiz method.
     * @param quiz quiz
     * @result quiz
     */
    @Override
    public final QuizDto addQuiz(final Quiz quiz) {
        if (cr.findById(quiz.getCate().getCategoryId()).isPresent()) {
            if (!qr.findQuizByName(quiz.getTopicName()).isPresent()) {
                QuizDto qd = new QuizDto();
                qd.setQuizId(quiz.getQuizId());
                qd.setTopicDescription(quiz.getTopicDescription());
                qd.setTopicName(quiz.getTopicName());
                qd.setCategoryId(quiz.getCate().getCategoryId());
                qd.setTimer(quiz.getTimer());
                qr.save(quiz);
                LOGGER.info("add question");
                return qd;
            } else {
                LOGGER.error("Topic is already exist,"
                        + "enter a new topic");
                throw new AlreadyExistException("Topic is already exist,"
                        + "enter a new topic");
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
        if (qr.findAll().size() != 0) {
            if (qr.findById(id).isPresent()) {
                 QuizDto qd = new QuizDto();
                 Optional<Quiz> q = qr.findById(id);
                 Quiz quiz = q.get();
                 qd.setQuizId(quiz.getQuizId());
                 qd.setTopicDescription(quiz.getTopicDescription());
                 qd.setTopicName(quiz.getTopicName());
                 qd.setCategoryId(quiz.getCate().getCategoryId());
                 qd.setTimer(quiz.getTimer());
                 LOGGER.info("get quiz by id");
                return Optional.of(qd);
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
        if (qr.findAll().size() != 0) {
            List<Quiz> l = qr.findAll();
            List<QuizDto> ld = convertToDto(l);
            return ld;
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
        List<QuizDto> ld = new  ArrayList<>();
        for (Quiz quiz:l) {
            QuizDto qd = new QuizDto();
            qd.setQuizId(quiz.getQuizId());
            qd.setTopicDescription(quiz.getTopicDescription());
            qd.setTopicName(quiz.getTopicName());
            qd.setCategoryId(quiz.getCate().getCategoryId());
            qd.setTimer(quiz.getTimer());
            ld.add(qd);
        }
        return ld;
    }
    /**
     * delete quiz method.
     * @param id quiz id
     */
    @Override
    public final void deleteQuiz(final int id) {
        if (qr.findAll().size() != 0) {
            if (qr.findById(id).isPresent()) {
                qr.deleteById(id);
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
     * @param q quiz
     * @result quiz
     */
    @Override
    public final QuizUpdateDto updateQuiz(final QuizUpdateDto q, final int id) {
        if (qr.findAll().size() != 0) {
        Optional<Quiz> existingQuiz = qr.findById(id);
        if (existingQuiz.isPresent()) {
            Quiz exiQuiz = existingQuiz.get();
            exiQuiz.setTopicName(q.getTopicName());
            exiQuiz.setTopicDescription(q.getTopicDescription());
            exiQuiz.setTimer(q.getTimer());
            qr.save(exiQuiz);
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
        if (qr.findAll().size() != 0) {
            if (qr.findQuizById(id).size() != 0) {
            List<Quiz> l = qr.findQuizById(id);
            List<QuizDto> ld = convertToDto(l);
            LOGGER.info("Find quiz by category id");
            return ld;
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
        if (qr.findAll().size() != 0) {
            if (qr.findQuizByName(name).isPresent()) {
               QuizDto qd = new QuizDto();
               Optional<Quiz> qui = qr.findQuizByName(name);
               Quiz quiz = qui.get();
               qd.setQuizId(quiz.getQuizId());
               qd.setTopicDescription(quiz.getTopicDescription());
               qd.setTopicName(quiz.getTopicName());
               qd.setCategoryId(quiz.getCate().getCategoryId());
               qd.setTimer(quiz.getTimer());
               LOGGER.info("find quiz by name");
               return Optional.of(qd);
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
