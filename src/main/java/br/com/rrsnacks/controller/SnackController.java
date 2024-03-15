package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.service.FileUploadService;
import br.com.rrsnacks.service.implement.SnackService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snacks")
@CrossOrigin(origins = "*")
public class SnackController {
    @Autowired()
    SnackService snackService;
    @Autowired
    FileUploadService fileUploadService;

    @GetMapping()
    public ResponseEntity<List<SnackDTO>> getAllSnacks() {
        return ResponseEntity.ok().body(snackService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SnackDTO> getSnack(@PathVariable Long id) {
        Optional<SnackDTO> snackDTO = snackService.getById(id);
        return snackDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<SnackDTO> createSnack(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("snack") String json) throws FileNotFoundException {
        Gson gson = new Gson();
        SnackDTO snackDTO = gson.fromJson(json, SnackDTO.class);
        if (snackDTO.getSnackId() == null) {
            SnackDTO dto = snackService.saveSnack(imageFile, snackDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }

        return ResponseEntity.ok().body(snackService.saveSnack(imageFile, snackDTO));
    }
}
