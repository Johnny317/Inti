/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Jhony Espinoza
 */
public class Modelo_Actividades {
       private int idActividad;
    private int idUnidad;
    private String tipo; // 'crucigrama', 'audio_test', 'drag_drop'
    private String pregunta;
    private String recursoUrl;
    private char respuestaCorrecta;

    public Modelo_Actividades() {
    }

    public Modelo_Actividades(int idActividad, int idUnidad, String tipo, String pregunta, String recursoUrl, char respuestaCorrecta) {
        this.idActividad = idActividad;
        this.idUnidad = idUnidad;
        this.tipo = tipo;
        this.pregunta = pregunta;
        this.recursoUrl = recursoUrl;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRecursoUrl() {
        return recursoUrl;
    }

    public void setRecursoUrl(String recursoUrl) {
        this.recursoUrl = recursoUrl;
    }

    public char getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(char respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }
    
}
