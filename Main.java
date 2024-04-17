public class Main {
    public static void main(String[] args) {
        Ciudad ciudad1 = new Ciudad("Ciudad A", new String[]{"Ciudad B", "Ciudad C"});
        Ciudad ciudad2 = new Ciudad("Ciudad B", new String[]{"Ciudad A", "Ciudad D"});
        Virus virus1 = new Virus("Virus X");
        Virus virus2 = new Virus("Virus Y");
        Vacuna vacuna1 = new Vacuna("Vacuna Z");
        Vacuna vacuna2 = new Vacuna("Vacuna W");
        DatosPartida datosPartida = new DatosPartida();
        ControlDatos controlDatos = new ControlDatos();
        ControlPartida controlPartida = new ControlPartida();
        PantallaPartida pantallaPartida = new PantallaPartida();
        PantallaInicio pantallaInicio = new PantallaInicio();
        
        datosPartida.getCiudades().add(ciudad1);
        datosPartida.getCiudades().add(ciudad2);
        datosPartida.getVirus().add(virus1);
        datosPartida.getVirus().add(virus2);
        datosPartida.getVacunas().add(vacuna1);
        datosPartida.getVacunas().add(vacuna2);
        
        controlDatos.cargarDatos();
        controlPartida.iniciarPartida(datosPartida);
        
        pantallaInicio.mostrar();
    }
}
