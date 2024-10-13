package com.gloit.tukohama.mail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gloit.tukohama.controller.error.MensajeError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/mail")
public class MailController {

    @Autowired
    MailServiceImpl service;

    @PostMapping(value = "/simple")
    @PreAuthorize("hasRole('ROLE_INVITADO')")
    public ResponseEntity<Object> simple(@Valid @RequestBody MailForm form, BindingResult result) throws Exception {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
        Mail mail=form.toPojo();
        service.sendSimpleMail(mail);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(mail.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

        return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
    }

    private String formatearError(BindingResult result) throws JsonProcessingException {
        //primero transformamos la lista de errores devuelta por Java Bean Validation
        List<Map<String, String>> errores = result.getFieldErrors().stream().map(err -> {
            Map<String, String> error = new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());
        MensajeError e1 = new MensajeError();
        e1.setCodigo("01");
        e1.setMensajes(errores);

        //ahora usamos la librería Jackson para pasar el objeto a json
        ObjectMapper maper = new ObjectMapper();
        String json = maper.writeValueAsString(e1);
        return json;
    }

    private String getError(String code, String err, String descr) throws JsonProcessingException {
        MensajeError e1 = new MensajeError();
        e1.setCodigo(code);
        ArrayList<Map<String, String>> errores = new ArrayList<>();
        Map<String, String> error = new HashMap<String, String>();
        error.put(err, descr);
        errores.add(error);
        e1.setMensajes(errores);

        //ahora usamos la librería Jackson para pasar el objeto a json
        ObjectMapper maper = new ObjectMapper();
        String json = maper.writeValueAsString(err);
        return json;
    }
}
