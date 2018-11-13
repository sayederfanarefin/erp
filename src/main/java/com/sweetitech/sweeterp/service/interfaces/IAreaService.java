package com.sweetitech.sweeterp.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.AreaDto;

import com.sweetitech.sweeterp.model.user.Area;

public interface IAreaService {
	Area findById(long id);
    void delete(Area area);
    Area updateArea(Area area);
    Page<Area> findAllArea( int page);
  
    AreaDto findAreaDtoById(Long id);
    Page<AreaDto> findAllAreaDto( int page);
    
    
    Page<AreaDto> findAllAreaDtoByDivision(Long divisionId, int page);
    
    
    AreaDto addAreaDto(AreaDto areaDto);
    AreaDto updateAreaDto(AreaDto areaDto);
    void deleteAreaByDto(AreaDto areaDto);
    
    List<AreaDto> search(String searchString);
    
    List<AreaDto> searchByDivision(String searchString, Long divisionId);
    
}
