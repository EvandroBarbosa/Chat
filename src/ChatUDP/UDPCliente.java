/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatUDP;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;


/**
 *
 * @author vando
 */
public class UDPCliente{
    
    public static void main(String[] args) throws IOException{
                         
        try {
                                          
                // lê o buffer do teclado
                BufferedReader msgUsuario = new BufferedReader(new InputStreamReader(System.in));
                // cria um socket para o cliente
                DatagramSocket clientSocket = new DatagramSocket();
                // pega o inderenço ip do cliente
                InetAddress enderecoIP = InetAddress.getByName("localhost");
                // array que vai guarda os bytes para envia ao servidor
                byte[] pacoteEnviado = new byte[1024];
                // array que vai guarda os bytes que chegarem do servidor
                byte[] pacoteRecebido = new byte[1024];
              while(true){
                // pega o buffer lido do teclado e tranforma em uma String
                String msg = msgUsuario.readLine();
                // pega os bytes da String dados que contém os bytes lidos do teclado
                pacoteEnviado = msg.getBytes();

                /* cria um pacote para envia ao servidor onde passamos o texto, o ip do cliente
                   e a porta que o servidor esta escutando  */
                DatagramPacket sendPacket = new DatagramPacket(pacoteEnviado, pacoteEnviado.length, enderecoIP, 6739);
                // envia o pacote
                clientSocket.send(sendPacket);

                // aqui o cliente fica esperando um pacote
                DatagramPacket receivePacket = new DatagramPacket(pacoteRecebido, pacoteRecebido.length);
                // aqui recebemnos um pacote que no caso sera mandado pelo servidor
                clientSocket.receive(receivePacket);
                //aqui pega a mensagem que o servidor mandou pelo pacote
                String msgRecebidas = new String(receivePacket.getData());
                    System.out.println("Servidor: "+ msgRecebidas);
              }    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
          
}
