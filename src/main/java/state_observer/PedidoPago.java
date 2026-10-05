package state_observer;

public class PedidoPago extends PedidoEstado {

    private PedidoPago() {
    }

    private static PedidoPago instance = new PedidoPago();

    public static PedidoPago getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Pago";
    }

    @Override
    public boolean enviar(Pedido pedido) {
        pedido.setEstado(PedidoEnviado.getInstance());
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoCancelado.getInstance());
        return true;
    }
}