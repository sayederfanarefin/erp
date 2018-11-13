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
import com.sweetitech.sweeterp.model.dto.TerritoryDto;

import com.sweetitech.sweeterp.config.Constants;
import com.sweetitech.sweeterp.model.user.Territory;
import com.sweetitech.sweeterp.repository.TerritoryRepository;
import com.sweetitech.sweeterp.service.interfaces.ITerritoryService;

@Service
@Transactional
public class TerritoryService implements ITerritoryService {

	@Autowired
	private TerritoryRepository territoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public Territory findById(long id) {
		return territoryRepository.findById(id);
	}

	@Override
	public void delete(Territory territory) {
		territoryRepository.delete(territory);
	}

	@Override
	public Territory updateTerritory(Territory territory) {
		return territoryRepository.save(territory);
	}

	@Override
	public Page<Territory> findAllTerritory(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return territoryRepository.findAll(request);
	}


	@Override
	public TerritoryDto findTerritoryDtoById(Long id) {
		// TODO Auto-generated method stub
		return convertToDto(territoryRepository.findById(id));
	}

	@Override
	public Page<TerritoryDto> findAllTerritoryDto(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Territory> roles = territoryRepository.findAll(request);

		Page<TerritoryDto> dtoPage = roles.map(new Converter<Territory, TerritoryDto>() {
			@Override
			public TerritoryDto convert(Territory entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public TerritoryDto addTerritoryDto(TerritoryDto territoryDto) {
		// TODO Auto-generated method stub
		Territory territory = territoryRepository.save(convertToEntity(territoryDto));
		return convertToDto(territory);
	}
	

	@Override
	public TerritoryDto updateTerritoryDto(TerritoryDto territoryDto) {
		return convertToDto(territoryRepository.save(convertToEntity(territoryDto)));
	}

	@Override
	public void deleteTerritoryByDto(TerritoryDto territoryDto) {
		territoryRepository.delete(convertToEntity(territoryDto));
	}
	
	
	private TerritoryDto convertToDto(Territory territory) {
		TerritoryDto territoryDto = modelMapper.map(territory, TerritoryDto.class);
		return territoryDto;
	}
	
	
	private Territory convertToEntity(TerritoryDto territoryDto) {
		Territory territory = modelMapper.map(territoryDto, Territory.class);
		return territory;
	}

	@Override
	public Page<TerritoryDto> findAllTerritoryDtoByArea(Long territoryId, int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Territory> territories = territoryRepository.findAllByArea_Id(territoryId, request);
		Page<TerritoryDto> dtoPage = territories.map(new Converter<Territory, TerritoryDto>() {
			@Override
			public TerritoryDto convert(Territory entity) {
				return convertToDto(entity);
			}
		});

		return dtoPage;
	}

	@Override
	public List<TerritoryDto> search(String searchString) {
		List<Territory> territorys = territoryRepository.findByNameContaining(searchString);
		List<TerritoryDto> territoryDtos = new ArrayList<TerritoryDto>();
		for(int i=0; i < territorys.size();i++) {
			TerritoryDto temp = convertToDto(territorys.get(i));
			territoryDtos.add(temp);
		}
		return territoryDtos;
	}

	@Override
	public List<TerritoryDto> searchByArea(String searchString, Long territoryId) {
		List<Territory> territorys = territoryRepository.findByNameContainingAndArea_Id(searchString, territoryId);
		List<TerritoryDto> territoryDtos = new ArrayList<TerritoryDto>();
		for(int i=0; i < territorys.size();i++) {
			TerritoryDto temp = convertToDto(territorys.get(i));
			territoryDtos.add(temp);
		}
		return territoryDtos;
	}
	
}