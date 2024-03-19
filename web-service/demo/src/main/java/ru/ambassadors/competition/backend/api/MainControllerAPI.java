package ru.ambassadors.competition.backend.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.ambassadors.competition.backend.dtos.*;

import java.util.List;

@Tag(name = "Основное АПИ")
@RequestMapping("/ambassadors-competition")
public interface MainControllerAPI {
    @GetMapping("/ambassadors")
    List<AmbassadorDTO> getAmbassadors(@RequestParam(required = false, name = "category") Integer filterCategory,
                                       @RequestParam(name = "page") Integer pageIndex);
    @GetMapping("/ambassadors/{id}")
    AmbassadorDTO getAmbassadorByID(@PathVariable Integer id);
    @GetMapping("/winners")
    List<AmbassadorDTO> getWinners();
    @PostMapping("/users")
    void saveUsersVote(@RequestBody UserDTO userDTO);
}
