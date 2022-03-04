package student.ntnu.no.oving5server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import student.ntnu.no.oving5server.model.SourceCode;
import student.ntnu.no.oving5server.service.CompilerService;

import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
@EnableAutoConfiguration
@CrossOrigin
public class RestController {

    @Autowired
    private CompilerService service;

    @PostMapping(value = "/compile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void doCompile(@RequestBody SourceCode sourceCode) throws IOException {
        service.doCompile(sourceCode);
    }

    @PostMapping(value = "/compile/run", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public SourceCode runCode(@RequestBody SourceCode sourceCode) throws IOException {
        sourceCode.setOutput(service.runCode(sourceCode));
        //System.out.println(sourceCode.getSourceCode());
        return sourceCode;
    }
}
