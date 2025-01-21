package com.aluracursos.ForoHub.controller;

import com.aluracursos.ForoHub.domain.topico.*;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepositorio topicoRepositorio;

    //Crear nuevo topico
    @PostMapping
    @RequestMapping("/registro")
    public ResponseEntity<DatosDeRespuestaTopic>crearTopico(@RequestBody @Valid DatosDeRegistroTopico datosDeRegistroTopico){
        Topico topico = topicoRepositorio.save(new Topico(datosDeRegistroTopico));
        DatosDeRespuestaTopic datosDeRespuestaTopic = new DatosDeRespuestaTopic(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaPublicacion(),
                topico.getUsuario().getNombre(),
                topico.getCurso());
        return ResponseEntity.ok(datosDeRespuestaTopic);
    }

    //Lista de todos los topicos
    public ResponseEntity<Page<DatosDeTodosLosTopicos>>ListaTopicos( @PageableDefault(size = 10) Pageable paginacion)
    {
        return ResponseEntity.ok(topicoRepositorio.findAll(paginacion).map(DatosDeTodosLosTopicos::new));
    }

    //Actualizar Topico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id,
                                              @RequestBody @Valid DatosDeActualizacionTopico datosDeActualizacionTopico) {
        Optional<Topico> optionalTopico = topicoRepositorio.findById(id);

        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.actualizarDatos(datosDeActualizacionTopico);
            topicoRepositorio.save(topico);

            var nuevoDatosTopico = new DatosDeRespuestaTopic(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaPublicacion(),
                    topico.getUsuario().getNombre(),
                    topico.getCurso()
            );
            return ResponseEntity.ok(nuevoDatosTopico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El tópico con ID " + id + " no fue encontrado.");
        }
    }

    //Mostrar Topico Especifico
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTopicoPorId(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepositorio.findById(id);

        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();

            var datosDelTopico = new DatosGeneralesTopico(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaPublicacion(),
                    topico.getStatus(),
                    topico.getUsuario().getNombre(),
                    topico.getCurso().toString()
            );

            return ResponseEntity.ok(datosDelTopico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El tópico con ID " + id + " no fue encontrado.");
        }
    }

    //Eliminar un Topico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id){
        Optional<Topico> optionalTopico = topicoRepositorio.findById(id);

        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.desactivarTopico();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El tópico no fue encontrado");
        }
    }

}
