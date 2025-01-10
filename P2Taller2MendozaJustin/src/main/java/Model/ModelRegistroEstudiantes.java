package Model;

import View.RegistroEstudiantes;

public class ModelRegistroEstudiantes {
    private String nombre, apellido, cedula, carrera, creditos, promedio, nivel;

    public ModelRegistroEstudiantes(String nombre, String apellido, String cedula, String carrera, String creditos, String promedio, String nivel) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.carrera = carrera;
        this.creditos = creditos;
        this.promedio = promedio;
        this.nivel = nivel;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    // Método para validar los datos
    public boolean validarDatos(RegistroEstudiantes view) {
        boolean esValido = true;

        // Validación de nombre
        if (nombre == null || nombre.isEmpty()) {
            view.errNombre.setText("El nombre no puede estar vacío");
            view.errNombre.setVisible(true);
            esValido = false;
        } else {
            view.errNombre.setVisible(false);
        }

        // Validación de apellido
        if (apellido == null || apellido.isEmpty()) {
            view.errApellido.setText("El apellido no puede estar vacío");
            view.errApellido.setVisible(true);
            esValido = false;
        } else {
            view.errApellido.setVisible(false);
        }

        // Validación de cédula
        if (cedula == null || cedula.isEmpty() || !cedula.matches("\\d{10}")) {
            view.errCedula.setText("La cédula debe ser un número de 10 dígitos");
            view.errCedula.setVisible(true);
            esValido = false;
        } else {
            view.errCedula.setVisible(false);
        }

        // Validación de carrera
        if (carrera == null || carrera.equalsIgnoreCase("SELECC...")) {
            view.errCarrera.setText("Debe seleccionar una carrera válida");
            view.errCarrera.setVisible(true);
            esValido = false;
        } else {
            view.errCarrera.setVisible(false);
        }

        // Validación de créditos
        if (creditos == null || !creditos.matches("\\d+") || Integer.parseInt(creditos) <= 0 || Integer.parseInt(creditos)>40) {
            view.errCreditos.setText("Los créditos deben ser entre 1-40");
            view.errCreditos.setVisible(true);
            esValido = false;
        } else {
            view.errCreditos.setVisible(false);
        }

        // Validación de promedio
        if (promedio == null || !promedio.matches("\\d+(\\.\\d+)?") || Float.parseFloat(promedio) < 0 || Float.parseFloat(promedio) > 20) {
            view.errPromedio.setText("El promedio debe ser un número entre 0 y 20");
            view.errPromedio.setVisible(true);
            esValido = false;
        } else {
            view.errPromedio.setVisible(false);
        }

        // Validación de nivel
        if (nivel == null || (!nivel.equalsIgnoreCase("Pregrado") && !nivel.equalsIgnoreCase("Posgrado"))) {
            view.errNivel.setText("Debe seleccionar un nivel válido (Pregrado o Posgrado)");
            view.errNivel.setVisible(true);
            esValido = false;
        } else {
            view.errNivel.setVisible(false);
        }

        return esValido;
    }
}
