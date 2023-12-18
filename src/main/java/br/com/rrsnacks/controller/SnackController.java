package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.service.implement.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snacks")
@CrossOrigin(origins = "http://localhost:5500")
public class SnackController {
    @Autowired()
    SnackService snackService;

    @GetMapping()
    public ResponseEntity<List<SnackDTO>> getAllSnacks() {
        return ResponseEntity.ok().body(snackService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SnackDTO> getSnack(@PathVariable Long id) {
        Optional<SnackDTO> snackDTO = snackService.getById(id);
        return snackDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("save")
    public ResponseEntity<SnackDTO> createSnack(@RequestBody SnackDTO snackDTO) {
        if (snackDTO.getSnackId() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(snackService.saveOrMerge(snackDTO));
        }

        return ResponseEntity.ok().body(snackService.saveOrMerge(snackDTO));
    }
}
