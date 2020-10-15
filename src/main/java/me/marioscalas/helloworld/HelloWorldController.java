package me.marioscalas.helloworld;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Getter
class SaluteResponse {
    private final String salute;
}

@RestController
@RequestMapping( "/hello" )
public class HelloWorldController {

    @GetMapping
    public SaluteResponse getSalute() {
        return new SaluteResponse( "Hello Kubernetes World!" );
    }
}
