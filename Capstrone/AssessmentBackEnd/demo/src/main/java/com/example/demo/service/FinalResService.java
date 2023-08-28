package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.FinalRes;

public interface FinalResService {
	
	Optional<FinalRes> getById(int id);

}
