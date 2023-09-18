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

/**
 * Question service interface.
 */
@Service
public class QuestionServiceImp implements QuestionsService {
    /**
     * auto wiring question repository.
     */
    @Autowired
    private QuestionsRepo qr;
    /**
     * auto wiring question repository.
     */
    @Autowired
    private QuizRepo qrr;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuestionServiceImp.class);
   /**
    * constructor.
    * @param questionsRepo questions repository
    * @param quizRepo quiz repository
    */
    public QuestionServiceImp(final QuestionsRepo questionsRepo,
            final QuizRepo quizRepo) {
       this.qr = questionsRepo;
       this.qrr = quizRepo;
    }

    /**
     * add question method.
     * @param que question
     * @return question
     */
    @Override
    public final QuestionsDto addQuestion(final Questions que) {
        if (qrr.findById(que.getQui().getQuizId()).isPresent()) {
            if (!qr.findByQuestion(que.getQuestion()).isPresent()) {
                QuestionsDto qu = new QuestionsDto();
                qu.setQuizId(que.getQui().getQuizId());
                qu.setCorrectOption(que.getCorrectOption());
                qu.setOption1(que.getOption1());
                qu.setOption2(que.getOption2());
                qu.setOption4(que.getOption4());
                qu.setOption3(que.getOption3());
                qu.setQuestion(que.getQuestion());
                qr.save(que);
                LOGGER.info("add question");
                return qu;
            } else {
                LOGGER.error("question already exist");
                throw new AlreadyExistException("question already exist");
            }
        } else {
            LOGGER.error("Quiz topic is not there");
            throw new NotFoundException("Quiz topic is not there");
        }
    }

    /**
     * get all questions.
     * @return list of questions.
     */
    @Override
    public final List<QuestionsDto> getQuestions() {
        if (qr.findAll().size() != 0) {
            List<Questions> que = qr.findAll();
            List<QuestionsDto> qd = convertToDto(que);
            LOGGER.info("get all giestions");
            return qd;
        } else {
            LOGGER.error("no question is present");
            throw new AllNotFoundException("no question is present");
        }
    }
    /**
     * convertToDto method.
     * @param q questions
     * @return list of questions
     */
    private List<QuestionsDto> convertToDto(final List<Questions> q) {
        List<QuestionsDto> qd = new ArrayList<>();
        for (Questions que : q) {
            QuestionsDto qu = new QuestionsDto();
            qu.setQuizId(que.getQui().getQuizId());
            qu.setCorrectOption(que.getCorrectOption());
            qu.setOption1(que.getOption1());
            qu.setOption2(que.getOption2());
            qu.setOption4(que.getOption4());
            qu.setOption3(que.getOption3());
            qu.setQuestion(que.getQuestion());
            qu.setQuestionId(que.getQid());
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
        if (qr.findAll().size() != 0) {
            if (qr.findById(id).isPresent()) {
                qr.deleteById(id);
                LOGGER.info("delete question");
            } else {
                LOGGER.error("wrong question Id,enter a valid Id");
                throw new NotFoundException(
                        "wrong question Id,enter a valid Id");
            }
        } else {
            LOGGER.error("no question is present");
            throw new AllNotFoundException("no question is present");
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
        if (qr.findAll().size() != 0) {
        Optional<Questions> existingQue = qr.findById(id);
        if (existingQue.isPresent()) {
            Questions exiQue = existingQue.get();
            exiQue.setOption1(q.getOption1());
            exiQue.setOption2(q.getOption2());
            exiQue.setOption3(q.getOption3());
            exiQue.setOption4(q.getOption4());
            exiQue.setCorrectOption(q.getCorrectOption());
            exiQue.setQuestion(q.getQuestion());
            qr.save(exiQue);
            LOGGER.info("update question");
            return q;
        } else {
            LOGGER.error("wrong question Id,enter a valid Id");
            throw new NotFoundException("wrong question Id,enter a valid Id");
        }
        } else {
            LOGGER.error("no question is present");
            throw new AllNotFoundException("no question is present");
        }
    }

    /**
     * find by question id.
     * @param id question id
     * @return questions
     */
    @Override
    public final List<QuestionsDto> findQueById(final int id) {
        if (qr.findAll().size() != 0) {
            if (qr.findQueById(id).size() != 0) {
                List<Questions> l = qr.findQueById(id);
                List<QuestionsDto> ld = convertToDto(l);
                LOGGER.info("find question by question id");
                return ld;
            } else {
                LOGGER.error("wrong question Id,enter a valid Id");
                throw new NotFoundException(
                        "wrong question Id,enter a valid Id");
            }
        } else {
            LOGGER.error("no question is present");
            throw new AllNotFoundException("no question is present");
        }
    }

    /**
     * find by question method.
     * @param name question name
     * @return questions
     */
    @Override
    public final Optional<QuestionsDto> findByQuestion(final String name) {
        if (qr.findAll().size() != 0) {
            if (qr.findByQuestion(name).isPresent()) {
                QuestionsDto qu = new QuestionsDto();
                Optional<Questions> q = qr.findByQuestion(name);
                Questions que = q.get();
                qu.setQuizId(que.getQui().getQuizId());
                qu.setCorrectOption(que.getCorrectOption());
                qu.setOption1(que.getOption1());
                qu.setOption2(que.getOption2());
                qu.setOption4(que.getOption4());
                qu.setOption3(que.getOption3());
                qu.setQuestion(que.getQuestion());
                qu.setQuestionId(que.getQid());
                LOGGER.info("find question");
                return Optional.of(qu);
            } else {
                LOGGER.error("Question is not there, enter a valid question");
                throw new NotFoundException(
                        "Question is not there, enter a valid question");
            }
        } else {
            LOGGER.error("no question is present");
            throw new AllNotFoundException("no question is present");
        }
    }

}
