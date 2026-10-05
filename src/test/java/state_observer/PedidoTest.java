package state_observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    Pedido pedido;
    Cliente cliente;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setDescricao("Pedido 1");
        cliente = new Cliente("Cliente 1");
    }

    @Test
    void deveIniciarPedidoComoNovo() {
        assertEquals(
                PedidoNovo.getInstance(),
                pedido.getEstado()
        );
    }

    @Test
    void deveNotificarClienteAoPagarPedido() {
        cliente.acompanhar(pedido);

        pedido.pagar();

        assertEquals(
                "Cliente 1, pedido atualizado: Pedido{descricao='Pedido 1', estado=Pago}",
                cliente.getUltimaNotificacao()
        );
    }

    @Test
    void deveNotificarClienteAoEnviarPedido() {
        cliente.acompanhar(pedido);

        pedido.pagar();
        pedido.enviar();

        assertEquals(
                "Cliente 1, pedido atualizado: Pedido{descricao='Pedido 1', estado=Enviado}",
                cliente.getUltimaNotificacao()
        );
    }

    @Test
    void deveNotificarClienteAoEntregarPedido() {
        cliente.acompanhar(pedido);

        pedido.pagar();
        pedido.enviar();
        pedido.entregar();

        assertEquals(
                "Cliente 1, pedido atualizado: Pedido{descricao='Pedido 1', estado=Entregue}",
                cliente.getUltimaNotificacao()
        );
    }

    @Test
    void deveNotificarClienteAoCancelarPedido() {
        cliente.acompanhar(pedido);

        pedido.cancelar();

        assertEquals(
                "Cliente 1, pedido atualizado: Pedido{descricao='Pedido 1', estado=Cancelado}",
                cliente.getUltimaNotificacao()
        );
    }

    @Test
    void deveNotificarDoisClientes() {
        Cliente cliente2 = new Cliente("Cliente 2");

        cliente.acompanhar(pedido);
        cliente2.acompanhar(pedido);

        pedido.pagar();

        assertEquals(
                "Cliente 1, pedido atualizado: Pedido{descricao='Pedido 1', estado=Pago}",
                cliente.getUltimaNotificacao()
        );

        assertEquals(
                "Cliente 2, pedido atualizado: Pedido{descricao='Pedido 1', estado=Pago}",
                cliente2.getUltimaNotificacao()
        );
    }

    @Test
    void naoDeveNotificarClienteNaoInscrito() {
        pedido.pagar();

        assertEquals(
                null,
                cliente.getUltimaNotificacao()
        );
    }

    @Test
    void naoDeveNotificarClienteQuandoEstadoNaoMudar() {
        cliente.acompanhar(pedido);

        assertFalse(pedido.enviar());

        assertEquals(
                null,
                cliente.getUltimaNotificacao()
        );
    }

    @Test
    void deveNotificarApenasClienteDoPedidoAcompanhado() {
        Pedido pedido2 = new Pedido();
        pedido2.setDescricao("Pedido 2");

        Cliente cliente2 = new Cliente("Cliente 2");

        cliente.acompanhar(pedido);
        cliente2.acompanhar(pedido2);

        pedido.pagar();

        assertEquals(
                "Cliente 1, pedido atualizado: Pedido{descricao='Pedido 1', estado=Pago}",
                cliente.getUltimaNotificacao()
        );

        assertEquals(
                null,
                cliente2.getUltimaNotificacao()
        );
    }
}