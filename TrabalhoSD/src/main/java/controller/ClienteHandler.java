package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Pessoa;

public class ClienteHandler implements Runnable {

    private Socket socket;
    private ServidorController servidor;

    public ClienteHandler(Socket socket, ServidorController servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.flush();

            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

            String nome = (String) entrada.readObject();
            String dataNascimento = (String) entrada.readObject();

            servidor.adicionarLog("Dados recebidos: " + nome);

            Pessoa pessoa = servidor.cadastrarPessoa(nome, dataNascimento);

            saida.writeObject(pessoa);
            saida.flush();

            entrada.close();
            saida.close();
            socket.close();

        } catch (Exception e) {
            servidor.adicionarLog("Erro ao atender cliente: " + e.getMessage());
        }
    }
}