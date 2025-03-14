package estacionamento;

public class Main {
    public static void main(String[] args) {
        Estacionamento estacionamentoCB = new Estacionamento();

        estacionamentoCB.cadastrarVeiculo("ABC-1234", "Honda Civic", "08:20");
        estacionamentoCB.cadastrarVeiculo("ABC-1234", "Honda CRV", "08:20");
        estacionamentoCB.cadastrarVeiculo("ABC-1233", "Honda CRV", "08:20");
        estacionamentoCB.cadastrarVeiculo("ABC-1115", "Renault Clio", "09:25");
        estacionamentoCB.removerVeiculo("ABC-1233");
        estacionamentoCB.removerVeiculo("ABC-1211");
        estacionamentoCB.exibirVagas();
        estacionamentoCB.calcTempoPermanencia("ABC-1234", "14:34", true);
        estacionamentoCB.calcTempoPermanencia("ABC-1115", "14:55", true);
        estacionamentoCB.cobrarTarifa("ABC-1234", "14:34", false);
        estacionamentoCB.cobrarTarifa("ABC-1115", "14:55", false);


        estacionamentoCB.exibirVeiculos();

    }
}
