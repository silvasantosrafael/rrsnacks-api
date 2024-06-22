package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.service.implement.SnackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snacks")
@CrossOrigin(origins = "*")
public class SnackController {
    private final SnackService snackService;

    public SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

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
    public ResponseEntity<SnackDTO> createSnack(@ModelAttribute SnackDTO snackDTO) throws FileNotFoundException {
        if (snackDTO.getSnackId() == null) {
            SnackDTO dto = snackService.saveSnack(snackDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
        return ResponseEntity.ok().body(snackService.saveSnack(snackDTO));
    }
}
