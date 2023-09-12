package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.FinalRes;

import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.FinalResultRepo;
import com.example.demo.repository.StudentResultRepo;
import com.example.demo.service.FinalResService;

/**
 * Final result service interface.
 */
@Service
public class FinalResultServiceImp implements FinalResService {
    /**
     * auto wiring final result repository.
     */
    @Autowired
    private FinalResultRepo fs;
    /**
     * auto wiring student result repository.
     */
    @Autowired
    private StudentResultRepo s;
    /**
     * auto wiring final category repository.
     */
    @Autowired
    private CategoryRepo cr;
    /**
     * constructor.
     * @param finalRepo final repository
     * @param categoryRepo category repository
     * @param resultRepo result repository
     */
    public FinalResultServiceImp(final FinalResultRepo finalRepo,
           final CategoryRepo categoryRepo,
           final StudentResultRepo resultRepo) {
       this.fs = finalRepo;
       this.cr = categoryRepo;
       this.s = resultRepo;
    }
    /**
     * get by id method.
     * @param id final result id
     * @return final result
     */
    @Override
    public final List<ResultDto> getById(final int id) {
        if (fs.findAll().size() != 0) {
            if (fs.getByUserId(id).size() != 0) {
            List<FinalRes> finalRes = fs.getByUserId(id);
            List<ResultDto> resultDto = convertToDto(finalRes);
                return resultDto;
            } else {
                throw new NotFoundException("User did not take the test");
            }
        } else {
            throw new AllNotFoundException("No user is there");
        }
    }
     /**
      * convert to dto method.
      * @param finalRes final result
      * @return list of result
      */
     private List<ResultDto> convertToDto(final List<FinalRes> finalRes) {
        List<ResultDto> r = new ArrayList<>();
        for (FinalRes f:finalRes) {
            ResultDto resultDto = new ResultDto();
            resultDto.setCategoryName(f.getCategoryName());
            resultDto.setDateAndTime(f.getDateAndTime());
            resultDto.setDateAndTime(f.getDateAndTime());
            resultDto.setQuizName(f.getQuizTopic());
            resultDto.setUserName(f.getUserName());
            resultDto.setResult(f.getResult());
            resultDto.setObtainMarks(f.getMarks());
         //   Optional<Category> c=cr.findByCategoryName(f.getCategoryName());
            resultDto.setCategoryId(f.getCategoryId());
            resultDto.setAttemptedQuestions(f.getAttemptedQuestions());
            resultDto.setEmail(f.getEmail());
            resultDto.setResultId(f.getResultId());
            resultDto.setMaxMarks(f.getMaxMarks());
            resultDto.setUserId(f.getUserId());
            resultDto.setTotalQuestions(f.getTotalNoOfQuestions());
            resultDto.setUserId(f.getUserId());
//            resultDto.setTotalQuestions(f.);
            r.add(resultDto);
        }
      return r;
}
    @Override
    public List<ResultDto> findAll() {
       if(fs.findAll().size()!= 0) {
           List<FinalRes> finalResult = fs.findAll();
           List<ResultDto> resultDto = convertToDto(finalResult);
           return resultDto;
       }
       else {
           throw new AllNotFoundException("No results are there");
       }
    }
  }
