package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
/**
 * result service implementation.
 */
@Service
public class ResultServiceImp implements ResultService {
    /**
     * auto wiring student result repository.
     */
    @Autowired
    private StudentResultRepo s;
    /**
     * auto wiring final result repository.
     */
    @Autowired
    private FinalResultRepo f;
    /**
     * auto wiring final quiz repository.
     */
    @Autowired
    private QuizRepo qr;
    /**
     * auto wiring final category repository.
     */
    @Autowired
    private CategoryRepo cr;
    /**
     * auto wiring final student repository.
     */
    @Autowired
    private StudentRepo srr;
   /**
    * constructor.
    * @param resultRepo result repository
    * @param finalRepo final repository
    * @param categoryRepo category repository
    * @param studentRepo student repository
    * @param quizRepo quiz repository
    */
    public ResultServiceImp(final StudentResultRepo resultRepo,
           final FinalResultRepo finalRepo,
           final CategoryRepo categoryRepo,
            final StudentRepo studentRepo,
            final QuizRepo quizRepo) {
       this.cr = categoryRepo;
       this.f = finalRepo;
       this.qr = quizRepo;
       this.s = resultRepo;
       this.srr = studentRepo;
    }

    /**
     * add result method.
     * @param sr studentResult
     * @return student result
     */
    @Override
    public final ResultDto addRes(final ResultDto sr) {
        FinalRes fr = new FinalRes();
       if (srr.findByEmail(sr.getEmail()).isPresent()) {
      // Optional<Student> ssss=srr.findByEmail(sr.getEmail());
        fr.setCategoryName(sr.getCategoryName());
         fr.setUserId(sr.getUserId());
        fr.setUserName(sr.getUserName());
        fr.setQuizTopic(sr.getQuizName());
        fr.setDateAndTime(sr.getDateAndTime());
        fr.setMarks(sr.getObtainMarks());
        fr.setMaxMarks(sr.getMaxMarks());
        fr.setResultId(sr.getResultId());
        fr.setAttemptedQuestions(sr.getAttemptedQuestions());
        fr.setCategoryId(sr.getCategoryId());
        fr.setCategoryName(sr.getCategoryName());
        fr.setEmail(sr.getEmail());
        fr.setTotalNoOfQuestions(sr.getTotalQuestions());
        if (qr.findQuizByName(fr.getQuizTopic()).isPresent()) {
            if (cr.findByCategoryName(fr.getCategoryName()).isPresent()) {
                    f.save(fr);
            } else {
                throw new NotFoundException("Category is not present");
            }

        } else {
            throw new NotFoundException("Quiz is not present");
        }
        f.save(fr);
        StudentResult ss = new StudentResult();
        ss.setAttemptedQuestions(sr.getAttemptedQuestions());
        ss.setCategoryId(sr.getCategoryId());
        ss.setDateAndTime(sr.getDateAndTime());
        Optional<Quiz> q = qr.findQuizByName(sr.getQuizName());
        Quiz q1 = q.get();
        ss.setQe(q1);
        Optional<Student> student = srr.findByEmail(sr.getEmail());
        Student sss = student.get();
        ss.setSs(sss);
        ss.setResult(sr.getResult());
        ss.setMaxMarks(sr.getObtainMarks());
        ss.setResultId(sr.getResultId());
        s.save(ss);
       } else {
           throw new NotFoundException("wrong email id");
       }
        return sr;
    }

    /**
     * get result method.
     * @param id student id
     * @return student result
     */
    @Override
    public final Optional<ResultDto> getRes(final int id) {
        if (s.findAll().size() != 0) {
            Optional<StudentResult> r = s.findById(id);
            if (r.isPresent()) {
                ResultDto resultDto = new ResultDto();
                StudentResult fr = r.get();
                resultDto.setUserName(fr.getSs().getUserName());
                resultDto.setEmail(fr.getSs().getEmail());
                Optional<Category> cc = cr.findById(fr.getCategoryId());
                Category c = cc.get();
                resultDto.setCategoryName(c.getCategoryName());
                resultDto.setQuizName(fr.getQe().getTopicName());
                resultDto.setResult(fr.getResult());
                resultDto.setDateAndTime(fr.getDateAndTime());
                resultDto.setObtainMarks(fr.getMaxMarks());
                resultDto.setAttemptedQuestions(fr.getAttemptedQuestions());
                resultDto.setCategoryId(c.getCategoryId());
                return Optional.of(resultDto);
            } else {
                throw new NotFoundException("wrong user Id,enter a valid Id");
            }
        } else {
            throw new AllNotFoundException("no studentresult is present");
        }
    }
    /**
     * get all result method.
     * @return student result
     */
    @Override
    public final List<ResultDto> getAllRes() {
        if (s.findAll().size() != 0) {
            List<StudentResult> sr  = s.findAll();
            List<ResultDto> resultDto = convertToDto(sr);
            return resultDto;
        } else {
            throw new AllNotFoundException("no studentresult is present");
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
        for (StudentResult fr:sr) {
            int n = sr.get(i).getCategoryId();
            Optional<Category> c = cr.findById(n);
            //Category cc=c.get();
            ResultDto resultDto = new ResultDto();
            resultDto.setUserName(fr.getSs().getUserName());
            resultDto.setEmail(fr.getSs().getEmail());
            resultDto.setCategoryName(c.get().getCategoryName());
            resultDto.setQuizName(fr.getQe().getTopicName());
            resultDto.setResult(fr.getResult());
            resultDto.setDateAndTime(fr.getDateAndTime());
            resultDto.setObtainMarks(fr.getMaxMarks());
            resultDto.setCategoryId(c.get().getCategoryId());
            resultDto.setAttemptedQuestions(fr.getAttemptedQuestions());
            resultDto.setMaxMarks(fr.getMaxMarks());
            rd.add(resultDto);
            i++;
        }
        return rd;
    }

}
