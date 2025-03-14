package estacionamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Veiculo{
    // Attributes
    private String placa;
    private String modelo;
    private String horaDeEntrada;

    // Other Methods
    protected String inputHoraDeEntrada(String horaMinuto){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy " + horaMinuto);
        String horaFormatter = now.format(formatter);
        return horaFormatter;
    }

    // Change Methods

    @Override
    public boolean equals(Object obj) {
//        if (this == obj) return true; // Veiculo v1 = new Veiculo("ABC-1234", "Toyota", "10:30") Veiculo v2 = v1
//        if (obj == null || getClass() != obj.getClass()) return false; // impede comparações erradas, como Veiculo vs String
        Veiculo veiculo = (Veiculo) obj; // Faz o cast para comparar com outro Veiculo
        return Objects.equals(placa, veiculo.placa); // Compara apenas a placa
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(placa));
    }

    @Override
    public String toString() {
        return "{\"Carro\": " + modelo + ", \"Placa\": " + placa + ", \"Hora_de_Entrada\": " + horaDeEntrada +
                "}";
    }

    // Constructor
    public Veiculo(String placa, String modelo, String horaDeEntrada){
        this.placa = placa;
        this.modelo = modelo;
        this.horaDeEntrada = inputHoraDeEntrada(horaDeEntrada);
    }

    // Getters 'n' Setters
    public String getPlaca(){
        return placa;
    }
    private void setPlaca(String placa){
        this.placa = placa;
    }

    public String getModelo(){
        return modelo;
    }
    private void setModelo(String modelo){
        this.modelo = modelo;
    }

    public String getHoraDeEntrada(){
        return horaDeEntrada;
    }
    private void setHoraDeEntrada(String horaDeEntrada){
        this.horaDeEntrada = horaDeEntrada;
    }
}
