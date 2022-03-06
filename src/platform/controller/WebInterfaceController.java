package platform.controller;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import platform.bean.CodePOJO;
import platform.bean.CodeSnippet;
import platform.service.CodeService;

import java.util.List;
import java.util.UUID;

/**
 * @author Fulkin
 * Created on 25.02.2022
 */

@Controller
@RequestMapping("/code")
public class WebInterfaceController {

    @Autowired
    CodeService codeService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    String getCodeHtml(@PathVariable UUID id, Model model) {
        CodePOJO code = codeService.getCodePojoById(id);
        model.addAttribute("code", code);
        model.addAttribute("codeSnippet", code.getCode());
        model.addAttribute("codeDate", code.getDate());
        model.addAttribute("codeTimeToDestroy", code.getTime());
        model.addAttribute("codeViewsToDestroy", code.getViews());
        return "home/code_view";
    }

    @GetMapping("/latest")
    String getLastTenCode(Model model) {
        List<CodePOJO> pojoList = codeService.getLastTenCode();
        model.addAttribute("codeList", pojoList);
        return "home/code_view_list";
    }

    @GetMapping(value = "/new")
    String getPageForNewCode() {
        return "home/create_code";
    }
}
