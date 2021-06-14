package com.example.eshop.product.service.impl;

import com.example.eshop.product.service.dto.WatchDto;
import com.example.eshop.product.service.WatchService;
import com.example.eshop.product.persistence.repository.WatchRepository;
import com.example.eshop.product.persistence.entity.Watch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *  CRUD operations for Watches with entity | DTO conversion.
 */
@Service
@Transactional
public class WatchServiceImpl implements WatchService {

    private WatchRepository watchRepository;

    private ModelMapper modelMapper;

    @Autowired
    public WatchServiceImpl(WatchRepository watchRepository, ModelMapper modelMapper){
        this.watchRepository = watchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WatchDto create(WatchDto watchDto) {
        if(watchDto.getId() != null) {
            throw new InvalidParameterException("Unable to create watches with assigned id.");
        }
        Watch watch = convertToEntity(watchDto);
        return convertToDto(watchRepository.save(watch));
    }

    @Override
    public WatchDto update(WatchDto watchDto) {
        if(watchDto.getId() == null) {
            throw new InvalidParameterException("Watch id not specified.");
        }
        Watch watch = convertToEntity(watchDto);
        return convertToDto(watchRepository.save(watch));
    }

    @Override
    public void remove(Long watchId) {
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Watches with id %d not found.", watchId)));
        watchRepository.delete(watch);
    }

    @Override
    public WatchDto findById(Long watchId) {
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Watches with id %d not found.", watchId)));
        return convertToDto(watch);
    }

    @Override
    public List<WatchDto> findAll() {
        Iterable<Watch> watches = watchRepository.findAll();
        return StreamSupport.stream(watches.spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeAll() {
        watchRepository.deleteAll();
    }

    private WatchDto convertToDto(Watch watch) {
        return modelMapper.map(watch, WatchDto.class);
    }

    private Watch convertToEntity(WatchDto watchDto)  {
        return modelMapper.map(watchDto, Watch.class);
    }
}
