package estacionamento;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;

public class Estacionamento implements IEstacionamento{
    public int TOT_VAGAS = 10;
    public int TARIFA = 5;

    HashSet<Veiculo> veiculos = new HashSet<>();

    // Methods Interface
    @Override
    public void cadastrarVeiculo(String placa, String modelo, String horaDeEntrada) {
        System.out.println("------------------------------------ CADASTRO DE VEÍCULO ------------------------------------");

        Veiculo v = new Veiculo(placa, modelo, horaDeEntrada);

            if(veiculos.size() == TOT_VAGAS){
                System.out.println("Não foi possível cadastrar o veículo porque o estacionamento está 100% ocupado.");
            } else if(veiculos.contains(v)){
                System.out.println("⚠ O veículo de placa " + placa + ", que entrou " + horaDeEntrada + ", já foi cadastrado. Hashcode: " +
                        v.hashCode() + ".");
            } else {
                veiculos.add(v);
                System.out.println("Veículo cadastrado com sucesso! Hashcode: " + v.hashCode());
            }

        System.out.println("O número de carros estacionados é: " + veiculos.size());
    }


    @Override
    public void exibirVeiculos() {
        if(veiculos.isEmpty()){
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            int index = 1;
            for(Veiculo v: veiculos){
                System.out.println("------------------------------------ Exibindo Veículo " + index + " ------------------------------------");
                System.out.println(v.getModelo());
                System.out.println(v.getPlaca());
                System.out.println(v.getHoraDeEntrada());
                index += 1;
            }
        }

    }


    @Override
    public void removerVeiculo(String placaVeiculoRemover) {
        System.out.println("------------------------------------ SISTEMA DE REMOÇÃO DE VEÍCULOS ------------------------------------");
        HashSet<Veiculo> veicBefore = new HashSet<>(veiculos);

        for(Veiculo v: veiculos){
            if(v.getPlaca().equals(placaVeiculoRemover)){
                System.out.println("\uD83D\uDDD1 Removendo o veículo " + v.getModelo() + " de placa " + v.getPlaca() + ".");
                veiculos.remove(v);
            }
        }
        HashSet<Veiculo> veicAfter = veiculos;

        if(veicBefore.equals(veicAfter)){
            System.out.println("⚠ Não foi encontrado veículo de placa " + placaVeiculoRemover + ".");
        }
    }

    @Override
    public void exibirVagas() {
        System.out.println("------------------------------------ SISTEMA DE EXIBIÇÃO DE VAGAS " +
                "------------------------------------");

        int veicEstacionados = veiculos.size();
        int vagas = TOT_VAGAS - veicEstacionados;
        System.out.println("Ainda restam "+ vagas + " vagas para estacionar.");
    }

    @Override
    public long[] calcTempoPermanencia(String placaVeiculo, String horaDeSaida, boolean exibirMsg) {
        if(exibirMsg){
            System.out.println("------------------------------------ SISTEMA DE TEMPO DE PERMANÊNCIA " +
                    "------------------------------------");
        }


        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int mon = now.getMonthValue();
        int year = now.getYear();
        int hora = Integer.parseInt(horaDeSaida.split(":")[0]);
        int min =  Integer.parseInt(horaDeSaida.split(":")[1]);
        String horaSaida = String.format("%02d:%02d", hora, min);

        String saidaSTR = String.format("%04d-%02d-%02d" + "T" + horaSaida + ":00", year,mon,day);
        LocalDateTime saidaLDT = LocalDateTime.parse(saidaSTR);

//        System.out.println(saidaSTR);
//        System.out.println(saidaLDT);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for(Veiculo v: veiculos){
            if(v.getPlaca().equals(placaVeiculo)){
                String entrada = v.getHoraDeEntrada();
                LocalDateTime entradaLDT = LocalDateTime.parse(entrada, formatter);
//                System.out.println(entradaLDT);
                Duration deltaTempoPermanencia = Duration.between(entradaLDT, saidaLDT);

                long deltaHours = deltaTempoPermanencia.toHours();
                long deltaMinutes = deltaTempoPermanencia.toMinutes() - deltaHours*60;

                if(exibirMsg){
                    System.out.println("O veículo " + v.getModelo() + " de placa " + v.getPlaca() + " permaneceu no " +
                            "estacionamento por " + deltaHours + " horas e " + deltaMinutes + " minutos." );
                }
                return new long[]{deltaHours, deltaMinutes};
            }
        }
        // Se o veículo não for encontrado, retorna -1 indicando erro
        return new long[]{-1, -1};
    }

    @Override
    public void cobrarTarifa(String placaVeiculo, String horaDeSaida, boolean exibirMsg) {
        System.out.println("------------------------------------ SISTEMA DE COBRANÇA DE TARIFA " +
                "------------------------------------");
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        long[] dataHora = calcTempoPermanencia(placaVeiculo, horaDeSaida, exibirMsg);

        long horas = dataHora[0];
        long min = dataHora[1];

        float totTarifa = horas*TARIFA + min/60f*TARIFA;

        String tarifaMsg =
                String.format("A tarifa cobrada para o veículo " + placaVeiculo + " é de " + formatoMoeda.format(totTarifa) + ".");
        System.out.println(tarifaMsg);
    }

    @Override
    public void sairDoPrograma() {

    }
}
