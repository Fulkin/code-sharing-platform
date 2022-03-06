package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.bean.CodeSnippet;
import platform.bean.CodePOJO;
import platform.service.CodeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Fulkin
 * Created on 27.02.2022
 */

@RestController
@RequestMapping("/api/code")
public class CodeApiController {

    @Autowired
    CodeService codeService;

    @GetMapping(value = "/{id}")
    CodePOJO getCodePojo(@PathVariable UUID id) {
        return codeService.getCodePojoById(id);
    }

    @PostMapping(value = "/new")
    Map<String, String> putCode(@RequestBody CodePOJO codePOJO) {
        LocalDateTime timeToDestroy = null;
        if (codePOJO.getTime() > 0) {
            timeToDestroy = LocalDateTime.now().plusSeconds(codePOJO.getTime());
        }
        UUID id = codeService.setCode(new CodeSnippet(
                codePOJO.getCode(),
                timeToDestroy,
                codePOJO.getViews()
        ));
        return Map.of("id", id.toString());
    }

    @GetMapping("/latest")
    List<CodePOJO> getLastTenCode() {
        return codeService.getLastTenCode();
    }
}
