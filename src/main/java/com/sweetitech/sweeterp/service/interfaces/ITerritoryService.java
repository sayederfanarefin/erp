package com.sweetitech.sweeterp.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.TerritoryDto;

import com.sweetitech.sweeterp.model.user.Territory;

public interface ITerritoryService {
	Territory findById(long id);
    void delete(Territory territory);
    Territory updateTerritory(Territory territory);
    Page<Territory> findAllTerritory( int page);
  
    TerritoryDto findTerritoryDtoById(Long id);
    Page<TerritoryDto> findAllTerritoryDto( int page);
    
    TerritoryDto addTerritoryDto(TerritoryDto territoryDto);
    TerritoryDto updateTerritoryDto(TerritoryDto territoryDto);
    void deleteTerritoryByDto(TerritoryDto territoryDto);
    
    Page<TerritoryDto> findAllTerritoryDtoByArea(Long areaId, int page);
    
    List<TerritoryDto> search(String searchString);
    
    List<TerritoryDto> searchByArea(String searchString, Long areaId);
}
