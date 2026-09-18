package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Pessoa;

public class ClienteController {

    private final String HOST = "localhost";
    private final int PORTA = 12345;

    public Pessoa enviarDados(String nome, String dataNascimento) throws Exception {

        Socket socket = new Socket(HOST, PORTA);

        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
        saida.flush();

        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

        saida.writeObject(nome);
        saida.writeObject(dataNascimento);
        saida.flush();

        Pessoa pessoa = (Pessoa) entrada.readObject();

        entrada.close();
        saida.close();
        socket.close();

        return pessoa;
    }
}