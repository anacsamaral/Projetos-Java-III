package unoeste.fipp.alospring.restcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.alospring.entities.Horario;

import java.time.LocalTime;

@RestController
@RequestMapping(value = "hora")
public class AloSringRestController {

    record Periodo(String periodo) {}

    // endpoints, apis, serviço
    @GetMapping(value = "get-horario")
    public ResponseEntity<Object> getHorario() {
        LocalTime horario = LocalTime.now();
        Horario hora = new Horario(horario.getHour(), horario.getMinute());
        return ResponseEntity.ok(hora);
    }

    @GetMapping(value = "get-periodo")
    public ResponseEntity<Object> getPeriodo() {
        LocalTime horario = LocalTime.now();
        String periodo;

        if(horario.getHour() >= 12 && horario.getHour() < 18)
            periodo = "Tarde";
        else if(horario.getHour() >= 18)
            periodo = "Noite";
        else
            periodo = "Manhã";

        return ResponseEntity.ok(new Periodo(periodo));
    }
}
