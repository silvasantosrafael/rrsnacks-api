package br.com.rrsnacks.service;

import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.model.Snack;
import br.com.rrsnacks.repository.SnackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SnackService {
    @Autowired
    SnackRepository snackRepository;
    @Autowired
    ModelMapper modelMapper;


    public List<SnackDTO> getAll() {
        Iterable<Snack> snacks = snackRepository.findAll();
        return Arrays.asList(modelMapper.map(snacks, SnackDTO[].class));
    }

    public SnackDTO getById(Long id) {
        Optional<Snack> snackOptional = snackRepository.findById(id);
        return snackOptional
                .map(snack -> modelMapper.map(snack, SnackDTO.class))
                .orElse(null);
    }

    public SnackDTO create(SnackDTO snackDTO) {
        Snack snackEntity = modelMapper.map(snackDTO, Snack.class);
        Snack snack = snackRepository.save(snackEntity);
        return modelMapper.map(snack, SnackDTO.class);
    }
}
