package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * @param questions question
     * @return question
     */
    @Override
    public final QuestionsDto addQuestion(final QuestionsDto questionsDto) {
        if (quizRepo.findById(questionsDto.getQuizId()).isPresent()) {
            if (!questionsRepo.findByQuestion(questionsDto.getQuestion())
                    .isPresent()) {
                if (questionsDto.getCorrectOption().equals(
                        questionsDto.getOption1())
                   || questionsDto.getCorrectOption().equals(
                           questionsDto.getOption2())
                   || questionsDto.getCorrectOption().equals(
                           questionsDto.getOption3())
                   || questionsDto.getCorrectOption().equals(
                           questionsDto.getOption4())) {
                    System.out.println(questionsDto.getCorrectOption());
                Questions question = new Questions();
                question.setQid(questionsDto.getQuizId());
                question.setQui(quizRepo.findById(questionsDto.getQuizId()).get());
                question.setCorrectOption(questionsDto.getCorrectOption());
                question.setOption1(questionsDto.getOption1());
                question.setOption2(questionsDto.getOption2());
                question.setOption4(questionsDto.getOption4());
                question.setOption3(questionsDto.getOption3());
                question.setQuestion(questionsDto.getQuestion());
                questionsRepo.save(question);
                LOGGER.info("add question");
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
            List<QuestionsDto> qd = convertToDto(questions);
            LOGGER.info("get all giestions");
            return qd;
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
        List<QuestionsDto> qd = new ArrayList<>();
        for (Questions questions : q) {
            QuestionsDto qu = new QuestionsDto();
            qu.setQuizId(questions.getQui().getQuizId());
            qu.setCorrectOption(questions.getCorrectOption());
            qu.setOption1(questions.getOption1());
            qu.setOption2(questions.getOption2());
            qu.setOption4(questions.getOption4());
            qu.setOption3(questions.getOption3());
            qu.setQuestion(questions.getQuestion());
            qu.setQuestionId(questions.getQid());
            qd.add(qu);
        }
        return qd;
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
                LOGGER.info("delete question");
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
     * @param q  questions.
     * @param id question id
     * @return questions
     */
    @Override
    public final QuestionsUpdateDto updateQue(final QuestionsUpdateDto q,
            final int id) {
        if (questionsRepo.findAll().size() != 0) {
            Optional<Questions> existingQue = questionsRepo.findById(id);
            if (existingQue.isPresent()) {
                Questions exiQue = existingQue.get();
                Optional<Questions> question = questionsRepo
                        .findByQuestion(q.getQuestion());
                if (question.isPresent()
                        && !(question.get().getQid() == id)) {
                throw new AlreadyExistException(ErrorMessages.QUESTION_EXIST);
                }
                exiQue.setOption1(q.getOption1());
                exiQue.setOption2(q.getOption2());
                exiQue.setOption3(q.getOption3());
                exiQue.setOption4(q.getOption4());
                exiQue.setCorrectOption(q.getCorrectOption());
                exiQue.setQuestion(q.getQuestion());
                questionsRepo.save(exiQue);
                LOGGER.info("update question");
                return q;
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
    public final List<QuestionsDto> findQueById(final int id) {
        if (questionsRepo.findQueById(id).size() != 0) {
                List<Questions> l = questionsRepo.findQueById(id);
                List<QuestionsDto> ld = convertToDto(l);
                LOGGER.info("find question by question id");
                return ld;
        } else {
            LOGGER.error(ErrorMessages.NO_QUESTION);
            throw new AllNotFoundException(ErrorMessages.NO_QUESTION);
        }
    }
    /**
     * find by question method.
     * @param name question name
     * @return questions
     */
    @Override
    public final Optional<QuestionsDto> findByQuestion(final String name) {
        if (questionsRepo.findAll().size() != 0) {
            if (questionsRepo.findByQuestion(name).isPresent()) {
                QuestionsDto questionsDto = new QuestionsDto();
                Optional<Questions> q = questionsRepo.findByQuestion(name);
                Questions questions = q.get();
                questionsDto.setQuizId(questions.getQui().getQuizId());
                questionsDto.setCorrectOption(questions.getCorrectOption());
                questionsDto.setOption1(questions.getOption1());
                questionsDto.setOption2(questions.getOption2());
                questionsDto.setOption4(questions.getOption4());
                questionsDto.setOption3(questions.getOption3());
                questionsDto.setQuestion(questions.getQuestion());
                questionsDto.setQuestionId(questions.getQid());
                LOGGER.info("find question");
                return Optional.of(questionsDto);
            } else {
                LOGGER.error(ErrorMessages.NO_QUESTION);
                throw new NotFoundException(ErrorMessages.NO_QUESTION);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_QUESTION);
            throw new AllNotFoundException(ErrorMessages.NO_QUESTION);
        }
    }

}
