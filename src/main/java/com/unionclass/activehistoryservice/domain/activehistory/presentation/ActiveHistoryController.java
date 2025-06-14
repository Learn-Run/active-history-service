package com.unionclass.activehistoryservice.domain.activehistory.presentation;

import com.unionclass.activehistoryservice.domain.activehistory.application.ActiveHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/active-history")
@Tag(name = "active-history")
public class ActiveHistoryController {

    private final ActiveHistoryService activeHistoryService;
}
