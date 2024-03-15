package br.com.rrsnacks.service.implement;

import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.model.Snack;
import br.com.rrsnacks.repository.SnackRepository;
import br.com.rrsnacks.service.FileUploadService;
import br.com.rrsnacks.service.ServiceStrategy;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SnackService implements ServiceStrategy<SnackDTO> {
    @Autowired
    SnackRepository snackRepository;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    ModelMapper mapper;


    @Override
    public List<SnackDTO> getAll() {
        return Arrays.stream(mapper.map(snackRepository.findAllGroupBySnackId(), SnackDTO[].class)).toList();
    }

    @Override
    public Optional<SnackDTO> getById(Long id) {
        return snackRepository.findById(id).map(snack -> mapper.map(snack, SnackDTO.class));
    }

    @Override
    public SnackDTO saveOrMerge(SnackDTO snackDTO) {
        Snack snackEntity = mapper.map(snackDTO, Snack.class);
        Snack snackCreated = snackRepository.save(snackEntity);

        return mapper.map(snackCreated, SnackDTO.class);
    }

    public SnackDTO saveSnack(MultipartFile imageFile, SnackDTO snackDTO) throws FileNotFoundException {
        try {
            if (!fileUploadService.isValidFile(imageFile)) {
                throw new FileNotFoundException();
            }
            String renameFile = fileUploadService.renameFile(imageFile);
            fileUploadService.uploadFile(imageFile, renameFile);
            snackDTO.setImage(renameFile);
        } catch (FileUploadException | FileNotFoundException e) {
            throw new FileNotFoundException("Image not found or invalid");
        }

        return saveOrMerge(snackDTO);
    }
}
