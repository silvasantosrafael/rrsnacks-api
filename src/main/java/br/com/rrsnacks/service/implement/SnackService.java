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
    ModelMapper mapper;


    @Override
    public List<SnackDTO> getAll() {
        return Arrays.stream(mapper.map(snackRepository.findAll(), SnackDTO[].class)).toList();
    }

    @Override
    public Optional<SnackDTO> getById(Long id) {
        return snackRepository.findById(id).map(snack -> mapper.map(snack, SnackDTO.class));
    }

    @Override
    public SnackDTO create(SnackDTO snackDTO) {
        Snack snackEntity = mapper.map(snackDTO, Snack.class);
        Snack snack = snackRepository.save(snackEntity);

        return mapper.map(snack, SnackDTO.class);
    }
}
