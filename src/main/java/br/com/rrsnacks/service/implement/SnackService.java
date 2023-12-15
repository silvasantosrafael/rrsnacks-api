package br.com.rrsnacks.service.implement;

import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.model.Snack;
import br.com.rrsnacks.repository.SnackRepository;
import br.com.rrsnacks.service.ServiceStrategy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SnackService implements ServiceStrategy<SnackDTO> {
    @Autowired
    SnackRepository snackRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<SnackDTO> getAll() {
        Iterable<Snack> snacks = snackRepository.findAll();
        return Arrays.asList(modelMapper.map(snacks, SnackDTO[].class));
    }

    @Override
    public Optional<SnackDTO> getById(Long id) {
        return snackRepository.findById(id)
                .map(snack -> modelMapper.map(snack, SnackDTO.class));
    }

    @Override
    public SnackDTO create(SnackDTO snackDTO) {
        Snack snackEntity = modelMapper.map(snackDTO, Snack.class);
        Snack snack = snackRepository.save(snackEntity);
        return modelMapper.map(snack, SnackDTO.class);
    }
}
