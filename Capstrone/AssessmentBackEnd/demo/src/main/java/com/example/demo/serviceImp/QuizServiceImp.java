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
import com.example.demo.entity.Category;
import com.example.demo.entity.Quiz;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.QuizRepo;
import com.example.demo.service.QuizService;
import com.example.demo.validationMessages.ErrorMessages;
import com.example.demo.validationMessages.Messages;

/**
 * quiz service interface.
 */
@Service
public class QuizServiceImp implements QuizService {
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
            .getLogger(QuizServiceImp.class);

    /**
     * add quiz method.
     * @param quizDto quiz
     * @result quiz
     */
    @Override
    public final QuizDto addQuiz(final QuizDto quizDto) {
        if (categoryRepo.findById(quizDto.getCategoryId()).isPresent()) {
            if (!quizRepo.findQuizByName(quizDto.getTopicName()).isPresent()) {
                Quiz quiz = new Quiz();
                quiz.setQuizId(quizDto.getQuizId());
                quiz.setTopicDescription(quizDto.getTopicDescription());
                quiz.setTopicName(quizDto.getTopicName());
                Category category = categoryRepo.
                        findById(quizDto.getCategoryId()).get();
                quiz.setCategory(category);
                quiz.setTimer(quizDto.getTimer());
                quizRepo.save(quiz);
                LOGGER.info(Messages.SAVE_QUIZ);
                return quizDto;
            } else {
                LOGGER.error(ErrorMessages.QUIZ_EXIST);
                throw new AlreadyExistException(ErrorMessages.QUIZ_EXIST);
            }
        } else {
            LOGGER.error(ErrorMessages.CATEGORY_NOTPRESENT);
            throw new NotFoundException(ErrorMessages.CATEGORY_NOTPRESENT);
        }
    }
    /**
     * get quiz method.
     * @param id quiz id
     * @result quiz
     */
    @Override
    public final QuizDto getQuiz(final int id) {
        if (quizRepo.findAll().size() != 0) {
            if (quizRepo.findById(id).isPresent()) {
                QuizDto quizDto = new QuizDto();
                Optional<Quiz> quizOptional = quizRepo.findById(id);
                Quiz quiz = quizOptional.get();
                quizDto.setQuizId(quiz.getQuizId());
                quizDto.setTopicDescription(quiz.getTopicDescription());
                quizDto.setTopicName(quiz.getTopicName());
                quizDto.setCategoryId(quiz.getCategory().getCategoryId());
                quizDto.setTimer(quiz.getTimer());
                LOGGER.info(Messages.FIND_QUIZ);
                return quizDto;
            } else {
                LOGGER.error(ErrorMessages.WRONG_QUIZID);
                throw new NotFoundException(ErrorMessages.WRONG_QUIZID);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_QUIZ);
            throw new AllNotFoundException(ErrorMessages.NO_QUIZ);
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
            LOGGER.error(ErrorMessages.NO_QUIZ);
            throw new AllNotFoundException(ErrorMessages.NO_QUIZ);
        }
    }
    /**
     * converToDto method.
     * @param quizList quiz list
     * @return quizDto
     */
    private List<QuizDto> convertToDto(final List<Quiz> quizList) {
        List<QuizDto> quizDto = new ArrayList<>();
        for (Quiz quiz : quizList) {
            QuizDto qd = new QuizDto();
            qd.setQuizId(quiz.getQuizId());
            qd.setTopicDescription(quiz.getTopicDescription());
            qd.setTopicName(quiz.getTopicName());
            qd.setCategoryId(quiz.getCategory().getCategoryId());
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
                LOGGER.info(Messages.DELETE_QUIZ);
            } else {
                LOGGER.error(ErrorMessages.WRONG_QUIZID);
                throw new NotFoundException(ErrorMessages.WRONG_QUIZID);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_QUIZ);
            throw new AllNotFoundException(ErrorMessages.NO_QUIZ);
        }
    }
    /**
     * update quiz method.
     * @param id quiz id
     * @param quizUpdateDto  quiz
     * @result quiz
     */
    @Override
    public final QuizUpdateDto updateQuiz(final QuizUpdateDto
            quizUpdateDto, final int id) {
        if (quizRepo.findAll().size() != 0) {
            Optional<Quiz> existingQuiz = quizRepo.findById(id);
            if (existingQuiz.isPresent()) {
                Quiz exiQuiz = existingQuiz.get();
                Optional<Quiz> quiz = quizRepo
                        .findQuizByName(quizUpdateDto.getTopicName());
                if (quiz.isPresent()
                        && !(quiz.get().getQuizId() == id)) {
                    throw new AlreadyExistException(ErrorMessages.QUIZ_EXIST);
                }
                exiQuiz.setTopicName(quizUpdateDto.getTopicName());
                exiQuiz.setTopicDescription(quizUpdateDto.
                        getTopicDescription());
                exiQuiz.setTimer(quizUpdateDto.getTimer());
                quizRepo.save(exiQuiz);
                LOGGER.info(Messages.UPDATE_QUIZ);
                return quizUpdateDto;
            } else {
                LOGGER.error(ErrorMessages.WRONG_QUIZID);
                throw new NotFoundException(ErrorMessages.WRONG_QUIZID);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_QUIZ);
            throw new AllNotFoundException(ErrorMessages.NO_QUIZ);
        }
    }
    /**
     * find quiz by id method.
     * @param id quiz id
     * @result quiz
     */
    @Override
    public final List<QuizDto> findQuizById(final int id) {
        if (quizRepo.findQuizById(id).size() != 0) {
                List<Quiz> quiz = quizRepo.findQuizById(id);
                List<QuizDto> quizDto = convertToDto(quiz);
                LOGGER.info(Messages.FIND_QUIZBYCATEGORYID);
                return quizDto;
        } else {
            LOGGER.error(ErrorMessages.NO_QUIZ);
            throw new AllNotFoundException(ErrorMessages.NO_QUIZ);
        }

    }
    /**
     * find quiz by name method.
     * @param name quiz name
     * @return quiz
     */
    @Override
    public final QuizDto findQuizByName(final String name) {
        if (quizRepo.findAll().size() != 0) {
            if (quizRepo.findQuizByName(name).isPresent()) {
                QuizDto quizDto = new QuizDto();
                Optional<Quiz> quizz = quizRepo.findQuizByName(name);
                Quiz quiz = quizz.get();
                quizDto.setQuizId(quiz.getQuizId());
                quizDto.setTopicDescription(quiz.getTopicDescription());
                quizDto.setTopicName(quiz.getTopicName());
                quizDto.setCategoryId(quiz.getCategory().getCategoryId());
                quizDto.setTimer(quiz.getTimer());
                LOGGER.info(Messages.FIND_QUIZ);
                return quizDto;
            } else {
                LOGGER.error(ErrorMessages.QUIZ_NOTPRESENT);
                throw new NotFoundException(ErrorMessages.QUIZ_NOTPRESENT);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_QUIZ);
            throw new AllNotFoundException(ErrorMessages.NO_QUIZ);
        }
    }
}
