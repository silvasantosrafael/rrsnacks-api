package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.service.implement.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snacks")
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

    @PostMapping("create")
    public ResponseEntity<SnackDTO> createSnack(@RequestBody SnackDTO snackDTO) {
        return ResponseEntity.created(URI.create("")).body(snackService.saveOrMerge(snackDTO));
    }
}
