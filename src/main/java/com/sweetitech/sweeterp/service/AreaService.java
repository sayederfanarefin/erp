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
import com.sweetitech.sweeterp.model.dto.AreaDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.user.Area;
import com.sweetitech.sweeterp.repository.AreaRepository;
import com.sweetitech.sweeterp.service.interfaces.IAreaService;

@Service
@Transactional
public class AreaService implements IAreaService {

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public Area findById(long id) {
		return areaRepository.findById(id);
	}

	@Override
	public void delete(Area division) {
		areaRepository.delete(division);
	}

	@Override
	public Area updateArea(Area division) {
		return areaRepository.save(division);
	}

	@Override
	public Page<Area> findAllArea(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return areaRepository.findAll(request);
	}


	@Override
	public AreaDto findAreaDtoById(Long id) {
		// TODO Auto-generated method stub
		return convertToDto(areaRepository.findById(id));
	}

	@Override
	public Page<AreaDto> findAllAreaDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Area> roles = areaRepository.findAll(request);

		Page<AreaDto> dtoPage = roles.map(new Converter<Area, AreaDto>() {
			@Override
			public AreaDto convert(Area entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public AreaDto addAreaDto(AreaDto areaDto) {
		// TODO Auto-generated method stub
		Area division = areaRepository.save(convertToEntity(areaDto));
		return convertToDto(division);
	}
	

	@Override
	public AreaDto updateAreaDto(AreaDto areaDto) {
		return convertToDto(areaRepository.save(convertToEntity(areaDto)));
	}

	@Override
	public void deleteAreaByDto(AreaDto areaDto) {
		areaRepository.delete(convertToEntity(areaDto));
	}
	
	
	private AreaDto convertToDto(Area division) {
		AreaDto areaDto = modelMapper.map(division, AreaDto.class);
		return areaDto;
	}
	
	
	private Area convertToEntity(AreaDto areaDto) {
		Area division = modelMapper.map(areaDto, Area.class);
		return division;
	}

	@Override
	public Page<AreaDto> findAllAreaDtoByDivision(Long divisionId, int page) {
		
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Area> roles = areaRepository.findAllByDivision_Id(divisionId, request);
		Page<AreaDto> dtoPage = roles.map(new Converter<Area, AreaDto>() {
			@Override
			public AreaDto convert(Area entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public List<AreaDto> search(String searchString) {
		List<Area> areas = areaRepository.findByNameContaining(searchString);
		List<AreaDto> areaDtos = new ArrayList<AreaDto>();
		for(int i=0; i < areas.size();i++) {
			AreaDto temp = convertToDto(areas.get(i));
			areaDtos.add(temp);
		}
		return areaDtos;
	}

	@Override
	public List<AreaDto> searchByDivision(String searchString, Long divisionId) {
		List<Area> areas = areaRepository.findByNameContainingAndDivision_Id(searchString, divisionId);
		List<AreaDto> areaDtos = new ArrayList<AreaDto>();
		for(int i=0; i < areas.size();i++) {
			AreaDto temp = convertToDto(areas.get(i));
			areaDtos.add(temp);
		}
		return areaDtos;
	}
	
}