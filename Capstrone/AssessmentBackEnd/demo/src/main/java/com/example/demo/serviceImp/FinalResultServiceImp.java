package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.FinalRes;

import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.FinalResultRepo;
import com.example.demo.repository.StudentResultRepo;
import com.example.demo.service.FinalResService;
import com.example.demo.validationMessages.ErrorMessages;
import com.example.demo.validationMessages.Messages;

/**
 * Final result service interface.
 */
@SuppressWarnings("unused")
@Service
public class FinalResultServiceImp implements FinalResService {
    /**
     * auto wiring final result repository.
     */
    @Autowired
    private FinalResultRepo finalResultRepo;
    /**
     * auto wiring student result repository.
     */
    @Autowired
    private StudentResultRepo studentResultRepo;
    /**
     * auto wiring final category repository.
     */
    @Autowired
    private CategoryRepo categoryRepo;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(FinalResultServiceImp.class);
    /**
     * get by id method.
     * @param id final result id
     * @return final result
     */
    @Override
    public final List<ResultDto> getById(final int id) {
        if (finalResultRepo.findAll().size() != 0) {
                List<FinalRes> finalRes = finalResultRepo.getByUserId(id);
                List<ResultDto> resultDto = convertToDto(finalRes);
                LOGGER.info(Messages.FIND_RESULT);
                return resultDto;
        } else {
            LOGGER.error(ErrorMessages.NO_USER);
            throw new AllNotFoundException(ErrorMessages.NO_USER);
        }
    }
    /**
     * convert to dto method.
     * @param finalRes final result
     * @return list of result
     */
    private List<ResultDto> convertToDto(final List<FinalRes> finalRes) {
        List<ResultDto> resultList = new ArrayList<>();
        for (FinalRes finalResult : finalRes) {
            ResultDto resultDto = new ResultDto();
            resultDto.setCategoryName(finalResult.getCategoryName());
            resultDto.setDateAndTime(finalResult.getDateAndTime());
            resultDto.setDateAndTime(finalResult.getDateAndTime());
            resultDto.setQuizName(finalResult.getQuizTopic());
            resultDto.setUserName(finalResult.getUserName());
            resultDto.setObtainMarks(finalResult.getMarks());
            resultDto.setCategoryId(finalResult.getCategoryId());
            resultDto.setAttemptedQuestions(finalResult.
                    getAttemptedQuestions());
            resultDto.setEmail(finalResult.getEmail());
            resultDto.setMaxMarks(finalResult.getMaxMarks());
            resultDto.setUserId(finalResult.getUserId());
            resultDto.setTotalQuestions(finalResult.getTotalNoOfQuestions());
            resultDto.setUserId(finalResult.getUserId());
            resultList.add(resultDto);
        }
        return resultList;
    }
    /**
     * find all methods.
     */
    @Override
    public final List<ResultDto> findAll() {
        if (finalResultRepo.findAll().size() != 0) {
            List<FinalRes> finalResult = finalResultRepo.findAll();
            List<ResultDto> resultDto = convertToDto(finalResult);
            LOGGER.info(Messages.FIND_ALLRESULT);
            return resultDto;
        } else {
            LOGGER.error(ErrorMessages.NO_USER);
            throw new AllNotFoundException(ErrorMessages.NO_USER);
        }
    }
}
