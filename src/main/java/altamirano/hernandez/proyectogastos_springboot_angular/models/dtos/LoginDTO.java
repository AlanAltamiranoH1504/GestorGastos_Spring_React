package altamirano.hernandez.proyectogastos_springboot_angular.models.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class LoginDTO {
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "El password es obligatorio")
    private String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(email, loginDTO.email) && Objects.equals(password, loginDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
