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
@RequestMapping("/compile")
@EnableAutoConfiguration
@CrossOrigin
public class RestController {

    @Autowired
    private CompilerService service;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public SourceCode runCompile(@RequestBody SourceCode sourceCode) throws IOException {

        sourceCode.setOutput(service.doCompile(sourceCode));
        //service.resetCompilation(sourceCode);
        //System.out.println(sourceCode.getSourceCode());
        return sourceCode;
    }
}
