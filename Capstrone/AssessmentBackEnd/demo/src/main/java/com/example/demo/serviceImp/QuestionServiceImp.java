package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                return qu;
            } else {
                throw new AlreadyExistException("question already exist");
            }
        } else {
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
            return qd;
        } else {
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
            } else {
                throw new NotFoundException(
                        "wrong question Id,enter a valid Id");
            }
        } else {
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
            return q;
        } else {
            throw new NotFoundException("wrong question Id,enter a valid Id");
        }
        } else {
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
                List<Questions> l = qr.findAll();
                List<QuestionsDto> ld = convertToDto(l);
                return ld;
            } else {
                throw new NotFoundException(
                        "wrong question Id,enter a valid Id");
            }
        } else {
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
                return Optional.of(qu);
            } else {
                throw new NotFoundException(
                        "Question is not there, enter a valid question");
            }
        } else {
            throw new AllNotFoundException("no question is present");
        }
    }

}
