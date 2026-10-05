package state_observer;

public class PedidoNovo extends PedidoEstado {

    private PedidoNovo() {
    }

    private static PedidoNovo instance = new PedidoNovo();

    public static PedidoNovo getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Novo";
    }

    @Override
    public boolean pagar(Pedido pedido) {
        pedido.setEstado(PedidoPago.getInstance());
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoCancelado.getInstance());
        return true;
    }
}