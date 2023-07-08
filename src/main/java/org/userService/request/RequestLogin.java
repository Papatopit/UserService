package org.userService.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
public class RequestLogin {
    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;
}
