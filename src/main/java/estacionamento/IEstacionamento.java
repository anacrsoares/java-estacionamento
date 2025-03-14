package estacionamento;

public interface IEstacionamento {
    public abstract void cadastrarVeiculo(String placa, String modelo, String horaDeEntrada);
    public abstract void exibirVeiculos();
    public void removerVeiculo(String placaVeiculoRemover);
    public abstract void exibirVagas();
    public abstract long[] calcTempoPermanencia(String placaVeiculo, String horaDeSaida, boolean exibirMsg);
    public abstract void cobrarTarifa(String placaVeiculo, String horaDeSaida, boolean exibirMsg);
    public abstract void sairDoPrograma();
}
