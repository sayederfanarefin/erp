package com.sweetitech.sweeterp.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.CartonDto;

import com.sweetitech.sweeterp.model.product.Carton;

public interface ICartonService {
	Carton findById(long id);
    void delete(Carton carton);
    Carton updateCarton(Carton carton);
    Page<Carton> findAllCarton( int page);
  
    CartonDto findCartonDtoById(Long id);
    Page<CartonDto> findAllCartonDto( int page);
    
    
    
    CartonDto addCartonDto(CartonDto cartonDto);
    CartonDto updateCartonDto(CartonDto cartonDto);
    void deleteCartonByDto(CartonDto cartonDto);
    
    List<CartonDto> search(String searchString);
    
    
}
