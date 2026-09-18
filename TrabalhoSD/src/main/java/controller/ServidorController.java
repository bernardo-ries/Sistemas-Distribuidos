package controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import model.Pessoa;
import view.ServidorGUI;

public class ServidorController {

    private ArrayList<Pessoa> pessoas = new ArrayList<>();
    private ServidorGUI view;
    private final int PORTA = 12345;

    public ServidorController(ServidorGUI view) {
        this.view = view;
    }

    public void iniciarServidor() {

        Thread threadServidor = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ServerSocket servidor = new ServerSocket(PORTA);

                    adicionarLog("Servidor iniciado na porta " + PORTA);

                    while (true) {
                        adicionarLog("Aguardando cliente...");

                        Socket socket = servidor.accept();

                        adicionarLog("Cliente conectado.");

                        Thread threadCliente = new Thread(new ClienteHandler(socket, ServidorController.this));
                        threadCliente.start();
                    }

                } catch (Exception e) {
                    adicionarLog("Erro no servidor: " + e.getMessage());
                }
            }
        });

        threadServidor.start();
    }

    public synchronized Pessoa cadastrarPessoa(String nome, String dataNascimento) {

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().equalsIgnoreCase(nome) && pessoa.getDataNascimento().equals(dataNascimento)) {
                adicionarLog("Pessoa já cadastrada: " + nome);
                return pessoa;
            }
        }

        String email = gerarEmail(nome, dataNascimento);
        Pessoa novaPessoa = new Pessoa(nome, dataNascimento, email);

        pessoas.add(novaPessoa);

        view.adicionarPessoa(novaPessoa);

        adicionarLog("Pessoa cadastrada: " + nome);

        return novaPessoa;
    }

    private String gerarEmail(String nome, String dataNascimento) {

        String[] partesNome = nome.trim().split("\\s+");

        String primeiroNome = partesNome[0];
        String ultimoSobrenome = partesNome[partesNome.length - 1];

        String[] partesData = dataNascimento.split("/");
        String ano = partesData[2];

        String email = primeiroNome + "." + ultimoSobrenome + "." + ano + "@ufn.edu.br";

        return email.toLowerCase();
    }

    public void adicionarLog(String mensagem) {
        view.adicionarLog(mensagem);
    }
}