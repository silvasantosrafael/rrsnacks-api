package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
        SnackDTO snackDTO = snackService.getById(id);
        return snackDTO != null ? ResponseEntity.ok().body(snackDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("create")
    public ResponseEntity<SnackDTO> createSnack(@RequestBody SnackDTO snackDTO) {
        return ResponseEntity.created(URI.create("")).body(snackService.create(snackDTO));
    }
}
