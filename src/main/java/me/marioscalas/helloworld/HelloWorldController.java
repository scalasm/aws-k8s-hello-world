package me.marioscalas.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

record SaluteResponse(String salute) {}

@RestController
@RequestMapping( "/hello" )
public class HelloWorldController {

    @GetMapping
    public SaluteResponse getSalute() {
        return new SaluteResponse( "Hello Kubernetes World from pod: " + System.getenv("MY_POD_NAME") );
    }
}
