package by.bsuir.kostyademens.weatherapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserReqDto {
    private String email;
    private String password;
}
