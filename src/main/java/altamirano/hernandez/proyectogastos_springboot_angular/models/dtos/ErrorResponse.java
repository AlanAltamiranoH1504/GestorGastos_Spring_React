package altamirano.hernandez.proyectogastos_springboot_angular.models.dtos;

import java.time.LocalDate;

public class ErrorResponse {
    private String error;
    private String excepcion;
    private String message;
    private LocalDate date;

    public ErrorResponse(String error, String excepcion, String message, LocalDate date) {
        this.error = error;
        this.excepcion = excepcion;
        this.message = message;
        this.date = date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(String excepcion) {
        this.excepcion = excepcion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
