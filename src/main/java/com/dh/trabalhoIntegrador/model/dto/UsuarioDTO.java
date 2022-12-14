package com.dh.trabalhoIntegrador.model.dto;

import com.dh.trabalhoIntegrador.model.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {

//    @NotBlank
    @Size(min=6)
    private String username;
//    @NotBlank
    @Size(min=6)
    private String password;


    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(this.username, this.password);
    }
}
