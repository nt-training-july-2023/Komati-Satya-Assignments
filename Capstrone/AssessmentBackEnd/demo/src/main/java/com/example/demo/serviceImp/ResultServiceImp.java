package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FinalRes;
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
     * add result method.
     * @param sr studentResult
     * @return student result
     */
    @Override
    public final StudentResult addRes(final StudentResult sr) {
        FinalRes fr = new FinalRes();
        fr.setCategoryName(sr.getQe().getCate().getCategoryName());
        fr.setUserId(sr.getSs().getUserId());
        fr.setUserName(sr.getSs().getUserName());
        fr.setQuizTopic(sr.getQe().getTopicName());
        fr.setResult(sr.getResult());
        fr.setDateAndTime(sr.getDateAndTime());
        fr.setMarks(sr.getQe().getMaxMarks());
        fr.setResultId(sr.getResultId());
        fr.setMaxMarks(sr.getMaxMarks());
        fr.setEmail(sr.getSs().getEmail());

        if (qr.findQuizByName(fr.getQuizTopic()).isPresent()) {
            if (cr.findByCategoryName(fr.getCategoryName()).isPresent()) {
                if (srr.findById(fr.getUserId()).isPresent()) {
                    if (srr.findByEmail(fr.getEmail()).isPresent()) {
                        f.save(fr);
                    } else {
                        throw new NotFoundException("wrong email id");
                    }
                } else {
                    throw new NotFoundException("wrong userId");
                }
            } else {
                throw new NotFoundException("Category is not present");
            }

        } else {
            throw new NotFoundException("Quiz is not present");
        }
        f.save(fr);

        return s.save(sr);
    }

    /**
     * get result method.
     * @param id student id
     * @return student result
     */
    @Override
    public final Optional<StudentResult> getRes(final int id) {
        if (s.findAll().size() != 0) {
            if (s.findById(id).isPresent()) {
                return s.findById(id);
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
    public final List<StudentResult> getAllRes() {
        if (s.findAll().size() != 0) {
            return s.findAll();
        } else {
            throw new AllNotFoundException("no studentresult is present");
        }
    }

}
