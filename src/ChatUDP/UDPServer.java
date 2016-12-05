/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author vando
 * criando o servidor
 */
public class UDPServer{
    
    public static void main(String[] args) throws Exception {
            //atributos   
            DatagramSocket serverSocket = new DatagramSocket(6739);
            
            // array que guardara os bytes do pacote recebido
                byte[] PacoteRecabido = new byte[1024];
                // array que guardara os bytes do pacote enviado
                byte[] PacoteEnviado = new byte[1024];
     
                try {
                
                while (true) {
            
                // aqui o servidor fica esperando um pacote
                DatagramPacket receivePacket = new DatagramPacket(PacoteRecabido, PacoteRecabido.length);
                // aqui recebemnos um pacote que no caso sera mandado pelo cliente
                serverSocket.receive(receivePacket);
                // aqui pegamos a mensagem que o cliente envia no pacote
                String mensagemCliente = new String(receivePacket.getData());
                // pega o endereço ip do cliente
                InetAddress host = receivePacket.getAddress();
                // pega a porta do cliente 
                int porta = receivePacket.getPort();
                
                // aqui pegamos o texto enviado pelo cliente
                String mensagemCapturada = mensagemCliente;
                // passamos os bytes do texto que a mensagemCapturada contém
                PacoteEnviado = mensagemCapturada.getBytes();
                /* cria um pacote para envia ao cliente onde passamos o texto, o ip do servidor
                   e a porta que o cliente esta escutando  */
                DatagramPacket sendPacket = new DatagramPacket(PacoteEnviado, PacoteEnviado.length, host, porta);
                // envia o pacote
                serverSocket.send(sendPacket);
                    System.out.println("Cliente: "+mensagemCapturada);

            }
                    
        }catch(IOException ex){
            ex.printStackTrace();
        }
            
    }    
}