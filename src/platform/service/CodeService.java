package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.bean.CodePOJO;
import platform.bean.CodeSnippet;
import platform.database.CodeRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Fulkin
 * Created on 27.02.2022
 */

@Service
public class CodeService {

    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    private static final int COUNT_LAST_CODE = 10;

    @Autowired
    CodeRepository codeRepository;

    public CodePOJO getCodePojoById(UUID id) {
        Optional<CodeSnippet> codeById = codeRepository.findById(id);
        if (codeById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CodeSnippet codeSnippet = codeById.get();
        CodePOJO.CodePOJOBuilder builderCodePOJO = CodePOJO.builder()
                .id(codeSnippet.getId())
                .code(codeSnippet.getCode())
                .date(codeSnippet.getDate().format(formatter));

        long secondsToDestroy = 0;

        if (codeSnippet.getTime() != null) {
            LocalDateTime timeDestroy = codeSnippet.getTime();
            Duration duration = Duration.between(LocalDateTime.now(), timeDestroy);
            secondsToDestroy = duration.getSeconds();
            if (secondsToDestroy <= 0) {
                codeRepository.delete(codeSnippet);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            builderCodePOJO = builderCodePOJO.timeRestrictions(true);
        }

        int views = codeSnippet.getViews();
        if (codeSnippet.isViewsRestrictions()) {
            codeSnippet.setViews(--views);
            if (views < 0) {
                codeRepository.delete(codeSnippet);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            codeRepository.save(codeSnippet);
            builderCodePOJO = builderCodePOJO.viewsRestrictions(true);
        }

        return builderCodePOJO.time(secondsToDestroy)
                .views(views)
                .build();
    }

    public List<CodePOJO> getLastTenCode() {
        List<CodeSnippet> allCodes = codeRepository.findByTimeRestrictionsAndViewsRestrictionsOrderByDateDesc(false, false);
        return allCodes.stream()
                .limit(COUNT_LAST_CODE)
                .map(code -> CodePOJO.builder()
                        .id(code.getId())
                        .code(code.getCode())
                        .date(code.getDate().format(formatter))
                        .build()
                )
                .collect(Collectors.toList());
    }

    public UUID setCode(CodeSnippet codeSnippet) {
        codeSnippet.setDate(LocalDateTime.now());
        if (codeSnippet.getTime() != null) {
            codeSnippet.setTimeRestrictions(true);
        }
        if (codeSnippet.getViews() > 0) {
            codeSnippet.setViewsRestrictions(true);
        }
        return codeRepository.save(codeSnippet).getId();
    }
}
