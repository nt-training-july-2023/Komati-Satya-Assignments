package com.example.demo.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.FinalRes;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.FinalResultRepo;
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
     * get by id method.
     * @param id final result id
     * @return final result
     */
    @Override
    public final Optional<FinalRes> getById(final int id) {
        if (fs.findAll().size() != 0) {
            if (fs.findById(id).isPresent()) {
                System.out.println(fs.findById(id));
                return fs.getByUserId(id);
            } else {
                throw new NotFoundException("User did not take the test");
            }
        } else {
            throw new AllNotFoundException("No user is there");
        }
    }
}
