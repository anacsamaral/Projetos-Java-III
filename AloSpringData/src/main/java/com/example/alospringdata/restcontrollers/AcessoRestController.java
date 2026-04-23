package com.example.alospringdata.restcontrollers;

import com.example.alospringdata.security.JWTTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apis/acesso")
public class AcessoRestController {
    @PostMapping("/autenticar")
    public ResponseEntity<Object> autenticar(String login, String senha)
    {
        String token="";
        if (login.equals("meu@email") && senha.equals("123")) {
            token = JWTTokenProvider.getToken(login, "ADM");
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("ACESSO NAO PERMITIDO",HttpStatus.NOT_ACCEPTABLE);
    }
}
