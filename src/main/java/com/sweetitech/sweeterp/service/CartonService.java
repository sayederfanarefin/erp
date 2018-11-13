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
import com.sweetitech.sweeterp.model.dto.CartonDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.product.Carton;
import com.sweetitech.sweeterp.repository.CartonRepository;
import com.sweetitech.sweeterp.service.interfaces.ICartonService;

@Service
@Transactional
public class CartonService implements ICartonService {

	@Autowired
	private CartonRepository cartonRepository;

	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public Carton findById(long id) {
		return cartonRepository.findById(id);
	}

	@Override
	public void delete(Carton division) {
		cartonRepository.delete(division);
	}

	@Override
	public Carton updateCarton(Carton division) {
		return cartonRepository.save(division);
	}

	@Override
	public Page<Carton> findAllCarton(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return cartonRepository.findAll(request);
	}


	@Override
	public CartonDto findCartonDtoById(Long id) {
		// TODO Auto-generated method stub
		return convertToDto(cartonRepository.findById(id));
	}

	@Override
	public Page<CartonDto> findAllCartonDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Carton> roles = cartonRepository.findAll(request);

		Page<CartonDto> dtoPage = roles.map(new Converter<Carton, CartonDto>() {
			@Override
			public CartonDto convert(Carton entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public CartonDto addCartonDto(CartonDto cartonDto) {
		// TODO Auto-generated method stub
		Carton division = cartonRepository.save(convertToEntity(cartonDto));
		return convertToDto(division);
	}
	

	@Override
	public CartonDto updateCartonDto(CartonDto cartonDto) {
		return convertToDto(cartonRepository.save(convertToEntity(cartonDto)));
	}

	@Override
	public void deleteCartonByDto(CartonDto cartonDto) {
		cartonRepository.delete(convertToEntity(cartonDto));
	}
	
	
	private CartonDto convertToDto(Carton division) {
		CartonDto cartonDto = modelMapper.map(division, CartonDto.class);
		return cartonDto;
	}
	
	
	private Carton convertToEntity(CartonDto cartonDto) {
		Carton division = modelMapper.map(cartonDto, Carton.class);
		return division;
	}

	

	@Override
	public List<CartonDto> search(String searchString) {
		List<Carton> cartons = cartonRepository.findByNameContaining(searchString);
		List<CartonDto> cartonDtos = new ArrayList<CartonDto>();
		for(int i=0; i < cartons.size();i++) {
			CartonDto temp = convertToDto(cartons.get(i));
			cartonDtos.add(temp);
		}
		return cartonDtos;
	}

	
}