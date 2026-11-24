package org.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utils.UtilContexto;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmpresaTest {
    private Empresa empresa;

    //Antes de cada test
    @BeforeEach
    public void setUp() {
        empresa = new Empresa();
        UtilContexto.cargadorDeContexto(empresa);
    }

    @Test
    public void testDosEmpleadosDeCadaTipo() {
        Set<Empleado> empleados = empresa.getEmpleadoset();
        //6 empleados creados
        assertEquals(6, empleados.size());

        int directivos = 0;
        int tecnicos = 0;
        int oficiales = 0;

        for (Empleado empleado : empleados) {
            if (empleado instanceof Directivo) {
                directivos++;
            } else if (empleado instanceof Tecnico) {
                tecnicos++;
            } else if (empleado instanceof Oficial) {
                oficiales++;
            }
        }

        assertEquals(2, directivos);
        assertEquals(2, tecnicos);
        assertEquals(2, oficiales);
    }
}