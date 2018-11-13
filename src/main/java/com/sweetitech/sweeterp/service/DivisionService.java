package com.sweetitech.sweeterp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sweetitech.sweeterp.model.dto.DivisionDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.user.Division;
import com.sweetitech.sweeterp.repository.DivisionRepository;
import com.sweetitech.sweeterp.service.interfaces.IDivisionService;

@Service
@Transactional
public class DivisionService implements IDivisionService {

	@Autowired
	private DivisionRepository divisionRepository;

	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public Division findById(long id) {
		return divisionRepository.findById(id);
	}

	@Override
	public void delete(Division division) {
		divisionRepository.delete(division);
	}

	@Override
	public Division updateDivision(Division division) {
		return divisionRepository.save(division);
	}

	@Override
	public Page<Division> findAllDivision(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return divisionRepository.findAll(request);
	}


	@Override
	public DivisionDto findDivisionDtoById(Long id) {
		// TODO Auto-generated method stub
		return convertToDto(divisionRepository.findById(id));
	}

	@Override
	public Page<DivisionDto> findAllDivisionDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Division> roles = divisionRepository.findAll(request);

		Page<DivisionDto> dtoPage = roles.map(new Converter<Division, DivisionDto>() {
			@Override
			public DivisionDto convert(Division entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public DivisionDto addDivisionDto(DivisionDto divisionDto) {
		// TODO Auto-generated method stub
		Division division = divisionRepository.save(convertToEntity(divisionDto));
		return convertToDto(division);
	}
	

	@Override
	public DivisionDto updateDivisionDto(DivisionDto divisionDto) {
		return convertToDto(divisionRepository.save(convertToEntity(divisionDto)));
	}

	@Override
	public void deleteDivisionByDto(DivisionDto divisionDto) {
		divisionRepository.delete(convertToEntity(divisionDto));
	}
	
	
	private DivisionDto convertToDto(Division division) {
		DivisionDto divisionDto = modelMapper.map(division, DivisionDto.class);
		return divisionDto;
	}
	
	
	private Division convertToEntity(DivisionDto divisionDto) {
		Division division = modelMapper.map(divisionDto, Division.class);
		return division;
	}
	
	@Override
	public List<DivisionDto> search(String searchString) {
		List<Division> areas = divisionRepository.findByNameContaining(searchString);
		List<DivisionDto> areaDtos = new ArrayList<DivisionDto>();
		for(int i=0; i < areas.size();i++) {
			DivisionDto temp = convertToDto(areas.get(i));
			areaDtos.add(temp);
		}
		return areaDtos;
	}
}