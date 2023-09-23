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
     * constructor.
     * @param questionssRepo questions repository
     * @param quizzRepo quiz repository
     */
    public QuestionServiceImp(final QuestionsRepo questionssRepo,
            final QuizRepo quizzRepo) {
        this.questionsRepo = questionssRepo;
        this.quizRepo = quizzRepo;
    }
    /**
     * add question method.
     * @param questions question
     * @return question
     */
    @Override
    public final QuestionsDto addQuestion(final Questions questions) {
        if (quizRepo.findById(questions.getQui().getQuizId()).isPresent()) {
            if (!questionsRepo.findByQuestion(questions.getQuestion())
                    .isPresent()) {
                QuestionsDto qu = new QuestionsDto();
                qu.setQuizId(questions.getQui().getQuizId());
                qu.setCorrectOption(questions.getCorrectOption());
                qu.setOption1(questions.getOption1());
                qu.setOption2(questions.getOption2());
                qu.setOption4(questions.getOption4());
                qu.setOption3(questions.getOption3());
                qu.setQuestion(questions.getQuestion());
                questionsRepo.save(questions);
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
        if (questionsRepo.findAll().size() != 0) {
            List<Questions> questions = questionsRepo.findAll();
            List<QuestionsDto> qd = convertToDto(questions);
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
        if (questionsRepo.findAll().size() != 0) {
            Optional<Questions> existingQue = questionsRepo.findById(id);
            if (existingQue.isPresent()) {
                Questions exiQue = existingQue.get();
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
     * find by question id.
     * @param id question id
     * @return questions
     */
    @Override
    public final List<QuestionsDto> findQueById(final int id) {
        if (questionsRepo.findAll().size() != 0) {
            if (questionsRepo.findQueById(id).size() != 0) {
                List<Questions> l = questionsRepo.findQueById(id);
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
