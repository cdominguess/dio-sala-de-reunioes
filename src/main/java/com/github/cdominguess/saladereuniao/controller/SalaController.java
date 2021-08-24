package com.github.cdominguess.saladereuniao.controller;

import com.github.cdominguess.saladereuniao.exception.ResourceNotFoundException;
import com.github.cdominguess.saladereuniao.model.Sala;
import com.github.cdominguess.saladereuniao.repository.SalaRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/salas")
public class SalaController {
    private SalaRepository salaRepository;

    @GetMapping("/")
    public List<Sala> listar() {
        return salaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Sala objSala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala com ID " + id + " não encontrada."));

        return ResponseEntity.ok().body(objSala);
    }

    @PostMapping("/")
    public Sala criar(@Valid @RequestBody Sala objReqSala) {
        return salaRepository.save(objReqSala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> atualizar(@PathVariable long id, @Valid @RequestBody Sala objReqSala) throws ResourceNotFoundException {
        Sala objSala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Sala com ID \" + id + \" não encontrada.")));

        objSala.setNome(objReqSala.getNome());
        objSala.setDataCadastro(objReqSala.getDataCadastro());
        objSala.setHoraInicial(objReqSala.getHoraInicial());
        objSala.setHoraFinal(objReqSala.getHoraFinal());

        final Sala updateSala = salaRepository.save(objSala);

        return ResponseEntity.ok(updateSala);
    }
}