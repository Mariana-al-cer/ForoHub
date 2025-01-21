package com.aluracursos.ForoHub.domain.topico;


import com.aluracursos.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.transaction.Status;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;

    @Column (unique = true)
    private String mensaje;
    private LocalDateTime fechaPublicacion;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String curso;

    public Topico(DatosDeRegistroTopico datosTopico){
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.status = true;
        this.usuario = datosTopico.usuario();
        this.curso = datosTopico.curso();
    }

    public void actualizarDatos(@Valid DatosDeActualizacionTopico datosDeActualizacionTopico) {
        if (datosDeActualizacionTopico.titulo() != null) {
            this.titulo = datosDeActualizacionTopico.titulo();
        }
        if (datosDeActualizacionTopico.mensaje() != null) {
            this.mensaje = datosDeActualizacionTopico.mensaje();
        }
        if (datosDeActualizacionTopico.usuario() != null) {
            this.usuario = datosDeActualizacionTopico.usuario();
        }
        if (datosDeActualizacionTopico.curso() != null) {
            this.curso = datosDeActualizacionTopico.curso();
        }
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void desactivarTopico(){
        this.status = false;
    }
}
