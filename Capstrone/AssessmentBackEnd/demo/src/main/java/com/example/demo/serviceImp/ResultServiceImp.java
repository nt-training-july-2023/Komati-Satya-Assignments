package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.FinalRes;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentResult;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.FinalResultRepo;
import com.example.demo.repository.QuizRepo;
import com.example.demo.repository.StudentRepo;
import com.example.demo.repository.StudentResultRepo;
import com.example.demo.service.ResultService;
import com.example.demo.validationMessages.ErrorMessages;
import com.example.demo.validationMessages.Messages;

/**
 * result service implementation.
 */
@Service
public class ResultServiceImp implements ResultService {
    /**
     * auto wiring student result repository.
     */
    @Autowired
    private StudentResultRepo studentResultRepo;
    /**
     * auto wiring final result repository.
     */
    @Autowired
    private FinalResultRepo finalResultRepo;
    /**
     * auto wiring final quiz repository.
     */
    @Autowired
    private QuizRepo quizRepo;
    /**
     * auto wiring final category repository.
     */
    @Autowired
    private CategoryRepo categoryRepo;
    /**
     * auto wiring final student repository.
     */
    @Autowired
    private StudentRepo studentRepo;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ResultServiceImp.class);

    /**
     * add result method.
     * @param resultDto studentResult
     * @return student result
     */
    @Override
    public final ResultDto addResult(final ResultDto resultDto) {
        FinalRes finalResult = new FinalRes();
        if (studentRepo.findByEmail(resultDto.getEmail()).isPresent()) {
            finalResult.setCategoryName(resultDto.getCategoryName());
            finalResult.setUserId(resultDto.getUserId());
            finalResult.setUserName(resultDto.getUserName());
            finalResult.setQuizTopic(resultDto.getQuizName());
            finalResult.setDateAndTime(resultDto.getDateAndTime());
            finalResult.setMarks(resultDto.getObtainMarks());
            finalResult.setMaxMarks(resultDto.getMaxMarks());
            finalResult.setResultId(resultDto.getResultId());
            finalResult
                    .setAttemptedQuestions(resultDto.getAttemptedQuestions());
            finalResult.setCategoryId(resultDto.getCategoryId());
            finalResult.setCategoryName(resultDto.getCategoryName());
            finalResult.setEmail(resultDto.getEmail());
            finalResult.setTotalNoOfQuestions(resultDto.getTotalQuestions());
            if (quizRepo.findQuizByName(finalResult.getQuizTopic())
                    .isPresent()) {
                if (categoryRepo
                        .findByCategoryName(finalResult.getCategoryName())
                        .isPresent()) {
                    LOGGER.info(Messages.SAVE_RESULT);
                    finalResultRepo.save(finalResult);
                } else {
                    LOGGER.error(ErrorMessages.CATEGORY_NOTPRESENT);
                    throw new NotFoundException(
                            ErrorMessages.CATEGORY_NOTPRESENT);
                }

            } else {
                LOGGER.error(ErrorMessages.QUIZ_NOTPRESENT);
                throw new NotFoundException(ErrorMessages.QUIZ_NOTPRESENT);
            }
            finalResultRepo.save(finalResult);
            StudentResult studentResult = new StudentResult();
            studentResult
                    .setAttemptedQuestions(resultDto.getAttemptedQuestions());
            studentResult.setCategoryId(resultDto.getCategoryId());
            studentResult.setDateAndTime(resultDto.getDateAndTime());
            Optional<Quiz> q = quizRepo.findQuizByName(resultDto.getQuizName());
            Quiz q1 = q.get();
            studentResult.setQuiz(q1);
            Optional<Student> student = studentRepo
                    .findByEmail(resultDto.getEmail());
            Student s = student.get();
            studentResult.setStudentResult(s);
            studentResult.setResult(resultDto.getResult());
            studentResult.setMaxMarks(resultDto.getObtainMarks());
            studentResult.setResultId(resultDto.getResultId());
            studentResultRepo.save(studentResult);
        } else {
            LOGGER.error(ErrorMessages.WRONG_EMAIL);
            throw new NotFoundException(ErrorMessages.WRONG_EMAIL);
        }
        return resultDto;
    }
    /**
     * get result method.
     * @param id student id
     * @return student result
     */
    @Override
    public final Optional<ResultDto> getResult(final int id) {
        if (studentResultRepo.findAll().size() != 0) {
            Optional<StudentResult> r = studentResultRepo.findById(id);
            if (r.isPresent()) {
                ResultDto resultDto = new ResultDto();
                StudentResult fr = r.get();
                resultDto.setUserName(fr.getStudentResult().getUserName());
                resultDto.setEmail(fr.getStudentResult().getEmail());
                Optional<Category> cc = categoryRepo
                        .findById(fr.getCategoryId());
                Category c = cc.get();
                resultDto.setCategoryName(c.getCategoryName());
                resultDto.setQuizName(fr.getQuiz().getTopicName());
                resultDto.setResult(fr.getResult());
                resultDto.setDateAndTime(fr.getDateAndTime());
                resultDto.setObtainMarks(fr.getMaxMarks());
                resultDto.setAttemptedQuestions(fr.getAttemptedQuestions());
                resultDto.setCategoryId(c.getCategoryId());
                LOGGER.info(Messages.FIND_RESULT);
                return Optional.of(resultDto);
            } else {
                LOGGER.error(ErrorMessages.WRONG_USERID);
                throw new NotFoundException(ErrorMessages.WRONG_USERID);
            }
        } else {
            LOGGER.error(ErrorMessages.NO_USER);
            throw new AllNotFoundException(ErrorMessages.NO_USER);
        }
    }
    /**
     * get all result method.
     * @return student result
     */
    @Override
    public final List<ResultDto> getResults() {
        if (studentResultRepo.findAll().size() != 0) {
            List<StudentResult> studentResult = studentResultRepo.findAll();
            List<ResultDto> resultDto = convertToDto(studentResult);
            LOGGER.info(Messages.FIND_ALLRESULT);
            return resultDto;
        } else {
            LOGGER.info(ErrorMessages.NO_USER);
            throw new AllNotFoundException(ErrorMessages.NO_USER);
        }
    }
    /**
     * convert to dto method.
     * @param sr student result
     * @return list of result
     */
    private List<ResultDto> convertToDto(final List<StudentResult> sr) {
        List<ResultDto> rd = new ArrayList<>();
        int i = 0;
        for (StudentResult studentResult : sr) {
            int n = sr.get(i).getCategoryId();
            Optional<Category> c = categoryRepo.findById(n);
            ResultDto resultDto = new ResultDto();
            resultDto.setUserName(studentResult.getStudentResult().
                    getUserName());
            resultDto.setEmail(studentResult.getStudentResult().getEmail());
            resultDto.setCategoryName(c.get().getCategoryName());
            resultDto.setQuizName(studentResult.getQuiz().getTopicName());
            resultDto.setResult(studentResult.getResult());
            resultDto.setDateAndTime(studentResult.getDateAndTime());
            resultDto.setObtainMarks(studentResult.getMaxMarks());
            resultDto.setCategoryId(c.get().getCategoryId());
            resultDto.setAttemptedQuestions(
                    studentResult.getAttemptedQuestions());
            resultDto.setMaxMarks(studentResult.getMaxMarks());
            rd.add(resultDto);
            i++;
        }
        return rd;
    }

}
