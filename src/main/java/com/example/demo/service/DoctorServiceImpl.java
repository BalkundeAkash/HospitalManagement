package com.example.demo.service;

import javax.print.Doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Doctor;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	@Override
	public void saveDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		doctorRepository.save(doctor);
	}

	@Override
	public void deleteDoctor(int id) {
		// TODO Auto-generated method stub
		doctorRepository.deleteById(id);
	}

	@Override
	public Doctor getDoctor(int id) {
		// TODO Auto-generated method stub
		Doctor doctor = doctorRepository.findById(id).get();
		return doctor;
	}

}
