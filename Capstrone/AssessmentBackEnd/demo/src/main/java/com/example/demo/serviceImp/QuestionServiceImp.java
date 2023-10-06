package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.QuestionsDto;
import com.example.demo.dto.QuestionsUpdateDto;
import com.example.demo.entity.Questions;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.QuestionsRepo;
import com.example.demo.repository.QuizRepo;
import com.example.demo.service.QuestionsService;
import com.example.demo.validationMessages.ErrorMessages;
import com.example.demo.validationMessages.Messages;


/**
 * Question service interface.
 */
@Service
public class QuestionServiceImp implements QuestionsService {
    /**
     * auto wiring question repository.
     */
    @Autowired
    private QuestionsRepo questionsRepo;
    /**
     * auto wiring question repository.
     */
    @Autowired
    private QuizRepo quizRepo;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuestionServiceImp.class);

    /**
     * add question method.
     * @param questionsDto question
     * @return question
     */
    @Override
    public final QuestionsDto addQuestion(final QuestionsDto questionsDto) {
        if (quizRepo.findById(questionsDto.getQuizId()).isPresent()) {
            if (!questionsRepo.findByQuestion(questionsDto.getQuestion())
                    .isPresent()) {
                Set<String> options = new HashSet<>();
                options.add(questionsDto.getOption1());
                options.add(questionsDto.getOption2());
                options.add(questionsDto.getOption3());
                options.add(questionsDto.getOption4());
                final int number = 4;
                if (options.size() < number) {
                    throw new AlreadyExistException(ErrorMessages
                           .OPTION_EXIST);
                }
                if (questionsDto.getCorrectOption().equals(
                        questionsDto.getOption1())
                   || questionsDto.getCorrectOption().equals(
                           questionsDto.getOption2())
                   || questionsDto.getCorrectOption().equals(
                           questionsDto.getOption3())
                   || questionsDto.getCorrectOption().equals(
                           questionsDto.getOption4())) {
                Questions question = new Questions();
                question.setQid(questionsDto.getQuizId());
                question.setQuiz(quizRepo.findById(
                        questionsDto.getQuizId()).get());
                question.setCorrectOption(questionsDto.getCorrectOption());
                question.setOption1(questionsDto.getOption1());
                question.setOption2(questionsDto.getOption2());
                question.setOption4(questionsDto.getOption4());
                question.setOption3(questionsDto.getOption3());
                question.setQuestion(questionsDto.getQuestion());
                questionsRepo.save(question);
                LOGGER.info(Messages.SAVE_QUESTION);
                return questionsDto;
                } else {
                    LOGGER.error(ErrorMessages.CORRECT_OPTION);
                    throw new NotFoundException(ErrorMessages.CORRECT_OPTION);
                }
            } else {
                LOGGER.error(ErrorMessages.QUESTION_EXIST);
                throw new AlreadyExistException(ErrorMessages.QUESTION_EXIST);
            }
        } else {
            LOGGER.error(ErrorMessages.QUIZ_NOTPRESENT);
            throw new NotFoundException(ErrorMessages.QUIZ_NOTPRESENT);
        }
    }
    /**
     * get all questions.
     * @return list of questions.
     */
    @Override
    public final List<QuestionsDto> getQuestions() {
        if (questionsRepo.findAll().size() != 0) {
            List<Questions> questions = questionsRepo.findAll();
            List<QuestionsDto> questionDto = convertToDto(questions);
            LOGGER.info(Messages.FIND_ALLQUESTION);
            return questionDto;
        } else {
            LOGGER.error(ErrorMessages.NO_QUESTION);
            throw new AllNotFoundException(ErrorMessages.NO_QUESTION);
        }
    }
    /**
     * convertToDto method.
     * @param q questions
     * @return list of questions
     */
    private List<QuestionsDto> convertToDto(final List<Questions> q) {
        List<QuestionsDto> quesDto = new ArrayList<>();
        for (Questions questions : q) {
            QuestionsDto questionDto = new QuestionsDto();
            questionDto.setQuizId(questions.getQuiz().getQuizId());
            questionDto.setCorrectOption(questions.getCorrectOption());
            questionDto.setOption1(questions.getOption1());
            questionDto.setOption2(questions.getOption2());
            questionDto.setOption4(questions.getOption4());
            questionDto.setOption3(questions.getOption3());
            questionDto.setQuestion(questions.getQuestion());
            questionDto.setQuestionId(questions.getQid());
            quesDto.add(questionDto);
        }
        return quesDto;
    }
    /**
     * delete method.
     * @param id question id
     */
    @Override
    public final void delete(final int id) {
        if (questionsRepo.findAll().size() != 0) {
            if (questionsRepo.findById(id).isPresent()) {
                questionsRepo.deleteById(id);
                LOGGER.info(Messages.DELETE_QUESTION);
            } else {
                LOGGER.error(ErrorMessages.WRONG_QUESTIONID);
                throw new NotFoundException(ErrorMessages.WRONG_QUESTIONID);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_QUESTION);
            throw new AllNotFoundException(ErrorMessages.NO_QUESTION);
        }
    }
    /**
     * update question method.
     * @param questionDto questions.
     * @param id question id
     * @return questions
     */
    @Override
    public final QuestionsUpdateDto updateQuestion(final QuestionsUpdateDto
            questionDto,
            final int id) {
        if (questionsRepo.findAll().size() != 0) {
            Optional<Questions> existingQue = questionsRepo.findById(id);
            if (existingQue.isPresent()) {
                Questions exiQue = existingQue.get();
                Optional<Questions> question = questionsRepo
                        .findByQuestion(questionDto.getQuestion());
                if (question.isPresent()
                        && !(question.get().getQid() == id)) {
                throw new AlreadyExistException(ErrorMessages.QUESTION_EXIST);
                }
                Set<String> options = new HashSet<>();
                options.add(questionDto.getOption1());
                options.add(questionDto.getOption2());
                options.add(questionDto.getOption3());
                options.add(questionDto.getOption4());
                final int number = 4;
                if (options.size() < number) {
                    throw new AlreadyExistException(ErrorMessages
                           .OPTION_EXIST);
                }
                if (questionDto.getCorrectOption().equals(
                        questionDto.getOption1())
                   || questionDto.getCorrectOption().equals(
                           questionDto.getOption2())
                   || questionDto.getCorrectOption().equals(
                           questionDto.getOption3())
                   || questionDto.getCorrectOption().equals(
                           questionDto.getOption4())) {
                exiQue.setOption1(questionDto.getOption1());
                exiQue.setOption2(questionDto.getOption2());
                exiQue.setOption3(questionDto.getOption3());
                exiQue.setOption4(questionDto.getOption4());
                exiQue.setCorrectOption(questionDto.getCorrectOption());
                exiQue.setQuestion(questionDto.getQuestion());
                questionsRepo.save(exiQue);
                LOGGER.info(Messages.UPDATE_QUESTION);
                return questionDto;
                } else {
                    LOGGER.error(ErrorMessages.CORRECT_OPTION);
                    throw new NotFoundException(ErrorMessages.CORRECT_OPTION);
                }
            } else {
                LOGGER.error(ErrorMessages.WRONG_QUESTIONID);
                throw new NotFoundException(ErrorMessages.WRONG_QUESTIONID);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_QUESTION);
            throw new AllNotFoundException(ErrorMessages.NO_QUESTION);
        }
    }
    /**
     * find by question id.
     * @param id question id
     * @return questions
     */
    @Override
    public final List<QuestionsDto> findQuestionById(final int id) {
        if (questionsRepo.findQueById(id).size() != 0) {
                List<Questions> questions = questionsRepo.findQueById(id);
                List<QuestionsDto> questionDto = convertToDto(questions);
                LOGGER.info(Messages.FIND_QUESTIONBYQUIZID);
                return questionDto;
        } else {
            LOGGER.error(ErrorMessages.NO_QUESTION);
            throw new AllNotFoundException(ErrorMessages.NO_QUESTION);
        }
    }
    /**
     * find by question method.
     * @param question question name
     * @return questions
     */
    @Override
    public final Optional<QuestionsDto> findByQuestion(final String question) {
        List<Questions> questionsList = questionsRepo.findAll();
        for (Questions questions : questionsList) {
            if (questions.getQuestion().contains(question)) {
                QuestionsDto questionsDto = new QuestionsDto();
                questionsDto.setQuizId(questions.getQuiz().getQuizId());
                questionsDto.setCorrectOption(questions.getCorrectOption());
                questionsDto.setOption1(questions.getOption1());
                questionsDto.setOption2(questions.getOption2());
                questionsDto.setOption4(questions.getOption4());
                questionsDto.setOption3(questions.getOption3());
                questionsDto.setQuestion(questions.getQuestion());
                questionsDto.setQuestionId(questions.getQid());
                LOGGER.info(Messages.FIND_QUESTION);
                return Optional.of(questionsDto);
            }
        }
        LOGGER.error(ErrorMessages.NO_QUESTION);
        throw new NotFoundException(ErrorMessages.NO_QUESTION);
    }
}
